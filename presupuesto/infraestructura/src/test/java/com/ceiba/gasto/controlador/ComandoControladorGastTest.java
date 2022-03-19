package com.ceiba.gasto.controlador;

import com.ceiba.ApplicationMock;
import com.ceiba.gasto.comando.ComandoGasto;
import com.ceiba.gasto.servicio.testdatabuilder.ComandoGastoTestDataBuilder;
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
@WebMvcTest(ComandoControladorGasto.class)
@ContextConfiguration(classes= ApplicationMock.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
class ComandoControladorGastTest {

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private MockMvc mocMvc;

    @Test
    @DisplayName("Deberia ingresar un gasto")
    void deberiaIngresarUnGasto() throws Exception{
        // arrange
        ComandoGasto gasto = new ComandoGastoTestDataBuilder()
                .conFechaGasto(getFechaBaseExistente())
                .conIdentificacionUsuario(getIdentifacionUsuarioExistente())
                .conValorGasto(1L)
                .build();
        // act - assert
        mocMvc.perform(post("/gastos")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(gasto)))
                .andExpect(status().isOk())
                .andExpect(content().json("{'valor': 2}"));
    }

    @Test
    @DisplayName("No deberia actualzia un gasto por que no existe gasto")
    void NoDeberiaCrearGastoPorQueNoExistente() throws Exception{
        // arrange
        Long id = 10L;
        ComandoGasto gasto = new ComandoGastoTestDataBuilder()
                .conIdentificacionUsuario(getIdentifacionUsuarioExistente())
                .conFechaGasto(getFechaBaseExistente())
                .conValorGasto(1L)
                .build();
        // act - assert
        mocMvc.perform(put("/gastos/{id}",id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(gasto)))
                .andExpect(content().json("{'mensaje': 'El gasto no existe en el sistema'}"));
    }

    @Test
    @DisplayName("No deberia ingresar un gasto por presupusto no existente")
    void NoDeberiaCrearGastoPorPresupuestoNoExistente() throws Exception{
        // arrange
        ComandoGasto gasto = new ComandoGastoTestDataBuilder()
                .build();
        // act - assert
        mocMvc.perform(post("/gastos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(gasto)))
                .andExpect(status().isNotFound())
                .andExpect(content().json("{'mensaje': 'No existe un presupuesto para aplicar el gasto'}"));
    }

    @Test
    @DisplayName("No deberia ingresar un gasto por presupusto no existente")
    void NoDeberiaCrearGastoPorSuperarElPresupuesto() throws Exception{
        // arrange
        ComandoGasto gasto = new ComandoGastoTestDataBuilder()
                .conFechaGasto(getFechaBaseExistente())
                .conIdentificacionUsuario(getIdentifacionUsuarioExistente())
                .conValorGasto(getValorGastoMayorAlPresupuesto())
                .build();
        // act - assert
        mocMvc.perform(post("/gastos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(gasto)))
                .andExpect(status().isBadRequest())
                .andExpect(content().json("{'mensaje': 'Se superó el valor del presupuesto'}"));
    }

    @Test
    @DisplayName("Deberia actualizar un gasto")
    void deberiaActualizarUnGasto() throws Exception{
        // arrange
        Long id = 1L;
        ComandoGasto gasto = new ComandoGastoTestDataBuilder()
                .conIdentificacionUsuario(getIdentifacionUsuarioExistente())
                .conFechaGasto(getFechaBaseExistente())
                .conValorGasto(1L)
                .build();
        // act - assert
        mocMvc.perform(put("/gastos/{id}",id)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(gasto)))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("Deberia eliminar un gasto")
    void deberiaEliminarUnGasto() throws Exception {
        // arrange
        Long id = 1L;
        // act - assert
        mocMvc.perform(delete("/gastos/{id}",id)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        mocMvc.perform(get("/gastos")
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

    private long getValorGastoMayorAlPresupuesto() {
        return 9999999L;
    }

}
