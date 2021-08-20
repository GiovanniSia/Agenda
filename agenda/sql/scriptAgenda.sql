CREATE DATABASE IF NOT EXISTS `agenda`;

USE agenda;

CREATE TABLE IF NOT EXISTS `personas`
(
  `idPersona` int(11) NOT NULL AUTO_INCREMENT,
  `Nombre` varchar(45) NOT NULL,
  `Telefono` varchar(20) NOT NULL,
  PRIMARY KEY (`idPersona`)
);

CREATE TABLE IF NOT EXISTS `tiposdecontactos`
(
    `idTipoContacto` int(11) NOT NULL AUTO_INCREMENT,
    `nombreTipoContacto` varchar(45) DEFAULT NULL,
	PRIMARY KEY (`idTipoContacto`)
);

//Ingreso valores predeterminados a la tabla tiposDeContactos

insert into tiposdecontactos values(1,"Trabajo");
insert into tiposdecontactos values(2,"Familia");
insert into tiposdecontactos values(3,"Amigos");
