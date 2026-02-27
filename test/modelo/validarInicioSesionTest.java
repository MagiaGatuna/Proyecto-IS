package test.modelo;

import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.hamcrest.MatcherAssert.assertThat;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.hamcrest.CoreMatchers.is;

import javax.swing.JPasswordField;
import javax.swing.JTextField;

import src.modelo.validadorInicioS;


public class validarInicioSesionTest{

    private JTextField cedula_id;
    private JPasswordField contrasena;
    private final Path rutaUsuarios = Paths.get("res/data/usuarios.json").toAbsolutePath();

    @BeforeEach
    void Arrange(){
        cedula_id = new JTextField();
        contrasena = new JPasswordField();
    }

    @Test
    void testInicioSesion_CedulaVacia(){
        //Arrange
        cedula_id.setText("");
        contrasena.setText("password123");
        //Act y Assert
        assertThat(validadorInicioS.validarInicioSesion(cedula_id, contrasena), is(false));
    }


    @Test
    void testInicioSesion_CedulaInvalida(){
        //Arrange
        cedula_id.setText("12@AB#");
        contrasena.setText("password123");
        //Act y Assert
        assertThat(validadorInicioS.validarInicioSesion(cedula_id, contrasena), is(false));
    }

    @Test
    void testInicioSesion_ContrasenaVacia(){
        //Arrange
        cedula_id.setText("12345678");
        contrasena.setText("");
        //Act y Assert
        assertThat(validadorInicioS.validarInicioSesion(cedula_id, contrasena), is(false));
    }

    @Test
    void testInicioSesion_UsuarioNoRegistrado(){
        //Arrange
        cedula_id.setText("99999999");
        contrasena.setText("password123");
        //Act y Assert
        assertThat(validadorInicioS.validarInicioSesion(cedula_id, contrasena), is(false));
    }


    @Test
    void testInicioSesion_AmbosVacios(){
        //Arrange
        cedula_id.setText("");
        contrasena.setText("");
        //Act y Assert
        assertThat(validadorInicioS.validarInicioSesion(cedula_id, contrasena), is(false));
    }


    @BeforeEach
    void agregarUsuarioPrueba() throws IOException{
        String contenido = new String(Files.readAllBytes(rutaUsuarios), StandardCharsets.UTF_8);
        JSONArray lista = new JSONArray(contenido);

        for(int i=0; i < lista.length(); i++){
            if (lista.getJSONObject(i).getString("cedula").equals("4444")) return;
        }

        JSONObject artemis = new JSONObject();
        artemis.put("cedula", "4444");
        artemis.put("nombres", "Artemis");
        artemis.put("apellidos", "Fowl");
        artemis.put("sexo", "Masculino");
        artemis.put("rol", "Estudiante");
        artemis.put("contraseña", "MindSpy");
        artemis.put("saldo", 0.0);

        lista.put(artemis);
        Files.write(rutaUsuarios, lista.toString(4).getBytes(StandardCharsets.UTF_8));
    }

    @AfterEach
    void limpiarUsuarioPrueba() throws IOException{
        String contenido = new String(Files.readAllBytes(rutaUsuarios), StandardCharsets.UTF_8);
        JSONArray lista = new JSONArray(contenido);
        JSONArray listaLimpia = new JSONArray();

        for (int i=0; i < lista.length(); i++) {
            if (!lista.getJSONObject(i).getString("cedula").equals("4444")) {
                listaLimpia.put(lista.getJSONObject(i));
            }
        }

        Files.write(rutaUsuarios, listaLimpia.toString(4).getBytes(StandardCharsets.UTF_8));
    }

    @Test
    void testInicioSesion_Exitoso(){
        //Arrange
        cedula_id.setText("4444");
        contrasena.setText("MindSpy");
        //Act y Assert
        assertThat(validadorInicioS.validarInicioSesion(cedula_id, contrasena), is(true));
    }

    @Test
    void testInicioSesion_ContrasenaIncorrecta(){
        //Arrange
        cedula_id.setText("4444");
        contrasena.setText("Butler");   
        //Act y Assert
        assertThat(validadorInicioS.validarInicioSesion(cedula_id, contrasena), is(false));
    }

}