package com.ceiba.gasto.puerto.dao.servicio;

import com.ceiba.dominio.excepcion.ExcepcionDuplicidad;
import com.ceiba.gasto.puerto.dao.modelo.entidad.Gasto;
import com.ceiba.gasto.puerto.dao.puerto.repositorio.RepositorioGasto;


public class ServicioCrearGasto {

    private static final String EL_USUARIO_YA_EXISTE_EN_EL_SISTEMA = "El usuario ya existe en el sistema";

    private final RepositorioGasto repositorioGasto;

    public ServicioCrearGasto(RepositorioGasto repositorioGasto) {
        this.repositorioGasto = repositorioGasto;
    }

    public Long ejecutar(Gasto gasto) {
        //validarExistenciaPrevia(gasto);
        return this.repositorioGasto.crear(gasto);
    }

    private void validarExistenciaPrevia(Gasto gasto) {
        boolean existe = this.repositorioGasto.existe(gasto.getId());
        if(existe) {
            throw new ExcepcionDuplicidad(EL_USUARIO_YA_EXISTE_EN_EL_SISTEMA);
        }
    }
}
