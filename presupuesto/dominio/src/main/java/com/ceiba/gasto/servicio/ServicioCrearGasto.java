package com.ceiba.gasto.servicio;

import com.ceiba.dominio.excepcion.ExcepcionSinDatos;
import com.ceiba.dominio.excepcion.ExcepcionValorInvalido;
import com.ceiba.gasto.modelo.entidad.Gasto;
import com.ceiba.gasto.puerto.repositorio.RepositorioGasto;


public class ServicioCrearGasto {

    private static final String NO_EXISTE_PRESUPUESTO = "No existe un presupuesto para aplicar el gasto";
    private static final String SE_SUPERO_EL_PRESUPUESTO = "Se superó el valor del presupuesto";

    private final RepositorioGasto repositorioGasto;

    public ServicioCrearGasto(RepositorioGasto repositorioGasto) {
        this.repositorioGasto = repositorioGasto;
    }

    public Long ejecutar(Gasto gasto) {
        validarExistenciaDePresupuesto(gasto);
        validarPresupuestoDisponible(gasto);
        return this.repositorioGasto.crear(gasto);
    }

    private void validarExistenciaDePresupuesto(Gasto gasto) {
        boolean existe = this.repositorioGasto.existePresupuesto(gasto);
        if(!existe) {
            throw new ExcepcionSinDatos(NO_EXISTE_PRESUPUESTO);
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
