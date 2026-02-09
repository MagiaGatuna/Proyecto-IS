package src.controlador;

import src.vista.Landingpage;
import src.vista.Registro;
import src.vista.InicioSesion;
import src.vista.HomeAdmin;
import src.vista.AlumnoView;
import src.vista.EmpleadoView;
import src.vista.MenuSemanal;
import src.vista.MenuDView;
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
        
        LimpiarFormulariosUtil.limpiarInicioSesion(inicio_sesion.getCedula_id(), inicio_sesion.getContraseña());
    }

    @Override
    public void actionPerformed(ActionEvent e){
        System.out.println("Se presionó un botón: " + e.getActionCommand());
        if(e.getSource() == inicio_sesion.getAdmin()){
    // Pasamos los objetos JTextField y JPasswordField directamente como pide tu validador
        System.out.println("Botón de Login detectado correctamente");
    if (validadorInicioS.validarInicioSesion(inicio_sesion.getCedula_id(), inicio_sesion.getContraseña())){
        Rol = validadorInicioS.getRol();
        Usuario usuarioLogueado = validadorInicioS.getUsuarioActual();
            
            if((Rol.equals("Administrador"))){
                HomeAdmin admin = new HomeAdmin(usuarioLogueado);
                new ControladorAdmin(inicio,admin);
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
                MenuDView menu_d= new MenuDView();
                JPanel monederoTemp = new JPanel();
                monederoTemp.add(new JLabel("Saldo Actual: $" + usuarioLogueado.getSaldo()));
                new Controlador_Alumno_Empleado(inicio, null, empleado, menu_s_e,monederoTemp,menu_d);
                new Controlador_MenuSemanal(null, empleado, menu_s_e);
                new Controlador_MenuD(null, empleado, menu_d);

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
                MenuDView menu_d= new MenuDView();
                JPanel monederoTemp = new JPanel();
                monederoTemp.add(new JLabel("Saldo Actual: $" + usuarioLogueado.getSaldo()));
                new Controlador_Alumno_Empleado(inicio, alumno, null, menu_s, monederoTemp,menu_d);
                new Controlador_MenuSemanal(alumno, null, menu_s);
                new Controlador_MenuD(alumno, null, menu_d);

                alumno.setExtendedState(JFrame.MAXIMIZED_BOTH);
                alumno.setResizable(false);
                alumno.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                alumno.setVisible(true);
                inicio_sesion.setVisible(false);
                inicio_sesion.dispose();
            }
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