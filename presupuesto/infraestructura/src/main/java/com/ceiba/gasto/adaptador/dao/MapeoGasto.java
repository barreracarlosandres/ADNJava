package com.ceiba.gasto.adaptador.dao;

import com.ceiba.gasto.modelo.dto.DtoGasto;
import com.ceiba.infraestructura.jdbc.MapperResult;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;

public class MapeoGasto implements RowMapper<DtoGasto>, MapperResult {

    @Override
    public DtoGasto mapRow(ResultSet resultSet, int rowNum) throws SQLException {

        Long id = resultSet.getLong("id");
        String identificacionUsuario = resultSet.getString("identificacion_usuario");
        int valorGasto = resultSet.getInt("valor_gasto");
        LocalDateTime fechaGasto = extraerLocalDateTime(resultSet, "fecha_gasto");

        return new DtoGasto(id, identificacionUsuario, valorGasto, fechaGasto);
    }

}
