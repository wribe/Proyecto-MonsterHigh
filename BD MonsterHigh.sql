-- drop database monsterhigh;
 
create database MonsterHigh;

use MonsterHigh;

create table Especies(
id integer primary key auto_increment,
especie varchar(500) default 'Monstruo' not null unique,
descripcion varchar(500) default null
);

create table SubEspecies(
id integer primary key auto_increment,
subespecie varchar(500) unique,
especiePrincipal varchar(500),
descipcion varchar(500) default null,
CONSTRAINT `fk_especiess` FOREIGN KEY (`especiePrincipal`) REFERENCES `Especies` (`especie`) ON UPDATE CASCADE
);

create table Habilidades(
id integer primary key auto_increment,
habilidad varchar(500) default null unique,
descripcion varchar(500) default null
);

create table Monstruito(
id integer primary key auto_increment,
nombre varchar(100) unique,
especie varchar(500),
forma enum('NORMIE', 'HIBRIDA', 'MURCIELAGO', 'FANTASMA'), -- las formas solo son esas porque aunque tengan diferentes especies suelen estar en forma humana (normie) o híbridas )por ejemplo un humano con varios brazos prque es mitad araña)
color_piel VARCHAR(50),
color_pelo VARCHAR(50),
colmillos BOOLEAN,
gafas BOOLEAN,
alas BOOLEAN,
zombie BOOLEAN,
CONSTRAINT `fk_especie` FOREIGN KEY (`especie`) REFERENCES `SubEspecies` (`subespecie`) ON UPDATE CASCADE
);

create table personaje_habilidad_dominio(
personaje_id integer, 
habilidad_id integer,
dominio integer default 0,
primary key(`personaje_id`, `habilidad_id`),
CONSTRAINT `fk_habilidad` FOREIGN KEY (`habilidad_id`) REFERENCES `Habilidades` (`id`) ON UPDATE CASCADE,
CONSTRAINT `fk_personaje` FOREIGN KEY (`personaje_id`) REFERENCES `Monstruito` (`id`) ON UPDATE CASCADE
);

create table MonsterHigh(
alumno_id integer primary key auto_increment,
alumno varchar(500),
matriculado boolean default false,
fecha_inicio date,
fecha_fin date,
CONSTRAINT `fk_monstr` FOREIGN KEY (`alumno`) REFERENCES `Monstruito` (`nombre`) ON UPDATE CASCADE
);

