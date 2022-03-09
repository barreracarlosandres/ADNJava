package com.ceiba.gasto.controlador;

import com.ceiba.ComandoRespuesta;
import com.ceiba.gasto.comando.ComandoGasto;
import com.ceiba.gasto.comando.manejador.ManejadorActualizarGasto;
import com.ceiba.gasto.comando.manejador.ManejadorCrearGasto;
import com.ceiba.gasto.comando.manejador.ManejadorEliminarGasto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/gasto")
@Api(tags = { "Controlador comando gastos"})
public class ComandoControladorGasto {

    private final ManejadorCrearGasto manejadorCrearGasto;
	private final ManejadorEliminarGasto manejadorEliminarGasto;
	private final ManejadorActualizarGasto manejadorActualizarGasto;

    @Autowired
    public ComandoControladorGasto(ManejadorCrearGasto manejadorCrearGasto,
								   ManejadorEliminarGasto manejadorEliminarGasto,
								   ManejadorActualizarGasto manejadorActualizarGasto) {
        this.manejadorCrearGasto = manejadorCrearGasto;
		this.manejadorEliminarGasto = manejadorEliminarGasto;
		this.manejadorActualizarGasto = manejadorActualizarGasto;
    }

    @PostMapping
    @ApiOperation("Crear Gasto")
    public ComandoRespuesta<Long> crear(@RequestBody ComandoGasto comandoGasto) {
        return manejadorCrearGasto.ejecutar(comandoGasto);
    }

    @DeleteMapping(value="/{id}")
	@ApiOperation("Eliminar Gasto")
	public void eliminar(@PathVariable Long id) {
		manejadorEliminarGasto.ejecutar(id);
	}

	@PutMapping(value="/{id}")
	@ApiOperation("Actualizar Gasto")
	public void actualizar(@RequestBody ComandoGasto comandoGasto,@PathVariable Long id) {
		comandoGasto.setId(id);
		manejadorActualizarGasto.ejecutar(comandoGasto);
	}
}
