package com.ceiba.gasto.controlador;

import com.ceiba.gasto.consulta.ManejadorListarGasto;
import com.ceiba.gasto.modelo.dto.DtoGasto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/gasto")
@Api(tags={"Controlador consulta gastos"})
public class ConsultaControladorGasto {

    private final ManejadorListarGasto manejadorListarGasto;

    public ConsultaControladorGasto(ManejadorListarGasto manejadorListarGasto) {
        this.manejadorListarGasto = manejadorListarGasto;
    }

    @GetMapping
    @ApiOperation("Listar Gastos")
    public List<DtoGasto> listar() {
        return this.manejadorListarGasto.ejecutar();
    }

}
