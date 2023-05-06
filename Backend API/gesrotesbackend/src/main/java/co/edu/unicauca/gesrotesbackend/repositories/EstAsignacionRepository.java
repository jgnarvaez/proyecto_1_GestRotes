package co.edu.unicauca.gesrotesbackend.repositories;

import co.edu.unicauca.gesrotesbackend.models.*;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface EstAsignacionRepository extends JpaRepository<EstAsignacion, EstAsignacionId> {

    /**
     *  Elimina los registros de la tabla "tbl_est_asignacion" que tengan una asignación en común.
     *  @param progId el ID del programa asociado.
     *  @param asigId el ID de la asignatura asociada.
     *  @param cooId el ID del coordinador de asignatura asociado.
    */
    @Modifying
    @Transactional
    @Query(value = "DELETE FROM tbl_est_asignacion " +
            "WHERE prog_id = :programId " +
            "AND asig_id = :subjectId " +
            "AND coo_id = :coordinatorId", nativeQuery = true)
    void deleteAllStudents(@Param("programId") int progId, @Param("subjectId") int asigId, @Param("coordinatorId") int cooId);

    /**
     *  Elimina un registro de la tabla "tbl_est_asignacion"
     *  @param progId el ID del programa asociado.
     *  @param asigId el ID de la asignatura asociada.
     *  @param cooId el ID del coordinador de asignatura asociado.
     *  @param studId el ID del estudiante asociado.
    */
    @Modifying
    @Transactional
    @Query(value = "DELETE FROM tbl_est_asignacion " +
            "WHERE pu_id = :studentId " +
            "AND prog_id = :programId " +
            "AND asig_id = :subjectId " +
            "AND coo_id = :coordinatorId", nativeQuery = true)
    void deleteStudent(@Param("programId") int progId, @Param("subjectId") int asigId, @Param("coordinatorId") int cooId,@Param("studentId") int studId);

}

