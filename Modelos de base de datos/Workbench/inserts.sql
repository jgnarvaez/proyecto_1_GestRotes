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
('Jorge Ivan', 'Solano', 'jsolano', '1234', 'Coordinador_de_asignatura', 'url foto');

-- coordinador de asignatura
INSERT INTO mydb.tbl_coordinador_asignatura (coo_id) VALUE (1);

-- asignatura
INSERT INTO mydb.tbl_asignatura (asig_nombre)
VALUES 
('Cuidado de Enfermería y Salud Pública'),
('Psicología General'),
('Cuidado de Enfermería en salud comunitaria y familiar'),
('Morfología'),
('Microbiología'),
('Cuidado a las personas con Procesos infecciosos'),
('Farmacología'),
('Cuidado de Enfermería en Salud Mental'),
('Cuidado de Enfermería al Adulto y al Anciano I'),
('Enfermería en salud nutricional'),
('Cuidado al Niño y al Adolescente');

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
('Cristian', 'Gomez Santos', 106813022001, 'cgomezs', 'url foto'),
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
('Juan David', 'Moreno', 106813022017, 'jdavidm', 'url foto');

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
(17);

-- matricula
INSERT INTO mydb.tbl_matricula (prog_id, pu_id)
VALUES
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
(1, 17);

-- asignacion
INSERT INTO mydb.tbl_asignacion (prog_id, asig_id, coo_id)
VALUES
(1, 8, 1),
(1, 9, 1),
(1, 10, 1);

-- est_asignacion
INSERT INTO mydb.tbl_est_asignacion (pu_id, prog_id, asig_id, coo_id)
VALUES
(3, 1, 8, 1),
(4, 1, 8, 1),
(5, 1, 9, 1),
(6, 1, 9, 1),
(7, 1, 10 ,1),
(8, 1, 10 ,1),
(9, 1, 8, 1),
(10, 1, 8, 1),
(11, 1, 9, 1),
(12, 1, 9, 1),
(13, 1, 10 ,1),
(14, 1, 10 ,1),
(15, 1, 10 ,1),
(16, 1, 10 ,1),
(17, 1, 10 ,1);

-- etiqueta
INSERT INTO mydb.tbl_etiqueta (eti_nombre, esc_id, ser_id)
VALUES
('Salas', 1, 3);
('Primer piso', 1, 1);

-- jornada
INSERT INTO mydb.tbl_jornada (jor_franja, jor_hora_inicio, jor_hora_fin)
VALUES
-- ('Mañana','08:00','12:00'),
-- ('Mañana, tarde y noche','06:30','21:30'),
-- ('Mañana','06:30','11:30'),
-- ('Tarde y noche','14:00','21:00'),
-- ('Mañana y tarde','06:30','14:30'),
-- ('Mañana y tarde','11:30','21:30');
('Mañana', '06:30', '11:30'),
('Mañana', '06:30', '12:00'),
('Mañana', '08:00', '12:00'),
('Mañana', '11:30', '12:00'),
('Tarde', '12:00', '18:00'),
('Tarde', '12:00', '14:30'),
('Tarde', '14:00', '18:00'),
('Noche', '18:00', '21:00'),
('Noche', '18:00', '21:30');

-- turno
INSERT INTO mydb.tbl_turno (tur_fecha, jor_id, eti_id)
VALUES
('2023-05-12', 2, 1);

-- turno_est_asig
INSERT INTO mydb.tbl_turno_est_asig (pu_id, prog_id, asig_id, coo_id, fecha, jor_id, eti_id)
VALUES
-- estudiante 1: 06:30 a 14:30
(3, 1, 8, 1, '2023-05-12', 2, 2), -- 6:30 a 12 
(3, 1, 8, 1, '2023-05-12', 6, 2), -- 12 a 14:30
-- estudiante 2: 06:30 a 11:30 y 14:00 a 21:00
(4, 1, 8, 1, '2023-05-12', 1, 1), -- 6:30 a 11:30
(4, 1, 8, 1, '2023-05-12', 7, 1), -- 14:00 a 18:00
(4, 1, 8, 1, '2023-05-12', 8, 1), -- 18:00 a 21:00
-- estudiante 3: 11:30 a 21:30
(5, 1, 9, 1, '2023-05-12', ), -- 11:00 a 12
(5, 1, 9, 1, '2023-05-12', ),
(5, 1, 9, 1, '2023-05-12', );
