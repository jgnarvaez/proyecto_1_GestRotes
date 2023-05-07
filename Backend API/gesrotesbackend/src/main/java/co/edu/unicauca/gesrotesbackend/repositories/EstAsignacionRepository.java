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

    /**
     *  Obtiene un registro de la tabla "tbl_est_asignacion" dados unos ID's
     *  
     *  @param estudianteId el ID del estudiante asociado.
     *  @param programaId el ID del programa asociada.
     *  @param asignaturaId el ID del asignatura de asignatura asociado.
     *  @param coordinadorId el ID del coordinador asociado.
     *  @return un objeto EstAsignacion que representa el registro obtenido de la tabla "tbl_est_asignacion"
    */
    @Query("SELECT ea " +
            "FROM EstAsignacion ea " +
            "WHERE ea.id.estudiante.id = :estudianteId " +
            "AND ea.id.asignacion.id.programa.id = :programaId " +
            "AND ea.id.asignacion.id.asignatura.id = :asignaturaId " +
            "AND ea.id.asignacion.id.coordinador.id = :coordinadorId")
    EstAsignacion obtenerPorIds(@Param("estudianteId") int estudianteId, @Param("programaId") int programaId, @Param("asignaturaId") int asignaturaId, @Param("coordinadorId") int coordinadorId);

    /**
     *  Elimina los registros asociados en la tabla "tbl_turno_est_asig" para poder usar el método deleteAllStudents
     *  @param programaId el ID del programa asociado.
     *  @param asignaturaId el ID de la asignatura asociada.
     *  @param coordinadorId el ID del coordinador de asignatura asociado.
    */
    @Transactional
    @Modifying
    @Query("DELETE FROM TurEstAsignacion tea " +
            "WHERE tea.id.estAsignacion.id.asignacion.id.coordinador.id = :coo_id " +
            "AND tea.id.estAsignacion.id.asignacion.id.asignatura.id = :asig_id " +
            "AND tea.id.estAsignacion.id.asignacion.id.programa.id = :prog_id")
    void eliminarRegistrosTurEstAsignacion(@Param("prog_id") int programaId, @Param("asig_id") int asignaturaId, @Param("coo_id") int coordinadorId);

    /**
     *  Elimina los registros asociados en la tabla "tbl_turno_est_asig" para poder usar el método deleteStudent
     *  @param estudianteId el ID del estudiante asociado.
     *  @param programaId el ID del programa asociado.
     *  @param asignaturaId el ID de la asignatura asociada.
     *  @param coordinadorId el ID del coordinador de asignatura asociado.
    */
    @Transactional
    @Modifying
    @Query("DELETE FROM TurEstAsignacion tea " +
            "WHERE tea.id.estAsignacion.id.estudiante.id = :est_id " +
            "AND tea.id.estAsignacion.id.asignacion.id.coordinador.id = :coo_id " +
            "AND tea.id.estAsignacion.id.asignacion.id.asignatura.id = :asig_id " +
            "AND tea.id.estAsignacion.id.asignacion.id.programa.id = :prog_id")
    void eliminarRegistrosDeEstudianteEnTurEstAsignacion(@Param("est_id") int estudianteId, @Param("prog_id") int programaId, @Param("asig_id") int asignaturaId, @Param("coo_id") int coordinadorId);

    //     @Query("DELETE FROM EstAsignacion ea " +
    //             "WHERE ea.coordinador.id = :coo_id " +
    //             "AND ea.asignatura.id = :asig_id " +
    //             "AND ea.programa.id = :prog_id")
    //     int eliminarRegistrosEstAsignacion(@Param("prog_id") int programaId, @Param("asig_id") int asignaturaId, @Param("coo_id") int coordinadorId);

}

