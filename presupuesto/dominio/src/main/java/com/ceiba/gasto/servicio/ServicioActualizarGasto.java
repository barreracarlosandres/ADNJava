package com.ceiba.gasto.servicio;

import com.ceiba.dominio.excepcion.ExcepcionSinDatos;
import com.ceiba.dominio.excepcion.ExcepcionValorInvalido;
import com.ceiba.gasto.modelo.entidad.Gasto;
import com.ceiba.gasto.puerto.repositorio.RepositorioGasto;

public class ServicioActualizarGasto {

    private static final String EL_GASTO_NO_EXISTE_EN_EL_SISTEMA = "El gasto no existe en el sistema";
    private static final String EL_PRESUPUESTO_NO_EXISTE_EN_EL_SISTEMA = "El presupuesto no existe en el sistema";
    private static final String SE_SUPERO_EL_PRESUPUESTO = "Se superó el valor del presupuesto";

    private final RepositorioGasto repositorioGasto;

    public ServicioActualizarGasto(RepositorioGasto repositorioGasto) {
        this.repositorioGasto = repositorioGasto;
    }

    public void ejecutar(Gasto gasto) {
        validarExistenciaPreviaDelGasto(gasto.getId());
        validarExistenciaDePresupuesto(gasto);
        validarPresupuestoDisponible(gasto);
        this.repositorioGasto.actualizar(gasto);
    }

    private void validarExistenciaPreviaDelGasto(Long id) {
        boolean existe = this.repositorioGasto.existe(id);
        if(!existe) {
            throw new ExcepcionSinDatos(EL_GASTO_NO_EXISTE_EN_EL_SISTEMA);
        }
    }

    private void validarExistenciaDePresupuesto(Gasto gasto) {
        boolean existe = this.repositorioGasto.existePresupuesto(gasto);
        if(!existe) {
            throw new ExcepcionSinDatos(EL_PRESUPUESTO_NO_EXISTE_EN_EL_SISTEMA);
        }
    }

    private void validarPresupuestoDisponible(Gasto gasto){
        Long temporal = sumaGastosPorFechaGasto(gasto)+gasto.getValorGasto();
        if( temporal.compareTo(presupuestoDelMes(gasto)) > 0 )
        {
            throw new ExcepcionValorInvalido(SE_SUPERO_EL_PRESUPUESTO);
        }
    }

    private Long sumaGastosPorFechaGasto(Gasto gasto) {
        return this.repositorioGasto.sumaGastosPorFechaGasto(gasto);
    }

    private Long presupuestoDelMes(Gasto gasto) {
        return this.repositorioGasto.presupuestoParaFechaGasto(gasto);

    }
}
