package src.controlador;

import src.vista.Landingpage;
import src.vista.Reconocimiento_facial;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class Controlador_Reconocimiento implements ActionListener{

    private Landingpage inicio;
    private Reconocimiento_facial reconocimiento;

    public Controlador_Reconocimiento(Landingpage inicio, Reconocimiento_facial reconocimiento){
        this.inicio=inicio;
        this.reconocimiento= reconocimiento;
        this.reconocimiento.getCerrar().addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e){

        if(e.getSource()==reconocimiento.getCerrar()){

            
            inicio.setExtendedState(JFrame.MAXIMIZED_BOTH);
            inicio.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            inicio.setVisible(true);
            reconocimiento.setVisible(false);
        }
        

    }
}