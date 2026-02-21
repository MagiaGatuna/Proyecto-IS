package src.vista;

import javax.swing.*;
import java.awt.*;

import src.util.Diseño_interfaz;
import src.util.PasswordYPlaceholderUtil;

public class RecargaView extends JFrame {
    private JTextField Referencia;
    private JTextField cedulaField;
    private JPanel PanelRecarga;
    JButton boton_verificar;
    JButton boton_volver;
    private Image imagenFondo;
    private JComboBox<String> combo_Cedula;
     private JComboBox<String> combo_Banco;
    public RecargaView() {

   try {
    // Cargamos la imagen original
    ImageIcon original = new ImageIcon("res/Comedor_interior.jpeg");
    
    //  Obtenemos el tamaño de la pantalla
    Dimension tamanoPantalla = Toolkit.getDefaultToolkit().getScreenSize();
    
    // creas una copia con el tamano de la jframe
    Image imagenEscalada = original.getImage().getScaledInstance(
        tamanoPantalla.width, 
        tamanoPantalla.height, 
        Image.SCALE_SMOOTH
    );
    
    // Se la pasamos al JLabel
    JLabel fondoLabel = new JLabel(new ImageIcon(imagenEscalada));
    fondoLabel.setBounds(0, 0, tamanoPantalla.width, tamanoPantalla.height);
    this.setContentPane(fondoLabel); 

} catch (Exception e) {
    this.getContentPane().setBackground(Color.DARK_GRAY);
}
    this.setLayout(null);
    this.setExtendedState(JFrame.MAXIMIZED_BOTH);
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    Dimension pantalla = Toolkit.getDefaultToolkit().getScreenSize();
    int anchoPanel = 500;
    int altoPanel = 540;
    int xCentro = (pantalla.width - anchoPanel) / 2;
    int yCentro = (pantalla.height - altoPanel) / 2;

    
    PanelRecarga = new JPanel() {
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);//para que dibuje el panel
            g2.setColor(new Color(0, 128, 128, 100));//r,g,b y alpha ese hace que sea transparente 
            g2.fillRoundRect(0, 0, getWidth(), getHeight(), 50, 50);//coordenadas de el panel 
            g2.dispose();
        }
    };
    PanelRecarga.setLayout(null);//yo decido donde ponerlo 
    PanelRecarga.setOpaque(false);//para que se vea atraves de e; panel 
    PanelRecarga.setBounds(xCentro, yCentro, anchoPanel, altoPanel);//coordenadas de el panel 

    //Título del Panel
    JLabel titulo = new JLabel("RECARGA DE SALDO", SwingConstants.CENTER);
    titulo.setFont(new Font("Arial", Font.BOLD, 24));
    titulo.setForeground(Color.WHITE);
    titulo.setBounds(0, 30, 500, 40);
    PanelRecarga.add(titulo);

    // Referencia
    Referencia = new JTextField();
    Referencia.setBounds(100, 120, 300, 40);
    PasswordYPlaceholderUtil.configurarPlaceholder(Referencia, "Número de referencia");
    PanelRecarga.add(Referencia);

    // Cédula
    cedulaField = new JTextField();
    cedulaField.setBounds(100, 190, 300, 40);
    PasswordYPlaceholderUtil.configurarPlaceholder(cedulaField, "Cédula del usuario");
    PanelRecarga.add(cedulaField);

    //  Verificar
    boton_verificar =Diseño_interfaz.Creador_Botones("VERIFICAR PAGO", 100, 370, 300, 50,Color.BLUE); 
    PanelRecarga.add(boton_verificar);

    //  Volver 
   /*  boton_volver = Diseño_interfaz.Creador_Botones("VOLVER", 100, 400, 300, 40,Color.blue);
    PanelRecarga.add(boton_volver); */

    //Tipo de cedula
    String[] opcionesCedula = {"V-", "E-", "P-"};
        combo_Cedula = new JComboBox<>(opcionesCedula);
        combo_Cedula.setBounds(55, 190, 45, 40);
        combo_Cedula.setBackground(Color.WHITE);
        PanelRecarga.add(combo_Cedula);
        combo_Cedula.setEditable(false);


    //tipo de banco
    String[] opcionesBanco = {"Banesco", "Venezuela", "Mercantil"};
        combo_Banco = new JComboBox<>(opcionesBanco);
        combo_Banco.setBounds(100, 260, 300, 40);
        combo_Banco.setBackground(Color.WHITE);
        PanelRecarga.add(combo_Banco);
       combo_Banco.setEditable(false);

    //falta Jpanel con icono de usuario 
    //Falata que tango el label con el saldo
   // Que sea transparente 

    
 this.add(PanelRecarga);
}
    
    @Override
    public void paint(Graphics g) {
        if (imagenFondo != null) {
            g.drawImage(imagenFondo, 0, 0, getWidth(), getHeight(), this);
        }
    
        super.paint(g);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new RecargaView().setVisible(true));
    }
}