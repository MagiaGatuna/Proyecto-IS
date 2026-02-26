package src.util;
import javax.swing.*;
import java.awt.*;

public class BotonUtil {
    public static void darEstiloBoton(JButton boton, int ancho, int alto){
        
        Color colorOriginal = boton.getBackground();
        Color colorHover;
            if(colorOriginal.equals(Color.WHITE)){
                colorHover = colorOriginal.darker(); 
            }else{
                colorHover = new Color(
                    Math.min(colorOriginal.getRed()   + 40, 255),
                    Math.min(colorOriginal.getGreen() + 40, 255),
                    Math.min(colorOriginal.getBlue()  + 40, 255)
                );
            }
        
        Dimension dimension = new Dimension(ancho, alto);
        boton.setPreferredSize(dimension);
        boton.setMinimumSize(dimension);
        boton.setMaximumSize(dimension);
    
        boton.setFocusPainted(false);
        boton.setBorderPainted(false); 
        boton.setContentAreaFilled(true);

        boton.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                boton.setBackground(colorHover);
                boton.setCursor(new Cursor(Cursor.HAND_CURSOR));
            }

            @Override
            public void mouseExited(java.awt.event.MouseEvent evt) {
                boton.setBackground(colorOriginal);
                boton.setBorder(BorderFactory.createLineBorder(Color.WHITE, 1));
            }
        });
    }
}
