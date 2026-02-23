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

    public static Reserva buscarPorCedula(String cedula) {
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
                if (obj.getString("cedula").equals(cedula)) {
                    return new Reserva(cedula, obj.getString("dia_turno"));
                }
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error al leer reservas: " + e.getMessage());
        }
        return null;
    }


    public static void eliminarPorCedula(String cedula) {
        try {
            if (!Files.exists(RUTA)) {
                return;
            }
            String contenido = new String(Files.readAllBytes(RUTA), StandardCharsets.UTF_8);
            JSONArray lista = new JSONArray(contenido);
            JSONArray nuevaLista = new JSONArray();
            for (int i = 0; i < lista.length(); i++) {
                JSONObject obj = lista.getJSONObject(i);
                if (!obj.getString("cedula").equals(cedula)) {
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
}