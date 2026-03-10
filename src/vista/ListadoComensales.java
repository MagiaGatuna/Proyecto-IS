package src.vista;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import src.util.Diseño_interfaz;
import java.awt.*;

public class ListadoComensales extends JFrame{

    private final Color COLOR_FONDO_BLANCO = Color.WHITE;
    private final Color COLOR_TURQUEZA = Diseño_interfaz.turquesa;
    private final Color COLOR_AZUL = Diseño_interfaz.colorazul;
    private final Color COLOR_AZUL_OSCURO = Diseño_interfaz.colorazulOscuro;
    private final Color COLOR_NEGRO = Color.BLACK;

    public JButton btnHome;
    public JButton btnDesayuno, btnAlmuerzo;
    public JLabel lblRegular, lblBecario, lblExonerado, lblEmpleado, lblProfesor, lblTotal;
    public JTable tabla;
    public JList<String> listFechas;

    public ListadoComensales(){
        iniciarVentana();
        add(crearPanelSuperior(), BorderLayout.NORTH);
        add(crearPanelCentral(), BorderLayout.CENTER);
    }

    private void iniciarVentana(){
        setTitle("Listado de Comensales");
        try{
            setIconImage(new ImageIcon("res/logoSistemaComedor.png").getImage());
        }catch(Exception e){ 
            System.out.println("Logo no encontrado");
        }

        Dimension pantalla = Toolkit.getDefaultToolkit().getScreenSize();
        setSize(pantalla.width, pantalla.height);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        getContentPane().setBackground(COLOR_TURQUEZA);
    }

    private JPanel crearPanelSuperior(){
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
        panel.setBorder(BorderFactory.createEmptyBorder(25, 20, 10, 20));
        panel.setBackground(COLOR_FONDO_BLANCO);

        JLabel lblIconoUCV = new JLabel();
        try{
            lblIconoUCV.setIcon(cargarIcono("res/LogoUCV.png", 80, 80));
        }catch(Exception e){
            System.out.println("Logo UCV no encontrado");
        }

        JLabel txtTitulo = new JLabel(" LISTADO DE COMENSALES");
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
        JPanel panelCentral = new JPanel(new BorderLayout(15, 0));
        panelCentral.setBackground(COLOR_TURQUEZA);
        panelCentral.setBorder(new EmptyBorder(20, 40, 40, 40));

        panelCentral.add(crearPanelFechas(), BorderLayout.WEST);
        panelCentral.add(crearPanelContenido(), BorderLayout.CENTER);

        return panelCentral;
    }

    private JPanel crearPanelFechas(){
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(COLOR_FONDO_BLANCO);
        panel.setBorder(new EmptyBorder(10, 10, 10, 10));
        panel.setPreferredSize(new Dimension(160, 0));

        JLabel lblFechas = new JLabel("Fechas", SwingConstants.CENTER);
        lblFechas.setFont(new Font("SANS_SERIF", Font.BOLD, 16));
        lblFechas.setBorder(new EmptyBorder(0, 0, 10, 0));

        listFechas = new JList<>(new DefaultListModel<>());
        listFechas.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        listFechas.setFont(new Font("SANS_SERIF", Font.PLAIN, 14));
        listFechas.setFixedCellHeight(35);

        JScrollPane scroll = new JScrollPane(listFechas);
        scroll.getViewport().setBackground(Color.WHITE);

        panel.add(lblFechas, BorderLayout.NORTH);
        panel.add(scroll, BorderLayout.CENTER);

        return panel;
    }

    private JPanel crearPanelContenido(){
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground(COLOR_TURQUEZA);

        panel.add(crearPanelResumen());
        panel.add(Box.createVerticalStrut(20));
        panel.add(crearPanelBotonesFiltro());
        panel.add(Box.createVerticalStrut(20));
        panel.add(crearPanelTabla());

        return panel;
    }

    private JPanel crearPanelResumen(){
        JPanel panel = new JPanel(new GridLayout(1, 6, 15, 0));
        panel.setBackground(COLOR_TURQUEZA);
        panel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 100));

        lblRegular   = new JLabel("Regulares: 0", SwingConstants.CENTER);
        lblBecario   = new JLabel("Becarios: 0", SwingConstants.CENTER);
        lblExonerado = new JLabel("Exonerados: 0", SwingConstants.CENTER);
        lblEmpleado  = new JLabel("Empleados: 0", SwingConstants.CENTER);
        lblProfesor  = new JLabel("Profesores: 0", SwingConstants.CENTER);
        lblTotal     = new JLabel("Total: 0", SwingConstants.CENTER);

        for(JLabel lbl : new JLabel[]{lblRegular, lblBecario, lblExonerado, lblEmpleado, lblProfesor, lblTotal}){
            lbl.setFont(new Font("SANS_SERIF", Font.BOLD, 20));
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

    private JPanel crearPanelBotonesFiltro(){
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 0));
        panel.setBackground(COLOR_TURQUEZA);
        panel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 50));

        btnDesayuno = Diseño_interfaz.Creador_Botones("DESAYUNO", EXIT_ON_CLOSE, ERROR, 160, 40, COLOR_AZUL_OSCURO);
        btnAlmuerzo = Diseño_interfaz.Creador_Botones("ALMUERZO", EXIT_ON_CLOSE, ERROR, 160, 40, COLOR_AZUL);

        panel.add(btnDesayuno);
        panel.add(btnAlmuerzo);

        return panel;
    }

    private JPanel crearPanelTabla(){
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(COLOR_FONDO_BLANCO);
        panel.setBorder(new EmptyBorder(10, 10, 10, 10));

        String[] columnas = {"Cédula", "Rol", "Tipo"};
        DefaultTableModel model = new DefaultTableModel(new Object[][]{}, columnas){
            @Override
            public boolean isCellEditable(int row, int column){
                return false;
            }
        };

        tabla = new JTable(model);
        tabla.setRowHeight(30);
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

    private void estilizarMensaje(JLabel mensaje, Color colorFuente, int size){
        mensaje.setForeground(colorFuente);
        mensaje.setFont(new Font("SANS_SERIF", Font.BOLD, size));
    }

    private ImageIcon cargarIcono(String ruta, int ancho, int alto){
        ImageIcon icono = new ImageIcon(ruta);
        if(icono.getImageLoadStatus() == java.awt.MediaTracker.ERRORED) return null;
        Image imgEscalada = icono.getImage().getScaledInstance(ancho, alto, Image.SCALE_SMOOTH);
        return new ImageIcon(imgEscalada);
    }
}