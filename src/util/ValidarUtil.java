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
        double mon = Double.parseDouble(monto.getText().trim());

        if (mon <= 0) {
            JOptionPane.showMessageDialog(null, "Error: El monto debe ser mayor a cero.");
            return false;
        }
    

    } catch (NumberFormatException e) {
        JOptionPane.showMessageDialog(null, "Error: El monto debe ser un número válido (ej: 100.50).");
        return false;
    }
      return false;
    }
    public static boolean ReferenciaValida(JTextField ref){
        if (ref.getText().trim().length() > 4) {
        JOptionPane.showMessageDialog(null, "Error: El número de referencia es demasiado largo, por favor colocar los ultimos 4 digitos.");
        return false;
    }
    return true;
    }

}
