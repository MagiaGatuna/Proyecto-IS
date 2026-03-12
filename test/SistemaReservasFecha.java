package test;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

import src.modelo.*;
import src.util.Calcular_dia;
import org.json.JSONArray;
import org.json.JSONObject;

import java.nio.file.*;
import java.nio.charset.StandardCharsets;
import java.io.IOException;

public class SistemaReservasFecha {

    private final String CEDULA_PRUEBA = "12345678";
    private final String MENU_PRUEBA_ID = "MONDAY_ALMUERZO";
    private final Path RUTA_RESERVAS = Paths.get("res/data/reservas.json").toAbsolutePath();
    private final Path RUTA_MENUS = Paths.get("res/data/menus.json").toAbsolutePath();

    @BeforeEach
    public void setUp() throws IOException {
        // Resetear el archivo de reservas a una lista vacía antes de cada test
        Files.write(RUTA_RESERVAS, "[]".getBytes(StandardCharsets.UTF_8));
        
        // Asegurarnos de que el menú de prueba tenga reservas en 0
        actualizarAforoEnJson(MENU_PRUEBA_ID, 0);
    }

    @Test
    @DisplayName("Escenario 1: Reserva futura no debe ser eliminada")
    public void testReservaFuturaPersiste() {
        String fechaFutura = Calcular_dia.calcularFechaMenu("MONDAY");
        
        Reserva res = new Reserva(CEDULA_PRUEBA, MENU_PRUEBA_ID, fechaFutura);
        ReservaDAO.guardar(res);
        Menus_lista.incrementarReserva(MENU_PRUEBA_ID);

        boolean fueAfectado = ReservaDAO.limpiarYVerificarUsuario(CEDULA_PRUEBA);

        assertFalse(fueAfectado, "El usuario no debería ser notificado porque la reserva es futura");
        assertNotNull(ReservaDAO.buscarPorCedula(CEDULA_PRUEBA, MENU_PRUEBA_ID), "La reserva debe seguir existiendo");
    }

    @Test
    @DisplayName("Escenario 2: Reserva pasada debe eliminarse y alertar al usuario")
    public void testReservaPasadaSeElimina() throws IOException {
        String fechaPasada = "2020-01-01";
        inyectarReservaManual(CEDULA_PRUEBA, MENU_PRUEBA_ID, fechaPasada);
        Menus_lista.incrementarReserva(MENU_PRUEBA_ID);
        
        int aforoAntes = obtenerAforoActual(MENU_PRUEBA_ID);
        boolean fueAfectado = ReservaDAO.limpiarYVerificarUsuario(CEDULA_PRUEBA);
        assertTrue(fueAfectado, "El método debe retornar true para disparar el JOptionPane en el controlador");
        assertNull(ReservaDAO.buscarPorCedula(CEDULA_PRUEBA, MENU_PRUEBA_ID), "La reserva vieja debe haber sido borrada del JSON");
        
        int aforoDespues = obtenerAforoActual(MENU_PRUEBA_ID);
        assertEquals(aforoAntes - 1, aforoDespues, "El aforo en menus.json debe haberse liberado (-1)");
    }

    // --- MÉTODOS AUXILIARES PARA MANIPULAR JSON EN PRUEBAS ---

    private void inyectarReservaManual(String cedula, String idMenu, String fecha) throws IOException {
        JSONArray lista = new JSONArray();
        JSONObject res = new JSONObject();
        res.put("cedula", cedula);
        res.put("dia_turno", idMenu);
        res.put("fecha_exacta", fecha);
        lista.put(res);
        Files.write(RUTA_RESERVAS, lista.toString(4).getBytes(StandardCharsets.UTF_8));
    }

    private int obtenerAforoActual(String idMenu) throws IOException {
        String contenido = new String(Files.readAllBytes(RUTA_MENUS), StandardCharsets.UTF_8);
        JSONArray lista = new JSONArray(contenido);
        for (int i = 0; i < lista.length(); i++) {
            JSONObject m = lista.getJSONObject(i);
            if (m.getString("dia_turno").equals(idMenu)) {
                return m.getInt("reservas_actual");
            }
        }
        return -1;
    }

    private void actualizarAforoEnJson(String idMenu, int nuevoValor) throws IOException {
        String contenido = new String(Files.readAllBytes(RUTA_MENUS), StandardCharsets.UTF_8);
        JSONArray lista = new JSONArray(contenido);
        for (int i = 0; i < lista.length(); i++) {
            JSONObject m = lista.getJSONObject(i);
            if (m.getString("dia_turno").equals(idMenu)) {
                m.put("reservas_actual", nuevoValor);
            }
        }
        Files.write(RUTA_MENUS, lista.toString(4).getBytes(StandardCharsets.UTF_8));
    }
}