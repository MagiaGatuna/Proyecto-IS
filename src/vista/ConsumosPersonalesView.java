package src.vista;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import src.util.Diseño_interfaz;
import src.modelo.Usuario;
import java.awt.*;

public class ConsumosPersonalesView extends JFrame {

    private final Color COLOR_FONDO_BLANCO = Color.WHITE;
    private final Color COLOR_TURQUEZA = Diseño_interfaz.turquesa;
    private final Color COLOR_AZUL = Diseño_interfaz.colorazul;
    private final Color COLOR_AZUL_OSCURO = Diseño_interfaz.colorazulOscuro;
    private final Color COLOR_NEGRO = Color.BLACK;

    public JButton btnVolver;
    public JButton btnDesayuno;
    public JButton btnAlmuerzo;
    public JButton btnTodos;
    public JTable tabla;
    public JLabel lblTotal;
    public JLabel lblTotalDesayuno;
    public JLabel lblTotalAlmuerzo;

    private Usuario usuarioLogueado;

    public ConsumosPersonalesView(Usuario u) {
        this.usuarioLogueado = u;
        iniciarVentana();
        add(crearPanelSuperior(), BorderLayout.NORTH);
        add(crearPanelCentral(), BorderLayout.CENTER);
    }

    private void iniciarVentana() {
        setTitle("Mis Consumos");
        try {
            setIconImage(new ImageIcon("res/logoSistemaComedor.png").getImage());
        } catch (Exception e) {
            System.out.println("Logo no encontrado");
        }

        Dimension pantalla = Toolkit.getDefaultToolkit().getScreenSize();
        setSize(pantalla.width, pantalla.height);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        getContentPane().setBackground(COLOR_TURQUEZA);
    }

    private JPanel crearPanelSuperior() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
        panel.setBorder(BorderFactory.createEmptyBorder(25, 20, 10, 20));
        panel.setBackground(COLOR_FONDO_BLANCO);

        JLabel lblIconoUCV = new JLabel();
        try {
            lblIconoUCV.setIcon(cargarIcono("res/LogoUCV.png", 80, 80));
        } catch (Exception e) {
            System.out.println("Logo UCV no encontrado");
        }

        JLabel txtTitulo = new JLabel(" MIS CONSUMOS");
        txtTitulo.setForeground(COLOR_NEGRO);
        txtTitulo.setFont(new Font("SANS_SERIF", Font.BOLD, 36));

