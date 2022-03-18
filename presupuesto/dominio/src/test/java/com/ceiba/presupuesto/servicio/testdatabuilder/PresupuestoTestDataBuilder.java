package com.ceiba.presupuesto.servicio.testdatabuilder;

import com.ceiba.presupuesto.modelo.entidad.Presupuesto;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class PresupuestoTestDataBuilder {

    private Long id;
    private String identificacionUsuario;
    private Long valorPresupuesto;
    private String fechaPresupuesto;

    public PresupuestoTestDataBuilder() {
        identificacionUsuario = "123";
        valorPresupuesto = 123L;
        fechaPresupuesto = getFechaConFormato();
    }

    private static String getFechaConFormato() {
        return LocalDateTime
                .now()
                .format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
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

    public PresupuestoTestDataBuilder conFechaPresupuesto(String fechaPresupuesto) {
        this.fechaPresupuesto = fechaPresupuesto;
        return this;
    }

    public Presupuesto build() {
        return new Presupuesto(id,identificacionUsuario, valorPresupuesto, fechaPresupuesto);
    }
}
