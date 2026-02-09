package src.vista;
import javax.swing.*;
import java.awt.*;



public class MenuDView extends JFrame {

    String diaSemana, MM;
    int diaMes, AA;
    String Nombre;
    String Descripcion;
    int ValNutri;

    private JLabel lblNombreDes, lblDescDes, lblValDes;
    private JLabel lblNombreAlm, lblDescAlm, lblValAlm;
    private JLabel lblNombreCen, lblDescCen, lblValCen;

    private final Color COLOR_FONDO = Color.WHITE;
    private final Color COLOR_HEADER = Color.decode("#D9D9D9");
    private final Color COLOR_BOTON_PRINCIPAL = Color.decode("#0086A3");
    private final Color COLOR_BOTON_CERRAR = Color.decode("#5CB49B");
    private final Color COLOR_TEXTO_TITULO = Color.decode("#000000");
    private final Color COLOR_COMIDA_TITULO = Color.decode("#0B879D");

    private JButton btnVerTurnos;
    private JButton btnHome;

    public MenuDView(String diaSemana, int diaMes, String MM, int AA) {

        this.diaSemana = diaSemana;
        this.diaMes = diaMes;
        this.MM = MM;
        this.AA = AA;

        iniciarVentana();

        inicializarLabels();

        JPanel panelNorte = crearPanelSuperior();
        JPanel panelSur = crearPanelCentral();
        JPanel panelSurSur= crearPanelInferior();

        add(panelNorte, BorderLayout.NORTH);
        add(panelSur, BorderLayout.CENTER);
        add(panelSurSur, BorderLayout.SOUTH);

        setVisible(true);

    }

    private void iniciarVentana() {

        setTitle("MenuDView");
        try {
            setIconImage(new ImageIcon("res/LogoUCV.png").getImage());
        } catch (Exception e) { System.out.println("Logo no encontrado"); }

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setSize(screenSize.width, screenSize.height);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setBackground(COLOR_HEADER);
        setLayout(new BorderLayout(10, 10));

    }

    private void inicializarLabels() {
        lblNombreDes = new JLabel("No disponible");
        lblDescDes = new JLabel("...");
        lblValDes = new JLabel("");
        
        lblNombreAlm = new JLabel("No disponible");
        lblDescAlm = new JLabel("...");
        lblValAlm = new JLabel("");
        
        lblNombreCen = new JLabel("No disponible");
        lblDescCen = new JLabel("...");
        lblValCen = new JLabel("");
    }

    public void setMenuDesayuno(String nombre, String desc, String valor) {
        lblNombreDes.setText("Comida: " + nombre);
        lblDescDes.setText("<html><body style='width: 200px'>Desc: " + desc + "</body></html>");
        lblValDes.setText("Valor Nutricional: " + valor);
    }

    public void setMenuAlmuerzo(String nombre, String desc, String valor) {
        lblNombreAlm.setText("Comida: " + nombre);
        lblDescAlm.setText("<html><body style='width: 200px'>Desc: " + desc + "</body></html>");
        lblValAlm.setText("Valor Nutricional: " + valor);
    }

    public void setMenuCena(String nombre, String desc, String valor) {
        lblNombreCen.setText("Comida: " + nombre);
        lblDescCen.setText("<html><body style='width: 200px'>Desc: " + desc + "</body></html>");
        lblValCen.setText("Valor Nutricional: " + valor);
    }

    public String getDiaSemana() { return diaSemana; }

