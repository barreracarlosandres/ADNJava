package com.ceiba.gasto.comando.manejador;

import com.ceiba.manejador.ManejadorComando;
import com.ceiba.gasto.servicio.ServicioEliminarGasto;
import org.springframework.stereotype.Component;


@Component
public class ManejadorEliminarGasto implements ManejadorComando<Long> {

    private final ServicioEliminarGasto servicioEliminarGasto;

    public ManejadorEliminarGasto(ServicioEliminarGasto servicioEliminarGasto) {
        this.servicioEliminarGasto = servicioEliminarGasto;
    }

    public void ejecutar(Long id) {
        this.servicioEliminarGasto.ejecutar(id);
    }
}
