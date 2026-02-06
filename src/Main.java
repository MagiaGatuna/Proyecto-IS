package src;

import src.controlador.Controlador_reg;
import src.controlador.Controlador_inicioS;
import src.controlador.Controlador_lp;
import src.controlador.Controlador_Alumno_Empleado;
import src.controlador.Controlador_MenuSemanal;
import javax.swing.*;

public class Main{
    public static void main(String[] args){
        Landingpage inicio= new Landingpage();
        Registro registro_b= new Registro();
        InicioSesion in_sesion= new InicioSesion();
        HomeAdmin admin= new HomeAdmin();
        AlumnoView alumno= new AlumnoView("");
        EmpleadoView empleado= new EmpleadoView("");
        MenuSemanal menu_semanal= new MenuSemanal();
        

        Controlador_lp control1;
        control1= new Controlador_lp(inicio, registro_b, in_sesion);
        Controlador_reg control2;
        control2= new Controlador_reg(inicio, registro_b, in_sesion);
        Controlador_inicioS control3;
        control3=new Controlador_inicioS(inicio, registro_b, in_sesion, admin,alumno,empleado);
        Controlador_Alumno_Empleado control4;
        control4= new Controlador_Alumno_Empleado(inicio,alumno,empleado,menu_semanal);
        Controlador_MenuSemanal control5;
        control5= new Controlador_MenuSemanal(alumno,empleado,menu_semanal);

        inicio.setExtendedState(JFrame.MAXIMIZED_BOTH);
        inicio.setResizable(false);
        inicio.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        inicio.setVisible(true);
    }

}