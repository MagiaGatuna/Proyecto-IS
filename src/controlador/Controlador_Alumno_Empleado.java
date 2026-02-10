package src.controlador;

import src.vista.*;
import src.util.LimpiarFormulariosUtil;

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
    private InicioSesion inicio_sesion;

   public Controlador_Alumno_Empleado(Landingpage inicio, AlumnoView alumno, EmpleadoView empleado, MenuSemanal menu_semanal, JPanel monedero, MenuDView menu_d,InicioSesion inicio_sesion) {
    this.inicio = inicio;
    this.menu_semanal = menu_semanal;
    this.alumno = alumno;
    this.menu_d=menu_d;
    this.empleado = empleado;
    this.monedero = monedero;
    this.inicio_sesion = inicio_sesion;

    if (this.alumno != null) {
        this.alumno.getinicio().addActionListener(this);
        this.alumno.getMenuS().addActionListener(this);
        this.alumno.getMenuD().addActionListener(this);
        avisoProximamente(this.alumno.getBtnConsumos());
        
       
        if (monedero != null && alumno.getPanelMonedero() != null) {
            alumno.getPanelMonedero().add(monedero);
            alumno.getPanelMonedero().revalidate();
            alumno.getPanelMonedero().repaint();
        }
    }

    
    if (this.empleado != null) {
        this.empleado.getinicio().addActionListener(this);
        this.empleado.getMenuS().addActionListener(this);
        this.empleado.getMenuD().addActionListener(this);
        avisoProximamente(this.empleado.getBtnConsumos());
        
        
        if (monedero != null && empleado.getPanelMonedero() != null) {
            empleado.getPanelMonedero().add(monedero);
            empleado.getPanelMonedero().revalidate();
            empleado.getPanelMonedero().repaint();
        }
    }


}
    private void avisoProximamente(JButton boton) {
            if (boton != null) {
                boton.addActionListener(e -> {
                    JOptionPane.showMessageDialog(null, 
                        "Esta funcionalidad estará disponible en la próxima actualización.", 
                        "En construcción", 
                        JOptionPane.INFORMATION_MESSAGE);
                });
            }
        }

@Override
    public void actionPerformed(ActionEvent e) {
       
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

        if (alumno != null && e.getSource() == alumno.getMenuD()) {
            mostrarMenu2(alumno);
            }else if (empleado != null && e.getSource() == empleado.getMenuD()) {
            mostrarMenu2(empleado);
            }
    }

    private void volverAInicio(JFrame vistaActual) {

        if (inicio_sesion != null) {
            LimpiarFormulariosUtil.limpiarInicioSesion(inicio_sesion.getCedula_id(), inicio_sesion.getContraseña());
        }
        
        inicio.setExtendedState(JFrame.MAXIMIZED_BOTH);
        inicio.setVisible(true);
        vistaActual.setVisible(false);
        vistaActual.dispose();
       
    }

    private void mostrarMenu(JFrame vistaActual) {
        menu_semanal.setExtendedState(JFrame.MAXIMIZED_BOTH);
        menu_semanal.setVisible(true);
        vistaActual.setVisible(false);
    }

    private void mostrarMenu2(JFrame vistaActual) {
        menu_d.setExtendedState(JFrame.MAXIMIZED_BOTH);
        menu_d.setVisible(true);
        vistaActual.setVisible(false);
    }

    

} 

