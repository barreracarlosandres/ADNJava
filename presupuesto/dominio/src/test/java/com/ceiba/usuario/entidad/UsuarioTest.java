package com.ceiba.usuario.entidad;

import com.ceiba.BasePrueba;

import com.ceiba.dominio.excepcion.ExcepcionValorObligatorio;
import com.ceiba.usuario.modelo.entidad.Usuario;
import com.ceiba.usuario.servicio.testdatabuilder.UsuarioTestDataBuilder;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.assertEquals;

class UsuarioTest {

    @Test
    @DisplayName("Deberia crear correctamente el usuario")
    void deberiaCrearCorrectamenteElUsuario() {
        // arrange
        //LocalDateTime fechaCreacion = LocalDateTime.now();
        //act
        Usuario usuario = new UsuarioTestDataBuilder().conId(1L).build();
        //assert
        assertEquals(1, usuario.getId());
        assertEquals("Carlos Andrés", usuario.getNombre());
        assertEquals("Barrera Santacruz", usuario.getApellido());
        assertEquals("CC94123123", usuario.getIdentificacionUsuario());
    }

    @Test
    @DisplayName("No debería ingresar un usuario sin nombre")
    void NoDeberiaCrearUsuarioSinNombre() {

        //Arrange
        UsuarioTestDataBuilder usuarioTestDataBuilder = new UsuarioTestDataBuilder().conNombre(null);
        //act-assert
        BasePrueba.assertThrows(() -> {
                    usuarioTestDataBuilder.build();
                },
                ExcepcionValorObligatorio.class, "Debe ingresar el atributo nombre");
    }

    @Test
    @DisplayName("No debería ingresar un usuario sin atributo apellido")
    void NoDeberiaIngresarUsuarioSinApellido() {

        //Arrange
        UsuarioTestDataBuilder usuarioTestDataBuilder = new UsuarioTestDataBuilder().conApellido(null);
        //act-assert
        BasePrueba.assertThrows(() -> {
                    usuarioTestDataBuilder.build();
                },
                ExcepcionValorObligatorio.class, "Debe ingresar el atributo apellido");
    }

    @Test
    @DisplayName("No debería ingresar un usuario sin atributo identificacionUsuario")
    void NoDeberiaIngresarUsuarioSinIdentificacionUsuario() {

        //Arrange
        UsuarioTestDataBuilder usuarioTestDataBuilder = new UsuarioTestDataBuilder().conIdentificacionUsuario(null);
        //act-assert
        BasePrueba.assertThrows(() -> {
                    usuarioTestDataBuilder.build();
                },
                ExcepcionValorObligatorio.class, "Debe ingresar identificacionUsuario");
    }
}
