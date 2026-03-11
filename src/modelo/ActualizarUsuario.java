package src.modelo;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.swing.JOptionPane;

public class ActualizarUsuario {
    private static Path ruta = Paths.get("res/data/usuarios.json").toAbsolutePath();
    private static JSONArray listaUsuarios;

    private static void cargarDatos(){
        try{
            if(Files.exists(ruta)){
                String contenidoJson = new String(Files.readAllBytes(ruta), StandardCharsets.UTF_8);
                listaUsuarios = new JSONArray(contenidoJson);
            }else{
                listaUsuarios = new JSONArray(); 
            }
        }catch(IOException e){
            listaUsuarios = new JSONArray();
            JOptionPane.showMessageDialog(null, "Error al cargar los datos de los usuarios " + e.getMessage(),
                    "Error de Carga", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static boolean actualizar(String nuevo, String cedula){
        cargarDatos();
        for (int i=0; i < listaUsuarios.length(); i++) {
            JSONObject usuario = listaUsuarios.getJSONObject(i);
            if(usuario.getString("cedula").trim().equals(cedula)){
                
                    usuario.put("cedula", usuario.getString("cedula").trim());
                    usuario.put("nombres", usuario.getString("nombres").trim());
                    usuario.put("apellidos", usuario.getString("apellidos").trim());
                    usuario.put("sexo", usuario.getString("sexo").trim());
                    usuario.put("contraseña", usuario.getString("contraseña").trim());
                    usuario.put("rol", usuario.getString("rol").trim());
                    usuario.put("saldo", usuario.optInt("saldo"));
                    usuario.put("estado", nuevo);

                    try(java.io.FileWriter file=new java.io.FileWriter(ruta.toFile())){
                        file.write(listaUsuarios.toString(4));
                        file.flush();
                        return true;
                    }catch(IOException e){
                        return false;
                    }
                
            }
        }

        return false;
    }

    }
