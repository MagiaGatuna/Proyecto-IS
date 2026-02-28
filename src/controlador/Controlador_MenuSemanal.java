package src.controlador;

import src.util.Calcular;
import src.util.Calcular_dia;
import src.vista.AlumnoView;
import src.vista.HomeAdmin;
import src.vista.MenuSemanal;
import src.vista.EmpleadoView;

import src.modelo.validadorInicioS;
import src.modelo.Menus_lista;
import src.modelo.Reserva;
import src.modelo.ReservaDAO;
import src.modelo.Usuario;
import src.modelo.UsuarioDAO;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

import org.json.JSONObject;

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
        menu.getAceptar("Desayuno").addActionListener(this);
        menu.getAceptar("Almuerzo").addActionListener(this);
        menu.getDefecto("Desayuno").addActionListener(this);
        menu.getDefecto("Almuerzo").addActionListener(this);
        menu.getreservas("desayuno").addActionListener(this);
        menu.getreservas("almuerzo").addActionListener(this);


        Rol = validadorInicioS.getRol();

        if(Rol.equals("Administrador")){
            menu.getEditar().setVisible(true);
            menu.getNoEditar().setVisible(false);
            menu.getAceptar("Desayuno").setVisible(false);
            menu.getAceptar("Almuerzo").setVisible(false);
            menu.ocultaradmin();
            
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

    }

    @Override
    public void actionPerformed(ActionEvent e){
        Rol = validadorInicioS.getRol();
       
        if(e.getSource()==menu.getvolver() ){
            if((Rol.equals("Estudiante"))){
                if(this.alumno != null){
            alumno.setExtendedState(JFrame.MAXIMIZED_BOTH);
            //alumno.setResizable(false);
            alumno.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            alumno.setVisible(true);
            menu.setVisible(false);
            menu.dispose();
                }
            }
            
        }

        if (e.getSource() == menu.getreservas("desayuno")) {
        realizarReserva("DESAYUNO");
        } else if (e.getSource() == menu.getreservas("almuerzo")) {
        realizarReserva("ALMUERZO");
        }

        
        if(e.getSource()==menu.getvolver() && (Rol.equals("Trabajador")||Rol.equals("Docente"))){//Aqui va un && con el JSON del tipo de usuario
            if(this.empleado != null){
        empleado.setExtendedState(JFrame.MAXIMIZED_BOTH);
            //empleado.setResizable(false);
            empleado.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            empleado.setVisible(true);
            menu.setVisible(false);
            menu.dispose();
            }
        }

        if(e.getSource()==menu.getvolver() && (Rol.equals("Administrador"))){//Aqui va un && con el JSON del tipo de usuario
            if(this.admin != null){
            admin.setExtendedState(JFrame.MAXIMIZED_BOTH);
            //admin.setResizable(false);
            admin.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            admin.setVisible(true);
            menu.setVisible(false);
            menu.dispose();
            }
        }

        if((e.getSource()==menu.getEditar())&&(Rol.equals("Administrador"))){
            menu.editar_paneles();
            menu.ocultaradmin();
        }
        if((e.getSource()==menu.getNoEditar())&&(Rol.equals("Administrador"))){
            menu.dejar_editar();
            menu.ocultaradmin();
        }
        
       if(e.getSource()==menu.getboton_dia("MONDAY")){//Aqui va un && con el JSON del tipo de usuario
            pinta = "MONDAY";
            dia_seleccionado="MONDAY";
            desactivar_botones(hora);
            if(Rol.equals("Administrador")){
            menu.getAceptar("Desayuno").setVisible(false);
            menu.getAceptar("Almuerzo").setVisible(false);
            menu.ocultaradmin();
            }
        }
        if(e.getSource()==menu.getboton_dia("TUESDAY")){//Aqui va un && con el JSON del tipo de usuario
            pinta = "TUESDAY";
            dia_seleccionado="TUESDAY";
            desactivar_botones(hora);
            if(Rol.equals("Administrador")){
            menu.getAceptar("Desayuno").setVisible(false);
            menu.getAceptar("Almuerzo").setVisible(false);
            menu.ocultaradmin();
            }
        }
        if(e.getSource()==menu.getboton_dia("WEDNESDAY")){//Aqui va un && con el JSON del tipo de usuario
             pinta = "WEDNESDAY";
            dia_seleccionado="WEDNESDAY";
            desactivar_botones(hora);
            if(Rol.equals("Administrador")){
            menu.getAceptar("Desayuno").setVisible(false);
            menu.getAceptar("Almuerzo").setVisible(false);
            menu.ocultaradmin();
            }
        }
        if(e.getSource()==menu.getboton_dia("THURSDAY")){//Aqui va un && con el JSON del tipo de usuario
             pinta = "THURSDAY";
            dia_seleccionado="THURSDAY";
            desactivar_botones(hora);
            if(Rol.equals("Administrador")){
            menu.getAceptar("Desayuno").setVisible(false);
            menu.getAceptar("Almuerzo").setVisible(false);
            menu.ocultaradmin();
            }
        }
        if(e.getSource()==menu.getboton_dia("FRIDAY")){//Aqui va un && con el JSON del tipo de usuario
             pinta = "FRIDAY";
            dia_seleccionado="FRIDAY";
            desactivar_botones(hora);
            if(Rol.equals("Administrador")){
            menu.getAceptar("Desayuno").setVisible(false);
            menu.getAceptar("Almuerzo").setVisible(false);
            menu.ocultaradmin();
            }
        }

        if(e.getSource()==menu.getAceptar("Desayuno")){
                Menus_lista.actualizarMenu(dia_seleccionado, "DESAYUNO", menu.getTexto("Desayuno","Comida"), menu.getTexto("Desayuno","Descripcion"),menu.getTexto("Desayuno","Valor_nutricional"), menu.getTexto("Desayuno","Aforo"));
                vaciar_campos("Desayuno");
        
        }
        if(e.getSource()==menu.getAceptar("Almuerzo")){

        Menus_lista.actualizarMenu(dia_seleccionado, "ALMUERZO", menu.getTexto("Almuerzo","Comida"), menu.getTexto("Almuerzo","Descripcion"),menu.getTexto("Almuerzo","Valor_nutricional"), menu.getTexto("Almuerzo","Aforo"));
        vaciar_campos("Almuerzo");
        }
        if(e.getSource()==menu.getDefecto("Desayuno")){
        
        Menus_lista.actualizarMenu(dia_seleccionado, "DESAYUNO", "Lo sentimos, no hay menu para este turno", " ","0", "0");
        vaciar_campos("Desayuno");

        }
        if(e.getSource()==menu.getDefecto("Almuerzo")){
        Menus_lista.actualizarMenu(dia_seleccionado, "ALMUERZO", "Lo sentimos, no hay menu para este turno", " ","0", "0");
        vaciar_campos("Almuerzo");
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

public void vaciar_campos(String turno){
    if(turno.equals("Desayuno")){
        menu.setTexto("Desayuno","Comida");
        menu.setTexto("Desayuno","Descripcion");
        menu.setTexto("Desayuno","Valor_nutricional");
        menu.setTexto("Desayuno","Aforo");
    }else{
        menu.setTexto("Almuerzo","Comida");
        menu.setTexto("Almuerzo","Descripcion");
        menu.setTexto("Almuerzo","Valor_nutricional");
        menu.setTexto("Almuerzo","Aforo");
    }
    
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

        if(Rol.equals("Administrador")){
            menu.getAceptar("Desayuno").setVisible(false);
            menu.getAceptar("Almuerzo").setVisible(false);
            return;
        }

        int indice_hoy= Calcular_dia.getIndiceDia(dia);
        int indice_seleccion= Calcular_dia.getIndiceDia(dia_seleccionado);

        if(indice_hoy==5 && (indice_seleccion==1 || indice_seleccion==0)){
             menu.getreservas("desayuno").setEnabled(true);
             menu.getreservas("almuerzo").setEnabled(true);
             menu.getreservas("desayuno").setForeground(new Color(0,0,0));
             menu.getreservas("almuerzo").setForeground(new Color(0,0,0));
        }else if(indice_hoy>indice_seleccion){
        menu.getreservas("desayuno").setEnabled(false);
        menu.getreservas("almuerzo").setEnabled(false);
        }else if(indice_hoy==indice_seleccion){
            if(minutos>=420){
                menu.getreservas("desayuno").setEnabled(false);
            }
            if(minutos>=720){
                menu.getreservas("almuerzo").setEnabled(false);
            }
        }else if(indice_hoy<indice_seleccion){
        menu.getreservas("desayuno").setEnabled(true);
        menu.getreservas("almuerzo").setEnabled(true);
        menu.getreservas("desayuno").setForeground(new Color(0,0,0));
        menu.getreservas("almuerzo").setForeground(new Color(0,0,0));
        }

    }

private void realizarReserva(String turno) {
        try {
            Usuario usuario = validadorInicioS.getUsuarioActual();
            if (usuario == null) {
                JOptionPane.showMessageDialog(menu, "No hay usuario logueado.");
                return;
            }

            String diaIngles = dia_seleccionado; 
            JSONObject menuData = Menus_lista.getMenuData(diaIngles, turno);
            if (menuData == null) {
                JOptionPane.showMessageDialog(menu, "No hay menú disponible para este turno.");
                return;
            }

            if (!menuData.has("comida") || menuData.getString("comida").trim().isEmpty() || menuData.getString("comida").equals("Lo sentimos, no hay menu para este turno")) {
                JOptionPane.showMessageDialog(menu, "Este menú aún no está disponible para reservas.");
                return;
            }

            int aforoMax = menuData.optInt("aforo_max", 0);
            int reservasActuales = menuData.optInt("reservas_actual", 0);
            if (reservasActuales >= aforoMax && aforoMax > 0) {
                JOptionPane.showMessageDialog(menu, "¡Lo sentimos! El aforo máximo para este menú ya está lleno.");
                return;
            }

            String idMenu = menuData.getString("dia_turno");
            double precioFinal = 0;
            try {
                precioFinal = Calcular.calcularPrecio(idMenu, usuario.getRol());
            } catch (Exception ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(menu, "Error al calcular el precio: " + ex.getMessage());
                return;
            }
            
            Reserva reservaExistente = ReservaDAO.buscarPorCedula(usuario.getCedula());
            if (reservaExistente != null) {
                JOptionPane.showMessageDialog(menu, "Ya tienes una reserva activa para: " + reservaExistente.getDiaTurno());
                return;
            }
            if (usuario.getSaldo() < precioFinal) {
                JOptionPane.showMessageDialog(menu, String.format("Saldo insuficiente.\nSu saldo es: %.2f Bs\nCosto del menú: %.2f Bs.", usuario.getSaldo(), precioFinal));
                return;
            }

            int opcion = JOptionPane.showConfirmDialog(menu,
                "¿Confirmar reserva?\n" +
                "Menú: " + menuData.getString("comida") + "\n" +
                "Precio final: " + precioFinal + " Bs\n" +
                "Saldo restante: " + (usuario.getSaldo() - precioFinal) + " Bs",
                "Confirmar reserva",
                JOptionPane.YES_NO_OPTION);

            if (opcion == JOptionPane.YES_OPTION) {

                    Reserva nuevaReserva = new Reserva(usuario.getCedula(), idMenu);
                    ReservaDAO.guardar(nuevaReserva);

                    Menus_lista.incrementarReserva(idMenu);
                    Menus_lista.mostrarMenu(menu.get_texto("desayuno"), menu.getaforo("desayuno"), dia_seleccionado, "DESAYUNO");
                    Menus_lista.mostrarMenu(menu.get_texto("almuerzo"), menu.getaforo("almuerzo"), dia_seleccionado, "ALMUERZO");

                    JOptionPane.showMessageDialog(menu, "¡Reserva realizada con éxito!");
                
            }

        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(menu, "Error inesperado: " + e.getMessage());
        }
    }
}