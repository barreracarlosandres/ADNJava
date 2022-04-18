package com.ceiba.presupuesto.controlador;

import com.ceiba.presupuesto.consulta.ManejadorListarPresupuesto;
import com.ceiba.presupuesto.modelo.dto.DtoPresupuesto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/presupuestos")
@Api(tags={"Controlador consulta presupuesto"})
public class ConsultaControladorPresupuesto {

    private final ManejadorListarPresupuesto manejadorListarPresupuesto;

    public ConsultaControladorPresupuesto(ManejadorListarPresupuesto manejadorListarPresupuesto) {
        this.manejadorListarPresupuesto = manejadorListarPresupuesto;
    }

    @GetMapping
    @ApiOperation("Listar Gastos")
    public List<DtoPresupuesto> listar() {
        return this.manejadorListarPresupuesto.ejecutar();
    }

}
