package src.controlador;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import src.vista.GestorCostosView;
import src.vista.Landingpage;
import src.vista.HomeAdmin;
import src.vista.GestorCFView;
import src.vista.GestorCVView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Controlador_GestorCostos implements ActionListener {
    private GestorCostosView view;
    private Landingpage landingpage;  
    private HomeAdmin homeAdmin;  
    private GestorCFView gestorCFView;
    private GestorCVView gestorCVView;

    public Controlador_GestorCostos(GestorCostosView view, Landingpage landingpage, HomeAdmin homeAdmin, GestorCFView gestorCFView, GestorCVView gestorCVView) {
        this.view = view;
        this.landingpage = landingpage;
        this.homeAdmin = homeAdmin;
        this.gestorCFView = gestorCFView;
        this.gestorCVView = gestorCVView;
        
        this.view.setControlador(this);

        src.modelo.MermayCCB.actualizarJSON_CCB();

        this.view.btnActualizarMerma.addActionListener(this);
        
        this.view.setVisible(true);

        actualizarCF();

        cargarMenusEnTabla();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String comando = e.getActionCommand();
            if ("MOD_CV".equals(comando)) {
                new Controlador_GestorCV(gestorCVView,landingpage,view);
                gestorCVView.setVisible(true);
                view.dispose();
            } else if ("MOD_CF".equals(comando)) {
                new Controlador_GestorCF(gestorCFView,landingpage,view);
                gestorCFView.setVisible(true);
                view.dispose();
            } else if ("GO_HOME".equals(comando)) {
                homeAdmin.setVisible(true);
                view.dispose();
            } else if(e.getSource() == view.btnActualizarMerma) {
                manejarCambiar();
            }
    }

    private void actualizarCF() {
        String nuevoCF = src.modelo.editarCostos.getCF();
        
        view.lblCostoFijoValor.setText(nuevoCF);
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

    private void manejarCambiar() {
        String idMenu = getMenuSeleccionado();
        String NuevaMerma = view.txtMermaPorcentaje.getText().trim();
        
        if (idMenu != null && !NuevaMerma.isEmpty()) {
            try {
                src.modelo.MermayCCB.editarMerma(idMenu, Double.parseDouble(NuevaMerma));
                cargarMenusEnTabla(); 
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(view, "La merma debe ser un número");
            }
        } else {
            JOptionPane.showMessageDialog(view, "Selecciona un menú en la tabla e ingresa merma");
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
                    String cv = src.modelo.editarCostos.getCV(idMenuTecnico); 
                    String CCB = src.modelo.MermayCCB.getCCB(idMenuTecnico);
                    String merma = src.modelo.MermayCCB.getMerma(idMenuTecnico);
                    
                    modelo.addRow(new Object[]{nombreParaUsuario, cv, "1", merma, CCB});
                }
            }
        } catch (Exception e) {
            System.err.println("Error al cargar menús: " + e.getMessage());
        }
    }


}