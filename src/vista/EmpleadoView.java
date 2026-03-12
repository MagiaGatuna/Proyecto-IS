package src.vista;
import javax.swing.*;
import java.awt.*;
import src.util.Conectar_ventanas;
import src.util.Diseño_interfaz;
import src.modelo.Usuario;
public class EmpleadoView extends JFrame {


    private final Color COLOR_FONDO = Color.WHITE;
    private final Color COLOR_HEADER = Color.decode("#D9D9D9");
    private final Color COLOR_BOTON_PRINCIPAL = Diseño_interfaz.colorazul;
    private final Color COLOR_BOTON_SECUNDARIO = Diseño_interfaz.turquesaOscuro;
    private final Color COLOR_BOTON_CERRAR = Diseño_interfaz.colorazulOscuro;
    private final Color COLOR_TEXTO_AZUL = Color.decode("#050082");


    private JButton btnVerMenu;
    private JButton btnVerMenuDiario;
    private JButton btnVerMenuSemanal;
    private JButton btnVerConsumos;
    private JButton btnConsumos;
    private JButton btnCerrarSesion;
    private JButton btnPerfil;
    private Usuario userLogueado;
    private JPanel panelContenedorMonedero;

    public EmpleadoView(Usuario pr2) {
        this.userLogueado = pr2;
        iniciarVentana();
        
        JPanel panelNorte = crearPanelSuperior();
        JPanel panelCentro = crearPanelCentral(pr2.getNombre(), pr2.getSexo(), pr2.getRol());

        add(panelNorte, BorderLayout.NORTH);
        add(panelCentro, BorderLayout.CENTER);

    // setVisible(true);
    }

    private void iniciarVentana() {
        setTitle("EmpleadoView");
        try {
            ImageIcon icon = new ImageIcon("res/logoSistemaComedor.png");
            if (icon.getImageLoadStatus() == MediaTracker.COMPLETE) {
                setIconImage(icon.getImage());
            } else {
                System.out.println("No se pudo cargar la imagen del icono.");
            }
        } catch (Exception e) {
            System.out.println("No se pudo encontrar la imagen del icono: " + e.getMessage());
        }
        
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setSize(screenSize.width, screenSize.height);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setBackground(COLOR_FONDO);
        setLayout(new BorderLayout(10, 10));
    }

