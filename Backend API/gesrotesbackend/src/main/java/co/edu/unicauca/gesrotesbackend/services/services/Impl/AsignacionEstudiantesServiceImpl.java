package co.edu.unicauca.gesrotesbackend.services.services.Impl;

import co.edu.unicauca.gesrotesbackend.models.Estudiante;
import co.edu.unicauca.gesrotesbackend.models.Programa;
import co.edu.unicauca.gesrotesbackend.models.ValidacionTurnos;
import co.edu.unicauca.gesrotesbackend.models.ValidacionTurnosId;
import co.edu.unicauca.gesrotesbackend.exceptions.HTTPException;
import co.edu.unicauca.gesrotesbackend.models.Asignacion;
import co.edu.unicauca.gesrotesbackend.models.AsignacionId;
import co.edu.unicauca.gesrotesbackend.models.Asignatura;
import co.edu.unicauca.gesrotesbackend.models.CoordinadorAsignatura;
import co.edu.unicauca.gesrotesbackend.models.EstAsignacion;
import co.edu.unicauca.gesrotesbackend.models.EstAsignacionId;

import co.edu.unicauca.gesrotesbackend.repositories.AsignacionRepository;
import co.edu.unicauca.gesrotesbackend.repositories.EstAsignacionRepository;
import co.edu.unicauca.gesrotesbackend.repositories.EstudianteRepository;
import co.edu.unicauca.gesrotesbackend.repositories.ProgramaRepository;
import co.edu.unicauca.gesrotesbackend.repositories.ValidacionTurnosRepository;
import co.edu.unicauca.gesrotesbackend.repositories.AsignaturaRepository;
import co.edu.unicauca.gesrotesbackend.repositories.CoordinadorAsigRepository;

import co.edu.unicauca.gesrotesbackend.services.DTO.EstAsignacionDTO;
import co.edu.unicauca.gesrotesbackend.services.DTO.EstudianteDTO;
import co.edu.unicauca.gesrotesbackend.services.DTO.ValidacionCreadaDTO;
import co.edu.unicauca.gesrotesbackend.services.services.IAsignacionEstudiantesService;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AsignacionEstudiantesServiceImpl implements IAsignacionEstudiantesService{
    private final AsignacionRepository asignacionRepository;
    private final EstAsignacionRepository estAsignacionRepository;
    private final EstudianteRepository estudianteRepository;
    private final ProgramaRepository programaRepository;
    private final AsignaturaRepository asignaturaRepository;
    private final CoordinadorAsigRepository coordinadorAsigRepository;
    private final ValidacionTurnosRepository validacionTurnosRepository;

    public AsignacionEstudiantesServiceImpl(AsignacionRepository asignacionRepository, EstAsignacionRepository estAsignacionRepository,
                                            EstudianteRepository estudianteRepository, ProgramaRepository programaRepository,
                                            AsignaturaRepository asignaturaRepository, CoordinadorAsigRepository coordinadorAsigRepository,
                                            ValidacionTurnosRepository validacionTurnosRepository){
        this.asignacionRepository = asignacionRepository;
        this.estAsignacionRepository = estAsignacionRepository;
        this.estudianteRepository = estudianteRepository;
        this.programaRepository = programaRepository;
        this.asignaturaRepository = asignaturaRepository;
        this.coordinadorAsigRepository = coordinadorAsigRepository;
        this.validacionTurnosRepository = validacionTurnosRepository;
    }

    // * Registrar estudiante en una asignatura
    @Override
    public EstAsignacionDTO registerStudent(int cooId, int progId, int asigId, int estId) {
        // * Validar que existan las asociaciones
        Estudiante estudiante = estudianteRepository.findById(estId)
            .orElseThrow(() -> new HTTPException(HttpStatus.NOT_FOUND.value(),
                                                "No se encontró el estudiante con el ID: " + estId));
        
        Programa programa = programaRepository.findById(progId)
            .orElseThrow(() -> new HTTPException(HttpStatus.NOT_FOUND.value(),
                                                "No se encontró el programa con el ID: " + progId));
        
        Asignatura asignatura = asignaturaRepository.findById(asigId)
            .orElseThrow(() -> new HTTPException(HttpStatus.NOT_FOUND.value(),
                                                "No se encontró la asignatura con el ID: " + asigId));
        
        CoordinadorAsignatura coordinador = this.coordinadorAsigRepository.findById(cooId)
            .orElseThrow(() -> new HTTPException(HttpStatus.NOT_FOUND.value(),
                                                "No se encontró el coordinador con el ID: " + cooId));
        
        AsignacionId idAsignacion = new AsignacionId(programa, asignatura, coordinador);
        
        Asignacion asignacion = asignacionRepository.findById(idAsignacion)
            .orElseThrow(() -> new HTTPException(HttpStatus.NOT_FOUND.value(),
                                                "No se encontró la asignación"));
        
        // * Asignarle los valores a la entity
        EstAsignacion entity = new EstAsignacion();
        EstAsignacionId entityId = new EstAsignacionId(estudiante, asignacion);
        entity.setId(entityId);
        
        // * Guardar el registro en la BD
        estAsignacionRepository.save(entity);
        
        // * Retornar el DTO
        return new EstAsignacionDTO(estId,progId,asigId,cooId);
    }

    // * Obtener estudiantes registrados en una asignatura
    @Override
    public List<EstudianteDTO> getAllStudents(int subjId, int progId, int cooId) {
        return estudianteRepository.getStudentsInfo(subjId,progId,cooId);
    }

    // * Obtener estudiantes por nombres o apellidos
    @Override
    public List<EstudianteDTO> findStudentsByName(String name, int subjId, int progId, int cooId) {
        return estudianteRepository.getStudentsInfoByName(name,subjId,progId,cooId);
    }

    // * Eliminar todos los estudiantes asociados a una asignatura
    public void deleteStudents(int cooId, int progId,int subjId){
        estAsignacionRepository.deleteAllStudents(progId,subjId,cooId);
    }

    // * Eliminar estudiante asociado a una asignatura
    public void deleteStudent(int cooId, int progId,int subjId, int studId){
        estAsignacionRepository.deleteStudent(progId,subjId,cooId,studId);
    }

    public ValidacionCreadaDTO crearValidacionTurnos(int studId, int progId, int subjId, int cooId){
        EstAsignacion estAsignacion = estAsignacionRepository.getRowByIds(studId, progId, subjId, cooId);
        
        ValidacionTurnosId idValidacionTurnos = new ValidacionTurnosId();
        idValidacionTurnos.setEstAsignacion(estAsignacion);
        ValidacionTurnos validacionTurnos = new ValidacionTurnos();
        validacionTurnos.setId(idValidacionTurnos);
        validacionTurnosRepository.save(validacionTurnos);
        ValidacionCreadaDTO validacionCreadaDTO = new ValidacionCreadaDTO(validacionTurnos.getId().getId(), null, null, null, null);
        return validacionCreadaDTO;
    }
}
