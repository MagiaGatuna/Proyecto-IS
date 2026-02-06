package src.controlador;
import src.Landingpage;
import src.Registro;
import src.InicioSesion;
import src.modelo.validadorRegistro;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.*;


public class Controlador_reg implements ActionListener{
    private Landingpage inicio;
    private Registro ventanaRegistro;
    private InicioSesion inicio_sesion;

    public Controlador_reg(Landingpage inicio, Registro ventanaRegistro, InicioSesion inicio_sesion){
        this.inicio=inicio;
        this.ventanaRegistro=ventanaRegistro;
        this.inicio_sesion=inicio_sesion;
        this.ventanaRegistro.getHome().addActionListener(this);
        this.ventanaRegistro.getAceptar().addActionListener(this);
        this.ventanaRegistro.getinicio_label().addMouseListener(new MouseAdapter(){

            @Override
            public void mouseClicked(MouseEvent ev){
            
            inicio_sesion.setExtendedState(JFrame.MAXIMIZED_BOTH);
            inicio_sesion.setResizable(false);
            inicio_sesion.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            inicio_sesion.setVisible(true);
            ventanaRegistro.setVisible(false);

            }

        });

    }
    
    @Override
    public void actionPerformed(ActionEvent e){

        if(e.getSource()==ventanaRegistro.getAceptar()){

            if (validadorRegistro.validarRegistro(ventanaRegistro.getTxtNombres(),
                                                    ventanaRegistro.getTxtApellidos(),
                                                    ventanaRegistro.getTxtCedula(),
                                                    ventanaRegistro.getTxtCorreo(),
                                                    ventanaRegistro.getComboSexo(),
                                                    ventanaRegistro.getComboRol(),
                                                    ventanaRegistro.getTxtPassword(),
                                                    ventanaRegistro.getTxtConfirmPassword())) {
                JOptionPane.showMessageDialog(ventanaRegistro,  
                    "¡Registro completado exitosamente!",
                    "Éxito",
                    JOptionPane.INFORMATION_MESSAGE);
                
                inicio_sesion.setExtendedState(JFrame.MAXIMIZED_BOTH);
                inicio_sesion.setResizable(false);
                inicio_sesion.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                inicio_sesion.setVisible(true);
                ventanaRegistro.setVisible(false);
            }
        }
        
        if(e.getSource()==ventanaRegistro.getHome()){
            inicio.setExtendedState(JFrame.MAXIMIZED_BOTH);
            inicio.setResizable(false);
            inicio.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            inicio.setVisible(true);
            ventanaRegistro.setVisible(false);
        }
    }
}

