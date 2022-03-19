package com.ceiba.gasto.puerto.repositorio;

import com.ceiba.gasto.modelo.entidad.Gasto;

public interface RepositorioGasto {
    /**
     * Permite crear un usuario
     * @return el id generado
     */
    Long crear(Gasto gasto);

    /**
     * Permite actualizar un usuario
     */
    void actualizar(Gasto gasto);

    /**
     * Permite eliminar un usuario
     */
    void eliminar(Long id);

    /**
     * Permite validar si existe un usuario con un nombre
     * @return si existe o no
     */
    boolean existePresupuesto(Gasto gasto);

    /**
     * Permite sumar los gastos ejecutados
     * @return la suma de los gastos ejecutados para el año y mes
     */
    Long sumaGastosPorFechaGasto(Gasto gasto);

    /**
     * Permite sumar los gastos ejecutados
     * @return la suma de los gastos ejecutados para el año y mes
     */
    Long presupuestoParaFechaGasto(Gasto gasto);

}
