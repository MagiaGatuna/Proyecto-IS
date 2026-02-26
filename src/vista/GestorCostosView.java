package src.vista;

import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.DefaultTableCellRenderer;
import src.util.Diseño_interfaz;
import java.awt.*;

public class GestorCostosView extends JFrame {

    private final Color COLOR_FONDO_BLANCO = Color.WHITE;
    private final Color COLOR_FONDO_GRIS = Color.decode("#0a0909");
    private final Color COLOR_TURQUEZA = Diseño_interfaz.turquesa;   
    private final Color COLOR_TURQUEZA_OSCURO = Diseño_interfaz.turquesaOscuro;   
    private final Color COLOR_AZUL = Diseño_interfaz.colorazul;  
    private final Color COLOR_AZUL_OSCURO = Diseño_interfaz.colorazulOscuro;
    private final Color COLOR_NEGRO = Color.BLACK;

    public JButton btnHome, btnActualizarMerma, btnModificarCV, btnModificarCF;
    public JTextField txtMermaPorcentaje;
    public JLabel lblCostoFijoValor;
    public JTable tabla; 

    public GestorCostosView() {
        iniciarVentana();
        add(crearPanelSuperior(), BorderLayout.NORTH);
        add(crearPanelCentral(), BorderLayout.CENTER);
    }

    private void iniciarVentana() {
        try {
            setIconImage(new ImageIcon("res/logoSistemaComedor.png").getImage());
        } catch (Exception e) { System.out.println("Logo no encontrado"); }
        
        setTitle("Panel de gestion de costos");
        Dimension pantalla = Toolkit.getDefaultToolkit().getScreenSize();
        setSize(pantalla.width, pantalla.height); 
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        getContentPane().setBackground(COLOR_FONDO_GRIS); 
    }

    private JPanel crearPanelSuperior() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(COLOR_FONDO_BLANCO);
        panel.setBorder(new EmptyBorder(20, 40, 10, 40));

        JLabel lblIconoUCV = new JLabel();
        try{ 
            lblIconoUCV.setIcon(cargarIcono("res/LogoUCV.png", 80, 80)); 
        } catch (Exception e){
            System.out.println("Error: No se pudo cargar la imagen en 'res/LogoUCV.png'");
        }
        
