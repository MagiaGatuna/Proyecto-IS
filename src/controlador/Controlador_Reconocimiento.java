package src.controlador;

import src.vista.Landingpage;
import src.vista.Reconocimiento_facial;
import src.modelo.Usuario;
import src.modelo.UsuarioDAO;
import src.modelo.Reserva;
import src.modelo.ReservaDAO;
import src.modelo.Menus_lista; 
import src.util.ReconocimientoFacialUtil;


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
            if (fotoSubida == null) {
                return;
            }

            File fotoRegistrada = new File("res/Info_Secretaria/" + cedula + ".png");
            if (!fotoRegistrada.exists()) {
                fotoRegistrada = new File("res/Info_Secretaria/" + cedula + ".jpg");
            }
            if (!fotoRegistrada.exists()) {
                JOptionPane.showMessageDialog(vista, "No hay foto registrada para esta cédula.");
                return;
            }

            boolean coinciden = ReconocimientoFacialUtil.validarIdentidad(fotoSubida, fotoRegistrada);
            if (!coinciden) {
                JOptionPane.showMessageDialog(vista, "La foto no coincide con la registrada. Acceso denegado.");
                return;
            }

            Reserva reserva = ReservaDAO.buscarPorCedula(cedula);
            if (reserva == null) {
                JOptionPane.showMessageDialog(vista, "El usuario no tiene ninguna reserva activa para hoy.");
                return;
            }

            String diaTurno = reserva.getDiaTurno(); 
            String[] partes = diaTurno.split("_");
            if (partes.length != 2) {
                JOptionPane.showMessageDialog(vista, "Error en el formato de la reserva.");
                return;
            }
            String dia = partes[0];
            String turno = partes[1];

            JSONObject menuData = Menus_lista.getMenuData(dia, turno);
            double precioFinal = src.modelo.MermayCCB.getCCB(diaTurno);


            if (usuario.getSaldo() < precioFinal) {
                JOptionPane.showMessageDialog(vista, "Saldo insuficiente. Saldo actual: " + usuario.getSaldo());
                return;
            }

            double nuevoSaldo = usuario.getSaldo() - precioFinal;
            boolean actualizado = UsuarioDAO.actualizarSaldo(cedula, nuevoSaldo);
            if (!actualizado) {
                JOptionPane.showMessageDialog(vista, "Error al actualizar el saldo.");
                return;
            }

            ReservaDAO.eliminarPorCedula(cedula);

            JOptionPane.showMessageDialog(vista,
                "¡Reconocimiento exitoso!\n" +
                "Reserva: " + diaTurno + "\n" +
                "Menú: " + menuData.getString("comida") + "\n" +
                "Precio final: " + precioFinal + " Bs\n" +
                "Nuevo saldo: " + nuevoSaldo);

            landing.setVisible(true);
            vista.dispose();
        }
    }
}