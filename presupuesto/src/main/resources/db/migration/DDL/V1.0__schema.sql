create table usuario (
 id int(11) not null auto_increment,
 nombre varchar(100) not null,
 clave varchar(45) not null,
 fecha_creacion datetime null,
 primary key (id)
);

create table gasto (
 id int(11) not null auto_increment,
 identificacion_usuario varchar(20) not null,
 valor_gasto int not null,
 fecha_gasto datetime not null,
 primary key (id)
);