insert into usuario (id, nombre, apellido, identificacion_usuario)
values(1,'nombreBD','apellidoBD', '94123123');

insert into presupuesto (id, identificacion_usuario, valor_presupuesto, fecha_presupuesto)
values(1,'94123123',500, '2022-03-08 17:00:00');

insert into gasto (id, identificacion_usuario, valor_gasto, fecha_gasto)
values(1,'94123123',100, '2022-03-08 17:00:00');
