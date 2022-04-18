package com.ceiba.gasto.servicio.testdatabuilder;

import com.ceiba.gasto.comando.ComandoGasto;
import org.apache.commons.lang3.RandomStringUtils;

import java.time.LocalDateTime;

public class ComandoGastoTestDataBuilder {

    private Long id;
    private String identificacionUsuario;
    private Long valorGasto;
    private LocalDateTime fechaGasto;

    public ComandoGastoTestDataBuilder() {
        identificacionUsuario = RandomStringUtils.randomAlphabetic(15);
        valorGasto =  Long.parseLong(RandomStringUtils.randomNumeric(7));
        fechaGasto = LocalDateTime.now();
    }

    public ComandoGastoTestDataBuilder conId(Long id){
        this.id=id;
        return this;
    }

    public ComandoGastoTestDataBuilder conIdentificacionUsuario(String identificacionUsuario){
        this.identificacionUsuario=identificacionUsuario;
        return this;
    }

    public ComandoGastoTestDataBuilder conFechaGasto(LocalDateTime fechaGasto) {
        this.fechaGasto = fechaGasto;
        return this;
    }

    public ComandoGastoTestDataBuilder conValorGasto(Long valorGasto) {
        this.valorGasto = valorGasto;
        return this;
    }

    public ComandoGasto build() {
        return new ComandoGasto(id, identificacionUsuario, valorGasto, fechaGasto);
    }
}
