package co.edu.unicauca.gesrotesbackend.repositories;

import java.sql.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import co.edu.unicauca.gesrotesbackend.models.Turno;
import co.edu.unicauca.gesrotesbackend.models.TurnoId;
import co.edu.unicauca.gesrotesbackend.services.DTO.EmailDTO;
import co.edu.unicauca.gesrotesbackend.services.DTO.EstudianteFechaDTO;
import co.edu.unicauca.gesrotesbackend.services.DTO.TurnoAsociadoDTO;
import jakarta.transaction.Transactional;

public interface TurnoRepository extends JpaRepository<Turno, TurnoId> {
    /**
     *  Obtiene los turnos, con todos sus atributos, filtrando por id de estudiante y fecha del turno
     *   
     *  @param estudianteId : id del estudiante
     *  @param fecha : fecha del turno
     *  @return
     */
    @Query(value = "SELECT * " +
            "FROM tbl_turno " +
            "WHERE pu_id = :estudianteId " +
            "AND tur_fecha = :fecha ", nativeQuery=true)
    List<Turno> findShiftsByStudentId(@Param("estudianteId") int estudianteId, @Param("fecha") Date fecha);

    /**
     *  Obtiene una lista de turnos asociados a un estudiante en una fecha
     *  
     *  @param estudianteId : id del estudiante
     *  @param fecha : fecha del turno
     *  @return lista de objetos TurnoAsociadoDTO
     */
    @Query("SELECT new co.edu.unicauca.gesrotesbackend.services.DTO.TurnoAsociadoDTO(t.id.id, es.nombre, j.franja, j.horaInicio, j.horaFin, et.nombre, t.fecha, e.id, t.alimentacion, CONCAT(e.nombres, ' ', e.apellidos)) " +
            "FROM Turno t " +
            "INNER JOIN Jornada j ON t.jornada.id = j.id " +
            "INNER JOIN Etiqueta et ON t.etiqueta.id = et.id " +
            "INNER JOIN EscenarioPractica es ON et.escenario.id = es.id " +
            "INNER JOIN Asignacion a ON t.id.estAsignacion.id.asignacion.id = a.id " +
            "INNER JOIN CoordinadorAsignatura c ON a.id.coordinador.id = c.id " +
            "INNER JOIN Asignatura asi ON a.id.asignatura.id = asi.id " +
            "INNER JOIN Estudiante e ON t.id.estAsignacion.id.estudiante.id = e.id " +
            "WHERE t.id.estAsignacion.id.estudiante.id = :estudianteId " +
            "AND t.fecha = :fecha ")
    List<TurnoAsociadoDTO> findShiftsAssociationsByDate(@Param("estudianteId") int estudianteId, @Param("fecha") Date fecha);

    
    /**
     *  Obtiene una lista de turnos asociados a un estudiante en una fecha
     *  
     *  @param fecha : fecha del turno
     *  @return lista de objetos 
     */
    @Query("SELECT t.id.estAsignacion.id.estudiante.id " +
            "FROM Turno t " +
            "WHERE t.id.estAsignacion.id.asignacion.id.programa.id = :progId " +
            "AND t.id.estAsignacion.id.asignacion.id.asignatura.id = :asigId " +
            "AND t.id.estAsignacion.id.asignacion.id.coordinador.id = :cooId " +
            "AND t.fecha = :fecha ")
    List<Integer> findAllStudentsIdByDate(@Param("fecha") Date fecha, @Param("progId") int progId, @Param("asigId") int asigId, @Param("cooId") int cooId);

    /**
     *  Obtiene los registros de tbl_turno que tienen un id de estudiante y fecha distintos
     *  
     *  @param programaId : id del programa
     *  @param asignaturaId : id de la asignatura
     *  @param coordinadorId : id del coordinador
     *  @return lista de objetos EstudianteFechaDTO
     */
    @Query("SELECT new co.edu.unicauca.gesrotesbackend.services.DTO.EstudianteFechaDTO(t.id.estAsignacion.id.estudiante.id, t.fecha) " +
                "FROM Turno t " +
                "WHERE t.id.estAsignacion.id.asignacion.id.programa.id = :programaId " +
                "AND t.id.estAsignacion.id.asignacion.id.asignatura.id = :asignaturaId " +
                "AND t.id.estAsignacion.id.asignacion.id.coordinador.id = :coordinadorId " +
                "GROUP BY t.id.estAsignacion.id.estudiante.id, t.id.estAsignacion.id.asignacion.id.programa.id, t.id.estAsignacion.id.asignacion.id.asignatura.id, t.id.estAsignacion.id.asignacion.id.coordinador.id, t.fecha " +
                "HAVING COUNT(*) > 0")
    List<EstudianteFechaDTO> findDifferentSchedules(@Param("programaId") int programaId, @Param("asignaturaId") int asignaturaId, 
                                                        @Param("coordinadorId") int coordinadorId);

