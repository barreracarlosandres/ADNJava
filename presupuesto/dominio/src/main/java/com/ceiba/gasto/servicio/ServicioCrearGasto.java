package com.ceiba.gasto.servicio;

import com.ceiba.dominio.excepcion.ExcepcionDuplicidad;
import com.ceiba.gasto.modelo.entidad.Gasto;
import com.ceiba.gasto.puerto.repositorio.RepositorioGasto;


public class ServicioCrearGasto {

    private static final String EL_GASTO_YA_EXISTE_EN_EL_SISTEMA = "El gasto ya existe en el sistema";

    private final RepositorioGasto repositorioGasto;

    public ServicioCrearGasto(RepositorioGasto repositorioGasto) {
        this.repositorioGasto = repositorioGasto;
    }

    public Long ejecutar(Gasto gasto) {
        validarExistenciaPrevia(gasto);
        return this.repositorioGasto.crear(gasto);
    }

    private void validarExistenciaPrevia(Gasto gasto) {
        boolean existe = this.repositorioGasto.existe(gasto.getId());
        if(existe) {
            throw new ExcepcionDuplicidad(EL_GASTO_YA_EXISTE_EN_EL_SISTEMA);
        }
    }
}
