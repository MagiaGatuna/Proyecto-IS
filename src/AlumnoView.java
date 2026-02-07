package src;
import javax.swing.*;

import src.util.BotonUtil;

import java.awt.*;
import src.util.Conectar_ventanas;
public class AlumnoView extends JFrame {


    private final Color COLOR_FONDO = Color.WHITE;
    private final Color COLOR_HEADER = Color.decode("#D9D9D9");
    private final Color COLOR_BOTON_PRINCIPAL = Color.decode("#0086A3");
    private final Color COLOR_BOTON_CERRAR = Color.decode("#625FBA");
    private final Color COLOR_TEXTO_AZUL = Color.decode("#050082");


    private JButton btnVerMenu;
    private JButton btnVerMenuDiario;
    private JButton btnVerMenuSemanal;
    private JButton btnVerConsumos;
    private JButton btnCerrarSesion;
    private Usuario userLogueado; 

    public AlumnoView(Usuario u) {
        
        iniciarVentana();
        this.userLogueado = u;
        JPanel panelNorte = crearPanelSuperior();
        JPanel panelCentro = crearPanelCentral(u.getNombre());

        add(panelNorte, BorderLayout.NORTH);
        add(panelCentro, BorderLayout.CENTER);

       // setVisible(true);
    }

    private void iniciarVentana() {
        setTitle("AlumnoView");
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

        btnCerrarSesion = new JButton("CERRAR SESIÓN");
        estilizarBoton(btnCerrarSesion, COLOR_BOTON_CERRAR, new Dimension(170, 40));
  panel.add(Box.createHorizontalGlue());
        JPanel contenedorBotones = new JPanel(new FlowLayout(FlowLayout.RIGHT, 15, 20));
    contenedorBotones.setOpaque(false);
        JButton btnMonedero = new JButton("MONEDERO");
        estilizarBoton(btnMonedero, Color.BLUE, new Dimension(130, 40)); 
        btnMonedero.addActionListener(e -> Conectar_ventanas.getInstancia().desplegarMonedero(this, userLogueado));
    panel.add(btnMonedero);
        
        JPanel wrapperBoton = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        wrapperBoton.setOpaque(false);
        wrapperBoton.setBorder(BorderFactory.createEmptyBorder(20, 0, 0, 50));
        wrapperBoton.add(btnCerrarSesion);
        
        panel.add(wrapperBoton);

        return panel;
    }

    private JPanel crearPanelCentral(String username) {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        panel.setBackground(COLOR_FONDO);

        panel.add(Box.createVerticalStrut(5));

        JLabel lblBienvenida = new JLabel("<html><center>¡BIENVENIDO/A ESTUDIANTE<br>" + username+ "!</center></html>");
        lblBienvenida.setFont(new Font("Arial", Font.BOLD, 50));
        lblBienvenida.setForeground(COLOR_TEXTO_AZUL);
        lblBienvenida.setAlignmentX(Component.CENTER_ALIGNMENT);
        lblBienvenida.setBorder(BorderFactory.createEmptyBorder(0,300,0,0));
        
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

        btnVerMenu = new JButton("VER MENÚ");
        estilizarBoton(btnVerMenu, COLOR_BOTON_PRINCIPAL, new Dimension(200, 40));
        BotonUtil.darEstiloBoton(btnVerMenu, 200, 40);          
        bloqueMenu.add(btnVerMenu); 

        btnVerMenuDiario = new JButton("MENÚ DIARIO");
        btnVerMenuDiario.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnVerMenuDiario.setMaximumSize(new Dimension(120, 40));
        bloqueMenu.add(btnVerMenuDiario);

        btnVerMenuSemanal = new JButton("MENÚ SEMANAL");
        btnVerMenuSemanal.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnVerMenuSemanal.setMaximumSize(new Dimension(120, 40));
        bloqueMenu.add(btnVerMenuSemanal);


        JPanel bloqueConsumos = new JPanel();
        bloqueConsumos.setLayout(new BoxLayout(bloqueConsumos, BoxLayout.Y_AXIS));
        bloqueConsumos.setOpaque(false);
        bloqueConsumos.setAlignmentY(Component.TOP_ALIGNMENT);

        JLabel lblIconoConsumo = new JLabel(cargarIcono("res/IconoConsumo.png", 150, 150));
        lblIconoConsumo.setAlignmentX(Component.CENTER_ALIGNMENT);
        bloqueConsumos.add(lblIconoConsumo);
        bloqueConsumos.add(Box.createVerticalStrut(10));

        btnVerConsumos = new JButton("CONSUMOS");
        estilizarBoton(btnVerConsumos, COLOR_BOTON_PRINCIPAL, new Dimension(200, 40));
        BotonUtil.darEstiloBoton(btnVerConsumos, 200, 40);
        bloqueConsumos.add(btnVerConsumos);


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

    private void estilizarBoton(JButton boton, Color colorFondo, Dimension dimension) {
        boton.setBackground(colorFondo);
        boton.setForeground(Color.WHITE);
        boton.setAlignmentX(Component.CENTER_ALIGNMENT);
        boton.setMaximumSize(dimension);
        boton.setPreferredSize(dimension); 
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

    public static void main(String[] args) {
           Usuario pruebaEstudiante = new Usuario("Jeon Jung-kook", 50.0, "estudiante");
    new AlumnoView(pruebaEstudiante);
    }
}