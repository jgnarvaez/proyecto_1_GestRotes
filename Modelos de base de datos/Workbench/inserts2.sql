-- programa
INSERT INTO mydb.tbl_programa (prog_nombre)
VALUES 
('Enfermería'),
('Fisioterapia'),
('Fonoaudiología'),
('Medicina');

-- coordinador
INSERT INTO mydb.tbl_coordinador (coo_nombres, coo_apellidos, coo_correo, coo_clave, coo_rol, coo_foto_perfil)
VALUES
('Jorge Ivan', 'Solano', 'jsolano', '1234', 'Coordinador_de_asignatura', 'url foto'),
('Maria Alejandra', 'Zapata', 'mzapata', '12345', 'Coordinador_de_asignatura', 'url foto'),
('Luis Carlos', 'Hernandez', 'lchernandez', '123456', 'Coordinador_de_asignatura', 'url foto');

-- coordinador de asignatura
INSERT INTO mydb.tbl_coordinador_asignatura (coo_id) 
VALUES 
(1),
(2),
(3);

-- asignatura
INSERT INTO mydb.tbl_asignatura (asig_nombre)
VALUES 
('Cuidado de Enfermería en salud comunitaria y familiar'), -- esta enfermeria
('Cuidado a las personas con Procesos infecciosos'), -- esta enfermeria
('Cuidado de Enfermería en Salud Mental'), -- esta enfermeria
('Cuidado de Enfermería al Adulto y al Anciano I'), -- esta enfermeria
('Cuidado de Enfermería al Adulto y al Anciano II'), -- esta enfermeria
('Fundamentos para el Cuidado de Enfermería'), -- esta enfermeria
('Cuidado al Niño y al Adolescente'), -- esta enfermeria
('Práctica en Fisioterapia Osteomuscular'), -- esta fisioterapia
('Práctica en Fisioterapia Neurológica'), -- esta fisioterapia
('Práctica en Fisioterapia Cardiopulmunar y Cuidado Crítico'); -- esta fisioterapia

-- asignacion
INSERT INTO mydb.tbl_asignacion (prog_id, asig_id, coo_id)
VALUES
(1, 1, 1),
(1, 2, 1),
(1, 3, 1),
(1, 4, 1),
(1, 5, 1),
(1, 6, 1),
(1, 7, 1),
(2, 8, 2),
(2, 9, 2),
(2, 10, 2);

-- escenario_practica
INSERT INTO mydb.tbl_escenario_practica (esc_nombre)
VALUES
('Hospital San José E.S.E'),
('Hospital Susana Lopez de Valencia'),
('Clínica la Estancia'),
('E.S.E Popayán'),
('Hospital del Norte'),
('Quilisalud'),
('Clinica Santa Gracia');

-- servicio
INSERT INTO mydb.tbl_servicio (ser_nombre)
VALUES 
('Cardiología'),
('Salud Mental'),
('Pediatría'),
('Urgencias');

