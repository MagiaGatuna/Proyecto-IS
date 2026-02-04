package src.util;
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

}
