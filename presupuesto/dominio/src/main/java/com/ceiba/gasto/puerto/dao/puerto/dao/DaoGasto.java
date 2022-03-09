package com.ceiba.gasto.puerto.dao.puerto.dao;

import com.ceiba.gasto.puerto.dao.modelo.dto.DtoGasto;

import java.util.List;

public interface DaoGasto {

    /**
     * Permite listar usuarios
     * @return los usuarios
     */
    List<DtoGasto> listar();
}
