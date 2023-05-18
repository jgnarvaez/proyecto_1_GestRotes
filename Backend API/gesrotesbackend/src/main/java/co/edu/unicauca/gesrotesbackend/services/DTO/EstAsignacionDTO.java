package co.edu.unicauca.gesrotesbackend.services.DTO;

/**
 *  Esta clase representa un DTO de un registro de un estudiante en una asignatura.
 *  Contiene el id del estudiante, id de la asignatura, id del programa al que pertenece la asignatura y
 *  el id del coordinador de asignatura asociado.
*/
public record EstAsignacionDTO (int puId, int progId, int asigId, int cooId){
    
}