package test.modelo;
import org.junit.jupiter.api.Test;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.jupiter.api.Assertions.*;

import javax.swing.JTextField;

import src.modelo.*;
public class Validador_recargaTest {
    //Arrange
Validador_recarga prueba= new Validador_recarga();
@Test
void ValidarCamposTest(){
//Act
JTextField cedula_Valida=  new JTextField("4222");
JTextField cedula_inValida= new JTextField("99999999");
JTextField cedula_vacia= new JTextField();
JTextField referencia_Valida= new JTextField("3456");
JTextField referencia_inValida= new JTextField("28365");
JTextField referencia_vacia= new JTextField();
JTextField Monto_positivo=new JTextField("12000");
JTextField monto_negativo=new JTextField("-23.0");
JTextField Monto_nulo=new JTextField("");
Usuario user_actual_registrado= new Usuario("Jimin", 500.0, "Estudiante","4222", "Masculino");

//Assert
assertThat(Validador_recarga.ValidarCampos(cedula_Valida,referencia_Valida,Monto_positivo,user_actual_registrado), is(true));
assertThat(Validador_recarga.ValidarCampos(cedula_inValida,referencia_Valida, Monto_positivo, user_actual_registrado), is(false));
assertThat(Validador_recarga.ValidarCampos(cedula_Valida,referencia_Valida, monto_negativo, user_actual_registrado), is(false));
assertThat(Validador_recarga.ValidarCampos(cedula_Valida,referencia_Valida, Monto_nulo, user_actual_registrado), is(false));
assertThat(Validador_recarga.ValidarCampos(cedula_Valida, referencia_inValida, Monto_positivo, user_actual_registrado), is(false));
assertThat(Validador_recarga.ValidarCampos(cedula_vacia,referencia_vacia, monto_negativo, user_actual_registrado), is(false));
}    
}