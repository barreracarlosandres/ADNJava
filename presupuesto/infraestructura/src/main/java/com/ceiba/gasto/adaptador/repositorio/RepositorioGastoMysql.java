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

    @SqlStatement(namespace="gasto", value="sumaGastoPorFecha")
    private static String sqlSumaGastoPorFecha;

    @SqlStatement(namespace="presupuesto", value="existePresupuesto")
    private static String sqlExistePresupuesto;

    @SqlStatement(namespace="presupuesto", value="valorPresupuestoPorFecha")
    private static String sqlVarlorPresupuestoPorFecha;

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
    public Long sumaGastosPorFechaGasto(Gasto gasto) {
        MapSqlParameterSource paramSource = getMapSqlParametros(gasto, "fechaGasto");

        return this.customNamedParameterJdbcTemplate.getNamedParameterJdbcTemplate().queryForObject(sqlSumaGastoPorFecha,paramSource, Long.class);
    }

    @Override
    public Long presupuestoParaFechaGasto(Gasto gasto) {
        MapSqlParameterSource paramSource = getMapSqlParametros(gasto, "fechaPresupuesto");

        return this.customNamedParameterJdbcTemplate.getNamedParameterJdbcTemplate().queryForObject(sqlVarlorPresupuestoPorFecha,paramSource, Long.class);
    }

    @Override
    public boolean existePresupuesto(Gasto gasto) {
        MapSqlParameterSource paramSource = getMapSqlParametros(gasto, "fechaPresupuesto");

        return this.customNamedParameterJdbcTemplate
                .getNamedParameterJdbcTemplate()
                .queryForObject(sqlExistePresupuesto,paramSource, Boolean.class);
    }

    @Override
    public void actualizar(Gasto gasto) {
        this.customNamedParameterJdbcTemplate.actualizar(gasto, sqlActualizar);
    }

    private MapSqlParameterSource getMapSqlParametros(Gasto gasto, String fechaGasto) {
        MapSqlParameterSource paramSource = new MapSqlParameterSource();
        paramSource.addValue("identificacionUsuario", gasto.getIdentificacionUsuario());
        paramSource.addValue(fechaGasto, gasto.getFechaGasto());
        return paramSource;
    }

}

