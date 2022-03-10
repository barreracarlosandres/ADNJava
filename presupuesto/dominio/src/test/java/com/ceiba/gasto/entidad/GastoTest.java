package com.ceiba.gasto.entidad;

import com.ceiba.BasePrueba;
import com.ceiba.dominio.excepcion.ExcepcionLongitudValor;
import com.ceiba.dominio.excepcion.ExcepcionValorInvalido;
import com.ceiba.dominio.excepcion.ExcepcionValorObligatorio;
import com.ceiba.gasto.modelo.entidad.Gasto;
import com.ceiba.gasto.servicio.testdatabuilder.GastoTestDataBuilder;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

class GastoTest {

    @Test
    @DisplayName("Deberia ingresar un gasto")
    void deberiaCrearCorrectamenteElGasto() {
        // arrange
        LocalDateTime fechaCreacion = LocalDateTime.now();
        //act
        Gasto gasto = new GastoTestDataBuilder().conId(1L).build();
        fechaCreacion = gasto.getFechaGasto();
        //assert
        assertEquals(1, gasto.getId());
        assertEquals("123", gasto.getIdentificacionUsuario());
        assertEquals(123, gasto.getValorGasto());
        assertEquals(fechaCreacion, gasto.getFechaGasto());
    }

    @Test
    @DisplayName("No debería ingresar sin identificacionUsuario")
    void deberiaFallarSinIdentificacionUsuario() {

        //Arrange
        GastoTestDataBuilder gastoTestDataBuilder = new GastoTestDataBuilder().conIdentificacionUsuario(null).conId(1L);
        //act-assert
        BasePrueba.assertThrows(() -> {
                    gastoTestDataBuilder.build();
                },
                ExcepcionValorObligatorio.class, "Debe ingresar identificacionUsuario");
    }

    @Test
    @DisplayName("No debería ingresar sin valorGasto")
    void deberiaFallarSinVarlorGasto() {

        //Arrange
        GastoTestDataBuilder gastoTestDataBuilder = new GastoTestDataBuilder().conValorGasto(null).conId(1L);
        //act-assert
        BasePrueba.assertThrows(() -> {
                    gastoTestDataBuilder.build();
                },
                ExcepcionValorObligatorio.class, "Debe ingresar valorGasto");
    }

    @Test
    @DisplayName("No debería ingresar sin fechaGasto")
    void deberiaFallarSinFechaGasto() {

        //Arrange
        GastoTestDataBuilder gastoTestDataBuilder = new GastoTestDataBuilder().conFechaGasto(null).conId(1L);
        //act-assert
        BasePrueba.assertThrows(() -> {
                    gastoTestDataBuilder.build();
                },
                ExcepcionValorObligatorio.class, "Debe ingresar fechaGasto");
    }

    @Test
    @DisplayName("No debería ingresar identificacionUsuario sin tamaño permitido")
    void deberiaFallarSinIdentificacionMayorTamanioPermitido() {

        //Arrange
        GastoTestDataBuilder gastoTestDataBuilder = new GastoTestDataBuilder()
                .conIdentificacionUsuario(RandomStringUtils.randomAlphabetic(16)).conId(1L);
        //act-assert
        BasePrueba.assertThrows(() -> {
                    gastoTestDataBuilder.build();
                },
                ExcepcionLongitudValor.class, "identificacionUsuario no debe ser mayor a 15");
    }

    @Test
    @DisplayName("No debería ingresar valorGasto sin tamaño permitido")
    void deberiaFallarSinValorGastoMayorTamanioPermitido() {

        //Arrange
        GastoTestDataBuilder gastoTestDataBuilder = new GastoTestDataBuilder()
                .conValorGasto(Long.parseLong(RandomStringUtils.randomNumeric(8)));
        //act-assert
        BasePrueba.assertThrows(() -> {
                    gastoTestDataBuilder.build();
                },
                ExcepcionLongitudValor.class, "valorGasto no debe ser mayor a 7");
    }

    @Test
    @DisplayName("No debería ingresar identificacionUsuario sin formato alfanumérico ")
    void deberiaFallarSinIdentificacionUsuarioAlafanumerico() {

        //Arrange
        GastoTestDataBuilder gastoTestDataBuilder = new GastoTestDataBuilder()
                .conIdentificacionUsuario("**").conId(1L);
        //act-assert
        BasePrueba.assertThrows(() -> {
                    gastoTestDataBuilder.build();
                },
                ExcepcionValorInvalido.class, "La identificacionUsuario debe ser alfanumérico");
    }
}
