package com.ceiba.usuario.puerto.dao.puerto.dao;

import com.ceiba.usuario.puerto.dao.modelo.dto.DtoUsuario;

import java.util.List;

public interface DaoUsuario {

    /**
     * Permite listar usuarios
     * @return los usuarios
     */
    List<DtoUsuario> listar();
}
