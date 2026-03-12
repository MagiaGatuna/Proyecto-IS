package test.modelo;
import org.junit.jupiter.api.Test;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.jupiter.api.Assertions.*;
import src.modelo.*;

public class ActualizarUsuarioTest {
    //arrange
    ActualizarUsuario prueba= new ActualizarUsuario();
    //Act y asertt
    @Test
    void testActualizar(){
    assertTrue(prueba.actualizar("Regular", "123"));
    assertFalse(prueba.actualizar("Becario", "0000"));
    assertTrue(prueba.actualizar("Exonerado", "15151515"));
}
}
