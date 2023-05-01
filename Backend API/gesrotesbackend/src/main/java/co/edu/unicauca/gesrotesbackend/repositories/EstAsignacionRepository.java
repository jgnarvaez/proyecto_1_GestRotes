package co.edu.unicauca.gesrotesbackend.repositories;

import co.edu.unicauca.gesrotesbackend.models.*;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface EstAsignacionRepository extends JpaRepository<EstAsignacion, EstAsignacionId> {

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM tbl_est_asignacion " +
            "WHERE prog_id = :programId " +
            "AND asig_id = :subjectId " +
            "AND coo_id = :coordinatorId", nativeQuery = true)
    void deleteAllStudents(@Param("programId") int progId, @Param("subjectId") int asigId, @Param("coordinatorId") int cooId);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM tbl_est_asignacion " +
            "WHERE pu_id = :studentId " +
            "AND prog_id = :programId " +
            "AND asig_id = :subjectId " +
            "AND coo_id = :coordinatorId", nativeQuery = true)
    void deleteStudent(@Param("programId") int progId, @Param("subjectId") int asigId, @Param("coordinatorId") int cooId,@Param("studentId") int studId);

}

