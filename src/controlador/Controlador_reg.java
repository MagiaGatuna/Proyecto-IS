package src.controlador;
import src.Landingpage;
import src.Registro;
import src.util.ValidarUtil;
import src.InicioSesion;

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

            if (validarRegistro()){
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


    private boolean validarRegistro(){  
        StringBuilder errores = new StringBuilder();

        
        // Validar nombres
        if(ValidarUtil.campoEstaVacio(ventanaRegistro.getTxtNombres(), "Nombres")){
            errores.append("- El campo Nombres es obligatorio\n");
        }
        
        if(ValidarUtil.campoEstaVacio(ventanaRegistro.getTxtApellidos(), "Apellidos")){
            errores.append("- El campo Apellidos es obligatorio\n");
        }
        
        if(ValidarUtil.campoEstaVacio(ventanaRegistro.getTxtCedula(), "Cédula de identidad")){
            errores.append("- El campo Cédula es obligatorio\n");
        } else if (!ValidarUtil.cedulaEsValida(ventanaRegistro.getTxtCedula())) {
            errores.append("- La cédula debe contener solo números\n");
        }
        
        if(ValidarUtil.campoEstaVacio(ventanaRegistro.getTxtCorreo(), "Correo Electrónico")){
            errores.append("- El campo Correo Electrónico es obligatorio\n");
        } else if (!ValidarUtil.esEmailValido(ventanaRegistro.getTxtCorreo().getText().trim())) {
            errores.append("- El formato del correo electrónico no es válido (ejemplo: usuario@dominio.com)\n");
        }
        
        if(ventanaRegistro.getComboSexo().getSelectedIndex() == 0){
            errores.append("- Debe seleccionar un Sexo\n");
        }
        
        if(ventanaRegistro.getComboRol().getSelectedIndex() == 0){
            errores.append("- Debe seleccionar un Rol\n");
        }
        
        String password = new String(ventanaRegistro.getTxtPassword().getPassword());
        if(ValidarUtil.campoEstaVacio(ventanaRegistro.getTxtPassword(), "Contraseña")){
            errores.append("- El campo Contraseña es obligatorio\n");
        }
        
        String confirmPassword = new String(ventanaRegistro.getTxtConfirmPassword().getPassword());
        if(ValidarUtil.campoEstaVacio(ventanaRegistro.getTxtConfirmPassword(), "Confirmar Contraseña")){
            errores.append("- Debe confirmar la contraseña\n");
        }
        
        if(!password.isEmpty() && !confirmPassword.isEmpty() && 
            !password.equals("Contraseña") && !confirmPassword.equals("Confirmar Contraseña")){
            if(!password.equals(confirmPassword)){
                errores.append("- Las contraseñas no coinciden\n");
            }
        }

        if(errores.length() > 0){
            JOptionPane.showMessageDialog(ventanaRegistro,  
                "Por favor corrija los siguientes errores:\n\n" + errores.toString(),
                "Errores en el formulario",
                JOptionPane.ERROR_MESSAGE);
            return false;
        }
        
        return true;
    }
}