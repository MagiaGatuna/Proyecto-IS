import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Landingpage extends JFrame{
private JLabel titulo;
private JToolBar barra;
private JButton inicio_sesion;
private JButton registro;
private JButton acerca;

public Landingpage(){
setLayout(null);
int pantalla= Toolkit.getDefaultToolkit().getScreenSize().width;
titulo= new JLabel ("Bienvenido al sistema del comedor universitario");
this.getContentPane().setBackground(new Color(255,255,255));

Font fuente1 = new Font ("Arial", Font.BOLD,50);
titulo.setFont(fuente1);

Font fuente2= new Font ("Arial", Font.PLAIN, 16);

barra= new JToolBar ();
barra.setBounds(0,0,pantalla,80);
barra.setBackground(new Color(90,223,217));
barra.setBorderPainted(false);

inicio_sesion= new JButton ("Inicio de sesión");
inicio_sesion.setBackground(Color.WHITE);
inicio_sesion.setPreferredSize(new Dimension(150,45));
inicio_sesion.setFont(fuente2);

registro= new JButton ("Registro");
registro.setBackground(Color.WHITE);
registro.setPreferredSize(new Dimension(150,45));
registro.setFont(fuente2);

acerca= new JButton ("Acerca de");
acerca.setBackground(Color.WHITE);
acerca.setPreferredSize(new Dimension(150,45));
acerca.setFont(fuente2);

barra.add(Box.createHorizontalGlue());
barra.add(inicio_sesion);
barra.addSeparator();
barra.add(registro);
barra.addSeparator();
barra.add(acerca);
barra.addSeparator();

titulo.setHorizontalAlignment(SwingConstants.CENTER);
titulo.setBounds(0,210,pantalla,90);
titulo.setForeground(new Color(25,25,112));

add(titulo);
add(barra);
}

public static void main (String args[]){
Landingpage inicio= new Landingpage();
inicio.setExtendedState(JFrame.MAXIMIZED_BOTH);
inicio.setResizable(false);
inicio.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
inicio.setVisible(true);
}
}