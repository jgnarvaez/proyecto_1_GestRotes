package co.edu.unicauca.gesrotesbackend.repositories;

import co.edu.unicauca.gesrotesbackend.models.Estudiante;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface EstudianteRepository extends JpaRepository<Estudiante, Integer> {
    /**
     *  Devuelve una lista de objetos Estudiante que contienen información sobre los 
     *  estudiantes asignados al programa, asignatura y coordinador dados.
     *  
     *  @param asigId : el ID de la asignatura.
     *  @param progId : el ID del programa.
     *  @param cooId : el ID del coordinador de asignatura.
     *  @return una lista de objetos Estudiante
     */
    @Query(value = "SELECT * " +
            "FROM tbl_persona_universitaria pu " +
            "INNER JOIN tbl_estudiante e ON pu.pu_id = e.pu_id " +
            "INNER JOIN tbl_est_asignacion ea ON e.pu_id = ea.pu_id " +
            "WHERE ea.asig_id = :asigId AND ea.prog_id = :progId AND ea.coo_id = :cooId", nativeQuery = true)
    List<Estudiante> getStudentInfo(@Param("asigId") int asigId, @Param("progId") int progId, @Param("cooId") int cooId);

    /**
     *  Devuelve una lista de objetos Estudiante que contienen información sobre los estudiantes 
     *  cuyos nombres contienen la cadena de búsqueda dada y que aún no han sido registrados en 
     *  una asignatura determinada.
     *  
     *  @param cadenaBusqueda : la cadena de busqueda que deben tener los nombres o apellidos de los estudiantes.
     *  @param asigId : el ID de la asignatura.
     *  @param progId : el ID del programa.
     *  @param cooId : el ID del coordinador de asignatura.
     *  @return una lista de objetos Estudiante
     */
    @Query(value = "SELECT * " +
            "FROM tbl_persona_universitaria pu " +
            "INNER JOIN tbl_estudiante e ON pu.pu_id = e.pu_id " +
            "WHERE (pu.pu_nombres LIKE %:cadenaBusqueda% OR pu.pu_apellidos LIKE %:cadenaBusqueda%) " +
            "AND e.pu_id NOT IN (" +
            "    SELECT ea.pu_id " +
            "    FROM tbl_est_asignacion ea " +
            "    WHERE ea.asig_id = :asigId " +
            "      AND ea.prog_id = :progId " +
            "      AND ea.coo_id = :cooId" +
            ")", nativeQuery = true)
    List<Estudiante> getStudentsByName(@Param("cadenaBusqueda") String cadenaBusqueda, @Param("asigId") int asigId, @Param("progId") int progId, @Param("cooId") int cooId);


}
