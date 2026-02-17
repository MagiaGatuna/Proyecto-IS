package src.vista;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import src.util.Diseño_interfaz;

import java.awt.*;

public class GestorCFView extends JFrame {

    private final Color COLOR_FONDO_BLANCO = Color.WHITE;
    private final Color COLOR_FONDO_GRIS = Color.decode("#D9D9D9");
    private final Color COLOR_AZUL_REY = Color.decode("#0086A3");    
    private final Color COLOR_NEGRO = Color.BLACK;
    private final Color COLOR_GRIS_OSCURO = Color.decode("#333333"); 

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
        setVisible(true);
    }

    private void iniciarVentana(){
        setTitle("Gestión CF");
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setSize(screenSize.width, screenSize.height);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        getContentPane().setBackground(COLOR_FONDO_GRIS); 
        
    }

    private JPanel crearPanelSuperior(){
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
        panel.setBorder(BorderFactory.createEmptyBorder(25, 20, 10, 20)); 
        panel.setBackground(COLOR_FONDO_BLANCO);

        JLabel txtTitulo = new JLabel(" GESTIÓN CF");
        estilizarMensaje(txtTitulo, COLOR_NEGRO, 36);

        JPanel izquierda = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 0));
        izquierda.setOpaque(false);
        izquierda.add(txtTitulo);

        JPanel derecha = new JPanel(new FlowLayout(FlowLayout.RIGHT, 20, 10));
        derecha.setOpaque(false);
        btnHome = Diseño_interfaz.Creador_Botones("VOLVER", EXIT_ON_CLOSE, ERROR,140, 40,COLOR_AZUL_REY);
        derecha.add(btnHome);
        panel.add(izquierda);
        panel.add(Box.createHorizontalGlue()); 
        panel.add(derecha);
        return panel;
    }

    private JPanel crearPanelCentral(){
        JPanel contenedorPrincipal = new JPanel(new GridBagLayout());
        contenedorPrincipal.setBackground(COLOR_FONDO_GRIS);
        contenedorPrincipal.add(crearFormularioPrincipal());
        return contenedorPrincipal;
    }

    private JPanel crearFormularioPrincipal(){
        JPanel panel = new JPanel(new BorderLayout(15, 15));
        panel.setBackground(COLOR_FONDO_BLANCO);
        panel.setPreferredSize(new Dimension(700, 500)); 
        panel.setBorder(BorderFactory.createCompoundBorder(
                new LineBorder(COLOR_NEGRO, 3), 
                new EmptyBorder(30, 30, 30, 30) 
        ));

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

        btnAgregar = Diseño_interfaz.Creador_Botones("Agregar", EXIT_ON_CLOSE, ERROR,220, 35,COLOR_GRIS_OSCURO);
        btnCambiar =Diseño_interfaz.Creador_Botones("Cambiar", EXIT_ON_CLOSE, ERROR,220, 35,COLOR_GRIS_OSCURO); 
        btnEliminar =Diseño_interfaz.Creador_Botones("Eliminar", EXIT_ON_CLOSE, ERROR,220, 35,COLOR_GRIS_OSCURO);
        panelDatos.add(btnAgregar);
        panelDatos.add(Box.createVerticalStrut(10));
        panelDatos.add(btnCambiar);
        panelDatos.add(Box.createVerticalStrut(10));
        panelDatos.add(btnEliminar);
        
        panelCuerpo.add(panelDatos, BorderLayout.CENTER);
        panel.add(panelCuerpo, BorderLayout.CENTER);

        lblTotal = new JLabel("Total: ");
        estilizarMensaje(lblTotal, COLOR_NEGRO, 20);
        panel.add(lblTotal, BorderLayout.SOUTH);
        return panel;
    }

   
    
    private void estilizarInput(JTextField txt) {
        txt.setMaximumSize(new Dimension(Integer.MAX_VALUE, 30)); 
        txt.setAlignmentX(Component.LEFT_ALIGNMENT);
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

}