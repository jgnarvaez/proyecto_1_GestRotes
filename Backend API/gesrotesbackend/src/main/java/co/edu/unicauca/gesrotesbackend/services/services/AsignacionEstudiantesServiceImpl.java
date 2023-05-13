package co.edu.unicauca.gesrotesbackend.services.services;

import co.edu.unicauca.gesrotesbackend.models.Estudiante;
import co.edu.unicauca.gesrotesbackend.models.Programa;
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
import co.edu.unicauca.gesrotesbackend.repositories.AsignaturaRepository;
import co.edu.unicauca.gesrotesbackend.repositories.CoordinadorAsigRepository;

import co.edu.unicauca.gesrotesbackend.services.DTO.EstAsignacionDTO;
import co.edu.unicauca.gesrotesbackend.services.DTO.EstudianteDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AsignacionEstudiantesServiceImpl implements IAsignacionEstudiantesService{
    @Autowired
    private AsignacionRepository asignacionRepository;
    @Autowired
    private EstAsignacionRepository estAsignacionRepository;
    @Autowired
    private EstudianteRepository estudianteRepository;
    @Autowired
    private ProgramaRepository programaRepository;
    @Autowired
    private AsignaturaRepository asignaturaRepository;
    @Autowired
    private CoordinadorAsigRepository coordinadorAsigRepository;

    // Registrar estudiante en una asignatura
    @Override
    public EstAsignacionDTO registerStudent(int cooId, int progId, int subjId, int studId) {

        int count = asignacionRepository.existsByIds(progId,subjId,cooId);
        if (count == 0) {
            // La fila no existe
            System.out.println("NO esxiste una asignacion disponible");
            return null;
        }

        Optional<Estudiante> student = estudianteRepository.findById(studId);
        
        Optional<Programa> programa = programaRepository.findById(progId);
        Optional<Asignatura> asignatura = asignaturaRepository.findById(subjId);
        Optional<CoordinadorAsignatura> coordinador = this.coordinadorAsigRepository.findById(cooId);

        AsignacionId idAsignacion = new AsignacionId(programa.get(), asignatura.get(), coordinador.get());
        Optional<Asignacion> asignacion = asignacionRepository.findById(idAsignacion);

        if (student.isPresent() && programa.isPresent() && asignatura.isPresent() && coordinador.isPresent() && asignacion.isPresent()) {
            //Asignarle los valores a la entity
            EstAsignacion entity = new EstAsignacion();
            EstAsignacionId entityId = new EstAsignacionId(student.get(), asignacion.get());
            entity.setId(entityId);
            //entity.setEstado(StuAsgState.Registrado.toString());
            try {
                if (estAsignacionRepository.save(entity) == null) {
                    // Error al guardar el registro
                } else {
                    EstAsignacionDTO objDTO = new EstAsignacionDTO(studId,progId,subjId,cooId);
                    // Registro guardado exitosamente
                    return objDTO;
                }
            }catch (Exception e){
                System.out.println("No se pudo registrar el estudiante");
            }
        } else if(!student.isPresent()){
            // no se encontró el estudiante
            System.out.println("No se encontro el estudiante con el id: " + studId);
        } else if(!programa.isPresent()){
            // no se encontró el programa
            System.out.println("No se encontro el programa con el id: " + progId);
        } else if(!asignatura.isPresent()){
            // no se encontró la asignatura
            System.out.println("No se encontro la asignatura con el id: " + subjId);
        } else if(!coordinador.isPresent()){
            // no se encontró el coordinador
            System.out.println("No se encontro el coordinador de asignatura con el id: " + cooId);
        }
        return null;
    }

    // Obtener estudiantes registrados en una asignatura
    @Override
    public List<EstudianteDTO> getAllStudents(int subjId, int progId, int cooId) {
        List<Estudiante> entities = estudianteRepository.getStudentInfo(subjId,progId,cooId);
        List<EstudianteDTO> listDTO = new ArrayList<>();
        for (Estudiante entity: entities) {
            EstudianteDTO objDTO = new EstudianteDTO(entity.getId(),entity.getNombres()+" "+entity.getApellidos(),entity.getIdentificacion(),entity.getUsuario());
            listDTO.add(objDTO);
        }
        return listDTO;
    }

    //Obtener estudiantes por nombres o apellidos
    @Override
    public List<EstudianteDTO> findStudentsByName(String name, int subjId, int progId, int cooId) {
        List<Estudiante> entities = estudianteRepository.getStudentsByName(name,subjId,progId,cooId);
        List<EstudianteDTO> listDTO = new ArrayList<>();
        for (Estudiante entity: entities) {
            EstudianteDTO objDTO = new EstudianteDTO(entity.getId(),entity.getNombres()+" "+entity.getApellidos(),entity.getIdentificacion(),entity.getUsuario());
            listDTO.add(objDTO);
        }
        return listDTO;
    }

    //Eliminar todos los estudiantes asociados a una asignatura
    public void deleteStudents(int cooId, int progId,int subjId){
        // estAsignacionRepository.eliminarRegistrosTurEstAsignacion(progId,subjId,cooId); //! Deprecated. necesario porque no está ON DELETE CASCADE
        estAsignacionRepository.deleteAllStudents(progId,subjId,cooId);
    }

    //Eliminar estudiante asociado a una asignatura
    public void deleteStudent(int cooId, int progId,int subjId, int studId){
        // estAsignacionRepository.eliminarRegistrosDeEstudianteEnTurEstAsignacion(studId,progId,subjId,cooId); //! Deprecated. necesario porque no está ON DELETE CASCADE
        estAsignacionRepository.deleteStudent(progId,subjId,cooId,studId);
    }
}
