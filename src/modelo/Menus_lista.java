package src.modelo;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.swing.*;

import org.json.JSONArray;
import org.json.JSONObject;

public class Menus_lista {

    private static Path rutaMenus = Paths.get("res/data/menus.json").toAbsolutePath();
    private static JSONArray listaMenus;

    private static void cargarDatosMenu(){
        try{
            if(Files.exists(rutaMenus)){
                String contenidoJson = new String(Files.readAllBytes(rutaMenus), StandardCharsets.UTF_8);
                listaMenus = new JSONArray(contenidoJson);
            }else{
                listaMenus = new JSONArray(); 
            }
        }catch(IOException e){
            listaMenus = new JSONArray();
            JOptionPane.showMessageDialog(null, "Error al cargar los datos de los menus ingresados por los administradores previamente: " + e.getMessage(),
                    "Error de Carga", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void mostrarMenu(JTextArea texto_menu, JLabel aforo, String dia, String turno){
    
    cargarDatosMenu();

    StringBuilder construir_frase= new StringBuilder();
    boolean hay_menu=false;
    
    
        
        for(int i=0; i < listaMenus.length();i++){
            JSONObject menu = listaMenus.getJSONObject(i);
            if(menu.getString("dia").equals(dia) && menu.getString("turno").equals(turno)){
                
                construir_frase.append("                      ---------♠ Menú ♠---------").append("\n").append("\n");
                construir_frase.append("     Comida: ").append(menu.getString("comida")).append("\n").append("\n");
                construir_frase.append("     Valor Nutricional: ").append(menu.getString("valorNutricional")).append("\n").append("\n");
                if(aforo != null){
                aforo.setText("Reservas actuales: " + menu.getInt("reservas_actual") + " / " + menu.getInt("aforo_max"));
                }

                hay_menu=true;

                break;
            }
        }

        if(hay_menu == false){
             texto_menu.setText("  No hay menú programado para este turno  ");
             aforo.setText(" Reservas actuales: 0/0 ");
        }else{
           texto_menu.setText(construir_frase.toString()); 
        }
   
    }

    public static JSONObject getMenuData(String dia, String turno) {
    cargarDatosMenu(); 
    
    for (int i = 0; i < listaMenus.length(); i++) {
        JSONObject menu = listaMenus.getJSONObject(i);
        // Compara ignorando mayus/minus por seguridad
        if (menu.getString("dia").equalsIgnoreCase(dia) && 
            menu.getString("turno").equalsIgnoreCase(turno)) {
            return menu;
        }
    }
    return null; 
}

public static void actualizarMenu(String dia, String turno, String comida, String descripcion, String nutricion, String cantBandejas){
try{
    cargarDatosMenu();
    boolean esta_aqui=false;

    for (int i = 0; i < listaMenus.length(); i++) {
        JSONObject menu = listaMenus.getJSONObject(i);
        // Compara ignorando mayus/minus por seguridad
        if (menu.getString("dia").equalsIgnoreCase(dia) && 
            menu.getString("turno").equalsIgnoreCase(turno)) {
            String concatenado=dia+"_"+turno;
            menu.put("comida",comida);
            menu.put("dia",dia);
            menu.put("turno",turno);
            menu.put("descripcion",descripcion);
            menu.put("valorNutricional",nutricion);
            menu.put("reservas_actual",0);
            menu.put("aforo_max",Integer.parseInt(cantBandejas));
            menu.put("dia_turno",concatenado);
            esta_aqui=true;
            break;
        }
    }

    if(!esta_aqui){
        String concatenado=dia+"_"+turno;
        JSONObject menu_nuevo= new JSONObject();
            menu_nuevo.put("comida",comida);
            menu_nuevo.put("dia",dia);
            menu_nuevo.put("turno",turno);
            menu_nuevo.put("descripcion",descripcion);
            menu_nuevo.put("valorNutricional",nutricion);
            menu_nuevo.put("reservas_actual",0);
            menu_nuevo.put("aforo_max",Integer.parseInt(cantBandejas));
            menu_nuevo.put("dia_turno",concatenado);

            listaMenus.put(menu_nuevo);
    }
    Files.write(rutaMenus, listaMenus.toString(4).getBytes(StandardCharsets.UTF_8));
    JOptionPane.showMessageDialog(null,esta_aqui ? "Menú actualizado con éxito":"Menú creado con éxito");
}catch (Exception e){
    JOptionPane.showMessageDialog(null,"error al tratar de actualizar el JSON: "+ e.getMessage());
}

}

}
