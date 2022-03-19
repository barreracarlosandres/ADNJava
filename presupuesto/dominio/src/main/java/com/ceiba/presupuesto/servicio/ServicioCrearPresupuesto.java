package com.ceiba.presupuesto.servicio;

import com.ceiba.dominio.excepcion.ExcepcionDuplicidad;
import com.ceiba.dominio.excepcion.ExcepcionSinDatos;
import com.ceiba.presupuesto.modelo.entidad.Presupuesto;
import com.ceiba.presupuesto.puerto.repositorio.RepositorioPresupuesto;

public class ServicioCrearPresupuesto {

    private static final String EL_PRESUPUESTO_YA_EXISTE_EN_EL_SISTEMA = "El presupuesto ya existe en el sistema";
    private static final String EL_USUARIO_NO_EXISTE_EN_EL_SISTEMA = "El usuario no existe";

    private final RepositorioPresupuesto repositorioPresupuesto;

    public ServicioCrearPresupuesto(RepositorioPresupuesto repositorioPresupuesto) {
        this.repositorioPresupuesto = repositorioPresupuesto;
    }

    public Long ejecutar(Presupuesto presupuesto) {
        validarExistenciaPreviaUsuario(presupuesto);
        validarExistenciaPreviaPresupuesto(presupuesto);
        return this.repositorioPresupuesto.crear(presupuesto);
    }

    private void validarExistenciaPreviaUsuario(Presupuesto presupuesto) {
        boolean existeUsuario = this.repositorioPresupuesto.existeUsuario(
                presupuesto.getIdentificacionUsuario()
        );
        if(!existeUsuario) {
            throw new ExcepcionSinDatos(EL_USUARIO_NO_EXISTE_EN_EL_SISTEMA);
        }
    }

    private void validarExistenciaPreviaPresupuesto(Presupuesto presupuesto) {
        boolean existePresupuesto = this.repositorioPresupuesto.existePresupuesto(
                presupuesto.getIdentificacionUsuario(),
                presupuesto.getFechaPresupuesto());
        if(existePresupuesto) {
            throw new ExcepcionDuplicidad(EL_PRESUPUESTO_YA_EXISTE_EN_EL_SISTEMA);
        }
    }


}
