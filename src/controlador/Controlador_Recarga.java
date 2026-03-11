package src.controlador;

import src.vista.*;
import src.modelo.Usuario;
import src.modelo.validadorInicioS;
import src.modelo.Validador_recarga;
import src.modelo.GestionSaldo;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.swing.*;

import org.json.JSONArray;
import org.json.JSONObject;

public class Controlador_Recarga implements ActionListener {
    AlumnoView vista_alumno;
    EmpleadoView vista_empleado;
    Monedero monedero;
    RecargaView vista_recarga;
    Usuario user_actual = validadorInicioS.getUsuarioActual();
    String Rol = user_actual.getRol();

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
        System.out.println("Evento recibido: " + e.getSource());
        System.out.println("Verificar es: " + vista_recarga.GetVerificar());
        if (e.getSource() == vista_recarga.GetVerificar()) {
            if (Validador_recarga.ValidarCampos(vista_recarga.cedula(), vista_recarga.referencia(), vista_recarga.Monto(), user_actual)) {
                
                double montoARecargar = Double.parseDouble(vista_recarga.Monto().getText().trim());
                String cedulaDestino = vista_recarga.cedula().getText().trim();

                String error = GestionSaldo.ActualizarSaldo(user_actual.getCedula(), cedulaDestino, montoARecargar);
                if (error == null) {
                    double nuevoSaldoTotal = user_actual.getSaldo() + montoARecargar;
                    user_actual.setSaldo(nuevoSaldoTotal);
                    if (this.monedero != null) this.monedero.actualizarSaldoVisual(user_actual.getSaldo());
                    JOptionPane.showMessageDialog(vista_recarga, "Recarga Exitosa. Nuevo saldo: " + user_actual.getSaldo());
                } else {
                    JOptionPane.showMessageDialog(vista_recarga, error, "Error", JOptionPane.WARNING_MESSAGE);
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