package src.util;

import javax.swing.*; 
import java.awt.*;

public class Diseño_interfaz {

public static final Color colorazul=new Color(60, 60, 140);
public static final Color turquesa=new Color(158, 200, 185);

//factorizamos los elementos mas comunes
public static JButton Creador_Botones(String texto, int x, int y, int ancho, int alto, Color fondo){
    JButton nuevo_boton = new JButton(texto);//creamos un objeto para el boton
    nuevo_boton.setBounds(x, y,ancho, alto);//colocamos las coordenadas de el nuevo objeto
    nuevo_boton.setBackground(fondo);//Por diseño,aqui agregariamos el color azul
    nuevo_boton.setForeground(Color.WHITE);//Para legibilidad, agregamos color blanco a las letras de el boton
    nuevo_boton.setFocusable(false);
    return nuevo_boton;//retornamos el nuevo diseño
}

public static JLabel Creador_iconos(String ruta, int x, int y, int ancho, int alto){

    ImageIcon icono= new ImageIcon(ruta);//creamos un objeto que busque la imagen en la carpeta
    Image copiaicono= icono.getImage().getScaledInstance(ancho, alto, Image.SCALE_SMOOTH);//extraemos la informacion de la imagen,luego copia esos datos y cmabia el tamaño,y le indica que no sea pixeleada
    ImageIcon icono_final = new ImageIcon(copiaicono);//creamos un objeto que tenga el nuevo logo ajustado a nuestro panel
    JLabel etiqueta = new JLabel(icono_final);//creamos el objeto para que la imgen sea visible,donde va a estar 
    etiqueta.setBounds(x, y, ancho, alto);//colocamos las coordenadas, segun el diseño
    return etiqueta;
} 
  }