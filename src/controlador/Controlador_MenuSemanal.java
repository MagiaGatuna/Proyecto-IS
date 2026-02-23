package src.controlador;

import src.util.Calcular_dia;
import src.vista.AlumnoView;
import src.vista.HomeAdmin;
import src.vista.MenuSemanal;
import src.vista.EmpleadoView;

import src.modelo.validadorInicioS;
import src.modelo.Menus_lista;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import java.awt.Color;

public class Controlador_MenuSemanal implements ActionListener{
    
    private AlumnoView alumno;
    private EmpleadoView empleado;
    private MenuSemanal menu;
    private String Rol="";
    private HomeAdmin admin;

    private int hora;
    private String dia;
    private String dia_seleccionado;
    private String pinta;

    public Controlador_MenuSemanal(AlumnoView alumno,EmpleadoView empleado, HomeAdmin admin ,MenuSemanal menu){
        this.alumno= alumno;
        this.empleado= empleado;
        this.menu= menu;
        this.admin=admin;

        hora= Calcular_dia.gethora();
        dia= Calcular_dia.getdia();

        menu.getEditar().addActionListener(this);
        menu.getNoEditar().addActionListener(this);

        Rol = validadorInicioS.getRol();

        if(Rol.equals("Administrador")){
            menu.getEditar().setVisible(true);
            menu.getNoEditar().setVisible(false);
        }else{
            menu.getEditar().setVisible(false);
            menu.getNoEditar().setVisible(false);
        }

        if(dia.equals("SATURDAY") || dia.equals("SUNDAY")){
            dia_seleccionado="MONDAY";
        }else{
            dia_seleccionado=dia;
        }
            Menus_lista.mostrarMenu(menu.get_texto("desayuno"), menu.getaforo("desayuno"),dia_seleccionado,"DESAYUNO");
            Menus_lista.mostrarMenu(menu.get_texto("almuerzo"), menu.getaforo("almuerzo"),dia_seleccionado,"ALMUERZO");

      String[] diasSemana = {"MONDAY", "TUESDAY", "WEDNESDAY", "THURSDAY", "FRIDAY"};

for (String d : diasSemana) {
    JButton btn = this.menu.getboton_dia(d);
    if (btn != null) { 
        btn.addActionListener(this);
    }
}

if (this.menu.getvolver() != null) {
    this.menu.getvolver().addActionListener(this);
}
        pintarboton(dia); 
        desactivar_botones(hora);

    avisoProximamente(this.menu.getBtnRes1());
    
    avisoProximamente(this.menu.getBtnRes3());

    }

    @Override
    public void actionPerformed(ActionEvent e){
        Rol = validadorInicioS.getRol();
       
        if(e.getSource()==menu.getvolver() ){
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

        if(e.getSource()==menu.getvolver() && (Rol.equals("Administrador"))){//Aqui va un && con el JSON del tipo de usuario
            if(this.admin != null){
            admin.setExtendedState(JFrame.MAXIMIZED_BOTH);
            admin.setResizable(false);
            admin.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            admin.setVisible(true);
            menu.setVisible(false);
            menu.dispose();
            }
        }

        if((e.getSource()==menu.getEditar())&&(Rol.equals("Administrador"))){
            menu.editar_paneles();
        }
        if((e.getSource()==menu.getNoEditar())&&(Rol.equals("Administrador"))){
            menu.dejar_editar();
        }
        
       if(e.getSource()==menu.getboton_dia("MONDAY")){//Aqui va un && con el JSON del tipo de usuario
            pinta = "MONDAY";
            dia_seleccionado="MONDAY";
        }
        if(e.getSource()==menu.getboton_dia("TUESDAY")){//Aqui va un && con el JSON del tipo de usuario
            pinta = "TUESDAY";
            dia_seleccionado="TUESDAY";
        }
        if(e.getSource()==menu.getboton_dia("WEDNESDAY")){//Aqui va un && con el JSON del tipo de usuario
             pinta = "WEDNESDAY";
            dia_seleccionado="WEDNESDAY";
        }
        if(e.getSource()==menu.getboton_dia("THURSDAY")){//Aqui va un && con el JSON del tipo de usuario
             pinta = "THURSDAY";
            dia_seleccionado="THURSDAY";
        }
        if(e.getSource()==menu.getboton_dia("FRIDAY")){//Aqui va un && con el JSON del tipo de usuario
             pinta = "FRIDAY";
            dia_seleccionado="FRIDAY";
        }

        

        if(dia_seleccionado != null && dia_seleccionado != "SUNDAY" && dia_seleccionado != "SATURDAY"){
            Menus_lista.mostrarMenu(menu.get_texto("desayuno"), menu.getaforo("desayuno"),dia_seleccionado,"DESAYUNO");
            Menus_lista.mostrarMenu(menu.get_texto("almuerzo"), menu.getaforo("almuerzo"),dia_seleccionado,"ALMUERZO");
        }else if(dia_seleccionado.equals("SUNDAY")|| dia_seleccionado.equals("SATURDAY")){
           
            Menus_lista.mostrarMenu(menu.get_texto("desayuno"), menu.getaforo("desayuno"),"MONDAY","DESAYUNO");
            Menus_lista.mostrarMenu(menu.get_texto("almuerzo"), menu.getaforo("almuerzo"),"MONDAY","ALMUERZO");
        }

        pintarboton(pinta);
        menu.repaint();
    }
public void pintarboton(String hoy) {
    String[] diasArr = {"MONDAY", "TUESDAY", "WEDNESDAY", "THURSDAY", "FRIDAY"};

    // Ponemos todos en blanco, pero validando que existan
    for (String d : diasArr) {
        JButton b = menu.getboton_dia(d);
        if (b != null) {
            b.setBackground(new Color(255, 255, 255));
        }
    }

    // Pintamos el día actual 
    JButton btnHoy = menu.getboton_dia(hoy);
    if (btnHoy != null) { 
        btnHoy.setBackground(new Color(180, 236, 227));
    }
}
    public void desactivar_botones(int minutos){
        /* 
        //Esta funcionalidad de desactivar la posibilida de reserva,
        // segun el dia y hora actual, será realizado en proximos Sprints,
        //pero se muestra un pequeño adelanto comentado, debido a que ello no entra en
        el sprint actual
        if(minutos>=420){
            menu.getreservas("desayuno").setEnabled(false);
        }
        if(minutos>=720){
            menu.getreservas("almuerzo").setEnabled(false);
        }
       
        */

        menu.getreservas("desayuno").setEnabled(false);
        menu.getreservas("almuerzo").setEnabled(false);
    }

    private void avisoProximamente(JButton boton) {
            if (boton != null) {
                boton.addActionListener(e -> {
                    JOptionPane.showMessageDialog(null, 
                        "Esta funcionalidad estará disponible en la próxima actualización.", 
                        "En construcción", 
                        JOptionPane.INFORMATION_MESSAGE);
                });
            }
        }
    
    

}
