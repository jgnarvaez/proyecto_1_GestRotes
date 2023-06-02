package co.edu.unicauca.gesrotesbackend.services.DTO;

public record ValidacionCreadaDTO (
    int idValidacion, 
    String nombreCompleto, 
    Boolean asistencia, 
    Boolean estado, 
    String observaciones){

}
