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

import javax.swing.JComboBox;
import javax.swing.JPasswordField;
import javax.swing.JTextField;


import src.modelo.validadorRegistro;

public class validarRegistroTest {

    private JTextField nombres;
    private JTextField apellidos;
    private JTextField cedula;
    private JTextField correo;
    private JComboBox<String> sexo;
    private JComboBox<String> rol;
    private JPasswordField password;
    private JPasswordField confirmPassword;

    private final Path rutaUsuarios = Paths.get("res/data/usuarios.json").toAbsolutePath();
    private final Path rutaUCV = Paths.get("res/data/BaseDeDatosUCV.json").toAbsolutePath();

    @BeforeEach
    void Arrange(){
        nombres = new JTextField();
        apellidos = new JTextField();
        cedula = new JTextField();
        correo = new JTextField();
        sexo = new JComboBox<>(new String[]{"", "Masculino", "Femenino"});
        rol = new JComboBox<>(new String[]{"", "Estudiante", "Profesor", "Empleado"});
        password = new JPasswordField();
        confirmPassword = new JPasswordField();
    }

    @Test
    void testRegistro_NombresVacio(){
        //Arrange
        nombres.setText("");
        apellidos.setText("Folw");
        cedula.setText("12345678");
        correo.setText("Artemis@test.com");
        sexo.setSelectedIndex(1);
        rol.setSelectedIndex(1);
        password.setText("MindSpy");
        confirmPassword.setText("MindSpy");
        //Act y Assert
        assertThat(validadorRegistro.validarRegistro(nombres, apellidos, cedula, correo, sexo, rol, password, confirmPassword), is(false));
    }

    @Test
    void testRegistro_ApellidosVacio(){
        //Arrange
        nombres.setText("Artemis");
        apellidos.setText("");
        cedula.setText("12345678");
        correo.setText("Artemis@test.com");
        sexo.setSelectedIndex(1);
        rol.setSelectedIndex(1);
        password.setText("MindSpy");
        confirmPassword.setText("MindSpy");
        //Act y Assert
        assertThat(validadorRegistro.validarRegistro(nombres, apellidos, cedula, correo, sexo, rol, password, confirmPassword), is(false));
    }


    @Test
    void testRegistro_CorreoSinArroba() {
        //Arrange
        nombres.setText("Artemis");
        apellidos.setText("Folw");
        cedula.setText("12345678");
        correo.setText("Artemis.com"); 
        sexo.setSelectedIndex(1);
        rol.setSelectedIndex(1);
        password.setText("MindSpy");
        confirmPassword.setText("MindSpy");
        //Act y Assert
        assertThat(validadorRegistro.validarRegistro(nombres, apellidos, cedula, correo, sexo, rol, password, confirmPassword), is(false));
    }

    @Test
    void testRegistro_CorreoSinPunto() {
        //Arrange
        nombres.setText("Artemis");
        apellidos.setText("Folw");
        cedula.setText("12345678");
        correo.setText("Artemis@test");      
        sexo.setSelectedIndex(1);
        rol.setSelectedIndex(1);
        password.setText("MindSpy");
        confirmPassword.setText("MindSpy");
        //Act y Assert
        assertThat(validadorRegistro.validarRegistro(nombres, apellidos, cedula, correo, sexo, rol, password, confirmPassword), is(false));
    }

    @Test
    void testRegistro_CorreoPuntoAntesDeArroba() {
        //Arrange
        nombres.setText("Artemis");
        apellidos.setText("Folw");
        cedula.setText("12345678");
        correo.setText("Artemis.test@com");
        sexo.setSelectedIndex(1);
        rol.setSelectedIndex(1);
        password.setText("MindSpy");
        confirmPassword.setText("MindSpy");
        //Act y Assert
        assertThat(validadorRegistro.validarRegistro(nombres, apellidos, cedula, correo, sexo, rol, password, confirmPassword), is(false));
    }

    @Test
    void testRegistro_SexoNoSeleccionado() {
        //Arrange
        nombres.setText("Artemis");
        apellidos.setText("Folw");
        cedula.setText("12345678");
        correo.setText("Artemis@test.com");
        sexo.setSelectedIndex(0); 
        rol.setSelectedIndex(1);
        password.setText("MindSpy");
        confirmPassword.setText("MindSpy");
        //Act y Assert
        assertThat(validadorRegistro.validarRegistro(nombres, apellidos, cedula, correo, sexo, rol, password, confirmPassword), is(false));
    }