    private JPanel crearPanelSuperior() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 10, 20, 10));
        panel.setBackground(COLOR_HEADER);

        JLabel lblIconoUCV = new JLabel(cargarIcono("res/LogoUCV.png", 100, 100));
        panel.add(lblIconoUCV);

        panel.add(Box.createHorizontalGlue());

        btnPerfil = Diseño_interfaz.Creador_Botones("PERFIL", EXIT_ON_CLOSE, ERROR, 130, 40, COLOR_BOTON_SECUNDARIO);
        btnPerfil.setForeground(Color.BLACK);
        btnPerfil.addActionListener(e -> Conectar_ventanas.getInstancia().desplegarPerfil(this, userLogueado));
        panel.add(btnPerfil);

        panel.add(Box.createHorizontalStrut(10));

        JButton btnMonedero = Diseño_interfaz.Creador_Botones("MONEDERO", EXIT_ON_CLOSE, ERROR, 130, 40, COLOR_BOTON_SECUNDARIO);
        btnMonedero.setForeground(Color.BLACK);
        btnMonedero.addActionListener(e -> Conectar_ventanas.getInstancia().desplegarMonedero(this, userLogueado));
        panel.add(btnMonedero);

        panel.add(Box.createHorizontalStrut(10));

        btnCerrarSesion = Diseño_interfaz.Creador_Botones("CERRAR SESIÓN", EXIT_ON_CLOSE, ERROR, 170, 40, COLOR_BOTON_CERRAR);
        panel.add(btnCerrarSesion);

        return panel;
    }

    private JPanel crearPanelCentral(String username, String sexo, String rol) {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        panel.setBackground(COLOR_FONDO);

        panel.add(Box.createVerticalStrut(5));

        JLabel lblBienvenida = new JLabel();
        if(sexo.equalsIgnoreCase("Femenino")){
            if(rol.equalsIgnoreCase("Empleado")){
                lblBienvenida = new JLabel("<html><center>¡BIENVENIDA EMPLEADA<br>" + username + "!</center></html>");
            }else{
                lblBienvenida = new JLabel("<html><center>¡BIENVENIDA PROFESORA<br>" + username + "!</center></html>");
            }
        }else if(sexo.equalsIgnoreCase("Masculino")){
            lblBienvenida = new JLabel("<html><center>¡BIENVENIDO " + rol.toUpperCase() + "<br>" + username + "!</center></html>");
        }else{
            lblBienvenida = new JLabel("<html><center>¡BIENVENIDO/A " + rol.toUpperCase() + "<br>" + username + "!</center></html>");
        }

        lblBienvenida.setFont(new Font("SANS_SERIF", Font.BOLD, 50));
        lblBienvenida.setForeground(COLOR_TEXTO_AZUL);
        lblBienvenida.setAlignmentX(Component.CENTER_ALIGNMENT);
        lblBienvenida.setBorder(BorderFactory.createEmptyBorder(0, 300, 0, 0));

        panel.add(lblBienvenida);
        panel.add(Box.createVerticalStrut(100));

        JPanel panelIconos = new JPanel();
        panelIconos.setLayout(new BoxLayout(panelIconos, BoxLayout.X_AXIS));
        panelIconos.setOpaque(false);
        panelIconos.setAlignmentX(Component.CENTER_ALIGNMENT);

        JPanel bloqueMenu = new JPanel();
        bloqueMenu.setLayout(new BoxLayout(bloqueMenu, BoxLayout.Y_AXIS));
        bloqueMenu.setOpaque(false);
        bloqueMenu.setAlignmentY(Component.TOP_ALIGNMENT);

        JLabel lblIconoMenu = new JLabel(cargarIcono("res/IconoMenu.png", 150, 150));
        lblIconoMenu.setAlignmentX(Component.CENTER_ALIGNMENT);
        bloqueMenu.add(lblIconoMenu);
        bloqueMenu.add(Box.createVerticalStrut(10));

        btnVerMenu = Diseño_interfaz.Creador_Botones("VER MENÚ", EXIT_ON_CLOSE, ERROR, 200, 40, COLOR_BOTON_PRINCIPAL);
        for(java.awt.event.MouseListener ml : btnVerMenu.getMouseListeners()) {
            btnVerMenu.removeMouseListener(ml);
        }
        bloqueMenu.add(btnVerMenu);
        bloqueMenu.add(Box.createVerticalStrut(10));

        btnVerMenuDiario = Diseño_interfaz.Creador_Botones("VER MENÚ DIARIO", EXIT_ON_CLOSE, ERROR, 160, 40, COLOR_BOTON_SECUNDARIO);
        btnVerMenuDiario.setForeground(Color.BLACK);
        bloqueMenu.add(btnVerMenuDiario);
        bloqueMenu.add(Box.createVerticalStrut(10));

        btnVerMenuSemanal = Diseño_interfaz.Creador_Botones("VER MENÚ SEMANAL", EXIT_ON_CLOSE, ERROR, 160, 40, COLOR_BOTON_SECUNDARIO);
        btnVerMenuSemanal.setForeground(Color.BLACK);
        bloqueMenu.add(btnVerMenuSemanal);


        JPanel bloqueConsumos = new JPanel();
        bloqueConsumos.setLayout(new BoxLayout(bloqueConsumos, BoxLayout.Y_AXIS));
        bloqueConsumos.setOpaque(false);
        bloqueConsumos.setAlignmentY(Component.TOP_ALIGNMENT);

        JLabel lblIconoConsumo = new JLabel(cargarIcono("res/IconoConsumo.png", 150, 150));
        lblIconoConsumo.setAlignmentX(Component.CENTER_ALIGNMENT);
        bloqueConsumos.add(lblIconoConsumo);
        bloqueConsumos.add(Box.createVerticalStrut(10));

        btnVerConsumos = Diseño_interfaz.Creador_Botones("CONSUMOS", EXIT_ON_CLOSE, ERROR, 200, 40, COLOR_BOTON_PRINCIPAL);
        for(java.awt.event.MouseListener ml : btnVerConsumos.getMouseListeners()) {
            btnVerConsumos.removeMouseListener(ml);
        }
        btnVerConsumos.setForeground(Color.WHITE);
        bloqueConsumos.add(btnVerConsumos);
        bloqueConsumos.add(Box.createVerticalStrut(10));

        btnConsumos = Diseño_interfaz.Creador_Botones("VER CONSUMOS", EXIT_ON_CLOSE, ERROR, 160, 40, COLOR_BOTON_SECUNDARIO);
        btnConsumos.setForeground(Color.BLACK);
        bloqueConsumos.add(btnConsumos);


        panelIconos.add(Box.createHorizontalGlue());
        panelIconos.add(bloqueMenu);
        panelIconos.add(Box.createHorizontalStrut(300));
        panelIconos.add(bloqueConsumos);
        panelIconos.add(Box.createHorizontalGlue());

        panel.add(panelIconos);
        panel.add(Box.createVerticalGlue());

        return panel;
    }

    private ImageIcon cargarIcono(String ruta, int ancho, int alto) {
        ImageIcon icono = new ImageIcon(ruta);
        if (icono.getImageLoadStatus() == MediaTracker.ERRORED) {
            return new ImageIcon();
        }
        Image imgEscalada = icono.getImage().getScaledInstance(ancho, alto, Image.SCALE_SMOOTH);
        return new ImageIcon(imgEscalada);
    }

    public JButton getinicio(){
        return btnCerrarSesion;
    }
    public JButton getMenuS(){
        return btnVerMenuSemanal;
    }
    public JButton getMenuD(){
        return btnVerMenuDiario;
    }
    public JButton getBtnConsumos(){
        return btnConsumos;
    }
    public JButton getBtnPerfil(){
        return btnPerfil;
    }
    public JPanel getPanelMonedero() {
        return panelContenedorMonedero;
    }
    public JButton getmenu() {
        return btnVerMenu;
    }

    public static void main(String[] args) {
    }
}