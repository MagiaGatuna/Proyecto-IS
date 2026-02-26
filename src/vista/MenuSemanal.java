package src.vista;
import src.util.Diseño_interfaz;
import javax.swing.*;
import java.awt.*;

public class MenuSemanal extends JFrame{
    private JButton lunes;
    private JButton martes;
    private JButton miercoles;
    private JButton jueves;
    private JButton viernes;
    private JButton volverHome;
    private JButton reservar_d;
    private JButton reservar_a;
    private JButton editar;
    private JButton dejar_editar;
    

    private JLabel titulo_universidad;
    private JLabel titulo_pagina;
    private JLabel aforo_d;
    private JLabel aforo_a;
    
    private JLabel turno_m;
    private JLabel turno_t;
    private JLabel turno_n;

    private JLabel comida_de;
    private JLabel descripcion_de;
    private JLabel valor_de;
    private JLabel aforo_de;

    private JLabel comida_al;
    private JLabel descripcion_al;
    private JLabel valor_al;
    private JLabel aforo_al;

    private JPanel desayuno;
    private JPanel almuerzo;
    private JPanel cena;

    private JScrollPane barra_desplazar;
    private JScrollPane barra_desplazar2;

    private JTextArea texto_desayuno;
    private JTextArea texto_almuerzo;
    

    private JToolBar barra_s;
    private JToolBar barra_d;

    private JTextField comida_d;
    private JTextField descripcion_d;
    private JTextField valor_nutricional_d;
    private JTextField aforo_max_d;
    private JButton sin_menu_d;
    private JButton aceptar_d;

    private JTextField comida_a;
    private JTextField descripcion_a;
    private JTextField valor_nutricional_a;
    private JTextField aforo_max_a;
    private JButton sin_menu_a;
     private JButton aceptar_a;


    public MenuSemanal(){
        setLayout(null);
        int pantalla= Toolkit.getDefaultToolkit().getScreenSize().width;
        int alto= Toolkit.getDefaultToolkit().getScreenSize().height; 
        this.getContentPane().setBackground(new Color(255,255,255));

        setIconImage(new ImageIcon("res/logoSistemaComedor.png").getImage());

        barra_superior(pantalla);
        barra_dias(pantalla);
        panel_desayuno(pantalla);
        panel_almuerzo(pantalla);
        panel_cena(pantalla);
        crear_campos_editar();

        setSize(pantalla, alto);              
        setExtendedState(JFrame.MAXIMIZED_BOTH);

    }



