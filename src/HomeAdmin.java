package src;
import javax.swing.*;
import src.util.BotonUtil;
import java.awt.*;
import src.util.Conectar_ventanas;
import src.util.Diseño_interfaz;
import src.modelo.Usuario;

public class HomeAdmin extends JFrame {
  JButton boton_cerrarsesion;
  JButton boton_Turnos;
  JButton boton_Menu;
  JButton boton_reporte;
  JButton boton_Inventario;
  JButton boton_Consumos;
  JButton boton_CostosFijos;
  JButton boton_CostosVariables;
  JLabel labela;
  private Usuario userActivo;
   public HomeAdmin(Usuario u){
    this.userActivo = u;
    setLayout(null);
     //centrammos la barra
    Dimension pantalla = Toolkit.getDefaultToolkit().getScreenSize();
    int anchoP = pantalla.width;
    int espacio = anchoP / 4;
    int yIconos = 280;
    int yBotones = 420;
    //Por diseño creamos la barra superior 
    JPanel barraSuperior = new JPanel();//creamos un nuevo objeto
    barraSuperior.setBackground(new Color(220, 220, 220)); // por diseño le colocamos color gris claro
    barraSuperior.setBounds(0, 0, anchoP, 120); //por diseño agregamos estas nuevas coordenadas
    barraSuperior.setLayout(null);
    add(barraSuperior);//agregamos a la venatana principal
//creamos el boton para regresar al inicio
    boton_cerrarsesion = Diseño_interfaz.Creador_Botones("CERRAR SESION", anchoP - 220, 35, 180, 45, Diseño_interfaz.colorazul);
    BotonUtil.darEstiloBoton(boton_cerrarsesion, 170, 40);
    //boton_cerrarsesion.addActionListener(e -> Conectar_ventanas.getInstancia().mostrarInicioSesion());
    barraSuperior.add(boton_cerrarsesion); // Se agrega a la barra
// Sección Menú
    add(Diseño_interfaz.Creador_iconos("res/Menu.png", (espacio * 0) + (espacio/2) - 75, yIconos, 150, 130));
    add(Diseño_interfaz.Creador_Botones("EDITAR MENÚ SEMANAL", (espacio * 0) + (espacio/2) - 90, yBotones, 180, 40, Diseño_interfaz.colorazul));
    
    add(Diseño_interfaz.Creador_Botones("EDITAR TURNOS", (espacio * 0) + (espacio/2) - 90, yBotones + 50, 180, 40, Diseño_interfaz.colorazul));
// Sección Reportes
    add(Diseño_interfaz.Creador_iconos("res/estadistica.png", (espacio * 1) + (espacio/2) - 60, yIconos + 15, 120, 120));
    add(Diseño_interfaz.Creador_Botones("REPORTE", (espacio * 1) + (espacio/2) - 90, yBotones, 180, 40, Diseño_interfaz.colorazul));

// Sección Inventario
    add(Diseño_interfaz.Creador_iconos("res/suministros.png", (espacio * 2) + (espacio/2) - 60, yIconos + 15, 120, 120));
    add(Diseño_interfaz.Creador_Botones("INVENTARIO", (espacio * 2) + (espacio/2) - 90, yBotones, 180, 40, Diseño_interfaz.colorazul));
    boton_CostosFijos = Diseño_interfaz.Creador_Botones("COSTOS FIJOS", (espacio * 2) + (espacio/2) - 90, yBotones + 50, 180, 40, Diseño_interfaz.colorazul);
    add(boton_CostosFijos);
    boton_CostosVariables = Diseño_interfaz.Creador_Botones("COSTOS VARIABLES", (espacio * 2) + (espacio/2) - 90, yBotones + 100, 180, 40, Diseño_interfaz.colorazul);
    add(boton_CostosVariables);

// Sección Consumos
    add(Diseño_interfaz.Creador_iconos("res/comida.png", (espacio * 3) + (espacio/2) - 60, yIconos + 15, 120, 120));
    add(Diseño_interfaz.Creador_Botones("CONSUMOS", (espacio * 3) + (espacio/2) - 90, yBotones, 180, 40, Diseño_interfaz.colorazul));
//creamos el saludo de bienvenida
labela=new JLabel("¡Bienvenido administrador  "+ userActivo.getNombre()+ "!");//creamos el objeto
labela.setFont(new Font("Arial", Font.BOLD, 30)); //Por diseño ajustamos la funte de la letra
labela.setHorizontalAlignment(SwingConstants.CENTER);// nos aseguramos de alinearla
labela.setBounds(0, 120,anchoP, 100);//Por diseño, colocam0so estas coordenadas
add(labela);//lo agreagamos a la ventana principal
//creamos el logo
barraSuperior.add(Diseño_interfaz.Creador_iconos("res/logo_ucv.png",20, 1, 120, 120)); // Se agrega a la barra, no al JFrame
//creamos las imagenes por diseño
//seccion de el monedero 
JButton btnMonedero = Diseño_interfaz.Creador_Botones("MONEDERO", anchoP - 360, 35, 120, 45, Color.BLUE);
barraSuperior.add(btnMonedero); 

btnMonedero.addActionListener(e -> {
    Conectar_ventanas.getInstancia().desplegarMonedero(this, userActivo);
});


  }

  public JButton getHome2(){
    return boton_cerrarsesion;
  }
  public void cierra(){
    this.setVisible(false);
    this.dispose();
  }

  public JButton getBtnGestorCF(){
    return boton_CostosFijos;
  }

  public static void main(String[] args) {
   
    Usuario pruebaAdmin = new Usuario("Suga", 150.0, "administrador");
    
    HomeAdmin frame = new HomeAdmin(pruebaAdmin);
    frame.setExtendedState(JFrame.MAXIMIZED_BOTH); 
    frame.setVisible(true);
    
}
}
