package com.ceiba.dominio.excepcion;

public class ExcepcionSinDatos extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
    public ExcepcionSinDatos(String mensaje) {
        super(mensaje);
    }
}
