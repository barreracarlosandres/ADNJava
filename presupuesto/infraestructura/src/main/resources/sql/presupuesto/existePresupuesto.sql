select
    count(1)
from presupuesto
    where identificacion_usuario = :identificacionUsuario
    and FORMATDATETIME(fecha_presupuesto, 'yyyy/MM') = FORMATDATETIME(:fechaPresupuesto, 'yyyy/MM')

