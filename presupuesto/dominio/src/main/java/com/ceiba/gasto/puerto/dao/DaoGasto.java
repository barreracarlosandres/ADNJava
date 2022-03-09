package com.ceiba.gasto.puerto.dao;

import com.ceiba.gasto.modelo.dto.DtoGasto;

import java.util.List;

public interface DaoGasto {

    /**
     * Permite listar usuarios
     * @return los gastos
     */
    List<DtoGasto> listar();
}
