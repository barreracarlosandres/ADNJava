package com.ceiba.gasto.servicio;

import com.ceiba.dominio.excepcion.ExcepcionDuplicidad;
import com.ceiba.dominio.excepcion.ExcepcionSinDatos;
import com.ceiba.gasto.modelo.entidad.Gasto;
import com.ceiba.gasto.puerto.repositorio.RepositorioGasto;

public class ServicioActualizarGasto {

    private static final String EL_GASTO_NO_EXISTE_EN_EL_SISTEMA = "El gasto no existe en el sistema";

    private final RepositorioGasto repositorioGasto;

    public ServicioActualizarGasto(RepositorioGasto repositorioGasto) {
        this.repositorioGasto = repositorioGasto;
    }

    public void ejecutar(Gasto gasto) {
        validarExistenciaPrevia(gasto);
        this.repositorioGasto.actualizar(gasto);
    }

    private void validarExistenciaPrevia(Gasto gasto) {
        boolean existe = this.repositorioGasto.existe(gasto.getId());
        if(!existe) {
            throw new ExcepcionSinDatos(EL_GASTO_NO_EXISTE_EN_EL_SISTEMA);
        }
    }
}
