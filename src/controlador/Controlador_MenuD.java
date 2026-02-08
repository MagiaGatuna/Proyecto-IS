package src.controlador;

import src.util.Calcular_dia;
import src.AlumnoView;
import src.MenuDView;
import src.EmpleadoView;

import src.modelo.validadorInicioS;
import src.modelo.Menus_lista;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import java.awt.Color;

public class Controlador_MenuD implements ActionListener{
    private AlumnoView alumno;
    private EmpleadoView empleado;
    private MenuDView menu;
    private String Rol="";

    private int hora;
    private String dia;

    public Controlador_MenuD(AlumnoView alumno,EmpleadoView empleado,MenuDView menu){
        this.alumno= alumno;
        this.empleado= empleado;
        this.menu= menu;

        hora= Calcular_dia.gethora();
        dia= Calcular_dia.getdia();

        menu.getHome().addActionListener(this);

    }

    @Override
    public void actionPerformed(ActionEvent e){
        Rol = validadorInicioS.getRol();
       
        if(e.getSource()==menu.getHome() ){//Aqui va un && con el JSON del tipo de usuario
            
            if((Rol.equals("Estudiante"))){
                if(this.alumno != null){
            alumno.setExtendedState(JFrame.MAXIMIZED_BOTH);
            alumno.setResizable(false);
            alumno.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            alumno.setVisible(true);
            menu.setVisible(false);
            menu.dispose();
                }
            }
            
        }

        
        if(e.getSource()==menu.getHome() && (Rol.equals("Trabajador")||Rol.equals("Docente"))){//Aqui va un && con el JSON del tipo de usuario
            if(this.empleado != null){
            empleado.setExtendedState(JFrame.MAXIMIZED_BOTH);
            empleado.setResizable(false);
            empleado.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            empleado.setVisible(true);
            menu.setVisible(false);
            menu.dispose();
            }
        }

    }
}
