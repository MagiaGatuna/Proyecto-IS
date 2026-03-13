package src.vista;

import javax.swing.*;
import java.awt.*;
import src.util.Diseño_interfaz;
import src.util.Calcular;

public class Tarifas extends JPanel{

    private final Color COLOR_AZUL = Diseño_interfaz.colorazul;
    private final Color COLOR_TURQUEZA = Diseño_interfaz.turquesa;

    private JTextField txtEstudiante;
    private JTextField txtBecario;
    private JTextField txtProfesor;
    private JTextField txtEmpleado;
    private JButton btnGuardar;

    public Tarifas(){
        setLayout(null);
        setSize(320, 380);
        setPreferredSize(new Dimension(320, 380));
        setOpaque(false);
        construirPanel();
    }

    private void construirPanel(){
        JLabel lblTitulo = new JLabel("Tarifas", SwingConstants.CENTER);
        lblTitulo.setFont(new Font("SANS_SERIF", Font.BOLD, 20));
        lblTitulo.setForeground(Color.BLACK);
        lblTitulo.setBounds(10, 15, 300, 30);
        add(lblTitulo);

        // Estudiante (20% - 30%)
        agregarCampo("Estudiante (20% - 30%)", String.valueOf(Calcular.PorcentajeEstudiante), 60);
        txtEstudiante = ultimoCampo;

        // Becario (1% - estudiante-1%)
        agregarCampo("Becario (1% - menor al Estudiante)", String.valueOf(Calcular.PorcentajeBecario), 130);
        txtBecario = ultimoCampo;

        // Profesor (70% - 90%)
        agregarCampo("Profesor (70% - 90%)", String.valueOf(Calcular.PorcentajeProfesor), 200);
        txtProfesor = ultimoCampo;

        // Empleado (90% - 110%)
        agregarCampo("Empleado (90% - 110%)", String.valueOf(Calcular.PorcentajeEmpleado), 270);
        txtEmpleado = ultimoCampo;

        btnGuardar = Diseño_interfaz.Creador_Botones("GUARDAR", 0, 0, 140, 35, COLOR_AZUL);
        btnGuardar.setBounds(85, 325, 140, 35);
        btnGuardar.addActionListener(e -> guardar());
        add(btnGuardar);
    }

    private JTextField ultimoCampo;

    private void agregarCampo(String etiqueta, String valorActual, int y){
        JLabel lbl = new JLabel(etiqueta);
        lbl.setFont(new Font("SANS_SERIF", Font.PLAIN, 12));
        lbl.setForeground(Color.GRAY);
        lbl.setBounds(20, y, 280, 18);
        add(lbl);

        JTextField txt = new JTextField(valorActual);
        txt.setFont(new Font("SANS_SERIF", Font.BOLD, 14));
        txt.setBounds(20, y + 20, 280, 35);
        txt.setBorder(BorderFactory.createLineBorder(COLOR_TURQUEZA, 1));
        add(txt);

        ultimoCampo = txt;
    }

    private void guardar(){
        try{
            double estudiante = Double.parseDouble(txtEstudiante.getText().trim());
            double becario = Double.parseDouble(txtBecario.getText().trim());
            double profesor = Double.parseDouble(txtProfesor.getText().trim());
            double empleado = Double.parseDouble(txtEmpleado.getText().trim());

            if(estudiante < 20 || estudiante > 30){
                JOptionPane.showMessageDialog(this, "El porcentaje del Estudiante debe estar entre 20% y 30%.", "Rango inválido", JOptionPane.WARNING_MESSAGE);
                return;
            }
            if(becario < 1 || becario >= estudiante){
                JOptionPane.showMessageDialog(this, "El porcentaje del Becario debe estar entre 1% y menor a " + estudiante + "%.", "Rango inválido", JOptionPane.WARNING_MESSAGE);
                return;
            }
            if(profesor < 70 || profesor > 90){
                JOptionPane.showMessageDialog(this, "El porcentaje del Profesor debe estar entre 70% y 90%.", "Rango inválido", JOptionPane.WARNING_MESSAGE);
                return;
            }
            if(empleado < 90 || empleado > 110){
                JOptionPane.showMessageDialog(this, "El porcentaje del Empleado debe estar entre 90% y 110%.", "Rango inválido", JOptionPane.WARNING_MESSAGE);
                return;
            }

            Calcular.cambiarPorcentaje(estudiante, "estudiante");
            Calcular.cambiarPorcentaje(becario, "becario");
            Calcular.cambiarPorcentaje(profesor, "profesor");
            Calcular.cambiarPorcentaje(empleado, "empleado");

            JOptionPane.showMessageDialog(this, "¡Tarifas actualizadas correctamente!", "Éxito", JOptionPane.INFORMATION_MESSAGE);

        }catch(NumberFormatException ex){
            JOptionPane.showMessageDialog(this, "Por favor ingrese valores numéricos válidos.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    @Override
    protected void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        g2.setColor(Color.WHITE);
        g2.fillRoundRect(0, 0, getWidth()-1, getHeight()-1, 20, 20);

        g2.setColor(new Color(158, 200, 185));
        g2.setStroke(new BasicStroke(2));
        g2.drawRoundRect(1, 1, getWidth()-3, getHeight()-3, 20, 20);

        g2.setColor(new Color(240, 240, 240));
        g2.drawLine(20, 55, getWidth()-20, 55);
    }
}