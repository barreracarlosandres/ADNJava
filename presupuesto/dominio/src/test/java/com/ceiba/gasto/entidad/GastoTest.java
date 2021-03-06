package com.ceiba.gasto.entidad;

import com.ceiba.BasePrueba;
import com.ceiba.dominio.excepcion.ExcepcionSinDatos;
import com.ceiba.dominio.excepcion.ExcepcionValorInvalido;
import com.ceiba.dominio.excepcion.ExcepcionValorObligatorio;
import com.ceiba.gasto.modelo.entidad.Gasto;
import com.ceiba.gasto.puerto.repositorio.RepositorioGasto;
import com.ceiba.gasto.servicio.ServicioActualizarGasto;
import com.ceiba.gasto.servicio.ServicioCrearGasto;
import com.ceiba.gasto.servicio.testdatabuilder.GastoTestDataBuilder;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

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
}
