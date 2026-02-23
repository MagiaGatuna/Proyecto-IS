package src.modelo;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.swing.JTextField;

import org.json.JSONArray;
import org.json.JSONObject;

public class GestionSaldo {
 public GestionSaldo(){

 }

 //metodos 
 public static boolean ActualizarSaldo(String cedulaUsuario, double montoRecarga) {
    try {
        Path ruta = Paths.get("res/data/usuarios.json");
        String contenido = new String(Files.readAllBytes(ruta), StandardCharsets.UTF_8);
        JSONArray usuarios = new JSONArray(contenido);

        for (int i = 0; i < usuarios.length(); i++) {
            JSONObject user = usuarios.getJSONObject(i);
            
            // Buscamos coincidencia con el usuario que tiene la sesión iniciada
            if (user.getString("cedula").equals(cedulaUsuario)) {
                double saldoActual = user.optDouble("saldo", 0.0);
                user.put("saldo", saldoActual + montoRecarga); // Sumamos el nuevo monto
                
                // Guardamos el archivo con formato 
                Files.write(ruta, usuarios.toString(4).getBytes(StandardCharsets.UTF_8));
                return true; 
            }
        }
    } catch (Exception e) {
        System.err.println("Error al actualizar saldo: " + e.getMessage());
    }
    return false;
}
}
