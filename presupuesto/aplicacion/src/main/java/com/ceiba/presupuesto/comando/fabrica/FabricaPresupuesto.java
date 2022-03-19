package com.ceiba.presupuesto.comando.fabrica;

import com.ceiba.presupuesto.comando.ComandoPresupuesto;
import com.ceiba.presupuesto.modelo.entidad.Presupuesto;
import org.springframework.stereotype.Component;

@Component
public class FabricaPresupuesto {

    public Presupuesto crear(ComandoPresupuesto comandoPresupuesto) {
        return new Presupuesto(
                comandoPresupuesto.getId(),
                comandoPresupuesto.getIdentificacionUsuario(),
                comandoPresupuesto.getValorPresupuesto(),
                comandoPresupuesto.getFechaPresupuesto()
        );
    }
}
