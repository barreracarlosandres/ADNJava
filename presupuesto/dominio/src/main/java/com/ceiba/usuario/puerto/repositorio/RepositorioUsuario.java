package com.ceiba.usuario.puerto.repositorio;

import com.ceiba.usuario.modelo.entidad.Usuario;

public interface RepositorioUsuario {
    /**
     * Permite crear un usuario
     * @return el id generado
     */
    Long crear(Usuario usuario);

    /**
     * Permite actualizar un usuario
     */
    void actualizar(Usuario usuario);

    /**
     * Permite eliminar un usuario
     */
    void eliminarPorId(Long id);

     /**
     * Permite validar si existe un usuario por número de identificación
     * @return si existe o no un usuario
     */
    boolean existePorIdentificacionUsuario(String identificacionUsuario);

}
