package com.ceiba.presupuesto.controlador;

import com.ceiba.ComandoRespuesta;
import com.ceiba.presupuesto.comando.ComandoPresupuesto;
import com.ceiba.presupuesto.comando.manejador.ManejadorActualizarPresupuesto;
import com.ceiba.presupuesto.comando.manejador.ManejadorCrearPresupuesto;
import com.ceiba.presupuesto.comando.manejador.ManejadorEliminarPresupuesto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/presupuestos")
@Api(tags = { "Controlador comando presupuesto"})
public class ComandoControladorPresupuesto {

    private final ManejadorCrearPresupuesto manejadorCrearPresupuesto;
	private final ManejadorEliminarPresupuesto manejadorEliminarPresupuesto;
	private final ManejadorActualizarPresupuesto manejadorActualizarPresupuesto;

    @Autowired
    public ComandoControladorPresupuesto(ManejadorCrearPresupuesto manejadorCrearPresupuesto,
										 ManejadorEliminarPresupuesto manejadorEliminarPresupuesto,
										 ManejadorActualizarPresupuesto manejadorActualizarPresupuesto) {
        this.manejadorCrearPresupuesto = manejadorCrearPresupuesto;
		this.manejadorEliminarPresupuesto = manejadorEliminarPresupuesto;
		this.manejadorActualizarPresupuesto = manejadorActualizarPresupuesto;
    }

    @PostMapping
    @ApiOperation("Crear Presupuesto")
    public ComandoRespuesta<Long> crear(@RequestBody ComandoPresupuesto comandoPresupuesto) {
        return manejadorCrearPresupuesto.ejecutar(comandoPresupuesto);
    }

  	@PutMapping(value="/{id}")
	@ApiOperation("Actualizar Presupuesto")
	public void actualizar(@RequestBody ComandoPresupuesto comandoPresupuesto,@PathVariable Long id) {
		comandoPresupuesto.setId(id);
		manejadorActualizarPresupuesto.ejecutar(comandoPresupuesto);
	}

	@DeleteMapping(value="/{id}")
	@ApiOperation("Eliminar Presupuesto")
	public void eliminar(@PathVariable Long id) { manejadorEliminarPresupuesto.ejecutar(id); }

}
