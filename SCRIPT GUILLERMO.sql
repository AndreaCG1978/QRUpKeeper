CREATE DATABASE datacenter_log; 

set foreign_key_checks=0;


DROP TABLE IF EXISTS `inspectors`; 

CREATE TABLE `inspectors` (
  `id` int(11) NOT NULL,
  `code` varchar(10) COLLATE utf8_spanish_ci NOT NULL,
  `usr` varchar(80) COLLATE utf8_spanish_ci NOT NULL,
  `password` varchar(80) COLLATE utf8_spanish_ci NOT NULL,
  `description` varchar(150) COLLATE utf8_spanish_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

DROP TABLE IF EXISTS `datacenters`; 

CREATE TABLE `datacenters` (
  `id` int(11) NOT NULL,
  `code` varchar(10) COLLATE utf8_spanish_ci NOT NULL,
  `name` varchar(80) COLLATE utf8_spanish_ci NOT NULL,
  `description` varchar(150) COLLATE utf8_spanish_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;


DROP TABLE IF EXISTS `tablero_tgbt`; 

CREATE TABLE `tablero_tgbt` (
      `id` int(20) NOT NULL AUTO_INCREMENT,
      `name` varchar(10) NOT NULL,
      `description` varchar(50) NULL,
      `nroForm` varchar(20) NULL,
      `kwr` varchar(10) NULL,
      `kws` varchar(10) NULL,
      `kwt` varchar(10) NULL,
      `par` varchar(10) NULL,
      `pas` varchar(10) NULL,
      `pat` varchar(10) NULL,
      `inspectorId` int(11) NOT NULL,
      `datacenterId` int(11) NOT NULL,
      `datetime` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
      PRIMARY KEY (`id`),
      KEY `inspectorId` (`inspectorId`),
      KEY `datacenterId` (`datacenterId`),
      CONSTRAINT `tablero-tgbt_ibfk_1` FOREIGN KEY (`inspectorId`) REFERENCES `inspectors` (`id`),
      CONSTRAINT `tablero-tgbt_ibfk_2` FOREIGN KEY (`datacenterId`) REFERENCES `datacenters` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;


DROP TABLE IF EXISTS `tablero_airechiller`; 

CREATE TABLE `tablero_airechiller` (
      `id` int(20) NOT NULL AUTO_INCREMENT,
      `name` varchar(10) NOT NULL,
      `description` varchar(50) NULL,
      `nroForm` varchar(20) NULL,
      `kwr` varchar(10) NULL,
      `kws` varchar(10) NULL,
      `kwt` varchar(10) NULL,
      `par` varchar(10) NULL,
      `pas` varchar(10) NULL,
      `pat` varchar(10) NULL,
      `inspectorId` int(11) NOT NULL,
      `datacenterId` int(11) NOT NULL,
      `datetime` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
      PRIMARY KEY (`id`),
      KEY `inspectorId` (`inspectorId`),
      KEY `datacenterId` (`datacenterId`),
      CONSTRAINT `tablero-airechiller_ibfk_1` FOREIGN KEY (`inspectorId`) REFERENCES `inspectors` (`id`),
      CONSTRAINT `tablero-airechiller_ibfk_2` FOREIGN KEY (`datacenterId`) REFERENCES `datacenters` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

DROP TABLE IF EXISTS `tablero_crac`; 

CREATE TABLE `tablero_crac` (
      `id` int(20) NOT NULL AUTO_INCREMENT,
      `name` varchar(10) NOT NULL,
      `description` varchar(50) NULL,
      `nroForm` varchar(20) NULL,
      `kwr` varchar(10) NULL,
      `kws` varchar(10) NULL,
      `kwt` varchar(10) NULL,
      `par` varchar(10) NULL,
      `pas` varchar(10) NULL,
      `pat` varchar(10) NULL,
      `inspectorId` int(11) NOT NULL,
      `datacenterId` int(11) NOT NULL,
      `datetime` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
      PRIMARY KEY (`id`),
      KEY `inspectorId` (`inspectorId`),
      KEY `datacenterId` (`datacenterId`),
      CONSTRAINT `tablero-crac_ibfk_1` FOREIGN KEY (`inspectorId`) REFERENCES `inspectors` (`id`),
      CONSTRAINT `tablero-crac_ibfk_2` FOREIGN KEY (`datacenterId`) REFERENCES `datacenters` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;




DROP TABLE IF EXISTS `tablero_inups`; 

CREATE TABLE `tablero_inups` (
      `id` int(20) NOT NULL AUTO_INCREMENT,
      `name` varchar(10) NOT NULL,
      `description` varchar(50) NULL,
      `nroForm` varchar(20) NULL,
      `kwr` varchar(10) NULL,
      `kws` varchar(10) NULL,
      `kwt` varchar(10) NULL,
      `par` varchar(10) NULL,
      `pas` varchar(10) NULL,
      `pat` varchar(10) NULL,
      `inspectorId` int(11) NOT NULL,
      `datacenterId` int(11) NOT NULL,
      `datetime` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
      PRIMARY KEY (`id`),
      KEY `inspectorId` (`inspectorId`),
      KEY `datacenterId` (`datacenterId`),
      CONSTRAINT `tablero-inups_ibfk_1` FOREIGN KEY (`inspectorId`) REFERENCES `inspectors` (`id`),
      CONSTRAINT `tablero-inups_ibfk_2` FOREIGN KEY (`datacenterId`) REFERENCES `datacenters` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

DROP TABLE IF EXISTS `load_ups`; 

CREATE TABLE `load_ups` (
      `id` int(20) NOT NULL AUTO_INCREMENT,
      `name` varchar(10) NOT NULL,
      `description` varchar(50) NULL,
      `nroForm` varchar(20) NULL,
      `percent_r` varchar(10) NULL,
      `percent_s` varchar(10) NULL,
      `percent_t` varchar(10) NULL,
      `alarma` varchar(5) NULL,
      `inspectorId` int(11) NOT NULL,
      `datacenterId` int(11) NOT NULL,
      `datetime` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
      PRIMARY KEY (`id`),
      KEY `inspectorId` (`inspectorId`),
      KEY `datacenterId` (`datacenterId`),
      CONSTRAINT `load-ups_ibfk_1` FOREIGN KEY (`inspectorId`) REFERENCES `inspectors` (`id`),
      CONSTRAINT `load-ups_ibfk_2` FOREIGN KEY (`datacenterId`) REFERENCES `datacenters` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

INSERT INTO `inspectors` VALUES (1,'1','JuanPerez','1234','Ingeniero Juan Perez');
INSERT INTO `inspectors` VALUES (2,'2','MarioSanchez','1234','Ingeniero Mario Sanchez');
INSERT INTO `inspectors` VALUES (3,'3','JulioFernandez','1234','Julio Fernandez');

INSERT INTO `datacenters` VALUES (1,'1001','Reconquista', 'Reconquista');
INSERT INTO `datacenters` VALUES (2,'1002','Cevallos', 'Cevallos');
INSERT INTO `datacenters` VALUES (3,'1003','Cordoba', 'Cordoba');
INSERT INTO `datacenters` VALUES (4,'1004','Rosario', 'Rosario');
INSERT INTO `datacenters` VALUES (5,'1005','Los Patos I', 'Los Patos I');
INSERT INTO `datacenters` VALUES (6,'1006','Los Patos II', 'Los Patos II');



set foreign_key_checks=1;