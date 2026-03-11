package src.modelo;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;



import org.json.JSONArray;
import org.json.JSONObject;

public class GestionSaldo {
 public GestionSaldo(){

 }

 //metodos 
public static boolean ActualizarSaldo(String cedulaPana, String cedulaReacarga, double monto) {
    try {
        Path ruta = Paths.get("res/data/usuarios.json");
        String contenido = new String(Files.readAllBytes(ruta), StandardCharsets.UTF_8);
        JSONArray usuarios = new JSONArray(contenido);

        JSONObject emisor = null;
        JSONObject receptor = null;

        for (int i = 0; i < usuarios.length(); i++) {
            JSONObject u = usuarios.getJSONObject(i);
            if (u.getString("cedula").equals(cedulaPana)) emisor = u;
            if (u.getString("cedula").equals(cedulaReacarga)) receptor = u;
        }

        if (emisor != null && receptor != null) {
            if (monto <= 0) return false;

            String rolE = emisor.getString("rol");
            String estadoE = emisor.getString("estado");

            if (estadoE.equalsIgnoreCase("Exonerado")) return false;

            if (!cedulaPana.equals(cedulaReacarga) && !rolE.equalsIgnoreCase("Estudiante")) {
                return false;
            }

            double saldoViejo = receptor.optDouble("saldo", 0.0);
            receptor.put("saldo", saldoViejo + monto);
            
            Files.write(ruta, usuarios.toString(4).getBytes(StandardCharsets.UTF_8));
            return true;
        }
    } catch (Exception e) {
        System.err.println("Error en recarga: " + e.getMessage());
    }
    return false;
}
}
