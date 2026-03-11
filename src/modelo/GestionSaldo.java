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
public static String ActualizarSaldo(String cedulaPana, String cedulaReacarga, double monto) {
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

        if (emisor == null || receptor == null) return "La cédula ingresada no existe en el sistema.";
        if (monto <= 0) return "El monto debe ser mayor a cero.";

        String rolE = emisor.getString("rol");
        String rolR = receptor.getString("rol");
        String estadoR = receptor.getString("estado");

        if (!rolE.equalsIgnoreCase("Estudiante") && !cedulaPana.equals(cedulaReacarga)) 
            return "Solo puedes recargarte a ti mismo.";
        if (!rolR.equalsIgnoreCase("Estudiante")) 
            return "Solo puedes recargar a otros estudiantes.";
        if (estadoR.equalsIgnoreCase("Exonerado")) 
            return "No puedes recargar a un estudiante exonerado.";

        double saldoViejo = receptor.optDouble("saldo", 0.0);
        receptor.put("saldo", saldoViejo + monto);
        Files.write(ruta, usuarios.toString(4).getBytes(StandardCharsets.UTF_8));
        return null; // éxito

    } catch (Exception e) {
        return "Error interno: " + e.getMessage();
    }
}
}
