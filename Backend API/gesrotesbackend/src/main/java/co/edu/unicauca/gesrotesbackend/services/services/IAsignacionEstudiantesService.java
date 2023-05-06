package co.edu.unicauca.gesrotesbackend.services.services;

import java.util.List;

import co.edu.unicauca.gesrotesbackend.services.DTO.EstAsignacionDTO;
import co.edu.unicauca.gesrotesbackend.services.DTO.EstudianteDTO;

public interface IAsignacionEstudiantesService {
    /**
     *  Registra un estudiante en una asignautra asociada a un coordinador específico.
     *  
     *  @param cooId : El ID del coordinador asociado.
     *  @param progId : El ID del programa de la asignatura asociada.
     *  @param subjId : El ID de la asignatura asociada.
     *  @param studId : El ID del estudiante a registar.
     *  @return Un objeto EstAsignacionDTO que representa el estudiante que ha sido
     *          registrado en la asignatura.
     */ 
    EstAsignacionDTO registerStudent(int cooId, int progId, int subjId, int studId);
    
    /**
     *  Obtiene la lista de estudiantes registrados en una asignatura asociada
     *  a un coordinador específico.
     *  
     *  @param subjId : El ID de la asignatura asociada.
     *  @param progId : El ID del programa de la asignatura asociada.
     *  @param cooId : El ID del coordinador asociado.
     *  @return Una lista de objetos EstudianteDTO que representa los estudiantes
     *          registrados en determinada asignatura.
     */ 
    List<EstudianteDTO> getAllStudents(int subjId, int progId, int cooId);
    
    /**
     *  Obtiene la lista de estudiantes que no están registrados en una asignatura asociada
     *  a un coordinador específico.
     *  
     *  @param cadenaBusqueda : cadena que debe estar en los nombres o apellidos del estudiante.
     *  @param subjId : El ID de la asignatura asociada.
     *  @param progId : El ID del programa de la asignatura asociada.
     *  @param cooId : El ID del coordinador asociado.
     *  @return Una lista de objetos EstudianteDTO que representa los estudiantes
     *          que no están registrados en determinada asignatura, los cuales en sus
     *          nombres o apellidos contienen la cadena de búsqueda.
     */ 
    List<EstudianteDTO> findStudentsByName(String cadenaBusqueda, int subjId, int progId, int cooId);
    
    /**
     *  Elimina todos los estudiantes registrados en una asignatura asociada
     *  a un coordinador específico.
     *  
     *  @param cooId : El ID del coordinador asociado.
     *  @param progId : El ID del programa de la asignatura asociada.
     *  @param subjId : El ID de la asignatura asociada.
     */ 
    void deleteStudents(int cooId, int progId,int subjId);

    //Eliminar estudiante asociado a una asignatura
    /**
     *  Elimina un estudiantes en específico registrado en una asignatura asociada
     *  a un coordinador específico.
     *  
     *  @param cooId : El ID del coordinador asociado.
     *  @param progId : El ID del programa de la asignatura asociada.
     *  @param subjId : El ID de la asignatura asociada.
     *  @param studId : El ID del estudiante a eliminar.
     */ 
    void deleteStudent(int cooId, int progId,int subjId, int studId);
}
