package com.ceiba.presupuesto.puerto.repositorio;

import com.ceiba.presupuesto.modelo.entidad.Presupuesto;

import java.time.LocalDateTime;

public interface RepositorioPresupuesto {
    /**
     * Permite crear un presupuesto
     * @return el id generado
     */
    Long crear(Presupuesto presupuesto);

    /**
     * Permite actualizar un presupuesto
     */
    void actualizar(Presupuesto presupuesto);

    /**
     * Permite eliminar un presupuesto por id
     */
    void eliminar(Long id);

    /**
     * Permite validar si existe un presupuesto por id
     * @return si existe o no
     */
    boolean existeUsuario(String identificacionUsuario);

    /**
     * Permite validar si existe un presupuesto por id
     * @return si existe o no
     */
    boolean existePresupuesto(String identificacionUsuario, LocalDateTime fechaPresupuesto);

}
