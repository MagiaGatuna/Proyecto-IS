package src.util;
import src.modelo.Usuario;
public class Calcular {
   
    public static double CCB = 5.0; 

    public static double calcularPrecio(Usuario u) {
        String rol = u.getRol().toLowerCase();
        
        if (rol.equals("estudiante")) {
            return CCB * 0.30; 
        } else if (rol.equals("profesor")) {
            return CCB * 0.80; 
        } else if (rol.equals("empleado")) {
            return CCB; 
        } else {
            return CCB; 
        }
    }
}