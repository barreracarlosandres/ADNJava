package com.ceiba.presupuesto.entidad;

import com.ceiba.BasePrueba;
import com.ceiba.dominio.excepcion.ExcepcionValorObligatorio;
import com.ceiba.presupuesto.modelo.entidad.Presupuesto;
import com.ceiba.presupuesto.servicio.testdatabuilder.PresupuestoTestDataBuilder;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PresupuestoTest {

    @Test
    @DisplayName("Deberia ingresar un Presupuesto")
    void deberiaCrearCorrectamenteElPresupuesto() {
        // arrange
        //act
        Presupuesto presupuesto = new PresupuestoTestDataBuilder().conId(1L).build();
        //assert
        assertEquals(1, presupuesto.getId());
        assertEquals("94123123", presupuesto.getIdentificacionUsuario());
        assertEquals(500, presupuesto.getValorPresupuesto());
        assertEquals(presupuesto.getFechaPresupuesto(), presupuesto.getFechaPresupuesto());
    }

    @Test
    @DisplayName("No debería ingresar sin identificacionUsuario")
    void NoDeberiaCrearPresupuestoSinIdentificacionUsuario() {

        //Arrange
        PresupuestoTestDataBuilder PresupuestoTestDataBuilder = new PresupuestoTestDataBuilder().conIdentificacionUsuario(null).conId(1L);
        //act-assert
        BasePrueba.assertThrows(() -> {
                    PresupuestoTestDataBuilder.build();
                },
                ExcepcionValorObligatorio.class, "Debe ingresar identificacionUsuario");
    }

    @Test
    @DisplayName("No debería ingresar sin valorPresupuesto")
    void NoDeberiaCrearPresupuestoSinVarlorPresupuesto() {

        //Arrange
        PresupuestoTestDataBuilder PresupuestoTestDataBuilder = new PresupuestoTestDataBuilder().conValorPresupuesto(null).conId(1L);
        //act-assert
        BasePrueba.assertThrows(() -> {
                    PresupuestoTestDataBuilder.build();
                },
                ExcepcionValorObligatorio.class, "Debe ingresar valorPresupuesto");
    }

    @Test
    @DisplayName("No debería ingresar sin fechaPresupuesto")
    void NoDeberiaCrearPresupuestoSinFechaPresupuesto() {

        //Arrange
        PresupuestoTestDataBuilder PresupuestoTestDataBuilder = new PresupuestoTestDataBuilder().conFechaPresupuesto(null).conId(1L);
        //act-assert
        BasePrueba.assertThrows(() -> {
                    PresupuestoTestDataBuilder.build();
                },
                ExcepcionValorObligatorio.class, "Debe ingresar fechaPresupuesto");
    }
}
