package com.ceiba.gasto.servicio;

import com.ceiba.BasePrueba;
import com.ceiba.dominio.excepcion.ExcepcionDuplicidad;
import com.ceiba.dominio.excepcion.ExcepcionLongitudValor;
import com.ceiba.gasto.modelo.entidad.Gasto;
import com.ceiba.gasto.puerto.repositorio.RepositorioGasto;
import com.ceiba.gasto.servicio.testdatabuilder.GastoTestDataBuilder;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ServicioCrearGastoTest {

    @Test
    @DisplayName("Deberia lanzar una excepcion cuando indentificacionUsuario mayor a 15")
    void deberiaLanzarUnaExepcionCuandoLaLongitudDeLaIdentificacionUsuarioSeaMayoA15() {
        // arrange
        GastoTestDataBuilder gastoTestDataBuilder = new GastoTestDataBuilder()
                .conIdentificacionUsuario(RandomStringUtils.randomAlphabetic(16));
        // act - assert
        BasePrueba.assertThrows(gastoTestDataBuilder::build, ExcepcionLongitudValor.class, "identificacionUsuario no debe ser mayor a 15");
    }
}
