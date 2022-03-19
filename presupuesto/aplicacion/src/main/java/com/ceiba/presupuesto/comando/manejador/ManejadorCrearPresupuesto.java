package com.ceiba.presupuesto.comando.manejador;

import com.ceiba.ComandoRespuesta;
import com.ceiba.manejador.ManejadorComandoRespuesta;
import com.ceiba.presupuesto.comando.ComandoPresupuesto;
import com.ceiba.presupuesto.comando.fabrica.FabricaPresupuesto;
import com.ceiba.presupuesto.modelo.entidad.Presupuesto;
import com.ceiba.presupuesto.servicio.ServicioCrearPresupuesto;
import org.springframework.stereotype.Component;

@Component
public class ManejadorCrearPresupuesto implements ManejadorComandoRespuesta<ComandoPresupuesto, ComandoRespuesta<Long>> {

    private final FabricaPresupuesto fabricaPresupuesto;
    private final ServicioCrearPresupuesto servicioCrearPresupuesto;

    public ManejadorCrearPresupuesto(FabricaPresupuesto fabricaPresupuesto, ServicioCrearPresupuesto servicioCrearPresupuesto) {
        this.fabricaPresupuesto = fabricaPresupuesto;
        this.servicioCrearPresupuesto = servicioCrearPresupuesto;
    }

    public ComandoRespuesta<Long> ejecutar(ComandoPresupuesto comandoPresupuesto) {
        Presupuesto presupuesto = this.fabricaPresupuesto.crear(comandoPresupuesto);
        return new ComandoRespuesta<>(this.servicioCrearPresupuesto.ejecutar(presupuesto));
    }

}
