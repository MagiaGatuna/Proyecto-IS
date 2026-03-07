package test;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import src.modelo.*;
import src.util.Calcular;

public class ReservaYControladorTest {

    private Usuario usuarioPrueba;
    private String idMenu = "MONDAY_ALMUERZO";

    @BeforeEach
    void setUp() {
        usuarioPrueba = new Usuario("Test User", 100.0, "Estudiante", "9999", "Masculino", "Regular");
        ReservaDAO.eliminarPorCedula("9999", idMenu);
    }

    @Test
    void testFlujoCompletoReservaYPay() {
        double precio = Calcular.calcularPrecio(idMenu, usuarioPrueba);
        double saldoInicial = usuarioPrueba.getSaldo();
        Reserva nueva = new Reserva(usuarioPrueba.getCedula(), idMenu);
        ReservaDAO.guardar(nueva);
        
        Reserva guardada = ReservaDAO.buscarPorCedula("9999", idMenu);
        assertNotNull(guardada, "La reserva debería haberse guardado en el JSON");

        // simular la lógica del Controlador_Reconocimiento 
        if (guardada != null && usuarioPrueba.getSaldo() >= precio) {
            double nuevoSaldo = usuarioPrueba.getSaldo() - precio;
            usuarioPrueba.setSaldo(nuevoSaldo);
            ReservaDAO.eliminarPorCedula("9999", idMenu);
        }

        assertEquals(saldoInicial - precio, usuarioPrueba.getSaldo(), 0.01, "El saldo debió disminuir");
        assertNull(ReservaDAO.buscarPorCedula("9999", idMenu), "La reserva debe borrarse tras el cobro");
    }

}