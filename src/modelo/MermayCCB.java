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

public class MermayCCB {
    public static String getCCB(String ID_MENU) {
        String cvStr = src.modelo.editarCostos.getCV(ID_MENU);
        String cfStr = src.modelo.editarCostos.getCF();

        if (cvStr.equals("No encontrado") || cvStr.startsWith("Error") ||
            cfStr.startsWith("Error")) {
            return "Error";
        }

        double CV = Double.parseDouble(cvStr.replace("Bs", "").trim());
        double CF = Double.parseDouble(cfStr.replace("Bs", "").trim());

        int NB = 1; 
        double Merma = getMerma(ID_MENU).replace("%", "").trim().equals("No encontrado") ? 0.0 :
                        Double.parseDouble(getMerma(ID_MENU).replace("%", "").trim()) / 100.0;
        double CBB = ((CV+CF)/NB)*(1+Merma);
        return String.format("%.2f Bs", CBB);
    }

    public static void actualizarJSON_CCB() {
        Path rutaCBB = Paths.get("res/data/Merma.json").toAbsolutePath();
        Path rutaMenus = Paths.get("res/data/menus.json").toAbsolutePath();

        try {
            JSONArray jsonMenus = new JSONArray(new String(Files.readAllBytes(rutaMenus), StandardCharsets.UTF_8));
            JSONArray jsonCBB = Files.exists(rutaCBB) ? new JSONArray(new String(Files.readAllBytes(rutaCBB), StandardCharsets.UTF_8)) : new JSONArray();

            Set<String> idsEnMenus = new HashSet<>();
            for (int i = 0; i < jsonMenus.length(); i++) {
                idsEnMenus.add(jsonMenus.getJSONObject(i).getString("dia_turno"));
            }

            Set<String> idsExistentes = new HashSet<>();
            for (int i = 0; i < jsonCBB.length(); i++) {
                idsExistentes.add(jsonCBB.getJSONObject(i).getString("dia_turno"));
            }

            boolean huboCambios = false;

            for (int i = 0; i < jsonMenus.length(); i++) {
                String idMenu = jsonMenus.getJSONObject(i).getString("dia_turno");
                if (!idsExistentes.contains(idMenu)) {
                    JSONObject nuevoCosto = new JSONObject();
                    nuevoCosto.put("dia_turno", idMenu);
                    nuevoCosto.put("merma",0.0);
                    jsonCBB.put(nuevoCosto);
                    idsExistentes.add(idMenu);
                    huboCambios = true;
                }
            }

            JSONArray jsonCBBFiltrado = new JSONArray();
            for (int i = 0; i < jsonCBB.length(); i++) {
                JSONObject item = jsonCBB.getJSONObject(i);
                if (idsEnMenus.contains(item.getString("dia_turno"))) {
                    jsonCBBFiltrado.put(item);
                } else {
                    huboCambios = true;
                }
            }

            if (huboCambios) {
                Files.write(rutaCBB, jsonCBBFiltrado.toString(4).getBytes(StandardCharsets.UTF_8));
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
        }
    }

    public static String getMerma(String ID_MENU) {
        Path rutaCV = Paths.get("res/data/Merma.json").toAbsolutePath();
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
                            return String.format("%.2f%%", item.getDouble("merma"));
                        }
                    }
                }
            }
        } catch (IOException | JSONException e) {
            return "Error: " + e.getMessage();
        }

        if (!encontrado) return "No encontrado";
        return "Error desconocido";
    }

    public static void editarMerma(String idMenu, double nuevaMerma) {
        Path rutaCBB = Paths.get("res/data/Merma.json").toAbsolutePath();

        try {
            if (!Files.exists(rutaCBB)) {
                JOptionPane.showMessageDialog(null, "El archivo de Merma no existe.");

                return;
            }

            String contenido = new String(Files.readAllBytes(rutaCBB), StandardCharsets.UTF_8);
            JSONArray listaCBB = new JSONArray(contenido);
            boolean actualizado = false;

            for (int i = 0; i < listaCBB.length(); i++) {
                JSONObject menuObj = listaCBB.getJSONObject(i);

                if (menuObj.getString("dia_turno").equals(idMenu)) {
                    
                    if (menuObj.has("merma")) {
                        menuObj.put("merma", nuevaMerma);
                        actualizado = true;
                    }
                    break;
                }
            }

            if (actualizado) {
                Files.write(rutaCBB, listaCBB.toString(4).getBytes(StandardCharsets.UTF_8));
            } else {
                JOptionPane.showMessageDialog(null, "No se encontró el menú: " + idMenu,
                        "Error", JOptionPane.ERROR_MESSAGE);
            }

        } catch (IOException | JSONException e) {
            JOptionPane.showMessageDialog(null, "Error al editar: " + e.getMessage());
        }
    }
}
