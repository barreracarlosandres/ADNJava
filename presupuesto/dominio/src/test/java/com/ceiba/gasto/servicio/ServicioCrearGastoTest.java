package com.ceiba.gasto.servicio;

import com.ceiba.BasePrueba;
import com.ceiba.dominio.excepcion.ExcepcionLongitudValor;
import com.ceiba.dominio.excepcion.ExcepcionValorInvalido;
import com.ceiba.gasto.modelo.entidad.Gasto;
import com.ceiba.gasto.puerto.repositorio.RepositorioGasto;
import com.ceiba.gasto.servicio.testdatabuilder.GastoTestDataBuilder;
import com.ceiba.presupuesto.modelo.entidad.Presupuesto;
import com.ceiba.presupuesto.puerto.repositorio.RepositorioPresupuesto;
import com.ceiba.presupuesto.servicio.ServicioCrearPresupuesto;
import com.ceiba.presupuesto.servicio.testdatabuilder.PresupuestoTestDataBuilder;
import com.ceiba.usuario.modelo.entidad.Usuario;
import com.ceiba.usuario.puerto.repositorio.RepositorioUsuario;
import com.ceiba.usuario.servicio.ServicioCrearUsuario;
import com.ceiba.usuario.servicio.testdatabuilder.UsuarioTestDataBuilder;
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
