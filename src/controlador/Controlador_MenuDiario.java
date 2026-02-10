package src.controlador;

import src.vista.AlumnoView;
import src.vista.EmpleadoView;
import src.vista.MenuDView;
import src.modelo.validadorInicioS;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import src.modelo.Menus_lista; 
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
            avisoProximamente(this.menu.getBtnReservarA());
            avisoProximamente(this.menu.getBtnReservarB());
            avisoProximamente(this.menu.getBtnReservarC());
        }
        cargarInformacionMenu();
        this.menu.setVisible(false);
}

    private void avisoProximamente(JButton boton) {
            if (boton != null) {
                boton.addActionListener(e -> {
                    JOptionPane.showMessageDialog(null, 
                        "Esta funcionalidad estará disponible en la próxima actualización.", 
                        "En construcción", 
                        JOptionPane.INFORMATION_MESSAGE);
                });
            }
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
                    alumno.setResizable(false);
                    alumno.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                    alumno.setVisible(true);
                    
                    menu.setVisible(false);
                    menu.dispose();
                }
            }
            if (Rol.equals("Trabajador") || Rol.equals("Docente")) {
                if (this.empleado != null) {
                    empleado.setExtendedState(JFrame.MAXIMIZED_BOTH);
                    empleado.setResizable(false);
                    empleado.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                    empleado.setVisible(true);
                    
                    menu.setVisible(false);
                    menu.dispose();
                }
            }
        }
    }
}