    private JPanel crearPanelSuperior() {

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 30, 10));
        panel.setBackground(COLOR_FONDO);

            btnHome= new JButton("HOME");
            estilizarBoton(btnHome, COLOR_BOTON_PRINCIPAL, new Dimension(150, 40));

            JLabel lblIconoUCV = new JLabel(cargarIcono("res/LogoUCV.png", 100, 100));

            JLabel txtTitulo = new JLabel("MENU DEL DÍA");
            estilizarMensaje(txtTitulo, COLOR_TEXTO_TITULO, 40);


            JPanel izquierdaNorte= new JPanel(new FlowLayout(FlowLayout.LEFT, 20, 10));
                izquierdaNorte.setOpaque(false);
                izquierdaNorte.setBorder(BorderFactory.createEmptyBorder(20, 00, 0, 0));
                izquierdaNorte.add(lblIconoUCV);
                izquierdaNorte.add(txtTitulo);
                        panel.add(izquierdaNorte);  


            JPanel derechaNorteSuperior= new JPanel(new FlowLayout(FlowLayout.RIGHT, 60, 10));
                derechaNorteSuperior.setOpaque(false);
                derechaNorteSuperior.setBorder(BorderFactory.createEmptyBorder(60, 0, 0, 0));
                derechaNorteSuperior.add(btnHome);
                    panel.add(derechaNorteSuperior);


            return panel;

    }

    private JPanel crearPanelCentral() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        panel.setBackground(COLOR_HEADER);

            JLabel mensajeMD = new JLabel("MENÚS DISPONIBLES");
            estilizarMensaje(mensajeMD, COLOR_TEXTO_TITULO, 45);
            mensajeMD.setAlignmentX(Component.CENTER_ALIGNMENT);
            mensajeMD.setHorizontalAlignment(SwingConstants.CENTER); 
                panel.add(mensajeMD);
            panel.add(Box.createRigidArea(new Dimension(0, 10)));

            JLabel mensajeFecha= new JLabel(diaSemana + " " +  diaMes + " de " + MM + " " + AA);
            estilizarMensaje(mensajeFecha, COLOR_TEXTO_TITULO, 45);
            mensajeFecha.setAlignmentX(Component.CENTER_ALIGNMENT);
            mensajeFecha.setHorizontalAlignment(SwingConstants.CENTER); 
                panel.add(mensajeFecha);
            panel.add(Box.createRigidArea(new Dimension(0, 15)));

            JPanel menusD= new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
            menusD.setOpaque(false);
            menusD.setBackground(COLOR_HEADER);

                menusD.add(crearTarjetaEstandar("Desayuno", lblNombreDes, lblDescDes, lblValDes));
                menusD.add(crearTarjetaEstandar("Almuerzo", lblNombreAlm, lblDescAlm, lblValAlm));
                menusD.add(crearTarjetaEstandar("Cena", lblNombreCen, lblDescCen, lblValCen));

            panel.add(menusD);

            return panel;

    }

    private JPanel crearPanelInferior() {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 50, 65));
        panel.setBackground(COLOR_HEADER);
            
            btnVerTurnos= new JButton("Ver turnos");
            estilizarBoton(btnVerTurnos, COLOR_BOTON_CERRAR, new Dimension(150, 40));
            panel.add(btnVerTurnos);

        return panel;

    }

    public JButton getBtnHome() {
        return btnHome;
    }

    public JButton getBtnVTurnos() {
        return btnVerTurnos;
    }

 // herramientas
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

    private void estilizarMensaje(JLabel mensaje, Color colorFuente, int size){

        mensaje.setForeground(colorFuente);

        mensaje.setFont(new Font("Arial", Font.BOLD, size));
    }

    private JPanel crearTarjetaEstandar(String titulo, JLabel lblNom, JLabel lblDesc, JLabel lblVal) {
        JPanel tarjeta = new JPanel();
        tarjeta.setLayout(new BoxLayout(tarjeta, BoxLayout.Y_AXIS)); 
        tarjeta.setBackground(Color.WHITE); 
        tarjeta.setPreferredSize(new Dimension(400, 300)); 
        tarjeta.setBorder(BorderFactory.createEmptyBorder(20, 5, 20, 20));

        JLabel lblTitulo = new JLabel(titulo);
        lblTitulo.setForeground(COLOR_COMIDA_TITULO); 
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 35));
        lblTitulo.setAlignmentX(Component.LEFT_ALIGNMENT);
        tarjeta.add(lblTitulo);
        
        estilizarMensaje(lblNom, COLOR_TEXTO_TITULO, 16);
        lblNom.setAlignmentX(Component.LEFT_ALIGNMENT);
        tarjeta.add(lblNom);
        tarjeta.add(Box.createRigidArea(new Dimension(0, 20)));
        
        estilizarMensaje(lblDesc, COLOR_TEXTO_TITULO, 16);
        lblDesc.setAlignmentX(Component.LEFT_ALIGNMENT);
        tarjeta.add(lblDesc);
        tarjeta.add(Box.createRigidArea(new Dimension(0, 20)));

        estilizarMensaje(lblVal, COLOR_TEXTO_TITULO, 16);
        lblVal.setAlignmentX(Component.LEFT_ALIGNMENT);
        tarjeta.add(lblVal);

        return tarjeta;
    }

}