package com.ceiba.gasto.modelo.entidad;


import lombok.Getter;

import java.time.LocalDateTime;

import static com.ceiba.dominio.ValidadorArgumento.*;

@Getter
public class Gasto {

    private Long id;
    private String identificacionUsuario;
    private Long valorGasto;
    private LocalDateTime fechaGasto;

    private static final String SE_DEBE_INGRESAR_IDENTIFICACION_USUARIO = "Debe ingresar identificacionUsuario";
    private static final String SE_DEBE_INGRESAR_VALOR_GASTO = "Debe ingresar valorGasto";
    private static final String SE_DEBE_INGRESAR_FECHA_GASTO = "Debe ingresar fechaGasto";

    private static final int LONGITUD_MAXIMA_IDENTIFICACION_USUARIO = 15;
    private static final int LONGITUD_MAXIMA_VALOR_GASTO = 7;

    private static final String LA_IDENTIFICACION_USUARIO_DEBE_TENER_UNA_LONGITUD_MAYOR_O_IGUAL_A = "identificacionUsuario no debe ser mayor a 15";
    private static final String EL_VALOR_GASTO_DEBE_TENER_UNA_LONGITUD_MAYOR_O_IGUAL_A = "valorGasto no debe ser mayor a 7";

    private static final String EL_FORMATO_IDENTIFICACION_USUARIO = "La identificacionUsuario debe ser alfanumérico";
    private static final String FORTAMO_ALFANUMERICO = "[A-Za-z0-9]+";

    public Gasto(Long id, String identificacionUsuario, Long valorGasto, LocalDateTime fechaGasto) {

        validarObligatorio(identificacionUsuario, SE_DEBE_INGRESAR_IDENTIFICACION_USUARIO);
        validarObligatorio(valorGasto, SE_DEBE_INGRESAR_VALOR_GASTO);
        validarObligatorio(fechaGasto, SE_DEBE_INGRESAR_FECHA_GASTO);

        validarRegex(identificacionUsuario,FORTAMO_ALFANUMERICO, EL_FORMATO_IDENTIFICACION_USUARIO);

        validarLongitudMaxima(identificacionUsuario, LONGITUD_MAXIMA_IDENTIFICACION_USUARIO, LA_IDENTIFICACION_USUARIO_DEBE_TENER_UNA_LONGITUD_MAYOR_O_IGUAL_A);
        validarLongitudMaxima(valorGasto, LONGITUD_MAXIMA_VALOR_GASTO, EL_VALOR_GASTO_DEBE_TENER_UNA_LONGITUD_MAYOR_O_IGUAL_A);

        this.id = id;
        this.identificacionUsuario = identificacionUsuario;
        this.valorGasto = valorGasto;
        this.fechaGasto = fechaGasto;
    }
}
