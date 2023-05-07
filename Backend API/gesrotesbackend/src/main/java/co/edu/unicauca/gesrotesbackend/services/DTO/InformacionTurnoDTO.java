package co.edu.unicauca.gesrotesbackend.services.DTO;

import java.sql.Time;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 *  Esta clase representa un DTO con la información de un turno.
 *  Contiene el nombre completo del estudiante, la hora de inicio y fin del turno,
 *  y booleanos para saber es apto para desayuno, almuerzo o comida.
 *  Se utiliza para:
 *  -Mostrar la información de un turno de un estudiante.
*/
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class InformacionTurnoDTO {
    String nombreEstudiante;
    Time horaInicio;
    Time horaFin;
    Boolean desayuno;
    Boolean almuerzo;
    Boolean comida;
}