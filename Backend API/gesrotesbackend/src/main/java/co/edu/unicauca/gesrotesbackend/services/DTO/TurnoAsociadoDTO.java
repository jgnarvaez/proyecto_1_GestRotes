package co.edu.unicauca.gesrotesbackend.services.DTO;

import java.sql.Time;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 *  Esta clase representa un DTO con la información de un turno asociado en una fecha.
 *  Contiene el id del turno, le nombre del escenario, la franja y el nombre de la etiqueta
 *  Se utiliza para:
 *  -Listar la informacion de un turno asociado a un estudiante en una fecha.
*/
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TurnoAsociadoDTO {
    int idTurno;
    String nombreEscenario;
    String franjaJornada;
    Time horaInicio;
    Time horaFin;
    String nombreEtiqueta;
}
