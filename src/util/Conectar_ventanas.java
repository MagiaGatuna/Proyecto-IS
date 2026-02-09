package src.util;
import src.vista.InicioSesion;
import src.vista.HomeAdmin;
import src.vista.Monedero;
import src.modelo.Usuario;
import javax.swing.*; 
import java.awt.*;
//como se nos explico en clase, usamos el patron Singleton para cambiar de ventanas 
 public class Conectar_ventanas {
    private static Conectar_ventanas instancia;//guarda la copia de la clase
    private JFrame ventanaActual;//un oabjeto vetnana 

    private Conectar_ventanas() {}//hacemos un constructor para que nadie cree copias accidentales

    public static Conectar_ventanas getInstancia() {
    if (instancia == null) instancia = new Conectar_ventanas();//si la instancia esta vacia, creeaala
    return instancia;//en cualquier caso devuelve la que ya existe
    }
    //para los camabios de las ventanas 
    public void mostrarInicioSesion() {
    cambiarVentana(new InicioSesion());//si la llmamos es porque queremos ver esta ventana
    }

    public void mostrarHomeAdmin(Usuario pr) {
    cambiarVentana(new HomeAdmin(pr));//igual sucede aqui
    }
    public void desplegarMonedero(JFrame ventanaDondeEstoy, Usuario u) {
    // 1. Buscamos si ya existe un monedero abierto para cerrarlo (evita duplicados)
    for (Component c : ventanaDondeEstoy.getLayeredPane().getComponents()) {
        if (c instanceof Monedero) {
            ventanaDondeEstoy.getLayeredPane().remove(c);
            ventanaDondeEstoy.repaint();
            return; 
        }
    }

    // 2. Creamos el monedero
    Monedero mo = new Monedero(u);
    
    // 3. Lo posicionamos arriba a la derecha
    int x = ventanaDondeEstoy.getWidth() - 320; 
    int y = 130; // Ajustado para que no tape la barra gris de tus compañeras
    mo.setBounds(x, y, 300, 200);
    
    // 4. USAMOS EL LAYERED PANE (El truco para que flote sobre BoxLayout/BorderLayout)
    ventanaDondeEstoy.getLayeredPane().add(mo, JLayeredPane.PALETTE_LAYER);
    
    ventanaDondeEstoy.repaint();
}
    
    /*public void mostrarLandingpage() {
    cambiarVentana(new Landingpage());//igual sucede aqui
    }

    public void mostrarRegistro() {
    cambiarVentana(new Registro());//igual sucede aqui
    }*/

    private void cambiarVentana(JFrame nueva) {//aqui nos aseguramos de que al abrir la ventana la vieja se cierre 
    if (ventanaActual != null) ventanaActual.dispose();
    ventanaActual = nueva;
    ventanaActual.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    ventanaActual.setExtendedState(JFrame.MAXIMIZED_BOTH);
    ventanaActual.setLocationRelativeTo(null);
    ventanaActual.setVisible(true);
    }
}
