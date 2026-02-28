package src.util;
import src.modelo.Usuario;

import javax.swing.JOptionPane;

import src.modelo.MermayCCB;

public class Calcular {
   // 
    public static double PorcentajeEstudiante = 20.0; //20% a 30%
    public static double PorcentajeProfesor = 70.0; //70% a 90%
    public static double PorcentajeEmpleado = 90.0; //90% a 110%

    public static double calcularPrecio(String idMenu, String rol) {
        double CCB = src.modelo.MermayCCB.getCCB(idMenu);
        if (rol.equalsIgnoreCase("estudiante")) {
            return CCB * (PorcentajeEstudiante / 100.0); 
        } else if (rol.equalsIgnoreCase("profesor")) {
            return CCB * (PorcentajeProfesor / 100.0); 
        } else if (rol.equalsIgnoreCase("empleado")) {
            return CCB * (PorcentajeEmpleado / 100.0); 
        } else {
            return CCB; 
        }
    }

    public static boolean cambiarPorcentaje(double NuevoPorcentaje, String rol) {
        
        if (rol.equals("estudiante")) {
            PorcentajeEstudiante = NuevoPorcentaje;
            return true;
        } else if (rol.equals("profesor")) {
            PorcentajeProfesor = NuevoPorcentaje;
            return true;
        } else if (rol.equals("empleado")) {
            PorcentajeEmpleado = NuevoPorcentaje;
            return true;
        } else {
            JOptionPane.showMessageDialog(null, "Rol no reconocido");
            return false;
        }
    }
}