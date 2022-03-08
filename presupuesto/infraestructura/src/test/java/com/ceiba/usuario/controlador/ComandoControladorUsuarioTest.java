package com.ceiba.usuario.controlador;

import com.ceiba.ApplicationMock;
import com.ceiba.usuario.comando.ComandoUsuario;
import com.ceiba.usuario.servicio.testdatabuilder.ComandoUsuarioTestDataBuilder;
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
@WebMvcTest(ComandoControladorUsuario.class)
@ContextConfiguration(classes= ApplicationMock.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
class ComandoControladorUsuarioTest {

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private MockMvc mocMvc;

    /*@Test
    @DisplayName("Debería reportar el gasto del mes para un usuario")
    void deberiaReportarGasto() throws Exception{
        // arrange
        ComandoUsuario usuario = new ComandoUsuarioTestDataBuilder().build();
        // act - assert
        mocMvc.perform(post("/presupuesto/gastos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(usuario)))
                .andExpect(status().isOk())
                .andExpect(content().json("json{'identificacionUsuario' : \"94303068\",'fechaGasto' : \"2022-03\", 'valorGasto' : 500000 }"));
    }*/

    @Test
    @DisplayName("Deberia crear un usuario")
    void deberiaCrearUnUsuario() throws Exception{
        // arrange
        ComandoUsuario usuario = new ComandoUsuarioTestDataBuilder().build();
        // act - assert
        mocMvc.perform(post("/gasto")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(usuario)))
                .andExpect(status().isOk())
                .andExpect(content().json("{'valor': 2}"));
    }

    @Test
    @DisplayName("Deberia actualizar un usuario")
    void deberiaActualizarUnUsuario() throws Exception{
        // arrange
        Long id = 1L;
        ComandoUsuario usuario = new ComandoUsuarioTestDataBuilder().build();
        // act - assert
        mocMvc.perform(put("/gasto/{id}",id)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(usuario)))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("Deberia eliminar un usuario")
    void deberiaEliminarUnUsuario() throws Exception {
        // arrange
        Long id = 1L;
        // act - assert
        mocMvc.perform(delete("/gasto/{id}",id)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        mocMvc.perform(get("/gasto")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(0)));
    }

}
