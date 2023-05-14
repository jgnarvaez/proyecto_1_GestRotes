package co.edu.unicauca.gesrotesbackend.controllers;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import co.edu.unicauca.gesrotesbackend.exceptions.ValidacionException;
import co.edu.unicauca.gesrotesbackend.services.DTO.EstudianteSeleccionadoDTO;
import co.edu.unicauca.gesrotesbackend.services.DTO.InformacionHorarioTurnoDTO;
import co.edu.unicauca.gesrotesbackend.services.DTO.JornadaDTO;
import co.edu.unicauca.gesrotesbackend.services.DTO.NuevoTurnoDTO;
import co.edu.unicauca.gesrotesbackend.services.DTO.SeleccionEstudianteDTO;
import co.edu.unicauca.gesrotesbackend.services.DTO.SeleccionEstudiantesDTO;
import co.edu.unicauca.gesrotesbackend.services.DTO.TurnoAsociadoDTO;
import co.edu.unicauca.gesrotesbackend.services.DTO.TurnoCreadoDTO;
import co.edu.unicauca.gesrotesbackend.services.services.ITurnoService;

@RestController
// @RequestMapping("{cooId}/{progId}/asignaturas/{asigId}")
@RequestMapping("turnos")
@CrossOrigin(origins = "*")
public class ControladorTurno {
    @Autowired
    ITurnoService turnoService;

    // * Seleccionar o deseleccionar un estudiante
    @PutMapping("/seleccion")
    @ResponseBody
    @CrossOrigin(origins = "*", methods = { RequestMethod.PUT })
    public void changeSelectState(@RequestBody SeleccionEstudianteDTO seleccionEstudiante){
        turnoService.cambiarEstadoSeleccionado(seleccionEstudiante);
    }

    // * Deseleccionar un estudiante
    // @PutMapping("/deseleccionar")
    // @ResponseBody
    // @CrossOrigin(origins = "*", methods = { RequestMethod.PUT })
    // public void deselectStudent(@RequestBody SeleccionEstudianteDTO seleccionEstudiante){
    //     turnoService.deseleccionarEstudiante(seleccionEstudiante);
    // }

    // * Listar los estudiantes seleccionados
    @GetMapping("/estudiantesSeleccionados/{progId}/{asigId}/{cooId}")
    @ResponseBody
    public List<EstudianteSeleccionadoDTO> findSelectedStudents(@PathVariable int progId, @PathVariable int asigId, @PathVariable int cooId){
        return turnoService.obtenerEstudiantesSeleccionados(progId, asigId, cooId);
    }

    // * Deseleccionar todos los estudiantes
    @PutMapping("/deseleccionarTodos")
    @ResponseBody
    @CrossOrigin(origins = "*", methods = { RequestMethod.PUT })
    public void deselectStudents(@RequestBody SeleccionEstudiantesDTO seleccionEstudiantes){
        turnoService.deseleccionarEstudiantes(seleccionEstudiantes);
    }

    // * Listar las jornadas
    @GetMapping("/jornadas")
    @ResponseBody
    public List<JornadaDTO> findHospitals(){
        return turnoService.obetenerJornadas();
    }

    // * Crear un turno a un estudiante
    @PostMapping("/")
    @ResponseBody
    public TurnoCreadoDTO create(@RequestBody NuevoTurnoDTO nuevoTurno){
        // TODO: Validar que los ID's no sean nulos
        // TODO: Validar que la fecha no sea nula
        System.out.println("Fecha a asignar: " + nuevoTurno.getFechaTurno());
        return turnoService.crearTurno(nuevoTurno);
    }

    // * Listar horario turno de un estudiante
    @GetMapping("/horarioTurno/{idEstudiante}/{fechaTurno}")
    @ResponseBody
    public InformacionHorarioTurnoDTO findSchedule(@PathVariable int idEstudiante, @PathVariable Date fechaTurno){
        return turnoService.obetenerHorarioTurnoPorFecha(idEstudiante, fechaTurno);
    }

    // * Listar turnos asociados a un estudiante
    @GetMapping("/turnosPorFechaEstudiante/{idEstudiante}/{fechaTurno}")
    @ResponseBody
    public List<TurnoAsociadoDTO> findShifts(@PathVariable int idEstudiante, @PathVariable Date fechaTurno){
        return turnoService.obetenerTurnosPorFecha(idEstudiante, fechaTurno);
    }

    // * Eliminar turno asociado a un estudiante
    @DeleteMapping("/{idTurno}")
    @ResponseBody
    @CrossOrigin(origins = "*", methods = { RequestMethod.DELETE })
    public ResponseEntity<String> delete(@PathVariable int idTurno) {
        try {
            turnoService.eliminarTurnoAsociado(idTurno);
            return ResponseEntity.ok("Turno asociado eliminado correctamente");
        } catch (ValidacionException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se ha encontrado el turno.");
        }
    }
}
