-- Consultas
-- listar estudiantes
SELECT pu.pu_id, pu.pu_nombres, pu.pu_identificacion, pu.pu_usuario
FROM mydb.tbl_persona_universitaria pu
INNER JOIN mydb.tbl_estudiante e ON pu.pu_id = e.pu_id
INNER JOIN mydb.tbl_est_asignacion ea ON e.pu_id = ea.pu_id
WHERE ea.asig_id = 8
  AND ea.prog_id = 1
  AND ea.coo_id = 1

-- Buscar por nombre, cuales no estan registrados
SELECT pu.pu_id, pu.pu_nombres, pu.pu_identificacion, pu.pu_correo
FROM tbl_persona_universitaria pu
INNER JOIN tbl_estudiante e ON pu.pu_id = e.pu_id
WHERE pu.pu_nombres LIKE '%Cr%'
AND e.pu_id NOT IN (
    SELECT ea.pu_id
    FROM tbl_est_asignacion ea
    WHERE ea.asig_id = 8
      AND ea.prog_id = 1
      AND ea.coo_id = 1
)

-- 
use mydb;
SELECT pu.pu_id AS id, pu.pu_nombres AS nombres, pu.pu_apellidos AS apellidos 
            FROM tbl_est_asignacion ea 
            INNER JOIN tbl_estudiante e ON ea.pu_id = e.pu_id 
            INNER JOIN tbl_persona_universitaria pu ON e.pu_id = pu.pu_id 
            WHERE ea.est_asig_seleccionado = 0 
            AND ea.prog_id = 1 
            AND ea.asig_id = 3 
            AND ea.coo_id = 1

--
use mydb;
SELECT pu_id, tur_fecha
FROM tbl_turno
GROUP BY pu_id, prog_id, asig_id, coo_id, tur_fecha
HAVING COUNT(*) > 0;
