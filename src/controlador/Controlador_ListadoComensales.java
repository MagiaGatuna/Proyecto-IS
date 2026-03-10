package src.controlador;

import src.vista.ListadoComensales;
import src.vista.HomeAdmin;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import org.json.JSONObject;
import org.json.JSONArray;


public class Controlador_ListadoComensales implements ActionListener {

    private ListadoComensales vista;
    private HomeAdmin homeAdmin;
    private String turnoActivo = "desayuno";
    private String fechaActiva = null;
    private JSONObject consumos = null;

    public Controlador_ListadoComensales(ListadoComensales vista, HomeAdmin homeAdmin){
        this.vista = vista;
        this.homeAdmin = homeAdmin;

        this.vista.btnHome.addActionListener(this);
        this.vista.btnDesayuno.addActionListener(this);
        this.vista.btnAlmuerzo.addActionListener(this);

        this.vista.listFechas.addListSelectionListener(new ListSelectionListener(){
            @Override
            public void valueChanged(ListSelectionEvent e){
                if(!e.getValueIsAdjusting()){
                    String fechaSeleccionada = vista.listFechas.getSelectedValue();
                    if(fechaSeleccionada != null){
                        fechaActiva = fechaSeleccionada;
                        cargarTabla();
                    }
                }
            }
        });

        cargarJSON();
        cargarFechas();
        resaltarBoton("desayuno");

        this.vista.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e){
        if(e.getSource() == vista.btnHome){
            homeAdmin.setVisible(true);
            vista.dispose();
        }else if(e.getSource() == vista.btnDesayuno){
            turnoActivo = "desayuno";
            resaltarBoton("desayuno");
            cargarFechas();
        }else if(e.getSource() == vista.btnAlmuerzo){
            turnoActivo = "almuerzo";
            resaltarBoton("almuerzo");
            cargarFechas();
        }
    }

    private void cargarJSON(){
        try{
            java.nio.file.Path ruta = java.nio.file.Paths.get("res/data/consumos.json").toAbsolutePath();
            String contenido = new String(java.nio.file.Files.readAllBytes(ruta), java.nio.charset.StandardCharsets.UTF_8);
            consumos = new JSONObject(contenido);
        }catch(Exception e){
            System.err.println("Error al leer consumos.json: " + e.getMessage());
        }
    }

    private void cargarFechas(){
        DefaultListModel<String> modelo = (DefaultListModel<String>) vista.listFechas.getModel();
        modelo.clear();
        fechaActiva = null;
        limpiarTablaYResumen();

        if(consumos == null) return;

        JSONObject turnoObj = consumos.optJSONObject(turnoActivo);
        if(turnoObj == null) return;

        java.util.List<String> fechas = new java.util.ArrayList<>(turnoObj.keySet());
        fechas.sort(java.util.Collections.reverseOrder());

        for (String fecha : fechas) {
            modelo.addElement(fecha);
        }

        if (!fechas.isEmpty()) {
            vista.listFechas.setSelectedIndex(0);
        }
    }

    private void cargarTabla(){
        if(consumos == null || fechaActiva == null) return;

        JSONObject turnoObj = consumos.optJSONObject(turnoActivo);
        if(turnoObj == null) return;

        JSONObject diaObj = turnoObj.optJSONObject(fechaActiva);
        if(diaObj == null) return;

        JSONArray asistentes = diaObj.getJSONArray("asistentes");
        JSONObject resumen = diaObj.getJSONObject("resumen");

        vista.lblRegular.setText("Regulares: " + resumen.getInt("regular"));
        vista.lblBecario.setText("Becarios: " + resumen.getInt("becario"));
        vista.lblExonerado.setText("Exonerados: " + resumen.getInt("exhonerado"));
        vista.lblEmpleado.setText("Empleados: " + resumen.getInt("empleado"));
        vista.lblProfesor.setText("Profesores: " + resumen.getInt("profesor"));
        vista.lblTotal.setText("Total: " + resumen.getInt("total"));

        DefaultTableModel modelo = (DefaultTableModel) vista.tabla.getModel();
        modelo.setRowCount(0);

        for(int i = asistentes.length() - 1; i >= 0; i--){
            JSONObject a = asistentes.getJSONObject(i);
            modelo.addRow(new Object[]{
                a.getString("cedula"),
                a.getString("rol"),
                a.optString("tipo", "-")
            });
        }
    }

    private void limpiarTablaYResumen(){
        vista.lblRegular.setText("Regulares: 0");
        vista.lblBecario.setText("Becarios: 0");
        vista.lblExonerado.setText("Exonerados: 0");
        vista.lblEmpleado.setText("Empleados: 0");
        vista.lblProfesor.setText("Profesores: 0");
        vista.lblTotal.setText("Total: 0");
        ((DefaultTableModel) vista.tabla.getModel()).setRowCount(0);
    }

    private void resaltarBoton(String turno) {
        vista.btnDesayuno.setText(turno.equals("desayuno") ? "▶ DESAYUNO" : "DESAYUNO");
        vista.btnAlmuerzo.setText(turno.equals("almuerzo") ? "▶ ALMUERZO" : "ALMUERZO");
    }
}