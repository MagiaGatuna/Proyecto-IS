package src.controlador;
//coment
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import src.GestorCVView;
import src.Landingpage;
import src.HomeAdmin;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Controlador_GestorCV implements ActionListener {
    private GestorCVView view;
    private HomeAdmin homeAdmin;  

    public Controlador_GestorCV(GestorCVView CV, Landingpage landingpage, HomeAdmin homeAdmin) {
        this.view = CV;
        this.homeAdmin = homeAdmin;
    
        src.modelo.editarCostos.actualizarJSON_CV();

        this.view.btnAgregar.addActionListener(this);
        this.view.btnCambiar.addActionListener(this);
        this.view.btnEliminar.addActionListener(this);
        this.view.btnHome.addActionListener(this);

        this.view.tabla.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                actualizarInterfaz();
            }
        });

        cargarMenusEnTabla();
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


    private String formatearIDParaUsuario(String id) {
        if (id == null) return "";
        return id.replace("_", " ")
                .replace("MONDAY", "Lunes")
                .replace("TUESDAY", "Martes")
                .replace("WEDNESDAY", "Miércoles")
                .replace("THURSDAY", "Jueves")
                .replace("FRIDAY", "Viernes")
                .replace("SATURDAY", "Sábado")
                .replace("SUNDAY", "Domingo")
                .replace("DESAYUNO", "Desayuno")
                .replace("ALMUERZO", "Almuerzo")
                .replace("CENA", "Cena");
    }

    private String getMenuSeleccionado() {
        int fila = view.tabla.getSelectedRow();
        if (fila != -1) {
            String nombreBonito = view.tabla.getValueAt(fila, 0).toString().toUpperCase();
            
            return nombreBonito.replace(" ", "_")
                            .replace("LUNES", "MONDAY")
                            .replace("MARTES", "TUESDAY")
                            .replace("MIÉRCOLES", "WEDNESDAY")
                            .replace("JUEVES", "THURSDAY")
                            .replace("VIERNES", "FRIDAY")
                            .replace("SÁBADO", "SATURDAY")
                            .replace("DOMINGO", "SUNDAY")
                            .replace("DESAYUNO", "DESAYUNO")
                            .replace("ALMUERZO", "ALMUERZO")
                            .replace("CENA", "CENA");
        }
        return null; 
    }


    private void manejarAgregar() {
        String idMenu = getMenuSeleccionado();
        if (idMenu == null) {
            JOptionPane.showMessageDialog(view, "Primero selecciona un menú en la tabla");
            return;
        }
    
        String nombre = view.txtNombre.getText().trim();
        String precioStr = view.txtPrecio.getText().trim();
        
        if(!nombre.isEmpty() && !precioStr.isEmpty()){
            try {
                double precio = Double.parseDouble(precioStr);
                src.modelo.editarCostos.agregarCV(idMenu, nombre, precio);
                actualizarInterfaz();
                limpiarCampos();
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(view, "El precio debe ser un número válido");
            }
        } else {
            JOptionPane.showMessageDialog(view, "Por favor completa los campos");
        }
    }

    private void manejarCambiar() {
        String idMenu = getMenuSeleccionado();
        String nombre = view.txtNombre.getText().trim();
        String precioStr = view.txtPrecio.getText().trim();
        
        if (idMenu != null && !nombre.isEmpty() && !precioStr.isEmpty()) {
            try {
                src.modelo.editarCostos.editarCV(idMenu, nombre, Double.parseDouble(precioStr));
                actualizarInterfaz();
                limpiarCampos();
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(view, "El precio debe ser un número");
            }
        } else {
            JOptionPane.showMessageDialog(view, "Selecciona un menú e ingresa nombre/precio");
        }
    }

    private void manejarEliminar() {
        String idMenu = getMenuSeleccionado();
        String nombre = view.txtNombre.getText().trim();
        
        if (idMenu != null && !nombre.isEmpty()) {
            int confirm = JOptionPane.showConfirmDialog(view, "¿Eliminar " + nombre + " de " + formatearIDParaUsuario(idMenu) + "?", "Eliminar", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                src.modelo.editarCostos.eliminarCV(idMenu, nombre);
                actualizarInterfaz();
                limpiarCampos();
            }
        } else {
            JOptionPane.showMessageDialog(view, "Selecciona un menú y escribe el nombre del costo a eliminar");
        }
    }


    public void cargarMenusEnTabla() {
        DefaultTableModel modelo = (DefaultTableModel) view.tabla.getModel();
        modelo.setRowCount(0); 

        try {
            java.nio.file.Path rutaMenus = java.nio.file.Paths.get("res/data/menus.json").toAbsolutePath();
            if (java.nio.file.Files.exists(rutaMenus)) {
                String contenido = new String(java.nio.file.Files.readAllBytes(rutaMenus), java.nio.charset.StandardCharsets.UTF_8);
                org.json.JSONArray jsonMenus = new org.json.JSONArray(contenido);

                for (int i = 0; i < jsonMenus.length(); i++) {
                    String idMenuTecnico = jsonMenus.getJSONObject(i).getString("dia_turno");
                    String nombreParaUsuario = formatearIDParaUsuario(idMenuTecnico);
                    String costoTotal = src.modelo.editarCostos.getCV(idMenuTecnico); 
                    
                    modelo.addRow(new Object[]{nombreParaUsuario, costoTotal});
                }
            }
        } catch (Exception e) {
            System.err.println("Error al cargar menús: " + e.getMessage());
        }
    }

    private void actualizarInterfaz() {
        String idMenu = getMenuSeleccionado();
        int fila = view.tabla.getSelectedRow();
        
        if (idMenu != null && fila != -1) {
            String nuevoTotal = src.modelo.editarCostos.getCV(idMenu);
            
            view.lblTotal.setText("Total " + formatearIDParaUsuario(idMenu) + ": " + nuevoTotal);
            
            view.txtAreaDetalles.setText(src.modelo.editarCostos.actualizarTextAreaCV(idMenu));
        
            view.tabla.setValueAt(nuevoTotal, fila, 1);
        }
    }

    private void limpiarCampos() {
        view.txtNombre.setText("");
        view.txtPrecio.setText("");
    }
}