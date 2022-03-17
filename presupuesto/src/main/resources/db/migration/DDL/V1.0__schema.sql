create table usuario (
 id int(11) not null auto_increment,
 identificacion_usuario varchar2(15) not null,
 nombre varchar(100) not null,
 apellido varchar(100) not null,
 primary key (id),
 UNIQUE KEY identificacion_usuario_usuario (identificacion_usuario)
);

create table gasto (
 id int(11) not null auto_increment,
 identificacion_usuario varchar(15) not null,
 valor_gasto int not null,
 fecha_gasto datetime not null,
 primary key (id),
 UNIQUE KEY identificacion_usuario_gasto (identificacion_usuario)
);