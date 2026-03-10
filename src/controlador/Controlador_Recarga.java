package src.controlador;

import src.vista.*;
import src.modelo.Usuario;
import src.modelo.validadorInicioS;
import src.modelo.Validador_recarga;
import src.modelo.GestionSaldo;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class Controlador_Recarga implements ActionListener {
    AlumnoView vista_alumno;
    EmpleadoView vista_empleado;
    Monedero monedero;
    RecargaView vista_recarga;
    Usuario user_actual = validadorInicioS.getUsuarioActual();
    String Rol = user_actual.getRol();
    String cedula = user_actual.getCedula();

    public Controlador_Recarga(AlumnoView vista_alumno, EmpleadoView vista_empleado, Monedero monedero, RecargaView vista_recarga) {
        this.monedero = monedero;
        this.vista_alumno = vista_alumno;
        this.vista_empleado = vista_empleado;
        this.vista_recarga = vista_recarga;

        this.vista_recarga.GetVolver().addActionListener(this);
        this.vista_recarga.GetVerificar().addActionListener(this);
        this.vista_recarga.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        
        if (e.getSource() == vista_recarga.GetVerificar()) {
            if (Validador_recarga.ValidarCampos(vista_recarga.cedula(), vista_recarga.referencia(), vista_recarga.Monto(), user_actual)) {
                
                double montoARecargar = Double.parseDouble(vista_recarga.Monto().getText().trim());

                if (GestionSaldo.ActualizarSaldo(user_actual.getCedula(),cedula, montoARecargar)) {
                    // Actualizamos el total en el objeto modelo
                    double nuevoSaldoTotal = user_actual.getSaldo() + montoARecargar;
                    user_actual.setSaldo(nuevoSaldoTotal);

                    // Actualizamos la vista Monedero con el TOTAL
                    if (this.monedero != null) {
                        this.monedero.actualizarSaldoVisual(user_actual.getSaldo());
                    }

                    JOptionPane.showMessageDialog(vista_recarga, "Recarga Exitosa. Nuevo saldo: " + user_actual.getSaldo());
                }
            }
        } 
    
        else if (e.getSource() == vista_recarga.GetVolver()) {
            if (Rol.equals("Estudiante")) {
                if (this.vista_alumno != null) {
                    vista_alumno.setExtendedState(JFrame.MAXIMIZED_BOTH);
                    vista_alumno.setVisible(true);
                }
            } else if (Rol.equals("Trabajador") || Rol.equals("Profesor")) {
                if (this.vista_empleado != null) {
                    vista_empleado.setExtendedState(JFrame.MAXIMIZED_BOTH);
                    vista_empleado.setVisible(true);
                }
            }

            // Refrescamos visualmente antes de cerrar
            if (this.monedero != null) {
                this.monedero.actualizarSaldoVisual(user_actual.getSaldo());
            }
            vista_recarga.dispose();
        }
    }
}