package co.edu.unicauca.gesrotesbackend.services.services;

import co.edu.unicauca.gesrotesbackend.services.DTO.AsignacionDTO;
import co.edu.unicauca.gesrotesbackend.services.DTO.EstAsignacionDTO;
import co.edu.unicauca.gesrotesbackend.services.DTO.EstudianteDTO;

import java.util.List;

public interface IAsignaturaService {
    //Obtener todas las asignaturas
    List<AsignacionDTO> getAllByCoo(int cooId);
    //Registrar un estudiante
    EstAsignacionDTO registerStudent(int cooId, int progId, int subjId, int studId);
    //Listar estudiantes
    List<EstudianteDTO> getAllStudents(int subjId, int progId, int cooId);
    //Buscar estudiantes por nombre
    List<EstudianteDTO> findStudentsByName(String name, int subjId, int progId, int cooId);
    //Eliminar todos los estudiantes asociados a una asignatura
    void deleteStudents(int cooId, int progId,int subjId);
    //Eliminar estudiante asociado a una asignatura
    void deleteStudent(int cooId, int progId,int subjId, int studId);
}
