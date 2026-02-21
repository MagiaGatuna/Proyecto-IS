package src.controlador;

import src.vista.Landingpage;
import src.vista.Registro;
import src.vista.InicioSesion;
import src.vista.Reconocimiento_facial;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class Controlador_lp implements ActionListener{

    private Landingpage inicio;
    private Registro ventanaRegistro;
    private InicioSesion inicio_sesion;
    private Reconocimiento_facial reconocimiento;

    public Controlador_lp(Landingpage inicio, Registro ventanaRegistro, InicioSesion inicio_sesion, Reconocimiento_facial reconocimiento){
        this.inicio=inicio;
        this.ventanaRegistro=ventanaRegistro;
        this.inicio_sesion=inicio_sesion;
        this.reconocimiento= reconocimiento;
        this.inicio.getRegistro().addActionListener(this);
        this.inicio.getAcercade().addActionListener(this);
        this.inicio.getInicio().addActionListener(this);
        this.inicio.getReconocimiento().addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e){
        
        if(e.getSource()==inicio.getAcercade()){
            JOptionPane.showMessageDialog(null,"Desarrolladores: Alexandra Amselmi, Valentina Almeida, Corina Matheus y Andrés Ortiz");
        }

        if(e.getSource()==inicio.getRegistro()){

            
            ventanaRegistro.setExtendedState(JFrame.MAXIMIZED_BOTH);
            ventanaRegistro.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            ventanaRegistro.setVisible(true);
            inicio.setVisible(false);
        }
        
        if(e.getSource()==inicio.getInicio()){

            inicio_sesion.setExtendedState(JFrame.MAXIMIZED_BOTH);
            inicio_sesion.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            inicio_sesion.setVisible(true);
            inicio.setVisible(false);

        }
        if(e.getSource()==inicio.getReconocimiento()){

            reconocimiento.setExtendedState(JFrame.MAXIMIZED_BOTH);
            reconocimiento.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            reconocimiento.setVisible(true);
            inicio.setVisible(false);

        }

    }
}


