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

-- jornada
INSERT INTO mydb.tbl_jornada (jor_franja, jor_hora_inicio, jor_hora_fin)
VALUES
('Mañana','08:00','12:00'),
('Mañana, tarde y noche','06:30','21:30'),
('Mañana','06:30','11:30'),
('Tarde y noche','14:00','21:00'),
('Mañana y tarde','06:30','14:30'),
('Mañana y tarde','11:30','21:30');

-- persona universitaria
INSERT INTO mydb.tbl_persona_universitaria (pu_nombres, pu_apellidos, pu_identificacion, pu_usuario, pu_foto_perfil)
VALUES
('Cristian','Gomez Santos',106813022001,'cgsantos','url foto'),
('Cristobal','Colon Lopez',106813022002,'cclopez','url foto'),
('Maria','Paz Bolaños',106813022003,'mpbolanos','url foto'),
('Juan Camilo','Sanchez',106813022004,'jcsacnchez','url foto'),
('Hector Estenam','Coral',106813022005,'hecoral','url foto'),
('Juan Sebastian','Perez',106813022006,'jsperez','url foto'),
('Paula Andrea','Velez',106813022007,'pavelez','url foto'),
('Natalia','Ortiz Bolaños',106813022008,'nbolanos','url foto');

-- estudiante
INSERT INTO mydb.tbl_estudiante (pu_id) VALUE (1);
INSERT INTO mydb.tbl_estudiante (pu_id) VALUE (2);
INSERT INTO mydb.tbl_estudiante (pu_id) VALUE (3);
INSERT INTO mydb.tbl_estudiante (pu_id) VALUE (4);
INSERT INTO mydb.tbl_estudiante (pu_id) VALUE (5);
INSERT INTO mydb.tbl_estudiante (pu_id) VALUE (6);
INSERT INTO mydb.tbl_estudiante (pu_id) VALUE (7);
INSERT INTO mydb.tbl_estudiante (pu_id) VALUE (8);

-- matricula
INSERT INTO mydb.tbl_matricula (prog_id, pu_id)
VALUES
(1,1),
(1,2),
(1,3),
(1,4),
(1,5),
(1,6),
(1,7),
(1,8);

-- asignacion
INSERT INTO mydb.tbl_asignacion (prog_id, asig_id, coo_id)
VALUES
(1,8,1),
(1,9,1),
(1,10,1);

-- est_asignacion
INSERT INTO mydb.tbl_est_asignacion (pu_id, prog_id, asig_id, coo_id)
VALUES
(3,1,8,1),
(4,1,8,1),
(5,1,9,1),
(6,1,9,1),
(7,1,10,1),
(8,1,10,1);

-- etiqueta
INSERT INTO mydb.tbl_etiqueta (eti_nombre, esc_id, ser_id)
VALUES
('Primer piso', 1, 1);