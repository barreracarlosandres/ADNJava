package com.ceiba.presupuesto.servicio;

import com.ceiba.BasePrueba;
import com.ceiba.dominio.excepcion.ExcepcionSinDatos;
import com.ceiba.presupuesto.modelo.entidad.Presupuesto;
import com.ceiba.presupuesto.puerto.repositorio.RepositorioPresupuesto;
import com.ceiba.presupuesto.servicio.testdatabuilder.PresupuestoTestDataBuilder;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.time.LocalDateTime;

class ServicioActualizarPresupuestoTest {

    @Test
    @DisplayName("Deberia actualizar actualizar el presupuesto")
    void deberiaActualizarCorrectamenteEnElRepositorio() {
        Presupuesto presupuesto = new PresupuestoTestDataBuilder()
                .conId(1L)
                .conValorPresupuesto(8000L)
                .build();
        RepositorioPresupuesto repositorioPresupuesto = Mockito.mock(RepositorioPresupuesto.class);
        Mockito.when(repositorioPresupuesto.existeUsuario(Mockito.anyString())).thenReturn(true);
        Mockito.when(repositorioPresupuesto.existe(Mockito.anyLong())).thenReturn(true);
        ServicioActualizarPresupuesto servicioActualizarPresupuesto = new ServicioActualizarPresupuesto(repositorioPresupuesto);
        // act
        servicioActualizarPresupuesto.ejecutar(presupuesto);
        //- assert
        Mockito.verify(repositorioPresupuesto, Mockito.times(1)).actualizar(presupuesto);
    }

    @Test
    @DisplayName("Deberia validar la existencia previa del usuario")
    void deberiaValidarLaExistenciaPreviaDelUsuario() {
        // arrange
        Presupuesto presupuesto = new PresupuestoTestDataBuilder().conIdentificacionUsuario("94").build();
        RepositorioPresupuesto repositorioPresupuesto = Mockito.mock(RepositorioPresupuesto.class);
        Mockito.when(repositorioPresupuesto.existeUsuario(Mockito.anyString())).thenReturn(true);
        Mockito.when(repositorioPresupuesto.existePresupuesto(Mockito.anyString(), Mockito.any(LocalDateTime.class))).thenReturn(false);
        ServicioActualizarPresupuesto servicioActualizarPresupuesto = new ServicioActualizarPresupuesto(repositorioPresupuesto);
        // act - assert
        BasePrueba.assertThrows(() ->
                        servicioActualizarPresupuesto.ejecutar(presupuesto)
                , ExcepcionSinDatos.class,"El presupuesto no existe en el sistema");
    }
}
