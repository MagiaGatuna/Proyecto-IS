package src;
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
    private JButton reservar_c;

    private JLabel titulo_universidad;
    private JLabel titulo_pagina;
    private JLabel aforo_d;
    private JLabel aforo_a;
    private JLabel aforo_c;
    private JLabel turno_m;
    private JLabel turno_t;
    private JLabel turno_n;

    private JPanel desayuno;
    private JPanel almuerzo;
    private JPanel cena;

    private JTextArea texto_desayuno;
    private JTextArea texto_almuerzo;
    private JTextArea texto_cena;

    private JToolBar barra_s;
    private JToolBar barra_d;

    public MenuSemanal(){
        setLayout(null);
        int pantalla= Toolkit.getDefaultToolkit().getScreenSize().width;
        this.getContentPane().setBackground(new Color(255,255,255));

        setIconImage(new ImageIcon("res/logoSistemaComedor.png").getImage());

        barra_superior(pantalla);
        barra_dias(pantalla);
        panel_desayuno(pantalla);
        panel_almuerzo(pantalla);
        panel_cena(pantalla);

    }

    private void darEstiloBoton(JButton boton, int ancho, int alto) {
        
    Color colorOriginal = boton.getBackground();
    Color colorHover = colorOriginal.brighter(); 
    
    Dimension dimension = new Dimension(ancho, alto);
    boton.setPreferredSize(dimension);
    boton.setMinimumSize(dimension);
    boton.setMaximumSize(dimension);

    boton.setFocusPainted(false);
    boton.setBorderPainted(false); 
    boton.setContentAreaFilled(true);

    boton.addMouseListener(new java.awt.event.MouseAdapter() {
        @Override
        public void mouseEntered(java.awt.event.MouseEvent evt) {
            boton.setBackground(colorHover);
            boton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        }

        @Override
        public void mouseExited(java.awt.event.MouseEvent evt) {
            boton.setBackground(colorOriginal);
            boton.setBorder(BorderFactory.createLineBorder(Color.WHITE, 1));
        }
    });
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

        volverHome= new JButton("Volver a Home");
        volverHome.setBackground(new Color(14,9,137));
        volverHome.setPreferredSize(new Dimension(150,45));
        volverHome.setForeground(new Color(255,255,255));
        volverHome.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 16));
        darEstiloBoton(volverHome, 150, 30);
        
        barra_s.add(Box.createHorizontalGlue());
        barra_s.add(volverHome);
        barra_s.addSeparator();

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

        lunes= new JButton("Lunes");
        lunes.setBackground(new Color(255,255,255));
        lunes.setPreferredSize(new Dimension(150,45));
        lunes.setForeground(new Color(0,0,0));
        lunes.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 16));
        darEstiloBoton(lunes, 250, 30);

        martes= new JButton("Martes");
        martes.setBackground(new Color(255,255,255));
        martes.setPreferredSize(new Dimension(150,45));
        martes.setForeground(new Color(0,0,0));
        martes.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 16));
        darEstiloBoton(martes, 250, 30);

        miercoles= new JButton("Miercoles");
        miercoles.setBackground(new Color(255,255,255));
        miercoles.setPreferredSize(new Dimension(150,45));
        miercoles.setForeground(new Color(0,0,0));
        miercoles.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 16));
        darEstiloBoton(miercoles, 250, 30);

        jueves= new JButton("Jueves");
        jueves.setBackground(new Color(255,255,255));
        jueves.setPreferredSize(new Dimension(150,45));
        jueves.setForeground(new Color(0,0,0));
        jueves.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 16));
        darEstiloBoton(jueves, 250, 30);

        viernes= new JButton("Viernes");
        viernes.setBackground(new Color(255,255,255));
        viernes.setPreferredSize(new Dimension(150,45));
        viernes.setForeground(new Color(0,0,0));
        viernes.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 16));
        darEstiloBoton(viernes, 250, 30);

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
        turno_m.setBounds(120,260,300,26);
        add(turno_m);

        desayuno= new JPanel();
        desayuno.setLayout(null);
        desayuno.setBackground(new Color(92,180,155));
        desayuno.setBounds(68,290,390,430);

        Font fuente2 = new Font ("Arial", Font.BOLD,16);
        aforo_d= new JLabel("Reservas Actuales: ");
        aforo_d.setFont(fuente2);
        aforo_d.setForeground(new Color(0,0,0));
        aforo_d.setBounds(20,380,200,30);
        desayuno.add(aforo_d);

        texto_desayuno= new JTextArea();
        texto_desayuno.setText("no hay menu");
        texto_desayuno.setFont(fuente2);
        texto_desayuno.setForeground(new Color(0,0,0));
        texto_desayuno.setBackground(new Color(255,255,255));
        texto_desayuno.setEditable(false);

        JScrollPane barra_desplazar= new JScrollPane(texto_desayuno);
        barra_desplazar.setBounds(20,40,350,300);
    
        desayuno.add(barra_desplazar);

        reservar_d= new JButton("RESERVAR");
        reservar_d.setBackground(new Color(255,255,255));
        reservar_d.setPreferredSize(new Dimension(100,40));
        reservar_d.setForeground(new Color(0,0,0));
        reservar_d.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 14));
        reservar_d.setBounds(280,380,100,40);
        darEstiloBoton(reservar_d, 100, 40);
        
        desayuno.add(reservar_d);

        add(desayuno);

    }
    public void panel_almuerzo(int pantalla){
        Font fuente1 = new Font ("Arial", Font.BOLD,16);

        turno_t=new JLabel("Turno de la Tarde: 12m a 3pm");
        turno_t.setFont(fuente1);
        turno_t.setForeground(new Color(25,25,112));
        turno_t.setBounds(566,260,300,26);
        add(turno_t);

        almuerzo= new JPanel();
        almuerzo.setLayout(null);
        almuerzo.setBackground(new Color(92,180,155));
        almuerzo.setBounds(495,290,390,430);

        Font fuente2 = new Font ("Arial", Font.BOLD,16);
        aforo_a= new JLabel("Reservas Actuales: ");
        aforo_a.setFont(fuente2);
        aforo_a.setForeground(new Color(0,0,0));
        aforo_a.setBounds(20,380,200,30);
        almuerzo.add(aforo_a);

        texto_almuerzo= new JTextArea();
        texto_almuerzo.setText("no hay menu");
        texto_almuerzo.setFont(fuente2);
        texto_almuerzo.setForeground(new Color(0,0,0));
        texto_almuerzo.setBackground(new Color(255,255,255));
        texto_almuerzo.setEditable(false);

        JScrollPane barra_desplazar2= new JScrollPane(texto_almuerzo);
        barra_desplazar2.setBounds(20,40,350,300);
    
        almuerzo.add(barra_desplazar2);

        reservar_a= new JButton(" RESERVAR ");
        reservar_a.setBackground(new Color(255,255,255));
        reservar_a.setPreferredSize(new Dimension(100,40));
        reservar_a.setForeground(new Color(0,0,0));
        reservar_a.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 14));
        reservar_a.setBounds(280,380,100,40);
        darEstiloBoton(reservar_a, 100, 40);
        
        almuerzo.add(reservar_a);

        add(almuerzo);

    }
    public void panel_cena(int pantalla){
        Font fuente1 = new Font ("Arial", Font.BOLD,16);

        turno_n=new JLabel("Turno de la Noche: 6pm a 9pm");
        turno_n.setFont(fuente1);
        turno_n.setForeground(new Color(25,25,112));
        turno_n.setBounds(980,260,300,26);
        add(turno_n);

        cena= new JPanel();
        cena.setLayout(null);
        cena.setBackground(new Color(92,180,155));
        cena.setBounds(920,290,390,430);

        Font fuente2 = new Font ("Arial", Font.BOLD,16);
        aforo_c= new JLabel("Reservas Actuales: ");
        aforo_c.setFont(fuente2);
        aforo_c.setForeground(new Color(0,0,0));
        aforo_c.setBounds(20,380,200,30);
        cena.add(aforo_c);

        texto_cena= new JTextArea();
        texto_cena.setText("no hay menu");
        texto_cena.setFont(fuente2);
        texto_cena.setForeground(new Color(0,0,0));
        texto_cena.setBackground(new Color(255,255,255));
        texto_cena.setEditable(false);

        JScrollPane barra_desplazar3= new JScrollPane(texto_cena);
        barra_desplazar3.setBounds(20,40,350,300);
    
        cena.add(barra_desplazar3);

        reservar_c= new JButton(" RESERVAR ");
        reservar_c.setBackground(new Color(255,255,255));
        reservar_c.setPreferredSize(new Dimension(100,40));
        reservar_c.setForeground(new Color(0,0,0));
        reservar_c.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 14));
        reservar_c.setBounds(280,380,100,40);
        darEstiloBoton(reservar_c, 100, 40);
        
        cena.add(reservar_c);

        add(cena);

    }

    public JButton getvolver(){
        return volverHome;
    }

    //ver si estos los puedo generalizar
    public JButton getboton_dia(String diferenciador){
        if (diferenciador=="Lunes"){
            return lunes;
        }else if (diferenciador=="Martes"){
            return martes;
        }else if (diferenciador=="Miercoles"){
            return miercoles;
        }else if (diferenciador=="Jueves"){
            return jueves;
        }else if (diferenciador=="Viernes"){
            return viernes;
        }else{
            return null;
        }
        
    }
    
    public static void main (String args[]){
    MenuSemanal m_semanal= new MenuSemanal();
    m_semanal.setExtendedState(JFrame.MAXIMIZED_BOTH);
    m_semanal.setResizable(false);
    m_semanal.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    m_semanal.setVisible(true);
    }
    
}
