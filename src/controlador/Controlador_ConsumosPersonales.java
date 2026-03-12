package src.controlador;

import src.vista.ConsumosPersonalesView;
import src.vista.AlumnoView;
import src.vista.EmpleadoView;
import src.modelo.Usuario;
import src.modelo.validadorInicioS;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.json.JSONObject;
import org.json.JSONArray;

public class Controlador_ConsumosPersonales implements ActionListener {

    private ConsumosPersonalesView vista;
    private AlumnoView alumno;
    private EmpleadoView empleado;
    private Usuario usuarioLogueado;

    private JSONObject consumos = null;
    private String filtroActivo = "todos";

    private int totalDesayunos = 0;
    private int totalAlmuerzos = 0;

    public Controlador_ConsumosPersonales(ConsumosPersonalesView vista, AlumnoView alumno, EmpleadoView empleado) {
        this.vista = vista;
        this.alumno = alumno;
        this.empleado = empleado;
        this.usuarioLogueado = validadorInicioS.getUsuarioActual();

        vista.getBtnVolver().addActionListener(this);
        vista.getBtnTodos().addActionListener(this);
        vista.getBtnDesayuno().addActionListener(this);
        vista.getBtnAlmuerzo().addActionListener(this);

        cargarJSON();
        calcularTotalesGlobales();
        actualizarResumen();

        vista.setColumnas("todos");
        cargarTabla();
        resaltarBoton("todos");

        vista.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == vista.getBtnVolver()) {
            volver();
        } else if (e.getSource() == vista.getBtnTodos()) {
            filtroActivo = "todos";
            resaltarBoton("todos");
            vista.setColumnas("todos");
            cargarTabla();
        } else if (e.getSource() == vista.getBtnDesayuno()) {
            filtroActivo = "desayuno";
            resaltarBoton("desayuno");
            vista.setColumnas("desayuno");
            cargarTabla();
        } else if (e.getSource() == vista.getBtnAlmuerzo()) {
            filtroActivo = "almuerzo";
            resaltarBoton("almuerzo");
            vista.setColumnas("almuerzo");
            cargarTabla();
        }
    }

    private void volver() {
        String rol = validadorInicioS.getRol();
        vista.setVisible(false);
        vista.dispose();

        if (rol.equals("Estudiante") && alumno != null) {
            alumno.setExtendedState(JFrame.MAXIMIZED_BOTH);
            alumno.setVisible(true);
        } else if ((rol.equals("Empleado") || rol.equals("Profesor")) && empleado != null) {
            empleado.setExtendedState(JFrame.MAXIMIZED_BOTH);
            empleado.setVisible(true);
        }
    }

    private void cargarJSON() {
        try {
            java.nio.file.Path ruta = java.nio.file.Paths.get("res/data/consumos.json").toAbsolutePath();
            String contenido = new String(java.nio.file.Files.readAllBytes(ruta), java.nio.charset.StandardCharsets.UTF_8);
            consumos = new JSONObject(contenido);
        } catch (Exception e) {
            System.err.println("Error al leer consumos.json: " + e.getMessage());
        }
    }

    private void calcularTotalesGlobales() {
        totalDesayunos = 0;
        totalAlmuerzos = 0;
        if (consumos == null || usuarioLogueado == null) return;

        String cedula = usuarioLogueado.getCedula();

        totalDesayunos = contarApariciones("desayuno", cedula);
        totalAlmuerzos = contarApariciones("almuerzo", cedula);
    }

    private int contarApariciones(String turno, String cedula) {
        int count = 0;
        JSONObject turnoObj = consumos.optJSONObject(turno);
        if (turnoObj == null) return 0;

        for (String fecha : turnoObj.keySet()) {
            JSONObject diaObj = turnoObj.optJSONObject(fecha);
            if (diaObj == null) continue;
            JSONArray asistentes = diaObj.optJSONArray("asistentes");
            if (asistentes == null) continue;
            for (int i = 0; i < asistentes.length(); i++) {
                if (cedula.equals(asistentes.getJSONObject(i).optString("cedula"))) {
                    count++;
                }
            }
        }
        return count;
    }

    private void actualizarResumen() {
        int total = totalDesayunos + totalAlmuerzos;
        vista.getLblTotal().setText("Total: " + total);
        vista.getLblTotalDesayuno().setText("Desayunos: " + totalDesayunos);
        vista.getLblTotalAlmuerzo().setText("Almuerzos: " + totalAlmuerzos);
    }

    private void cargarTabla() {
        DefaultTableModel modelo = vista.getTableModel();
        if (consumos == null || usuarioLogueado == null) return;

        String cedula = usuarioLogueado.getCedula();
        List<String[]> filas = new ArrayList<>();

        String[] turnos = filtroActivo.equals("todos")
                ? new String[]{"desayuno", "almuerzo"}
                : new String[]{filtroActivo};

        for (String turno : turnos) {
            JSONObject turnoObj = consumos.optJSONObject(turno);
            if (turnoObj == null) continue;

            List<String> fechas = new ArrayList<>(turnoObj.keySet());
            Collections.sort(fechas, Collections.reverseOrder());

            for (String fecha : fechas) {
                JSONObject diaObj = turnoObj.optJSONObject(fecha);
                if (diaObj == null) continue;
                JSONArray asistentes = diaObj.optJSONArray("asistentes");
                if (asistentes == null) continue;

                for (int i = 0; i < asistentes.length(); i++) {
                    JSONObject a = asistentes.getJSONObject(i);
                    if (cedula.equals(a.optString("cedula"))) {
                        if (filtroActivo.equals("todos")) {
                            filas.add(new String[]{
                                fecha,
                                turno.substring(0, 1).toUpperCase() + turno.substring(1)
                            });
                        } else {
                            filas.add(new String[]{fecha});
                        }
                    }
                }
            }
        }

        filas.sort((a, b) -> b[0].compareTo(a[0]));

        for (String[] fila : filas) {
            modelo.addRow(fila);
        }
    }

    private void resaltarBoton(String activo) {
        vista.getBtnTodos().setText(activo.equals("todos")     ? "▶ TODOS"    : "TODOS");
        vista.getBtnDesayuno().setText(activo.equals("desayuno") ? "▶ DESAYUNO" : "DESAYUNO");
        vista.getBtnAlmuerzo().setText(activo.equals("almuerzo") ? "▶ ALMUERZO" : "ALMUERZO");
    }
}