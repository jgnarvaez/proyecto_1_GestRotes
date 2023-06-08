package co.edu.unicauca.gesrotesbackend.repositories;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import co.edu.unicauca.gesrotesbackend.models.Mes;
import co.edu.unicauca.gesrotesbackend.models.ValidacionTurnos;
import co.edu.unicauca.gesrotesbackend.models.ValidacionTurnosId;
import co.edu.unicauca.gesrotesbackend.services.DTO.ValidacionEstudianteDTO;
import jakarta.transaction.Transactional;

public interface ValidacionTurnosRepository extends JpaRepository<ValidacionTurnos, ValidacionTurnosId> {

    /**
     *  Elimina un registro de la tabla tbl_validacion_turnos, dados unos parametros específicos
     *  
     *  @param estudianteId : id del estudiante asociado
     *  @param programaId : id del programa asociado
     *  @param asignaturaId : id de la asignatura asociada
     *  @param coordinadorId : id del coordinador asociado
    */
    @Modifying
    @Transactional
    @Query("DELETE FROM ValidacionTurnos vtu " +
            "WHERE vtu.id.estAsignacion.id.estudiante.id = :estudianteId " +
            "AND vtu.id.estAsignacion.id.asignacion.id.programa.id = :programaId " +
            "AND vtu.id.estAsignacion.id.asignacion.id.asignatura.id = :asignaturaId " +
            "AND vtu.id.estAsignacion.id.asignacion.id.coordinador.id = :coordinadorId ")
    void deleteRowByUnique(@Param("estudianteId") int estudianteId, @Param("programaId") int programaId, 
                                @Param("asignaturaId") int asignaturaId, @Param("coordinadorId") int coordinadorId);
    
    /**
     *  Elimina unos registro de la tabla tbl_validacion_turnos, dados unos parametros específicos
     *  
     *  @param programaId : id del programa asociado
     *  @param asignaturaId : id de la asignatura asociada
     *  @param coordinadorId : id del coordinador asociado
    */
    @Modifying
    @Transactional
    @Query("DELETE FROM ValidacionTurnos vtu " +
            "WHERE vtu.id.estAsignacion.id.asignacion.id.programa.id = :programaId " +
            "AND vtu.id.estAsignacion.id.asignacion.id.asignatura.id = :asignaturaId " +
            "AND vtu.id.estAsignacion.id.asignacion.id.coordinador.id = :coordinadorId ")
    void deleteRowsByAsignation(@Param("programaId") int programaId, @Param("asignaturaId") int asignaturaId, 
                                @Param("coordinadorId") int coordinadorId);

    /**
     *  Obtiene los estudiantes para listar y validar.
     *  
     *  @param mes : mes de validacion
     *  @param anio : año de validacion
     *  @param programaId : id del programa asociado
     *  @param asignaturaId : id de la asignatura asociada
     *  @param coordinadorId : id del coordinador asociado
     */
    @Query("SELECT DISTINCT new co.edu.unicauca.gesrotesbackend.services.DTO.ValidacionEstudianteDTO(vtu.id.id, CONCAT(e.nombres, ' ', e.apellidos), vtu.asistencia, vtu.estado, vtu.observaciones) " +
            "FROM ValidacionTurnos vtu " +
            "INNER JOIN Estudiante e ON vtu.id.estAsignacion.id.estudiante.id = e.id " +
            "INNER JOIN Turno t ON e.id = t.id.estAsignacion.id.estudiante.id " +
            "AND vtu.id.estAsignacion.id.asignacion.id.programa.id = t.id.estAsignacion.id.asignacion.id.programa.id " +
            "AND vtu.id.estAsignacion.id.asignacion.id.asignatura.id = t.id.estAsignacion.id.asignacion.id.asignatura.id " +
            "AND vtu.id.estAsignacion.id.asignacion.id.coordinador.id = t.id.estAsignacion.id.asignacion.id.coordinador.id " +
            "WHERE vtu.mes = :mes " +
            "AND vtu.anio = :anio " +
            "AND vtu.id.estAsignacion.id.asignacion.id.programa.id = :programaId " +
            "AND vtu.id.estAsignacion.id.asignacion.id.asignatura.id = :asignaturaId " +
            "AND vtu.id.estAsignacion.id.asignacion.id.coordinador.id = :coordinadorId ")
    List<ValidacionEstudianteDTO> getStudentsToValidate(@Param("mes") Mes mes, @Param("anio") int anio, 
                                                        @Param("programaId") int programaId, 
                                                        @Param("asignaturaId") int asignaturaId, 
                                                        @Param("coordinadorId") int coordinadorId);
    
