package test.modelo;
import org.junit.jupiter.api.Test;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.jupiter.api.Assertions.*;
import src.modelo.*;


public class GestionSaldoTest {
    //arrange
    private final GestionSaldo prueba = new GestionSaldo();
@Test
void testActualizarSaldo(){
    // Setup de datos
    String panaValido = "1234";      
    String panaExonerado = "5555";   
    String miCedula = "4321";        
    String cedulaInvalida = "0000"; 
    
    double montoPositivo = 500.0;
    double montoNegativo = -100.0;
    double montoNulo = 0;

    assertThat(prueba.ActualizarSaldo(panaValido, miCedula, montoPositivo), is(true));
    assertThat(prueba.ActualizarSaldo(panaValido, miCedula, montoNegativo), is(false));
    assertThat(prueba.ActualizarSaldo(panaValido, miCedula, montoNulo), is(false));
    assertThat(prueba.ActualizarSaldo(panaExonerado, miCedula, montoPositivo), is(false));
    assertThat(prueba.ActualizarSaldo(cedulaInvalida, miCedula, montoPositivo), is(false));
    assertThat(prueba.ActualizarSaldo(cedulaInvalida, miCedula, montoNegativo), is(false));
}
    
}
