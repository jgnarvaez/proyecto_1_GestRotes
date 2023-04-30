-- MySQL Script generated by MySQL Workbench
-- Sat Apr 29 11:51:21 2023
-- Model: New Model    Version: 1.0
-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `mydb` ;

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `mydb` DEFAULT CHARACTER SET utf8 ;
USE `mydb` ;

-- -----------------------------------------------------
-- Table `mydb`.`tbl_persona_universitaria`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`tbl_persona_universitaria` ;

CREATE TABLE IF NOT EXISTS `mydb`.`tbl_persona_universitaria` (
  `pu_id` INT NOT NULL AUTO_INCREMENT,
  `pu_nombres` VARCHAR(20) NULL,
  `pu_apellidos` VARCHAR(20) NULL,
  `pu_identificacion` BIGINT(15) NULL,
  `pu_correo` VARCHAR(45) NULL,
  `pu_foto_perfil` VARCHAR(150) NULL,
  PRIMARY KEY (`pu_id`),
  UNIQUE INDEX `pu_id_UNIQUE` (`pu_id` ASC) VISIBLE,
  UNIQUE INDEX `pu_identificacion_UNIQUE` (`pu_identificacion` ASC) VISIBLE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`tbl_estudiante`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`tbl_estudiante` ;

CREATE TABLE IF NOT EXISTS `mydb`.`tbl_estudiante` (
  `pu_id` INT NOT NULL,
  PRIMARY KEY (`pu_id`),
  CONSTRAINT `fk_tbl_estudiante_tbl_persona_universitaria`
    FOREIGN KEY (`pu_id`)
    REFERENCES `mydb`.`tbl_persona_universitaria` (`pu_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`tbl_coordinador`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`tbl_coordinador` ;

CREATE TABLE IF NOT EXISTS `mydb`.`tbl_coordinador` (
  `coo_id` INT NOT NULL AUTO_INCREMENT,
  `coo_nombres` VARCHAR(50) NULL,
  `coo_apellidos` VARCHAR(50) NULL,
  `coo_correo` VARCHAR(45) NULL,
  `coo_clave` VARCHAR(20) NULL,
  `coo_rol` ENUM('Coordinador de asignatura', 'Coordinador de programa') NULL,
  `coo_foto_perfil` VARCHAR(150) NULL,
  PRIMARY KEY (`coo_id`),
  UNIQUE INDEX `coo_id_UNIQUE` (`coo_id` ASC) VISIBLE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`tbl_programa`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`tbl_programa` ;

CREATE TABLE IF NOT EXISTS `mydb`.`tbl_programa` (
  `prog_id` INT NOT NULL AUTO_INCREMENT,
  `prog_nombre` VARCHAR(45) NULL,
  PRIMARY KEY (`prog_id`),
  UNIQUE INDEX `prog_id_UNIQUE` (`prog_id` ASC) VISIBLE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`tbl_asignatura`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`tbl_asignatura` ;

CREATE TABLE IF NOT EXISTS `mydb`.`tbl_asignatura` (
  `asig_id` INT NOT NULL AUTO_INCREMENT,
  `asig_nombre` VARCHAR(100) NULL,
  PRIMARY KEY (`asig_id`),
  UNIQUE INDEX `asig_id_UNIQUE` (`asig_id` ASC) VISIBLE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`tbl_escenario_practica`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`tbl_escenario_practica` ;

CREATE TABLE IF NOT EXISTS `mydb`.`tbl_escenario_practica` (
  `esc_id` INT NOT NULL AUTO_INCREMENT,
  `esc_nombre` VARCHAR(100) NULL,
  PRIMARY KEY (`esc_id`),
  UNIQUE INDEX `esc_id_UNIQUE` (`esc_id` ASC) VISIBLE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`tbl_jornada`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`tbl_jornada` ;

CREATE TABLE IF NOT EXISTS `mydb`.`tbl_jornada` (
  `jor_id` INT NOT NULL AUTO_INCREMENT,
  `jor_franja` VARCHAR(30) NULL,
  `jor_hora_inicio` TIME NULL,
  `jor_hora_fin` TIME NULL,
  PRIMARY KEY (`jor_id`),
  UNIQUE INDEX `jor_id_UNIQUE` (`jor_id` ASC) VISIBLE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`tbl_servicio`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`tbl_servicio` ;

CREATE TABLE IF NOT EXISTS `mydb`.`tbl_servicio` (
  `ser_id` INT NOT NULL AUTO_INCREMENT,
  `ser_nombre` VARCHAR(30) NULL,
  PRIMARY KEY (`ser_id`),
  UNIQUE INDEX `ser_id_UNIQUE` (`ser_id` ASC) VISIBLE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`tbl_etiqueta`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`tbl_etiqueta` ;

CREATE TABLE IF NOT EXISTS `mydb`.`tbl_etiqueta` (
  `eti_id` INT NOT NULL AUTO_INCREMENT,
  `eti_nombre` VARCHAR(30) NOT NULL,
  `eti_esc_id` INT NOT NULL,
  `eti_ser_id` INT NULL,
  PRIMARY KEY (`eti_id`, `eti_esc_id`),
  UNIQUE INDEX `eti_id_UNIQUE` (`eti_id` ASC) VISIBLE,
  INDEX `fk_tbl_etiqueta_tbl_escenario_practica1_idx` (`eti_esc_id` ASC) VISIBLE,
  INDEX `fk_tbl_etiqueta_tbl_servicio1_idx` (`eti_ser_id` ASC) VISIBLE,
  UNIQUE INDEX `ser_id_UNIQUE` (`eti_ser_id` ASC) VISIBLE,
  CONSTRAINT `fk_tbl_etiqueta_tbl_escenario_practica1`
    FOREIGN KEY (`eti_esc_id`)
    REFERENCES `mydb`.`tbl_escenario_practica` (`esc_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_tbl_etiqueta_tbl_servicio1`
    FOREIGN KEY (`eti_ser_id`)
    REFERENCES `mydb`.`tbl_servicio` (`ser_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`tbl_turno`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`tbl_turno` ;

CREATE TABLE IF NOT EXISTS `mydb`.`tbl_turno` (
  `tur_id` INT NOT NULL AUTO_INCREMENT,
  `tur_fecha` DATE NULL,
  `tur_desayuno` TINYINT NULL,
  `tur_almuerzo` TINYINT NULL,
  `tur_comida` TINYINT NULL,
  `tur_jor_id` INT NOT NULL,
  `tur_eti_id` INT NOT NULL,
  `tur_asig_id` INT NOT NULL,
  PRIMARY KEY (`tur_id`, `tur_jor_id`, `tur_eti_id`, `tur_asig_id`),
  UNIQUE INDEX `tur_id_UNIQUE` (`tur_id` ASC) VISIBLE,
  INDEX `fk_tbl_turno_tbl_jornada1_idx` (`tur_jor_id` ASC) VISIBLE,
  INDEX `fk_tbl_turno_tbl_etiqueta1_idx` (`tur_eti_id` ASC) VISIBLE,
  INDEX `fk_tbl_turno_tbl_asignatura1_idx` (`tur_asig_id` ASC) VISIBLE,
  CONSTRAINT `fk_tbl_turno_tbl_jornada1`
    FOREIGN KEY (`tur_jor_id`)
    REFERENCES `mydb`.`tbl_jornada` (`jor_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_tbl_turno_tbl_etiqueta1`
    FOREIGN KEY (`tur_eti_id`)
    REFERENCES `mydb`.`tbl_etiqueta` (`eti_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_tbl_turno_tbl_asignatura1`
    FOREIGN KEY (`tur_asig_id`)
    REFERENCES `mydb`.`tbl_asignatura` (`asig_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`tbl_turno_estudiante`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`tbl_turno_estudiante` ;

CREATE TABLE IF NOT EXISTS `mydb`.`tbl_turno_estudiante` (
  `tur_id` INT NOT NULL,
  `pu_id` INT NOT NULL,
  PRIMARY KEY (`tur_id`, `pu_id`),
  INDEX `fk_tbl_turno_has_tbl_estudiante_tbl_estudiante1_idx` (`pu_id` ASC) VISIBLE,
  INDEX `fk_tbl_turno_has_tbl_estudiante_tbl_turno1_idx` (`tur_id` ASC) VISIBLE,
  CONSTRAINT `fk_tbl_turno_has_tbl_estudiante_tbl_turno1`
    FOREIGN KEY (`tur_id`)
    REFERENCES `mydb`.`tbl_turno` (`tur_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_tbl_turno_has_tbl_estudiante_tbl_estudiante1`
    FOREIGN KEY (`pu_id`)
    REFERENCES `mydb`.`tbl_estudiante` (`pu_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`tbl_coordinador_asignatura`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`tbl_coordinador_asignatura` ;

CREATE TABLE IF NOT EXISTS `mydb`.`tbl_coordinador_asignatura` (
  `coo_id` INT NOT NULL,
  PRIMARY KEY (`coo_id`),
  CONSTRAINT `fk_tbl_coordinador_asignatura_tbl_coordinador1`
    FOREIGN KEY (`coo_id`)
    REFERENCES `mydb`.`tbl_coordinador` (`coo_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`tbl_asignacion`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`tbl_asignacion` ;

CREATE TABLE IF NOT EXISTS `mydb`.`tbl_asignacion` (
  `prog_id` INT NOT NULL,
  `asig_id` INT NOT NULL,
  `coo_id` INT NOT NULL,
  PRIMARY KEY (`prog_id`, `asig_id`, `coo_id`),
  INDEX `fk_tbl_programa_has_tbl_asignatura_tbl_asignatura1_idx` (`asig_id` ASC) VISIBLE,
  INDEX `fk_tbl_programa_has_tbl_asignatura_tbl_programa1_idx` (`prog_id` ASC) VISIBLE,
  INDEX `fk_tbl_asignacion_tbl_coordinador_asignatura1_idx` (`coo_id` ASC) VISIBLE,
  CONSTRAINT `fk_tbl_programa_has_tbl_asignatura_tbl_programa1`
    FOREIGN KEY (`prog_id`)
    REFERENCES `mydb`.`tbl_programa` (`prog_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_tbl_programa_has_tbl_asignatura_tbl_asignatura1`
    FOREIGN KEY (`asig_id`)
    REFERENCES `mydb`.`tbl_asignatura` (`asig_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_tbl_asignacion_tbl_coordinador_asignatura1`
    FOREIGN KEY (`coo_id`)
    REFERENCES `mydb`.`tbl_coordinador_asignatura` (`coo_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`tbl_est_asignacion`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`tbl_est_asignacion` ;

CREATE TABLE IF NOT EXISTS `mydb`.`tbl_est_asignacion` (
  `pu_id` INT NOT NULL,
  `prog_id` INT NOT NULL,
  `asig_id` INT NOT NULL,
  `coo_id` INT NOT NULL,
  `est_asig_estado` ENUM('Registrado', 'No registrado') NULL,
  PRIMARY KEY (`pu_id`, `prog_id`, `asig_id`, `coo_id`),
  INDEX `fk_tbl_asignatura_has_tbl_estudiante_tbl_estudiante1_idx` (`pu_id` ASC) VISIBLE,
  INDEX `fk_tbl_matricula_tbl_asignacion1_idx` (`prog_id` ASC, `asig_id` ASC, `coo_id` ASC) VISIBLE,
  CONSTRAINT `fk_tbl_asignatura_has_tbl_estudiante_tbl_estudiante1`
    FOREIGN KEY (`pu_id`)
    REFERENCES `mydb`.`tbl_estudiante` (`pu_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_tbl_matricula_tbl_asignacion1`
    FOREIGN KEY (`prog_id` , `asig_id` , `coo_id`)
    REFERENCES `mydb`.`tbl_asignacion` (`prog_id` , `asig_id` , `coo_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`tbl_matricula`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`tbl_matricula` ;

CREATE TABLE IF NOT EXISTS `mydb`.`tbl_matricula` (
  `prog_id` INT NOT NULL,
  `pu_id` INT NOT NULL,
  `mat_estado` ENUM('Matriculado', 'No matriculado') NULL,
  PRIMARY KEY (`prog_id`, `pu_id`),
  INDEX `fk_tbl_programa_has_tbl_estudiante_tbl_estudiante1_idx` (`pu_id` ASC) VISIBLE,
  INDEX `fk_tbl_programa_has_tbl_estudiante_tbl_programa1_idx` (`prog_id` ASC) VISIBLE,
  CONSTRAINT `fk_tbl_programa_has_tbl_estudiante_tbl_programa1`
    FOREIGN KEY (`prog_id`)
    REFERENCES `mydb`.`tbl_programa` (`prog_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_tbl_programa_has_tbl_estudiante_tbl_estudiante1`
    FOREIGN KEY (`pu_id`)
    REFERENCES `mydb`.`tbl_estudiante` (`pu_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
