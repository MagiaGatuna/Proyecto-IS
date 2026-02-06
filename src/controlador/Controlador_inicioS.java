package src.controlador;

import src.Landingpage;
import src.Registro;
import src.InicioSesion;
import src.HomeAdmin;
import src.AlumnoView;
import src.EmpleadoView;
import src.MenuSemanal;
import src.modelo.validadorInicioS;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


import javax.swing.*;


public class Controlador_inicioS implements ActionListener{
    //getHome y getRegistro
    private Landingpage inicio;
    private Registro ventanaRegistro;
    private InicioSesion inicio_sesion;

    String Rol="";

    public Controlador_inicioS(Landingpage inicio, Registro ventanaRegistro, InicioSesion inicio_sesion){
        this.inicio=inicio;
        this.ventanaRegistro=ventanaRegistro;
        this.inicio_sesion=inicio_sesion;
       
        this.inicio_sesion.getRegistro().addActionListener(this);
        this.inicio_sesion.getHome().addActionListener(this);

        this.inicio_sesion.getAdmin().addActionListener(this);
        
    }
    @Override
    public void actionPerformed(ActionEvent e){
        /* 
        if(e.getSource() == admin.getHome2()){

            inicio.setExtendedState(JFrame.MAXIMIZED_BOTH);
            inicio.setResizable(false);
            inicio.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            inicio.setVisible(true);
            admin.setVisible(false);

        }*/
        if(e.getSource() == inicio_sesion.getAdmin()){
            if (validadorInicioS.validarInicioSesion(inicio_sesion.getCedula_id(), inicio_sesion.getContraseña())){
                Rol = validadorInicioS.getRol();
                    
                    if((Rol.equals("Administrador"))){

                        HomeAdmin admin= new HomeAdmin();
                        admin.setExtendedState(JFrame.MAXIMIZED_BOTH);
                        admin.setResizable(false);
                        admin.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                        admin.setVisible(true);
                        inicio_sesion.setVisible(false);
                        
                    }
                    if((Rol.equals("Trabajador")||Rol.equals("Docente"))){
                    EmpleadoView empleado= new EmpleadoView("");//sustituyan la cedula por el nombre mas tarde
                    MenuSemanal menu_s_e= new MenuSemanal();

                    Controlador_Alumno_Empleado control5= new Controlador_Alumno_Empleado(inicio, null,empleado,menu_s_e);
                    Controlador_MenuSemanal control6= new Controlador_MenuSemanal(null, empleado,menu_s_e);

                    empleado.setExtendedState(JFrame.MAXIMIZED_BOTH);
                    empleado.setResizable(false);
                    empleado.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                    empleado.setVisible(true);
                    inicio_sesion.setVisible(false);
                    
                        
                    }
                    if((Rol.equals("Estudiante"))){
                    AlumnoView alumno= new AlumnoView("");//sustituyan la cedula por el nombre mas tarde
                    MenuSemanal menu_s= new MenuSemanal();

                    Controlador_Alumno_Empleado control4= new Controlador_Alumno_Empleado(inicio, alumno,null,menu_s);
                    Controlador_MenuSemanal control7= new Controlador_MenuSemanal(alumno, null,menu_s);
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
