package src;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class GestorCVView extends JFrame {

    private final Color COLOR_FONDO_BLANCO = Color.WHITE;
    private final Color COLOR_FONDO_GRIS = Color.decode("#D9D9D9");
    private final Color COLOR_AZUL_REY = Color.decode("#0086A3");    
    private final Color COLOR_NEGRO = Color.BLACK;
    private final Color COLOR_GRIS_OSCURO = Color.decode("#333333"); 

    public JButton btnHome;
    public JButton btnAgregar, btnCambiar, btnEliminar;
    public JTextField txtNombre, txtPrecio;
    public JTextArea txtAreaDetalles;
    public JLabel lblTotal;
    
    public JTable tabla; 

    public GestorCVView(){
        iniciarVentana();
        add(crearPanelSuperior(), BorderLayout.NORTH);
        add(crearPanelCentral(), BorderLayout.CENTER);
        setVisible(true);
    }

    private void iniciarVentana(){
        setTitle("Gestion CV");
        try {
            setIconImage(new ImageIcon("res/LogoUCV.png").getImage());
        } catch (Exception e) { System.out.println("Logo no encontrado"); }

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

        JLabel lblIconoUCV = new JLabel();
        try { lblIconoUCV.setIcon(cargarIcono("res/LogoUCV.png", 80, 80)); } catch (Exception e){}

        JLabel txtTitulo = new JLabel(" GESTIÓN CV");
        estilizarMensaje(txtTitulo, COLOR_NEGRO, 36);

        JPanel izquierda = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 0));
        izquierda.setOpaque(false);
        izquierda.add(lblIconoUCV);
        izquierda.add(txtTitulo);

        JPanel derecha = new JPanel(new FlowLayout(FlowLayout.RIGHT, 20, 10));
        derecha.setOpaque(false);

        btnHome = new JButton("HOME");
        estilizarBoton(btnHome, COLOR_AZUL_REY, new Dimension(140, 30));

        derecha.add(btnHome);

        panel.add(izquierda);
        panel.add(Box.createHorizontalGlue()); 
        panel.add(derecha);

        return panel;
    }

    private JPanel crearPanelCentral(){
        JPanel panelCentral = new JPanel(new GridBagLayout()); 
        panelCentral.setBackground(COLOR_FONDO_GRIS);
        panelCentral.setBorder(new EmptyBorder(20, 40, 40, 40)); 
        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(0, 10, 0, 10);
        gbc.weighty = 1.0;

        gbc.gridx = 0;
        gbc.weightx = 0.7; 
        panelCentral.add(crearCajaIzquierdaFormulario(), gbc);

        gbc.gridx = 1;
        gbc.weightx = 0.3;
        panelCentral.add(crearCajaDerechaTabla(), gbc);

        return panelCentral;
    }

    private JPanel crearCajaIzquierdaFormulario(){
        JPanel panel = new JPanel(new BorderLayout(15, 15));
        panel.setBackground(COLOR_FONDO_BLANCO);
        panel.setBorder(BorderFactory.createCompoundBorder(
                new LineBorder(COLOR_NEGRO, 3), 
                new EmptyBorder(20, 20, 20, 20) 
        ));

        JPanel panelCuerpo = new JPanel(new BorderLayout(20, 0));
        panelCuerpo.setBackground(COLOR_FONDO_BLANCO);

        txtAreaDetalles = new JTextArea("");
        txtAreaDetalles.setEditable(false);
        txtAreaDetalles.setFont(new Font("Monospaced", Font.PLAIN, 14)); 
        txtAreaDetalles.setBackground(new Color(245, 245, 245));
        txtAreaDetalles.setLineWrap(true);
        txtAreaDetalles.setWrapStyleWord(true);
        
        JScrollPane scrollArea = new JScrollPane(txtAreaDetalles);
        scrollArea.setPreferredSize(new Dimension(300, 0)); 
        scrollArea.setBorder(BorderFactory.createTitledBorder(new LineBorder(Color.GRAY), "Detalles del Menú"));
        
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

        btnAgregar = new JButton("Agregar");
        estilizarBoton(btnAgregar, COLOR_GRIS_OSCURO, new Dimension(200, 35));
        btnCambiar = new JButton("Cambiar");
        estilizarBoton(btnCambiar, COLOR_GRIS_OSCURO, new Dimension(200, 35));
        btnEliminar = new JButton("Eliminar");
        estilizarBoton(btnEliminar, COLOR_GRIS_OSCURO, new Dimension(200, 35));

        panelDatos.add(btnAgregar);
        panelDatos.add(Box.createVerticalStrut(10));
        panelDatos.add(btnCambiar);
        panelDatos.add(Box.createVerticalStrut(10));
        panelDatos.add(btnEliminar);
        
        panelCuerpo.add(panelDatos, BorderLayout.CENTER);
        panel.add(panelCuerpo, BorderLayout.CENTER);

        lblTotal = new JLabel("Total Seleccionado: 0.00 Bs");
        estilizarMensaje(lblTotal, COLOR_NEGRO, 20);
        panel.add(lblTotal, BorderLayout.SOUTH);

        return panel;
    }

    private JPanel crearCajaDerechaTabla(){
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(COLOR_FONDO_BLANCO);
        panel.setBorder(BorderFactory.createCompoundBorder(
                new LineBorder(COLOR_NEGRO, 3),
                new EmptyBorder(10, 10, 10, 10)
        ));

        String[] columnas = {"Menu", "CV Total"};
        Object[][] datos = {};

        DefaultTableModel model = new DefaultTableModel(datos, columnas) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; 
            }
        };

        tabla = new JTable(model);
        tabla.setRowHeight(30);
        tabla.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tabla.getTableHeader().setReorderingAllowed(false);
        
        tabla.getTableHeader().setBackground(COLOR_AZUL_REY);
        tabla.getTableHeader().setForeground(Color.WHITE);
        tabla.getTableHeader().setFont(new Font("Arial", Font.BOLD, 14));
        
        JScrollPane scroll = new JScrollPane(tabla);
        scroll.getViewport().setBackground(Color.WHITE); 
        
        panel.add(scroll, BorderLayout.CENTER);

        return panel;
    }

    private void estilizarBoton(JButton boton, Color colorFondo, Dimension dimension) {
        boton.setBackground(colorFondo);
        boton.setForeground(Color.WHITE);
        boton.setFont(new Font("Arial", Font.BOLD, 14));
        boton.setFocusPainted(false);
        boton.setBorderPainted(false);
        boton.setMaximumSize(dimension);
        boton.setPreferredSize(dimension);
        boton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        boton.setAlignmentX(Component.LEFT_ALIGNMENT); 
    }
    
    private void estilizarInput(JTextField txt){
        txt.setMaximumSize(new Dimension(Integer.MAX_VALUE, 30)); 
        txt.setAlignmentX(Component.LEFT_ALIGNMENT);
        txt.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        txt.setFont(new Font("Arial", Font.PLAIN, 14));
    }

    private JLabel crearLabelCampo(String texto){
        JLabel lbl = new JLabel(texto);
        lbl.setFont(new Font("Arial", Font.BOLD, 14));
        lbl.setAlignmentX(Component.LEFT_ALIGNMENT);
        return lbl;
    }

    private void estilizarMensaje(JLabel mensaje, Color colorFuente, int size){
        mensaje.setForeground(colorFuente);
        mensaje.setFont(new Font("Arial", Font.BOLD, size));
    }

    private ImageIcon cargarIcono(String ruta, int ancho, int alto) {
        ImageIcon icono = new ImageIcon(ruta);
        if (icono.getImageLoadStatus() == java.awt.MediaTracker.ERRORED) return null; 
        Image imgEscalada = icono.getImage().getScaledInstance(ancho, alto, Image.SCALE_SMOOTH);
        return new ImageIcon(imgEscalada);
    }
}