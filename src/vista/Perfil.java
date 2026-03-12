package src.vista;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import src.util.Diseño_interfaz;
import src.modelo.Usuario;

public class Perfil extends JPanel {

    private final Color COLOR_AZUL = Diseño_interfaz.colorazul;
    private final Color COLOR_TURQUEZA = Diseño_interfaz.turquesa;

    private JLabel lblFoto;
    private JLabel lblCedula;
    private JLabel lblRol;
    private JLabel lblSexo;
    private JLabel lblEstado;
    private JLabel lblMensajeSecretaria;

    private Usuario usuario;

    public Perfil(Usuario u) {
        this.usuario = u;
        setLayout(null);
        setSize(320, 390);
        setPreferredSize(new Dimension(320, 390));
        setOpaque(false);

        construirPanel();
    }

    private void construirPanel() {
        // Foto
        lblFoto = new JLabel();
        lblFoto.setBounds(85, 20, 150, 150);
        lblFoto.setHorizontalAlignment(SwingConstants.CENTER);
        lblFoto.setBorder(BorderFactory.createLineBorder(COLOR_TURQUEZA, 2));
        cargarFoto();
        add(lblFoto);

        // Mensaje secretaría (solo visible si no tiene foto)
        lblMensajeSecretaria = new JLabel(
            "<html><center>Diríjase a Secretaría para<br>cargar su foto en el sistema</center></html>",
            SwingConstants.CENTER
        );
        lblMensajeSecretaria.setFont(new Font("SANS_SERIF", Font.ITALIC, 11));
        lblMensajeSecretaria.setForeground(Color.GRAY);
        lblMensajeSecretaria.setBounds(10, 175, 300, 35);
        add(lblMensajeSecretaria);

        // Cédula
        lblCedula = crearLabel("CI: " + usuario.getCedula(), Font.PLAIN, 14);
        lblCedula.setBounds(10, 225, 300, 22);
        add(lblCedula);

        // Rol
        lblRol = crearLabel("Rol: " + usuario.getRol(), Font.PLAIN, 14);
        lblRol.setBounds(10, 251, 300, 22);
        add(lblRol);

        // Sexo
        lblSexo = crearLabel("Sexo: " + usuario.getSexo(), Font.PLAIN, 14);
        lblSexo.setBounds(10, 277, 300, 22);
        add(lblSexo);

        // Estado
        lblEstado = crearLabel("Estado: " + usuario.getEstado(), Font.PLAIN, 14);
        lblEstado.setBounds(10, 303, 300, 22);
        add(lblEstado);
    }

    private void cargarFoto() {
        String cedula = usuario.getCedula();
        ImageIcon foto = null;

        // Buscar png primero, luego jpg
        File png = new File("res/Info_Secretaria/" + cedula + ".png");
        File jpg = new File("res/Info_Secretaria/" + cedula + ".jpg");

        if (png.exists()) {
            foto = new ImageIcon(png.getAbsolutePath());
        } else if (jpg.exists()) {
            foto = new ImageIcon(jpg.getAbsolutePath());
        }

        if (foto != null && foto.getImageLoadStatus() != java.awt.MediaTracker.ERRORED) {
            // Tiene foto propia
            Image scaled = foto.getImage().getScaledInstance(150, 150, Image.SCALE_SMOOTH);
            lblFoto.setIcon(new ImageIcon(scaled));
            // Ocultamos mensaje de secretaría más adelante (después de add)
            SwingUtilities.invokeLater(() -> lblMensajeSecretaria.setVisible(false));
        } else {
            // Sin foto, mostrar genérica
            ImageIcon generica = new ImageIcon("res/user_icon.png");
            if (generica.getImageLoadStatus() != java.awt.MediaTracker.ERRORED) {
                Image scaled = generica.getImage().getScaledInstance(150, 150, Image.SCALE_SMOOTH);
                lblFoto.setIcon(new ImageIcon(scaled));
            }
        }
    }

    private JLabel crearLabel(String texto, int estilo, int size) {
        JLabel lbl = new JLabel(texto, SwingConstants.CENTER);
        lbl.setFont(new Font("SANS_SERIF", estilo, size));
        lbl.setForeground(Color.BLACK);
        return lbl;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Fondo blanco redondeado
        g2.setColor(Color.WHITE);
        g2.fillRoundRect(0, 0, getWidth() - 1, getHeight() - 1, 20, 20);

        // Borde turquesa
        g2.setColor(new Color(158, 200, 185));
        g2.setStroke(new BasicStroke(2));
        g2.drawRoundRect(1, 1, getWidth() - 3, getHeight() - 3, 20, 20);

        // Línea divisora
        g2.setColor(new Color(240, 240, 240));
        g2.drawLine(20, 210, getWidth() - 20, 210);
    }
}