package src.controlador;

import src.Landingpage;
import src.AlumnoView;
import src.EmpleadoView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class Controlador_Alumno_Empleado implements ActionListener{
    
    private Landingpage inicio;
    private AlumnoView alumno;
    private EmpleadoView empleado;

    public Controlador_Alumno_Empleado(Landingpage inicio, AlumnoView alumno, EmpleadoView empleado){

        //getinicio()
        this.inicio=inicio;
        this.alumno=alumno;
        this.empleado=empleado;
        this.empleado.getinicio().addActionListener(this);
        this.alumno.getinicio().addActionListener(this);
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
    }

}
