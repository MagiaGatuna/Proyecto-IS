package src.controlador;

import src.Landingpage;
import src.Registro;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.JOptionPane;

public class Controlador_lp implements ActionListener{

    private Landingpage inicio;
    private Registro ventanaRegistro;
    //private Inicio_sesion inicioSesion;

    public Controlador_lp(Landingpage inicio, Registro ventanaRegistro){
        this.inicio=inicio;
        this.ventanaRegistro=ventanaRegistro;
        this.inicio.getRegistro().addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e){
       
        if(e.getSource()==inicio.getAcercade()){
            JOptionPane.showMessageDialog(null,"Desarrolladores: Alexandra Amselmi, Valentina Almeida, Corina Matheus y Andrés Ortiz");
        }

        if(e.getSource()==inicio.getRegistro()){

            inicio.setVisible(false);
            ventanaRegistro.setExtendedState(JFrame.MAXIMIZED_BOTH);
            ventanaRegistro.setResizable(false);
            ventanaRegistro.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            ventanaRegistro.setVisible(true);

        }
        /*
        if(e.getResource()==inicio.getInicio()){

            inicio.setVisible(false);
            inicioSesion.setVisible(true);

        }
        */

    }
}


