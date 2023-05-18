package co.edu.unicauca.gesrotesbackend.services.DTO;

/**
 *  Esta clase representa un DTO de un estudiante.
 *  Contiene el id del estudiante, su nombre completo, su codigo de identificación y
 *  su usuario.
 *  Se utiliza para:
 *  -Listar estudiantes registrados en una asignatura
 *  -Buscar estudiantes, por nombres o apellidos, que no están registrados en una asignatura.
*/
public record EstudianteDTO (int id, String nombreCompleto, Long identificacion, String usuario){
    
}