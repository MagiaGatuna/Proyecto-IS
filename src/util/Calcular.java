package src.util;
import src.modelo.Usuario;
import src.modelo.MermayCCB; 

public class Calcular {
    public static double PorcentajeEstudiante = 20.0; 
    public static double PorcentajeBecario = 5.0;     //5% (siempre menor al regular)
    public static double PorcentajeProfesor = 70.0;   
    public static double PorcentajeEmpleado = 90.0;   

    // OJOOOOO Cambié el parámetro de String rol a Usuario usuario
    public static double calcularPrecio(String idMenu, Usuario usuario) {
        double CCB = src.modelo.MermayCCB.getCCB(idMenu);
        String rol = usuario.getRol();
        
        if (rol.equalsIgnoreCase("estudiante")) {
            String estado = usuario.getCondicion();
            
            if ("Exonerado".equalsIgnoreCase(estado)) {
                return 0.0; // El exonerado no paga nada
            } else if ("Becario".equalsIgnoreCase(estado)) {
                return CCB * (PorcentajeBecario / 100.0); 
            } else {
                return CCB * (PorcentajeEstudiante / 100.0); 
            }
            
        } else if (rol.equalsIgnoreCase("profesor")) {
            return CCB * (PorcentajeProfesor / 100.0); 
        } else if (rol.equalsIgnoreCase("empleado")) {
            return CCB * (PorcentajeEmpleado / 100.0); 
        } else {
            return CCB; 
        }
    }

    public static boolean cambiarPorcentaje(double NuevoPorcentaje, String tipo) {
        if (tipo.equalsIgnoreCase("estudiante")) {
            PorcentajeEstudiante = NuevoPorcentaje;
            return true;
        } else if (tipo.equalsIgnoreCase("becario")) { // Para que el Admin pueda cambiarlo
            PorcentajeBecario = NuevoPorcentaje;
            return true;
        } else if (tipo.equalsIgnoreCase("profesor")) {
            PorcentajeProfesor = NuevoPorcentaje;
            return true;
        } else if (tipo.equalsIgnoreCase("empleado")) {
            PorcentajeEmpleado = NuevoPorcentaje;
            return true;
        }
        return false;
    }
}