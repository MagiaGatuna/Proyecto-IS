package src.modelo;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import javax.swing.JOptionPane;
import org.json.JSONArray;
import org.json.JSONObject;

public class editarCostos {

    public static void agregarCF(String Nombre, String Costo){
        Path rutaCF = Paths.get("res/data/costosFijos.json").toAbsolutePath();
        JSONArray listaCF = new JSONArray();
        JSONObject nuevoCF = new JSONObject();

        nuevoCF.put("nombre", Nombre);
        nuevoCF.put("costo", Costo);

        try{
            if(Files.exists(rutaCF)){
                String contenido = new String(Files.readAllBytes(rutaCF), StandardCharsets.UTF_8);
                if(!contenido.trim().isEmpty()){
                    listaCF = new JSONArray(contenido);
                }
            }

            for(int i=0; i<listaCF.length(); i++){
                JSONObject cf = listaCF.getJSONObject(i);
                if(cf.getString("nombre").trim().equalsIgnoreCase(Nombre.trim())){
                    JOptionPane.showMessageDialog(null, "Este costo fijo ya se encuentra registrado",
                        "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
            }

            listaCF.put(nuevoCF);
            Files.write(rutaCF, listaCF.toString(4).getBytes(StandardCharsets.UTF_8));

        }catch (IOException e){
            JOptionPane.showMessageDialog(null, "Error al acceder a la base de datos de costos fijos: " + e.getMessage(),
                        "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void editarCF(String Nombre, String Costo){
        Path rutaCF = Paths.get("res/data/costosFijos.json").toAbsolutePath();
        JSONArray listaCF = new JSONArray();

        try{
            if(Files.exists(rutaCF)){
                String contenido = new String(Files.readAllBytes(rutaCF), StandardCharsets.UTF_8);
                if(!contenido.trim().isEmpty()){
                    listaCF = new JSONArray(contenido);
                }
            }

            for(int i=0; i < listaCF.length(); i++){
                JSONObject cf = listaCF.getJSONObject(i);
                if(cf.getString("nombre").trim().equalsIgnoreCase(Nombre.trim())){
                    cf.put("costo", Costo); // Aquí se reemplaza el valor anterior
                    Files.write(rutaCF, listaCF.toString(4).getBytes(StandardCharsets.UTF_8));
                    JOptionPane.showMessageDialog(null, "Costo actualizado correctamente");
                    return;
                }
            }
            JOptionPane.showMessageDialog(null, "Este costo fijo no se encuentra registrado",
                        "Error", JOptionPane.ERROR_MESSAGE);

        }catch(IOException e){
            JOptionPane.showMessageDialog(null, "Error al editar la base de datos de costos fijos: " + e.getMessage(),
                        "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void eliminarCF(String Nombre){
        Path rutaCF = Paths.get("res/data/costosFijos.json").toAbsolutePath();
        JSONArray listaCF = new JSONArray();

        try{
            if(Files.exists(rutaCF)){
                String contenido = new String(Files.readAllBytes(rutaCF), StandardCharsets.UTF_8);
                if(!contenido.trim().isEmpty()){
                    listaCF = new JSONArray(contenido);
                }
            }

            boolean encontrado=false;
            for(int i=0; i < listaCF.length(); i++){
                JSONObject cf = listaCF.getJSONObject(i);
                if(cf.getString("nombre").trim().equalsIgnoreCase(Nombre.trim())){
                    listaCF.remove(i);
                    encontrado = true;
                    break;
                }
            }

            if(encontrado){
                Files.write(rutaCF, listaCF.toString(4).getBytes(StandardCharsets.UTF_8));
            }else{
                JOptionPane.showMessageDialog(null, "No se encontró el costo fijo para eliminar",
                        "Error", JOptionPane.ERROR_MESSAGE);
            }

        }catch(IOException e){
            JOptionPane.showMessageDialog(null, "Error al acceder a la base de datos de costos fijos: " + e.getMessage(),
                        "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