        JPanel izquierda = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 0));
        izquierda.setOpaque(false);
        izquierda.add(lblIconoUCV);
        izquierda.add(txtTitulo);

        JPanel derecha = new JPanel(new FlowLayout(FlowLayout.RIGHT, 20, 10));
        derecha.setOpaque(false);
        btnVolver = Diseño_interfaz.Creador_Botones("VOLVER", EXIT_ON_CLOSE, ERROR, 140, 40, COLOR_AZUL_OSCURO);
        derecha.add(btnVolver);

        panel.add(izquierda);
        panel.add(Box.createHorizontalGlue());
        panel.add(derecha);

        return panel;
    }

    private JPanel crearPanelCentral() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground(COLOR_TURQUEZA);
        panel.setBorder(new EmptyBorder(20, 40, 40, 40));

        panel.add(crearPanelResumen());
        panel.add(Box.createVerticalStrut(20));
        panel.add(crearPanelBotonesFiltro());
        panel.add(Box.createVerticalStrut(20));
        panel.add(crearPanelTabla());

        return panel;
    }

    private JPanel crearPanelResumen() {
        JPanel panel = new JPanel(new GridLayout(1, 3, 15, 0));
        panel.setBackground(COLOR_TURQUEZA);
        panel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 100));

        lblTotalDesayuno  = new JLabel("Desayunos: 0",         SwingConstants.CENTER);
        lblTotalAlmuerzo  = new JLabel("Almuerzos: 0",         SwingConstants.CENTER);
        lblTotal          = new JLabel("Total: 0",             SwingConstants.CENTER);

        for (JLabel lbl : new JLabel[]{lblTotalDesayuno, lblTotalAlmuerzo, lblTotal}) {
            lbl.setFont(new Font("SANS_SERIF", Font.BOLD, 22));
            lbl.setForeground(COLOR_NEGRO);
            lbl.setOpaque(true);
            lbl.setBackground(COLOR_FONDO_BLANCO);
            lbl.setBorder(new EmptyBorder(15, 10, 15, 10));
            panel.add(lbl);
        }

        lblTotal.setBackground(COLOR_AZUL);
        lblTotal.setForeground(COLOR_FONDO_BLANCO);

        return panel;
    }

    private JPanel crearPanelBotonesFiltro() {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 0));
        panel.setBackground(COLOR_TURQUEZA);
        panel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 50));

        btnTodos    = Diseño_interfaz.Creador_Botones("TODOS",    EXIT_ON_CLOSE, ERROR, 140, 40, COLOR_AZUL);
        btnDesayuno = Diseño_interfaz.Creador_Botones("DESAYUNO", EXIT_ON_CLOSE, ERROR, 160, 40, COLOR_AZUL);
        btnAlmuerzo = Diseño_interfaz.Creador_Botones("ALMUERZO", EXIT_ON_CLOSE, ERROR, 160, 40, COLOR_AZUL);

        panel.add(btnTodos);
        panel.add(btnDesayuno);
        panel.add(btnAlmuerzo);

        return panel;
    }

    private JPanel crearPanelTabla() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(COLOR_FONDO_BLANCO);
        panel.setBorder(new EmptyBorder(10, 10, 10, 10));

        // Columnas iniciales con Turno (modo "Todos")
        String[] columnas = {"Fecha", "Turno"};
        DefaultTableModel model = new DefaultTableModel(new Object[][]{}, columnas) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        tabla = new JTable(model);
        tabla.setRowHeight(35);
        tabla.setFont(new Font("SANS_SERIF", Font.PLAIN, 14));
        tabla.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tabla.getTableHeader().setReorderingAllowed(false);
        tabla.getTableHeader().setBackground(COLOR_AZUL);
        tabla.getTableHeader().setForeground(Color.WHITE);
        tabla.getTableHeader().setFont(new Font("SANS_SERIF", Font.BOLD, 14));

        JScrollPane scroll = new JScrollPane(tabla);
        scroll.getViewport().setBackground(Color.WHITE);

        panel.add(scroll, BorderLayout.CENTER);

        return panel;
    }

    private ImageIcon cargarIcono(String ruta, int ancho, int alto) {
        ImageIcon icono = new ImageIcon(ruta);
        if (icono.getImageLoadStatus() == java.awt.MediaTracker.ERRORED) return null;
        Image imgEscalada = icono.getImage().getScaledInstance(ancho, alto, Image.SCALE_SMOOTH);
        return new ImageIcon(imgEscalada);
    }

    // Cambia las columnas de la tabla según el filtro
    public void setColumnas(String filtro) {
        DefaultTableModel model = (DefaultTableModel) tabla.getModel();
        model.setRowCount(0);
        model.setColumnCount(0);
        if (filtro.equals("todos")) {
            model.addColumn("Fecha");
            model.addColumn("Turno");
        } else {
            model.addColumn("Fecha");
        }
    }

    public DefaultTableModel getTableModel() {
        return (DefaultTableModel) tabla.getModel();
    }

    public JButton getBtnVolver()   { return btnVolver; }
    public JButton getBtnDesayuno() { return btnDesayuno; }
    public JButton getBtnAlmuerzo() { return btnAlmuerzo; }
    public JButton getBtnTodos()    { return btnTodos; }
    public JLabel getLblTotal()         { return lblTotal; }
    public JLabel getLblTotalDesayuno() { return lblTotalDesayuno; }
    public JLabel getLblTotalAlmuerzo() { return lblTotalAlmuerzo; }
}