package co.edu.unicauca.gesrotesbackend.services.DTO;

import java.sql.Date;
import java.sql.Time;

import co.edu.unicauca.gesrotesbackend.models.TipoAlimentacion;
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
public class InformacionTurnoAsociadoDTO {
    // * Información en la matriz
    String nombreEscenario;
    String nombreEtiqueta;
    String franjaJornada;
    // * Información pop up
    String nombreEstudiante;
    Time horaInicio;
    Time horaFin;
    TipoAlimentacion alimentacion;
    // * para el front
    Date fecha;
}