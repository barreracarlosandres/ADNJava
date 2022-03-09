package com.ceiba.gasto.comando.manejador;

import com.ceiba.ComandoRespuesta;
import com.ceiba.gasto.comando.ComandoGasto;
import com.ceiba.gasto.comando.fabrica.FabricaGasto;
import com.ceiba.gasto.modelo.entidad.Gasto;
import com.ceiba.manejador.ManejadorComandoRespuesta;
import com.ceiba.gasto.servicio.ServicioCrearGasto;
import org.springframework.stereotype.Component;

@Component
public class ManejadorCrearGasto implements ManejadorComandoRespuesta<ComandoGasto, ComandoRespuesta<Long>> {

    private final FabricaGasto fabricaGasto;
    private final ServicioCrearGasto servicioCrearGasto;

    public ManejadorCrearGasto(FabricaGasto fabricaGasto, ServicioCrearGasto servicioCrearGasto) {
        this.fabricaGasto = fabricaGasto;
        this.servicioCrearGasto = servicioCrearGasto;
    }

    public ComandoRespuesta<Long> ejecutar(ComandoGasto comandoGasto) {
        Gasto gasto = this.fabricaGasto.crear(comandoGasto);
        return new ComandoRespuesta<>(this.servicioCrearGasto.ejecutar(gasto));
    }
}
