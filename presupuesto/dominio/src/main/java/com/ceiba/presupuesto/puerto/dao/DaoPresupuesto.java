package com.ceiba.presupuesto.puerto.dao;

import com.ceiba.presupuesto.modelo.dto.DtoPresupuesto;

import java.util.List;

@SuppressWarnings("unused")
public interface DaoPresupuesto {

    /**
     * Permite listar usuarios
     * @return los gastos
     */
    List<DtoPresupuesto> listar();
}
