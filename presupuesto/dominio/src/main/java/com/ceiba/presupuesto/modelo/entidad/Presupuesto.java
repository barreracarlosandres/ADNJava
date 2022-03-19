package com.ceiba.presupuesto.modelo.entidad;


import lombok.Getter;

import java.time.LocalDateTime;

import static com.ceiba.dominio.ValidadorArgumento.*;

@SuppressWarnings("FieldMayBeFinal")
@Getter
public class Presupuesto {

    private Long id;
    private String identificacionUsuario;
    private Long valorPresupuesto;
    private LocalDateTime fechaPresupuesto;

    private static final String SE_DEBE_INGRESAR_IDENTIFICACION_USUARIO = "Debe ingresar identificacionUsuario";
    private static final String SE_DEBE_INGRESAR_VALOR_PRESUPUESTO = "Debe ingresar valorPresupuesto";
    private static final String SE_DEBE_INGRESAR_FECHA_PRESUPUESTO = "Debe ingresar fechaPresupuesto";

    private static final int LONGITUD_MAXIMA_IDENTIFICACION_USUARIO = 15;
    private static final int LONGITUD_MAXIMA_VALOR_PRESUPUESTO = 7;

    private static final String LA_IDENTIFICACION_USUARIO_DEBE_TENER_UNA_LONGITUD_MAYOR_O_IGUAL_A = String.format("identificacionUsuario no debe ser mayor a %s",
                                    LONGITUD_MAXIMA_IDENTIFICACION_USUARIO);
    private static final String EL_VALOR_PRESUPUESTO_DEBE_TENER_UNA_LONGITUD_MAYOR_O_IGUAL_A = String.format("valorPresupuesto no debe ser mayor a %s",
                                    LONGITUD_MAXIMA_VALOR_PRESUPUESTO);

    private static final String FORTAMATO_ALFANUMERICO = "[A-Za-z0-9]+";
    private static final String FORTAMATO_FECHA_PRESUPUESTO = "^(\\d{4})/[0-1][0-9]$";

    private static final String EL_FORMATO_IDENTIFICACION_USUARIO = "La identificacionUsuario debe ser alfanumérico";
    private static final String EL_FORMATO_VALOR_PRESUPUESTO = "valorPresupuesto debe ser numérico";
    private static final String EL_FORTAMATO_FECHA_PRESUPUESTO = "fechaPresupuesto debe ser YYYY/MM";

    public Presupuesto(Long id, String identificacionUsuario, Long valorPresupuesto, LocalDateTime fechaPresupuesto) {

        validarRestriccionesDeParametrosEntrada(identificacionUsuario, valorPresupuesto, fechaPresupuesto);

        this.id = id;
        this.identificacionUsuario = identificacionUsuario;
        this.valorPresupuesto = valorPresupuesto;
        this.fechaPresupuesto = fechaPresupuesto;
    }

    private void validarRestriccionesDeParametrosEntrada(String identificacionUsuario, Long valorPresupuesto, LocalDateTime fechaPresupuesto) {

        validarObligatorio(fechaPresupuesto, SE_DEBE_INGRESAR_FECHA_PRESUPUESTO);
        validarObligatorio(valorPresupuesto, SE_DEBE_INGRESAR_VALOR_PRESUPUESTO);
        validarObligatorio(identificacionUsuario, SE_DEBE_INGRESAR_IDENTIFICACION_USUARIO);

        validarRegex(identificacionUsuario,FORTAMATO_ALFANUMERICO, EL_FORMATO_IDENTIFICACION_USUARIO);

        validarLongitudMaxima(identificacionUsuario, LONGITUD_MAXIMA_IDENTIFICACION_USUARIO, LA_IDENTIFICACION_USUARIO_DEBE_TENER_UNA_LONGITUD_MAYOR_O_IGUAL_A);
        validarLongitudMaxima(valorPresupuesto, LONGITUD_MAXIMA_VALOR_PRESUPUESTO, EL_VALOR_PRESUPUESTO_DEBE_TENER_UNA_LONGITUD_MAYOR_O_IGUAL_A);
    }

}
