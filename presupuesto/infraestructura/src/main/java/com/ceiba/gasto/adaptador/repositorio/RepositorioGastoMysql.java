package com.ceiba.gasto.adaptador.repositorio;

import com.ceiba.gasto.puerto.repositorio.RepositorioGasto;
import com.ceiba.infraestructura.jdbc.CustomNamedParameterJdbcTemplate;
import com.ceiba.infraestructura.jdbc.sqlstatement.SqlStatement;
import com.ceiba.gasto.modelo.entidad.Gasto;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.stereotype.Repository;

@Repository
public class RepositorioGastoMysql implements RepositorioGasto {

    private final CustomNamedParameterJdbcTemplate customNamedParameterJdbcTemplate;

    @SqlStatement(namespace="gasto", value="crear")
    private static String sqlCrear;

    @SqlStatement(namespace="gasto", value="actualizar")
    private static String sqlActualizar;

    @SqlStatement(namespace="gasto", value="eliminar")
    private static String sqlEliminar;

    @SqlStatement(namespace="gasto", value="existe")
    private static String sqlExiste;

    public RepositorioGastoMysql(CustomNamedParameterJdbcTemplate customNamedParameterJdbcTemplate) {
        this.customNamedParameterJdbcTemplate = customNamedParameterJdbcTemplate;
    }

    @Override
    public Long crear(Gasto gasto) {
        return this.customNamedParameterJdbcTemplate.crear(gasto, sqlCrear);
    }

    @Override
    public void eliminar(Long id) {
        MapSqlParameterSource paramSource = new MapSqlParameterSource();
        paramSource.addValue("id", id);

        this.customNamedParameterJdbcTemplate.getNamedParameterJdbcTemplate().update(sqlEliminar, paramSource);
    }

    @Override
    public boolean existe(Long id) {
        MapSqlParameterSource paramSource = new MapSqlParameterSource();
        paramSource.addValue("id", id);

        return this.customNamedParameterJdbcTemplate.getNamedParameterJdbcTemplate().queryForObject(sqlExiste,paramSource, Boolean.class);
    }

    @Override
    public void actualizar(Gasto gasto) {
        this.customNamedParameterJdbcTemplate.actualizar(gasto, sqlActualizar);
    }

}

