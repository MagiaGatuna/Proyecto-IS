package src.util;
import java.awt.*;
import javax.swing.*;

public class PasswordYPlaceholderUtil {
    public static void configurarPasswordConPlaceholder(JPasswordField campo, String texto) {
        
        campo.setEchoChar((char) 0); 
        campo.setText(texto);
        campo.setForeground(Color.GRAY);
        campo.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 14));

        campo.addFocusListener(new java.awt.event.FocusAdapter() {
            @Override
            public void focusGained(java.awt.event.FocusEvent evt) {
                
                String passActual = new String(campo.getPassword());
                if (passActual.equals(texto)) {
                    campo.setText("");
                    campo.setEchoChar('•'); 
                    campo.setForeground(Color.BLACK);
                }
            }

            @Override
            public void focusLost(java.awt.event.FocusEvent evt) {
                
                if (campo.getPassword().length == 0) {
                    campo.setEchoChar((char) 0); 
                    campo.setText(texto);
                    campo.setForeground(Color.GRAY);
                }
            }
        });
    }

    public static void configurarPlaceholder(JTextField campo, String texto){
        campo.setForeground(Color.GRAY);
        campo.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 14));
        
        campo.addFocusListener(new java.awt.event.FocusAdapter() {
            @Override
            public void focusGained(java.awt.event.FocusEvent evt) {
                if (campo.getText().equals(texto)) {
                    campo.setText("");
                    campo.setForeground(Color.BLACK);
                }
            }

            @Override
            public void focusLost(java.awt.event.FocusEvent evt) {
                if (campo.getText().isEmpty()) {
                    campo.setForeground(Color.GRAY);
                    campo.setText(texto);
                }
            }}
        );
    }

    public static JToggleButton crearBotonMostrarOcultar(
            JPasswordField passwordField,
            int x, int y, int ancho, int alto) {
        
        ImageIcon iconVer = null;
        ImageIcon iconOcultar = null;
        String rutaIconoVer = "res/ojoAbierto.png";
        String rutaIconoOcultar = "res/ojoCerrado.png";
        
        try{
            ImageIcon tempIconVer = new ImageIcon(rutaIconoVer);
            if(tempIconVer.getImageLoadStatus() == MediaTracker.COMPLETE){
                iconVer = new ImageIcon(tempIconVer.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH));
            }
        }catch(Exception e){
            System.out.println("Error al cargar icono ver: " + e.getMessage());
        }
        
        try{
            ImageIcon tempIconOcultar = new ImageIcon(rutaIconoOcultar);
            if(tempIconOcultar.getImageLoadStatus() == MediaTracker.COMPLETE){
                iconOcultar = new ImageIcon(tempIconOcultar.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH));
            }
        }catch(Exception e){
            System.out.println("Error al cargar icono ocultar: " + e.getMessage());
        }

        if (iconVer == null && iconOcultar == null) {
            System.out.println("No se pudieron cargar los iconos de mostrar/ocultar contraseña.");
            return null;
        }
        
        ImageIcon finalIconVer = (iconVer != null) ? iconVer : iconOcultar;
        ImageIcon finalIconOcultar = (iconOcultar != null) ? iconOcultar : iconVer;
        
        JToggleButton btnToggle = new JToggleButton(finalIconVer);
        btnToggle.setBounds(x, y, ancho, alto);
        btnToggle.setContentAreaFilled(false); 
        btnToggle.setBorderPainted(false);
        btnToggle.setFocusPainted(false);
        btnToggle.setCursor(new Cursor(Cursor.HAND_CURSOR));
        
        btnToggle.addActionListener(e -> {
            if(btnToggle.isSelected()){
                passwordField.setEchoChar((char) 0);  
                btnToggle.setIcon(finalIconOcultar);
            }else{
                passwordField.setEchoChar('•');       
                btnToggle.setIcon(finalIconVer);
            }
        });
        
        return btnToggle;
    }
}
