package co.edu.unicauca.gesrotesbackend.services.DTO;

import java.sql.Time;

/**
 *  Esta clase representa un DTO con la información de una jornada
 *  Contiene el id de la jornada, la franja, la hora de inicio y la hora de fin.
 *  programa, asignatura y coordinador para asociar
 *  Se utiliza para:
 *  -Listar las 
*/
public record JornadaDTO (int idJornada, String franja, Time horaInicio, Time horaFin){
    
}
