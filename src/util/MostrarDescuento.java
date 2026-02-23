package src.util;

import src.modelo.Usuario;

public class MostrarDescuento {
     public static String Descuento(Usuario u) {
        String rol = u.getRol().toLowerCase();
        String rango="";
        if (rol.equals("estudiante")) {
           rango="20%-30%";
            return rango;
        } else if (rol.equals("profesor")) {
            rango="70%-90%";
            return rango;
        } else if (rol.equals("empleado")) {
            rango="90%-110%";
            return rango;
        } 
        return " ";
    }
    
}
