package src.vista;

import javax.swing.*;
import src.util.BotonUtil;
import java.awt.*;

public class CambiarUsuario extends JFrame{

private JLabel titulo;
private JLabel instruccion1;
private JButton cerrar;
private JTextField cedula;
private JToolBar barra;
private JButton aceptar;
private JComboBox<String> opciones;
 

public CambiarUsuario(){
    setLayout(null);
    int pantalla= Toolkit.getDefaultToolkit().getScreenSize().width;
    int alto= Toolkit.getDefaultToolkit().getScreenSize().height;
    this.getContentPane().setBackground(new Color(255,255,255));

    setIconImage(new ImageIcon("res/logoSistemaComedor.png").getImage());

    barrasuperior(pantalla);
    fondo_panel(pantalla, alto);
    setSize(pantalla, alto);            
    setExtendedState(JFrame.MAXIMIZED_BOTH);
}

public void barrasuperior(int pantalla){

barra= new JToolBar ();
barra.setBounds(0,0,pantalla,100);
barra.setBackground(new Color(255,255,255));
barra.setBorderPainted(false);

ImageIcon icono2= new ImageIcon ("res/LogoUCV.png");
Image tam_ima= icono2.getImage().getScaledInstance(90,90,Image.SCALE_SMOOTH);
JLabel logoucv= new JLabel(new ImageIcon(tam_ima));
logoucv.setBounds(10,5,60,60);


barra.add(logoucv);
barra.addSeparator();

cerrar= new JButton ("HOME");
cerrar.setBackground(new Color(14,9,137));
cerrar.setPreferredSize(new Dimension(150,45));
cerrar.setForeground(new Color(255,255,255));
cerrar.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 14));
BotonUtil.darEstiloBoton(cerrar, 150, 30);

barra.add(Box.createHorizontalGlue());
barra.addSeparator();
barra.add(cerrar);
barra.addSeparator();
barra.addSeparator();

add(barra);
}

public void fondo_panel(int pantalla, int alto){
    Font fuente_1= new Font (Font.SANS_SERIF, Font.BOLD, 25);
    Font fuente_2= new Font (Font.SANS_SERIF, Font.BOLD, 16);

    JLayeredPane panel_capas= new JLayeredPane();
    panel_capas.setBounds(0,0,pantalla,alto);
    
    ImageIcon fondo_icon= new ImageIcon ("res/fondo_reconocimiento.jpg");
    Image tam_fondo= fondo_icon.getImage().getScaledInstance(pantalla,alto,Image.SCALE_SMOOTH);
    JLabel fondo= new JLabel(new ImageIcon(tam_fondo));
    fondo.setBounds(0,0,pantalla,alto);
    fondo.setOpaque(true);

    JPanel panel_transparente= new JPanel();
    panel_transparente.setLayout(null);
    panel_transparente.setBounds(((pantalla-800)/2),((alto-200)/4),800,540);
    panel_transparente.setBackground(new Color(255,255,255,210));

    titulo= new JLabel("SISTEMA DE CAMBIO DE ESTADO AL CONSUMIDOR");
    titulo.setBounds(100,50,760,60);
    titulo.setFont(fuente_1);
    titulo.setForeground(new Color(25,25,112));

    instruccion1= new JLabel("Ingrese la cédula del estudiante");
    instruccion1.setBounds(200,270,760,30);
    instruccion1.setFont(fuente_2);
    instruccion1.setForeground(new Color(0,0,0));

    cedula= new JTextField();
    cedula.setBounds(190,300,400,50);
    cedula.setFont(fuente_2);
    cedula.setBackground(new Color(92,180,155));

    ImageIcon icono3= new ImageIcon ("res/logoSistemaComedor.png");
    Image tam_icon= icono3.getImage().getScaledInstance(140,140,Image.SCALE_SMOOTH);
    JLabel logo= new JLabel(new ImageIcon(tam_icon));
    logo.setBounds(300,120,140,140);

    String[] opcion={"Regular","Exonerado","Becario"};

    opciones= new JComboBox<>(opcion);
    opciones.setBounds(270,370,200,40);
    opciones.setBackground(Color.WHITE);
    opciones.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 12));
    

    aceptar= new JButton ("Aceptar");
    aceptar.setBackground(new Color(14,9,137));
    aceptar.setPreferredSize(new Dimension(150,45));
    aceptar.setForeground(new Color(255,255,255));
    aceptar.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 14));
    aceptar.setBounds(300,460,150,50);
    BotonUtil.darEstiloBoton(aceptar, 150, 50);
    
    panel_transparente.add(titulo);
    panel_transparente.add(instruccion1);
    panel_transparente.add(cedula);
    panel_transparente.add(aceptar);
    panel_transparente.add(opciones);
    panel_transparente.add(logo);

    panel_capas.add(fondo,JLayeredPane.DEFAULT_LAYER);
    panel_capas.add(panel_transparente,JLayeredPane.PALETTE_LAYER);
    add(panel_capas);


}
public JTextField getTxtCedula() {
    return cedula;
}
public JComboBox getCombo() {
    return opciones;
}
public JButton getCerrar() {
    return cerrar;
}
public JButton getAceptar() {
    return aceptar;
}

}
