package com.ceiba.gasto.adaptador.dao;

import com.ceiba.infraestructura.jdbc.CustomNamedParameterJdbcTemplate;
import com.ceiba.infraestructura.jdbc.sqlstatement.SqlStatement;
import com.ceiba.gasto.modelo.dto.DtoGasto;
import com.ceiba.gasto.puerto.dao.DaoGasto;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DaoGastoMysql implements DaoGasto {

    private final CustomNamedParameterJdbcTemplate customNamedParameterJdbcTemplate;

    @SqlStatement(namespace="gasto", value="listar")
    private static String sqlListar;

    public DaoGastoMysql(CustomNamedParameterJdbcTemplate customNamedParameterJdbcTemplate) {
        this.customNamedParameterJdbcTemplate = customNamedParameterJdbcTemplate;
    }

    @Override
    public List<DtoGasto> listar() {
        return this.customNamedParameterJdbcTemplate.getNamedParameterJdbcTemplate().query(sqlListar, new MapeoGasto());
    }
}
