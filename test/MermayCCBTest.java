package test;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import src.modelo.MermayCCB;

public class MermayCCBTest {

    @Test

    void test_campos_correctos(){
    String id= "MONDAY_DESAYUNO";
    String llamada=MermayCCB.getMerma(id);
    assertNotNull(llamada);
    assertFalse(llamada.equals("No encontrado"));
    assertFalse(llamada.contains("Error"));
    }

    @Test

    void test_campodia_incorrecto(){
    String id="SATURDAY_ALMUERZO";
    String llamada=MermayCCB.getMerma(id);
    assertEquals("No encontrado", llamada);
    }

    @Test

    void test_campoturno_incorrecto(){
     String id="FRIDAY_MERIENDA";
     String llamada=MermayCCB.getMerma(id);
     assertEquals("No encontrado", llamada);
    }

}