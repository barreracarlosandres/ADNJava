package com.ceiba.gasto.servicio;

import com.ceiba.gasto.puerto.repositorio.RepositorioGasto;

public class ServicioEliminarGasto {

    private final RepositorioGasto repositorioGasto;

    public ServicioEliminarGasto(RepositorioGasto repositorioGasto) {
        this.repositorioGasto = repositorioGasto;
    }

    public void ejecutar(Long id) {
        this.repositorioGasto.eliminar(id);
    }
}
