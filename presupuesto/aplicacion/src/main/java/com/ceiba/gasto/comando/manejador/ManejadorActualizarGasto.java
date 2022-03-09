package com.ceiba.gasto.comando.manejador;

import com.ceiba.gasto.comando.ComandoGasto;
import com.ceiba.gasto.comando.fabrica.FabricaGasto;
import com.ceiba.manejador.ManejadorComando;
import com.ceiba.gasto.modelo.entidad.Gasto;
import com.ceiba.gasto.servicio.ServicioActualizarGasto;
import org.springframework.stereotype.Component;

@Component
public class ManejadorActualizarGasto implements ManejadorComando<ComandoGasto> {

    private final FabricaGasto fabricaGasto;
    private final ServicioActualizarGasto servicioActualizarGasto;

    public ManejadorActualizarGasto(FabricaGasto fabricaGasto, ServicioActualizarGasto servicioActualizarGasto) {
        this.fabricaGasto = fabricaGasto;
        this.servicioActualizarGasto = servicioActualizarGasto;
    }

    public void ejecutar(ComandoGasto comandoGasto) {
        Gasto gasto = this.fabricaGasto.crear(comandoGasto);
        this.servicioActualizarGasto.ejecutar(gasto);
    }
}
