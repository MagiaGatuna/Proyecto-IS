package src.modelo;

import javax.swing.JTextField;

import src.util.ValidarUtil;

public class Validador_recarga {
public Validador_recarga(){

}
public static boolean ValidarCampos(JTextField id, JTextField ref, JTextField monto, Usuario user){

StringBuilder errores = new StringBuilder();
if(ValidarUtil.campoEstaVacio(id, "Cédula de identidad")){
errores.append("- El campo Cédula es obligatorio\n\n");
}else if(!ValidarUtil.cedulaEsValida(id)){
errores.append("- La cédula debe contener solo números\n");
 
}
if(ValidarUtil.campoEstaVacio(ref, "Numero de referencia")){
errores.append("- El campo Referencia es obligatorio\n\n");

}else if(!ValidarUtil.ReferenciaValida(ref)){
    errores.append("- Coloque los ultimos 4 digitos, ejem: ref=123456, usted debe colocar 3456\n\n");
    return false;
}
if(ValidarUtil.campoEstaVacio(monto, "Monto a recargar")){
    return false; 
} else if(!ValidarUtil.MontoValido(monto)){
    return false;
}
 String cedulaInput = id.getText().trim();
if(!(user.getCedula().equals(cedulaInput))){
return false;
}
return true;
}
    
}
