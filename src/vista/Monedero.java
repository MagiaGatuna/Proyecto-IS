package src.vista;
import javax.swing.*;
import java.awt.*;
import src.util.Calcular;
import src.util.Diseño_interfaz;
import src.modelo.Usuario;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Monedero extends JPanel {
 //declaramos los elementos a utilizar 
    JButton boton_recargar;
    JLabel label_monedero;
    JLabel label_descuento;
    JLabel label_saldoactual;
//aqui definimos a nuestro usuario 
    private Usuario cliente;//guardara a el que ste usandolo en el momento
//constructor 
    public Monedero(Usuario pruebUsuario) {
    this.cliente= pruebUsuario;
    setLayout(null);//para poder arreglar a nustra conveniencia
    setSize(300, 200);
    setPreferredSize(new Dimension(300, 200));//por diseño le colocamos estas coordenadas
    setOpaque(false); // Para que se vea el redondeado

//creamos el boton para recargar 
      boton_recargar=Diseño_interfaz.Creador_Botones("Recargar",75, 135, 150, 35,Color.BLUE);
      boton_recargar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, 
                    "Esta funcionalidad estará disponible en la próxima actualización.", 
                    "En construcción", 
                    JOptionPane.INFORMATION_MESSAGE);
            }
        });
      add(boton_recargar);



//creamos el inico de el diseño conla palabra monedor virtual
      label_monedero=new JLabel("Monedero Virtual");//creamos objeto e ingresamos el texto
      label_monedero.setFont(new Font("Arial", Font.BOLD, 20));//por diseño definifmos el tipo de fuente,color y tamaño de la letra
      label_monedero.setForeground(Color.BLACK);//color de las letras
      label_monedero.setBounds(30, 20, 200, 30);//por diseño coocamos sus coordenadas
      add(label_monedero);//lo agregamos a el panel 
//creamos el texto de nustro saldo actual 
      label_saldoactual=new JLabel("Saldo actual:" + cliente.getSaldo());
      label_saldoactual.setFont(new Font("Arial", Font.BOLD, 16));//Fuente,tamaño,diseño
      label_saldoactual.setForeground(new Color(20, 50, 80));//color de las letras
      label_saldoactual.setBounds(30, 55, 240, 25);//coordenadas
      add(label_saldoactual);//lo agregamos al panel
//creamos el texto de nuestro descunto de el dia 
        double precioFinal = Calcular.calcularPrecio(this.cliente);
        double descuentoRepresentado = Calcular.CCB - precioFinal; 
        double porcentaje = (descuentoRepresentado / Calcular.CCB) * 100;
        label_descuento = new JLabel("Descuento por bandeja:" + porcentaje + "%");//objeto y nombre
        label_descuento.setFont(new Font("Arial", Font.PLAIN, 13));//fuente,diseño,tamaño
        label_descuento.setForeground(Color.GRAY);//color de las letras
        label_descuento.setBounds(30, 80, 240, 20);//coordenadas
        add(label_descuento);//agregamos a el panel

    }
   
    @Override//sobre cargamos la funcion
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        // Suavizado de bordes
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
       g2.setColor(Color.WHITE);
    g2.fillRoundRect(0, 0, getWidth() - 1, getHeight() - 1, 20, 20);

    // Borde sutil turquesa (para que combine con el fondo de la app)
    g2.setColor(new Color(158, 200, 185)); 
    g2.setStroke(new BasicStroke(2));
    g2.drawRoundRect(1, 1, getWidth() - 3, getHeight() - 3, 20, 20);

    // Línea divisora muy tenue
    g2.setColor(new Color(240, 240, 240));
    g2.drawLine(20, 110, getWidth() - 20, 110);

        
    }
    public static void main(String[] args) {
        /* 
        JFrame f = new JFrame();
    f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    f.setLayout(new GridBagLayout()); 
    f.getContentPane().setBackground(new Color(100, 170, 140));
    
    Usuario test = new Usuario("Valentina Test", 150.0, "estudiante");
    f.add(new Monedero(test)); 
    
    f.setSize(500, 400);
    f.setLocationRelativeTo(null);
    f.setVisible(true);
*/
    }
}
