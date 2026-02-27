package test;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import src.modelo.Menus_lista;

public class Menus_listaTest {


    @Test
    

    void testCampos_sin_rellenar(){
       
         //arrange
        String dia="FRIDAY";
        String turno="DESAYUNO";
        //String comida= "Arroz con pollo y ensalada césar";
        String descripcion= "Rico arroz con pollo y maíz, con una ensalada reconfortante";
        //String nutricion="256";
        String cantidad="1260";
        
        boolean indicador_prueba= Menus_lista.actualizarMenu(dia, turno, "", descripcion, "", cantidad);
        assertFalse(indicador_prueba,"Se debe retornar false si al menos un campo está vacío");
        
    }

    @Test

    void verificar_aforo_no_es_numero(){
        //arrange
        String dia="MONDAY";
        String turno="DESAYUNO";
        String comida= "Arepa con queso";
        String descripcion= "Arepa con queso rica y reconfortante";
        String nutricion="156";
        String cantidad="abcdefg";
        
        boolean indicador_prueba= Menus_lista.actualizarMenu(dia, turno, comida, descripcion,nutricion, cantidad);
        assertFalse(indicador_prueba,"Se debe retornar false si la cantidad de bandejas no es un número");
    }

    @Test

    void verificar_aforo_no_es_positivo(){
        //arrange
        String dia="TUESDAY";
        String turno="ALMUERZO";
        String comida= "Pizza";
        String descripcion= "Rica pizza con queso fundido, jamón y maíz";
        String nutricion="436";
        String cantidad="-12356";
        
        boolean indicador_prueba= Menus_lista.actualizarMenu(dia, turno, comida, descripcion,nutricion, cantidad);
        assertFalse(indicador_prueba,"Se debe retornar false si la cantidad de bandejas no es un número positivo mayor o igual a cero");
    }

    @Test

    void verificar_escritura_correcta(){
        //arrange
        String dia="MONDAY";
        String turno="ALMUERZO";
        String comida= "Ensalada César y pollo";
        String descripcion= "Rico pollo con una ensalada reconfortante";
        String nutricion="236";
        String cantidad="1006";
        
        boolean indicador_prueba= Menus_lista.actualizarMenu(dia, turno, comida, descripcion,nutricion, cantidad);
        assertTrue(indicador_prueba,"Se debe retornar true si todo fue escrito correctamente en JSON");
    }
    
}
