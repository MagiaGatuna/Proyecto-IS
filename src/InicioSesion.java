package src;

import javax.swing.*;

import src.util.BotonUtil;
import src.util.PasswordYPlaceholderUtil;

import java.awt.*;
//import java.awt.event.ActionListener;

//la clase que contiena la factorizacion
class Diseño_interfaz {

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

//como se nos explico en clase, usamos el patron Singleton para cambiar de ventanas 
class Conectar_ventanas {
    private static Conectar_ventanas instancia;//guarda la copia de la clase
    private JFrame ventanaActual;//un oabjeto vetnana 

    private Conectar_ventanas() {}//hacemos un constructor para que nadie cree copias accidentales

    public static Conectar_ventanas getInstancia() {
    if (instancia == null) instancia = new Conectar_ventanas();//si la instancia esta vacia, creeaala
    return instancia;//en cualquier caso devuelve la que ya existe
    }
    //para los camabios de las ventanas 
    public void mostrarInicioSesion() {
    cambiarVentana(new InicioSesion());//si la llmamos es porque queremos ver esta ventana
    }

    private void cambiarVentana(JFrame nueva) {//aqui nos aseguramos de que al abrir la ventana la vieja se cierre 
    if (ventanaActual != null) ventanaActual.dispose();
    ventanaActual = nueva;
    ventanaActual.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    ventanaActual.setExtendedState(JFrame.MAXIMIZED_BOTH);
    ventanaActual.setLocationRelativeTo(null);
    ventanaActual.setVisible(true);
    }
}


//inicio de sesion
public class InicioSesion extends JFrame { 
    //declacion de elementos a usar
    JPanel Panel1;//contenedor en la ventana principal
    private JLabel label_Saludo;//texto de bienvenida
    private JLabel label_Sinoregistro;//texto de pregunta si nos hemos registrado
    private JTextField cedula_id;//campo donde el usuario coloca la cedula
    private JPasswordField contraseña;//campo donde el usuario coloca el password
    protected JButton boton_Registro;
    protected JButton boton_Home;
    protected JButton boton_InicioSesion;
//constructor para el diseño de la ventana
    public InicioSesion() { 

        try{
            ImageIcon icon = new ImageIcon("res/logoSistemaComedor.png");
            if(icon.getImageLoadStatus() == MediaTracker.COMPLETE){
                setIconImage(icon.getImage());
            }else{
                System.out.println("No se pudo cargar la imagen del icono.");
            }
        }catch(Exception e){
            System.out.println("No se pudo encontrar la imagen del icono: " + e.getMessage());
        }

    setLayout(null); 
    getContentPane().setBackground(Color.WHITE);
    //antes para que el panel quede centrado:
    //aqui obtenemos el tamano actual de la  pantalla 
    Dimension pantalla = Toolkit.getDefaultToolkit().getScreenSize();
    int anchoP = pantalla.width;
    int altoP = pantalla.height;
    int anchoPanel = 500;
    int altoPanel = 600;
    // Calculamos la posición para que quede en el centro
    int xCentro = (anchoP - anchoPanel) / 2;
    int yCentro = (altoP - altoPanel) / 2;
    //Por diseño, debemos crear un panel que tenga bordes redondeados
    Panel1 = new JPanel() {
    protected void paintComponent(Graphics g) {
    super.paintComponent(g);//limpiamos el fondo
    Graphics2D g2 = (Graphics2D) g;//usa otra forma de pintar el borde
    g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);//suavisa los bordes
    g2.setColor(getBackground());//colocale el color definido
    g2.fillRoundRect(0, 0, getWidth(), getHeight(), 50, 50); //coloca el rectangulo con bordes redondeados
    }
};

