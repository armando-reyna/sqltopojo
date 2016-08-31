-- -----------------------------------------------------
-- Database: dermis
-- -----------------------------------------------------

drop database if exists dermis;
create schema dermis;
use dermis;

-- TABLES

--
-- Table structure for table `cat_user_role`
--
DROP TABLE IF EXISTS `cat_user_role`;
CREATE TABLE `cat_user_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL,
  `menu` varchar(50) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
INSERT INTO `cat_user_role` VALUES (1,'Visitante','menu_visitor');
INSERT INTO `cat_user_role` VALUES (2,'Administrador','menu_admin');
INSERT INTO `cat_user_role` VALUES (3,'Cliente','menu_client');

--
-- Table structure for table `cat_payment_method`
--
DROP TABLE IF EXISTS `cat_payment_method`;
create table `cat_payment_method`(
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(20) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
insert into cat_payment_method values(1, 'PayPal');
insert into cat_payment_method values(2, 'PayU');
insert into cat_payment_method values(3, 'Efectivo');

--
-- Table structure for table `tab_user_file`
--
DROP TABLE IF EXISTS `tab_user_file`;
CREATE TABLE `tab_user_file` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL,
  `name` varchar(2000) NOT NULL,
  `path` varchar(200) NOT NULL,
  `creation_date` timestamp DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  CONSTRAINT tab_user_file_user_fk FOREIGN KEY (`user_id`) REFERENCES `tab_user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Table structure for table `tab_cubicle_file`
--
DROP TABLE IF EXISTS `tab_cubicle_file`;
CREATE TABLE `tab_cubicle_file` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `cubicle_id` int(11) NOT NULL,
  `name` varchar(2000) NOT NULL,
  `path` varchar(200) NOT NULL,
  `creation_date` timestamp DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  CONSTRAINT tab_cubicle_file_cubicle_fk FOREIGN KEY (`cubicle_id`) REFERENCES `tab_cubicle` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Table structure for table `cat_cubicle_type`
--
DROP TABLE IF EXISTS `cat_cubicle_type`;
CREATE TABLE `cat_cubicle_type` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
insert into cat_cubicle_type values(null, 'Consultorio Médico');
insert into cat_cubicle_type values(null, 'Consultorio Dental');

