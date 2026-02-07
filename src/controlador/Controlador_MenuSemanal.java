package src.controlador;

import src.util.Calcular_dia;
import src.AlumnoView;
import src.MenuSemanal;
import src.EmpleadoView;

import src.modelo.validadorInicioS;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import java.awt.Color;

public class Controlador_MenuSemanal implements ActionListener{
    
    private AlumnoView alumno;
    private EmpleadoView empleado;
    private MenuSemanal menu;
    private String Rol="";

    private int hora;
    private String dia;
    private String pinta;

    public Controlador_MenuSemanal(AlumnoView alumno,EmpleadoView empleado,MenuSemanal menu){
        this.alumno= alumno;
        this.empleado= empleado;
        this.menu= menu;

        hora= Calcular_dia.gethora();
        dia= Calcular_dia.getdia();

        this.menu.getboton_dia("MONDAY").addActionListener(this);
        this.menu.getboton_dia("TUESDAY").addActionListener(this);
        this.menu.getboton_dia("WEDNESDAY").addActionListener(this);
        this.menu.getboton_dia("THURSDAY").addActionListener(this);
        this.menu.getboton_dia("FRIDAY").addActionListener(this);
        this.menu.getvolver().addActionListener(this);
        

        pintarboton(dia); 
        desactivar_botones(hora);

    }

    @Override
    public void actionPerformed(ActionEvent e){
        Rol = validadorInicioS.getRol();
       
        if(e.getSource()==menu.getvolver() ){//Aqui va un && con el JSON del tipo de usuario
            
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

        
        if(e.getSource()==menu.getvolver() && (Rol.equals("Trabajador")||Rol.equals("Docente"))){//Aqui va un && con el JSON del tipo de usuario
            if(this.empleado != null){
            empleado.setExtendedState(JFrame.MAXIMIZED_BOTH);
            empleado.setResizable(false);
            empleado.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            empleado.setVisible(true);
            menu.setVisible(false);
            menu.dispose();
            }
        }
        
       if(e.getSource()==menu.getboton_dia("MONDAY")){//Aqui va un && con el JSON del tipo de usuario
        pinta = "MONDAY";

        }
        if(e.getSource()==menu.getboton_dia("TUESDAY")){//Aqui va un && con el JSON del tipo de usuario
            pinta = "TUESDAY";
            
        }
        if(e.getSource()==menu.getboton_dia("WEDNESDAY")){//Aqui va un && con el JSON del tipo de usuario
             pinta = "WEDNESDAY";

        }
        if(e.getSource()==menu.getboton_dia("THURSDAY")){//Aqui va un && con el JSON del tipo de usuario
             pinta = "THURSDAY";
            
        }
        if(e.getSource()==menu.getboton_dia("FRIDAY")){//Aqui va un && con el JSON del tipo de usuario
             pinta = "FRIDAY";
            
        }

        pintarboton(pinta);
        menu.repaint();
    }
    
    public void pintarboton(String hoy){
            
                menu.setColorBoton("FRIDAY",new Color(255,255,255));
                menu.setColorBoton("THURSDAY",new Color(255,255,255));
                menu.setColorBoton("WEDNESDAY",new Color(255,255,255));
                menu.setColorBoton("TUESDAY",new Color(255,255,255));
                menu.setColorBoton("MONDAY",new Color(255,255,255));

                menu.setColorBoton(hoy,new Color(180,236,227));

                menu.repaint();
            }

    public void desactivar_botones(int minutos){
        if(minutos>=420){
            menu.getreservas("desayuno").setEnabled(false);
        }
        if(minutos>=720){
            menu.getreservas("almuerzo").setEnabled(false);
        }
        if(minutos>=1080){
            menu.getreservas("cena").setEnabled(false);
        }
    }
    
    //get_texto y getaforo

}
