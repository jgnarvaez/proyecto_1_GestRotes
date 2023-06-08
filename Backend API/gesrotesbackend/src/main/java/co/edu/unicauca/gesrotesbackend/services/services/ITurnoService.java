package co.edu.unicauca.gesrotesbackend.services.services;

import java.sql.Date;
import java.util.List;

import co.edu.unicauca.gesrotesbackend.services.DTO.EstudianteSeleccionadoDTO;
import co.edu.unicauca.gesrotesbackend.services.DTO.HorarioDTO;
import co.edu.unicauca.gesrotesbackend.services.DTO.InformacionHorarioTurnoDTO;
import co.edu.unicauca.gesrotesbackend.services.DTO.JornadaDTO;
import co.edu.unicauca.gesrotesbackend.services.DTO.ModificarObsDTO;
import co.edu.unicauca.gesrotesbackend.services.DTO.NuevoTurnoDTO;
import co.edu.unicauca.gesrotesbackend.services.DTO.SeleccionEstudianteDTO;
import co.edu.unicauca.gesrotesbackend.services.DTO.SeleccionEstudiantesDTO;
import co.edu.unicauca.gesrotesbackend.services.DTO.TurnoAsociadoDTO;
import co.edu.unicauca.gesrotesbackend.services.DTO.TurnoCreadoDTO;
import co.edu.unicauca.gesrotesbackend.services.DTO.ValidacionEstudianteDTO;
import co.edu.unicauca.gesrotesbackend.services.DTO.ValidacionTurnoDTO;

public interface ITurnoService {

    //* GESTION ESTUDIANTES */
    /**
     *  Cambia el estado de selección de un estudiante registrado en una asignación
     *  
     *  @param seleccionEstudiante : DTO con la información para modificar el estado de selección
     */
    void cambiarEstadoSeleccionado(SeleccionEstudianteDTO seleccionEstudiante);
    
    /**
     *  Obtiene los estudiantes, de una asignación, que tienen el estado de seleccionado en true
     *  
     *  @param progId : id del programa asociado
     *  @param asigId : id de la asignatura asociada
     *  @param cooId : id del coordinador asociado
     *  @return lista de objetos EstudianteSeleccionadoDTO
     */
    List<EstudianteSeleccionadoDTO> obtenerEstudiantesSeleccionados(int progId, int asigId, int cooId);

    /**
     *  Deselecciona todos los estudiantes registrados en una asignación
     *  
     *  @param seleccionEstudiantes : DTO con la información de la asignacion para la cual se va a deseleccionar todos los estudiantes 
     */
    void deseleccionarEstudiantes(SeleccionEstudiantesDTO seleccionEstudiantes);

    //* GESTION TURNOS */
    /**
     *  Obtiene una lista de objetos JornadaDTO que representan las jornadas registradas.
     *  
     *  @return Una lista de objetos JornadaDTO que representan las jornadas existentes.
     */ 
    List<JornadaDTO> obetenerJornadas();
    
    /**
     *  Crea un turno a un estudiante
     *  
     *  @param nuevoTurno : un objeto NuevoTurnoDTO con la información para crear y asociar el
     *                      turno al estudiante.
     *  @return Un objeto TurnoCreadoDTO que representa el turno asociado al estudiante.
     */ 
    TurnoCreadoDTO crearTurno(NuevoTurnoDTO nuevoTurno);

    /**
     *  Obtiene la información a mostrar de un horario de turnos asignado a un estudiante
     *  
     *  @param idEstudiante : id del estudiante
     *  @param fechaTurno : fecha en la que tiene asignado el horario
     *  @return un objeto InformacionHorarioTurnoDTO
     */
    InformacionHorarioTurnoDTO obetenerInfoHorarioTurnoPorFecha(int idEstudiante, Date fechaTurno);

    /**
     *  Obtiene todos los horarios de los estudiantes registrados en una asignación
     *  
     *  @param idPrograma : id del programa asociado
     *  @param idCoordinador : id del coordinador asociado
     *  @param idAsignatura : id de la asignatura asociada
     *  @return una lista de objetos HorarioDTO
     */
    List<HorarioDTO> obetenerHorariosTurno(int idPrograma, int idCoordinador, int idAsignatura);

    /**
     *   Obtiene los turnos asociados a un estudiante en determinada fecha
     *  
     *  @param idEstudiante : id del estudiante
     *  @param fechaTurno : fecha de los turnos asociados
     *  @return lista de objetos TurnoAsociadoDTO
     */
    List<TurnoAsociadoDTO> obetenerTurnosPorFecha(int idEstudiante, Date fechaTurno);

    /**
     *  Elimina un turno por medio de su id
     *  
     *  @param idTurno : id del turno a eliminar
     */
    void eliminarTurnoAsociado(int idTurno);

    List<InformacionHorarioTurnoDTO> obtenerEstudiantesConAlimentacion(Date fechaTurno, int progId, int asigId, int cooId);

    List<ValidacionEstudianteDTO> obtenerEstudiantesValidacion(int progId, int asigId, int cooId, String mes, int anio);

    void modificarMesYAnio(int progId, int asigId, int cooId, String mes, int anio);

    void modificarAsistenciaYEstado(ValidacionTurnoDTO validacionAsistenciaDTO);

    void modificarObservaciones(ModificarObsDTO modificarObsDTO);
}
