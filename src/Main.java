package src;
import src.controlador.Controlador_lp;
import javax.swing.*;

public class Main{
    public static void main(String[] args){
        Landingpage inicio= new Landingpage();
        Registro registro_b= new Registro();

        Controlador_lp control1;
        control1= new Controlador_lp(inicio, registro_b);

        inicio.setExtendedState(JFrame.MAXIMIZED_BOTH);
        inicio.setResizable(false);
        inicio.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        inicio.setVisible(true);
    }

}