package com.ceiba.gasto.comando.fabrica;

import com.ceiba.gasto.comando.ComandoGasto;
import com.ceiba.gasto.modelo.entidad.Gasto;
import org.springframework.stereotype.Component;

@Component
public class FabricaGasto {

    public Gasto crear(ComandoGasto comandoGasto) {
        return new Gasto(
                comandoGasto.getId(),
                comandoGasto.getIdentificacionUsuario(),
                comandoGasto.getValorGasto(),
                comandoGasto.getFechaGasto()
        );
    }

}
