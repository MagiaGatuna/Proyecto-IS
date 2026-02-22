package src.vista;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import src.util.Diseño_interfaz;

import java.awt.*;

public class GestorCFView extends JFrame {

    private final Color COLOR_FONDO_BLANCO = Color.WHITE;
    private final Color COLOR_FONDO_GRIS = Color.decode("#0a0909");
    private final Color COLOR_TURQUEZA = Diseño_interfaz.turquesa;   
    private final Color COLOR_TURQUEZA_OSCURO = Diseño_interfaz.turquesaOscuro;   
    private final Color COLOR_AZUL = Diseño_interfaz.colorazul;  
    private final Color COLOR_AZUL_OSCURO = Diseño_interfaz.colorazulOscuro;
    private final Color COLOR_NEGRO = Color.BLACK;

    public JButton btnHome;
    public JButton btnAgregar, btnCambiar, btnEliminar;
    public JTextField txtNombre, txtPrecio;
    
    public JTextArea txtAreaInfo;
    public JLabel lblTotal;

    public GestorCFView(){
        iniciarVentana();
        add(crearPanelSuperior(), BorderLayout.NORTH);
        add(crearPanelCentral(), BorderLayout.CENTER);
        setIconImage(new ImageIcon("res/logoSistemaComedor.png").getImage());
    }

    private void iniciarVentana(){
        setTitle("Gestión CF");
        try {
            setIconImage(new ImageIcon("res/logoSistemaComedor.png").getImage());
        } catch (Exception e) { System.out.println("Logo no encontrado"); }

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setSize(screenSize.width, screenSize.height);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        getContentPane().setBackground(COLOR_TURQUEZA); 
    }

