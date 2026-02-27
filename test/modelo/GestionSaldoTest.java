package test.modelo;
import org.junit.jupiter.api.Test;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.is;
import static  org.junit.jupiter.api.Assertions.*;
import src.modelo.*;


public class GestionSaldoTest {
    //arrange
    private final GestionSaldo prueba = new GestionSaldo();
@Test
void testActualizarSaldo(){

//Act
String cedula_Valida="1234";
String cedula_Invalida="512307";
double monto_positivo=999999.9;
double monto_negativo=-999999.9;
double monto_nulo=0;

//Assert
assertThat(prueba.ActualizarSaldo(cedula_Valida,monto_positivo),is(true));
assertThat(prueba.ActualizarSaldo(cedula_Valida,monto_negativo),is(false));
assertThat(prueba.ActualizarSaldo(cedula_Valida,monto_nulo),is(false));
assertThat(prueba.ActualizarSaldo(cedula_Invalida,monto_negativo),is(false));
assertThat(prueba.ActualizarSaldo(cedula_Invalida,monto_positivo),is(false));
assertThat(prueba.ActualizarSaldo(cedula_Invalida,monto_nulo),is(false));

}
    
}
