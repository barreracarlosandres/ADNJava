package com.ceiba.presupuesto.controlador;

import com.ceiba.ApplicationMock;
import com.ceiba.presupuesto.comando.ComandoPresupuesto;
import com.ceiba.presupuesto.servicio.testdatabuilder.ComandoPresupuestoTestDataBuilder;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
@WebMvcTest(ComandoControladorPresupuesto.class)
@ContextConfiguration(classes= ApplicationMock.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
class ComandoControladorPresupuestoTest {

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private MockMvc mocMvc;

    @Test
    @DisplayName("Deberia ingresar un presupuesto")
    void deberiaIngresarUnPresupuesto() throws Exception{
        // arrange
        ComandoPresupuesto presupuesto = new ComandoPresupuestoTestDataBuilder()
                .conIdentificacionUsuario(getIdentifacionUsuarioExistente())
                .conFechaPresupuesto(getFechaBaseNoExistente())
                .build();
        // act - assert
        mocMvc.perform(post("/presupuestos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(presupuesto)))
                        .andExpect(status().isOk())
                        .andExpect(content().json("{'valor': 2}"));
    }

    @Test
    @DisplayName("No deberia ingresar un presupuesto dado que no existe el usuario")
    void NoDeberiaIngresarUnPresupuestoDadoQueNoExisteElUsuario() throws Exception{
        // arrange
        ComandoPresupuesto presupuesto = new ComandoPresupuestoTestDataBuilder()
                .conIdentificacionUsuario("000000")
                .build();
        // act - assert
        mocMvc.perform(post("/presupuestos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(presupuesto)))
                        .andExpect(status().isNotFound())
                        .andExpect(content().json("{'mensaje': 'El usuario no existe'}"));
    }

    @Test
    @DisplayName("No deberia ingresar un presupuesto dado que ya existe el presupuesto")
    void NoDeberiaIngresarUnPresupuestoDadoQueEstaDupicadoElPresupuesto() throws Exception{

        LocalDateTime fechaPresupuesto = getFechaBaseExistente();
        // arrange
        ComandoPresupuesto presupuesto = new ComandoPresupuestoTestDataBuilder()
                .conIdentificacionUsuario(getIdentifacionUsuarioExistente())
                .conFechaPresupuesto(getFechaBaseExistente())
                .build();
        // act - assert
        mocMvc.perform(post("/presupuestos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(presupuesto)))
                        .andExpect(status().isBadRequest())
                        .andExpect(content().json("{'mensaje': 'El presupuesto ya existe en el sistema'}"));
    }

    @Test
    @DisplayName("Deberia actualizar un presupuesto existente")
    void deberiaActualizarUnPresupuesto() throws Exception{
        // arrange
        Long id = 1L;
        ComandoPresupuesto presupuesto = new ComandoPresupuestoTestDataBuilder()
                .conVarlorPresupuesto(500L)
                .build();
        // act - assert
        mocMvc.perform(put("/presupuestos/{id}",id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(presupuesto)))
                        .andExpect(status().isOk());
    }

    @Test
    @DisplayName("Deberia actualizar un presupuesto existente")
    void NoDeberiaActualizarUnPresupuestoDadoQueNoExiste() throws Exception{
        // arrange
        Long id = 10L;
        ComandoPresupuesto presupuesto = new ComandoPresupuestoTestDataBuilder().build();
        // act - assert
        mocMvc.perform(put("/presupuestos/{id}",id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(presupuesto)))
                .andExpect(status().isNotFound())
                .andExpect(content().json("{'mensaje': 'El presupuesto no existe en el sistema'}"));;
    }

    @Test
    @DisplayName("Deberia eliminar un presupuesto")
    void deberiaEliminarUnGasto() throws Exception {
        // arrange
        Long id = 1L;
        // act - assert
        mocMvc.perform(delete("/presupuestos/{id}",id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                        .andExpect(status().isOk());

        mocMvc.perform(get("/presupuestos")
                        .contentType(MediaType.APPLICATION_JSON))
                        .andExpect(status().isOk())
                        .andExpect(jsonPath("$", hasSize(0)));
    }

    private static LocalDateTime getFechaBaseExistente() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime fechaPresupuesto = LocalDateTime.parse("2022-03-08 17:00:00", formatter);
        return fechaPresupuesto;
    }

    private static LocalDateTime getFechaBaseNoExistente() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime fechaPresupuesto = LocalDateTime.parse("1500-01-01 00:00:00", formatter);
        return fechaPresupuesto;
    }

    private static String getIdentifacionUsuarioExistente() {
        return "94123123";
    }

}
