package co.edu.unicauca.gesrotesbackend.services.DTO;

import java.sql.Date;

/**
 *  Esta clase representa un DTO con la información de un turno.
 *  Contiene el nombre completo del estudiante, la hora de inicio y fin del turno,
 *  y booleanos para saber es apto para desayuno, almuerzo o comida.
 *  Se utiliza para:
 *  -Mostrar la información de un turno de un estudiante.
*/
public record HorarioDTO (String nombreEscenario, String nombreEtiqueta, String franjasJornada, Date fechaTurno, int idEstudiante){

}
