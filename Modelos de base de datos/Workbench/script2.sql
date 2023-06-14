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
  `pu_nombres` VARCHAR(20) NOT NULL,
  `pu_apellidos` VARCHAR(20) NOT NULL,
  `pu_identificacion` BIGINT(15) NOT NULL,
  `pu_usuario` VARCHAR(45) NOT NULL,
  `pu_foto_perfil` VARCHAR(150) NULL,
  PRIMARY KEY (`pu_id`),
  UNIQUE INDEX `pu_id_UNIQUE` (`pu_id` ASC) VISIBLE,
  UNIQUE INDEX `pu_identificacion_UNIQUE` (`pu_identificacion` ASC) VISIBLE,
  UNIQUE INDEX `nombre_completo` (`pu_nombres` ASC, `pu_apellidos` ASC) VISIBLE)
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
  `coo_nombres` VARCHAR(50) NOT NULL,
  `coo_apellidos` VARCHAR(50) NOT NULL,
  `coo_correo` VARCHAR(45) NOT NULL,
  `coo_clave` VARCHAR(20) NOT NULL,
  `coo_rol` ENUM('Coordinador_de_asignatura', 'Coordinador_de_programa') NOT NULL,
  `coo_foto_perfil` VARCHAR(150) NULL,
  PRIMARY KEY (`coo_id`),
  UNIQUE INDEX `coo_id_UNIQUE` (`coo_id` ASC) VISIBLE,
  UNIQUE INDEX `nombre_completo` (`coo_nombres` ASC, `coo_apellidos` ASC) VISIBLE,
  UNIQUE INDEX `coo_correo_UNIQUE` (`coo_correo` ASC) VISIBLE,
  UNIQUE INDEX `coo_clave_UNIQUE` (`coo_clave` ASC) VISIBLE)
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
  `asig_nombre` VARCHAR(100) NOT NULL,
  PRIMARY KEY (`asig_id`),
  UNIQUE INDEX `asig_id_UNIQUE` (`asig_id` ASC) VISIBLE,
  UNIQUE INDEX `asig_nombre_UNIQUE` (`asig_nombre` ASC) VISIBLE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`tbl_escenario_practica`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`tbl_escenario_practica` ;

