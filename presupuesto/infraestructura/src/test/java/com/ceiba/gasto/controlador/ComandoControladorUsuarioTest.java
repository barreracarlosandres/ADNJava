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

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
@WebMvcTest(ComandoControladorGasto.class)
@ContextConfiguration(classes= ApplicationMock.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
class ComandoControladorUsuarioTest {

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private MockMvc mocMvc;

    @Test
    @DisplayName("Deberia ingresar un gasto")
    void deberiaIngresarUnGasto() throws Exception{
        // arrange
        ComandoGasto gasto = new ComandoGastoTestDataBuilder().build();
        // act - assert
        mocMvc.perform(post("/gastos")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(gasto)))
                .andExpect(status().isOk())
                .andExpect(content().json("{'valor': 2}"));
    }

    @Test
    @DisplayName("Deberia actualizar un gasto")
    void deberiaActualizarUnGasto() throws Exception{
        // arrange
        Long id = 1L;
        ComandoGasto gasto = new ComandoGastoTestDataBuilder().build();
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

}
