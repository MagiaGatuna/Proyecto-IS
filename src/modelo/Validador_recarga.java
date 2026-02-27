package src.modelo;

import javax.swing.JOptionPane;
import javax.swing.JTextField;

import src.util.ValidarUtil;

public class Validador_recarga {
public Validador_recarga(){

}
public static boolean ValidarCampos(JTextField id, JTextField ref, JTextField monto, Usuario user) {
    StringBuilder errores = new StringBuilder();
    boolean hayErrores = false;

    // Validación de Cédula
    if (ValidarUtil.campoEstaVacio(id, "Cédula de identidad")) {
        errores.append("- El campo Cédula es obligatorio.\n");
        hayErrores = true;
    } else if (!ValidarUtil.cedulaEsValida(id)) {
        errores.append("- La cédula debe contener solo números.\n");
        hayErrores = true;
    } else if (!(user.getCedula().equals(id.getText().trim()))) {
        errores.append("- La cédula no coincide con el usuario actual.\n");
        hayErrores = true;
    }

    // Validación de Referencia
    if (ValidarUtil.campoEstaVacio(ref, "Número de referencia")) {
        errores.append("- El campo Referencia es obligatorio.\n");
        hayErrores = true;
    } else if (!ValidarUtil.ReferenciaValida(ref)) {
        errores.append("- Referencia inválida (use los últimos 4 dígitos).\n");
        hayErrores = true;
    }

    // Validación de Monto
    if (ValidarUtil.campoEstaVacio(monto, "Monto a recargar")) {
        errores.append("- El campo Monto es obligatorio.\n");
        hayErrores = true;
    } else if (!ValidarUtil.MontoValido(monto)) {
        errores.append("- El monto no es válido (ej: 100.0),tampoco < 0.\n");
        hayErrores = true;
    }

    // SI HAY ERRORES, MOSTRAMOS LA VENTANA
    if (hayErrores) {
        JOptionPane.showMessageDialog(null, errores.toString(), "Errores de Validación", JOptionPane.WARNING_MESSAGE);
        return false;
    }

    return true;
}
}
