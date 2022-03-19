package com.ceiba.usuario.servicio.testdatabuilder;

import com.ceiba.usuario.modelo.entidad.Usuario;

public class UsuarioTestDataBuilder {

    private Long id;
    private String nombre;
    private String apellido;
    private String identificacionUsuario;

    public UsuarioTestDataBuilder() {
        nombre = "Carlos Andrés";
        apellido = "Barrera Santacruz";
        identificacionUsuario = "94123123";
    }

    public UsuarioTestDataBuilder conNombre(String nombre) {
        this.nombre = nombre;
        return this;
    }

    public UsuarioTestDataBuilder conId(Long id) {
        this.id = id;
        return this;
    }

    public UsuarioTestDataBuilder conApellido(String apellido) {
        this.apellido = apellido;
        return this;
    }

    public UsuarioTestDataBuilder conIdentificacionUsuario(String identificacionUsuario) {
        this.identificacionUsuario = identificacionUsuario;
        return this;
    }

    public Usuario build() {
        return new Usuario(id,nombre, apellido, identificacionUsuario );
    }
}
