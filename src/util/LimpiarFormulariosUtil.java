package src.util;
import java.awt.*;
import javax.swing.*;

public class LimpiarFormulariosUtil {
    public static void limpiarRegistro(JTextField txtNombres, JTextField txtApellidos, JTextField txtCedula, JTextField txtCorreo, JComboBox<String> comboCedula, JComboBox<String> comboSexo, JComboBox<String> comboRol, JPasswordField txtPassword, JPasswordField txtConfirmPassword) {
        txtNombres.setText("Nombres");
        txtNombres.setForeground(Color.GRAY);
        
        txtApellidos.setText("Apellidos");
        txtApellidos.setForeground(Color.GRAY);
        
        txtCedula.setText("Cédula de identidad");
        txtCedula.setForeground(Color.GRAY);
        
        txtCorreo.setText("Correo Electrónico");
        txtCorreo.setForeground(Color.GRAY);
        
        comboCedula.setSelectedIndex(0);
        comboSexo.setSelectedIndex(0);
        comboRol.setSelectedIndex(0);
        
        txtPassword.setText("Contraseña");
        txtPassword.setEchoChar((char) 0);
        txtPassword.setForeground(Color.GRAY);
        
        txtConfirmPassword.setText("Confirmar Contraseña");
        txtConfirmPassword.setEchoChar((char) 0);
        txtConfirmPassword.setForeground(Color.GRAY);
    }
    
    public static void limpiarInicioSesion(JTextField txtCedula, JPasswordField txtPassword) {
    // Reset de Cédula
    txtCedula.setText("Cédula de identidad");
    txtCedula.setForeground(Color.GRAY);
    
    // Reset de Contraseña
    txtPassword.setText("Contraseña");
    txtPassword.setEchoChar((char) 0); 
    txtPassword.setForeground(Color.GRAY);
}
}
