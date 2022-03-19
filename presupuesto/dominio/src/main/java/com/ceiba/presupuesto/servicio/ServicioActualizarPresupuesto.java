package com.ceiba.presupuesto.servicio;

import com.ceiba.dominio.excepcion.ExcepcionSinDatos;
import com.ceiba.presupuesto.modelo.entidad.Presupuesto;
import com.ceiba.presupuesto.puerto.repositorio.RepositorioPresupuesto;

public class ServicioActualizarPresupuesto {

    private static final String EL_PRESUPUESTO_NO_EXISTE_EN_EL_SISTEMA = "El presupuesto no existe en el sistema";

    private final RepositorioPresupuesto repositorioPresupuesto;

    public ServicioActualizarPresupuesto(RepositorioPresupuesto repositorioPresupuesto) {
        this.repositorioPresupuesto = repositorioPresupuesto;
    }

    public void ejecutar(Presupuesto presupuesto) {
        validarExistenciaPreviaPresupuesto(presupuesto);
        this.repositorioPresupuesto.actualizar(presupuesto);
    }

    private void validarExistenciaPreviaPresupuesto(Presupuesto presupuesto) {
        boolean existe = this.repositorioPresupuesto.existePresupuesto(
                presupuesto.getIdentificacionUsuario(),
                presupuesto.getFechaPresupuesto());
        if(existe) {
            throw new ExcepcionSinDatos(EL_PRESUPUESTO_NO_EXISTE_EN_EL_SISTEMA);
        }
    }
}
