package com.ceiba.presupuesto.servicio;


import com.ceiba.presupuesto.puerto.repositorio.RepositorioPresupuesto;

public class ServicioEliminarPresupuesto {

    private final RepositorioPresupuesto repositorioPresupuesto;

    public ServicioEliminarPresupuesto(RepositorioPresupuesto repositorioPresupuesto) {
        this.repositorioPresupuesto = repositorioPresupuesto;
    }

    public void ejecutar(Long id) {
        this.repositorioPresupuesto.eliminar(id);
    }
}
