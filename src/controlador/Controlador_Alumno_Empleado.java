package src.controlador;

import src.Landingpage;
import src.AlumnoView;
import src.EmpleadoView;
import src.MenuSemanal;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class Controlador_Alumno_Empleado implements ActionListener{
    
    private Landingpage inicio;
    private AlumnoView alumno;
    private EmpleadoView empleado;
    private MenuSemanal menu_semanal;

    public Controlador_Alumno_Empleado(Landingpage inicio, AlumnoView alumno, EmpleadoView empleado, MenuSemanal menu_semanal){

        //getinicio()
        this.inicio=inicio;
        this.menu_semanal=menu_semanal;
        this.alumno=alumno;
        this.empleado=empleado;
        this.empleado.getinicio().addActionListener(this);
        this.alumno.getinicio().addActionListener(this);
        this.alumno.getMenuS().addActionListener(this);
        this.alumno.getMenuD().addActionListener(this);
        this.empleado.getMenuS().addActionListener(this);
        this.empleado.getMenuD().addActionListener(this);
    }
    @Override
    public void actionPerformed(ActionEvent e){
        if(e.getSource() == alumno.getinicio()){

            inicio.setExtendedState(JFrame.MAXIMIZED_BOTH);
            inicio.setResizable(false);
            inicio.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            inicio.setVisible(true);
            alumno.setVisible(false);

        }
        if(e.getSource() == empleado.getinicio()){

            inicio.setExtendedState(JFrame.MAXIMIZED_BOTH);
            inicio.setResizable(false);
            inicio.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            inicio.setVisible(true);
            empleado.setVisible(false);

        }
        
        if(e.getSource() == alumno.getMenuS()){

            menu_semanal.setExtendedState(JFrame.MAXIMIZED_BOTH);
            menu_semanal.setResizable(false);
            menu_semanal.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            menu_semanal.setVisible(true);
            alumno.setVisible(false);

        }
        
        if(e.getSource() == empleado.getMenuS()){

            menu_semanal.setExtendedState(JFrame.MAXIMIZED_BOTH);
            menu_semanal.setResizable(false);
            menu_semanal.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            menu_semanal.setVisible(true);
            empleado.setVisible(false);

        }
    }

}