-- persona universitaria
INSERT INTO mydb.tbl_persona_universitaria (pu_nombres, pu_apellidos, pu_identificacion, pu_usuario, pu_foto_perfil)
VALUES
('Cristian', 'Gomez Santos', 106813022001, 'cristiangomezsantos165', 'url foto'),
('Cristobal', 'Colon Lopez', 106813022002,'ccolonl', 'url foto'),
('Maria', 'Paz Bolaños', 106813022003, 'mpazb', 'url foto'),
('Juan Camilo', 'Sanchez', 106813022004, 'jcamilos', 'url foto'),
('Hector Esteban', 'Coral', 106813022005, 'hestebanc', 'url foto'),
('Juan Sebastian', 'Perez', 106813022006, 'jsebastianp', 'url foto'),
('Paula Andrea', 'Velez', 106813022007, 'pandreav', 'url foto'),
('Natalia', 'Ortiz Bolaños', 106813022008, 'nortizb', 'url foto'),
('Sandra Lorena', 'Ruiz', 106813022009, 'slorenar', 'url foto'),
('Angela', 'Gomez Lopez', 106813022010, 'agomezl', 'url foto'),
('Diana Andrea', 'Flores', 106813022011, 'dandreaf', 'url foto'),
('Pepito', 'Perez Ruiz', 106813022012, 'pperezr', 'url foto'),
('Maria Fernanda', 'Orozco', 106813022013, 'mfernandao', 'url foto'),
('Liseth', 'Perez Sanchez', 106813022014, 'lperezs', 'url foto'),
('Diego Andres', 'Diaz', 106813022015, 'dandresd', 'url foto'),
('Daniel', 'Flores Peña', 106813022016, 'dfloresp', 'url foto'),
('Juan David', 'Moreno', 106813022017, 'jdavidm', 'url foto'),
('Lina', 'Gomez Ramirez', 106813022018, 'lgomezr', 'url foto'),
('Pedro', 'Castro', 106813022019,'pcastro', 'url foto'),
('Valeria', 'Paez', 106813022020, 'vpa', 'url foto'),
('Mauricio', 'Cifuentes', 106813022021, 'mcifuentes', 'url foto'),
('Sofia', 'Castro Ramirez', 106813022022, 'scastrol', 'url foto'),
('Jorge', 'Hernandez', 106813022023, 'jhernandez', 'url foto'),
('Laura', 'Garcia Perez', 106813022024, 'lgarciap', 'url foto'),
('Mateo', 'Gonzalez', 106813022025, 'mgonzalez', 'url foto'),
('Ana Maria', 'Garcia Lopez', 106813022026, 'amgarcial', 'url foto'),
('Felipe', 'Gonzalez Ramirez', 106813022027, 'fgonzalezr', 'url foto'),
('Andres', 'Rojas', 106813022028, 'arojas', 'url foto'),
('Sara', 'Jimenez', 106813022029, 'sjimenez', 'url foto'),
('David', 'Rodriguez', 106813022030, 'drodriguez', 'url foto'),
('Isabella', 'Sanchez Gomez', 106813022031, 'isanchezg', 'url foto'),
('Manuela', 'Lopez', 106813022032, 'mlopez', 'url foto'),
('Catalina', 'Gomez Perez', 106813022033, 'cgomezp', 'url foto'),
('Carlos', 'Gonzalez Ramirez', 106813022034, 'cgonzalezr', 'url foto'),
('Fabiola', 'Gutierrez', 106813022035, 'fgutierrez', 'url foto'),
('Miguel', 'Rodriguez Sanchez', 106813022036, 'mrodriguezs', 'url foto'),
('Camila', 'Garcia Ramirez', 106813022037, 'cgarcial', 'url foto'),
('Daniel Mauricio', 'Cisneros Silva', 106813022038, 'dcisneros', 'url foto'),
('José', 'Gregorio', 106813022039, 'jgnarvaez', 'url foto');

-- estudiante
INSERT INTO mydb.tbl_estudiante (pu_id) 
VALUES 
(1),
(2),
(3),
(4),
(5),
(6),
(7),
(8),
(9),
(10),
(11),
(12),
(13),
(14),
(15),
(16),
(17),
(18),
(19),
(20),
(21),
(22),
(23),
(24),
(25),
(26),
(27),
(28),
(29),
(30),
(31),
(32),
(33),
(34),
(35),
(36),
(37),
(38),
(39);

-- matricula
INSERT INTO mydb.tbl_matricula (prog_id, pu_id)
VALUES
-- Enfermeria
(1, 1),
(1, 2),
(1, 3),
(1, 4),
(1, 5),
(1, 6),
(1, 7),
(1, 8),
(1, 9),
(1, 10),
(1, 11),
(1, 12),
(1, 13),
(1, 14),
(1, 15),
(1, 16),
(1, 17),
(1, 38),
(1, 39),
-- Fisioterapia
(2, 18),
(2, 19),
(2, 20),
(2, 21),
(2, 22),
(2, 23),
(2, 24),
(2, 25),
(2, 26),
(2, 27),
(2, 28),
(2, 29),
(2, 30),
-- Medicina
(4, 31),
(4, 32),
(4, 33),
(4, 34),
(4, 35),
(4, 36),
(4, 37);

-- est_asignacion
INSERT INTO mydb.tbl_est_asignacion (pu_id, prog_id, asig_id, coo_id, est_asig_seleccionado)
VALUES
-- Enfermeria
(3, 1, 3, 1, 1),
(4, 1, 3, 1, 1),
(5, 1, 4, 1, 1),
(6, 1, 4, 1, 1),
(7, 1, 1, 1, 1),
(8, 1, 1, 1, 1),
(9, 1, 3, 1, 1),
(10, 1, 3, 1, 1),
(11, 1, 4, 1, 1),
(12, 1, 4, 1, 1),
(13, 1, 1, 1, 1),
(14, 1, 1, 1, 1),
(15, 1, 1, 1, 1),
(16, 1, 1, 1, 1),
(17, 1, 1, 1, 1),
-- Fisioterapia
(22, 2, 8, 2, 1),
(23, 2, 8, 2, 1),
(24, 2, 8, 2, 1),
(25, 2, 9, 2, 1),
(26, 2, 9, 2, 1),
(27, 2, 9, 2, 1),
(28, 2, 10, 2, 1),
(29, 2, 10, 2, 1),
(30, 2, 10, 2, 1);

