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
String cedulapropia = "123";       
String yo_Estudiante = "123";           
String pana_Estudiante = "15151515";     
String pana_Exonerado = "555";           
String empleado = "22222222";           

double monto_pos = 500.0;
double monto_neg = -10.0;

assertNull(prueba.ActualizarSaldo(cedulapropia, cedulapropia, monto_pos));          // éxito: estudiante a sí mismo
assertNull(prueba.ActualizarSaldo(pana_Estudiante, yo_Estudiante, monto_pos));      // éxito: estudiante a otro estudiante
assertNotNull(prueba.ActualizarSaldo(empleado, yo_Estudiante, monto_pos));          // falla: no estudiante recargando a otro
assertNotNull(prueba.ActualizarSaldo(pana_Exonerado, pana_Exonerado, monto_pos));  // falla: exonerado
assertNotNull(prueba.ActualizarSaldo(yo_Estudiante, yo_Estudiante, monto_neg));    // falla: monto negativo
assertNotNull(prueba.ActualizarSaldo(yo_Estudiante, empleado, monto_pos));    // falla: receptor no es estudiante
assertNotNull(prueba.ActualizarSaldo("00000000", yo_Estudiante, monto_pos));  // falla: cédula emisor no existe
assertNotNull(prueba.ActualizarSaldo(yo_Estudiante, "00000000", monto_pos));  // falla: cédula receptor no existe
}
    
}
