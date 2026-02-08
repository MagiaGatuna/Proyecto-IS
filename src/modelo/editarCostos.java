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

    public static String getCF(){
        Path rutaCF = Paths.get("res/data/costosFijos.json").toAbsolutePath();
        double sumaTotal = 0.0;

        try{
            if(Files.exists(rutaCF)){
                String contenido = new String(Files.readAllBytes(rutaCF), StandardCharsets.UTF_8);
                if(!contenido.trim().isEmpty()){
                    JSONArray lista = new JSONArray(contenido);

                    for(int i = 0; i < lista.length(); i++){
                        JSONObject cf = lista.getJSONObject(i);
                        sumaTotal += cf.getDouble("costo");
                    }
                }
            }
        }catch(IOException e){
            return "Error";
        }

        return String.format("%.2f  Bs", sumaTotal);
    }

    public static String actualizarTextArea() {
        Path rutaCF=Paths.get("res/data/costosFijos.json").toAbsolutePath();
        StringBuilder sb = new StringBuilder();

        try{
            if(Files.exists(rutaCF)){
                String contenido = new String(Files.readAllBytes(rutaCF), StandardCharsets.UTF_8);
                if(!contenido.trim().isEmpty()){
                    JSONArray listaCF = new JSONArray(contenido);

                    for(int i=0; i < listaCF.length(); i++){
                        JSONObject cf = listaCF.getJSONObject(i);
                        String nombre = cf.getString("nombre");
                        
                        double costo = cf.getDouble("costo"); 

                        sb.append(String.format("- %-15s  %.2f Bs \n", nombre, costo));
                    }
                }else{
                    sb.append("(No existen costos fijos registrados)");
                }
            }else{
                sb.append("(No se encontró el archivo de datos de costos fijos)");
            }
        }catch(IOException e){
            return "Error al leer los datos: " + e.getMessage();
        }

        return sb.toString();
    }


    public static void agregarCF(String Nombre, double Costo){
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

    public static void editarCF(String Nombre, double Costo){
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