-- etiqueta
INSERT INTO mydb.tbl_etiqueta (eti_nombre, esc_id, ser_id)
VALUES
('Salas', 1, 3),
('Primer piso', 1, 1),
("Salas", 2, 2);

-- jornada
INSERT INTO mydb.tbl_jornada (jor_franja, jor_hora_inicio, jor_hora_fin)
VALUES
-- ('Mañana','08:00','12:00'),
-- ('Mañana, tarde y noche','06:30','21:30'),
-- ('Mañana','06:30','11:30'),
-- ('Tarde y noche','14:00','21:00'),
-- ('Mañana y tarde','06:30','14:30'),
-- ('Mañana y tarde','11:30','21:30');
('Mañana', '06:30', '11:30'), -- 1
('Mañana', '06:30', '12:00'), -- 2
('Mañana', '08:00', '12:00'), -- 3
('Mañana', '11:30', '12:00'), -- 4
('Tarde', '12:00', '18:00'), -- 5
('Tarde', '12:00', '14:30'), -- 6
('Tarde', '14:00', '18:00'), -- 7
('Noche', '18:00', '21:00'), -- 8
('Noche', '18:00', '21:30'); -- 9

-- turno
INSERT INTO mydb.tbl_turno(tur_fecha, pu_id, prog_id, asig_id, coo_id, jor_id, eti_id, tur_alimentacion)
VALUES
-- estudiante 1: 06:30 a 14:30
-- ('2023-05-12', 3, 1, 3, 1, 2, 2, 'Desayuno'), -- 6:30 a 12 
-- ('2023-05-12', 3, 1, 3, 1, 6, 2, 'Almuerzo'), -- 12 a 14:30
-- estudiante 2: 06:30 a 11:30 y 14:00 a 21:00
('2023-06-13', 4, 1, 3, 1, 1, 1, 'Desayuno'), -- 6:30 a 11:30
('2023-06-13', 4, 1, 3, 1, 7, 1, null), -- 14:00 a 18:00
('2023-06-13', 4, 1, 3, 1, 8, 1, 'Comida'), -- 18:00 a 21:00
-- estudiante 3: 11:30 a 21:30
('2023-06-13', 5, 1, 4, 1, 4, 1, 'Desayuno'), -- 11:30 a 12:00
('2023-06-13', 5, 1, 4, 1, 5, 1, 'Almuerzo'), -- 12:00 a 18:00
('2023-06-13', 5, 1, 4, 1, 9, 1, 'Comida'); -- 18:00 a 21:30

-- VALIDACIONES
-- Un estudiante no puede tener un turno distinto en la misma fecha
-- INSERT INTO mydb.tbl_est_asignacion (pu_id, prog_id, asig_id, coo_id, est_asig_seleccionado)
-- VALUE
-- (4, 1, 1, 1, 1);
-- INSERT INTO mydb.tbl_turno(tur_fecha, pu_id, prog_id, asig_id, coo_id, jor_id, eti_id, tur_alimentacion)
-- VALUE
-- ('2023-05-12', 4, 1, 1, 1, 1, 2, 'Desayuno');
-- Un estudiante no puede tener registrado una misma jornada en una misma fecha
-- INSERT INTO mydb.tbl_turno(tur_fecha, pu_id, prog_id, asig_id, coo_id, jor_id, eti_id, tur_alimentacion)
-- VALUE
-- ('2023-05-12', 4, 1, 3, 1, 1, 2, 'Desayuno');

INSERT INTO mydb.tbl_validacion_turnos (pu_id, prog_id, asig_id, coo_id)
VALUES
-- Cuidado de Enfermería en salud comunitaria y familiar
(7, 1, 1, 1),
(8, 1, 1, 1),
(13, 1, 1, 1),
(14, 1, 1, 1),
(15, 1, 1, 1),
(16, 1, 1, 1),
(17, 1, 1, 1),
-- Cuidado de Enfermería en Salud Mental
(3, 1, 3, 1),
(4, 1, 3, 1),
(9, 1, 3, 1),
(10, 1, 3, 1),
-- Cuidado de Enfermería al Adulto y al Anciano I
(5, 1, 4, 1),
(6, 1, 4, 1),
(11, 1, 4, 1),
(12, 1, 4, 1);
