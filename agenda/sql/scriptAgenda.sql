DROP DATABASE IF EXISTS agenda;
CREATE DATABASE `agenda`;
USE agenda;

CREATE TABLE IF NOT EXISTS paises
(
    idPais int(11) NOT NULL AUTO_INCREMENT,
    nombrePais varchar(45) DEFAULT NULL,
    PRIMARY KEY (idPais)
);

CREATE TABLE IF NOT EXISTS provincias
(
    idProvincia int(11) NOT NULL AUTO_INCREMENT,
    nombreProvincia varchar(45) DEFAULT NULL,
    foreignPais int(11) NOT NULL,
    PRIMARY KEY (idProvincia),
    FOREIGN KEY (foreignPais) REFERENCES paises(idPais)
);

CREATE TABLE IF NOT EXISTS localidades
(
    idLocalidad int(11) NOT NULL AUTO_INCREMENT,
    nombreLocalidad varchar(45) DEFAULT NULL,
    idForeignPais int(11) NOT NULL,
    idForeignProvincia int(11) NOT NULL,
    PRIMARY KEY (idLocalidad),
    FOREIGN KEY (idForeignProvincia) REFERENCES provincias(idProvincia),
    FOREIGN KEY (idForeignPais) REFERENCES paises(idPais)
);

CREATE TABLE IF NOT EXISTS tiposdecontactos(
    idTipoContacto int(11) NOT NULL AUTO_INCREMENT,
    nombreTipoContacto varchar(45) DEFAULT NULL,
	PRIMARY KEY (idTipoContacto)
);
CREATE TABLE IF NOT EXISTS personas (
	idPersona int(11) NOT NULL AUTO_INCREMENT,
  	Nombre varchar(45) NOT NULL,
  	Telefono varchar(20) NOT NULL,
	calle varchar(45) NOT NULL,
	altura varchar(45) NOT NULL,
	piso varchar(45) NOT NULL,
	departamento varchar(45) NOT NULL,
	email varchar(45) NOT NULL,
	fechaCumpleanios date,
	tipoContacto varchar(45) NOT NULL,	
	pais varchar(45) NOT NULL,
	provincia varchar(45) NOT NULL,
	localidad varchar(45) NOT NULL,
    PRIMARY KEY (idPersona)
);

insert into paises values(1,"Argentina");
insert into provincias values(1,"Buenos Aires",1);
insert into localidades values(1,"Malvinas Argentinas",1,1);