    @Test
    void testRegistro_RolNoSeleccionado() {
        //Arrange
        nombres.setText("Artemis");
        apellidos.setText("Folw");
        cedula.setText("12345678");
        correo.setText("Artemis@test.com");
        sexo.setSelectedIndex(1);
        rol.setSelectedIndex(0);       
        password.setText("MindSpy");
        confirmPassword.setText("MindSpy");
        //Act y Assert
        assertThat(validadorRegistro.validarRegistro(nombres, apellidos, cedula, correo, sexo, rol, password, confirmPassword), is(false));
    }

    @Test
    void testRegistro_ContrasenasNoCoinciden() {
        //Arrange
        nombres.setText("Artemis");
        apellidos.setText("Folw");
        cedula.setText("12345678");
        correo.setText("Artemis@test.com");
        sexo.setSelectedIndex(1);
        rol.setSelectedIndex(1);
        password.setText("MindSpy");
        confirmPassword.setText("MindSpy2");
        //Act y Assert
        assertThat(validadorRegistro.validarRegistro(nombres, apellidos, cedula, correo, sexo, rol, password, confirmPassword), is(false));
    }


    @Test
    void testRegistro_TodosLosCamposVacios() {
        //Arrange
        nombres.setText("");
        apellidos.setText("");
        cedula.setText("");
        correo.setText("");
        sexo.setSelectedIndex(0);
        rol.setSelectedIndex(0);
        password.setText("");
        confirmPassword.setText("");
        //Act and Assert
        assertThat(validadorRegistro.validarRegistro(nombres, apellidos, cedula, correo, sexo, rol, password, confirmPassword), is(false));
    }

    @Test
    void testRegistro_AmbasContrasenasVacias() {
        //Arrange
        nombres.setText("Artemis");
        apellidos.setText("Folw");
        cedula.setText("12345678");
        correo.setText("Artemis@test.com");
        sexo.setSelectedIndex(1);
        rol.setSelectedIndex(1);
        password.setText("");
        confirmPassword.setText("");
        //Act and Assert
        assertThat(validadorRegistro.validarRegistro(nombres, apellidos, cedula, correo, sexo, rol, password, confirmPassword), is(false));
    }

    @BeforeEach
    void agregarCedulaPrueba() throws IOException {
        String contenidoUCV = new String(Files.readAllBytes(rutaUCV), StandardCharsets.UTF_8);
        JSONArray listaUCV = new JSONArray(contenidoUCV);

        for (int i=0; i < listaUCV.length(); i++) {
            if (listaUCV.getJSONObject(i).getString("cedula").equals("4444")) return;
        }

        JSONObject cedulaPrueba = new JSONObject();
        cedulaPrueba.put("cedula", "4444");
        cedulaPrueba.put("rol", "Estudiante");
        listaUCV.put(cedulaPrueba);

        Files.write(rutaUCV, listaUCV.toString(4).getBytes(StandardCharsets.UTF_8));
    }

    @AfterEach
    void limpiarDatosPrueba() throws IOException {
        String contenidoUCV = new String(Files.readAllBytes(rutaUCV), StandardCharsets.UTF_8);
        JSONArray listaUCV = new JSONArray(contenidoUCV);
        JSONArray ucvLimpia = new JSONArray();
        for (int i = 0; i < listaUCV.length(); i++) {
            if (!listaUCV.getJSONObject(i).getString("cedula").equals("4444")) {
                ucvLimpia.put(listaUCV.getJSONObject(i));
            }
        }
        Files.write(rutaUCV, ucvLimpia.toString(4).getBytes(StandardCharsets.UTF_8));

        String contenidoUsuarios = new String(Files.readAllBytes(rutaUsuarios), StandardCharsets.UTF_8);
        JSONArray listaUsuarios = new JSONArray(contenidoUsuarios);
        JSONArray usuariosLimpia = new JSONArray();
        for (int i=0; i < listaUsuarios.length(); i++) {
            if (!listaUsuarios.getJSONObject(i).getString("cedula").equals("4444")) {
                usuariosLimpia.put(listaUsuarios.getJSONObject(i));
            }
        }
        Files.write(rutaUsuarios, usuariosLimpia.toString(4).getBytes(StandardCharsets.UTF_8));
    }

    @Test
    void testRegistro_Exitoso() {
        //Arrange
        nombres.setText("Artemis");
        apellidos.setText("Fowl");
        cedula.setText("4444");
        correo.setText("Artemis@fowl.com");
        sexo.setSelectedIndex(1);              
        rol.setSelectedIndex(1);                
        password.setText("MindSpy");
        confirmPassword.setText("MindSpy");
        //Act y Assert
        assertThat(validadorRegistro.validarRegistro(nombres, apellidos, cedula, correo, sexo, rol, password, confirmPassword), is(true));
    }

}