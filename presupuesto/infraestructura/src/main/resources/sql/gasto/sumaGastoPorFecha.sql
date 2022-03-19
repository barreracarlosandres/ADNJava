select
sum(valor_gasto)
from gasto
where FORMATDATETIME(fecha_gasto, 'yyyy-MM') = FORMATDATETIME(:fechaGasto, 'yyyy-MM')
and identificacion_usuario = :identificacionUsuario
group by FORMATDATETIME(fecha_gasto, 'yyyy-MM')