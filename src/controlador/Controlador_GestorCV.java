
package src.controlador;

import javax.swing.JOptionPane;
import src.GestorCVView;
import src.Landingpage;
import src.HomeAdmin;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Controlador_GestorCV implements ActionListener {
    private GestorCVView view;
    private Landingpage landingpage;  
    private HomeAdmin homeAdmin;  

    public Controlador_GestorCV(GestorCVView CV, Landingpage landingpage, HomeAdmin homeAdmin) {
        this.view = CV;
        this.landingpage = landingpage;
        this.homeAdmin = homeAdmin;
        
        this.view.btnAgregar.addActionListener(this);
        this.view.btnCambiar.addActionListener(this);
        this.view.btnEliminar.addActionListener(this);
        this.view.btnHome.addActionListener(this);
        actualizarInterfaz();
    }

    @Override
    public void actionPerformed(ActionEvent e){
        if(e.getSource() == view.btnAgregar){
            manejarAgregar();
        } else if(e.getSource() == view.btnEliminar) {
            manejarEliminar();
        } else if(e.getSource() == view.btnCambiar) {
            manejarCambiar();
        } else if (e.getSource() == view.btnHome) {
            homeAdmin.setVisible(true);
            view.dispose();
        }
    }

    private void manejarAgregar() {
        /* 
        String nombre = view.txtNombre.getText();
        String precio = view.txtPrecio.getText();
        
        if(!nombre.isEmpty() && !precio.isEmpty()){
            try {
                src.modelo.editarCostos.agregarCF(nombre, Double.parseDouble(precio));
                actualizarInterfaz();
                limpiarCampos();
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(view, "El precio debe ser un número válido");
            }
        } else {
            JOptionPane.showMessageDialog(view, "Por favor completa los campos");
        }
        */
    }

    private void manejarCambiar() {
        /*
        String nombre = view.txtNombre.getText();
        String precio = view.txtPrecio.getText();
        
        if (!nombre.isEmpty() && !precio.isEmpty()) {
            try {
                src.modelo.editarCostos.editarCF(nombre, Double.parseDouble(precio));
                actualizarInterfaz();
                limpiarCampos();
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(view, "El precio debe ser un número válido");
            }
        } else {
            JOptionPane.showMessageDialog(view, "Por favor completa nombre y precio para cambiar");
        }
            */
    }

    private void manejarEliminar() {
        /*
        String nombre = view.txtNombre.getText();
        
        if (!nombre.isEmpty()) {
            int confirm = JOptionPane.showConfirmDialog(view, "¿Estás seguro de eliminar: " + nombre + "?", "Eliminar", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                src.modelo.editarCostos.eliminarCF(nombre);
                actualizarInterfaz();
                limpiarCampos();
            }
        } else {
            JOptionPane.showMessageDialog(view, "Por favor ingresa el nombre del costo a eliminar");
        }
            */
    }

    private void actualizarInterfaz() {
        /* 
        view.lblTotal.setText("Total: " + src.modelo.editarCostos.getCF());
        view.txtAreaDetalles.setText(src.modelo.editarCostos.actualizarTextArea());
        */
    }

    private void limpiarCampos() {
        view.txtNombre.setText("");
        view.txtPrecio.setText("");
    }
}