        JPanel izquierda = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 0));
        izquierda.setOpaque(false);
        izquierda.add(lblIconoUCV);

        JLabel txtTitulo = new JLabel("GESTIÓN DE COSTOS");
        estilizarMensaje(txtTitulo, COLOR_NEGRO, 32);

        btnHome = Diseño_interfaz.Creador_Botones("VOLVER", EXIT_ON_CLOSE, ERROR, 140, 40, COLOR_AZUL_OSCURO);

        JPanel derecha = new JPanel(new FlowLayout(FlowLayout.RIGHT, 20, 10));
        derecha.setOpaque(false);
        derecha.add(btnHome);

        panel.add(txtTitulo, BorderLayout.CENTER);
        panel.add(derecha, BorderLayout.EAST);
        panel.add(izquierda, BorderLayout.WEST);

        return panel;
    }

    private JPanel crearPanelCentral() {
        JPanel panelCentral = new JPanel(new BorderLayout(0, 15));
        panelCentral.setBackground(COLOR_TURQUEZA);
        panelCentral.setBorder(new EmptyBorder(10, 40, 40, 40));

        JPanel barraControl = new JPanel(new GridLayout(1, 3, 20, 0)); // 1 fila, 3 columnas
        barraControl.setBackground(COLOR_FONDO_BLANCO);
        barraControl.setBorder(new EmptyBorder(15, 20, 15, 20));


        JPanel pnlMerma = new JPanel(new FlowLayout(FlowLayout.LEFT));
        pnlMerma.setOpaque(false);
        JLabel lblMerma = new JLabel("Modificar Merma (%): ");
        lblMerma.setFont(new Font("SANS_SERIF", Font.BOLD, 14));
        txtMermaPorcentaje = new JTextField(6);
        btnActualizarMerma = Diseño_interfaz.Creador_Botones("MODIFICAR", 0, 0, 100, 30, Color.DARK_GRAY);
        pnlMerma.add(lblMerma);
        pnlMerma.add(txtMermaPorcentaje);
        pnlMerma.add(btnActualizarMerma);

        // 2. Bloque de Costo Fijo (Centro)
        JPanel pnlCF = new JPanel(new FlowLayout(FlowLayout.CENTER));
        pnlCF.setOpaque(false);
        JLabel lblCF = new JLabel("TOTAL DE COSTOS FIJOS (CF): ");
        lblCF.setFont(new Font("SANS_SERIF", Font.BOLD, 14));
        lblCostoFijoValor = new JLabel("Bs 0.00");
        lblCostoFijoValor.setForeground(COLOR_AZUL_OSCURO);
        lblCostoFijoValor.setFont(new Font("SANS_SERIF", Font.BOLD, 18));
        pnlCF.add(lblCF);
        pnlCF.add(lblCostoFijoValor);

        // 3. Bloque de Botones de Modificación (Derecha)
        JPanel pnlBotones = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 0));
        pnlBotones.setOpaque(false);
        btnModificarCV = Diseño_interfaz.Creador_Botones("MODIFICAR CV", 0, 0, 140, 35, COLOR_TURQUEZA_OSCURO);
        btnModificarCV.setForeground(COLOR_NEGRO);
        btnModificarCF = Diseño_interfaz.Creador_Botones("MODIFICAR CF", 0, 0, 140, 35, COLOR_TURQUEZA_OSCURO);
        btnModificarCF.setForeground(COLOR_NEGRO);
        btnModificarCV.setActionCommand("MOD_CV");
        btnModificarCF.setActionCommand("MOD_CF");
        btnHome.setActionCommand("GO_HOME");
        pnlBotones.add(btnModificarCV);
        pnlBotones.add(btnModificarCF);

        barraControl.add(pnlMerma);
        barraControl.add(pnlCF);
        barraControl.add(pnlBotones);

        panelCentral.add(barraControl, BorderLayout.NORTH);
        panelCentral.add(crearCajaTabla(), BorderLayout.CENTER);

        return panelCentral;
    }

    private JPanel crearCajaTabla() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(COLOR_FONDO_BLANCO);
        panel.setBorder(new EmptyBorder(5, 5, 5, 5));

        String[] columnas = {"Menu", "Costos Variables (CV)", "Numero de Bandejas", "Merma (%)", "CCB Total"};
        Object[][] datos = {
    
        };

        DefaultTableModel model = new DefaultTableModel(datos, columnas) {
            @Override public boolean isCellEditable(int row, int column) { return false; }
        };

        tabla = new JTable(model);
        tabla.setRowHeight(20);
        tabla.getTableHeader().setBackground(COLOR_AZUL);
        tabla.getTableHeader().setForeground(Color.WHITE);
        tabla.getTableHeader().setFont(new Font("SANS_SERIF", Font.BOLD, 16));

        tabla.clearSelection();
        tabla.getTableHeader().setReorderingAllowed(false);
        
        resaltarColumnaCCB();

        JScrollPane scroll = new JScrollPane(tabla);
        scroll.getViewport().setBackground(Color.WHITE);
        panel.add(scroll, BorderLayout.CENTER);

        return panel;
    }

    private void resaltarColumnaCCB() {
        DefaultTableCellRenderer rendererCCB = new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, 
                    boolean isSelected, boolean hasFocus, int row, int column) {
                Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                c.setFont(new Font("SANS_SERIF", Font.BOLD, 14));
                c.setForeground(COLOR_AZUL);
                setHorizontalAlignment(JLabel.CENTER);
                if (!isSelected) c.setBackground(new Color(235, 245, 255));
                return c;
            }
        };
        tabla.getColumnModel().getColumn(4).setCellRenderer(rendererCCB);
    }

    private void estilizarMensaje(JLabel mensaje, Color colorFuente, int size) {
        mensaje.setForeground(colorFuente);
        mensaje.setFont(new Font("SANS_SERIF", Font.BOLD, size));
    }

    private ImageIcon cargarIcono(String ruta, int ancho, int alto) {
        ImageIcon icono = new ImageIcon(ruta);
        if (icono.getImageLoadStatus() == java.awt.MediaTracker.ERRORED) return null; 
        Image imgEscalada = icono.getImage().getScaledInstance(ancho, alto, Image.SCALE_SMOOTH);
        return new ImageIcon(imgEscalada);
    }

    public void setControlador(ActionListener l) {
            btnHome.addActionListener(l);
            btnModificarCV.addActionListener(l);
            btnModificarCF.addActionListener(l);
    }

}