    /**
     *  Obtiene las filas de tbl_turno filtrando por id del estudiante y fecha en la que tiene el tunro
     *  
     *  @param estudianteId : id del estudiante que tiene el turno
     *  @param fecha : fecha del turno
     *  @return lista de objetos TurnoAsociadoDTO
     */
    @Query("SELECT new co.edu.unicauca.gesrotesbackend.services.DTO.TurnoAsociadoDTO(t.id.id, es.nombre, j.franja, j.horaInicio, j.horaFin, et.nombre, t.fecha, e.id, t.alimentacion, CONCAT(e.nombres, ' ', e.apellidos)) " +
            "FROM Turno t " +
            "INNER JOIN Jornada j ON t.jornada.id = j.id " +
            "INNER JOIN Etiqueta et ON t.etiqueta.id = et.id " +
            "INNER JOIN EscenarioPractica es ON et.escenario.id = es.id " +
            "INNER JOIN Asignacion a ON t.id.estAsignacion.id.asignacion.id = a.id " +
            "INNER JOIN CoordinadorAsignatura c ON a.id.coordinador.id = c.id " +
            "INNER JOIN Asignatura asi ON a.id.asignatura.id = asi.id " +
            "INNER JOIN Estudiante e ON t.id.estAsignacion.id.estudiante.id = e.id " +
            "WHERE t.id.estAsignacion.id.estudiante.id = :estudianteId " +
            "AND t.fecha = :fecha ")
    List<TurnoAsociadoDTO> findShiftsAssociationsByDate2(@Param("estudianteId") int estudianteId, @Param("fecha") Date fecha);

    /** Elimina un registro de la tabla tbl_turno mediante el id del turno
     *  
     *  @param idTurno : id del turno a eliminar
     */
    @Modifying
    @Transactional
    @Query("DELETE FROM Turno t " +
            "WHERE t.id.id = :idTurno ")
    void myDeletebyid(@Param("idTurno") int idTurno);

    /** Verifica si ya existe un turno con los valores pasados por parámetro
     *  
     *  @param fecha : fecha del turno
     *  @param estudianteId : id del estudiante asociado
     *  @param programaId : id del programa asociado
     *  @param asignaturaId : id de la asignatura asociada
     *  @param coordinadorId : id del coordinador asociado
     *  @param jornadaId : id de la jornada asociada
     *  @param etiquetaId : id de la etiqueta asociada
     * 
     *  @return un entero con el conteo de reigstros que tienen esos atributos  
     */
    @Query(value = "SELECT COUNT(*) FROM tbl_turno " +
                "WHERE tur_fecha = :fecha " +
                "AND pu_id = :estudianteId " +
                "AND prog_id = :programaId " +
                "AND asig_id = :asignaturaId " +
                "AND coo_id = :coordinadorId " +
                "AND jor_id = :jornadaId " +
                "AND eti_id = :etiquetaId", nativeQuery = true)
    int alreadyExists(@Param("fecha") Date fecha, @Param("estudianteId") int estudianteId, @Param("programaId") int programaId, 
                                        @Param("asignaturaId") int asignaturaId, @Param("coordinadorId") int coordinadorId,
                                        @Param("jornadaId") int jornadaId, @Param("etiquetaId") int etiquetaId);

    /** Actualiza un registro de la tabla tbl_turno
     *  
     *  @param turnoId : id del turno
     *  @param etiquetaId : id de la etiqueta asociada
     * 
     *  @return un entero con el conteo de reigstros que tienen esos atributos  
     */
    @Transactional
    @Modifying
    @Query(value = "UPDATE tbl_turno " +
            "SET jor_id = :jornadaId, eti_id = :etiquetaId "+ 
            "WHERE tur_id = :turnoId " , nativeQuery = true)
    int updateShift(@Param("turnoId") int turnoId,
                                        @Param("jornadaId") int jornadaId, 
                                        @Param("etiquetaId") int etiquetaId);
    
    /** Obtiene la información de un turno para enviarla por correo a un estudiante
     *  
     *  @param fecha : fecha del turno
     *  @param estudianteId : id del estudiante asociado
     *  @param programaId : id del programa asociado
     *  @param asignaturaId : id de la asignatura asociada
     *  @param coordinadorId : id del coordinador asociado
     *  @param jornadaId : id de la jornada asociada
     *  @param etiquetaId : id de la etiqueta asociada
     * 
     *  @return un DTO con la información necesaria para enviar el correo  
     */
    @Query("SELECT new " +
            "co.edu.unicauca.gesrotesbackend.services.DTO.EmailDTO(CONCAT(e.nombres, ' ', e.apellidos), " +
                                                                                "e.usuario, " +
                                                                                "t.fecha, " +
                                                                                "es.nombre, " + 
                                                                                "et.nombre, " +
                                                                                "t.alimentacion, " +
                                                                                "j.horaInicio, " +
                                                                                "j.horaFin) " +
            "FROM Turno t " +
            "INNER JOIN Estudiante e ON t.id.estAsignacion.id.estudiante.id = e.id " +
            "INNER JOIN Jornada j ON t.jornada.id = j.id " +
            "INNER JOIN Etiqueta et ON t.etiqueta.id = et.id " +
            "INNER JOIN EscenarioPractica es ON et.escenario.id = es.id " +
            "WHERE t.fecha = :fecha " +
            "AND t.id.estAsignacion.id.estudiante.id = :estudianteId " +
            "AND t.id.estAsignacion.id.asignacion.id.programa.id = :programaId " +
            "AND t.id.estAsignacion.id.asignacion.id.asignatura.id = :asignaturaId " +
            "AND t.id.estAsignacion.id.asignacion.id.coordinador.id = :coordinadorId " +
            "AND t.jornada.id = :jornadaId " +
            "AND t.etiqueta.id = :etiquetaId")
    EmailDTO getInfoShiftToNotify(@Param("fecha") Date fecha, @Param("estudianteId") int estudianteId, @Param("programaId") int programaId, 
                                        @Param("asignaturaId") int asignaturaId, @Param("coordinadorId") int coordinadorId,
                                        @Param("jornadaId") int jornadaId, @Param("etiquetaId") int etiquetaId);
}