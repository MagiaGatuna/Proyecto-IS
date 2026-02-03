package src;
import javax.swing.*;
import java.awt.*;
import src.util.BotonUtil;
import src.util.ValidarUtil;
import src.util.PasswordYPlaceholderUtil;

import static src.util.ValidarUtil.*;



public class Registro extends JFrame {

    private JPasswordField txtPassword;
    private JPasswordField txtConfirmPassword;
    private JTextField txtNombres;
    private JTextField txtApellidos;
    private JTextField txtCedula;
    private JTextField txtCorreo;
    private JComboBox<String> comboCedula;
    private JComboBox<String> comboSexo;
    private JComboBox<String> comboRol;
    private JButton btnAceptar;
    private JButton btnHome;
    private JLabel lblLogin;

    public Registro() {
    
        setLayout(new BorderLayout());
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle("Registro");
        getContentPane().setBackground(Color.WHITE);


        try{
            ImageIcon icon = new ImageIcon("res/logoSistemaComedor.png");
            if(icon.getImageLoadStatus() == MediaTracker.COMPLETE){
                setIconImage(icon.getImage());
            }else{
                System.out.println("No se pudo cargar la imagen del icono.");
            }
        }catch(Exception e){
            System.out.println("No se pudo encontrar la imagen del icono: " + e.getMessage());
        }

        JPanel panelNorte = new JPanel(new FlowLayout(FlowLayout.RIGHT, 20, 10));
        panelNorte.setOpaque(false);
        btnHome = new JButton("HOME");
        btnHome.setBackground(new Color(0x0E0989));
        btnHome.setForeground(Color.WHITE);
        btnHome.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 14));
        btnHome.setFocusPainted(false);
        btnHome.setBorderPainted(false);
        BotonUtil.darEstiloBoton(btnHome,77,30);
        panelNorte.add(btnHome);
        add(panelNorte, BorderLayout.NORTH);


        JPanel panelCentro = new JPanel(new GridBagLayout());
        panelCentro.setOpaque(false);
        Color turquesa = new Color(0x83C0AF);
        PanelRedondeado tarjeta = new PanelRedondeado(50, turquesa);
        tarjeta.setPreferredSize(new Dimension(540, 480));
        tarjeta.setLayout(null);
        panelCentro.add(tarjeta);
        add(panelCentro, BorderLayout.CENTER);


        try{
            ImageIcon Logo = new ImageIcon("res/LogoUCV.png");
            if(Logo.getImageLoadStatus() == MediaTracker.COMPLETE) {
                Image LogoEscalado = Logo.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
                ImageIcon logoFinal = new ImageIcon(LogoEscalado);
                JLabel etiquetaLogo = new JLabel(logoFinal);
                etiquetaLogo.setBounds(200, 10, 140, 140);
                tarjeta.add(etiquetaLogo);
            }else{
                System.out.println("No se pudo cargar el logo UCV.");
            }
        }catch (Exception e){
            System.out.println("Error al cargar el logo UCV: " + e.getMessage());
        }

        JLabel titulo = new JLabel("REGISTRO DEL COMEDOR");
        titulo.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 24));
        titulo.setForeground(Color.BLACK);
        titulo.setBounds(0, 130, 540, 40);
        titulo.setHorizontalAlignment(SwingConstants.CENTER);
        tarjeta.add(titulo);


        txtNombres = agregarCampo(tarjeta, "Nombres", 20, 180);

        txtCedula = new JTextField("Cédula de identidad");
        txtCedula.setBounds(70, 230, 180, 40);
        txtCedula.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 14));
        txtCedula.setForeground(Color.GRAY);
        tarjeta.add(txtCedula);
        txtCedula.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(0xCCCCCC), 1),
            BorderFactory.createEmptyBorder(0, 5, 0, 0)
        ));

        PasswordYPlaceholderUtil.configurarPlaceholder(txtNombres, "Nombres");
        PasswordYPlaceholderUtil.configurarPlaceholder(txtCedula, "Cédula de identidad");

        String[] opcionesCedula = {"V-", "E-", "P-"};
        comboCedula = new JComboBox<>(opcionesCedula);
        comboCedula.setBounds(20, 235, 45, 30);
        comboCedula.setBackground(Color.WHITE);
        tarjeta.add(comboCedula);
        comboCedula.setEditable(false);

        
        txtApellidos = agregarCampo(tarjeta, "Apellidos", 290, 180);
        PasswordYPlaceholderUtil.configurarPlaceholder(txtApellidos, "Apellidos");
        txtCorreo = agregarCampo(tarjeta, "Correo Electrónico", 290, 230);
        PasswordYPlaceholderUtil.configurarPlaceholder(txtCorreo, "Correo Electrónico");
        
        JLabel etiquetaSexo = new JLabel("Sexo:");
        etiquetaSexo.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 20));
        etiquetaSexo.setBounds(20, 290, 540, 20);
        tarjeta.add(etiquetaSexo);
        
        String[] opcionesSexo = {"", "Femenino", "Masculino", "Prefiero no decirlo"};
        comboSexo = new JComboBox<>(opcionesSexo);
        comboSexo.setBounds(80, 285, 120, 30);
        comboSexo.setBackground(Color.WHITE);
        tarjeta.add(comboSexo);
        comboSexo.setEditable(false);

        JLabel etiquetaRol = new JLabel("Rol:");
        etiquetaRol.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 20));
        etiquetaRol.setBounds(290, 290, 540, 20);
        tarjeta.add(etiquetaRol);

        String[] opcionesRol = {"", "Estudiante", "Docente", "Trabajador", "Administrador"};
        comboRol = new JComboBox<>(opcionesRol);
        comboRol.setBounds(335, 285, 120, 30);
        comboRol.setBackground(Color.WHITE);
        tarjeta.add(comboRol);
        comboRol.setEditable(false);
    

        btnAceptar = new JButton("ACEPTAR REGISTRO");
        btnAceptar.setBounds(130, 400, 300, 40);
        btnAceptar.setBackground(new Color(0x4F4C96));
        btnAceptar.setForeground(Color.WHITE);
        btnAceptar.setFocusPainted(false);
        btnAceptar.setBorderPainted(false);
        btnAceptar.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 14));
        BotonUtil.darEstiloBoton(btnAceptar,300,40);
        tarjeta.add(btnAceptar);

        btnAceptar.addActionListener(e -> {
            if (validarRegistro()){
                JOptionPane.showMessageDialog(this,
                    "¡Registro completado exitosamente!",
                    "Éxito",
                    JOptionPane.INFORMATION_MESSAGE);
                
            }
        });


        txtPassword = new JPasswordField();
        txtPassword.setBounds(20, 345, 190, 40);
        PasswordYPlaceholderUtil.configurarPasswordConPlaceholder(txtPassword, "Contraseña");
        tarjeta.add(txtPassword);
        txtPassword.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(0xCCCCCC), 1),
            BorderFactory.createEmptyBorder(0, 5, 0, 0)
        ));

        
        JToggleButton ojo1 = PasswordYPlaceholderUtil.crearBotonMostrarOcultar(
            txtPassword,
            220, 355,
            20, 20
        );
        tarjeta.add(ojo1);
        

        txtConfirmPassword = new JPasswordField();
        txtConfirmPassword.setBounds(290, 345, 190, 40);
        tarjeta.add(txtConfirmPassword);
        PasswordYPlaceholderUtil.configurarPasswordConPlaceholder(txtConfirmPassword, "Confirmar Contraseña");
        txtConfirmPassword.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(0xCCCCCC), 1),
            BorderFactory.createEmptyBorder(0, 5, 0, 0)
        ));

        JToggleButton ojo2 = PasswordYPlaceholderUtil.crearBotonMostrarOcultar(
            txtConfirmPassword,
            490, 355,
            20, 20
        );
        tarjeta.add(ojo2);

        lblLogin = new JLabel("¿Ya tienes una cuenta? Inicia sesión aquí");
        lblLogin.setBounds(0, 450, 540, 20);
        lblLogin.setHorizontalAlignment(SwingConstants.CENTER);
        lblLogin.setForeground(new Color(0x4F4C96));
        lblLogin.setCursor(new Cursor(Cursor.HAND_CURSOR));

        lblLogin.addMouseListener(new java.awt.event.MouseAdapter(){
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt){
                System.out.println("Abriendo ventana de Login...");
            }

            @Override
            public void mouseEntered(java.awt.event.MouseEvent evt){
                lblLogin.setText("<html><u>¿Ya tienes cuenta? Inicia sesión aquí</u></html>");
            }

            @Override
            public void mouseExited(java.awt.event.MouseEvent evt){
                lblLogin.setText("¿Ya tienes cuenta? Inicia sesión aquí");
            }
        });

        tarjeta.add(lblLogin);

    }

    
    private JTextField agregarCampo(JPanel panel, String texto, int x, int y){
        JTextField campo = new JTextField(texto);
        campo.setBounds(x, y, 230, 40);
        campo.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 14));
        campo.setForeground(Color.GRAY);
        panel.add(campo);
        campo.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(0xCCCCCC), 1),
            BorderFactory.createEmptyBorder(0, 5, 0, 0)
        ));
        
        return campo;
    }

    public boolean validarRegistro(){
        StringBuilder errores = new StringBuilder();
        
        if(ValidarUtil.campoEstaVacio(txtNombres, "Nombres")){
            errores.append("- El campo Nombres es obligatorio\n");
        }
        
        if(ValidarUtil.campoEstaVacio(txtApellidos, "Apellidos")){
            errores.append("- El campo Apellidos es obligatorio\n");
        }
        
        if(ValidarUtil.campoEstaVacio(txtCedula, "Cédula de identidad")){
            errores.append("- El campo Cédula es obligatorio\n");
        } else if (!ValidarUtil.cedulaEsValida(txtCedula)) {
            errores.append("- La cédula debe contener solo números\n");
        }
        
        if(ValidarUtil.campoEstaVacio(txtCorreo, "Correo Electrónico")){
            errores.append("- El campo Correo Electrónico es obligatorio\n");
        } else if (!ValidarUtil.esEmailValido(txtCorreo.getText().trim())) {
            errores.append("- El formato del correo electrónico no es válido (ejemplo: usuario@dominio.com)\n");
        }
        
        if(comboSexo.getSelectedIndex() == 0){
            errores.append("- Debe seleccionar un Sexo\n");
        }
        
        if(comboRol.getSelectedIndex() == 0){
            errores.append("- Debe seleccionar un Rol\n");
        }
        
        String password = new String(txtPassword.getPassword());
        if(campoEstaVacio(txtPassword, "Contraseña")){
            errores.append("- El campo Contraseña es obligatorio\n");
        }
        
        String confirmPassword = new String(txtConfirmPassword.getPassword());
        if(campoEstaVacio(txtConfirmPassword, "Confirmar Contraseña")){
            errores.append("- Debe confirmar la contraseña\n");
        }
            
        if(!password.equals(confirmPassword)){
            errores.append("- Las contraseñas no coinciden\n");
        }
        
    

        if(errores.length() > 0){
            JOptionPane.showMessageDialog(this, 
                "Por favor corrija los siguientes errores:\n\n" + errores.toString(),
                "Errores en el formulario",
                JOptionPane.ERROR_MESSAGE);
            return false;
        }
        
        return true;
    }

    class PanelRedondeado extends JPanel{
        private int radio;
        private Color colorFondo;

        public PanelRedondeado(int radio, Color color){
            this.radio = radio;
            this.colorFondo = color;
            setOpaque(false);
        }

        @Override
        protected void paintComponent(Graphics g){
            Graphics2D g2 = (Graphics2D) g;
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setColor(colorFondo);
            g2.fillRoundRect(0, 0, getWidth(), getHeight(), radio, radio);
            super.paintComponent(g);
        }
    }
    
    public JButton getAceptar(){
        return btnAceptar;
    }
    
    public JButton getHome(){
        return btnHome;
    }
    
    public JLabel getinicio_label(){
        return lblLogin;
    }

    public static void main(String args[]){
    
        Registro ventanaRegistro = new Registro();
        ventanaRegistro.setBounds(0, 0, 640, 640);
        ventanaRegistro.setResizable(false);
        
        ventanaRegistro.setLocationRelativeTo(null);
        ventanaRegistro.setVisible(true);
    }
}
