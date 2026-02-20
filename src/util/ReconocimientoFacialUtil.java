package src.util;

import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;
import javax.swing.JOptionPane;

public class ReconocimientoFacialUtil {

    public static boolean validarIdentidad(File imagenSubida, File imagenBaseDatos) {
        try {
            BufferedImage imgSubida = ImageIO.read(imagenSubida);
            BufferedImage imgBD = ImageIO.read(imagenBaseDatos);

            if (imgSubida.getWidth() != imgBD.getWidth() || imgSubida.getHeight() != imgBD.getHeight()) {
                return false; 
            }

            for (int y = 0; y < imgSubida.getHeight(); y++) {
                for (int x = 0; x < imgSubida.getWidth(); x++) {
                    if (imgSubida.getRGB(x, y) != imgBD.getRGB(x, y)) {
                        return false; 
                    }
                }
            }
            
            return true; 
            
        } catch (Exception e) {
                JOptionPane.showMessageDialog(null, 
                "Error: Las condiciones faciales no coinciden o la imagen es inválida.\nPor favor, comuníquese con Secretaría para resolver este problema.", 
                "Fallo en Reconocimiento", 
                JOptionPane.ERROR_MESSAGE);
                return false;
        }
    }
}