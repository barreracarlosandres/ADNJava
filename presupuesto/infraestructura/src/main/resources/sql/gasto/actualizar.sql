update gasto
set identificacion_usuario = :identificacionUsuario,
	valor_gasto = :valorGasto,
	fecha_gasto = :fechaGasto
where id = :id