    public void barra_superior(int pantalla){
        
        Font fuente2_1= new Font ("Arial", Font.PLAIN, 16);
        titulo_universidad= new JLabel("Sistema Comedor Universitario");

        barra_s= new JToolBar ();
        barra_s.setBounds(0,0,pantalla,100);
        barra_s.setBackground(new Color(254,254,254));
        barra_s.setBorderPainted(false);

        titulo_universidad.setBounds(200,10,200,30);
        titulo_universidad.setFont(fuente2_1);

        ImageIcon icono2= new ImageIcon ("res/LogoUCV.png");
        Image tam_ima= icono2.getImage().getScaledInstance(90,90,Image.SCALE_SMOOTH);
        JLabel logoucv= new JLabel(new ImageIcon(tam_ima));
        logoucv.setBounds(10,5,60,60);
        

        barra_s.add(logoucv);
        barra_s.addSeparator();
        barra_s.add(titulo_universidad);

        volverHome = Diseño_interfaz.Creador_Botones("VOLVER", 0, 0, 150, 30, new Color(14, 9, 137));
        volverHome.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 16));

    
        editar = Diseño_interfaz.Creador_Botones("EDITAR", 0, 0, 150, 30, new Color(92, 180, 155));
        editar.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 16));

        dejar_editar = Diseño_interfaz.Creador_Botones("TERMINAR EDICIÓN", 0, 0, 190, 30, new Color(14, 9, 137));
        dejar_editar.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 16));
        

        barra_s.add(Box.createHorizontalGlue());
        barra_s.add(volverHome);
        barra_s.addSeparator();
        barra_s.add(editar);
        barra_s.addSeparator();
        barra_s.add(dejar_editar);
        barra_s.addSeparator();

        dejar_editar.setVisible(false);
        editar.setVisible(false);

        add(barra_s);

    }
    public void barra_dias(int pantalla){
        
        titulo_pagina= new JLabel(" Menús Semanales ofrecidos por el Comedor ");
        Font fuente1 = new Font ("Arial", Font.BOLD,40);
        titulo_pagina.setFont(fuente1);
        titulo_pagina.setHorizontalAlignment(SwingConstants.CENTER);
        titulo_pagina.setBounds(0,120,pantalla,40);
        titulo_pagina.setForeground(new Color(25,25,112));
        add(titulo_pagina);
        

        barra_d= new JToolBar();
        barra_d.setBounds(0,180,pantalla,60);
        barra_d.setBackground(new Color(92,180,155));
        barra_d.setBorderPainted(false);

        lunes = Diseño_interfaz.Creador_Botones("Lunes", 0, 0, 250, 30, Color.WHITE);
        lunes.setForeground(Color.BLACK);
        lunes.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 16));

        martes= Diseño_interfaz.Creador_Botones("Martes", 0, 0, 250, 30, Color.WHITE);
        martes.setForeground(Color.BLACK);
        martes.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 16));

        miercoles= Diseño_interfaz.Creador_Botones("Miércoles", 0, 0, 250, 30, Color.WHITE);
        miercoles.setForeground(Color.BLACK);
        miercoles.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 16));

        jueves= Diseño_interfaz.Creador_Botones("Jueves", 0, 0, 250, 30, Color.WHITE);
        jueves.setForeground(Color.BLACK);
        jueves.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 16));

        viernes= Diseño_interfaz.Creador_Botones("Viernes", 0, 0, 250, 30, Color.WHITE);
        viernes.setForeground(Color.BLACK);
        viernes.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 16));
        

        barra_d.addSeparator();
        barra_d.addSeparator();
        barra_d.addSeparator();
        barra_d.add(lunes);
        barra_d.addSeparator();
        barra_d.add(martes);
        barra_d.addSeparator();
        barra_d.add(miercoles);
        barra_d.addSeparator();
        barra_d.add(jueves);
        barra_d.addSeparator();
        barra_d.add(viernes);

        add(barra_d);
    }

    public void panel_desayuno(int pantalla){
        
        Font fuente1 = new Font ("Arial", Font.BOLD,16);

        turno_m=new JLabel("Turno de la Mañana: 7am a 10 am");
        turno_m.setFont(fuente1);
        turno_m.setForeground(new Color(25,25,112));
        turno_m.setBounds(120,240,300,26);
        add(turno_m);

        desayuno= new JPanel();
        desayuno.setLayout(null);
        desayuno.setBackground(new Color(92,180,155));
        desayuno.setBounds(68,270,390,430);

        Font fuente2 = new Font ("Arial", Font.BOLD,16);
        Font fuente3 = new Font ("Arial", Font.BOLD,14);
        aforo_d= new JLabel("Reservas Actuales: ");
        aforo_d.setFont(fuente3);
        aforo_d.setForeground(new Color(0,0,0));
        aforo_d.setBounds(20,350,240,30);
        desayuno.add(aforo_d);

        texto_desayuno= new JTextArea();
        texto_desayuno.setText("no hay menu");
        texto_desayuno.setFont(fuente2);
        texto_desayuno.setForeground(new Color(0,0,0));
        texto_desayuno.setBackground(new Color(255,255,255));
        texto_desayuno.setEditable(false);

        barra_desplazar= new JScrollPane(texto_desayuno);
        barra_desplazar.setBounds(20,40,350,300);
    
        desayuno.add(barra_desplazar);

        reservar_d= Diseño_interfaz.Creador_Botones("RESERVAR", 280, 380, 100, 40, Color.WHITE);
        
        desayuno.add(reservar_d);

        add(desayuno);

    }
    public void panel_almuerzo(int pantalla){
        Font fuente1 = new Font ("Arial", Font.BOLD,16);
        Font fuente3 = new Font ("Arial", Font.BOLD,14);

        turno_t=new JLabel("Turno de la Tarde: 12m a 3pm");
        turno_t.setFont(fuente1);
        turno_t.setForeground(new Color(25,25,112));
        turno_t.setBounds(566,240,300,26);
        add(turno_t);

        almuerzo= new JPanel();
        almuerzo.setLayout(null);
        almuerzo.setBackground(new Color(92,180,155));
        almuerzo.setBounds(495,270,390,430);

        Font fuente2 = new Font ("Arial", Font.BOLD,16);
        aforo_a= new JLabel("Reservas Actuales: ");
        aforo_a.setFont(fuente3);
        aforo_a.setForeground(new Color(0,0,0));
        aforo_a.setBounds(20,350,240,30);
        almuerzo.add(aforo_a);

        texto_almuerzo= new JTextArea();
        texto_almuerzo.setText("no hay menu");
        texto_almuerzo.setFont(fuente2);
        texto_almuerzo.setForeground(new Color(0,0,0));
        texto_almuerzo.setBackground(new Color(255,255,255));
        texto_almuerzo.setEditable(false);

        barra_desplazar2= new JScrollPane(texto_almuerzo);
        barra_desplazar2.setBounds(20,40,350,300);
    
        almuerzo.add(barra_desplazar2);

        reservar_a= Diseño_interfaz.Creador_Botones("RESERVAR", 280, 380, 100, 40, Color.WHITE);
        
        
        almuerzo.add(reservar_a);

        add(almuerzo);

    }
    public void panel_cena(int pantalla){
        Font fuente1 = new Font ("Arial", Font.BOLD,16);
        Font fuente3 = new Font ("Arial", Font.BOLD,14);

        turno_n=new JLabel("Turno de la Noche: 6pm a 9pm");
        turno_n.setFont(fuente1);
        turno_n.setForeground(new Color(25,25,112));
        turno_n.setBounds(980,240,300,26);
        add(turno_n);

        cena= new JPanel();
        cena.setLayout(null);
        cena.setBackground(new Color(92,180,155));
        cena.setBounds(920,270,390,430);

        ImageIcon iconofuera= new ImageIcon ("res/fuera_servicio.jpg");
        Image tam_fuera_s= iconofuera.getImage().getScaledInstance(350,380,Image.SCALE_SMOOTH);
        JLabel fuera_servicio= new JLabel(new ImageIcon(tam_fuera_s));
        fuera_servicio.setBounds(20,20,350,380);

        cena.add(fuera_servicio);
        add(cena);

    }

    public void crear_campos_editar(){
        

        Font fuente_label= new Font(Font.SANS_SERIF, Font.PLAIN, 12);
        comida_de=new JLabel("Ingrese comida");
        comida_de.setFont(fuente_label);
        comida_de.setForeground(new Color(0,0,0));
        comida_de.setBounds(40,20,280,20);
        desayuno.add(comida_de);

        descripcion_de=new JLabel("Ingrese descripción");
        descripcion_de.setFont(fuente_label);
        descripcion_de.setForeground(new Color(0,0,0));
        descripcion_de.setBounds(40,120,280,20);
        desayuno.add(descripcion_de);

        valor_de=new JLabel("Ingrese el valor nutricional");
        valor_de.setFont(fuente_label);
        valor_de.setForeground(new Color(0,0,0));
        valor_de.setBounds(40,210,280,20);
        desayuno.add(valor_de);

        aforo_de=new JLabel("Ingrese cantidad de bandejas");
        aforo_de.setFont(fuente_label);
        aforo_de.setForeground(new Color(0,0,0));
        aforo_de.setBounds(40,290,280,20);
        desayuno.add(aforo_de);

        comida_al=new JLabel("Ingrese comida");
        comida_al.setFont(fuente_label);
        comida_al.setForeground(new Color(0,0,0));
        comida_al.setBounds(40,20,280,20);
        almuerzo.add(comida_al);

        descripcion_al=new JLabel("Ingrese descripción");
        descripcion_al.setFont(fuente_label);
        descripcion_al.setForeground(new Color(0,0,0));
        descripcion_al.setBounds(40,120,280,20);
        almuerzo.add(descripcion_al);

        valor_al=new JLabel("Ingrese el valor nutricional");
        valor_al.setFont(fuente_label);
        valor_al.setForeground(new Color(0,0,0));
        valor_al.setBounds(40,210,280,20);
        almuerzo.add(valor_al);

        aforo_al=new JLabel("Ingrese cantidad de bandejas");
        aforo_al.setFont(fuente_label);
        aforo_al.setForeground(new Color(0,0,0));
        aforo_al.setBounds(40,290,280,20);
        almuerzo.add(aforo_al);
//
        comida_d= new JTextField();
        comida_d.setBounds(40,60,280,50);
        comida_d.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 14));
        comida_d.setBackground(new Color(255,255,255));

        descripcion_d=new JTextField();
        descripcion_d.setBounds(40,150,280,50);
        descripcion_d.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 14));
        descripcion_d.setBackground(new Color(255,255,255));

        valor_nutricional_d=new JTextField();
        valor_nutricional_d.setBounds(40,240,280,50);
        valor_nutricional_d.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 14));
        valor_nutricional_d.setBackground(new Color(255,255,255));

        aforo_max_d=new JTextField();
        aforo_max_d.setBounds(40,320,280,50);
        aforo_max_d.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 14));
        aforo_max_d.setBackground(new Color(255,255,255));

        sin_menu_d= Diseño_interfaz.Creador_Botones("Sin Menu", 230, 382, 120, 30, Color.WHITE);
        sin_menu_d.setForeground(Color.BLACK);
        sin_menu_d.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 16));


        comida_a= new JTextField();
        comida_a.setBounds(40,60,280,50);
        comida_a.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 14));
        comida_a.setBackground(new Color(255,255,255));

        descripcion_a=new JTextField();
        descripcion_a.setBounds(40,150,280,50);
        descripcion_a.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 14));
        descripcion_a.setBackground(new Color(255,255,255));

        valor_nutricional_a=new JTextField();
        valor_nutricional_a.setBounds(40,240,280,50);
        valor_nutricional_a.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 14));
        valor_nutricional_a.setBackground(new Color(255,255,255));

        aforo_max_a=new JTextField();
        aforo_max_a.setBounds(40,320,280,50);
        aforo_max_a.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 14));
        aforo_max_a.setBackground(new Color(255,255,255));

        sin_menu_a= Diseño_interfaz.Creador_Botones("Sin Menu", 230, 382, 120, 30, Color.WHITE);
        sin_menu_a.setForeground(Color.BLACK);
        sin_menu_a.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 16));

        aceptar_d= Diseño_interfaz.Creador_Botones("Aceptar Edición", 40, 382, 170, 30, Color.WHITE);
        aceptar_d.setForeground(Color.BLACK);
        aceptar_d.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 14));

        aceptar_a= Diseño_interfaz.Creador_Botones("Aceptar Edición", 40, 382, 170, 30, Color.WHITE);
        aceptar_a.setForeground(Color.BLACK);
        aceptar_a.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 14));


        almuerzo.add(sin_menu_a);
        almuerzo.add(aforo_max_a);
        almuerzo.add(valor_nutricional_a);
        almuerzo.add(descripcion_a);
        almuerzo.add(comida_a);
        almuerzo.add(aceptar_a);

        desayuno.add(sin_menu_d);
        desayuno.add(aforo_max_d);
        desayuno.add(valor_nutricional_d);
        desayuno.add(descripcion_d);
        desayuno.add(comida_d);
        desayuno.add(aceptar_d);

       sin_menu_d.setVisible(false);
       aforo_max_d.setVisible(false);
       valor_nutricional_d.setVisible(false);
       descripcion_d.setVisible(false);
       comida_d.setVisible(false);
       aceptar_d.setVisible(false);

       sin_menu_a.setVisible(false);
       aforo_max_a.setVisible(false);
       valor_nutricional_a.setVisible(false);
       descripcion_a.setVisible(false);
       comida_a.setVisible(false);
       aceptar_a.setVisible(false);

        comida_de.setVisible(false);
        descripcion_de.setVisible(false);
        valor_de.setVisible(false);
        aforo_de.setVisible(false);

        comida_al.setVisible(false);
        descripcion_al.setVisible(false);
        valor_al.setVisible(false);
        aforo_al.setVisible(false);
    }

    public JButton getvolver(){
        return volverHome;
    }
    public JButton getEditar(){
        return editar;
    }
    public JButton getNoEditar(){
        return dejar_editar;
    }

    public void editar_paneles(){

        aforo_a.setVisible(false);
        barra_desplazar2.setVisible(false);
    
        aforo_d.setVisible(false);
        barra_desplazar.setVisible(false);

        editar.setVisible(false);
        dejar_editar.setVisible(true);
        
        reservar_d.setVisible(false);
        reservar_a.setVisible(false);

        aceptar_d.setVisible(true);
        aceptar_a.setVisible(true);

       sin_menu_d.setVisible(true);
       aforo_max_d.setVisible(true);
       valor_nutricional_d.setVisible(true);
       descripcion_d.setVisible(true);
       comida_d.setVisible(true);

       sin_menu_a.setVisible(true);
       aforo_max_a.setVisible(true);
       valor_nutricional_a.setVisible(true);
       descripcion_a.setVisible(true);
       comida_a.setVisible(true);
       
        comida_de.setVisible(true);
        descripcion_de.setVisible(true);
        valor_de.setVisible(true);
        aforo_de.setVisible(true);

        comida_al.setVisible(true);
        descripcion_al.setVisible(true);
        valor_al.setVisible(true);
        aforo_al.setVisible(true);
    }

    public void dejar_editar(){

        aforo_a.setVisible(true);
        barra_desplazar2.setVisible(true);
    
        aforo_d.setVisible(true);
        barra_desplazar.setVisible(true);

        editar.setVisible(true);
        dejar_editar.setVisible(false);
        //hasta aqui el panel queda como antes

        reservar_d.setVisible(true);
        reservar_a.setVisible(true);

        aceptar_d.setVisible(false);
        aceptar_a.setVisible(false);

       sin_menu_d.setVisible(false);
       aforo_max_d.setVisible(false);
       aforo_max_d.setText("");
       valor_nutricional_d.setVisible(false);
       valor_nutricional_d.setText("");
       descripcion_d.setVisible(false);
       descripcion_d.setText("");
       comida_d.setVisible(false);
       comida_d.setText("");

       sin_menu_a.setVisible(false);
       aforo_max_a.setVisible(false);
       aforo_max_a.setText("");
       valor_nutricional_a.setVisible(false);
       valor_nutricional_a.setText("");
       descripcion_a.setVisible(false);
       descripcion_a.setText("");
       comida_a.setVisible(false);
       comida_a.setText("");

        comida_de.setVisible(false);
        descripcion_de.setVisible(false);
        valor_de.setVisible(false);
        aforo_de.setVisible(false);

        comida_al.setVisible(false);
        descripcion_al.setVisible(false);
        valor_al.setVisible(false);
        aforo_al.setVisible(false);
    }

    public JButton getreservas(String indicador){
        if(indicador=="desayuno"){
            return reservar_d;
        }
        if(indicador=="almuerzo"){
            return reservar_a;
        }
        
        return null;
    }

    public void ocultaradmin(){
        reservar_d.setVisible(false);
        reservar_a.setVisible(false);
    }

    public JLabel getaforo(String id){
        if(id=="desayuno"){
            return aforo_d;
        }
        if(id=="almuerzo"){
            return aforo_a;
        }
        
        return null;
    }

    public JTextArea get_texto(String id){
        if(id=="desayuno"){
            return texto_desayuno;
        }
        if(id=="almuerzo"){
            return texto_almuerzo;
        }
        
        return null;
    }

    public void setColorBoton(String hoy, Color colorcito){
        JButton boton_auxiliar= getboton_dia(hoy);
        if(boton_auxiliar != null){
            boton_auxiliar.setBackground(colorcito);
            boton_auxiliar.setBorderPainted(false);
            boton_auxiliar.setOpaque(true);
            boton_auxiliar.setContentAreaFilled(true);
        }

    }

    public JButton getboton_dia(String diferenciador){
        if (diferenciador.equals("MONDAY")){
            return lunes;
        }else if (diferenciador.equals("TUESDAY")){
            return martes;
        }else if (diferenciador.equals("WEDNESDAY")){
            return miercoles;
        }else if (diferenciador.equals("THURSDAY")){
            return jueves;
        }else if (diferenciador.equals("FRIDAY")){
            return viernes;
        }else{
            return null;
        }
        
    }

    public JButton getDefecto(String turno){
        if(turno.equals("Desayuno")){
            return sin_menu_d;
        }else if(turno.equals("Almuerzo")){
            return sin_menu_a;
        }else{
            return null;
        }

    }

    public JButton getAceptar(String turno){
        if(turno.equals("Desayuno")){
            return aceptar_d;
        }else if(turno.equals("Almuerzo")){
            return aceptar_a;
        }else{
            return null;
        }

    }

    public String getTexto(String turno,String campo) {
        
        if(turno.equals("Desayuno") && campo.equals("Comida")){
            return comida_d.getText().trim();
        }
        if(turno.equals("Desayuno") && campo.equals("Descripcion")){
            return descripcion_d.getText().trim();
        }
        if(turno.equals("Desayuno") && campo.equals("Valor_nutricional")){
            return valor_nutricional_d.getText().trim();
        }
        if(turno.equals("Desayuno") && campo.equals("Aforo")){
            return aforo_max_d.getText().trim();
        }

        if(turno.equals("Almuerzo") && campo.equals("Comida")){
            return comida_a.getText().trim();
        }
        if(turno.equals("Almuerzo") && campo.equals("Descripcion")){
            return descripcion_a.getText().trim();
        }
        if(turno.equals("Almuerzo") && campo.equals("Valor_nutricional")){
            return valor_nutricional_a.getText().trim();
        }
        if(turno.equals("Almuerzo") && campo.equals("Aforo")){
            return aforo_max_a.getText().trim();
        }

        return "";
    }
    public void setTexto(String turno,String campo) {
        
        if(turno.equals("Desayuno") && campo.equals("Comida")){
            comida_d.setText("");;
        }
        if(turno.equals("Desayuno") && campo.equals("Descripcion")){
            descripcion_d.setText("");;
        }
        if(turno.equals("Desayuno") && campo.equals("Valor_nutricional")){
            valor_nutricional_d.setText("");;
        }
        if(turno.equals("Desayuno") && campo.equals("Aforo")){
            aforo_max_d.setText("");;
        }

        if(turno.equals("Almuerzo") && campo.equals("Comida")){
            comida_a.setText("");;
        }
        if(turno.equals("Almuerzo") && campo.equals("Descripcion")){
            descripcion_a.setText("");;
        }
        if(turno.equals("Almuerzo") && campo.equals("Valor_nutricional")){
            valor_nutricional_a.setText("");;
        }
        if(turno.equals("Almuerzo") && campo.equals("Aforo")){
            aforo_max_a.setText("");;
        }

    }
    
    public static void main (String args[]){
    MenuSemanal m_semanal= new MenuSemanal();
    m_semanal.setExtendedState(JFrame.MAXIMIZED_BOTH);
    //m_semanal.setResizable(false);
    m_semanal.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    m_semanal.setVisible(true);
    }

    public JButton getBtnRes1() {
        return reservar_a;
    }

    public JButton getBtnRes3() {
        return reservar_d;
    }
    
    

}
