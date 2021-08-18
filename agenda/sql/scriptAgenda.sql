DROP DATABASE IF EXISTS agenda;
CREATE DATABASE `agenda`;
USE agenda;
CREATE TABLE `personas`	(
	`idPersona` int(11) NOT NULL AUTO_INCREMENT,
  	`Nombre` varchar(45) NOT NULL,
  	`Telefono` varchar(20) NOT NULL,
	`calle` varchar(45) NOT NULL,
	`altura` varchar(45) NOT NULL,
	`piso` varchar(45) NOT NULL,
	`departamento` varchar(45) NOT NULL,
	`email` varchar(45) NOT NULL,
	`fechaCumpleanios` Date,
	PRIMARY KEY (`idPersona`)	
	);