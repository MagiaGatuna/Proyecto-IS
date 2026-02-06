package src.controlador;

import src.Landingpage;
import src.Registro;
import src.InicioSesion;
import src.HomeAdmin;
import src.AlumnoView;
import src.EmpleadoView;
import src.modelo.validadorInicioS;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


import javax.swing.*;


public class Controlador_inicioS implements ActionListener{
    //getHome y getRegistro
    private Landingpage inicio;
    private Registro ventanaRegistro;
    private InicioSesion inicio_sesion;
    private HomeAdmin admin;
    private AlumnoView alumno;
    private EmpleadoView empleado;

    String Rol="";

    public Controlador_inicioS(Landingpage inicio, Registro ventanaRegistro, InicioSesion inicio_sesion, HomeAdmin admin, AlumnoView alumno,EmpleadoView empleado ){
        this.inicio=inicio;
        this.ventanaRegistro=ventanaRegistro;
        this.inicio_sesion=inicio_sesion;
        this.admin=admin;
        this.alumno=alumno;
        this.empleado=empleado;
        this.inicio_sesion.getRegistro().addActionListener(this);
        this.inicio_sesion.getHome().addActionListener(this);
        this.admin.getHome2().addActionListener(this);
        this.inicio_sesion.getAdmin().addActionListener(this);
    }
    @Override
    public void actionPerformed(ActionEvent e){
        if(e.getSource() == admin.getHome2()){

            inicio.setExtendedState(JFrame.MAXIMIZED_BOTH);
            inicio.setResizable(false);
            inicio.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            inicio.setVisible(true);
            admin.setVisible(false);

        }
        if(e.getSource() == inicio_sesion.getAdmin()){
            if (validadorInicioS.validarInicioSesion(inicio_sesion.getCedula_id(), inicio_sesion.getContraseña())){
                Rol = validadorInicioS.getRol();

                    if((Rol.equals("Administrador"))){
                        admin.setExtendedState(JFrame.MAXIMIZED_BOTH);
                        admin.setResizable(false);
                        admin.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                        admin.setVisible(true);
                        inicio_sesion.setVisible(false);
                    }
                    if((Rol.equals("Trabajador")||Rol.equals("Docente"))){
                    empleado.setExtendedState(JFrame.MAXIMIZED_BOTH);
                    empleado.setResizable(false);
                    empleado.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                    empleado.setVisible(true);
                    inicio_sesion.setVisible(false);
                        
                    }
                    if((Rol.equals("Estudiante"))){
                    alumno.setExtendedState(JFrame.MAXIMIZED_BOTH);
                    alumno.setResizable(false);
                    alumno.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                    alumno.setVisible(true);
                    inicio_sesion.setVisible(false);

                    }
                 
                
            }
        }
        if(e.getSource()==inicio_sesion.getRegistro()){

            ventanaRegistro.setExtendedState(JFrame.MAXIMIZED_BOTH);
            ventanaRegistro.setResizable(false);
            ventanaRegistro.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            ventanaRegistro.setVisible(true);
            inicio_sesion.setVisible(false);
        }
        if(e.getSource()==inicio_sesion.getHome()){

            inicio.setExtendedState(JFrame.MAXIMIZED_BOTH);
            inicio.setResizable(false);
            inicio.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            inicio.setVisible(true);
            inicio_sesion.setVisible(false);

        }

    }

}
