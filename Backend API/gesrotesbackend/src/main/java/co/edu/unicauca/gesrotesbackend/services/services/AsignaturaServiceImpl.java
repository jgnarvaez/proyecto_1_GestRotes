package co.edu.unicauca.gesrotesbackend.services.services;

import co.edu.unicauca.gesrotesbackend.models.*;
import co.edu.unicauca.gesrotesbackend.repositories.*;
import co.edu.unicauca.gesrotesbackend.services.DTO.AsignacionDTO;
import co.edu.unicauca.gesrotesbackend.services.DTO.EstAsignacionDTO;
import co.edu.unicauca.gesrotesbackend.services.DTO.EstudianteDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AsignaturaServiceImpl implements IAsignaturaService {
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

    //Obetener asignaturas asociadas a un coordinador de asignatura
    @Override
    public List<AsignacionDTO> getAllByCoo(int cooId) {
        List<Asignacion> entities = asignacionRepository.findByIdCoordinadorId(cooId);
        List<AsignacionDTO> listDTO = new ArrayList<>();
        for (Asignacion entity : entities) {
            AsignacionDTO objDTO = new AsignacionDTO();
            objDTO.setIdAsignatura(entity.getId().getAsignatura().getId());
            objDTO.setNombreAsignatura(entity.getId().getAsignatura().getName());
            objDTO.setNombrePrograma(entity.getId().getPrograma().getNombre());
            listDTO.add(objDTO);
        }
        //System.out.println("Enviado");
        return listDTO;
    }

    // Registrar estudiante en una asignatura
    @Override
    public EstAsignacionDTO registerStudent(int cooId, int progId, int subjId, int studId) {

        int count = this.asignacionRepository.existsByIds(progId,subjId,cooId);
        if (count == 0) {
            // La fila no existe
            System.out.println("NO esxiste una asignacion disponible");
            return null;
        }

        Optional<Estudiante> student = this.estudianteRepository.findById(studId);
        Optional<Programa> program = this.programaRepository.findById(progId);
        Optional<Asignatura> subject = this.asignaturaRepository.findById(subjId);
        Optional<CoordinadorAsignatura> coordinator = this.coordinadorAsigRepository.findById(cooId);

        if (student.isPresent() && program.isPresent() && subject.isPresent() && coordinator.isPresent()) {
            //Asignarle los valores a la entity
            EstAsignacion entity = new EstAsignacion();
            EstAsignacionId entityId = new EstAsignacionId(student.get(),program.get(),subject.get(),coordinator.get());
            entity.setId(entityId);
            //entity.setEstado(StuAsgState.Registrado.toString());
            try {
                if (this.estAsignacionRepository.save(entity) == null) {
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
        } else if(!program.isPresent()){
            // no se encontró el programa
        } else if(!subject.isPresent()){
            // no se encontró la asignatura
        } else if(!coordinator.isPresent()){
            // no se encontró el coordinador
        }
        return null;
    }

    // Obtener estudiantes registrados en una asignatura
    @Override
    public List<EstudianteDTO> getAllStudents(int subjId, int progId, int cooId) {
        List<Estudiante> entities = this.estudianteRepository.getStudentInfo(subjId,progId,cooId);
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
        List<Estudiante> entities = this.estudianteRepository.getStudentsByName(name,subjId,progId,cooId);
        List<EstudianteDTO> listDTO = new ArrayList<>();
        for (Estudiante entity: entities) {
            EstudianteDTO objDTO = new EstudianteDTO(entity.getId(),entity.getNombres()+" "+entity.getApellidos(),entity.getIdentificacion(),entity.getUsuario());
            listDTO.add(objDTO);
        }
        return listDTO;
    }

    //Eliminar todos los estudiantes asociados a una asignatura
    public void deleteStudents(int cooId, int progId,int subjId){
        System.out.println("Coo: " + cooId + " Prog: " + progId + " Subj: " + subjId);
        this.estAsignacionRepository.deleteAllStudents(progId,subjId,cooId);
    }

    //Eliminar estudiante asociado a una asignatura
    public void deleteStudent(int cooId, int progId,int subjId, int studId){
        this.estAsignacionRepository.deleteStudent(progId,subjId,cooId,studId);
    }
}

