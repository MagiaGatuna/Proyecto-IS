package src.controlador;

import src.util.Calcular_dia;
import src.AlumnoView;
import src.EmpleadoView;
import src.MenuDView;
import src.modelo.validadorInicioS;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;


public class Controlador_MenuDiario implements ActionListener {
    private AlumnoView alumno;
    private EmpleadoView empleado;
    private MenuDView menu;
    private String Rol="";


    String diaSemana, MM;
    int diaMes, AA;

    public Controlador_MenuDiario(AlumnoView alumno,EmpleadoView empleado,MenuDView menu){
        this.alumno= alumno;
        this.empleado= empleado;

        diaSemana= Calcular_dia.getdia();
        diaMes= Calcular_dia.getDiaMesNumero();
        MM= Calcular_dia.getMes();
        AA= Calcular_dia.getAnio();


        String[] diasSemanaINGLES = {"MONDAY", "TUESDAY", "WEDNESDAY", "THURSDAY", "FRIDAY", "SATURDAY", "SUNDAY"};
        String[] diasSemanaESPANOL = {"Lunes", "Martes", "Miercoles", "Jueves", "Viernes", "Sabado", "Domingo"};
        String[] MesINGLES = {"JANUARY", "FEBRUARY", "MARCH", "APRIL", "MAY", "JUNE", "JULY", "AUGUST", "SEPTEMBER", "OCTOBER", "NOVEMBER", "DECEMBER"};
        String[] MesESPANOL = {"Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio", "Julio", "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre"};
        //Traductores
        for(int i= 0; i < diasSemanaINGLES.length; i++){
            if (diasSemanaINGLES[i].equals(diaSemana)) {diaSemana= diasSemanaESPANOL[i]; break;}
        }
        for(int i= 0; i < MesINGLES.length; i++){
            if (MesINGLES[i].equals(MM)) {MM= MesESPANOL[i]; break;}
        }

        this.menu = new MenuDView(diaSemana, diaMes, MM, AA);

        this.menu = new MenuDView(diaSemana, diaMes, MM, AA);

        if (this.menu.getBtnHome() != null) {
            this.menu.getBtnHome().addActionListener(this);
        }
        
        this.menu.setVisible(true);
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





