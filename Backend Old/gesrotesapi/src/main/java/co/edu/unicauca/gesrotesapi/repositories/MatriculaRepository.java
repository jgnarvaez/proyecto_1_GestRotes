package co.edu.unicauca.gesrotesapi.repositories;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import co.edu.unicauca.gesrotesapi.models.AsignaturaEntity;
import co.edu.unicauca.gesrotesapi.models.EstudianteEntity;
import co.edu.unicauca.gesrotesapi.models.MatriculaEntity;

@Repository
public class MatriculaRepository {
    
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private AsignaturaRepository asignaturaRepository;

    public List<EstudianteEntity> findStudentsInSubject(int id_asignatura) {
        List<EstudianteEntity> estudiantesMatriculados = new ArrayList<>();
        System.out.println("Invocando a listar todos los estudiantes registrados en una asignatura");
    
        String sql = "SELECT tbl_estudiante.pu_id, pu_nombres, pu_apellidos, pu_identificacion " +
                    "FROM tbl_persona_universitaria " +
                    "INNER JOIN tbl_estudiante ON tbl_persona_universitaria.pu_id = tbl_estudiante.pu_id " +
                    "INNER JOIN tbl_matricula ON tbl_estudiante.pu_id = tbl_matricula.pu_id " +
                    "WHERE tbl_matricula.asig_id = ?";
    
        List<Map<String, Object>> rows = jdbcTemplate.queryForList(sql, id_asignatura);
    
        for (Map<String, Object> row : rows) {
            EstudianteEntity estudiante = new EstudianteEntity();
            estudiante.setPu_id((Integer) row.get("pu_id"));
            estudiante.setPu_nombres((String) row.get("pu_nombres"));
            estudiante.setPu_apellidos((String) row.get("pu_apellidos"));
            estudiante.setPu_identificacion((Long) row.get("pu_identificacion"));
            estudiantesMatriculados.add(estudiante);
        }
    
        return estudiantesMatriculados;
    }

    //registrar matricula
    public MatriculaEntity registerStudentInSubject(int id_estudiante, int id_asignatura){
        System.out.println("Invocando a registrar matricula");
        AsignaturaEntity asignatura = asignaturaRepository.findByID(id_asignatura);
        EstudianteEntity estudiante = findStudentById(id_estudiante);
        MatriculaEntity matricula = new MatriculaEntity();
        matricula.setAsignatura(asignatura);
        matricula.setEstudiante(estudiante);
        matricula.setEstado(true);

        String sql = "INSERT INTO tbl_matricula (pu_id, asig_id, mat_estado) VALUES (?, ?, ?)";
        int rowsAffected = jdbcTemplate.update(sql, id_estudiante, id_asignatura, 1);
        if(rowsAffected > 0){
            return matricula;
        }else{
            return null;
        }
    }
    
    //eliminar todos los estudiantes registrados a una asignatura
    public boolean deleteAllStudentsFromSubject(int id_asignatura) {
        String sql = "DELETE FROM tbl_matricula WHERE asig_id = ?";
        int numRowsAffected = jdbcTemplate.update(sql, id_asignatura);
        if(numRowsAffected > 0){
            return true;
        }else{
            return false;
        }
    }
    
    //eliminar un estudiante en especifico de una asignatura
    public boolean removeStudentFromSubject(int id_estudiante, int id_asignatura) {
        String sql = "DELETE FROM tbl_matricula WHERE pu_id = ? AND asig_id = ?";
        int affectedRows = jdbcTemplate.update(sql, id_estudiante, id_asignatura);
        if(affectedRows > 0){
            return true;
        }else{
            return false;
        }
    }
    
    //buscar un estudiante por id
    public EstudianteEntity findStudentById(int id) {
        String sql = "SELECT * FROM tbl_estudiante WHERE pu_id = ?";
        EstudianteEntity estudiante = jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(EstudianteEntity.class), id);
        return estudiante;
    }
    
}
