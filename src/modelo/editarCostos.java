package src.modelo;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.Set;

import javax.swing.JOptionPane;
import org.json.JSONArray;
import org.json.JSONException;
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

    public static String actualizarTextAreaCF() {
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
                    cf.put("costo", Costo);
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

    public static void actualizarJSON_CV() {
        Path rutaCV = Paths.get("res/data/costosVariables.json").toAbsolutePath();
        Path rutaMenus = Paths.get("res/data/menus.json").toAbsolutePath();

        try {
            JSONArray jsonMenus = new JSONArray(new String(Files.readAllBytes(rutaMenus), StandardCharsets.UTF_8));
            JSONArray jsonCV = Files.exists(rutaCV) ? new JSONArray(new String(Files.readAllBytes(rutaCV), StandardCharsets.UTF_8)) : new JSONArray();

            Set<String> idsExistentes = new HashSet<>();
            for (int i=0; i < jsonCV.length(); i++) {
                idsExistentes.add(jsonCV.getJSONObject(i).getString("dia_turno"));
            }

            boolean huboCambios = false;
            for (int i=0; i < jsonMenus.length(); i++) {
                String idMenu = jsonMenus.getJSONObject(i).getString("dia_turno");

                if (!idsExistentes.contains(idMenu)) {
                    JSONObject nuevoCosto = new JSONObject();
                    nuevoCosto.put("dia_turno", idMenu);
                    nuevoCosto.put("detalles_costos", new JSONObject());

                    jsonCV.put(nuevoCosto);
                    idsExistentes.add(idMenu);
                    huboCambios = true;
                }
            }

            if (huboCambios) {
                Files.write(rutaCV, jsonCV.toString(4).getBytes(StandardCharsets.UTF_8));
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
        }
    }

    public static String getCV(String ID_MENU) {
        Path rutaCV = Paths.get("res/data/costosVariables.json").toAbsolutePath();
        double sumaTotal = 0.0;
        boolean encontrado = false;

        try {
            if (Files.exists(rutaCV)) {
                String contenido = new String(Files.readAllBytes(rutaCV), StandardCharsets.UTF_8);
                if (!contenido.trim().isEmpty()) {
                    JSONArray lista = new JSONArray(contenido);

                    for (int i = 0; i < lista.length(); i++) {
                        JSONObject item = lista.getJSONObject(i);
                        
                        if (item.getString("dia_turno").equals(ID_MENU)) {
                            encontrado = true;
                            
                            if (item.has("detalles_costos")) {
                                JSONObject detalles = item.getJSONObject("detalles_costos");
                                
                                for (String clave : detalles.keySet()) {
                                    sumaTotal += detalles.getDouble(clave);
                                }
                            }
                            break;
                        }
                    }
                }
            }
        } catch (IOException | JSONException e) {
            return "Error: " + e.getMessage();
        }

        if (!encontrado) return "No encontrado";

        return String.format("%.2f Bs", sumaTotal);
    }

    public static String actualizarTextAreaCV(String ID_MENU) {
        Path rutaCV = Paths.get("res/data/costosVariables.json").toAbsolutePath();
        StringBuilder sb = new StringBuilder();
        boolean menuEncontrado = false;

        try {
            if (Files.exists(rutaCV)) {
                String contenido = new String(Files.readAllBytes(rutaCV), StandardCharsets.UTF_8);
                if (!contenido.trim().isEmpty()) {
                    JSONArray listaCV = new JSONArray(contenido);

                    for (int i = 0; i < listaCV.length(); i++) {
                        JSONObject item = listaCV.getJSONObject(i);
                        if (item.getString("dia_turno").equals(ID_MENU)) {
                            menuEncontrado = true;
                            
                            if (item.has("detalles_costos")) {
                                JSONObject detalles = item.getJSONObject("detalles_costos");
                            
                                for (String nombreCosto : detalles.keySet()) {
                                    double monto = detalles.getDouble(nombreCosto);
                                    sb.append(String.format("- %-15s  %.2f Bs\n", nombreCosto, monto));
                                }
                            }
                            break;
                        }
                    }
                }
            }

            if (!menuEncontrado) {
                sb.append("(No hay costos registrados para este menú)");
            }

        } catch (IOException | JSONException e) {
            return "Error al procesar los datos: " + e.getMessage();
        }

        return sb.toString();
    }


    public static void agregarCV(String idMenu, String nombreItem, double costo) {
        Path rutaCV = Paths.get("res/data/costosVariables.json").toAbsolutePath();

        try {
            if (!Files.exists(rutaCV)) {
                JOptionPane.showMessageDialog(null, "El archivo no existe.");
                return;
            }

            String contenido = new String(Files.readAllBytes(rutaCV), StandardCharsets.UTF_8);
            JSONArray listaCV = new JSONArray(contenido);
            boolean encontrado = false;

            for (int i = 0; i < listaCV.length(); i++) {
                JSONObject menuObj = listaCV.getJSONObject(i);

                if (menuObj.getString("dia_turno").equals(idMenu)) {
                    encontrado = true;

                    if (!menuObj.has("detalles_costos")) {
                        menuObj.put("detalles_costos", new JSONObject());
                    }
                    
                    JSONObject detalles = menuObj.getJSONObject("detalles_costos");

                    if (detalles.has(nombreItem)) {
                        JOptionPane.showMessageDialog(null, "Este costo (" + nombreItem + ") ya existe en este menú.");
                        return;
                    }

                    detalles.put(nombreItem, costo);
                    break; 
                }
            }

            if (encontrado) {
                Files.write(rutaCV, listaCV.toString(4).getBytes(StandardCharsets.UTF_8));
                JOptionPane.showMessageDialog(null, "Costo agregado correctamente.");
            } else {
                JOptionPane.showMessageDialog(null, "No se encontró el menú especificado.");
            }

        } catch (IOException | JSONException e) {
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
        }
    }

    public static void editarCV(String idMenu, String nombreItem, double nuevoCosto) {
        Path rutaCV = Paths.get("res/data/costosVariables.json").toAbsolutePath();

        try {
            if (!Files.exists(rutaCV)) {
                JOptionPane.showMessageDialog(null, "El archivo de costos no existe.");
                return;
            }

            String contenido = new String(Files.readAllBytes(rutaCV), StandardCharsets.UTF_8);
            JSONArray listaCV = new JSONArray(contenido);
            boolean actualizado = false;

            for (int i = 0; i < listaCV.length(); i++) {
                JSONObject menuObj = listaCV.getJSONObject(i);

                if (menuObj.getString("dia_turno").equals(idMenu)) {
                    
                    if (menuObj.has("detalles_costos")) {
                        JSONObject detalles = menuObj.getJSONObject("detalles_costos");
                        if (detalles.has(nombreItem)) {
                            detalles.put(nombreItem, nuevoCosto);
                            actualizado = true;
                        }
                    }
                    break;
                }
            }

            if (actualizado) {
                Files.write(rutaCV, listaCV.toString(4).getBytes(StandardCharsets.UTF_8));
            } else {
                JOptionPane.showMessageDialog(null, "No se encontró el ítem '" + nombreItem + "' en el menú: " + idMenu,
                        "Error", JOptionPane.ERROR_MESSAGE);
            }

        } catch (IOException | JSONException e) {
            JOptionPane.showMessageDialog(null, "Error al editar: " + e.getMessage());
        }
    }

    public static void eliminarCV(String idMenu, String nombreItem) {
        Path rutaCV = Paths.get("res/data/costosVariables.json").toAbsolutePath();

        try {
            if (!Files.exists(rutaCV)) return;

            String contenido = new String(Files.readAllBytes(rutaCV), StandardCharsets.UTF_8);
            JSONArray listaCV = new JSONArray(contenido);
            boolean encontrado = false;

            for (int i = 0; i < listaCV.length(); i++) {
                JSONObject menuObj = listaCV.getJSONObject(i);

                if (menuObj.getString("dia_turno").equals(idMenu)) {
                    
                    if (menuObj.has("detalles_costos")) {
                        JSONObject detalles = menuObj.getJSONObject("detalles_costos");


                        if (detalles.has(nombreItem)) {
                            detalles.remove(nombreItem); 
                            encontrado = true;
                        }
                    }
                    break; 
                }
            }

            if(encontrado) {
                Files.write(rutaCV, listaCV.toString(4).getBytes(StandardCharsets.UTF_8));
            } else {
                JOptionPane.showMessageDialog(null, "No se encontró '" + nombreItem + "' en este menú.",
                        "Error", JOptionPane.ERROR_MESSAGE);
            }

        } catch (IOException | JSONException e) {
            JOptionPane.showMessageDialog(null, "Error al eliminar: " + e.getMessage());
        }
    }

}
