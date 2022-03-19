package com.ceiba.presupuesto.comando.manejador;

import com.ceiba.manejador.ManejadorComando;
import com.ceiba.presupuesto.servicio.ServicioEliminarPresupuesto;
import org.springframework.stereotype.Component;


@Component
public class ManejadorEliminarPresupuesto implements ManejadorComando<Long> {

    private final ServicioEliminarPresupuesto servicioEliminarPresupuesto;

    public ManejadorEliminarPresupuesto(ServicioEliminarPresupuesto servicioEliminarPresupuesto) {
        this.servicioEliminarPresupuesto = servicioEliminarPresupuesto;
    }

    public void ejecutar(Long id) {
        this.servicioEliminarPresupuesto.ejecutar(id);
    }
}
