package com.ceiba.presupuesto.adaptador.dao;

import com.ceiba.infraestructura.jdbc.MapperResult;
import com.ceiba.presupuesto.modelo.dto.DtoPresupuesto;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;

public class MapeoPresupuesto implements RowMapper<DtoPresupuesto>, MapperResult {

    @Override
    public DtoPresupuesto mapRow(ResultSet resultSet, int rowNum) throws SQLException {

        Long id = resultSet.getLong("id");
        String identificacionUsuario = resultSet.getString("identificacion_usuario");
        Long valorPresupuesto = resultSet.getLong("valor_presupuesto");
        LocalDateTime fechaPresupuesto = extraerLocalDateTime(resultSet, "fecha_presupuesto");

        return new DtoPresupuesto(id, identificacionUsuario, valorPresupuesto, fechaPresupuesto);
    }

}
