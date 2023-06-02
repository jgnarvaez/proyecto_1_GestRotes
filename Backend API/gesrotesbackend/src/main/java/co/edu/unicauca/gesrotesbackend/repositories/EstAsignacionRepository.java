package co.edu.unicauca.gesrotesbackend.repositories;

import co.edu.unicauca.gesrotesbackend.models.*;
import co.edu.unicauca.gesrotesbackend.services.DTO.EstudianteSeleccionadoDTO;
import jakarta.transaction.Transactional;

import java.util.List;

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
    EstAsignacion getRowByIds(@Param("estudianteId") int estudianteId, @Param("programaId") int programaId, @Param("asignaturaId") int asignaturaId, @Param("coordinadorId") int coordinadorId);

    /**
     *  Cambia el atributo est_asig_seleccionado de un registro en la tabla tbl_est_asignacion
     *  
     *  @param estado : estado seleccionado(true) o deseleccionado(false)
     *  @param estudianteId : id del estudiante asociado
     *  @param programaId : id del programa asociado
     *  @param asignaturaId : id de la asignatura asociada
     *  @param coordinadorId : id del coordinador asociado
     */
    @Transactional
    @Modifying
    @Query("UPDATE EstAsignacion ea " +
            "SET ea.seleccionado = :estado " +
            "WHERE ea.id.estudiante.id = :estudianteId " +
            "AND ea.id.asignacion.id.programa.id = :programaId " +
            "AND ea.id.asignacion.id.asignatura.id = :asignaturaId " +
            "AND ea.id.asignacion.id.coordinador.id = :coordinadorId ")
    void modifyStateStudent(@Param("estado") Boolean estado, @Param("estudianteId") int estudianteId, @Param("programaId") int programaId, 
                                @Param("asignaturaId") int asignaturaId, @Param("coordinadorId") int coordinadorId);

    /**
     *  Obtiene los registros de tbl_est_asignacion que tienen en 1 el atributo est_asig_seleccionado
     *  
     *  @param programaId : id del programa asociado
     *  @param asignaturaId : id de la asignatura asociada
     *  @param coordinadorId : id del coordinador asociado
     *  @return lista de objetos EstudianteSeleccionadoDTO
     */
    @Query("SELECT new co.edu.unicauca.gesrotesbackend.services.DTO.EstudianteSeleccionadoDTO(e.id, CONCAT(e.nombres, ' ', e.apellidos)) " +
            "FROM EstAsignacion ea " +
            "INNER JOIN Estudiante e ON ea.id.estudiante.id = e.id " +
            "WHERE ea.seleccionado = true " +
            "AND ea.id.asignacion.id.programa.id = :programaId " +
            "AND ea.id.asignacion.id.asignatura.id = :asignaturaId " +
            "AND ea.id.asignacion.id.coordinador.id = :coordinadorId")
    List<EstudianteSeleccionadoDTO> getSelectedStudents(@Param("programaId") int programaId, @Param("asignaturaId") int asignaturaId, 
                                                                @Param("coordinadorId") int coordinadorId);

    @Transactional
    @Modifying
    @Query("UPDATE EstAsignacion ea " +
            "SET ea.seleccionado = false " +
            "WHERE ea.id.asignacion.id.programa.id = :programaId " +
            "AND ea.id.asignacion.id.asignatura.id = :asignaturaId " +
            "AND ea.id.asignacion.id.coordinador.id = :coordinadorId ")
    void deselectStudents(@Param("programaId") int programaId, @Param("asignaturaId") int asignaturaId, @Param("coordinadorId") int coordinadorId);
}

