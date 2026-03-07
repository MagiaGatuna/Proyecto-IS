package test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import src.util.Calcular;
import src.modelo.Usuario;
/**
 * * | Clase de Equivalencia  | Entrada (Condición) | Resultado Esperado (Lógica) |
 * |------------------------|---------------------|-----------------------------|
 * | CEV-01: Est. Regular   | "Regular"           | CCB * (PorcentajeEst / 100) |
 * | CEV-02: Est. Becario   | "Becario"           | CCB * (PorcentajeBec / 100) |
 * | CEV-03: Est. Exonerado | "Exonerado"         | 0.0 (Gratis)                |
 * | CEV-04: Rol Profesor   | "Profesor"          | CCB * (PorcentajeProf / 100)|
 * | CEV-05: Rol Empleado   | "Empleado"          | CCB * (PorcentajeEmp / 100) |
 * |------------------------|---------------------|-----------------------------|
 * | CEI-01: Cond. Nula     | null                | Tratar como "Regular" (Default)|
 * | CEI-02: Cond. Inválida | "Cualquier texto"   | Tratar como "Regular" (Default)|
 */
public class CalcularPrecioTest {

    private String idMenu = "MONDAY_ALMUERZO";

    @BeforeEach
    void setUp() {
        Calcular.PorcentajeEstudiante = 20.0;
        Calcular.PorcentajeBecario = 5.0;
    }

    @Test
    void testPrecioEstudianteRegular() {
        Usuario estudiante = new Usuario("Juan", 100.0, "Estudiante", "123", "M", "Regular");
        double precio = Calcular.calcularPrecio(idMenu, estudiante);
        
        double ccbReal = src.modelo.MermayCCB.getCCB(idMenu);
        assertEquals(ccbReal * 0.20, precio, 0.01);
    }

    @Test
    void testPrecioEstudianteBecario() {
        Usuario becario = new Usuario("Ana", 50.0, "Estudiante", "456", "F", "Becario");
        double precio = Calcular.calcularPrecio(idMenu, becario);
        
        double ccbReal = src.modelo.MermayCCB.getCCB(idMenu);
        assertEquals(ccbReal * 0.05, precio, 0.01);
    }

    @Test
    void testPrecioEstudianteExonerado() {
        Usuario exonerado = new Usuario("Luis", 10.0, "Estudiante", "789", "M", "Exonerado");
        double precio = Calcular.calcularPrecio(idMenu, exonerado);
        
        assertEquals(0.0, precio, "El exonerado debería pagar 0 siempre");
    }
}