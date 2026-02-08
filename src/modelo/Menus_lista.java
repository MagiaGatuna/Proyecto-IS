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
    private static int indiceMenuEncontrado = -1;

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
    int aforo_actual=0;
    int aforo_max=0;
    boolean hay_menu=false;
    
    
        
        for(int i=0; i < listaMenus.length();i++){
            JSONObject menu = listaMenus.getJSONObject(i);
            if(menu.getString("dia").equalsIgnoreCase(dia) && menu.getString("turno").equalsIgnoreCase(turno)){
                construir_frase.append("---------♠ Menu ♠---------").append("\n");
                construir_frase.append("Comida: ").append(menu.getString("comida")).append("\n");
                construir_frase.append("Valor Nutricional: ").append(menu.getString("valorNutricional")).append("\n");
                construir_frase.append("Precio: ").append(menu.getInt("precio")).append(" Bs \n");

                aforo.setText("Reservas actuales: " + menu.getInt("reservas_actual") + " / " + menu.getInt("aforo_max"));

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


}
