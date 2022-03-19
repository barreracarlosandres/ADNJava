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

import static org.junit.jupiter.api.Assertions.assertEquals;

class GastoTest {

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

    @Test
    @DisplayName("No debería ingresar identificacionUsuario sin tamaño permitido")
    void NodeberiaCrearGastoConIdentificacionUsuarioMayorTamanioPermitido() {
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
    void NodeberiaCrearGastoConValorGastoMayorTamanioPermitido() {

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
    void NodeberiaCrearGastoSinIdentificacionUsuarioAlafanumerico() {

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