--
-- Table structure for table `tab_cubicle`
--
DROP TABLE IF EXISTS `tab_cubicle`;
create table `tab_cubicle`(
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `id_type` int(10) NOT NULL,
  `name` varchar(100) NOT NULL,
  `capacity` int(20) NOT NULL,
  `description` varchar(500) NOT NULL,
  `location` varchar(200) NOT NULL,
  `active` int(1) NOT NULL DEFAULT '1',
  `cloud_path` varchar(200),
  `favorite_img_name` varchar(100),
  PRIMARY KEY (id),
  CONSTRAINT type_fk FOREIGN KEY (`id_type`) REFERENCES `cat_cubicle_type` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Table structure for table `cat_person_type`
--
DROP TABLE IF EXISTS `cat_person_type`;
CREATE TABLE `cat_person_type` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
insert into cat_person_type values(1, 'Cliente');
insert into cat_person_type values(2, 'Empleado');
insert into cat_person_type values(3, 'Proveedor');

--
-- Table structure for table `cat_estado`
--
DROP TABLE IF EXISTS `cat_estado`;
CREATE TABLE `cat_estado` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
insert into cat_estado values (null, 'Aguascalientes');
insert into cat_estado values (null, 'Baja California');
insert into cat_estado values (null, 'Baja California Sur');
insert into cat_estado values (null, 'Campeche');
insert into cat_estado values (null, 'Chiapas');
insert into cat_estado values (null, 'Chihuahua');
insert into cat_estado values (null, 'Coahuila');
insert into cat_estado values (null, 'Colima');
insert into cat_estado values (null, 'Distrito Federal');
insert into cat_estado values (null, 'Durango');
insert into cat_estado values (null, 'Estado de México');
insert into cat_estado values (null, 'Guanajuato');
insert into cat_estado values (null, 'Guerrero');
insert into cat_estado values (null, 'Hidalgo');
insert into cat_estado values (null, 'Jalisco');
insert into cat_estado values (null, 'Michoacán');
insert into cat_estado values (null, 'Morelos');
insert into cat_estado values (null, 'Nayarit');
insert into cat_estado values (null, 'Nuevo León');
insert into cat_estado values (null, 'Oaxaca');
insert into cat_estado values (null, 'Puebla');
insert into cat_estado values (null, 'Querétaro');
insert into cat_estado values (null, 'Quintana Roo');
insert into cat_estado values (null, 'San Luis Potosí');
insert into cat_estado values (null, 'Sinaloa');
insert into cat_estado values (null, 'Sonora');
insert into cat_estado values (null, 'Tabasco');
insert into cat_estado values (null, 'Tamaulipas');
insert into cat_estado values (null, 'Tlaxcala');
insert into cat_estado values (null, 'Veracruz');
insert into cat_estado values (null, 'Yucatán');
insert into cat_estado values (null, 'Zacatecas');

--
-- Table structure for table `tab_bank_account`
--
DROP TABLE IF EXISTS `tab_bank_account`;
CREATE TABLE `tab_bank_account` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `bank` varchar(100) NOT NULL,
  `beneficiario` varchar(100) NOT NULL,
  `cta` varchar(11) NOT NULL,
  `sucursal` varchar(50) NOT NULL,
  `clabe` varchar(18) NOT NULL,
  `forma_pago` varchar(100) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Table structure for table `cat_turno`
--
DROP TABLE IF EXISTS `cat_turno`;
CREATE TABLE `cat_turno` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
insert into cat_turno values (1, 'Matutino');
insert into cat_turno values (2, 'Vespertino');

--
-- Table structure for table `tab_adress`
--
DROP TABLE IF EXISTS `tab_adress`;
CREATE TABLE `tab_adress` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `id_estado` int(11) NOT NULL,
  `calle` varchar(100) NOT NULL,
  `no_ext` varchar(5) NOT NULL,
  `no_int` varchar(5) NULL,
  `colonia` varchar(100) NOT NULL,
  `delegacion` varchar(100) NOT NULL,
  `cp` varchar(5) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Table structure for table `tab_employee`
--
DROP TABLE IF EXISTS `tab_employee`;
CREATE TABLE `tab_employee` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `id_turno` int(11) NOT NULL,
  `id_adress_puesto` int(11) NOT NULL,
  `id_adress_doc` int(11) NOT NULL,
  `ccto` varchar(100) NOT NULL,
  `puesto` varchar(100) NOT NULL,
  `fecha_ingreso` timestamp NOT NULL,
  `horario` varchar(100) NOT NULL,
  `salario` double NOT NULL,
  `otros` varchar(100) NULL,
  `marca` varchar(100) NULL,
  `ubicacion` varchar(200) NULL,
  `jefe_directo` varchar(100) NULL,
  `doc_boutique` varchar(100) NULL,
  `doc_email` varchar(100) NULL,
  `doc_contacto` varchar(100) NULL,
  `doc_tel` varchar(100) NULL,
  `doc_obs` text NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `fk_tab_employee_id_adress_puesto` FOREIGN KEY (`id_adress_puesto`) REFERENCES `tab_adress` (`id`),
  CONSTRAINT `fk_tab_employee_id_adress_doc` FOREIGN KEY (`id_adress_doc`) REFERENCES `tab_adress` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Table structure for table `tab_person`
--
DROP TABLE IF EXISTS `tab_person`;
CREATE TABLE `tab_person` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `id_person_type` int(11) NOT NULL,
  `id_bank_account` int(11) NULL,
  `name` varchar(100) NOT NULL,
  `rfc` varchar(13) NOT NULL,
  `tel` varchar(20) NULL,
  `email` varchar(50) NULL,
  `contact_name` varchar(100) NULL,
  `first_time` timestamp NULL,
  `motivo` text NULL,
  `active` int(1) NOT NULL DEFAULT 1,
  `creation_date` timestamp DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  CONSTRAINT `fk_tab_person_id_person_type` FOREIGN KEY (`id_person_type`) REFERENCES `cat_person_type` (`id`),
  CONSTRAINT `fk_tab_person_id_bank_account` FOREIGN KEY (`id_bank_account`) REFERENCES `tab_bank_account` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Table structure for table `cat_person_file_type`
--
DROP TABLE IF EXISTS `cat_person_file_type`;
CREATE TABLE `cat_person_file_type` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `id_person_type` int(11) NOT NULL,
  `name` varchar(100) NOT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT fk_cat_person_file_type_id_person_type FOREIGN KEY (`id_person_type`) REFERENCES `cat_person_type` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Table structure for table `tab_person_file`
--
DROP TABLE IF EXISTS `tab_person_file`;
CREATE TABLE `tab_person_file` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `id_person` int(11) NOT NULL,
  `id_person_file_type` int(11) NOT NULL,
  `name` varchar(100) NOT NULL,
  `path` varchar(200) NOT NULL,
  `creation_date` timestamp DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  CONSTRAINT fk_tab_person_file_id_person FOREIGN KEY (`id_person`) REFERENCES `tab_person` (`id`),
  CONSTRAINT fk_tab_person_file_id_person_file_type FOREIGN KEY (`id_person_file_type`) REFERENCES `cat_person_file_type` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Table structure for table `tab_user`
--
DROP TABLE IF EXISTS `tab_user`;
CREATE TABLE `tab_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `id_role` int(11) NOT NULL,
  `id_person` int(11) NULL,
  `name` varchar(100) NOT NULL,
  `active` int(1) NOT NULL DEFAULT 1,
  `user` varchar(100) NOT NULL,
  `password` varchar(100) NOT NULL,
  `password_changed` int(1) NOT NULL DEFAULT 0,
  `creation_date` timestamp DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE KEY `user` (`user`),
  KEY `id_role` (`id_role`),
  CONSTRAINT `tab_user_ibfk_1` FOREIGN KEY (`id_role`) REFERENCES `cat_user_role` (`id`),
  CONSTRAINT fk_tab_user_id_person FOREIGN KEY (`id_person`) REFERENCES `tab_person` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
INSERT INTO `tab_user` VALUES (null,2,null,'Armando Reyna',1,'armando@airsoftware.com.mx','4rm4nd0',1,NOW());

--
-- Table structure for table `tab_event`
--
DROP TABLE IF EXISTS `tab_event`;
CREATE TABLE `tab_event` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(100) NOT NULL,
  `date` timestamp NOT NULL,
  `start_hour` int (3) NOT NULL,
  `end_hour` int (3) NOT NULL,
  `user_id` int(11) NOT NULL,
  `cubicle_id` int(11) NOT NULL,
  `active` int(1) NOT NULL DEFAULT 1,
  PRIMARY KEY (`id`),
  CONSTRAINT `event_user_fk` FOREIGN KEY (`user_id`) REFERENCES tab_user (`id`),
  CONSTRAINT `event_cubicle_fk` FOREIGN KEY (`cubicle_id`) REFERENCES tab_cubicle (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;