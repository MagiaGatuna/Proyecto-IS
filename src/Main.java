package src;

import src.controlador.Controlador_reg;
import src.controlador.Controlador_inicioS;
import src.controlador.Controlador_lp;
import javax.swing.*;
import src.vista.Landingpage;
import src.vista.Registro;
import src.vista.InicioSesion;

public class Main{
    public static void main(String[] args){
        Landingpage inicio= new Landingpage();
        Registro registro_b= new Registro();
        InicioSesion in_sesion= new InicioSesion();

        Controlador_lp control1;
        control1= new Controlador_lp(inicio, registro_b, in_sesion);
        Controlador_reg control2;
        control2= new Controlador_reg(inicio, registro_b, in_sesion);
        Controlador_inicioS control3;
        control3=new Controlador_inicioS(inicio, registro_b, in_sesion);


        inicio.setExtendedState(JFrame.MAXIMIZED_BOTH);
        inicio.setResizable(false);
        inicio.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        inicio.setVisible(true);
    }

}