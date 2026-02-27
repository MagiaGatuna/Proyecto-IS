package src.util;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class ValidarUtil {
    public static boolean esEmailValido(String email) {
        return email.contains("@") && email.contains(".") && 
            email.indexOf("@") < email.lastIndexOf(".");
    }

    public static boolean campoEstaVacio(JTextField campo, String textoPlaceholder) {
        return campo.getText().trim().isEmpty() || campo.getText().equals(textoPlaceholder);
    }

    public static boolean cedulaEsValida(JTextField cedula) {
        if (!cedula.getText().trim().matches("\\d+")){
            return false;
        }
        return true;
    }
    public static boolean MontoValido( JTextField monto) {
       try {
       
        String texto = monto.getText().trim().replace(",", ".");
        double mon = Double.parseDouble(texto);

        if (mon <= 0) {
    
            JOptionPane.showMessageDialog(null, "Error: El monto debe ser mayor a cero.");
            return false;
        }
        return true;

    } catch (NumberFormatException e) {
        JOptionPane.showMessageDialog(null, "Error: El monto debe ser un número válido (ej: 100.50).");
        return false;
    }
}
    public static boolean ReferenciaValida(JTextField ref){
        if(ref.getText().trim().length()==4){
            return true;
        }
        return false;
    }

}
