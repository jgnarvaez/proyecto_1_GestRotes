package co.edu.unicauca.gesrotesbackend.services.DTO;

//DTO para mostrar información de un horario de turnos asignado a un estudiante en determinada fecha
public record InformacionHorarioTurnoDTO (String nombreEstudiante, String rangoHorario, Boolean desayuno, Boolean almuerzo, Boolean comida){
    
}
