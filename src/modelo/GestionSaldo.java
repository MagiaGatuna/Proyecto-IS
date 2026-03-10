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

        // 1. Buscamos al que paga y al que recibe en el JSON
        for (int i = 0; i < usuarios.length(); i++) {
            JSONObject u = usuarios.getJSONObject(i);
            if (u.getString("cedula").equals(cedulaPana)) emisor = u;
            if (u.getString("cedula").equals(cedulaReacarga)) receptor = u;
        }

        // 2. Si conseguimos a los dos, chequeamos las reglas
        if (emisor != null && receptor != null) {
            
            if (monto <= 0) return false;

            // REGLA: El pana que paga debe ser Estudiante y NO estar Exonerado
            // (Si no es Estudiante o si es Exonerado, rebota el pago)
            if (!emisor.getString("rol").equalsIgnoreCase("Estudiante") || 
                emisor.getString("estado").equalsIgnoreCase("Exonerado")) {
                return false; 
            }

            // 3. Todo fino, le sumamos la plata al usuario de la sesión (receptor)
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
