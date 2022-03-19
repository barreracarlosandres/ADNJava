package com.ceiba.presupuesto.adaptador.repositorio;

import com.ceiba.infraestructura.jdbc.CustomNamedParameterJdbcTemplate;
import com.ceiba.infraestructura.jdbc.sqlstatement.SqlStatement;
import com.ceiba.presupuesto.modelo.entidad.Presupuesto;
import com.ceiba.presupuesto.puerto.repositorio.RepositorioPresupuesto;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
public class RepositorioPresupuestoMysql implements RepositorioPresupuesto {

    private final CustomNamedParameterJdbcTemplate customNamedParameterJdbcTemplate;

    @SqlStatement(namespace="presupuesto", value="crear")
    private static String sqlCrear;

    @SqlStatement(namespace="presupuesto", value="actualizar")
    private static String sqlActualizar;

    @SqlStatement(namespace="presupuesto", value="eliminar")
    private static String sqlEliminar;

    @SqlStatement(namespace="presupuesto", value="existePorIdentificacionUsuarioYPorFechaPresupuesto")
    private static String existePorIdentificacionUsuarioYFechaPresupuesto;

    @SqlStatement(namespace="usuario", value="existePorIdentificacionUsuario")
    private static String sqlExisteUsuarioPorIdentificacionUsuario;

    public RepositorioPresupuestoMysql(CustomNamedParameterJdbcTemplate customNamedParameterJdbcTemplate) {
        this.customNamedParameterJdbcTemplate = customNamedParameterJdbcTemplate;
    }

    @Override
    public Long crear(Presupuesto presupuesto) {
        return this.customNamedParameterJdbcTemplate.crear(presupuesto, sqlCrear);
    }

    @Override
    public void eliminar(Long id) {
        MapSqlParameterSource paramSource = new MapSqlParameterSource();
        paramSource.addValue("id", id);

        this.customNamedParameterJdbcTemplate.getNamedParameterJdbcTemplate().update(sqlEliminar, paramSource);
    }

    @Override
    public boolean existeUsuario(String identificacionUsuario) {
        MapSqlParameterSource paramSource = new MapSqlParameterSource();
        paramSource.addValue("identificacionUsuario", identificacionUsuario);

        return this.customNamedParameterJdbcTemplate.getNamedParameterJdbcTemplate().queryForObject(sqlExisteUsuarioPorIdentificacionUsuario,paramSource, Boolean.class);
    }

    @Override
    public boolean existePresupuesto(String identificacionUsuario, LocalDateTime fechaPresupuesto) {
        MapSqlParameterSource paramSource = new MapSqlParameterSource();
        paramSource.addValue("identificacionUsuario", identificacionUsuario);
        paramSource.addValue("fechaPresupuesto", fechaPresupuesto);

        return this.customNamedParameterJdbcTemplate.getNamedParameterJdbcTemplate().queryForObject(existePorIdentificacionUsuarioYFechaPresupuesto,paramSource, Boolean.class);
    }

    @Override
    public void actualizar(Presupuesto presupuesto) {
        this.customNamedParameterJdbcTemplate.actualizar(presupuesto, sqlActualizar);
    }

}

