package co.edu.unicauca.gesrotesbackend.services.services;

import java.util.List;

import co.edu.unicauca.gesrotesbackend.services.DTO.ConsultaTurnoEstudianteDTO;
import co.edu.unicauca.gesrotesbackend.services.DTO.EstudianteSeleccionadoDTO;
import co.edu.unicauca.gesrotesbackend.services.DTO.InformacionHorarioTurnoDTO;
import co.edu.unicauca.gesrotesbackend.services.DTO.JornadaDTO;
import co.edu.unicauca.gesrotesbackend.services.DTO.NuevoTurnoDTO;
import co.edu.unicauca.gesrotesbackend.services.DTO.SeleccionEstudianteDTO;
import co.edu.unicauca.gesrotesbackend.services.DTO.SeleccionEstudiantesDTO;
import co.edu.unicauca.gesrotesbackend.services.DTO.TurnoCreadoDTO;

public interface ITurnoService {

    //* GESTION ESTUDIANTES */
    /**
     *  TODO: Documentar
     *  @param seleccionEstudiante
     *  @return
     */
    void cambiarEstadoSeleccionado(SeleccionEstudianteDTO seleccionEstudiante);
    
    // void deseleccionarEstudiante(SeleccionEstudianteDTO seleccionEstudiante);

    /**
     *  TODO: Documentar
     *  @param seleccionEstudiantes
     *  @return
     */
    List<EstudianteSeleccionadoDTO> obtenerEstudiantesSeleccionados(SeleccionEstudiantesDTO seleccionEstudiantes);

    /**
     *  TODO: Documentar
     *  @param seleccionEstudiantes
     *  @return
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
     *  TODO: Documentar
     *  @param idEstudiante
     *  @return
     */
    InformacionHorarioTurnoDTO obetenerTurnosEstPorFecha(ConsultaTurnoEstudianteDTO turnoEstudiante);
}
