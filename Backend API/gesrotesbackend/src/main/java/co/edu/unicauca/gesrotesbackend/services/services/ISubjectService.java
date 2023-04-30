package co.edu.unicauca.gesrotesbackend.services.services;

import co.edu.unicauca.gesrotesbackend.services.DTO.AssignmentDTO;
import co.edu.unicauca.gesrotesbackend.services.DTO.StuAssignmentDTO;
import co.edu.unicauca.gesrotesbackend.services.DTO.StudentDTO;

import java.util.List;

public interface ISubjectService {
    //Obtener todas las asignaturas
    List<AssignmentDTO> getAllByCoo(int cooId);
    //Registrar un estudiante
    StuAssignmentDTO registerStudent(int cooId, int progId,int subjId, int studId);
    //Listar estudiantes
    List<StudentDTO> getAllStudents(int subjId, int progId, int cooId);
    //Buscar estudiantes por nombre
    List<StudentDTO> findStudentsByName(String name, int subjId, int progId, int cooId);
    //Eliminar todos los estudiantes asociados a una asignatura
    void deleteStudents(int cooId, int progId,int subjId);
    //Eliminar estudiante asociado a una asignatura
    void deleteStudent(int cooId, int progId,int subjId, int studId);
}
