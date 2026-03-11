package src.modelo;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import org.json.JSONArray;
import org.json.JSONObject;

public class UsuarioDAO {
    private static final Path RUTA_USUARIOS = Paths.get("res/data/usuarios.json").toAbsolutePath();

    public static Usuario buscarPorCedula(String cedula) {
        try {
            if (!Files.exists(RUTA_USUARIOS)) return null;
            String contenido = new String(Files.readAllBytes(RUTA_USUARIOS), StandardCharsets.UTF_8);
            if (contenido.trim().isEmpty()) return null;
            JSONArray lista = new JSONArray(contenido);
            for (int i = 0; i < lista.length(); i++) {
                JSONObject obj = lista.getJSONObject(i);
                if (obj.getString("cedula").trim().equals(cedula)) {
                    String nombre = obj.getString("nombres");
                    double saldo = obj.optDouble("saldo", 0.0); 
                    String rol = obj.getString("rol");
                    String estado = obj.optString("estado", "Regular");
                    return new Usuario(nombre, saldo, rol, cedula, null, estado);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static boolean actualizarSaldo(String cedula, double nuevoSaldo) {
    try {
        if (!Files.exists(RUTA_USUARIOS)) return false;
        String contenido = new String(Files.readAllBytes(RUTA_USUARIOS), StandardCharsets.UTF_8);
        if (contenido.trim().isEmpty()) return false;
        JSONArray lista = new JSONArray(contenido);
        boolean encontrado = false;
        for (int i = 0; i < lista.length(); i++) {
            JSONObject obj = lista.getJSONObject(i);
            if (obj.getString("cedula").trim().equals(cedula)) {
                obj.put("saldo", nuevoSaldo);
                encontrado = true;
                break;
            }
        }
        if (encontrado) {
            Files.write(RUTA_USUARIOS, lista.toString(4).getBytes(StandardCharsets.UTF_8));
            return true;
        }
    } catch (IOException e) {
        e.printStackTrace();
    }
    return false;
    }
}