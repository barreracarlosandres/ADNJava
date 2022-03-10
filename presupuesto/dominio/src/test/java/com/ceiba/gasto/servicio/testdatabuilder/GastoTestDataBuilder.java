package com.ceiba.gasto.servicio.testdatabuilder;

import com.ceiba.gasto.modelo.entidad.Gasto;

import java.time.LocalDateTime;

public class GastoTestDataBuilder {

    private Long id;
    private String identificacionUsuario;
    private Long valorGasto;
    private LocalDateTime fechaGasto;
    private String valorGastoString;
    private String fechaGastoString;


    public GastoTestDataBuilder() {
        identificacionUsuario = "123";
        valorGasto = 123L;
        fechaGasto = LocalDateTime.now();
        valorGastoString = "sinFormatoNumerico";
        fechaGastoString = "sinFormatoFecha";
    }

    public GastoTestDataBuilder conId(Long id) {
        this.id = id;
        return this;
    }
    public GastoTestDataBuilder conIdentificacionUsuario(String identificacionUsuario) {
        this.identificacionUsuario = identificacionUsuario;
        return this;
    }

    public GastoTestDataBuilder conValorGasto(Long valorGasto) {
        this.valorGasto = valorGasto;
        return this;
    }

    public GastoTestDataBuilder conValorGastoString(String valorGastoString) {
        this.valorGastoString = valorGastoString;
        return this;
    }

    public GastoTestDataBuilder conFechaGasto(LocalDateTime fechaGasto) {
        this.fechaGasto = fechaGasto;
        return this;
    }

    public GastoTestDataBuilder conFechaGastoString(String fechaGastoString) {
        this.fechaGastoString = fechaGastoString;
        return this;
    }

    public Gasto build() {
        return new Gasto(id,identificacionUsuario, valorGasto, fechaGasto);
    }
}
