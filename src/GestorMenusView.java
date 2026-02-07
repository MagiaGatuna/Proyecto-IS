package src;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;

public class GestorMenusView extends JFrame {

    private final Color COLOR_FONDO_BLANCO = Color.WHITE;
    private final Color COLOR_FONDO_GRIS = Color.decode("#D9D9D9");
    private final Color COLOR_AZUL_REY = Color.decode("#0086A3");    
    private final Color COLOR_CERRAR = Color.decode("#5CB49B"); 
    private final Color COLOR_NEGRO = Color.BLACK;
    private final Color COLOR_GRIS_OSCURO = Color.decode("#333333"); 

    public JButton btnHome, btnCerrarSesion;
    public JButton btnAgregar, btnCambiar, btnEliminar;
    public JTextField txtNombre, txtPrecio;

    public GestorMenusView() {
        iniciarVentana();

        add(crearPanelSuperior(), BorderLayout.NORTH);
        add(crearPanelCentral(), BorderLayout.CENTER);

        setVisible(true);
    }

    private void iniciarVentana() {
        setTitle("GestionMView");
        try {
            setIconImage(new ImageIcon("res/LogoUCV.png").getImage());
        } catch (Exception e) { System.out.println("Logo no encontrado"); }

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setSize(screenSize.width, screenSize.height);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        getContentPane().setBackground(COLOR_FONDO_GRIS); 
    }

    private JPanel crearPanelSuperior() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
        panel.setBorder(BorderFactory.createEmptyBorder(25, 20, 10, 20)); 
        panel.setBackground(COLOR_FONDO_BLANCO);

            JLabel lblIconoUCV = new JLabel();
            try { lblIconoUCV.setIcon(cargarIcono("res/LogoUCV.png", 80, 80)); } catch (Exception e){}

            JLabel txtTitulo = new JLabel(" GESTIÓN MENU");
            estilizarMensaje(txtTitulo, COLOR_NEGRO, 36);

            JPanel izquierda = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 0));
            izquierda.setOpaque(false);
            izquierda.add(lblIconoUCV);
            izquierda.add(txtTitulo);

            JPanel derecha = new JPanel(new FlowLayout(FlowLayout.RIGHT, 20, 10));
            derecha.setOpaque(false);

                btnHome = new JButton("HOME");
                estilizarBoton(btnHome, COLOR_AZUL_REY, new Dimension(140, 30));

                btnCerrarSesion = new JButton("Cerrar Sesión");
                estilizarBoton(btnCerrarSesion, COLOR_CERRAR, new Dimension(140, 30));

            derecha.add(btnHome);
            derecha.add(btnCerrarSesion);


        panel.add(izquierda);
        panel.add(Box.createHorizontalGlue()); 
        panel.add(derecha);

        return panel;
    }


    private JPanel crearPanelCentral() {
        JPanel panelCentral = new JPanel(new GridLayout(1, 2, 40, 0)); 
        panelCentral.setBackground(COLOR_FONDO_GRIS);
        panelCentral.setBorder(new EmptyBorder(20, 40, 40, 40)); 

        panelCentral.add(crearCajaIzquierdaFormulario());
        panelCentral.add(crearCajaDerechaTabla());

        return panelCentral;
    }

    private JPanel crearCajaIzquierdaFormulario() {
        JPanel panel = new JPanel(new BorderLayout(15, 15));
        panel.setBackground(COLOR_FONDO_BLANCO);
        panel.setBorder(BorderFactory.createCompoundBorder(
                new LineBorder(COLOR_NEGRO, 3), 
                new EmptyBorder(20, 20, 20, 20) 
        ));

        JPanel panelCuerpo = new JPanel(new BorderLayout(20, 0));
        panelCuerpo.setBackground(COLOR_FONDO_BLANCO);

            JPanel panelImagen = new JPanel();
            panelImagen.setPreferredSize(new Dimension(200, 0));
            panelImagen.setBackground(Color.LIGHT_GRAY);
            panelImagen.setBorder(new LineBorder(Color.GRAY, 1));
            panelImagen.add(new JLabel("FOTO")); 
            panelCuerpo.add(panelImagen, BorderLayout.WEST);


            JPanel panelDatos = new JPanel();
            panelDatos.setLayout(new BoxLayout(panelDatos, BoxLayout.Y_AXIS)); 
            panelDatos.setBackground(COLOR_FONDO_BLANCO);


            panelDatos.add(crearLabelCampo("Nombre:"));
            txtNombre = new JTextField();
            estilizarInput(txtNombre);
            panelDatos.add(txtNombre);
            panelDatos.add(Box.createVerticalStrut(15)); 

            panelDatos.add(crearLabelCampo("Precio:"));
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

            JLabel lblTotal = new JLabel("Total: ");
            estilizarMensaje(lblTotal, COLOR_NEGRO, 20);
        panel.add(lblTotal, BorderLayout.SOUTH);

        return panel;
    }

    private JPanel crearCajaDerechaTabla() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(COLOR_FONDO_BLANCO);
        panel.setBorder(BorderFactory.createCompoundBorder(
                new LineBorder(COLOR_NEGRO, 3),
                new EmptyBorder(10, 10, 10, 10)
        ));

        // Tabla de ejemplo para entender ve'
        String[] columnas = {"Plato", "Precio", "Estado"};
        Object[][] datos = {
                {"Pabellón", "150.00", "Disponible"},
                {"Jugo Natural", "40.00", "Agotado"}
        };

            JTable tabla = new JTable(datos, columnas);
            tabla.setRowHeight(25);
            tabla.getTableHeader().setBackground(COLOR_AZUL_REY);
            tabla.getTableHeader().setForeground(Color.WHITE);
            tabla.getTableHeader().setFont(new Font("Arial", Font.BOLD, 14));
            
            JScrollPane scroll = new JScrollPane(tabla);
            scroll.getViewport().setBackground(Color.WHITE); 
            
            panel.add(scroll, BorderLayout.CENTER);

            return panel;
        }

    // Herramientas

    private void estilizarBoton(JButton boton, Color colorFondo, Dimension dimension) {
        boton.setBackground(colorFondo);
        boton.setForeground(Color.WHITE);
        boton.setFont(new Font("Arial", Font.BOLD, 14));
        boton.setFocusPainted(false);
        boton.setBorderPainted(false);
        boton.setMaximumSize(dimension);
        boton.setPreferredSize(dimension);
        boton.setAlignmentX(Component.LEFT_ALIGNMENT); 
    }
    
    private void estilizarInput(JTextField txt) {
        txt.setMaximumSize(new Dimension(Integer.MAX_VALUE, 30)); 
        txt.setAlignmentX(Component.LEFT_ALIGNMENT);
        txt.setBorder(BorderFactory.createLineBorder(Color.GRAY));
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

    private ImageIcon cargarIcono(String ruta, int ancho, int alto) {
        ImageIcon icono = new ImageIcon(ruta);
        if (icono.getImageLoadStatus() == MediaTracker.ERRORED) {
            return null; 
        }
        Image imgEscalada = icono.getImage().getScaledInstance(ancho, alto, Image.SCALE_SMOOTH);
        return new ImageIcon(imgEscalada);
    }

    public static void main(String[] args) {

        new GestorMenusView();
    }
}