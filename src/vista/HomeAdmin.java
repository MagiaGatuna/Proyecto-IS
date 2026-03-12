package src.vista;
import javax.swing.*;
import src.util.BotonUtil;
import java.awt.*;
import src.util.Diseño_interfaz;
import src.modelo.Usuario;

public class HomeAdmin extends JFrame {
  JButton boton_cerrarsesion;
  JButton boton_Menu;
  JButton boton_ListadoComensales;
  JButton boton_Costos;
  JButton boton_editar_estado;
  JLabel labela;
  private Usuario userActivo;
  public HomeAdmin(Usuario u){
    this.userActivo = u;
    setLayout(null);
     //centrammos la barra
    Dimension pantalla = Toolkit.getDefaultToolkit().getScreenSize();
    int anchoP = pantalla.width;
    int espacio = anchoP / 3;
    int yIconos = 280;
    int yBotones = 420;
    //Por diseño creamos la barra superior 
    JPanel barraSuperior = new JPanel();//creamos un nuevo objeto
    barraSuperior.setBackground(new Color(220, 220, 220)); // por diseño le colocamos color gris claro
    barraSuperior.setBounds(0, 0, anchoP, 120); //por diseño agregamos estas nuevas coordenadas
    barraSuperior.setLayout(null);
    add(barraSuperior);//agregamos a la venatana principal
//creamos el boton para regresar al inicio
setIconImage(new ImageIcon("res/logoSistemaComedor.png").getImage());
    boton_cerrarsesion = Diseño_interfaz.Creador_Botones("CERRAR SESIÓN", anchoP - 220, 35, 180, 45, Diseño_interfaz.colorazul);
    boton_editar_estado = Diseño_interfaz.Creador_Botones("EDITAR USUARIO", anchoP - 450, 35, 180, 45, Diseño_interfaz.colorazul);
    BotonUtil.darEstiloBoton(boton_cerrarsesion, 170, 40);
    BotonUtil.darEstiloBoton(boton_editar_estado, 170, 40);

    //boton_cerrarsesion.addActionListener(e -> Conectar_ventanas.getInstancia().mostrarInicioSesion());
    barraSuperior.add(boton_cerrarsesion); // Se agrega a la barra
    barraSuperior.add(boton_editar_estado);
// Sección Menú
    add(Diseño_interfaz.Creador_iconos("res/Menu.png", (espacio * 0) + (espacio/2) - 75, yIconos, 150, 130));
    boton_Menu= Diseño_interfaz.Creador_Botones("EDITAR MENÚ SEMANAL", (espacio * 0) + (espacio/2) - 90, yBotones, 180, 40, Diseño_interfaz.colorazul);
    add(boton_Menu);
    // Sección ListadoComensaless
    add(Diseño_interfaz.Creador_iconos("res/comida.png", (espacio * 1) + (espacio/2) - 60, yIconos + 15, 120, 120));
    boton_ListadoComensales=(Diseño_interfaz.Creador_Botones("COMENSALES", (espacio * 1) + (espacio/2) - 90, yBotones, 180, 40, Diseño_interfaz.colorazul));
    add(boton_ListadoComensales);

    // Sección Inventario
    add(Diseño_interfaz.Creador_iconos("res/estadistica.png", (espacio * 2) + (espacio/2) - 60, yIconos + 15, 120, 120));
    boton_Costos = Diseño_interfaz.Creador_Botones("COSTOS", (espacio * 2) + (espacio/2) - 90, yBotones, 180, 40, Diseño_interfaz.colorazul);
    add(boton_Costos);

//creamos el saludo de bienvenida
    if(userActivo.getSexo().equals("Femenino")){
      labela=new JLabel("¡Bienvenida administradora "+ userActivo.getNombre()+ "!");//creamos el objeto
    }else{
      labela=new JLabel("¡Bienvenido administrador "+ userActivo.getNombre()+ "!");//creamos el objeto
    }

labela.setFont(new Font("SANS_SERIF", Font.BOLD, 30)); //Por diseño ajustamos la funte de la letra
labela.setHorizontalAlignment(SwingConstants.CENTER);// nos aseguramos de alinearla
labela.setBounds(0, 120,anchoP, 100);//Por diseño, colocam0so estas coordenadas
add(labela);//lo agreagamos a la ventana principal
//creamos el logo
barraSuperior.add(Diseño_interfaz.Creador_iconos("res/logo_ucv.png",20, 1, 120, 120)); // Se agrega a la barra, no al JFrame
//creamos las imagenes por diseño

  }

  public JButton getHome2(){
    return boton_cerrarsesion;
  }
  public void cierra(){
    this.setVisible(false);
    this.dispose();
  }


  // sin funcion aun
  public JButton getBtnMenu(){
    return boton_Menu;
  }

  public JButton getBtnListadoComensales(){
    return boton_ListadoComensales;
  }

  public JButton getBtnCostos(){
    return boton_Costos;
  }

  public JButton getBtnUsuarios(){
    return boton_editar_estado;
  }

  public static void main(String[] args) {
   
    //Usuario pruebaAdmin = new Usuario("Suga", 150.0, "administrador");
    
    //HomeAdmin frame = new HomeAdmin(pruebaAdmin);
    // frame.setSize(1200, 800); 
    // frame.setLocationRelativeTo(null);
    // frame.setVisible(true);
    // frame.setVisible(true);
    
}
}