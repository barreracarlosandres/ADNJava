package com.ceiba.presupuesto.servicio;

import com.ceiba.BasePrueba;
import com.ceiba.dominio.excepcion.ExcepcionDuplicidad;
import com.ceiba.dominio.excepcion.ExcepcionLongitudValor;
import com.ceiba.presupuesto.modelo.entidad.Presupuesto;
import com.ceiba.presupuesto.puerto.repositorio.RepositorioPresupuesto;
import com.ceiba.presupuesto.servicio.testdatabuilder.PresupuestoTestDataBuilder;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ServicioCrearPresupuestoTest {

    @Test
    @DisplayName("Deberia lanzar una exepecion cuando indentificacionUsuario mayor a 15")
    void deberiaLanzarUnaExepcionCuandoLaLongitudDeLaIdentificacionUsuarioSeaMayoA15() {
        // arrange
        PresupuestoTestDataBuilder presupuestoTestDataBuilder = new PresupuestoTestDataBuilder()
                .conIdentificacionUsuario(RandomStringUtils.randomAlphabetic(16));
        // act - assert
        BasePrueba.assertThrows(presupuestoTestDataBuilder::build, ExcepcionLongitudValor.class, "identificacionUsuario no debe ser mayor a 15");
    }

    @Test
    @DisplayName("Deberia lanzar una exepcion cuando se valide la existencia del Presupuesto")
    void deberiaLanzarUnaExepcionCuandoSeValideLaExistenciaDelPresupuesto() {
        // arrange
        Presupuesto presupuesto = new PresupuestoTestDataBuilder().conId(1L).build();
        RepositorioPresupuesto repositorioPresupuesto = Mockito.mock(RepositorioPresupuesto.class);
        Mockito.when(repositorioPresupuesto.existe(Mockito.anyLong())).thenReturn(true);
        ServicioCrearPresupuesto servicioCrearPresupuesto = new ServicioCrearPresupuesto(repositorioPresupuesto);
        // act - assert
        BasePrueba.assertThrows(() -> servicioCrearPresupuesto.ejecutar(presupuesto), ExcepcionDuplicidad.class,"El presupuesto ya existe en el sistema");
    }

    @Test
    @DisplayName("Deberia Crear el presupuesto de manera correcta")
    void deberiaCrearElPresupuestoDeManeraCorrecta() {
        // arrange
        Presupuesto presupuesto = new PresupuestoTestDataBuilder().build();
        RepositorioPresupuesto repositorioPresupuesto = Mockito.mock(RepositorioPresupuesto.class);
        Mockito.when(repositorioPresupuesto.existe(Mockito.anyLong())).thenReturn(false);
        Mockito.when(repositorioPresupuesto.crear(presupuesto)).thenReturn(10L);
        ServicioCrearPresupuesto servicioCrearPresupuesto = new ServicioCrearPresupuesto(repositorioPresupuesto);
        // act
        Long idPresupuesto = servicioCrearPresupuesto.ejecutar(presupuesto);
        //- assert
        assertEquals(10L,idPresupuesto);
        Mockito.verify(repositorioPresupuesto, Mockito.times(1)).crear(presupuesto);
    }
}