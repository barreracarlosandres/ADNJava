package com.ceiba.presupuesto.adaptador.dao;

import com.ceiba.infraestructura.jdbc.CustomNamedParameterJdbcTemplate;
import com.ceiba.infraestructura.jdbc.sqlstatement.SqlStatement;
import com.ceiba.presupuesto.modelo.dto.DtoPresupuesto;
import com.ceiba.presupuesto.puerto.dao.DaoPresupuesto;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DaoPresupuestoMysql implements DaoPresupuesto {

    private final CustomNamedParameterJdbcTemplate customNamedParameterJdbcTemplate;

    @SqlStatement(namespace="presupuesto", value="listar")
    private static String sqlListar;

    public DaoPresupuestoMysql(CustomNamedParameterJdbcTemplate customNamedParameterJdbcTemplate) {
        this.customNamedParameterJdbcTemplate = customNamedParameterJdbcTemplate;
    }

    @Override
    public List<DtoPresupuesto> listar() {
        return this.customNamedParameterJdbcTemplate.getNamedParameterJdbcTemplate().query(sqlListar, new MapeoPresupuesto());
    }
}
