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
    @DisplayName("Deberia lanzar una exepecion cuando indentificacionUsuario mayor a 15")
    void deberiaLanzarUnaExepcionCuandoLaLongitudDeLaIdentificacionUsuarioSeaMayoA15() {
        // arrange
        GastoTestDataBuilder gastoTestDataBuilder = new GastoTestDataBuilder()
                .conIdentificacionUsuario(RandomStringUtils.randomAlphabetic(16));
        // act - assert
        BasePrueba.assertThrows(gastoTestDataBuilder::build, ExcepcionLongitudValor.class, "identificacionUsuario no debe ser mayor a 15");
    }

    @Test
    @DisplayName("Deberia lanzar una exepcion cuando se valide la existencia del Gasto")
    void deberiaLanzarUnaExepcionCuandoSeValideLaExistenciaDelGasto() {
        // arrange
        Gasto gasto = new GastoTestDataBuilder().conId(1L).build();
        RepositorioGasto repositorioGasto = Mockito.mock(RepositorioGasto.class);
        Mockito.when(repositorioGasto.existe(Mockito.anyLong())).thenReturn(true);
        ServicioCrearGasto servicioCrearGasto = new ServicioCrearGasto(repositorioGasto);
        // act - assert
        BasePrueba.assertThrows(() -> servicioCrearGasto.ejecutar(gasto), ExcepcionDuplicidad.class,"El gasto ya existe en el sistema");
    }

    @Test
    @DisplayName("Deberia Crear el usuario de manera correcta")
    void deberiaCrearElGastoDeManeraCorrecta() {
        // arrange
        Gasto gasto = new GastoTestDataBuilder().build();
        RepositorioGasto repositorioGasto = Mockito.mock(RepositorioGasto.class);
        Mockito.when(repositorioGasto.existe(Mockito.anyLong())).thenReturn(false);
        Mockito.when(repositorioGasto.crear(gasto)).thenReturn(10L);
        ServicioCrearGasto servicioCrearGasto = new ServicioCrearGasto(repositorioGasto);
        // act
        Long idGasto = servicioCrearGasto.ejecutar(gasto);
        //- assert
        assertEquals(10L,idGasto);
        Mockito.verify(repositorioGasto, Mockito.times(1)).crear(gasto);
    }
}
