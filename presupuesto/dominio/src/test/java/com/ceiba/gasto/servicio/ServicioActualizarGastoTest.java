package com.ceiba.gasto.servicio;

import com.ceiba.BasePrueba;
import com.ceiba.dominio.excepcion.ExcepcionDuplicidad;
import com.ceiba.dominio.excepcion.ExcepcionSinDatos;
import com.ceiba.dominio.excepcion.ExcepcionValorInvalido;
import com.ceiba.gasto.modelo.entidad.Gasto;
import com.ceiba.gasto.puerto.repositorio.RepositorioGasto;
import com.ceiba.gasto.servicio.testdatabuilder.GastoTestDataBuilder;
import com.ceiba.usuario.modelo.entidad.Usuario;
import com.ceiba.usuario.puerto.repositorio.RepositorioUsuario;
import com.ceiba.usuario.servicio.ServicioCrearUsuario;
import com.ceiba.usuario.servicio.testdatabuilder.UsuarioTestDataBuilder;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

class ServicioActualizarGastoTest {

    @Test
    @DisplayName("Deberia actualizar correctamente el gasto el repositorio")
    void deberiaActualizarCorrectamenteEnElRepositorio() {
        // arrange
        Gasto gasto = new GastoTestDataBuilder()
                .conId(1L)
                .conValorGasto(0L)
                .build();
        RepositorioGasto repositorioGasto = Mockito.mock(RepositorioGasto.class);
        Mockito.when(repositorioGasto.existePresupuesto(Mockito.any(Gasto.class))).thenReturn(true);
        Mockito.when(repositorioGasto.existe(Mockito.anyLong())).thenReturn(true);
        ServicioActualizarGasto servicioActualizarGasto = new ServicioActualizarGasto(repositorioGasto);
        // act
        servicioActualizarGasto.ejecutar(gasto);
        //assert
        Mockito.verify(repositorioGasto,Mockito.times(1)).actualizar(gasto);
    }

    @Test
    @DisplayName("Deberia validar la existencia previa del gasto")
    void deberiaValidarLaExistenciaPreviaDelGasto() {
        // arrange
        Gasto gasto = new GastoTestDataBuilder().conId(11L).build();
        RepositorioGasto repositorioGasto = Mockito.mock(RepositorioGasto.class);
        Mockito.when(repositorioGasto.existe(Mockito.anyLong())).thenReturn(false);
        ServicioActualizarGasto servicioActualizarGasto = new ServicioActualizarGasto(repositorioGasto);
        // act - assert
        BasePrueba.assertThrows(()
                -> servicioActualizarGasto.ejecutar(gasto)
                , ExcepcionSinDatos.class,"El gasto no existe en el sistema");
    }

    @Test
    @DisplayName("Deberia validar la existencia previa del presupuesto")
    void deberiaValidarLaExistenciaPreviaDelPresupuesto() {
        // arrange
        Gasto gasto = new GastoTestDataBuilder().conId(1L).build();
        RepositorioGasto repositorioGasto = Mockito.mock(RepositorioGasto.class);
        Mockito.when(repositorioGasto.existePresupuesto(Mockito.any(Gasto.class))).thenReturn(false);
        Mockito.when(repositorioGasto.existe(Mockito.anyLong())).thenReturn(true);
        ServicioActualizarGasto servicioActualizarGasto = new ServicioActualizarGasto(repositorioGasto);
        // act - assert
        BasePrueba.assertThrows(()
                        -> servicioActualizarGasto.ejecutar(gasto)
                , ExcepcionSinDatos.class,"El presupuesto no existe en el sistema");
    }

    @Test
    @DisplayName("No deberia actualizar gasto por superar presupuesto")
    void NoDeberiaCrearGastoPorSuperarPresupuesto() {
        // arrange
        Gasto gasto = new GastoTestDataBuilder()
                .conId(1L)
                .conValorGasto(9999999L)
                .build();
        RepositorioGasto repositorioGasto = Mockito.mock(RepositorioGasto.class);
        Mockito.when(repositorioGasto.existePresupuesto(Mockito.any(Gasto.class))).thenReturn(true);
        Mockito.when(repositorioGasto.existe(Mockito.anyLong())).thenReturn(true);
        ServicioActualizarGasto servicioActualizarGasto = new ServicioActualizarGasto(repositorioGasto);
        // act - assert
        BasePrueba.assertThrows(()
                        -> servicioActualizarGasto.ejecutar(gasto)
                , ExcepcionValorInvalido.class,"Se superó el valor del presupuesto");
    }

}
