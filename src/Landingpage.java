import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Landingpage extends JFrame{
private JLabel titulo;
private JLabel label1;
private JLabel label2;
private JTextArea texto;
private JToolBar barra;
private JButton inicio_sesion;
private JButton registro;
private JButton acerca;
private JPanel barrita;

public Landingpage(){
setLayout(null);
int pantalla= Toolkit.getDefaultToolkit().getScreenSize().width;
int alto= Toolkit.getDefaultToolkit().getScreenSize().height;
titulo= new JLabel ("Bienvenido al sistema del comedor universitario");
texto= new JTextArea("Sistema desarrollado para la población de estudiantes y profesionales de " + "la Universidad Central de Venezuela (UCV). Ingrese al sistema del " + "Comedor y obtenga todos los beneficios de este servicio.");
this.getContentPane().setBackground(new Color(255,255,255));

barrita= new JPanel();

texto.setLineWrap(true);
texto.setWrapStyleWord(true);
texto.setEditable(false);
texto.setFocusable(false);

label1= new JLabel("Sistema Comedor Universitario");
label2= new JLabel("@comedor.ucv    @secretariaucve");

Font fuente1 = new Font ("Arial", Font.BOLD,50);
titulo.setFont(fuente1);

Font fuente2= new Font ("Arial", Font.PLAIN, 16);

Font fuente3= new Font ("Arial", Font.PLAIN, 20);

barra= new JToolBar ();
barra.setBounds(0,0,pantalla,100);
barra.setBackground(new Color(255,255,255));
barra.setBorderPainted(false);

inicio_sesion= new JButton ("Inicio de sesión");
inicio_sesion.setBackground(new Color(92,180,155));
inicio_sesion.setPreferredSize(new Dimension(150,45));
inicio_sesion.setFont(fuente2);
inicio_sesion.setForeground(new Color(0,0,0));

registro= new JButton ("Registro");
registro.setBackground(new Color(14,9,137));
registro.setPreferredSize(new Dimension(150,45));
registro.setFont(fuente2);
registro.setForeground(new Color(255,255,255));

acerca= new JButton ("Acerca de");
acerca.setBackground(new Color(14,9,137));
acerca.setPreferredSize(new Dimension(150,45));
acerca.setFont(fuente2);
acerca.setForeground(new Color(255,255,255));

ImageIcon icono1= new ImageIcon ("res/instagram.png");
Image insta_ima= icono1.getImage().getScaledInstance(30,30,Image.SCALE_SMOOTH);

ImageIcon icono2= new ImageIcon ("res/LogoUCV.png");
Image tam_ima= icono2.getImage().getScaledInstance(90,90,Image.SCALE_SMOOTH);

ImageIcon icono3= new ImageIcon ("res/comedorfoto.jpeg");
Image comedor_inicio= icono3.getImage().getScaledInstance(1140,360,Image.SCALE_SMOOTH);

JLabel imagen_central= new JLabel(new ImageIcon(comedor_inicio));
imagen_central.setBounds(110,320,1140,360);


JLabel instagram_logo= new JLabel(new ImageIcon(insta_ima));
instagram_logo.setBounds(10,10,30,30);


JLabel logoucv= new JLabel(new ImageIcon(tam_ima));
logoucv.setBounds(10,5,60,60);
label1.setBounds(200,10,200,30);
label1.setFont(fuente2);

barra.add(logoucv);
barra.addSeparator();
barra.add(label1);

barra.add(Box.createHorizontalGlue());
barra.add(inicio_sesion);
barra.addSeparator();
barra.add(registro);
barra.addSeparator();
barra.add(acerca);
barra.addSeparator();

titulo.setHorizontalAlignment(SwingConstants.CENTER);
titulo.setBounds(0,150,pantalla,90);
titulo.setForeground(new Color(25,25,112));

label2.setFont(new Font("Arial", Font.PLAIN, 14));
label2.setForeground(new Color(0,0,0));
label2.setBounds(50,5,pantalla,40);


texto.setOpaque(false);
texto.setBackground(new Color(0,0,0,0));
texto.setFont(fuente3);
texto.setBounds(110,240, 700, 300);
texto.setForeground(new Color(0,0,0));


barrita.setLayout(null);
barrita.setBackground(new Color(92,180,155));
barrita.setBounds(0,alto-70,pantalla,50);

barrita.add(instagram_logo);

barrita.add(label2);

setIconImage(new ImageIcon("res/logoSistemaComedor.png").getImage());

add(barrita);
add(titulo);
add(barra);
add(texto);
add(imagen_central);

}

public static void main (String args[]){
Landingpage inicio= new Landingpage();
inicio.setExtendedState(JFrame.MAXIMIZED_BOTH);
inicio.setResizable(false);
inicio.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
inicio.setVisible(true);
}
}