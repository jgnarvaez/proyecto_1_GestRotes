package co.edu.unicauca.gesrotesbackend.services.DTO;

public record ValidacionEstudianteDTO (
    int idEstudiante, 
    String nombreCompleto, 
    Boolean asistencia, 
    Boolean estado, 
    String observaciones){

}