CREATE TABLE IF NOT EXISTS `mydb`.`tbl_escenario_practica` (
  `esc_id` INT NOT NULL AUTO_INCREMENT,
  `esc_nombre` VARCHAR(100) NOT NULL,
  PRIMARY KEY (`esc_id`),
  UNIQUE INDEX `esc_id_UNIQUE` (`esc_id` ASC) VISIBLE,
  UNIQUE INDEX `esc_nombre_UNIQUE` (`esc_nombre` ASC) VISIBLE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`tbl_jornada`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`tbl_jornada` ;

CREATE TABLE IF NOT EXISTS `mydb`.`tbl_jornada` (
  `jor_id` INT NOT NULL AUTO_INCREMENT,
  `jor_franja` ENUM('Mañana', 'Tarde', 'Noche') NOT NULL,
  `jor_hora_inicio` TIME NOT NULL,
  `jor_hora_fin` TIME NOT NULL,
  PRIMARY KEY (`jor_id`),
  UNIQUE INDEX `jor_id_UNIQUE` (`jor_id` ASC) VISIBLE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`tbl_servicio`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`tbl_servicio` ;

CREATE TABLE IF NOT EXISTS `mydb`.`tbl_servicio` (
  `ser_id` INT NOT NULL AUTO_INCREMENT,
  `ser_nombre` VARCHAR(30) NOT NULL,
  PRIMARY KEY (`ser_id`),
  UNIQUE INDEX `ser_id_UNIQUE` (`ser_id` ASC) VISIBLE,
  UNIQUE INDEX `ser_nombre_UNIQUE` (`ser_nombre` ASC) VISIBLE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`tbl_etiqueta`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`tbl_etiqueta` ;

CREATE TABLE IF NOT EXISTS `mydb`.`tbl_etiqueta` (
  `eti_id` INT NOT NULL AUTO_INCREMENT,
  `eti_nombre` VARCHAR(30) NOT NULL,
  `esc_id` INT NOT NULL,
  `ser_id` INT NULL,
  PRIMARY KEY (`eti_id`),
  UNIQUE INDEX `eti_id_UNIQUE` (`eti_id` ASC) VISIBLE,
  INDEX `fk_tbl_etiqueta_tbl_escenario_practica1_idx` (`esc_id` ASC) VISIBLE,
  INDEX `fk_tbl_etiqueta_tbl_servicio1_idx` (`ser_id` ASC) VISIBLE,
  UNIQUE INDEX `nombre_y_escenario` (`eti_nombre` ASC, `esc_id` ASC) VISIBLE,
  CONSTRAINT `fk_tbl_etiqueta_tbl_escenario_practica`
    FOREIGN KEY (`esc_id`)
    REFERENCES `mydb`.`tbl_escenario_practica` (`esc_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_tbl_etiqueta_tbl_servicio`
    FOREIGN KEY (`ser_id`)
    REFERENCES `mydb`.`tbl_servicio` (`ser_id`)
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
  `est_asig_seleccionado` TINYINT NULL,
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
-- Table `mydb`.`tbl_turno`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`tbl_turno` ;

CREATE TABLE IF NOT EXISTS `mydb`.`tbl_turno` (
  `tur_id` INT NOT NULL AUTO_INCREMENT,
  `tur_fecha` DATE NOT NULL,
  `pu_id` INT NOT NULL,
  `prog_id` INT NOT NULL,
  `asig_id` INT NOT NULL,
  `coo_id` INT NOT NULL,
  `jor_id` INT NOT NULL,
  `eti_id` INT NOT NULL,
  `tur_alimentacion` ENUM('Desayuno', 'Almuerzo', 'Comida') NULL,
  PRIMARY KEY (`tur_id`, `pu_id`, `prog_id`, `asig_id`, `coo_id`),
  UNIQUE INDEX `tur_id_UNIQUE` (`tur_id` ASC) VISIBLE,
  INDEX `fk_tbl_turno_tbl_jornada1_idx` (`jor_id` ASC) VISIBLE,
  INDEX `fk_tbl_turno_tbl_etiqueta1_idx` (`eti_id` ASC) VISIBLE,
  INDEX `fk_tbl_turno_tbl_est_asignacion1_idx` (`pu_id` ASC, `prog_id` ASC, `asig_id` ASC, `coo_id` ASC) VISIBLE,
  UNIQUE INDEX `fecha_unica` (`tur_fecha` ASC, `pu_id` ASC, `prog_id` ASC, `asig_id` ASC, `coo_id` ASC, `jor_id` ASC) INVISIBLE,
  CONSTRAINT `fk_tbl_turno_tbl_jornada1`
    FOREIGN KEY (`jor_id`)
    REFERENCES `mydb`.`tbl_jornada` (`jor_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_tbl_turno_tbl_etiqueta1`
    FOREIGN KEY (`eti_id`)
    REFERENCES `mydb`.`tbl_etiqueta` (`eti_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_tbl_turno_tbl_est_asignacion1`
    FOREIGN KEY (`pu_id` , `prog_id` , `asig_id` , `coo_id`)
    REFERENCES `mydb`.`tbl_est_asignacion` (`pu_id` , `prog_id` , `asig_id` , `coo_id`)
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


-- -----------------------------------------------------
-- Table `mydb`.`tbl_validacion_turnos`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`tbl_validacion_turnos` ;

CREATE TABLE IF NOT EXISTS `mydb`.`tbl_validacion_turnos` (
  `vtu_id` INT NOT NULL AUTO_INCREMENT,
  `vtu_mes` ENUM('Enero', 'Febrero', 'Marzo', 'Abril', 'Mayo', 'Junio', 'Julio', 'Agosto', 'Septiembre', 'Octubre', 'Noviembre', 'Diciembre') NULL,
  `vtu_anio` YEAR(4) NULL,
  `vtu_asistencia` TINYINT NULL,
  `vtu_estado` TINYINT NULL,
  `vtu_observaciones` VARCHAR(300) NULL,
  `pu_id` INT NOT NULL,
  `prog_id` INT NOT NULL,
  `asig_id` INT NOT NULL,
  `coo_id` INT NOT NULL,
  PRIMARY KEY (`vtu_id`, `pu_id`, `prog_id`, `asig_id`, `coo_id`),
  INDEX `fk_tbl_validacion_turnos_tbl_est_asignacion1_idx` (`pu_id` ASC, `prog_id` ASC, `asig_id` ASC, `coo_id` ASC) VISIBLE,
  UNIQUE INDEX `vtu_id_UNIQUE` (`vtu_id` ASC) VISIBLE,
  UNIQUE INDEX `no_repetido` (`vtu_mes` ASC, `vtu_anio` ASC, `pu_id` ASC, `prog_id` ASC, `asig_id` ASC, `coo_id` ASC) VISIBLE,
  CONSTRAINT `fk_tbl_validacion_turnos_tbl_est_asignacion1`
    FOREIGN KEY (`pu_id` , `prog_id` , `asig_id` , `coo_id`)
    REFERENCES `mydb`.`tbl_est_asignacion` (`pu_id` , `prog_id` , `asig_id` , `coo_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

USE `mydb`;

DELIMITER $$

USE `mydb`$$
DROP TRIGGER IF EXISTS `mydb`.`tbl_escenario_practica_BEFORE_DELETE` $$
USE `mydb`$$
CREATE DEFINER = CURRENT_USER TRIGGER `mydb`.`tbl_escenario_practica_BEFORE_DELETE` BEFORE DELETE ON `tbl_escenario_practica` FOR EACH ROW
BEGIN
	DELETE FROM tbl_etiqueta WHERE esc_id = OLD.esc_id;
END$$


USE `mydb`$$
DROP TRIGGER IF EXISTS `mydb`.`tbl_est_asignacion_AFTER_UPDATE` $$
USE `mydb`$$
CREATE DEFINER = CURRENT_USER TRIGGER `mydb`.`tbl_est_asignacion_AFTER_UPDATE` AFTER UPDATE ON `tbl_est_asignacion` FOR EACH ROW
BEGIN
	IF NEW.est_asig_seleccionado = 0 AND OLD.est_asig_seleccionado = 1 THEN
        DELETE FROM tbl_turno WHERE pu_id = NEW.pu_id AND prog_id = NEW.prog_id AND asig_id = NEW.asig_id AND coo_id = NEW.coo_id;
    END IF;
END$$


USE `mydb`$$
DROP TRIGGER IF EXISTS `mydb`.`tbl_est_asignacion_BEFORE_DELETE` $$
USE `mydb`$$
CREATE DEFINER = CURRENT_USER TRIGGER `mydb`.`tbl_est_asignacion_BEFORE_DELETE` BEFORE DELETE ON `tbl_est_asignacion` FOR EACH ROW
BEGIN
	DELETE FROM tbl_turno WHERE pu_id = OLD.pu_id AND prog_id = OLD.prog_id AND asig_id = OLD.asig_id AND coo_id = OLD.coo_id;
END$$


USE `mydb`$$
DROP TRIGGER IF EXISTS `mydb`.`tbl_turno_BEFORE_INSERT` $$
USE `mydb`$$
CREATE DEFINER = CURRENT_USER TRIGGER `mydb`.`tbl_turno_BEFORE_INSERT` BEFORE INSERT ON `tbl_turno` FOR EACH ROW
BEGIN
  DECLARE existing_row_count INT;

  -- Verificar si ya existe una fila con la misma tur_fecha y pu_id pero distintos prog_id, asig_id y coo_id
  SELECT COUNT(*) INTO existing_row_count
  FROM mydb.tbl_turno
  WHERE tur_fecha = NEW.tur_fecha
    AND pu_id = NEW.pu_id
    AND (prog_id <> NEW.prog_id OR asig_id <> NEW.asig_id OR coo_id <> NEW.coo_id);

  -- Si existe una fila con los criterios mencionados, lanzar un error
  IF existing_row_count > 0 THEN
    SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Ya existe un turno asignado en esa fecha.';
  END IF;
END$$


DELIMITER ;

SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
