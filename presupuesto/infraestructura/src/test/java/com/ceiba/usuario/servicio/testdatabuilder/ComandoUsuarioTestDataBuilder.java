package com.ceiba.usuario.servicio.testdatabuilder;

import com.ceiba.usuario.comando.ComandoUsuario;

import java.time.LocalDateTime;
import java.util.UUID;

public class ComandoUsuarioTestDataBuilder {

    private Long id;
    private String nombre;
    private String clave;
    private LocalDateTime fecha;

    private String identificacionUsuario;
    private LocalDateTime fechaGasto;
    private int valorGasto;

    public ComandoUsuarioTestDataBuilder() {
        nombre = UUID.randomUUID().toString();
        clave = "1234";
        fecha = LocalDateTime.now();
    }

    /*public ComandoGastoTestDataBuilder() {
        identificacionUsuario = "94303068";
        fechaGasto = LocalDateTime.now();
        valorGasto = 500000;
    }*/

    public ComandoUsuarioTestDataBuilder conNombre(String nombre) {
        this.nombre = nombre;
        return this;
    }

    public ComandoUsuario build() {
        return new ComandoUsuario(id,nombre, clave,fecha);
    }
}
