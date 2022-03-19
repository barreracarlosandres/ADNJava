package com.ceiba.usuario.modelo.entidad;


import lombok.Getter;

import static com.ceiba.dominio.ValidadorArgumento.*;

@SuppressWarnings("FieldMayBeFinal")
@Getter
public class Usuario {

    private Long id;
    private String nombre;
    private String apellido;
    private String identificacionUsuario;

    private static final String SE_DEBE_INGRESAR_LA_IDENTIFICACION_USUARIO = "Debe ingresar identificacionUsuario";
    private static final String SE_DEBE_INGRESAR_NOMBRE_DE_USUARIO = "Debe ingresar el atributo nombre";
    private static final String SE_DEBE_INGRESAR_EL_APELLIDO_DE_USUARIO = "Debe ingresar el atributo apellido";

    private static final int LONGITUD_MAXIMA_IDENTIFICACION_USUARIO = 15;
    private static final int LONGITUD_MAXIMA_NOMBRE = 100;
    private static final int LONGITUD_MAXIMA_APELLIDO = 100;

    private static final String LA_IDENTIFICACION_USUARIO_DEBE_TENER_UNA_LONGITUD_MAYOR_O_IGUAL_A = String.format("identificacionUsuario no debe ser mayor a %s",LONGITUD_MAXIMA_IDENTIFICACION_USUARIO);
    private static final String EL_NOMBRE_DEBE_TENER_UNA_LONGITUD_MAYOR_O_IGUAL_A = String.format("el atributo nombre no debe ser mayor a %s",LONGITUD_MAXIMA_NOMBRE);
    private static final String EL_APELLIDO_DEBE_TENER_UNA_LONGITUD_MAYOR_O_IGUAL_A = String.format("el atributo apellido no debe ser mayor a %s",LONGITUD_MAXIMA_APELLIDO);


    private static final String EL_FORMATO_NOMBRE_USUARIO = "El nombre puede incluir caracteres del alfabeto, espacio y acentos";
    private static final String EL_FORMATO_APELLIDO_USUARIO = "El apellido puede incluir caracteres del alfabeto, espacio y acentos";
    private static final String EL_FORMATO_IDENTIFICACION_USUARIO = "La identificacionUsuario debe ser alfanumérico";
    private static final String FORTAMO_ALFANUMERICO = "[A-Za-z0-9]+";
    private static final String FORTAMO_ALFABETO_ESPACIO_ACENTOS = "[A-Za-zÀ-ÿ\\u00f1\\u00d1 ]+";

    public Usuario(Long id, String nombre, String apellido, String identificacionUsuario) {
        validarObligatorio(nombre, SE_DEBE_INGRESAR_NOMBRE_DE_USUARIO);
        validarObligatorio(apellido, SE_DEBE_INGRESAR_EL_APELLIDO_DE_USUARIO);
        validarObligatorio(identificacionUsuario, SE_DEBE_INGRESAR_LA_IDENTIFICACION_USUARIO);

        validarLongitudMaxima(nombre,LONGITUD_MAXIMA_NOMBRE, EL_NOMBRE_DEBE_TENER_UNA_LONGITUD_MAYOR_O_IGUAL_A);
        validarLongitudMaxima(apellido,LONGITUD_MAXIMA_APELLIDO, EL_APELLIDO_DEBE_TENER_UNA_LONGITUD_MAYOR_O_IGUAL_A);
        validarLongitudMaxima(identificacionUsuario,LONGITUD_MAXIMA_IDENTIFICACION_USUARIO, LA_IDENTIFICACION_USUARIO_DEBE_TENER_UNA_LONGITUD_MAYOR_O_IGUAL_A);

        validarRegex(nombre,FORTAMO_ALFABETO_ESPACIO_ACENTOS, EL_FORMATO_NOMBRE_USUARIO);
        validarRegex(apellido,FORTAMO_ALFABETO_ESPACIO_ACENTOS,EL_FORMATO_APELLIDO_USUARIO );
        validarRegex(identificacionUsuario,FORTAMO_ALFANUMERICO,EL_FORMATO_IDENTIFICACION_USUARIO );

        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.identificacionUsuario = identificacionUsuario;
    }

}
