package co.edu.unicauca.gesrotesbackend.services.DTO;

import java.sql.Time;

public record TurnoModificadoDTO (
    String nombreEtiqueta,
    String franjaJornada,
    Time horaInicio,
    Time horaFin,
    String nombreEscenario){
}
