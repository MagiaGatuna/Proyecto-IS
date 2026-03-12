package src;
import src.controlador.Controlador_reg;
import src.controlador.Controlador_inicioS;
import src.controlador.Controlador_lp;
import src.controlador.Controlador_Reconocimiento;
import javax.swing.*;
import java.awt.*;
import src.vista.Landingpage;
import src.vista.Reconocimiento_facial;
import src.vista.Registro;
import src.vista.InicioSesion;

public class Main_Decision extends JFrame{
    private JButton opcion_normal;
    private JButton opcion_reconocimiento;
    public Main_Decision(){
        setTitle("Bienvenido al selector de módulo");
        setSize(300,200);
        setIconImage(new ImageIcon("res/logoSistemaComedor.png").getImage());

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new GridLayout(2,1,10,10));

        opcion_normal= new JButton("Comedor Online");
        opcion_normal.setBackground(new Color(14,9,137));
        opcion_normal.setPreferredSize(new Dimension(150,45));
        opcion_normal.setForeground(new Color(255,255,255));
        opcion_normal.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 14)); 

        opcion_normal.addActionListener(e->{
            iniciarNormal();
            this.dispose();
        });


        opcion_reconocimiento= new JButton("Reconocimiento Facial");
        opcion_reconocimiento.setBackground(new Color(92,180,155));
        opcion_reconocimiento.setPreferredSize(new Dimension(150,45));
        opcion_reconocimiento.setForeground(new Color(255,255,255));
        opcion_reconocimiento.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 14));

        opcion_reconocimiento.addActionListener(e->{
            iniciarFacial();
            this.dispose();
        });

        add(opcion_reconocimiento);
        add(opcion_normal);
        setLocationRelativeTo(null);
    }

    private void iniciarNormal(){
        Landingpage inicio= new Landingpage();
        Registro registro_b= new Registro();
        InicioSesion in_sesion= new InicioSesion();

        Controlador_lp control1;
        control1= new Controlador_lp(inicio, registro_b, in_sesion);
        Controlador_reg control2;
        control2= new Controlador_reg(inicio, registro_b, in_sesion);
        Controlador_inicioS control3;
        control3=new Controlador_inicioS(inicio, registro_b, in_sesion);


        inicio.setExtendedState(JFrame.MAXIMIZED_BOTH);
       
        //inicio.setResizable(false);
        inicio.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        inicio.setResizable(true);
        inicio.setVisible(true);

    }

    private void iniciarFacial(){
        src.modelo.ReservaDAO.limpiarReservasCaducadas(); // elimina reservas
        Reconocimiento_facial vistaFacial = new Reconocimiento_facial();
        new Controlador_Reconocimiento(vistaFacial);
        vistaFacial.setExtendedState(JFrame.MAXIMIZED_BOTH);
        vistaFacial.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        vistaFacial.setVisible(true);
        
    }

    public static void main(String[] args){
        SwingUtilities.invokeLater(()-> new Main_Decision().setVisible(true));
    }

}