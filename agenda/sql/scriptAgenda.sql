DROP DATABASE IF EXISTS agenda;
CREATE DATABASE `agenda`;
USE agenda;

CREATE TABLE IF NOT EXISTS paises (
	idPais int AUTO_INCREMENT NOT NULL,
	nombre varchar(100) NOT NULL,
	PRIMARY KEY (idPais)
);

CREATE TABLE IF NOT EXISTS provincias (
	idProvincia int AUTO_INCREMENT NOT NULL,
	idPais int NOT NULL,
	nombreProvincia varchar(100) NOT NULL,
	PRIMARY KEY (idProvincia),
	FOREIGN KEY (idPais) REFERENCES paises(idPais)
);

CREATE TABLE IF NOT EXISTS localidades (
	idLocalidad int AUTO_INCREMENT NOT NULL,
	idProvincia int NOT NULL,
	nombre varchar(100) NOT NULL,
	PRIMARY KEY (idLocalidad),
	CONSTRAINT fk_localidades_provincias FOREIGN KEY (idProvincia) REFERENCES provincias(idProvincia)
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
	idPais int(45) NOT NULL,
	idProvincia int(45) NOT NULL,
	idLocalidad int(45) NOT NULL,
    PRIMARY KEY (idPersona)
);

INSERT into tiposdecontactos VALUES(1,"Trabajo");
INSERT into tiposdecontactos VALUES(2,"Familia");
INSERT into tiposdecontactos VALUES(3,"Amigos");

INSERT into paises VALUES (1, 'Argentina');
INSERT into paises VALUES (2, 'Uruguay');
INSERT into paises VALUES (3, 'Paraguay');

INSERT into provincias VALUES (1, 1, 'Buenos Aires');
INSERT into provincias VALUES (2, 1, 'Catamarca');
INSERT into provincias VALUES (3, 1, 'Chaco');

INSERT into localidades VALUES (1, 1, 'Malvinas Argentinas');
INSERT into localidades VALUES (2, 1, 'San Miguel');
INSERT into localidades VALUES (3, 1, 'Jose C. Paz');
