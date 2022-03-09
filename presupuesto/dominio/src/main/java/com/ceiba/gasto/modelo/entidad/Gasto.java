package com.ceiba.gasto.modelo.entidad;


import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class Gasto {

    private static final String SE_DEBE_INGRESAR_LA_FECHA_CREACION = "Se debe ingresar la fecha de creación";
    private static final String LA_CLAVE_DEBE_TENER_UNA_LONGITUD_MAYOR_O_IGUAL_A = "La clave debe tener una longitud mayor o igual a %s";
    private static final String SE_DEBE_INGRESAR_LA_CLAVE = "Se debe ingresar la clave";
    private static final String SE_DEBE_INGRESAR_EL_NOMBRE_DE_USUARIO = "Se debe ingresar el nombre de usuario";

    private static final int LONGITUD_MINIMA_CLAVE = 4;

    private Long id;
    private String identificacionUsuario;
    private int valorGasto;
    private LocalDateTime fechaGasto;

    public Gasto(Long id, String identificacionUsuario, int valorGasto, LocalDateTime fechaGasto) {

        this.id = id;
        this.identificacionUsuario = identificacionUsuario;
        this.valorGasto = valorGasto;
        this.fechaGasto = fechaGasto;
    }

}
