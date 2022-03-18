package com.ceiba.presupuesto.puerto.repositorio;

import com.ceiba.presupuesto.modelo.entidad.Presupuesto;

public interface RepositorioPresupuesto {
    /**
     * Permite crear un presupuesto
     * @param presupuesto
     * @return el id generado
     */
    Long crear(Presupuesto presupuesto);

    /**
     * Permite actualizar un usuario
     * @param presupuesto
     */
    void actualizar(Presupuesto presupuesto);

    /**
     * Permite eliminar un usuario
     * @param id
     */
    void eliminar(Long id);

    /**
     * Permite validar si existe un usuario con un nombre
     * @param id
     * @return si existe o no
     */
    boolean existe(Long id);

}
