package src.modelo;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import javax.swing.JOptionPane;
import org.json.JSONArray;
import org.json.JSONObject;

public class ReservaDAO {
    private static final Path RUTA = Paths.get("res/data/reservas.json").toAbsolutePath();

    public static Reserva buscarPorCedula(String cedula, String diaTurno) {
        try {
            if (!Files.exists(RUTA)) {
                return null;
            }
            String contenido = new String(Files.readAllBytes(RUTA), StandardCharsets.UTF_8);
            if (contenido.trim().isEmpty()) {
                return null;
            }
            JSONArray lista = new JSONArray(contenido);
            for (int i = 0; i < lista.length(); i++) {
                JSONObject obj = lista.getJSONObject(i);
                if (obj.getString("cedula").equals(cedula) && obj.getString("dia_turno").equals(diaTurno)) {
                    String fecha = obj.has("fecha_exacta") ? obj.getString("fecha_exacta") : "";
                    return new Reserva(cedula, obj.getString("dia_turno"), fecha);
                }
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error al leer reservas: " + e.getMessage());
        }
        return null;
    }


    public static void eliminarPorCedula(String cedula, String idMenu) {
        try {
            if (!Files.exists(RUTA)) {
                return;
            }
            String contenido = new String(Files.readAllBytes(RUTA), StandardCharsets.UTF_8);
            JSONArray lista = new JSONArray(contenido);
            JSONArray nuevaLista = new JSONArray();
            for (int i = 0; i < lista.length(); i++) {
                JSONObject obj = lista.getJSONObject(i);
                if (!obj.getString("cedula").equals(cedula)||!obj.getString("dia_turno").equals(idMenu)) {
                    nuevaLista.put(obj);
                }
            }
            // Si la lista cambió, guardamos
            if (nuevaLista.length() != lista.length()) {
                Files.write(RUTA, nuevaLista.toString(4).getBytes(StandardCharsets.UTF_8));
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error al eliminar reserva: " + e.getMessage());
        }
    }

    public static void guardar(Reserva reserva) {
    try {
        JSONArray lista;
        if (Files.exists(RUTA)) {
            String contenido = new String(Files.readAllBytes(RUTA), StandardCharsets.UTF_8);
            lista = new JSONArray(contenido);
        } else {
            lista = new JSONArray();
        }

        JSONObject nueva = new JSONObject();
        nueva.put("cedula", reserva.getCedula());
        nueva.put("dia_turno", reserva.getDiaTurno());
        nueva.put("fecha_exacta", reserva.getFechaExacta());
        lista.put(nueva);

        Files.write(RUTA, lista.toString(4).getBytes(StandardCharsets.UTF_8));
    } catch (IOException e) {
        JOptionPane.showMessageDialog(null, "Error al guardar reserva: " + e.getMessage());
    }}

// Modifica el método en ReservaDAO.java
    public static boolean limpiarYVerificarUsuario(String cedulaUsuario) {
        boolean usuarioAfectado = false;
        try {
            if (!Files.exists(RUTA)) return false;
            String contenido = new String(Files.readAllBytes(RUTA), StandardCharsets.UTF_8);
            JSONArray listaOriginal = new JSONArray(contenido);
            JSONArray listaNueva = new JSONArray();

            for (int i = 0; i < listaOriginal.length(); i++) {
                JSONObject res = listaOriginal.getJSONObject(i);
                String fechaReserva = res.optString("fecha_exacta", "");
                String idMenu = res.getString("dia_turno");
                String cedulaReserva = res.getString("cedula");

                if (!fechaReserva.isEmpty() && src.util.Calcular_dia.isFechaPasada(fechaReserva)) {
                    src.modelo.Menus_lista.decrementarReserva(idMenu);
                    
                    if (cedulaReserva.equals(cedulaUsuario)) {
                        usuarioAfectado = true;
                    }
                } else {
                    // Si no ha pasado, la mantenemos en el sistema
                    listaNueva.put(res);
                }
            }

            Files.write(RUTA, listaNueva.toString(4).getBytes(StandardCharsets.UTF_8));
        } catch (Exception e) {
            System.out.println("Error en limpieza: " + e.getMessage());
        }
        return usuarioAfectado; // Nos dice si el usuario que entro perdió su reserva
    }
}

