CREATE USER 'usuario'@'localhost' IDENTIFIED BY 'usuario';
GRANT ALL PRIVILEGES ON *.* TO 'usuario'@'localhost';
FLUSH PRIVILEGES;

CREATE DATABASE agenda;
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
    idForeignPais int(11) NOT NULL,
    PRIMARY KEY (idProvincia)
);

ALTER TABLE provincias ADD FOREIGN KEY(idForeignPais) REFERENCES paises(idPais) ON DELETE CASCADE;

CREATE TABLE IF NOT EXISTS localidades
(
    idLocalidad int(11) NOT NULL AUTO_INCREMENT,
    nombreLocalidad varchar(45) DEFAULT NULL,
    idForeignProvincia int(11) NOT NULL,
    PRIMARY KEY (idLocalidad)
);

ALTER TABLE localidades ADD FOREIGN KEY(idForeignProvincia) REFERENCES provincias(idProvincia) ON DELETE CASCADE;

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
	signoZodiaco varchar(45) NOT NULL,	
	pais varchar(45) NOT NULL,
	provincia varchar(45) NOT NULL,
	localidad varchar(45) NOT NULL,
    PRIMARY KEY (idPersona)
);

CREATE TABLE IF NOT EXISTS signozodiaco(
	idSignoZodiaco int NOT NULL AUTO_INCREMENT,
	nombreSignoZodiaco varchar(30) NOT NULL,
	PRIMARY KEY (idSignoZodiaco)
);

insert into paises values(1,"Argentina");
insert into provincias values(1,"Buenos Aires",1);
insert into localidades values(1,"Malvinas Argentinas",1);

insert into paises values(2,"Brasil");
insert into provincias values(2,"Rio de Janeiro",2);
insert into localidades values(2,"Playita",2);

insert into tiposdecontactos values(1,"Trabajo");
insert into tiposdecontactos values(2,"Familia");
insert into tiposdecontactos values(3,"Amigos");

insert into signozodiaco values(1,"Aries");
insert into signozodiaco values(2,"Tauro");
insert into signozodiaco values(3,"Geminis");
insert into signozodiaco values(4,"Cancer");
insert into signozodiaco values(5,"Leo");
insert into signozodiaco values(6,"Virgo");
insert into signozodiaco values(7,"Libra");
insert into signozodiaco values(8,"Escorpio");
insert into signozodiaco values(9,"Sagitario");
insert into signozodiaco values(10,"Capricornio");
insert into signozodiaco values(11,"Acuario");
insert into signozodiaco values(12,"Piscis");

