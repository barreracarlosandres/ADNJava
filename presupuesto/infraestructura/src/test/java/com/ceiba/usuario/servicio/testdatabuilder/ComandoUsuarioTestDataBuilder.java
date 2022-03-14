package com.ceiba.usuario.servicio.testdatabuilder;

import com.ceiba.usuario.comando.ComandoUsuario;
import org.apache.commons.lang3.RandomStringUtils;

public class ComandoUsuarioTestDataBuilder {

    private Long id;
    private String nombre;
    private String apellido;
    private String identificacionUsuario;

    public ComandoUsuarioTestDataBuilder() {
        nombre = RandomStringUtils.randomAlphabetic(100);
        apellido = RandomStringUtils.randomAlphabetic(100);
        identificacionUsuario = RandomStringUtils.randomAlphabetic(15);
    }

    public ComandoUsuario build() {
        return new ComandoUsuario(id, nombre, apellido, identificacionUsuario);
    }
}
