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

    public ComandoGasto build() {
        return new ComandoGasto(id, identificacionUsuario, valorGasto, fechaGasto);
    }
}
