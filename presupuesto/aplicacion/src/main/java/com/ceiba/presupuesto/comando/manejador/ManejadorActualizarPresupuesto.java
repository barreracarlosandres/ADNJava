package com.ceiba.presupuesto.comando.manejador;

import com.ceiba.manejador.ManejadorComando;
import com.ceiba.presupuesto.comando.ComandoPresupuesto;
import com.ceiba.presupuesto.comando.fabrica.FabricaPresupuesto;
import com.ceiba.presupuesto.modelo.entidad.Presupuesto;
import com.ceiba.presupuesto.servicio.ServicioActualizarPresupuesto;
import org.springframework.stereotype.Component;

@Component
public class ManejadorActualizarPresupuesto implements ManejadorComando<ComandoPresupuesto> {

    private final FabricaPresupuesto fabricaPresupuesto;
    private final ServicioActualizarPresupuesto servicioActualizarPresupuesto;

    public ManejadorActualizarPresupuesto(FabricaPresupuesto fabricaPresupuesto, ServicioActualizarPresupuesto servicioActualizarPresupuesto) {
        this.fabricaPresupuesto = fabricaPresupuesto;
        this.servicioActualizarPresupuesto = servicioActualizarPresupuesto;
    }

    public void ejecutar(ComandoPresupuesto comandoPresupuesto) {
        Presupuesto presupuesto = this.fabricaPresupuesto.crear(comandoPresupuesto);
        this.servicioActualizarPresupuesto.ejecutar(presupuesto);
    }
}
