package src.util;
import src.vista.InicioSesion;
import src.vista.HomeAdmin;
import src.vista.Monedero;
import src.vista.Perfil;
import src.vista.Tarifas;
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

        for (Component c : ventanaDondeEstoy.getLayeredPane().getComponents()) {
            if (c instanceof Perfil) {
                ventanaDondeEstoy.getLayeredPane().remove(c);
                ventanaDondeEstoy.repaint();
                break;
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

    public void desplegarPerfil(JFrame ventanaDondeEstoy, Usuario u) {
        for (Component c : ventanaDondeEstoy.getLayeredPane().getComponents()) {
            if (c instanceof Perfil) {
                ventanaDondeEstoy.getLayeredPane().remove(c);
                ventanaDondeEstoy.repaint();
                return;
            }
        }

        // Cerrar monedero si está abierto
        for (Component c : ventanaDondeEstoy.getLayeredPane().getComponents()) {
            if (c instanceof Monedero) {
                ventanaDondeEstoy.getLayeredPane().remove(c);
                ventanaDondeEstoy.repaint();
                break;
            }
        }

        Perfil perfil = new Perfil(u);
        int x = ventanaDondeEstoy.getWidth() - 340;
        int y = 130;
        perfil.setBounds(x, y, 320, 390);
        ventanaDondeEstoy.getLayeredPane().add(perfil, JLayeredPane.PALETTE_LAYER);
        ventanaDondeEstoy.repaint();
    }

    public void desplegarTarifas(JFrame ventanaDondeEstoy) {
        for (Component c : ventanaDondeEstoy.getLayeredPane().getComponents()) {
            if (c instanceof Tarifas) {
                ventanaDondeEstoy.getLayeredPane().remove(c);
                ventanaDondeEstoy.repaint();
                return;
            }
        }

        for (Component c : ventanaDondeEstoy.getLayeredPane().getComponents()) {
            if (c instanceof Monedero || c instanceof Perfil) {
                ventanaDondeEstoy.getLayeredPane().remove(c);
                ventanaDondeEstoy.repaint();
                break;
            }
        }

        Tarifas tarifas = new Tarifas();
        int x = ventanaDondeEstoy.getWidth() - 340;
        int y = 130;
        tarifas.setBounds(x, y, 320, 380);
        ventanaDondeEstoy.getLayeredPane().add(tarifas, JLayeredPane.PALETTE_LAYER);
        ventanaDondeEstoy.repaint();
    }

    private void cambiarVentana(JFrame nueva) {
        if (ventanaActual != null) ventanaActual.dispose();
        ventanaActual = nueva;
        ventanaActual.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ventanaActual.setExtendedState(JFrame.MAXIMIZED_BOTH);
        ventanaActual.setLocationRelativeTo(null);
        ventanaActual.setVisible(true);
    }
}