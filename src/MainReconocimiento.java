package src;

import src.controlador.Controlador_Reconocimiento;
import src.vista.Reconocimiento_facial;
import src.vista.Landingpage;
import javax.swing.JFrame;

public class MainReconocimiento {
    public static void main(String[] args) {
        Reconocimiento_facial vistaFacial = new Reconocimiento_facial();
        //Creamos una landing "fantasma" o nula solo para no romper el constructor del controlador
        Landingpage dummyLanding = new Landingpage(); 
        new Controlador_Reconocimiento(dummyLanding, vistaFacial);
        vistaFacial.setExtendedState(JFrame.MAXIMIZED_BOTH);
        vistaFacial.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        vistaFacial.setVisible(true);
    }
}