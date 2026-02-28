package src.controlador;

import src.vista.Landingpage;
import src.vista.Reconocimiento_facial;
import src.modelo.Usuario;
import src.modelo.UsuarioDAO;
import src.modelo.Reserva;
import src.modelo.ReservaDAO;
import src.modelo.Menus_lista; 
import src.util.ReconocimientoFacialUtil;
import src.util.Calcular;
import src.util.Calcular_dia;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import javax.swing.*;
import org.json.JSONObject;

public class Controlador_Reconocimiento implements ActionListener {

    private Landingpage landing;
    private Reconocimiento_facial vista;

    public Controlador_Reconocimiento(Landingpage landing, Reconocimiento_facial vista) {
        this.landing = landing;
        this.vista = vista;
        this.vista.getCerrar().addActionListener(this);
        this.vista.getBtnSubirFoto().addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == vista.getCerrar()) {
            landing.setVisible(true);
            vista.dispose();
        } else if (e.getSource() == vista.getBtnSubirFoto()) {
            String cedula = vista.getTxtCedula().getText().trim();
            if (cedula.isEmpty()) {
                JOptionPane.showMessageDialog(vista, "Debe ingresar una cédula.");
                return;
            }

            Usuario usuario = UsuarioDAO.buscarPorCedula(cedula);
            if (usuario == null) {
                JOptionPane.showMessageDialog(vista, "Usuario no encontrado.");
                return;
            }

            File fotoSubida = vista.seleccionarArchivo();
            if (fotoSubida == null) return;

            File fotoRegistrada = new File("res/Info_Secretaria/" + cedula + ".png");
            if (!fotoRegistrada.exists()) fotoRegistrada = new File("res/Info_Secretaria/" + cedula + ".jpg");
            
            if (!fotoRegistrada.exists()) {
                JOptionPane.showMessageDialog(vista, "No hay foto registrada.");
                return;
            }

            if (!ReconocimientoFacialUtil.validarIdentidad(fotoSubida, fotoRegistrada)) {
                JOptionPane.showMessageDialog(vista, "La foto no coincide. Acceso denegado.");
                return;
            }

            String V_Dia = Calcular_dia.getdia();
            String V_Turno = Calcular_dia.getTurno();
            String idMenu = "";

            if(V_Turno==""){
                JOptionPane.showMessageDialog(vista, "Lamentamos informar que el comedor no ofrece servicio en este horario");
                return;
            }else{
                idMenu = V_Dia + "_" + V_Turno;
            }

            Reserva reserva = ReservaDAO.buscarPorCedula(cedula, idMenu);
            if (reserva == null) {
                JOptionPane.showMessageDialog(vista, "No tienes una reserva activa.");
                return;
            }

            String diaTurno = reserva.getDiaTurno(); 
            String[] partes = diaTurno.split("_");
            String diaReserva = partes[0];
            String turnoReserva = partes[1];


            String diaActual = Calcular_dia.getdia();
            int horaActualMinutos = Calcular_dia.gethora();
            int indiceDiaReserva = Calcular_dia.getIndiceDia(diaReserva);
            int indiceDiaActual = Calcular_dia.getIndiceDia(diaActual);

            if (indiceDiaReserva > indiceDiaActual) {
                JOptionPane.showMessageDialog(vista, "Tu reserva es para el " + diaReserva + ". Aún no puedes retirarla.");
                return;
            } else if (indiceDiaReserva < indiceDiaActual) {
                cancelarPorExpiracion(cedula, diaTurno, "La reserva era de un día anterior. Ha sido cancelada.");
                return;
            }


            int inicio = 0, fin = 0;
            if (turnoReserva.equals("DESAYUNO")) { inicio = 420; fin = 600; } 
            else if (turnoReserva.equals("ALMUERZO")) { inicio = 690; fin = 900; }
            else if (turnoReserva.equals("CENA")) { inicio = 1020; fin = 1200; }

            if (horaActualMinutos < inicio) {
                JOptionPane.showMessageDialog(vista, "Aún es muy temprano para el " + turnoReserva);
                return;
            } else if (horaActualMinutos > fin) {
                cancelarPorExpiracion(cedula, diaTurno, "La reserva fue hace mucho y fue cancelada.");
                return;
            }

            JSONObject menuData = Menus_lista.getMenuData(diaReserva, turnoReserva);
            double precioFinal = Calcular.calcularPrecio(diaTurno, usuario.getRol());

            if (usuario.getSaldo() < precioFinal) {
                JOptionPane.showMessageDialog(vista, "Saldo insuficiente para pagar la reserva.\nSaldo: " + usuario.getSaldo() + " Bs\nPrecio: " + precioFinal + " Bs");
                return;
            }

            double nuevoSaldo = usuario.getSaldo() - precioFinal;
            if (UsuarioDAO.actualizarSaldo(cedula, nuevoSaldo)) {
                
                ReservaDAO.eliminarPorCedula(cedula, idMenu);
                
                JOptionPane.showMessageDialog(vista,
                    "¡Cobro Exitoso y Comida Entregada!\n" +
                    "Menú: " + menuData.getString("comida") + "\n" +
                    "Precio pagado: " + precioFinal + " Bs\n" +
                    "Saldo restante: " + nuevoSaldo + " Bs");

                landing.setVisible(true);
                vista.dispose();
            } else {
                JOptionPane.showMessageDialog(vista, "Error al procesar el pago en la base de datos.");
            }
        }
    }

    private void cancelarPorExpiracion(String cedula, String diaTurno, String mensaje) {
        ReservaDAO.eliminarPorCedula(cedula, diaTurno);
        Menus_lista.decrementarReserva(diaTurno);
        JOptionPane.showMessageDialog(vista, mensaje, "Reserva Expirada", JOptionPane.WARNING_MESSAGE);
    }
}