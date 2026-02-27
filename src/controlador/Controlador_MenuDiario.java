package src.controlador;

import src.vista.AlumnoView;
import src.vista.EmpleadoView;
import src.vista.MenuDView;
import src.modelo.validadorInicioS;
import src.util.Calcular;
import src.util.Calcular_dia;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import src.modelo.Menus_lista;
import src.modelo.Reserva;
import src.modelo.ReservaDAO;
import src.modelo.Usuario;

import org.json.JSONObject; 


public class Controlador_MenuDiario implements ActionListener {
    private AlumnoView alumno;
    private EmpleadoView empleado;
    private MenuDView menu;
    private String Rol="";


    public Controlador_MenuDiario(AlumnoView alumno,EmpleadoView empleado,MenuDView menu){
        this.alumno= alumno;
        this.empleado= empleado;
        this.menu = menu;

        if (this.menu.getBtnHome() != null) {
            this.menu.getBtnHome().addActionListener(this);
            if (menu.getBtnReservarA() != null) { // desayuno
                menu.getBtnReservarA().addActionListener(this);
            }
            if (menu.getBtnReservarB() != null) { // almuerzo
                menu.getBtnReservarB().addActionListener(this);
            }
        }
        cargarInformacionMenu();

        int horaActual = Calcular_dia.gethora();
        String diaActual = Calcular_dia.getdia(); 


        if (diaActual.equals("SATURDAY") || diaActual.equals("SUNDAY")) {
            menu.deshabilitarBoton("DESAYUNO");
            menu.deshabilitarBoton("ALMUERZO");
         } else {
            if (horaActual >= 600) {
                menu.deshabilitarBoton("DESAYUNO");
            }

            if (horaActual >= 900) {
                menu.deshabilitarBoton("ALMUERZO");
            }
         }
            this.menu.setVisible(false);
    }



    private void cargarInformacionMenu() {
        String diaEspanol = menu.getDiaSemana(); 

        if (diaEspanol.equalsIgnoreCase("Sabado") || diaEspanol.equalsIgnoreCase("Domingo")) {
        JOptionPane.showMessageDialog(menu, 
            "Estimado usuario: Es fin de semana y nuestro comedor está fuera de servicio.", 
            "Servicio Cerrado", 
            JOptionPane.INFORMATION_MESSAGE);}

        String diaIngles = traducirDiaAlIngles(diaEspanol);

        actualizarTurno(diaIngles, "DESAYUNO");
        actualizarTurno(diaIngles, "ALMUERZO");
        actualizarTurno(diaIngles, "CENA");
    }

    private void actualizarTurno(String dia, String turno) {
        JSONObject datos = Menus_lista.getMenuData(dia, turno);

        if (datos != null) {
            String comida = datos.getString("comida");
            String nutri = datos.getString("valorNutricional");
            String desc = datos.getString("descripcion"); 


            if (turno.equals("DESAYUNO")) {
                menu.setMenuDesayuno(comida, desc, nutri);
            } else if (turno.equals("ALMUERZO")) {
                menu.setMenuAlmuerzo(comida, desc, nutri);
            } else if (turno.equals("CENA")) {
                menu.setMenuCena(comida, desc, nutri);
            }
        } else {
             if (turno.equals("DESAYUNO")) menu.setMenuDesayuno("No hay servicio", "-", "-");
        }
    }

    private String traducirDiaAlIngles(String diaEs) {
        switch (diaEs) {
            case "Lunes": return "MONDAY";
            case "Martes": return "TUESDAY";
            case "Miercoles": return "WEDNESDAY";
            case "Jueves": return "THURSDAY";
            case "Viernes": return "FRIDAY";
            case "Sabado": return "SATURDAY";
            case "Domingo": return "SUNDAY";
            default: return "MONDAY"; 
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Rol = validadorInicioS.getRol();
        if (e.getSource() == menu.getBtnHome()) {
            if (Rol.equals("Estudiante")) {
                if (this.alumno != null) {
                    alumno.setExtendedState(JFrame.MAXIMIZED_BOTH);
                    //alumno.setResizable(false);
                    alumno.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                    alumno.setVisible(true);
                    
                    menu.setVisible(false);
                    menu.dispose();
                }
            }
            if (Rol.equals("Trabajador") || Rol.equals("Docente")) {
                if (this.empleado != null) {
                    empleado.setExtendedState(JFrame.MAXIMIZED_BOTH);
                    //empleado.setResizable(false);
                    empleado.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                    empleado.setVisible(true);
                    
                    menu.setVisible(false);
                    menu.dispose();
                }
            }

        }
        if (e.getSource() == menu.getBtnReservarA()) {
            realizarReserva("DESAYUNO");
        } else if (e.getSource() == menu.getBtnReservarB()) {
            realizarReserva("ALMUERZO");
}
    }

    private void realizarReserva(String turno) {
    Usuario usuario = validadorInicioS.getUsuarioActual();
    if (usuario == null) {
        JOptionPane.showMessageDialog(menu, "No hay usuario logueado.");
        return;
    }
    String diaEspanol = menu.getDiaSemana();
    String diaIngles = traducirDiaAlIngles(diaEspanol);
    JSONObject menuData = Menus_lista.getMenuData(diaIngles, turno);
    if (menuData == null) {
        JOptionPane.showMessageDialog(menu, "No hay menú disponible para este turno.");
        return;
    }



    String idMenu = menuData.getString("dia_turno");
    double precioFinal = Calcular.calcularPrecio(idMenu, usuario.getRol());

    Reserva reservaExistente = ReservaDAO.buscarPorCedula(usuario.getCedula());
    if (reservaExistente != null) {
        JOptionPane.showMessageDialog(menu, "Ya tienes una reserva activa para: " + reservaExistente.getDiaTurno());
        return;
    }

    int opcion = JOptionPane.showConfirmDialog(menu,
        "¿Confirmar reserva?\n" +
        "Menú: " + menuData.getString("comida") + "\n" +
        "Precio final: " + precioFinal + " Bs",
        "Confirmar reserva",
        JOptionPane.YES_NO_OPTION);

    if (opcion != JOptionPane.YES_OPTION) {
        return;
    }

    String diaTurno = idMenu;
    Reserva nuevaReserva = new Reserva(usuario.getCedula(), diaTurno);
    ReservaDAO.guardar(nuevaReserva);
    Menus_lista.incrementarReserva(idMenu);
    actualizarTurno(diaIngles, turno); 

    JOptionPane.showMessageDialog(menu, "Reserva realizada con éxito.");
}
}
