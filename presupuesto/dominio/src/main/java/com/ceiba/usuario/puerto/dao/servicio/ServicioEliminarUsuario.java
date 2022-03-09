package com.ceiba.usuario.puerto.dao.servicio;

import com.ceiba.usuario.puerto.dao.puerto.repositorio.RepositorioUsuario;

public class ServicioEliminarUsuario {

    private final RepositorioUsuario repositorioUsuario;

    public ServicioEliminarUsuario(RepositorioUsuario repositorioUsuario) {
        this.repositorioUsuario = repositorioUsuario;
    }

    public void ejecutar(Long id) {
        this.repositorioUsuario.eliminar(id);
    }
}