    Panel1.setLayout(null);
    Panel1.setOpaque(false);
    Panel1.setBackground(new Color(158, 200, 185));//definimos el color de el panel
    Panel1.setBounds(xCentro,yCentro, 500, 540);//colocamos su coordenadas
    add(Panel1);//agregamos el panel a la ventana principal

//creamos el saludo de el inico de sesion
    label_Saludo=new JLabel("ACCESO AL COMEDOR");//creamos el objeto con las letras 
    label_Saludo.setFont(new Font("Arial", Font.BOLD, 26)); //Por diseño, se agrega una nueva configuaracion a la letra
    label_Saludo.setHorizontalAlignment(SwingConstants.CENTER);//por diseño, alineamos las letras 
    label_Saludo.setBounds(0, 150, 500, 40);// Colocamos las coordenadas de el nuevo objeto
    Panel1.add(label_Saludo);//por diseño lo añadimos a nuestro contendor 

//creamos la preyunta a el usuario y verificar si no se ha registrado 
    label_Sinoregistro=new JLabel("¿Aun no se ha registrado?");//creamos el objeto para la pregunta
    label_Sinoregistro.setBounds(130, 420, 200, 20);//colocamos las coordenas de dicho objeto 
    Panel1.add(label_Sinoregistro);//por diseño lo agregamos a nuestro contenedor

//creamos el campo donde el usuario ingresara su cedula
    
    cedula_id= new JTextField("Cédula de identidad");//creamos el objeto
    cedula_id.setBounds(125, 210, 250, 40);//colocamos las coordenadas de el objeto
    Panel1.add(cedula_id);//Por diseño lo agregamos a el contenedor
    PasswordYPlaceholderUtil.configurarPlaceholder(cedula_id, "Cédula de identidad"); 
    cedula_id.requestFocusInWindow();

//creamos el campo donde el usuario ingresa su contraseña
    contraseña= new JPasswordField();//creamos el objeto
    contraseña.setBounds(125, 270, 250, 40);//colocamos sus coodenadas 
    PasswordYPlaceholderUtil.configurarPasswordConPlaceholder(contraseña, "Contraseña");
    Panel1.add(contraseña);//Por diseño se agrega a el contenedor
    
    JToggleButton btnMostrarOcultar = PasswordYPlaceholderUtil.crearBotonMostrarOcultar(contraseña, 380, 275, 30, 30);
    Panel1.add(btnMostrarOcultar);

//creamos el icono que poor diseño es ta en la interfaz
    Panel1.add(Diseño_interfaz.Creador_iconos("res/logo_ucv.png",190, 20, 120, 120));
    
//creamos el boton que redirecciona a el inicio de sesion
    boton_InicioSesion=Diseño_interfaz.Creador_Botones("INICIAR SESION",150, 340, 200, 45,Diseño_interfaz.colorazul);
    BotonUtil.darEstiloBoton(boton_InicioSesion, 200, 45);
    boton_InicioSesion=Diseño_interfaz.Creador_Botones("INICIAR SESION",150, 340, 200, 45,Diseño_interfaz.colorazul);
    Panel1.add(boton_InicioSesion);

//creamos el boton que nos permite redireccionar a el registro.
    boton_Registro=Diseño_interfaz.Creador_Botones("Registro",280, 415, 100, 30,Diseño_interfaz.colorazul);
    BotonUtil.darEstiloBoton(boton_Registro, 100, 30);
    Panel1.add(boton_Registro);

    add(Panel1);//luego de agregar todos los elementos agragamos nuestro panel, el que se creo por diseño
    //boton_Registro.addActionListener(e -> Conectar_ventanas.getInstancia().mostrarRegistro());

//creamos el boton que nos redirecciona el inicio
    boton_Home=Diseño_interfaz.Creador_Botones("Home",anchoP-120, 30, 80, 30,Diseño_interfaz.colorazul);
    BotonUtil.darEstiloBoton(boton_Home, 80, 30);
    add(boton_Home);//lo añadimos a la ventana principal

    this.getContentPane().setFocusable(true);
    this.getContentPane().requestFocusInWindow();
 }

 public JButton getHome(){
    return boton_Home;
 }
 public JButton getRegistro(){
    return boton_Registro;
 }
 public JButton getAdmin(){
    return boton_InicioSesion;
 }

 public JPasswordField getContraseña(){
    return contraseña;
 }

 

public JTextField getCedula_id() { return cedula_id; }
  public static void main(String args[]) { 
        Conectar_ventanas.getInstancia().mostrarInicioSesion();
    }

}
