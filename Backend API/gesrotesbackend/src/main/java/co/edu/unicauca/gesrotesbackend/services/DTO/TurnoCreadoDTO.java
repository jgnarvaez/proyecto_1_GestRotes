package co.edu.unicauca.gesrotesbackend.services.DTO;

import java.sql.Time;

/**
 *  Esta clase representa un DTO con la información de un turno creado
 *  Contiene
 *  Se utiliza para:
 *  -Retornar un DTO con la información del turno creado.
 *  -Listar la información de turnos creados.
*/
public record TurnoCreadoDTO (
        String nombreEtiqueta,
        String franjaJornada,
        Time horaInicio,
        Time horaFin,
        String nombreEscenario){
}