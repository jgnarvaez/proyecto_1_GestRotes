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
('Jorge Ivan', 'Solano', 'jsolano@unicauca.edu.co', '1234', 'Coordinador de asignatura', 'url foto');

-- coordinador de asignatura
INSERT INTO mydb.tbl_coordinador_asignatura (coo_id) VALUE (1);

-- asignatura
INSERT INTO mydb.tbl_asignatura (asig_nombre, coo_id, prog_id)
VALUES 
('Cuidado de Enfermería y Salud Pública', 1, 1),
('Psicología General', 1, 1),
('Cuidado de Enfermería en salud comunitaria y familiar', 1, 1),
('Morfología', 1, 1),
('Microbiología', 1, 1),
('Cuidado a las personas con Procesos infecciosos', 1, 1),
('Farmacología', 1, 1),
('Cuidado de Enfermería en Salud Mental', 1, 1),
('Cuidado de Enfermería al Adulto y al Anciano I', 1, 1),
('Enfermería en salud nutricional', 1, 1),
('Cuidado al Niño y al Adolescente', 1, 1);

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
('Pediatría');

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
INSERT INTO mydb.tbl_persona_universitaria (pu_nombres, pu_apellidos, pu_identificacion, pu_correo, pu_foto_perfil, prog_id)
VALUES
('Cristian','Gomez Santos',10611,'cgsantos@unicauca.edu.co','url foto',1);

-- estudiante
INSERT INTO mydb.tbl_estudiante (pu_id) VALUE (1);


