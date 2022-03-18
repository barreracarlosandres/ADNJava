package com.ceiba.usuario.servicio;

import com.ceiba.BasePrueba;
import com.ceiba.dominio.excepcion.ExcepcionDuplicidad;
import com.ceiba.dominio.excepcion.ExcepcionLongitudValor;
import com.ceiba.dominio.excepcion.ExcepcionValorInvalido;
import com.ceiba.usuario.modelo.entidad.Usuario;
import com.ceiba.usuario.puerto.repositorio.RepositorioUsuario;
import com.ceiba.usuario.servicio.testdatabuilder.UsuarioTestDataBuilder;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ServicioCrearUsuarioTest {

    @Test
    @DisplayName("Deberia Crear el usuario de manera correcta")
    void deberiaCrearElUsuarioDeManeraCorrecta() {
        // arrange
        Usuario usuario = new UsuarioTestDataBuilder().build();
        RepositorioUsuario repositorioUsuario = Mockito.mock(RepositorioUsuario.class);
        Mockito.when(repositorioUsuario.existePorIdentificacionUsuario(Mockito.anyString())).thenReturn(false);
        Mockito.when(repositorioUsuario.crear(usuario)).thenReturn(10L);
        ServicioCrearUsuario servicioCrearUsuario = new ServicioCrearUsuario(repositorioUsuario);
        // act
        Long idUsuario = servicioCrearUsuario.ejecutar(usuario);
        //- assert
        assertEquals(10L,idUsuario);
        Mockito.verify(repositorioUsuario, Mockito.times(1)).crear(usuario);
    }

    @Test
    @DisplayName("Deberia lanzar una exepcion cuando se valide la existencia del Usuario")
    void deberiaLanzarUnaExepcionCuandoSeValideLaExistenciaDelUsuario() {
        // arrange
        Usuario usuario = new UsuarioTestDataBuilder().build();
        RepositorioUsuario repositorioUsuario = Mockito.mock(RepositorioUsuario.class);
        Mockito.when(repositorioUsuario.existePorIdentificacionUsuario(Mockito.anyString())).thenReturn(true);
        ServicioCrearUsuario servicioCrearUsuario = new ServicioCrearUsuario(repositorioUsuario);
        // act - assert
        BasePrueba.assertThrows(() -> servicioCrearUsuario.ejecutar(usuario), ExcepcionDuplicidad.class,"El usuario ya existe en el sistema");
    }

    @Test
    @DisplayName("No debería sin tamaño identificacionUsuario mayor al permitido")
    void NoDeberiaIngresarUsuarioConIdentificacionUsuarioMayorAlPermitido() {

        //Arrange
        UsuarioTestDataBuilder usuarioTestDataBuilder = new UsuarioTestDataBuilder()
                .conIdentificacionUsuario(getRandomStringAlfabeto(16));
        //act-assert
        BasePrueba.assertThrows(() -> {
                    usuarioTestDataBuilder.build();
                },
                ExcepcionLongitudValor.class, "identificacionUsuario no debe ser mayor a 15");
    }

    @Test
    @DisplayName("No debería sin tamaño nombre mayor al permitido")
    void NoDeberiaIngresarUsuarioConNombreMayorAlPermitido() {

        //Arrange
        UsuarioTestDataBuilder usuarioTestDataBuilder = new UsuarioTestDataBuilder()
                .conNombre(getRandomStringAlfabeto(101));
        //act-assert
        BasePrueba.assertThrows(() -> {
                    usuarioTestDataBuilder.build();
                },
                ExcepcionLongitudValor.class, "el atributo nombre no debe ser mayor a 100");
    }

    @Test
    @DisplayName("No debería sin tamaño apellido mayor al permitido")
    void NoDeberiaIngresarUsuarioConApellidoMayorAlPermitido() {

        //Arrange
        UsuarioTestDataBuilder usuarioTestDataBuilder = new UsuarioTestDataBuilder()
                .conApellido(getRandomStringAlfabeto(101));
        //act-assert
        BasePrueba.assertThrows(() -> {
                    usuarioTestDataBuilder.build();
                },
                ExcepcionLongitudValor.class, "el atributo apellido no debe ser mayor a 100");
    }

    @Test
    @DisplayName("No debería ingresar Usuario con nombre diferente a alfabético ,espacio y acentos")
    void NodeberiaIngresarUsuarioConNombreSinFormatoPermitido() {

        //Arrange
        UsuarioTestDataBuilder usuarioTestDataBuilder = new UsuarioTestDataBuilder()
                .conNombre("Juan *");
        //act-assert
        BasePrueba.assertThrows(() -> {
                    usuarioTestDataBuilder.build();
                },
                ExcepcionValorInvalido.class, "El nombre puede incluir caracteres del alfabeto, espacio y acentos");
    }

    @Test
    @DisplayName("No debería ingresar Usuario con apellido diferente a alfabético ,espacio y acentos")
    void NodeberiaIngresarUsuarioConApellidoSinFormatoPermitido() {

        //Arrange
        UsuarioTestDataBuilder usuarioTestDataBuilder = new UsuarioTestDataBuilder()
                .conApellido("Perez *");
        //act-assert
        BasePrueba.assertThrows(() -> {
                    usuarioTestDataBuilder.build();
                },
                ExcepcionValorInvalido.class, "El apellido puede incluir caracteres del alfabeto, espacio y acentos");
    }

    @Test
    @DisplayName("No debería ingresar Usuario con identificacionUsuario diferente a alfanumérico")
    void NodeberiaIngresarUsuarioConIdentificacionUsuarioSinFormatoPermitido() {

        //Arrange
        UsuarioTestDataBuilder usuarioTestDataBuilder = new UsuarioTestDataBuilder()
                .conIdentificacionUsuario("**");
        //act-assert
        BasePrueba.assertThrows(() -> {
                    usuarioTestDataBuilder.build();
                },
                ExcepcionValorInvalido.class, "La identificacionUsuario debe ser alfanumérico");
    }

    private static  String getRandomStringAlfabeto(int tammano) {
        return RandomStringUtils.randomAlphabetic(tammano);
    }
}
