package com.ceiba.gasto.puerto.dao.servicio;

import com.ceiba.dominio.excepcion.ExcepcionDuplicidad;
import com.ceiba.gasto.puerto.dao.modelo.entidad.Gasto;
import com.ceiba.gasto.puerto.dao.puerto.repositorio.RepositorioGasto;

public class ServicioActualizarGasto {

    private static final String EL_USUARIO_NO_EXISTE_EN_EL_SISTEMA = "El no usuario existe en el sistema";

    private final RepositorioGasto repositorioGasto;

    public ServicioActualizarGasto(RepositorioGasto repositorioGasto) {
        this.repositorioGasto = repositorioGasto;
    }

    public void ejecutar(Gasto gasto) {
        validarExistenciaPrevia(gasto);
        this.repositorioGasto.actualizar(gasto);
    }

    private void validarExistenciaPrevia(Gasto gasto) {
        boolean existe = this.repositorioGasto.existePorId(gasto.getId());
        if(!existe) {
            throw new ExcepcionDuplicidad(EL_USUARIO_NO_EXISTE_EN_EL_SISTEMA);
        }
    }
}
