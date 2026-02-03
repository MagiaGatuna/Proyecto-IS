package src.controlador;

import src.Landingpage;
import src.Registro;
import src.InicioSesion;
import src.util.ValidarUtil;
import src.HomeAdmin;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class Controlador_inicioS implements ActionListener{
    //getHome y getRegistro
    private Landingpage inicio;
    private Registro ventanaRegistro;
    private InicioSesion inicio_sesion;
    private HomeAdmin admin;

    public Controlador_inicioS(Landingpage inicio, Registro ventanaRegistro, InicioSesion inicio_sesion, HomeAdmin admin){
        this.inicio=inicio;
        this.ventanaRegistro=ventanaRegistro;
        this.inicio_sesion=inicio_sesion;
        this.admin=admin;
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

            admin.setExtendedState(JFrame.MAXIMIZED_BOTH);
            admin.setResizable(false);
            admin.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            admin.setVisible(true);
            inicio_sesion.setVisible(false);

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
        if(e.getSource() == botonhomeadmin){
            if (ValidarUtil.cedulaEsValida(inicio_sesion.getCedula_id()) && ValidarUtil.campoEstaVacio(inicio_sesion.getCedula_id(), "Cédula de identidad") == false){
                                
                inicio.setExtendedState(JFrame.MAXIMIZED_BOTH);
                inicio.setResizable(false);
                inicio.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                inicio.setVisible(true);
                inicio_sesion.setVisible(false);
                this.inicio_sesion.ocultar();
            }else{
                JOptionPane.showMessageDialog(ventanaRegistro,  
                "Por favor corrija los siguientes errores:\n\n" + "Cedula inválida.",
                "Errores en el formulario",
                JOptionPane.ERROR_MESSAGE);
            }
            //Hola me dio sueño mañana resuelvo estas  validaciones :)
        }
        

    }
}
