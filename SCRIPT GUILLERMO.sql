CREATE DATABASE datacenter_log; 

set foreign_key_checks=0;


DROP TABLE IF EXISTS `inspectors`; 

CREATE TABLE `inspectors` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `codigo` varchar(10) COLLATE utf8_spanish_ci NOT NULL,
  `usr` varchar(15) COLLATE utf8_spanish_ci NOT NULL,
  `psw` varchar(15) COLLATE utf8_spanish_ci NOT NULL,
  `description` varchar(150) COLLATE utf8_spanish_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf_unicode_ci;

DROP TABLE IF EXISTS `datacenters`; 

CREATE TABLE `datacenters` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `codigo` varchar(10) COLLATE utf8_spanish_ci NOT NULL,
  `name` varchar(80) COLLATE utf8_spanish_ci NOT NULL,
  `description` varchar(150) COLLATE utf8_spanish_ci DEFAULT NULL,    
  `cantTableroTGBT` int(4) NOT NULL DEFAULT 2,
  `cantTableroAireChiller` int(4) NOT NULL DEFAULT 2,
  `cantTableroCrac` int(4) NOT NULL DEFAULT 4,
  `cantTableroInUps` int(4) NOT NULL DEFAULT 3,
  `cantLoadUps` int(4) NOT NULL DEFAULT 7,
  `cantGrupoElectrogeno` int(4) NOT NULL DEFAULT 3,
  `cantAireCrac` int(4) NOT NULL DEFAULT 16,
  `cantAireChiller` int(4) NOT NULL DEFAULT 3,
  `cantIncendio` int(4) NOT NULL DEFAULT 1,
  `cantPresostato` int(4) NOT NULL DEFAULT 3,
  `cantAAcondSalaBateria` int(4) NOT NULL DEFAULT 1,
  `cantTableroPDR` int(4) NOT NULL DEFAULT 6,
  `cantPresurizacionEscalera` int(4) NOT NULL DEFAULT 1,
  `cantEstractorAire` int(4) NOT NULL DEFAULT 4,
  `cantPresurizacionCanieria` int(4) NOT NULL DEFAULT 2,
  
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;


DROP TABLE IF EXISTS `forms`; 

