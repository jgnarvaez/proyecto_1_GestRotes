package co.edu.unicauca.gesrotesapi.services.services;

import java.util.List;

import co.edu.unicauca.gesrotesapi.services.DTO.EstudianteDTO;
import co.edu.unicauca.gesrotesapi.services.DTO.MatriculaDTO;

public interface IServiceMatricula {
    //Registrar estudiante en una asignatura
    public MatriculaDTO matricularEstudiante(int id_estudiante, int id_asignatura);
    //Obtener los estudiantes registrados en una asignatura
    public List<EstudianteDTO> estudiantesMatriculados(int id_asignatura);
    //ELiminar todos los estudiantes de una asignatura
    public boolean eliminarEstudiantes(int id_asignatura);
    //Eliminar un estudiante de una asignatura
    public boolean eliminarEstudiante(int id_estudiante, int id_asignatura);
}
