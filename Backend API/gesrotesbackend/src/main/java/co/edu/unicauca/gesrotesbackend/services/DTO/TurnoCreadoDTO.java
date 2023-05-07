package co.edu.unicauca.gesrotesbackend.services.DTO;

import java.sql.Time;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 *  Esta clase representa un DTO con la información de un turno creado
 *  Contiene
 *  Se utiliza para:
 *  -Retornar un DTO con la información del turno creado.
 *  -Listar la información de turnos creados.
*/
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TurnoCreadoDTO {
    String nombreEtiqueta;
    String franjaJornada;
    Time horaInicio;
    Time horaFin;
    String nombreEscenario;
}
