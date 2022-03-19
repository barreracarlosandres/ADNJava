select valor_presupuesto
from presupuesto
where FORMATDATETIME(fecha_presupuesto, 'yyyy-MM') = FORMATDATETIME(:fechaPresupuesto, 'yyyy-MM')
and identificacion_usuario = :identificacionUsuario