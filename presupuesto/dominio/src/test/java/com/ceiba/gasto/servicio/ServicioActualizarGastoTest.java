package com.ceiba.gasto.servicio;

import com.ceiba.BasePrueba;
import com.ceiba.dominio.excepcion.ExcepcionSinDatos;
import com.ceiba.gasto.modelo.entidad.Gasto;
import com.ceiba.gasto.puerto.repositorio.RepositorioGasto;
import com.ceiba.gasto.servicio.testdatabuilder.GastoTestDataBuilder;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

class ServicioActualizarGastoTest {

    @Test
    @DisplayName("Deberia validar la existencia previa del gasto")
    void deberiaValidarLaExistenciaPreviaDelGasto() {
        // arrange
        Gasto gasto = new GastoTestDataBuilder().conId(3L).build();
        RepositorioGasto repositorioGasto = Mockito.mock(RepositorioGasto.class);
        Mockito.when(repositorioGasto.existe(Mockito.anyLong())).thenReturn(false);
        ServicioActualizarGasto servicioActualizarGasto = new ServicioActualizarGasto(repositorioGasto);
        // act - assert
        BasePrueba.assertThrows(()
                -> servicioActualizarGasto.ejecutar(gasto)
                , ExcepcionSinDatos.class,"El gasto no existe en el sistema");
    }

    @Test
    @DisplayName("Deberia actualizar correctamente el gasto el repositorio")
    void deberiaActualizarCorrectamenteEnElRepositorio() {
        // arrange
        Gasto gasto = new GastoTestDataBuilder().conId(1L).build();
        RepositorioGasto repositorioGasto = Mockito.mock(RepositorioGasto.class);
        Mockito.when(repositorioGasto.existe(Mockito.anyLong())).thenReturn(true);
        ServicioActualizarGasto servicioActualizarGasto = new ServicioActualizarGasto(repositorioGasto);
        // act
        servicioActualizarGasto.ejecutar(gasto);
        //assert
        Mockito.verify(repositorioGasto,Mockito.times(1)).actualizar(gasto);
    }
}
