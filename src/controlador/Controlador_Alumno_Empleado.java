package src.controlador;

import src.Landingpage;
import src.AlumnoView;
import src.EmpleadoView;
import src.MenuSemanal;
import src.MenuDView;
import src.Monedero;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class Controlador_Alumno_Empleado implements ActionListener{
    
    private Landingpage inicio;
    private AlumnoView alumno;
    private EmpleadoView empleado;
    private MenuSemanal menu_semanal;
    private JPanel monedero;
    private MenuDView menu_d;

   public Controlador_Alumno_Empleado(Landingpage inicio, AlumnoView alumno, EmpleadoView empleado, MenuSemanal menu_semanal, JPanel monedero, MenuDView menu_d) {
    this.inicio = inicio;
    this.menu_semanal = menu_semanal;
    this.alumno = alumno;
    this.menu_d=menu_d;
    this.empleado = empleado;
    this.monedero = monedero;

    // Asignar listeners a Alumno
    if (this.alumno != null) {
        this.alumno.getinicio().addActionListener(this);
        this.alumno.getMenuS().addActionListener(this);
        this.alumno.getMenuD().addActionListener(this);
        
        // Inyectar monedero en Alumno
        if (monedero != null && alumno.getPanelMonedero() != null) {
            alumno.getPanelMonedero().add(monedero);
            alumno.getPanelMonedero().revalidate();
            alumno.getPanelMonedero().repaint();
        }
    }

    // Asignar listeners a Empleado
    if (this.empleado != null) {
        this.empleado.getinicio().addActionListener(this);
        this.empleado.getMenuS().addActionListener(this);
        this.empleado.getMenuD().addActionListener(this);
        
        // Inyectar monedero en Empleado (Bloque separado del de alumno)
        if (monedero != null && empleado.getPanelMonedero() != null) {
            empleado.getPanelMonedero().add(monedero);
            empleado.getPanelMonedero().revalidate();
            empleado.getPanelMonedero().repaint();
        }
    }
}
@Override
    public void actionPerformed(ActionEvent e) {
        // Lógica para volver al Inicio (Cerrar Sesión)
        if (alumno != null && e.getSource() == alumno.getinicio()) {
            volverAInicio(alumno);
        } else if (empleado != null && e.getSource() == empleado.getinicio()) {
            volverAInicio(empleado);
        }
        
        // Lógica para ver el Menú Semanal
        if (alumno != null && e.getSource() == alumno.getMenuS()) {
            mostrarMenu(alumno);
        } else if (empleado != null && e.getSource() == empleado.getMenuS()) {
            mostrarMenu(empleado);
        }

        if (e.getSource() == alumno.getMenuD()) {
                alumno.setVisible(false);
                new Controlador_MenuDiario(alumno, null, null);
            }else if (e.getSource() == empleado.getMenuD()) {
                empleado.setVisible(false); 
                new Controlador_MenuDiario(null, empleado, null);
            }
    }

    private void volverAInicio(JFrame vistaActual) {
        inicio.setExtendedState(JFrame.MAXIMIZED_BOTH);
        inicio.setVisible(true);
        vistaActual.setVisible(false);
    }

    private void mostrarMenu(JFrame vistaActual) {
        menu_semanal.setExtendedState(JFrame.MAXIMIZED_BOTH);
        menu_semanal.setVisible(true);
        vistaActual.setVisible(false);
    }

    

} 

