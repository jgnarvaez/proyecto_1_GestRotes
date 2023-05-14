package co.edu.unicauca.gesrotesbackend.repositories;

import java.sql.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import co.edu.unicauca.gesrotesbackend.models.Turno;
import co.edu.unicauca.gesrotesbackend.models.TurnoId;
import co.edu.unicauca.gesrotesbackend.services.DTO.InformacionTurnoAsociadoDTO;
import co.edu.unicauca.gesrotesbackend.services.DTO.TurnoAsociadoDTO;
import jakarta.transaction.Transactional;

public interface TurnoRepository extends JpaRepository<Turno, TurnoId> {

    @Query(value = "SELECT * " +
            "FROM tbl_turno " +
            "WHERE pu_id = :estudianteId " +
            "AND tur_fecha = :fecha ", nativeQuery=true)
    List<Turno> findShiftsByStudentId(@Param("estudianteId") int estudianteId, @Param("fecha") Date fecha);

    //TODO: Documentar
    @Query("SELECT new co.edu.unicauca.gesrotesbackend.services.DTO.InformacionTurnoAsociadoDTO(es.nombre, et.nombre, j.franja, CONCAT(e.nombres, ' ', e.apellidos), j.horaInicio, j.horaFin, t.alimentacion, t.fecha) " +
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
    List<InformacionTurnoAsociadoDTO> findShiftsAssociationsByDate(@Param("estudianteId") int estudianteId, @Param("fecha") Date fecha);

    //TODO: Documentar
    @Query("SELECT new co.edu.unicauca.gesrotesbackend.services.DTO.TurnoAsociadoDTO(t.id.id, es.nombre, j.franja, j.horaInicio, j.horaFin, et.nombre) " +
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

    /**
     *  TODO: Documentar
     *  
     *  @param fecha
     *  @param estudianteId
     *  @param programaId
     *  @param asignaturaId
     *  @param coordinadorId
     *  @param jornadaId
     *  @param etiquetaId
     *  @return 
    */
    @Modifying
    @Transactional
    @Query("DELETE FROM Turno t " +
            "WHERE t.fecha = :fecha " +
            "AND t.id.estAsignacion.id.estudiante.id = :estudianteId " +
            "AND t.id.estAsignacion.id.asignacion.id.programa.id = :programaId " +
            "AND t.id.estAsignacion.id.asignacion.id.asignatura.id = :asignaturaId " +
            "AND t.id.estAsignacion.id.asignacion.id.coordinador.id = :coordinadorId " +
            "AND t.jornada.id = :jornadaId " +
            "AND t.etiqueta.id = :etiquetaId")
    void deleteRowByIdsAndOthers(@Param("fecha") Date fecha, @Param("estudianteId") int estudianteId, @Param("programaId") int programaId, 
                                        @Param("asignaturaId") int asignaturaId, @Param("coordinadorId") int coordinadorId,
                                        @Param("jornadaId") int jornadaId, @Param("etiquetaId") int etiquetaId);

    @Modifying
    @Transactional
    @Query("DELETE FROM Turno t " +
            "WHERE t.id.id = :idTurno ")
    void myDeletebyid(@Param("idTurno") int idTurno);
}
