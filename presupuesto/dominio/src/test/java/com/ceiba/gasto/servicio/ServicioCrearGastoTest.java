package com.ceiba.gasto.servicio;

import com.ceiba.BasePrueba;
import com.ceiba.dominio.excepcion.ExcepcionLongitudValor;
import com.ceiba.dominio.excepcion.ExcepcionSinDatos;
import com.ceiba.dominio.excepcion.ExcepcionValorInvalido;
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
    @DisplayName("Deberia Crear el gasto de manera correcta")
    void deberiaCrearElGastoDeManeraCorrecta() {
        // arrange
        Gasto gasto = new GastoTestDataBuilder().build();
        RepositorioGasto repositorioGasto = Mockito.mock(RepositorioGasto.class);
        Mockito.when(repositorioGasto.presupuestoParaFechaGasto(Mockito.any(Gasto.class))).thenReturn(90000L);
        Mockito.when(repositorioGasto.existePresupuesto(Mockito.any(Gasto.class))).thenReturn(true);
        Mockito.when(repositorioGasto.crear(gasto)).thenReturn(10L);
        ServicioCrearGasto servicioCrearGasto = new ServicioCrearGasto(repositorioGasto);
        // act
        Long idGasto = servicioCrearGasto.ejecutar(gasto);
        //- assert
        assertEquals(10L,idGasto);
        Mockito.verify(repositorioGasto, Mockito.times(1)).crear(gasto);
    }

    @Test
    @DisplayName("Deberia Crear el gasto sin datos previos en gastos")
    void deberiaCrearElGastoSinGastosPreviosIngresados() {
        // arrange
        Gasto gasto = new GastoTestDataBuilder().build();
        RepositorioGasto repositorioGasto = Mockito.mock(RepositorioGasto.class);
        Mockito.when(repositorioGasto.existe(Mockito.anyLong())).thenReturn(false);
        Mockito.when(repositorioGasto.sumaGastosPorFechaGasto(Mockito.any(Gasto.class))).thenReturn(0L);
        Mockito.when(repositorioGasto.existePresupuesto(Mockito.any(Gasto.class))).thenReturn(true);
        Mockito.when(repositorioGasto.presupuestoParaFechaGasto(Mockito.any(Gasto.class))).thenReturn(90000L);
        Mockito.when(repositorioGasto.crear(gasto)).thenReturn(10L);
        ServicioCrearGasto servicioCrearGasto = new ServicioCrearGasto(repositorioGasto);
        // act
        Long idGasto = servicioCrearGasto.ejecutar(gasto);
        //- assert
        assertEquals(10L,idGasto);
        Mockito.verify(repositorioGasto, Mockito.times(1)).crear(gasto);
    }

    @Test
    @DisplayName("no deberia crear gasto por superar presupuesto")
    void NoberiaCrearGastoPorSuperarPresupuseto() {
        Gasto gasto = new GastoTestDataBuilder()
                .conValorGasto(1L)
                .conValorGasto(9999999L)
                .build();
        RepositorioGasto repositorioGasto = Mockito.mock(RepositorioGasto.class);
        Mockito.when(repositorioGasto.existePresupuesto(Mockito.any(Gasto.class))).thenReturn(true);
        Mockito.when(repositorioGasto.crear(gasto)).thenReturn(10L);
        ServicioCrearGasto servicioCrearGasto = new ServicioCrearGasto(repositorioGasto);
        // act - assert
        BasePrueba.assertThrows(()
                        -> servicioCrearGasto.ejecutar(gasto)
                , ExcepcionValorInvalido.class,"Se super?? el valor del presupuesto");
    }

    @Test
    @DisplayName("No deberia crear gasto por superar presupuesto")
    void NoDeberiaCrearGastoPorNoExistirPresupuesto() {
        // arrange
        Gasto gasto = new GastoTestDataBuilder()
                .conId(1L)
                .conValorGasto(0L)
                .build();
        RepositorioGasto repositorioGasto = Mockito.mock(RepositorioGasto.class);
        Mockito.when(repositorioGasto.existePresupuesto(Mockito.any(Gasto.class))).thenReturn(false);
        ServicioCrearGasto servicioCrearGasto = new ServicioCrearGasto(repositorioGasto);
        // act - assert
        BasePrueba.assertThrows(()
                        -> servicioCrearGasto.ejecutar(gasto)
                , ExcepcionSinDatos.class,"No existe un presupuesto para aplicar el gasto");
    }

    @Test
    @DisplayName("No deberia crear gasto por superar presupuesto")
    void NoDeberiaCrearGastoPorSuperarPresupuesto() {
        // arrange
        Gasto gasto = new GastoTestDataBuilder()
                .conId(1L)
                .conValorGasto(9999999L)
                .build();
        RepositorioGasto repositorioGasto = Mockito.mock(RepositorioGasto.class);
        Mockito.when(repositorioGasto.existePresupuesto(Mockito.any(Gasto.class))).thenReturn(true);
        Mockito.when(repositorioGasto.existe(Mockito.anyLong())).thenReturn(true);
        ServicioCrearGasto servicioCrearGasto = new ServicioCrearGasto(repositorioGasto);
        // act - assert
        BasePrueba.assertThrows(()
                        -> servicioCrearGasto.ejecutar(gasto)
                , ExcepcionValorInvalido.class,"Se super?? el valor del presupuesto");
    }

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
    @DisplayName("No deber??a ingresar identificacionUsuario sin tama??o permitido")
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
    @DisplayName("No deber??a ingresar valorGasto sin tama??o permitido")
    void NodeberiaCrearGastoConValorGastoMayorTamanioPermitido() {

        //Arrange
        GastoTestDataBuilder gastoTestDataBuilder = new GastoTestDataBuilder()
                .conValorGasto(getValorGastoMayoTamanoDelPermido());
        //act-assert
        BasePrueba.assertThrows(() -> {
                    gastoTestDataBuilder.build();
                },
                ExcepcionLongitudValor.class, "valorGasto no debe ser mayor a 7");
    }

    private long getValorGastoMayoTamanoDelPermido() {
        return Long.parseLong(RandomStringUtils.randomNumeric(8));
    }

    @Test
    @DisplayName("No deber??a ingresar identificacionUsuario sin formato alfanum??rico ")
    void NodeberiaCrearGastoSinIdentificacionUsuarioAlafanumerico() {

        //Arrange
        GastoTestDataBuilder gastoTestDataBuilder = new GastoTestDataBuilder()
                .conIdentificacionUsuario("**").conId(1L);
        //act-assert
        BasePrueba.assertThrows(() -> {
                    gastoTestDataBuilder.build();
                },
                ExcepcionValorInvalido.class, "La identificacionUsuario debe ser alfanum??rico");
    }
}
