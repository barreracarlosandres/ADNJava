package com.ceiba.presupuesto.servicio.testdatabuilder;

import com.ceiba.presupuesto.modelo.entidad.Presupuesto;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class PresupuestoTestDataBuilder {

    private Long id;
    private String identificacionUsuario;
    private Long valorPresupuesto;
    private LocalDateTime fechaPresupuesto;

    public PresupuestoTestDataBuilder() {
        identificacionUsuario = "94123123";
        valorPresupuesto = 500L;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        fechaPresupuesto = LocalDateTime.parse("2022-03-08 17:00:00", formatter);
    }

    public PresupuestoTestDataBuilder conId(Long id) {
        this.id = id;
        return this;
    }

    public PresupuestoTestDataBuilder conIdentificacionUsuario(String identificacionUsuario) {
        this.identificacionUsuario = identificacionUsuario;
        return this;
    }

    public PresupuestoTestDataBuilder conValorPresupuesto(Long valorPresupuesto) {
        this.valorPresupuesto = valorPresupuesto;
        return this;
    }

    public PresupuestoTestDataBuilder conFechaPresupuesto(LocalDateTime fechaPresupuesto) {
        this.fechaPresupuesto = fechaPresupuesto;
        return this;
    }

    public Presupuesto build() {
        return new Presupuesto(id,identificacionUsuario, valorPresupuesto, fechaPresupuesto);
    }
}
