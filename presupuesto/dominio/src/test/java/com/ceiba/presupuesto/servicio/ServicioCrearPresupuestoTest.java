package com.ceiba.presupuesto.servicio;

import com.ceiba.BasePrueba;
import com.ceiba.dominio.excepcion.ExcepcionDuplicidad;
import com.ceiba.dominio.excepcion.ExcepcionLongitudValor;
import com.ceiba.dominio.excepcion.ExcepcionSinDatos;
import com.ceiba.dominio.excepcion.ExcepcionValorInvalido;
import com.ceiba.presupuesto.modelo.entidad.Presupuesto;
import com.ceiba.presupuesto.puerto.repositorio.RepositorioPresupuesto;
import com.ceiba.presupuesto.servicio.testdatabuilder.PresupuestoTestDataBuilder;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ServicioCrearPresupuestoTest {

    @Test
    @DisplayName("Deberia Crear el presupuseto de manera correcta")
    void deberiaCrearPresupuesto() {
        // arrange
        Presupuesto presupuesto = new PresupuestoTestDataBuilder().build();
        RepositorioPresupuesto repositorioPresupuesto = Mockito.mock(RepositorioPresupuesto.class);
        Mockito.when(repositorioPresupuesto.existeUsuario(Mockito.anyString())).thenReturn(true);
        Mockito.when(repositorioPresupuesto.existePresupuesto(Mockito.anyString(), Mockito.any(LocalDateTime.class))).thenReturn(false);
        ServicioCrearPresupuesto servicioCrearPresupuesto = new ServicioCrearPresupuesto(repositorioPresupuesto);
        // act
        Long idPresupuesto = servicioCrearPresupuesto.ejecutar(presupuesto);
        //- assert
        assertEquals(0L,idPresupuesto);
        Mockito.verify(repositorioPresupuesto, Mockito.times(1)).crear(presupuesto);
    }

    @Test
    @DisplayName("No deberia Crear el presupuseto de manera correcta dado que no existe usuario")
    void NodeberiaCrearPresupuestoDadoQueNoExisteUsuario() {
        // arrange
        Presupuesto presupuesto = new PresupuestoTestDataBuilder().build();
        RepositorioPresupuesto repositorioPresupuesto = Mockito.mock(RepositorioPresupuesto.class);
        Mockito.when(repositorioPresupuesto.existeUsuario(Mockito.anyString())).thenReturn(false);
        ServicioCrearPresupuesto servicioCrearPresupuesto = new ServicioCrearPresupuesto(repositorioPresupuesto);
        // act - assert
        BasePrueba.assertThrows(() ->
                servicioCrearPresupuesto.ejecutar(presupuesto)
                , ExcepcionSinDatos.class,"El usuario no existe");;
    }

    @Test
    @DisplayName("No deberia Crear el presupuseto dado que ya existe")
    void NodeberiaCrearPresupuestoDadoQueYaExiste() {
        // arrange
        Presupuesto presupuesto = new PresupuestoTestDataBuilder().build();
        RepositorioPresupuesto repositorioPresupuesto = Mockito.mock(RepositorioPresupuesto.class);
        Mockito.when(repositorioPresupuesto.existeUsuario(Mockito.anyString())).thenReturn(true);
        Mockito.when(repositorioPresupuesto.existePresupuesto(Mockito.anyString(), Mockito.any(LocalDateTime.class))).thenReturn(true);
        ServicioCrearPresupuesto servicioCrearPresupuesto = new ServicioCrearPresupuesto(repositorioPresupuesto);
        // act - assert
        BasePrueba.assertThrows(() ->
                        servicioCrearPresupuesto.ejecutar(presupuesto)
                , ExcepcionDuplicidad.class,"El presupuesto ya existe en el sistema");;
    }

    @Test
    @DisplayName("Deberia lanzar una exepecion cuando indentificacionUsuario mayor a 15")
    void NoDeberiaCrearPresupuestoPorIdentificacionMayorAlPermitido() {
        // arrange
        PresupuestoTestDataBuilder presupuestoTestDataBuilder = new PresupuestoTestDataBuilder()
                .conIdentificacionUsuario(getRandomStringAlfabetoDeTamanno16());
        // act - assert
        BasePrueba.assertThrows(presupuestoTestDataBuilder::build, ExcepcionLongitudValor.class, "identificacionUsuario no debe ser mayor a 15");
    }

    @Test
    @DisplayName("Deberia lanzar una exepecion cuando valorPresupuesto mayor a 7")
    void NoDeberiaCrearPresupuestoConValorPresupuestoMayorAlTamanioPermitido() {

        //Arrange
        PresupuestoTestDataBuilder PresupuestoTestDataBuilder = new PresupuestoTestDataBuilder()
                .conValorPresupuesto(getRandomNumeroDeTamanno8());

        //act-assert
        BasePrueba.assertThrows(() -> {
                    PresupuestoTestDataBuilder.build();
                },
                ExcepcionLongitudValor.class, "valorPresupuesto no debe ser mayor a 7");
    }

    @Test
    @DisplayName("No debería ingresar identificacionUsuario sin formato alfanumérico ")
    void NoDeberiaCrearPresupuestoSinFormadoIdentificacionUsuarioAlafanumerico() {

        //Arrange
        PresupuestoTestDataBuilder PresupuestoTestDataBuilder = new PresupuestoTestDataBuilder()
                .conIdentificacionUsuario("**").conId(1L);
        //act-assert
        BasePrueba.assertThrows(() -> {
                    PresupuestoTestDataBuilder.build();
                },
                ExcepcionValorInvalido.class, "La identificacionUsuario debe ser alfanumérico");
    }

    private static long getRandomNumeroDeTamanno8() {
        return 999999999L;
    }

    private static  String getRandomStringAlfabetoDeTamanno16() {
        return RandomStringUtils.randomAlphabetic(16);
    }
}
