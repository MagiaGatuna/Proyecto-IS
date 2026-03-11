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
    String cedulapropia= "22222222"; 
    String yo_Estudiante = "123";   
    String pana_Estudiante = "15151515"; 
    String pana_Exonerado = "555";  
    
    double monto_pos = 500.0;
    double monto_neg = -10.0;


    assertThat(prueba.ActualizarSaldo(cedulapropia,cedulapropia, monto_pos), is(true));
    assertThat(prueba.ActualizarSaldo(pana_Estudiante, yo_Estudiante, monto_pos), is(true));
    assertThat(prueba.ActualizarSaldo(cedulapropia, yo_Estudiante, monto_pos), is(false));
    assertThat(prueba.ActualizarSaldo(pana_Exonerado, pana_Exonerado, monto_pos), is(false));
    assertThat(prueba.ActualizarSaldo(yo_Estudiante, yo_Estudiante, monto_neg), is(false));
}
    
}
