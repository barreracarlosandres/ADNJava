package com.ceiba.presupuesto.entidad;

import com.ceiba.BasePrueba;
import com.ceiba.dominio.excepcion.ExcepcionLongitudValor;
import com.ceiba.dominio.excepcion.ExcepcionValorInvalido;
import com.ceiba.dominio.excepcion.ExcepcionValorObligatorio;
import com.ceiba.presupuesto.modelo.entidad.Presupuesto;
import com.ceiba.presupuesto.servicio.testdatabuilder.PresupuestoTestDataBuilder;
import org.apache.commons.lang3.RandomStringUtils;
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
        assertEquals("123", presupuesto.getIdentificacionUsuario());
        assertEquals(123, presupuesto.getValorPresupuesto());
        assertEquals(presupuesto.getFechaPresupuesto(), presupuesto.getFechaPresupuesto());
    }

    @Test
    @DisplayName("No debería ingresar sin identificacionUsuario")
    void deberiaFallarSinIdentificacionUsuario() {

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
    void deberiaFallarSinVarlorPresupuesto() {

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
    void deberiaFallarSinFechaPresupuesto() {

        //Arrange
        PresupuestoTestDataBuilder PresupuestoTestDataBuilder = new PresupuestoTestDataBuilder().conFechaPresupuesto(null).conId(1L);
        //act-assert
        BasePrueba.assertThrows(() -> {
                    PresupuestoTestDataBuilder.build();
                },
                ExcepcionValorObligatorio.class, "Debe ingresar fechaPresupuesto");
    }

    @Test
    @DisplayName("No debería ingresar identificacionUsuario sin tamaño permitido")
    void deberiaFallarSinIdentificacionMayorTamanioPermitido() {
        //Arrange
        PresupuestoTestDataBuilder PresupuestoTestDataBuilder = new PresupuestoTestDataBuilder()
                .conIdentificacionUsuario(getRandomStringAlfabetoDe16()).conId(1L);
        //act-assert
        BasePrueba.assertThrows(() -> {
                    PresupuestoTestDataBuilder.build();
                },
                ExcepcionLongitudValor.class, "identificacionUsuario no debe ser mayor a 15");
    }

    @Test
    @DisplayName("No debería ingresar valorPresupuesto sin tamaño permitido")
    void deberiaFallarSinValorPresupuestoMayorTamanioPermitido() {

        //Arrange
        PresupuestoTestDataBuilder PresupuestoTestDataBuilder = new PresupuestoTestDataBuilder()
                .conValorPresupuesto(getRandomNumeroDe8());
        //act-assert
        BasePrueba.assertThrows(() -> {
                    PresupuestoTestDataBuilder.build();
                },
                ExcepcionLongitudValor.class, "valorPresupuesto no debe ser mayor a 7");
    }

    @Test
    @DisplayName("No debería ingresar identificacionUsuario sin formato alfanumérico ")
    void deberiaFallarSinIdentificacionUsuarioAlafanumerico() {

        //Arrange
        PresupuestoTestDataBuilder PresupuestoTestDataBuilder = new PresupuestoTestDataBuilder()
                .conIdentificacionUsuario("**").conId(1L);
        //act-assert
        BasePrueba.assertThrows(() -> {
                    PresupuestoTestDataBuilder.build();
                },
                ExcepcionValorInvalido.class, "La identificacionUsuario debe ser alfanumérico");
    }

    private static long getRandomNumeroDe8() {
        return Long.parseLong(RandomStringUtils.randomNumeric(8));
    }

    private static  String getRandomStringAlfabetoDe16() {
        return RandomStringUtils.randomAlphabetic(16);
    }
}
