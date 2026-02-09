package src.controlador;

import src.vista.AlumnoView;
import src.vista.EmpleadoView;
import src.vista.MenuDView;
import src.modelo.validadorInicioS;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;


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
        }
        
        this.menu.setVisible(false);
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
