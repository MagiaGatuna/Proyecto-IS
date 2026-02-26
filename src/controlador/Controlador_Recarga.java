package src.controlador;
import src.vista.*;
import src.modelo.Usuario;
import src.modelo.validadorInicioS;
import src.modelo.Validador_recarga;
import src.modelo.GestionSaldo;
//import src.util.LimpiarFormulariosUtil;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class Controlador_Recarga implements ActionListener {
    //vistas relacionadas 
    AlumnoView vista_alumno;
    EmpleadoView vista_empleado;
    Monedero monedero;
     //nuestra clase vista 
     RecargaView vista_recarga;
     Usuario user_actual= validadorInicioS.getUsuarioActual();
     String Rol=user_actual.getRol();
     //constructor
   public Controlador_Recarga( AlumnoView vista_alumno, EmpleadoView vista_empleado,Monedero monedero, RecargaView vista_recarga){
    
    this.monedero=monedero;
    this.vista_alumno=vista_alumno;
    this.vista_empleado=vista_empleado;
    this.vista_recarga=vista_recarga;


    this.vista_recarga.GetVolver().addActionListener(this);
    this.vista_recarga.GetVerificar().addActionListener(this);

    this.vista_recarga.setVisible(true);

    }


    //aqui es donde decididmos que pasa si toco un boton
    @Override
    public void actionPerformed(ActionEvent e) {
       
    if (e.getSource() == vista_recarga.GetVerificar()) {
         if(Validador_recarga.ValidarCampos(vista_recarga.cedula(),vista_recarga.Monto(),vista_recarga.referencia(),user_actual)){
            String montoInput = vista_recarga.Monto().getText().trim();

            GestionSaldo.ActualizarSaldo(user_actual.getCedula(),Double.parseDouble(montoInput));
            double nuevoSaldo = user_actual.getSaldo() + Double.parseDouble(montoInput);
            user_actual.setSaldo(nuevoSaldo);
            if (this.monedero != null) {
        this.monedero.actualizarSaldoVisual();
    }
            JOptionPane.showMessageDialog(vista_recarga, "Recarga Exitosa. Nuevo saldo: " + user_actual.getSaldo());
        }
     
    } else if (e.getSource() == vista_recarga.GetVolver()) {
         if(Rol.equals("Estudiante")){
         if (this.vista_alumno != null) {
                    vista_alumno.setExtendedState(JFrame.MAXIMIZED_BOTH);
                    //vista_alumno.setResizable(false);
                    vista_alumno.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                    vista_alumno.setVisible(true);
                    
                    vista_recarga.setVisible(false);
                    this.monedero.actualizarSaldoVisual();
                    vista_recarga.dispose();
                }
         }
        if (Rol.equals("Trabajador")||Rol.equals("Profesor")){
        if (this.vista_empleado != null) {
                    vista_empleado.setExtendedState(JFrame.MAXIMIZED_BOTH);
                    //vista_empleado.setResizable(false);
                    vista_empleado.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                    vista_empleado.setVisible(true);
                    
                    vista_recarga.setVisible(false);
                    this.monedero.actualizarSaldoVisual();
                    vista_recarga.dispose();
                }
        
        }
        
    }
    

}
}
