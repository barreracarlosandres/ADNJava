package com.ceiba.gasto.puerto.dao.servicio;

import com.ceiba.gasto.puerto.dao.puerto.repositorio.RepositorioGasto;

public class ServicioEliminarGasto {

    private final RepositorioGasto repositorioGasto;

    public ServicioEliminarGasto(RepositorioGasto repositorioGasto) {
        this.repositorioGasto = repositorioGasto;
    }

    public void ejecutar(Long id) {
        this.repositorioGasto.eliminar(id);
    }
}
