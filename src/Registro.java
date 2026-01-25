package src;
import javax.swing.*;
import java.awt.*;



public class Registro extends JFrame {

    JPasswordField txtPassword;
    JPasswordField txtConfirmPassword;

    public Registro() {
    
        setLayout(new BorderLayout());
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle("Registro");
        getContentPane().setBackground(Color.WHITE);


        try {
            setIconImage(new ImageIcon(getClass().getResource("/res/icon.png")).getImage());
        } catch (Exception e) {
            System.out.println("No se pudo encontrar la imagen del icono.");
        }

        JPanel panelNorte = new JPanel(new FlowLayout(FlowLayout.RIGHT, 20, 10));
        panelNorte.setOpaque(false);
        JButton btnHome = new JButton("HOME");
        btnHome.setBackground(new Color(0x0E0989));
        btnHome.setForeground(Color.WHITE);
        btnHome.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 14));
        btnHome.setFocusPainted(false);
        btnHome.setBorderPainted(false);
        darEstiloBoton(btnHome,77,30);
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


        ImageIcon Logo = new ImageIcon(getClass().getResource("/res/LogoUCV.png"));
        Image LogoEscalado = Logo.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
        ImageIcon logoFinal = new ImageIcon(LogoEscalado);
        JLabel etiquetaLogo = new JLabel(logoFinal);
        etiquetaLogo.setBounds(200, 10, 140, 140);
        tarjeta.add(etiquetaLogo);

        JLabel titulo = new JLabel("REGISTRO DEL COMEDOR");
        titulo.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 24));
        titulo.setForeground(Color.BLACK);
        titulo.setBounds(0, 130, 540, 40);
        titulo.setHorizontalAlignment(SwingConstants.CENTER);
        tarjeta.add(titulo);


        agregarCampo(tarjeta, "Nombres", 20, 180);

        JTextField cedula = new JTextField("Cédula de identidad");
        cedula.setBounds(70, 230, 180, 40);
        cedula.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 14));
        cedula.setForeground(Color.GRAY);
        tarjeta.add(cedula);
        cedula.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(0xCCCCCC), 1),
            BorderFactory.createEmptyBorder(0, 5, 0, 0)
        ));

        cedula.addFocusListener(new java.awt.event.FocusAdapter() {
            @Override
            public void focusGained(java.awt.event.FocusEvent evt) {
                if (cedula.getText().equals("Cédula de identidad")) {
                    cedula.setText("");
                    cedula.setForeground(Color.BLACK);
                }
            }

            @Override
            public void focusLost(java.awt.event.FocusEvent evt) {
                if (cedula.getText().isEmpty()) {
                    cedula.setForeground(Color.GRAY);
                    cedula.setText("Cédula de identidad");
                }
            }}
        );

        String[] opcionesCedula = {"V-", "E-"};
        JComboBox<String> comboCedula = new JComboBox<>(opcionesCedula);
        comboCedula.setBounds(20, 235, 45, 30);
        comboCedula.setBackground(Color.WHITE);
        tarjeta.add(comboCedula);
        comboCedula.setEditable(false);

        
        agregarCampo(tarjeta, "Apellidos", 290, 180);
        agregarCampo(tarjeta, "Correo Electrónico", 290, 230);
        
        JLabel etiquetaSexo = new JLabel("Sexo:");
        etiquetaSexo.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 20));
        etiquetaSexo.setBounds(20, 290, 540, 20);
        tarjeta.add(etiquetaSexo);
        
        String[] opcionesSexo = {"", "Femenino", "Masculino"};
        JComboBox<String> comboSexo = new JComboBox<>(opcionesSexo);
        comboSexo.setBounds(80, 285, 120, 30);
        comboSexo.setBackground(Color.WHITE);
        tarjeta.add(comboSexo);
        comboSexo.setEditable(false);

        JLabel etiquetaRol = new JLabel("Rol:");
        etiquetaRol.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 20));
        etiquetaRol.setBounds(290, 290, 540, 20);
        tarjeta.add(etiquetaRol);

        String[] opcionesRol = {"", "Estudiante", "Docente", "Trabajador"};
        JComboBox<String> comboRol = new JComboBox<>(opcionesRol);
        comboRol.setBounds(335, 285, 120, 30);
        comboRol.setBackground(Color.WHITE);
        tarjeta.add(comboRol);
        comboRol.setEditable(false);
    

        JButton btnAceptar = new JButton("ACEPTAR REGISTRO");
        btnAceptar.setBounds(130, 400, 300, 40);
        btnAceptar.setBackground(new Color(0x4F4C96));
        btnAceptar.setForeground(Color.WHITE);
        btnAceptar.setFocusPainted(false);
        btnAceptar.setBorderPainted(false);
        btnAceptar.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 14));
        darEstiloBoton(btnAceptar,300,40);
        tarjeta.add(btnAceptar);

        
        

        txtPassword = new JPasswordField();
        txtPassword.setBounds(20, 345, 190, 40);
        configurarPasswordConPlaceholder(txtPassword, "Contraseña");
        tarjeta.add(txtPassword);

    
        ImageIcon iconVer = new ImageIcon(new ImageIcon(getClass().getResource("/res/ojoAbierto.png"))
        .getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH));
        ImageIcon iconOcultar = new ImageIcon(new ImageIcon(getClass().getResource("/res/ojoCerrado.png"))
                .getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH));

        JToggleButton btnShowPass = new JToggleButton(iconVer);
        btnShowPass.setBounds(210, 347, 35, 35);

        btnShowPass.setContentAreaFilled(false); 
        btnShowPass.setBorderPainted(false);
        btnShowPass.setFocusPainted(false);
        btnShowPass.setCursor(new Cursor(Cursor.HAND_CURSOR));
    

        btnShowPass.addActionListener(e -> {
            if (btnShowPass.isSelected()) {
                txtPassword.setEchoChar((char) 0); 
                btnShowPass.setIcon(iconOcultar);  
            } else {
                txtPassword.setEchoChar('•');     
                btnShowPass.setIcon(iconVer);     
            }
        });

        tarjeta.add(btnShowPass);
        tarjeta.setComponentZOrder(btnShowPass, 0);

        

        txtConfirmPassword = new JPasswordField();
        txtConfirmPassword.setBounds(290, 345, 190, 40);
        tarjeta.add(txtConfirmPassword);
        configurarPasswordConPlaceholder(txtConfirmPassword, "Confirmar Contraseña");

        JLabel lblLogin = new JLabel("¿Ya tienes cuenta? Inicia sesión aquí");
        lblLogin.setBounds(0, 450, 540, 20);
        lblLogin.setHorizontalAlignment(SwingConstants.CENTER);
        lblLogin.setForeground(new Color(0x4F4C96));
        lblLogin.setCursor(new Cursor(Cursor.HAND_CURSOR));

        lblLogin.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                System.out.println("Abriendo ventana de Login...");
            }

            @Override
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lblLogin.setText("<html><u>¿Ya tienes cuenta? Inicia sesión aquí</u></html>");
            }

            @Override
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lblLogin.setText("¿Ya tienes cuenta? Inicia sesión aquí");
            }
        });

        tarjeta.add(lblLogin);

    }

    
    private void agregarCampo(JPanel panel, String texto, int x, int y) {
        JTextField campo = new JTextField(texto);
        campo.setBounds(x, y, 230, 40);
        campo.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 14));
        campo.setForeground(Color.GRAY);
        panel.add(campo);
        campo.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(0xCCCCCC), 1),
            BorderFactory.createEmptyBorder(0, 5, 0, 0)
        ));

        campo.addFocusListener(new java.awt.event.FocusAdapter() {
            @Override
            public void focusGained(java.awt.event.FocusEvent evt) {
                if (campo.getText().equals(texto)) {
                    campo.setText("");
                    campo.setForeground(Color.BLACK);
                }
            }

            @Override
            public void focusLost(java.awt.event.FocusEvent evt) {
                if (campo.getText().isEmpty()) {
                    campo.setForeground(Color.GRAY);
                    campo.setText(texto);
                }
            }}
        );
    }

    private void darEstiloBoton(JButton boton, int ancho, int alto) {
        
        Color colorOriginal = boton.getBackground();
        Color colorHover = colorOriginal.brighter(); 
        
        Dimension dimension = new Dimension(ancho, alto);
        boton.setPreferredSize(dimension);
        boton.setMinimumSize(dimension);
        boton.setMaximumSize(dimension);
    
        boton.setFocusPainted(false);
        boton.setBorderPainted(false); 
        boton.setContentAreaFilled(true);

        boton.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                boton.setBackground(colorHover);
                boton.setCursor(new Cursor(Cursor.HAND_CURSOR));
            }

            @Override
            public void mouseExited(java.awt.event.MouseEvent evt) {
                boton.setBackground(colorOriginal);
                boton.setBorder(BorderFactory.createLineBorder(Color.WHITE, 1));
            }
        });
    }

    private void configurarPasswordConPlaceholder(JPasswordField campo, String texto) {
        
        campo.setEchoChar((char) 0); 
        campo.setText(texto);
        campo.setForeground(Color.GRAY);
        campo.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 14));

        campo.addFocusListener(new java.awt.event.FocusAdapter() {
            @Override
            public void focusGained(java.awt.event.FocusEvent evt) {
                
                String passActual = new String(campo.getPassword());
                if (passActual.equals(texto)) {
                    campo.setText("");
                    campo.setEchoChar('•'); 
                    campo.setForeground(Color.BLACK);
                }
            }

            @Override
            public void focusLost(java.awt.event.FocusEvent evt) {
                
                if (campo.getPassword().length == 0) {
                    campo.setEchoChar((char) 0); 
                    campo.setText(texto);
                    campo.setForeground(Color.GRAY);
                }
            }
        });
    }

    class PanelRedondeado extends JPanel {
        private int radio;
        private Color colorFondo;

        public PanelRedondeado(int radio, Color color) {
            this.radio = radio;
            this.colorFondo = color;
            setOpaque(false);
        }

        @Override
        protected void paintComponent(Graphics g) {
            Graphics2D g2 = (Graphics2D) g;
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setColor(colorFondo);
            g2.fillRoundRect(0, 0, getWidth(), getHeight(), radio, radio);
            super.paintComponent(g);
        }
    }
    public static void main(String args[]) {
    
        Registro ventanaRegistro = new Registro();
        ventanaRegistro.setBounds(0, 0, 640, 640);
        ventanaRegistro.setResizable(false);
        
        ventanaRegistro.setLocationRelativeTo(null);
        ventanaRegistro.setVisible(true);

        
    }
}
    