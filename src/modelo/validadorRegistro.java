package src.modelo;
import src.util.ValidarUtil;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import org.json.JSONArray;
import org.json.JSONObject;

public class validadorRegistro {

    public static boolean validarRegistro(JTextField nombres, JTextField apellidos, JTextField cedula, JTextField correo, JComboBox<String>  sexo, JComboBox<String>  rol, JPasswordField password, JPasswordField confirmPassword) {  
        StringBuilder errores = new StringBuilder();
        StringBuilder problemas = new StringBuilder();

        
        // Validar nombres
        if(ValidarUtil.campoEstaVacio(nombres, "Nombres")){
            errores.append("- El campo Nombres es obligatorio\n");
        }
        
        if(ValidarUtil.campoEstaVacio(apellidos, "Apellidos")){
            errores.append("- El campo Apellidos es obligatorio\n");
        }
        
        if(ValidarUtil.campoEstaVacio(cedula, "Cédula de identidad")){
            errores.append("- El campo Cédula es obligatorio\n");
        } else if (!ValidarUtil.cedulaEsValida(cedula)) {
            errores.append("- La cédula debe contener solo números\n");
        }
        
        if(ValidarUtil.campoEstaVacio(correo, "Correo Electrónico")){
            errores.append("- El campo Correo Electrónico es obligatorio\n");
        } else if (!ValidarUtil.esEmailValido(correo.getText().trim())) {
            errores.append("- El formato del correo electrónico no es válido (ejemplo: usuario@dominio.com)\n");
        }
        
        if(sexo.getSelectedIndex() == 0){
            errores.append("- Debe seleccionar un Sexo\n");
        }
        
        if(rol.getSelectedIndex() == 0){
            errores.append("- Debe seleccionar un Rol\n");
        }
        
        String password2 = new String(password.getPassword());
        if(ValidarUtil.campoEstaVacio(password, "Contraseña")){
            errores.append("- El campo Contraseña es obligatorio\n");
        }
        
        String confirmPassword2 = new String(confirmPassword.getPassword());
        if(ValidarUtil.campoEstaVacio(confirmPassword, "Confirmar Contraseña")){
            errores.append("- Debe confirmar la contraseña\n");
        }
        
        if(!password2.isEmpty() && !confirmPassword2.isEmpty() && 
            !password2.equals("Contraseña") && !confirmPassword2.equals("Confirmar Contraseña")){
            if(!password2.equals(confirmPassword2)){
                errores.append("- Las contraseñas no coinciden\n");
            }
        }

        if(errores.length() > 0){
            JOptionPane.showMessageDialog(null,  
                "Por favor corrija los siguientes errores:\n\n" + errores.toString(),
                "Errores en el formulario",
                JOptionPane.ERROR_MESSAGE);
            return false;
        }else{
            try{
                Path rutaUsuarios = Paths.get("res/data/usuarios.json").toAbsolutePath();
                JSONArray listaUsuarios = new JSONArray();

                if(Files.exists(rutaUsuarios)){
                    String contenido = new String(Files.readAllBytes(rutaUsuarios), StandardCharsets.UTF_8);
                    if (!contenido.trim().isEmpty()) {
                        listaUsuarios = new JSONArray(contenido);
                    }
                }

                for(int i=0; i<listaUsuarios.length(); i++){
                    JSONObject usuario = listaUsuarios.getJSONObject(i);
                    if(usuario.getString("cedula").trim().equals(cedula.getText().trim())){
                        problemas.append("-  Esta cédula ya se encuentra registrada\n");
                    }
                }

                if(rol.getSelectedItem().toString().equals("Administrador")) {
                    try{
                        Path rutaAdmins = Paths.get("res/data/administradores.json").toAbsolutePath();
                        if (!Files.exists(rutaAdmins)) {
                            problemas.append("- No existe el archivo de administradores autorizados\n\n");
                        }

                        String contenidoAdmins = new String(Files.readAllBytes(rutaAdmins), StandardCharsets.UTF_8);
                        JSONArray listaAdmins = new JSONArray(contenidoAdmins);
                        boolean autorizado = false;
                        
                        for (int i=0; i<listaAdmins.length(); i++) {
                            JSONObject admin = listaAdmins.getJSONObject(i);
                            if (admin.getString("cedula").trim().replaceAll("[^0-9]", "").equals(cedula.getText().trim().replaceAll("[^0-9]", ""))){
                                autorizado = true;
                                break;
                            }
                        }

                        if (!autorizado) {
                            problemas.append("- El usuario a registrar no está en la lista de administradores permitidos\n");
                        }

                    }catch(IOException e){
                        problemas.append("- Error al leer la base de datos de los administradores\n");
                    }
                }

                if(problemas.length()>0){
                    JOptionPane.showMessageDialog(null, "¡Lo sentimos! no se pudo registrar el usuario:\n\n" + problemas.toString(),
                        "Errores en el formulario", JOptionPane.ERROR_MESSAGE);
                        return false;
                }else{
                    JSONObject nuevoUsuario = new JSONObject();
                    nuevoUsuario.put("cedula", cedula.getText().trim());
                    nuevoUsuario.put("nombres", nombres.getText().trim());
                    nuevoUsuario.put("apellidos", apellidos.getText().trim());
                    nuevoUsuario.put("sexo", sexo.getSelectedItem().toString());
                    nuevoUsuario.put("contraseña", password2);
                    nuevoUsuario.put("rol", rol.getSelectedItem().toString());
                    listaUsuarios.put(nuevoUsuario);

                    Files.write(rutaUsuarios, listaUsuarios.toString(4).getBytes(StandardCharsets.UTF_8));
                    return true;
                }
            }catch(IOException e){
                problemas.append("-  Error al guardar los datos del usuario\n");
                return false;
            }
        }
    }
}

    