    private JPanel crearPanelSuperior(){
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
        panel.setBackground(COLOR_FONDO_BLANCO);

        JLabel lblIconoUCV = new JLabel();
        try{ 
            lblIconoUCV.setIcon(cargarIcono("res/LogoUCV.png", 80, 80)); 
        } catch (Exception e){
            System.out.println("Error: No se pudo cargar la imagen en 'res/LogoUCV.png'");
        }

        JLabel txtTitulo = new JLabel("GESTIÓN COSTOS FIJOS");
        estilizarMensaje(txtTitulo, COLOR_NEGRO, 36);

        JPanel izquierda = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 0));
        izquierda.setOpaque(false);
        izquierda.add(lblIconoUCV);
        izquierda.add(txtTitulo);

        JPanel derecha = new JPanel(new FlowLayout(FlowLayout.RIGHT, 20, 10));
        derecha.setOpaque(false);
        btnHome = Diseño_interfaz.Creador_Botones("VOLVER", EXIT_ON_CLOSE, ERROR, 140, 40, COLOR_AZUL_OSCURO);
        derecha.add(btnHome);
        panel.add(izquierda);
        panel.add(Box.createHorizontalGlue()); 
        panel.add(derecha);
        return panel;
    }

    private JPanel crearPanelCentral(){
        JPanel contenedorPrincipal = new JPanel(new GridBagLayout());
        contenedorPrincipal.setBackground(COLOR_TURQUEZA);
        contenedorPrincipal.add(crearFormularioPrincipal());
        return contenedorPrincipal;
    }

    private JPanel crearFormularioPrincipal(){
        JPanel panel = new JPanel(new BorderLayout(15, 15));
        panel.setBackground(COLOR_FONDO_BLANCO);
        panel.setPreferredSize(new Dimension(700, 500)); 
        panel.setBorder(new EmptyBorder(5, 5, 5, 5));

        JPanel panelCuerpo = new JPanel(new BorderLayout(40, 0));
        panelCuerpo.setBackground(COLOR_FONDO_BLANCO);

        txtAreaInfo = new JTextArea("");
        txtAreaInfo.setFont(new Font("Arial", Font.PLAIN, 14));
        txtAreaInfo.setLineWrap(true);
        txtAreaInfo.setWrapStyleWord(true);
        txtAreaInfo.setEditable(false);
        
        JScrollPane scrollArea = new JScrollPane(txtAreaInfo);
        scrollArea.setPreferredSize(new Dimension(250, 0));
        scrollArea.setBorder(BorderFactory.createTitledBorder(""));
        
        panelCuerpo.add(scrollArea, BorderLayout.WEST);

        // Botones con LEFT_ALIGNMENT para consistencia con BoxLayout
        btnAgregar = Diseño_interfaz.Creador_Botones("Agregar", EXIT_ON_CLOSE, ERROR, 220, 35, Color.DARK_GRAY);
        btnCambiar = Diseño_interfaz.Creador_Botones("Cambiar", EXIT_ON_CLOSE, ERROR, 220, 35, Color.DARK_GRAY); 
        btnEliminar = Diseño_interfaz.Creador_Botones("Eliminar", EXIT_ON_CLOSE, ERROR, 220, 35, Color.DARK_GRAY);
        btnAgregar.setAlignmentX(Component.LEFT_ALIGNMENT);
        btnCambiar.setAlignmentX(Component.LEFT_ALIGNMENT);
        btnEliminar.setAlignmentX(Component.LEFT_ALIGNMENT);

        JPanel panelDatos = new JPanel();
        panelDatos.setLayout(new BoxLayout(panelDatos, BoxLayout.Y_AXIS)); 
        panelDatos.setBackground(COLOR_FONDO_BLANCO);

        panelDatos.add(crearLabelCampo("Nombre del Item:"));
        txtNombre = new JTextField();
        estilizarInput(txtNombre);
        panelDatos.add(txtNombre);
        panelDatos.add(Box.createVerticalStrut(15)); 

        panelDatos.add(crearLabelCampo("Costo (Bs):"));
        txtPrecio = new JTextField();
        estilizarInput(txtPrecio);
        panelDatos.add(txtPrecio);
        panelDatos.add(Box.createVerticalStrut(30)); 

        panelDatos.add(btnAgregar);
        panelDatos.add(Box.createVerticalStrut(10));
        panelDatos.add(btnCambiar);
        panelDatos.add(Box.createVerticalStrut(10));
        panelDatos.add(btnEliminar);
        
        
        JPanel panelCentro = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 20));
        panelCentro.setBackground(COLOR_FONDO_BLANCO);
        panelCentro.add(panelDatos);

        panelCuerpo.add(panelCentro, BorderLayout.CENTER);
        panel.add(panelCuerpo, BorderLayout.CENTER);

        lblTotal = new JLabel("Total: ");
        estilizarMensaje(lblTotal, COLOR_NEGRO, 20);
        panel.add(lblTotal, BorderLayout.SOUTH);
        return panel;
    }


    private void estilizarInput(JTextField txt) {
        txt.setMaximumSize(new Dimension(220, 30));
        txt.setPreferredSize(new Dimension(220, 30));
        txt.setAlignmentX(Component.LEFT_ALIGNMENT);
        txt.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        txt.setFont(new Font("Arial", Font.PLAIN, 14));
    }

    
    private JLabel crearLabelCampo(String texto) {
        JLabel lbl = new JLabel(texto);
        lbl.setFont(new Font("Arial", Font.BOLD, 14));
        lbl.setAlignmentX(Component.LEFT_ALIGNMENT);
        return lbl;
    }

    private void estilizarMensaje(JLabel mensaje, Color colorFuente, int size){
        mensaje.setForeground(colorFuente);
        mensaje.setFont(new Font("Arial", Font.BOLD, size));
    }

    public static void main(String[] args){
        SwingUtilities.invokeLater(() -> new GestorCFView());
    }

    private ImageIcon cargarIcono(String ruta, int ancho, int alto) {
        ImageIcon icono = new ImageIcon(ruta);
        if (icono.getImageLoadStatus() == java.awt.MediaTracker.ERRORED) return null; 
        Image imgEscalada = icono.getImage().getScaledInstance(ancho, alto, Image.SCALE_SMOOTH);
        return new ImageIcon(imgEscalada);
    }
}