package com.ceiba.presupuesto.testdatabuilder;

import com.ceiba.presupuesto.comando.ComandoPresupuesto;
import org.apache.commons.lang3.RandomStringUtils;

import java.time.LocalDateTime;

public class ComandoPresupuestoTestDataBuilder {

    private Long id;
    private String identificacionUsuario;
    private Long valorPresupuesto;
    private LocalDateTime fechaPresupuesto;

    public ComandoPresupuestoTestDataBuilder() {
        identificacionUsuario = RandomStringUtils.randomAlphabetic(15);
        valorPresupuesto =  Long.parseLong(RandomStringUtils.randomNumeric(7));
        fechaPresupuesto = LocalDateTime.now();
    }

    public ComandoPresupuestoTestDataBuilder conIdentificacionUsuario(String identificacionUsuario){
        this.identificacionUsuario=identificacionUsuario;
        return this;
    }

    public ComandoPresupuestoTestDataBuilder conVarlorPresupuesto(Long valorPresupuesto){
        this.valorPresupuesto=valorPresupuesto;
        return this;
    }

    public ComandoPresupuestoTestDataBuilder conFechaPresupuesto(LocalDateTime fechaPresupuesto) {
        this.fechaPresupuesto = fechaPresupuesto;
        return this;
    }

    public ComandoPresupuesto build() {
        return new ComandoPresupuesto(id, identificacionUsuario, valorPresupuesto, fechaPresupuesto);
    }
}
