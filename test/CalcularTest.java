package test;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import src.util.Calcular;

public class CalcularTest {

    @Test

    void testProfesor(){
     double NuevoPorcentaje=75;
     String rol="Profesor";
     boolean resultado= Calcular.cambiarPorcentaje(NuevoPorcentaje,rol);
     assertTrue(resultado,"Debe mostrar true si se actualizó correctamente el porcentaje");
    }

    @Test
    
    void testRol_desconocido(){
     double NuevoPorcentaje=65;
     String rol="Visitante";
     boolean resultado= Calcular.cambiarPorcentaje(NuevoPorcentaje,rol);
     assertFalse(resultado,"Debe mostrar false, ya que el rol no es reconocido por el sistema");
    }
}

