package src.modelo;
import src.util.ValidarUtil;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import org.json.JSONArray;
import org.json.JSONObject;

public class validadorInicioS {

    private static Path rutaRaiz = Paths.get("res/data/usuarios.json").toAbsolutePath();
    private static JSONArray listaUsuarios;
    private static int indiceUsuarioEncontrado = -1;

    private static void cargarDatos(){
        try{
            if(Files.exists(rutaRaiz)){
                String contenidoJson = new String(Files.readAllBytes(rutaRaiz), StandardCharsets.UTF_8);
                listaUsuarios = new JSONArray(contenidoJson);
            }else{
                listaUsuarios = new JSONArray(); 
            }
        }catch(IOException e){
            listaUsuarios = new JSONArray();
            JOptionPane.showMessageDialog(null, "Error al cargar los datos de usuarios: " + e.getMessage(),
                    "Error de Carga", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static boolean validarInicioSesion(JTextField cedula_id, JPasswordField contraseña){
        cargarDatos();
        
        StringBuilder errores = new StringBuilder();
        StringBuilder problemas = new StringBuilder();

        if(ValidarUtil.campoEstaVacio(cedula_id, "Cédula de identidad")){
            errores.append("- El campo Cédula es obligatorio\n\n");
        }else if(!ValidarUtil.cedulaEsValida(cedula_id)){
            errores.append("- La cédula debe contener solo números\n");
        }

        if (ValidarUtil.campoEstaVacio(contraseña, "Contraseña")){
            errores.append("- El campo Contraseña es obligatorio\n\n");
        }

        if(errores.length()>0){
            JOptionPane.showMessageDialog(null, "Por favor corrija los siguientes errores:\n\n" + errores.toString(),
                    "Errores en el formulario", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        String cedulaInput = cedula_id.getText().trim();
        String passInput = String.valueOf(contraseña.getPassword());
        
        indiceUsuarioEncontrado = -1;

        for (int i=0; i < listaUsuarios.length(); i++) {
            JSONObject usuario = listaUsuarios.getJSONObject(i);
            if(usuario.getString("cedula").trim().equals(cedulaInput)){
                if(usuario.getString("contraseña").equals(passInput)){
                    indiceUsuarioEncontrado = i;
                    return true;
                }else{
                    problemas.append("- Contraseña incorrecta\n\n");
                    break; 
                }
            }
        }

        if(indiceUsuarioEncontrado == -1 && problemas.length() == 0){
            problemas.append("- Usuario no registrado\n\n");
        }

        JOptionPane.showMessageDialog(null, "¡Lo sentimos! no se pudo iniciar sesión:\n\n" + problemas.toString(),
                "Error de Autenticación", JOptionPane.ERROR_MESSAGE);
        return false;
    }

    public static String getRol(){
            return listaUsuarios.getJSONObject(indiceUsuarioEncontrado).getString("rol");
    }
public static Usuario getUsuarioActual() {
    if (indiceUsuarioEncontrado != -1) {
        JSONObject datos = listaUsuarios.getJSONObject(indiceUsuarioEncontrado);
        
        // CAMBIO: Usar "nombres" en lugar de "nombre"
        String nombreReal = datos.getString("nombres");
        
        // CAMBIO: Si el saldo no existe en el JSON, le ponemos 0.0 por defecto
        double saldoReal = datos.has("saldo") ? datos.getDouble("saldo") : 0.0;
        
        return new Usuario(
            nombreReal, 
            saldoReal,
            datos.getString("rol")
        );
    }
    return null;
}
}