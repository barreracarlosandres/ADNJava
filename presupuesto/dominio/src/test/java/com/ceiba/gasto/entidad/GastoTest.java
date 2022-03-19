package com.ceiba.gasto.entidad;

import com.ceiba.BasePrueba;
import com.ceiba.dominio.excepcion.ExcepcionValorObligatorio;
import com.ceiba.gasto.modelo.entidad.Gasto;
import com.ceiba.gasto.servicio.testdatabuilder.GastoTestDataBuilder;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class GastoTest {

    @Test
    void deberiaCrearCorrectamenteElGasto() {
        // arrange
        //act
        Gasto gasto = new GastoTestDataBuilder().conId(1L).build();
        //assert
        assertEquals(1, gasto.getId());
        assertEquals("94123123", gasto.getIdentificacionUsuario());
        assertEquals(100L, gasto.getValorGasto());
        assertEquals(gasto.getFechaGasto(), gasto.getFechaGasto());

    }

    @Test
    @DisplayName("No debería ingresar sin identificacionUsuario")
    void NodeberiaCrearGastoSinIdentificacionUsuario() {

        //Arrange
        GastoTestDataBuilder gastoTestDataBuilder = new GastoTestDataBuilder().conIdentificacionUsuario(null).conId(1L);
        //act-assert
        BasePrueba.assertThrows(() -> { gastoTestDataBuilder.build(); },
                ExcepcionValorObligatorio.class, "Debe ingresar identificacionUsuario");
    }

    @Test
    @DisplayName("No debería ingresar sin valorGasto")
    void NodeberiaCrearGastoSinVarlorGasto() {

        //Arrange
        GastoTestDataBuilder gastoTestDataBuilder = new GastoTestDataBuilder().conValorGasto(null).conId(1L);
        //act-assert
        BasePrueba.assertThrows(() -> { gastoTestDataBuilder.build(); },
                ExcepcionValorObligatorio.class, "Debe ingresar valorGasto");
    }

    @Test
    @DisplayName("No debería ingresar sin fechaGasto")
    void NodeberiaCrearGastoSinFechaGasto() {

        //Arrange
        GastoTestDataBuilder gastoTestDataBuilder = new GastoTestDataBuilder().conFechaGasto(null).conId(1L);
        //act-assert
        BasePrueba.assertThrows(() -> {
                    gastoTestDataBuilder.build();
                },
                ExcepcionValorObligatorio.class, "Debe ingresar fechaGasto");
    }
}
