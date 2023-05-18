package co.edu.unicauca.gesrotesbackend.services.DTO;

/**
 *  Esta clase representa un DTO de una asignatura asociada a un coordinador.
 *  Contiene el id de la asignatura, el nombre de la asignatura y el nombre del programa al que pertenece.
 *  Se utiliza para:
 *  -Listar las asignaturas asociadas a un coordinador de asignatura
*/
public record AsignacionDTO (int idAsignatura, String nombreAsignatura, String nombrePrograma){
    
}