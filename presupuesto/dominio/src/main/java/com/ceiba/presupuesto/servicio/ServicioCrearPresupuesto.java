package com.ceiba.presupuesto.servicio;

import com.ceiba.dominio.excepcion.ExcepcionDuplicidad;
import com.ceiba.presupuesto.modelo.entidad.Presupuesto;
import com.ceiba.presupuesto.puerto.repositorio.RepositorioPresupuesto;

public class ServicioCrearPresupuesto {

    private static final String EL_PRESUPUESTO_YA_EXISTE_EN_EL_SISTEMA = "El presupuesto ya existe en el sistema";

    private final RepositorioPresupuesto repositorioPresupuesto;

    public ServicioCrearPresupuesto(RepositorioPresupuesto repositorioPresupuesto) {
        this.repositorioPresupuesto = repositorioPresupuesto;
    }

    public Long ejecutar(Presupuesto presupuesto) {
        validarExistenciaPrevia(presupuesto);
        return this.repositorioPresupuesto.crear(presupuesto);
    }

    private void validarExistenciaPrevia(Presupuesto presupuesto) {
        boolean existe = this.repositorioPresupuesto.existe(presupuesto.getId());
        if(existe) {
            throw new ExcepcionDuplicidad(EL_PRESUPUESTO_YA_EXISTE_EN_EL_SISTEMA);
        }
    }
}