    /**
     *  Obtiene los registros de validacion turnos dados unos IDs.
     *  
     *  @param programaId : id del programa asociado
     *  @param asignaturaId : id de la asignatura asociada
     *  @param coordinadorId : id del coordinador asociado
     */
    @Query("SELECT vtu " +
            "FROM ValidacionTurnos vtu " +
            "INNER JOIN Estudiante e ON vtu.id.estAsignacion.id.estudiante.id = e.id " +
            "WHERE vtu.id.estAsignacion.id.asignacion.id.programa.id = :programaId " +
            "AND vtu.id.estAsignacion.id.asignacion.id.asignatura.id = :asignaturaId " +
            "AND vtu.id.estAsignacion.id.asignacion.id.coordinador.id = :coordinadorId ")
    List<ValidacionTurnos> getStudentsByProgSubjAndCoo(@Param("programaId") int programaId, 
                                                        @Param("asignaturaId") int asignaturaId, 
                                                        @Param("coordinadorId") int coordinadorId);
    
    /**
     *  Obtiene los registros de validacion turnos dados unos IDs.
     *  
     *  @param mes : mes de validacion
     *  @param anio : año de validacion
     *  @param programaId : id del programa asociado
     *  @param asignaturaId : id de la asignatura asociada
     *  @param coordinadorId : id del coordinador asociado
     */
    @Query("SELECT vtu " +
            "FROM ValidacionTurnos vtu " +
            "INNER JOIN Estudiante e ON vtu.id.estAsignacion.id.estudiante.id = e.id " +
            "WHERE vtu.mes = :mes " +
            "AND vtu.anio = :anio " +
            "AND vtu.id.estAsignacion.id.asignacion.id.programa.id = :programaId " +
            "AND vtu.id.estAsignacion.id.asignacion.id.asignatura.id = :asignaturaId " +
            "AND vtu.id.estAsignacion.id.asignacion.id.coordinador.id = :coordinadorId ")
    List<ValidacionTurnos> getStudentsByProgSubjAndCooMonthAndYear(@Param("mes") Mes mes, @Param("anio") int anio,
                                                                        @Param("programaId") int programaId, 
                                                                        @Param("asignaturaId") int asignaturaId, 
                                                                        @Param("coordinadorId") int coordinadorId);
    
    /**
     *  Método que cambia el mes y el año de un registro de validacion de turnos.
     *  
     *  @param mes : mes de validacion
     *  @param anio : año de validacion
     *  @param programaId : id del programa asociado
     *  @param asignaturaId : id de la asignatura asociada
     *  @param coordinadorId : id del coordinador asociado
     */
    @Transactional
    @Modifying
    @Query("UPDATE ValidacionTurnos vtu " +
            "SET vtu.mes = :mes, vtu.anio = :anio "+ 
            "WHERE vtu.id.estAsignacion.id.asignacion.id.programa.id = :programaId " +
            "AND vtu.id.estAsignacion.id.asignacion.id.asignatura.id = :asignaturaId " +
            "AND vtu.id.estAsignacion.id.asignacion.id.coordinador.id = :coordinadorId ")
    void modifyMonthAndYearOfAssosiations(@Param("mes") Mes mes, @Param("anio") int anio, @Param("programaId") int programaId, 
                                @Param("asignaturaId") int asignaturaId, @Param("coordinadorId") int coordinadorId);
    
    /**
     *  Método que cambia la asistencia y el estado de un registro de validacion de turnos.
     *  
     *  @param vtuId : el id del registro.
     *  @param asistencia : valor booleano, 0 no asistió, 1 si asistió.
     *  @param estado : valor booleano, 0 reprobado, 1 aprobado.
     *  @param observaciones : el id del servicio a asociar con la etiqueta.
     */
    @Transactional
    @Modifying
    @Query("UPDATE ValidacionTurnos vtu " +
            "SET vtu.asistencia = :asistencia, vtu.estado = :estado , vtu.observaciones = :observaciones "+ 
            "WHERE vtu.id.id = :vtuId")
    void modifyAssistanceAndState(@Param("vtuId") int vtuId, @Param("asistencia") Boolean asistencia, 
                                        @Param("estado") Boolean estado, @Param("observaciones") String observaciones);
    
    /**
     *  Método que asocia una etiqueta con un servicio dado.
     *  
     *  @param idEtiqueta : el id de la etiqueta a asociar con el servicio.
     *  @param idServicio : el id del servicio a asociar con la etiqueta.
     */
    @Transactional
    @Modifying
    @Query("UPDATE ValidacionTurnos vtu " +
            "SET vtu.observaciones = :observaciones "+ 
            "WHERE vtu.id.id = :vtuId")
    void modifyObservations(@Param("vtuId") int vtuId, @Param("observaciones") String observaciones);
}
