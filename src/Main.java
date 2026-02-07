package src;
import src.modelo.Usuario;
import src.controlador.Controlador_reg;
import src.controlador.Controlador_inicioS;
import src.controlador.Controlador_lp;
import src.controlador.Controlador_Alumno_Empleado;
import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        Landingpage inicio = new Landingpage();
        Registro registro_b = new Registro();
        InicioSesion in_sesion = new InicioSesion();
        HomeAdmin admin = null; 
        AlumnoView alumno = null;
        EmpleadoView empleado = null;

        Controlador_lp control1 = new Controlador_lp(inicio, registro_b, in_sesion);
        Controlador_reg control2 = new Controlador_reg(inicio, registro_b, in_sesion);
        
        Controlador_inicioS control3 = new Controlador_inicioS(inicio, registro_b, in_sesion);
        MenuSemanal menu_s = new MenuSemanal();
        Controlador_Alumno_Empleado control4 = new Controlador_Alumno_Empleado(inicio, alumno, empleado,menu_s);

        inicio.setExtendedState(JFrame.MAXIMIZED_BOTH);
        inicio.setResizable(false);
        inicio.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        inicio.setVisible(true);
    }
}