CREATE TABLE `forms` (
  `id` int(20) NOT NULL AUTO_INCREMENT,
  `nroForm` varchar(100) COLLATE utf8_spanish_ci,
  `inspectorId` int(11) NOT NULL,
  `datacenterId` int(11) NOT NULL,
  `description` varchar(150) COLLATE utf8_spanish_ci DEFAULT NULL,
  `fecha` varchar(30) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;


DROP TABLE IF EXISTS `tablero_tgbt`; 

CREATE TABLE `tablero_tgbt` (
      `id` int(20) NOT NULL AUTO_INCREMENT,
      `name` varchar(30) NOT NULL,
      `description` varchar(150) NULL,
      `codigo` int(4) NOT NULL,
      `kwr` varchar(10) NULL,
      `kws` varchar(10) NULL,
      `kwt` varchar(10) NULL,
      `par` varchar(10) NULL,
      `pas` varchar(10) NULL,
      `pat` varchar(10) NULL,
	`idForm` int(20) NOT NULL,
      PRIMARY KEY (`id`),
      KEY `idForm` (`idForm`),
      CONSTRAINT `artefact_ibfk_1` FOREIGN KEY (`idForm`) REFERENCES `forms` (`id`)      
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;


DROP TABLE IF EXISTS `tablero_airechiller`; 

CREATE TABLE `tablero_airechiller` (
      `id` int(20) NOT NULL AUTO_INCREMENT,
      `name` varchar(30) NOT NULL,
      `description` varchar(150) NULL,
	 `codigo` int(4) NOT NULL,
      `kwr` varchar(10) NULL,
      `kws` varchar(10) NULL,
      `kwt` varchar(10) NULL,
      `par` varchar(10) NULL,
      `pas` varchar(10) NULL,
      `pat` varchar(10) NULL,
     	 `idForm` int(20) NOT NULL,
      PRIMARY KEY (`id`),
      KEY `idForm` (`idForm`),
      CONSTRAINT `artefact_ibfk_2` FOREIGN KEY (`idForm`) REFERENCES `forms` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

DROP TABLE IF EXISTS `tablero_crac`; 

CREATE TABLE `tablero_crac` (
      `id` int(20) NOT NULL AUTO_INCREMENT,
      `name` varchar(30) NOT NULL,
      `description` varchar(150) NULL,
	 `codigo` int(4) NOT NULL,
      `kwr` varchar(10) NULL,
      `kws` varchar(10) NULL,
      `kwt` varchar(10) NULL,
      `par` varchar(10) NULL,
      `pas` varchar(10) NULL,
      `pat` varchar(10) NULL,
      `idForm` int(20) NOT NULL,
      PRIMARY KEY (`id`),
      KEY `idForm` (`idForm`),
      CONSTRAINT `artefact_ibfk_3` FOREIGN KEY (`idForm`) REFERENCES `forms` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;




DROP TABLE IF EXISTS `tablero_inups`; 

CREATE TABLE `tablero_inups` (
      `id` int(20) NOT NULL AUTO_INCREMENT,
      `name` varchar(30) NOT NULL,
      `description` varchar(150) NULL,
	 `codigo` int(4) NOT NULL,
      `kwr` varchar(10) NULL,
      `kws` varchar(10) NULL,
      `kwt` varchar(10) NULL,
      `par` varchar(10) NULL,
      `pas` varchar(10) NULL,
      `pat` varchar(10) NULL,
      `idForm` int(20) NOT NULL,
      PRIMARY KEY (`id`),
      KEY `idForm` (`idForm`),
      CONSTRAINT `artefact_ibfk_4` FOREIGN KEY (`idForm`) REFERENCES `forms` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

DROP TABLE IF EXISTS `load_ups`; 

CREATE TABLE `load_ups` (
      `id` int(20) NOT NULL AUTO_INCREMENT,
      `name` varchar(30) NOT NULL,
      `description` varchar(150) NULL,
	 `codigo` int(4) NOT NULL,
      `percent_r` varchar(10) NULL,
      `percent_s` varchar(10) NULL,
      `percent_t` varchar(10) NULL,
      `alarma` varchar(5) NULL,
      `idForm` int(20) NOT NULL,
      PRIMARY KEY (`id`),
      KEY `idForm` (`idForm`),
      CONSTRAINT `artefact_ibfk_5` FOREIGN KEY (`idForm`) REFERENCES `forms` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

DROP TABLE IF EXISTS `grupo_electrogeno`; 

CREATE TABLE `grupo_electrogeno` (
      `id` int(20) NOT NULL AUTO_INCREMENT,
      `name` varchar(40) NOT NULL,
      `description` varchar(150) NULL,
	 `codigo` int(4) NOT NULL,
      `percent_comb` varchar(10) NULL,
      `temperatura` varchar(10) NULL,
      `alarma` varchar(5) NULL,
      `auto` varchar(5) NULL,
      `nivelcomb75` varchar(5) NULL,
      `precalent` varchar(5) NULL,
      `cargadorbat` varchar(5) NULL,
      `idForm` int(20) NOT NULL,
      PRIMARY KEY (`id`),
      KEY `idForm` (`idForm`),
      CONSTRAINT `artefact_ibfk_6` FOREIGN KEY (`idForm`) REFERENCES `forms` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;


DROP TABLE IF EXISTS `aire_crac`; 

CREATE TABLE `aire_crac` (
      `id` int(20) NOT NULL AUTO_INCREMENT,
      `name` varchar(40) NOT NULL,
      `description` varchar(150) NULL,
	 `codigo` int(4) NOT NULL,
      `temperatura` varchar(10) NULL,
      `funciona_ok` varchar(5) NULL,
      `idForm` int(20) NOT NULL,
      PRIMARY KEY (`id`),
      KEY `idForm` (`idForm`),
      CONSTRAINT `artefact_ibfk_7` FOREIGN KEY (`idForm`) REFERENCES `forms` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;


DROP TABLE IF EXISTS `aire_chiller`; 

CREATE TABLE `aire_chiller` (
      `id` int(20) NOT NULL AUTO_INCREMENT,
      `name` varchar(40) NOT NULL,
      `description` varchar(150) NULL,
	 `codigo` int(4) NOT NULL,
      `comp1_ok` varchar(5) NULL,
      `comp2_ok` varchar(5) NULL,
      `comp1_load` varchar(10) NULL,
      `comp2_load` varchar(10) NULL,
      `atr_out` varchar(10) NULL,
      `idForm` int(20) NOT NULL,
      PRIMARY KEY (`id`),
      KEY `idForm` (`idForm`),
      CONSTRAINT `artefact_ibfk_8` FOREIGN KEY (`idForm`) REFERENCES `forms` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;


DROP TABLE IF EXISTS `incendio`; 

CREATE TABLE `incendio` (
      `id` int(20) NOT NULL AUTO_INCREMENT,
      `name` varchar(40) NOT NULL,
      `description` varchar(150) NULL,
	 `codigo` int(4) NOT NULL,
      `funciona_ok` varchar(5) NULL,
      `presion` varchar(10) NULL,
      `energiaA_ok` varchar(5) NULL,
      `energiaB_ok` varchar(5) NULL,
      `idForm` int(20) NOT NULL,
      PRIMARY KEY (`id`),
      KEY `idForm` (`idForm`),
      CONSTRAINT `artefact_ibfk_9` FOREIGN KEY (`idForm`) REFERENCES `forms` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

DROP TABLE IF EXISTS `presostato`; 

CREATE TABLE `presostato` (
      `id` int(20) NOT NULL AUTO_INCREMENT,
      `name` varchar(40) NOT NULL,
      `description` varchar(150) NULL,
	 `codigo` int(4) NOT NULL,
      `aire_ok` varchar(5) NULL,
      `aire_presion` varchar(10) NULL,
      `agua_ok` varchar(5) NULL,
      `agua_presion` varchar(10) NULL,
      `idForm` int(20) NOT NULL,
      PRIMARY KEY (`id`),
      KEY `idForm` (`idForm`),
      CONSTRAINT `artefact_ibfk_10` FOREIGN KEY (`idForm`) REFERENCES `forms` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;


DROP TABLE IF EXISTS `aireAcond`; 

CREATE TABLE `aireAcond` (
      `id` int(20) NOT NULL AUTO_INCREMENT,
      `name` varchar(40) NOT NULL,
      `description` varchar(150) NULL,
	 `codigo` int(4) NOT NULL,
      `funciona_ok` varchar(5) NULL,
      `temperatura` varchar(10) NULL,
      `idForm` int(20) NOT NULL,
      PRIMARY KEY (`id`),
      KEY `idForm` (`idForm`),
      CONSTRAINT `artefact_ibfk_11` FOREIGN KEY (`idForm`) REFERENCES `forms` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;



DROP TABLE IF EXISTS `tableroPDR`; 

CREATE TABLE `tableroPDR` (
      `id` int(20) NOT NULL AUTO_INCREMENT,
      `name` varchar(40) NOT NULL,
      `description` varchar(150) NULL,
	 `codigo` int(4) NOT NULL,
      `pottotRA` varchar(10) NULL,
      `pottotRB` varchar(10) NULL,
      `idForm` int(20) NOT NULL,
      PRIMARY KEY (`id`),
      KEY `idForm` (`idForm`),
      CONSTRAINT `artefact_ibfk_12` FOREIGN KEY (`idForm`) REFERENCES `forms` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;


DROP TABLE IF EXISTS `presurizacionEscalera`; 

CREATE TABLE `presurizacionEscalera` (
      `id` int(20) NOT NULL AUTO_INCREMENT,
      `name` varchar(40) NOT NULL,
      `description` varchar(150) NULL,
	 `codigo` int(4) NOT NULL,
      `arranque` varchar(5) NULL,
      `tiemp` varchar(5) NULL,
      `funcionamiento` varchar(5) NULL,
      `engrase` varchar(5) NULL,
      `limpieza` varchar(5) NULL,
      `correas` varchar(5) NULL,
      `idForm` int(20) NOT NULL,
      PRIMARY KEY (`id`),
      KEY `idForm` (`idForm`),
      CONSTRAINT `artefact_ibfk_13` FOREIGN KEY (`idForm`) REFERENCES `forms` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;


DROP TABLE IF EXISTS `estractorAire`; 

CREATE TABLE `estractorAire` (
      `id` int(20) NOT NULL AUTO_INCREMENT,
      `name` varchar(40) NOT NULL,
      `description` varchar(150) NULL,
	 `codigo` int(4) NOT NULL,
      `arranque` varchar(5) NULL,
      `funcionamiento` varchar(5) NULL,
      `engrase` varchar(5) NULL,
      `limpieza` varchar(5) NULL,
      `correas` varchar(5) NULL,
      `idForm` int(20) NOT NULL,
      PRIMARY KEY (`id`),
      KEY `idForm` (`idForm`),
      CONSTRAINT `artefact_ibfk_14` FOREIGN KEY (`idForm`) REFERENCES `forms` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;


DROP TABLE IF EXISTS `presurizacionCanieria`; 


CREATE TABLE `presurizacionCanieria` (
      `id` int(20) NOT NULL AUTO_INCREMENT,
      `name` varchar(40) NOT NULL,
      `description` varchar(150) NULL,
	 `codigo` int(4) NOT NULL,
      `alarma` varchar(5) NULL,
      `encendido` varchar(5) NULL,
      `idForm` int(20) NOT NULL,
      PRIMARY KEY (`id`),
      KEY `idForm` (`idForm`),
      CONSTRAINT `artefact_ibfk_15` FOREIGN KEY (`idForm`) REFERENCES `forms` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;




INSERT INTO `inspectors` VALUES (1,'1','JuanPerez','1234','Ingeniero Juan Perez');
INSERT INTO `inspectors` VALUES (2,'2','MarioSanchez','1234','Ingeniero Mario Sanchez');
INSERT INTO `inspectors` VALUES (3,'3','JulioFernandez','1234','Julio Fernandez');
INSERT INTO `inspectors` VALUES (4,'4','andrea','1234','Lic. Andrea Cecilia Grassano');



INSERT INTO `datacenters` VALUES (1,'1001','Reconquista', 'Reconquista',2,2,4,3,7,3,16,3,1,3,1,6,1,4,2);
INSERT INTO `datacenters` VALUES (2,'1002','Cevallos', 'Cevallos',2,2,4,3,7,3,16,3,1,3,1,6,1,4,2);
INSERT INTO `datacenters` VALUES (3,'1003','Cordoba', 'Cordoba',2,2,4,3,7,3,16,3,1,3,1,6,1,4,2);
INSERT INTO `datacenters` VALUES (4,'1004','Rosario', 'Rosario',2,2,4,3,7,3,16,3,1,3,1,6,1,4,2);
INSERT INTO `datacenters` VALUES (5,'1005','Los Patos I', 'Los Patos I',2,2,4,3,7,3,16,3,1,3,1,6,1,4,2);
INSERT INTO `datacenters` VALUES (6,'1006','Los Patos II', 'Los Patos II',2,2,4,3,7,3,16,3,1,3,1,6,1,4,2);


set foreign_key_checks=1;
