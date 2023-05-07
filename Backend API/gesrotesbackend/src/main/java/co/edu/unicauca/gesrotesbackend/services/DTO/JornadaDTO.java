package co.edu.unicauca.gesrotesbackend.services.DTO;

import java.sql.Time;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 *  Esta clase representa un DTO con la información de una jornada
 *  Contiene el id de la jornada, la franja, la hora de inicio y la hora de fin.
 *  programa, asignatura y coordinador para asociar
 *  Se utiliza para:
 *  -Listar las 
*/
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class JornadaDTO {
    int idJornada;
    String franja;
    Time horaInicio;
    Time horaFin;
}
