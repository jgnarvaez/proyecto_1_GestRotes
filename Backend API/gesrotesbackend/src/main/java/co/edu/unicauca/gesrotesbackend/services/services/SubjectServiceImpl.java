package co.edu.unicauca.gesrotesbackend.services.services;

import co.edu.unicauca.gesrotesbackend.models.*;
import co.edu.unicauca.gesrotesbackend.repositories.*;
import co.edu.unicauca.gesrotesbackend.services.DTO.AssignmentDTO;
import co.edu.unicauca.gesrotesbackend.services.DTO.StuAssignmentDTO;
import co.edu.unicauca.gesrotesbackend.services.DTO.StudentDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class SubjectServiceImpl implements ISubjectService {
    @Autowired
    private AssignmentRepository assignmentRepository;
    @Autowired
    private StuAssignmentRepository stuAssignmentRepository;
    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private ProgramRepository programRepository;
    @Autowired
    private SubjectRepository subjectRepository;
    @Autowired
    private SubCoordinatorRepository subCoordinatorRepository;

    //Obetener asignaturas asociadas a un coordinador de asignatura
    @Override
    public List<AssignmentDTO> getAllByCoo(int cooId) {
        List<AssignmentEntity> entities = assignmentRepository.findByCoordinator(cooId);
        List<AssignmentDTO> listDTO = new ArrayList<>();
        for (AssignmentEntity entity : entities) {
            AssignmentDTO objDTO = new AssignmentDTO();
            objDTO.setAsig_nombre(entity.getId().getSubject().getName());
            objDTO.setProg_nombre(entity.getId().getProgram().getName());
            listDTO.add(objDTO);
        }
        //System.out.println("Enviado");
        return listDTO;
    }

    // Registrar estudiante en una asignatura
    @Override
    public StuAssignmentDTO registerStudent(int cooId, int progId,int subjId, int studId) {

        int count = this.assignmentRepository.existsByIds(progId,subjId,cooId);
        if (count < 0) {
            // La fila no existe
            System.out.println("NO esxiste una asignacion disponible");
            return null;
        }

        Optional<StudentEntity> student = this.studentRepository.findById(studId);
        Optional<ProgramEntity> program = this.programRepository.findById(progId);
        Optional<SubjectEntity> subject = this.subjectRepository.findById(subjId);
        Optional<SubCoordinatorEntity> coordinator = this.subCoordinatorRepository.findById(cooId);

        if (student.isPresent() && program.isPresent() && subject.isPresent() && coordinator.isPresent()) {
            //Asignarle los valores a la entity
            StuAssignmentEntity entity = new StuAssignmentEntity();
            StuAssignmentId entityId = new StuAssignmentId(student.get(),program.get(),subject.get(),coordinator.get());
            entity.setId(entityId);
            entity.setState(StuAsgState.Registrado.toString());
            try {
                if (this.stuAssignmentRepository.save(entity) == null) {
                    // Error al guardar el registro
                } else {
                    StuAssignmentDTO objDTO = new StuAssignmentDTO(studId,progId,subjId,cooId,StuAsgState.Registrado);
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
    public List<StudentDTO> getAllStudents(int subjId, int progId, int cooId) {
        List<StudentEntity> entities = this.studentRepository.getStudentInfo(subjId,progId,cooId);
        List<StudentDTO> listDTO = new ArrayList<>();
        for (StudentEntity entity: entities) {
            StudentDTO objDTO = new StudentDTO(entity.getId(),entity.getNames()+" "+entity.getLastnames(),entity.getIdentificationCode(),entity.getUsername());
            listDTO.add(objDTO);
        }
        return listDTO;
    }

    //Obtener estudiantes por nombres o apellidos
    @Override
    public List<StudentDTO> findStudentsByName(String name, int subjId, int progId, int cooId) {
        List<StudentEntity> entities = this.studentRepository.getStudentsByName(name,subjId,progId,cooId);
        List<StudentDTO> listDTO = new ArrayList<>();
        for (StudentEntity entity: entities) {
            StudentDTO objDTO = new StudentDTO(entity.getId(),entity.getNames()+" "+entity.getLastnames(),entity.getIdentificationCode(),entity.getUsername());
            listDTO.add(objDTO);
        }
        return listDTO;
    }

    //Eliminar todos los estudiantes asociados a una asignatura
    public void deleteStudents(int cooId, int progId,int subjId){
        System.out.println("Coo: " + cooId + " Prog: " + progId + " Subj: " + subjId);
        this.stuAssignmentRepository.deleteAllStudents(progId,subjId,cooId);
    }

    //Eliminar estudiante asociado a una asignatura
    public void deleteStudent(int cooId, int progId,int subjId, int studId){
        this.stuAssignmentRepository.deleteStudent(progId,subjId,cooId,studId);
    }
}

