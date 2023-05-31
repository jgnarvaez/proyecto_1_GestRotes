package co.edu.unicauca.gesrotesbackend.controllers;

import java.sql.Date;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

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
import co.edu.unicauca.gesrotesbackend.services.services.ITurnoService;

@RestController
@RequestMapping("turnos")
@Validated
@CrossOrigin(origins = "*")
public class ControladorTurno {
    private final ITurnoService turnoService;

    public ControladorTurno(ITurnoService turnoService){
        this.turnoService = turnoService;
    }

    // * Seleccionar o deseleccionar un estudiante
    @PutMapping("/seleccion")
    @CrossOrigin(origins = "*", methods = { RequestMethod.PUT })
    public void changeSelectState(@RequestBody SeleccionEstudianteDTO seleccionEstudiante){
        turnoService.cambiarEstadoSeleccionado(seleccionEstudiante);
    }

    // * Listar los estudiantes seleccionados
    @GetMapping("/estudiantesSeleccionados/{progId}/{asigId}/{cooId}")
    public List<EstudianteSeleccionadoDTO> findSelectedStudents(@PathVariable int progId, @PathVariable int asigId, @PathVariable int cooId){
        return turnoService.obtenerEstudiantesSeleccionados(progId, asigId, cooId);
    }

    // * Deseleccionar todos los estudiantes
    @PutMapping("/deseleccionarTodos")
    @CrossOrigin(origins = "*", methods = { RequestMethod.PUT })
    public void deselectStudents(@RequestBody SeleccionEstudiantesDTO seleccionEstudiantes){
        turnoService.deseleccionarEstudiantes(seleccionEstudiantes);
    }

    // * Listar las jornadas
    @GetMapping("/jornadas")
    public List<JornadaDTO> findHospitals(){
        return turnoService.obetenerJornadas();
    }

    // * Crear un turno a un estudiante
    @PostMapping("/")
    public TurnoCreadoDTO create(@RequestBody NuevoTurnoDTO nuevoTurno){
        return turnoService.crearTurno(nuevoTurno);
    }

    // * Listar informacion de un horario en determinada fecha
    @GetMapping("/horarioTurno/{idEstudiante}/{fechaTurno}")
    public InformacionHorarioTurnoDTO findSchedule(@PathVariable int idEstudiante, @PathVariable Date fechaTurno){
        return turnoService.obetenerInfoHorarioTurnoPorFecha(idEstudiante, fechaTurno);
    }

    // * Listar horarios para una asignacion
    @GetMapping("/{idPrograma}/{idCoordinador}/{idAsignatura}")
    public List<HorarioDTO> findSchedules(@PathVariable int idPrograma, @PathVariable int idCoordinador, @PathVariable int idAsignatura){
        return turnoService.obetenerHorariosTurno(idPrograma, idCoordinador, idAsignatura);
    }

    // * Listar turnos asociados a un estudiante
    @GetMapping("/turnosPorFechaEstudiante/{idEstudiante}/{fechaTurno}")
    public List<TurnoAsociadoDTO> findShifts(@PathVariable int idEstudiante, @PathVariable Date fechaTurno){
        return turnoService.obetenerTurnosPorFecha(idEstudiante, fechaTurno);
    }

    // * Eliminar turno asociado a un estudiante
    @DeleteMapping("/{idTurno}")
    @CrossOrigin(origins = "*", methods = { RequestMethod.DELETE })
    public ResponseEntity<String> delete(@PathVariable int idTurno) {
        // try {
            turnoService.eliminarTurnoAsociado(idTurno);
            return ResponseEntity.ok("Turno asociado eliminado correctamente");
        // } catch (ValidacionException e) {
        //     return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se ha encontrado el turno.");
        // }
    }

    // * Listar los estudiantes con alimentacion
    @GetMapping("/estudiantesConAlimentacion/{progId}/{asigId}/{cooId}/{fecha}")
    public List<InformacionHorarioTurnoDTO> findStudentsWithFood(@PathVariable Date fecha, @PathVariable int progId, @PathVariable int asigId, @PathVariable int cooId){
        return turnoService.obtenerEstudiantesConAlimentacion(fecha, progId, asigId, cooId);
    }

    // * Listar los estudiantes para validacion
    @GetMapping("/estudiantesAValidar/{progId}/{asigId}/{cooId}/{mes}/{anio}")
    public List<ValidacionEstudianteDTO> findStudentsToValidate(@PathVariable int progId, @PathVariable int asigId, @PathVariable int cooId, @PathVariable String mes, @PathVariable int anio){
        SeleccionEstudiantesDTO seleccionEstudiantesDTO = new SeleccionEstudiantesDTO(progId, asigId, cooId, mes, anio);
        return turnoService.obtenerEstudiantesValidacion(seleccionEstudiantesDTO);
    }

    // * Validar asistencia
    @PutMapping("/validarAsistencia")
    @CrossOrigin(origins = "*", methods = { RequestMethod.PUT })
    public void validateAttendance(@RequestBody ValidacionTurnoDTO validacionTurnoDTO){
        turnoService.modificarAsistenciaYEstado(validacionTurnoDTO);
    }

    // * Modificar observaciones
    @PutMapping("/modificarObservaciones")
    @CrossOrigin(origins = "*", methods = { RequestMethod.PUT })
    public void modifyObservations(@RequestBody ModificarObsDTO modificarObsDTO){
        turnoService.modificarObservaciones(modificarObsDTO);
    }
}
