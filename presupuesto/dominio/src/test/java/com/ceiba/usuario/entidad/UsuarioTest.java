package com.ceiba.usuario.entidad;

import com.ceiba.BasePrueba;
import com.ceiba.dominio.excepcion.ExcepcionLongitudValor;
import com.ceiba.dominio.excepcion.ExcepcionValorInvalido;
import com.ceiba.dominio.excepcion.ExcepcionValorObligatorio;
import com.ceiba.usuario.modelo.entidad.Usuario;
import com.ceiba.usuario.servicio.testdatabuilder.UsuarioTestDataBuilder;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.apache.commons.lang3.RandomStringUtils;

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
        assertEquals("nombreTest", usuario.getNombre());
        assertEquals("apellidoTest", usuario.getApellido());
        assertEquals("identTest", usuario.getIdentificacionUsuario());
    }

    @Test
    @DisplayName("No debería ingresar sin nombre")
    void deberiaFallarSinNombreDeUsuario() {

        //Arrange
        UsuarioTestDataBuilder usuarioTestDataBuilder = new UsuarioTestDataBuilder().conNombre(null).conId(1L);
        //act-assert
        BasePrueba.assertThrows(() -> {
                    usuarioTestDataBuilder.build();
                },
                ExcepcionValorObligatorio.class, "Debe ingresar nombre");
    }

    @Test
    @DisplayName("No debería ingresar sin apellido")
    void deberiaFallarSinApellido() {

        //Arrange
        UsuarioTestDataBuilder usuarioTestDataBuilder = new UsuarioTestDataBuilder().conApellido(null).conId(1L);
        //act-assert
        BasePrueba.assertThrows(() -> {
                    usuarioTestDataBuilder.build();
                },
                ExcepcionValorObligatorio.class, "Debe ingresar apellido");
    }

    @Test
    @DisplayName("debe fallar sin identificacionUsuario")
    void deberiaFallarSinIdentificacionUsuario() {

        //Arrange
        UsuarioTestDataBuilder usuarioTestDataBuilder = new UsuarioTestDataBuilder().conIdentificacionUsuario(null).conId(1L);
        //act-assert
        BasePrueba.assertThrows(() -> {
                    usuarioTestDataBuilder.build();
                },
                ExcepcionValorObligatorio.class, "Debe ingresar identificacionUsuario");
    }

    @Test
    @DisplayName("debe fallar sin tamaño nombre")
    void deberiaFallarSinTamanioNombre() {

        //Arrange
        UsuarioTestDataBuilder usuarioTestDataBuilder = new UsuarioTestDataBuilder()
                .conNombre(RandomStringUtils.randomAlphabetic(101))
                .conId(1L);
        //act-assert
        BasePrueba.assertThrows(() -> {
                    usuarioTestDataBuilder.build();
                },
                ExcepcionLongitudValor.class, "nombre no debe ser mayor a 100");
    }

    @Test
    @DisplayName("debe fallar sin tamaño apellido")
    void deberiaFallarSinTamanioApellido() {

        //Arrange
        UsuarioTestDataBuilder usuarioTestDataBuilder = new UsuarioTestDataBuilder()
                .conApellido(RandomStringUtils.randomAlphabetic(101))
                .conId(1L);
        //act-assert
        BasePrueba.assertThrows(() -> {
                    usuarioTestDataBuilder.build();
                },
                ExcepcionLongitudValor.class, "apellido no debe ser mayor a 100");
    }

    @Test
    @DisplayName("debe fallar sin tamaño identificacionUsuario")
    void deberiaFallarSinTamanioIdentificacionUsuario() {

        //Arrange
        UsuarioTestDataBuilder usuarioTestDataBuilder = new UsuarioTestDataBuilder()
                .conIdentificacionUsuario(RandomStringUtils.randomAlphabetic(101))
                .conId(1L);
        //act-assert
        BasePrueba.assertThrows(() -> {
                    usuarioTestDataBuilder.build();
                },
                ExcepcionLongitudValor.class, "identificacionUsuario no debe ser mayor a 15");
    }

    @Test
    @DisplayName("debe fallar con nombre no alfanumérico")
    void deberiaFallarNombreNoAlfanumerico() {

        //Arrange
        UsuarioTestDataBuilder usuarioTestDataBuilder = new UsuarioTestDataBuilder()
                .conNombre("**").conId(1L);
        //act-assert
        BasePrueba.assertThrows(() -> {
                    usuarioTestDataBuilder.build();
                },
                ExcepcionValorInvalido.class, "El nombre debe ser alfanumérico");
    }

    @Test
    @DisplayName("debe fallar con apellido no alfanumérico")
    void deberiaFallarApellidoNoAlfanumerico() {

        //Arrange
        UsuarioTestDataBuilder usuarioTestDataBuilder = new UsuarioTestDataBuilder()
                .conApellido("**").conId(1L);
        //act-assert
        BasePrueba.assertThrows(() -> {
                    usuarioTestDataBuilder.build();
                },
                ExcepcionValorInvalido.class, "El apellido debe ser alfanumérico");
    }

    @Test
    @DisplayName("debe fallar con identificacionUsuario no alfanumérico")
    void deberiaFallarIdentificacionUsuarioNoAlfanumerico() {

        //Arrange
        UsuarioTestDataBuilder usuarioTestDataBuilder = new UsuarioTestDataBuilder()
                .conIdentificacionUsuario("**").conId(1L);
        //act-assert
        BasePrueba.assertThrows(() -> {
                    usuarioTestDataBuilder.build();
                },
                ExcepcionValorInvalido.class, "La identificacionUsuario debe ser alfanumérico");
    }
}
