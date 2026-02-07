package src.controlador;

import src.Landingpage;
import src.Registro;
import src.InicioSesion;
import src.HomeAdmin;
import src.AlumnoView;
import src.EmpleadoView;
import src.MenuSemanal;
import src.modelo.Usuario;
import src.modelo.validadorInicioS;
import src.util.LimpiarFormulariosUtil;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class Controlador_inicioS implements ActionListener{
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
        if(e.getSource() == inicio_sesion.getAdmin()){
            // CAMBIO: Extraer el texto real de los campos para validar
            String cedulaTxt = inicio_sesion.getCedula_id().getText();
            String passTxt = new String(inicio_sesion.getContraseña().getPassword());

            if (validadorInicioS.validarInicioSesion(cedulaTxt, passTxt)){
                Rol = validadorInicioS.getRol();
                Usuario usuarioLogueado = validadorInicioS.getUsuarioActual();
                    
                    if((Rol.equals("Administrador"))){
                        HomeAdmin admin = new HomeAdmin(usuarioLogueado);
                        admin.setExtendedState(JFrame.MAXIMIZED_BOTH);
                        admin.setResizable(false);
                        admin.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                        admin.setVisible(true);
                        inicio_sesion.setVisible(false);
                        inicio_sesion.dispose();
                    }

                    if((Rol.equals("Trabajador")||Rol.equals("Docente"))){
                        EmpleadoView empleado= new EmpleadoView(usuarioLogueado);
                        MenuSemanal menu_s_e= new MenuSemanal();

                        // CAMBIO: Instanciar controladores aquí para evitar NullPointerException del Main
                        new Controlador_Alumno_Empleado(inicio, null, empleado, menu_s_e, null);
                        new Controlador_MenuSemanal(null, empleado, menu_s_e);

                        empleado.setExtendedState(JFrame.MAXIMIZED_BOTH);
                        empleado.setResizable(false);
                        empleado.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                        empleado.setVisible(true);
                        inicio_sesion.setVisible(false);
                        inicio_sesion.dispose();
                    }

                    if((Rol.equals("Estudiante"))){
                        AlumnoView alumno= new AlumnoView(usuarioLogueado);
                        MenuSemanal menu_s= new MenuSemanal();
                        
                        // CAMBIO: Instanciar controladores aquí para evitar NullPointerException del Main
                        new Controlador_Alumno_Empleado(inicio, alumno, null, menu_s, null);
                        new Controlador_MenuSemanal(alumno, null, menu_s);

                        alumno.setExtendedState(JFrame.MAXIMIZED_BOTH);
                        alumno.setResizable(false);
                        alumno.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                        alumno.setVisible(true);
                        inicio_sesion.setVisible(false);
                        inicio_sesion.dispose();
                    }
                 
            }else{
                // Si falla, se limpia usando los componentes originales
                LimpiarFormulariosUtil.limpiarInicioSesion(inicio_sesion.getCedula_id(), inicio_sesion.getContraseña());
                JOptionPane.showMessageDialog(null, "Datos incorrectos");
            }
        }

        if(e.getSource()==inicio_sesion.getRegistro()){
            LimpiarFormulariosUtil.limpiarInicioSesion(inicio_sesion.getCedula_id(), inicio_sesion.getContraseña());
            ventanaRegistro.setExtendedState(JFrame.MAXIMIZED_BOTH);
            ventanaRegistro.setResizable(false);
            ventanaRegistro.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            ventanaRegistro.setVisible(true);
            inicio_sesion.setVisible(false);
        }

        if(e.getSource()==inicio_sesion.getHome()){
            LimpiarFormulariosUtil.limpiarInicioSesion(inicio_sesion.getCedula_id(), inicio_sesion.getContraseña());
            inicio.setExtendedState(JFrame.MAXIMIZED_BOTH);
            inicio.setResizable(false);
            inicio.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            inicio.setVisible(true);
            inicio_sesion.setVisible(false);
        }
    }
}