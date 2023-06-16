package co.edu.unicauca.gesrotesbackend.services.DTO;

import java.sql.Date;
import java.sql.Time;

import co.edu.unicauca.gesrotesbackend.models.TipoAlimentacion;

public record EmailDTO(
    String nombreEstudiante,
    String usuario,
    Date fechaTurno,
    String nombreEscenario,
    String nombreEtiqueta,
    TipoAlimentacion alimentacion,
    Time horaInicio,
    Time horaFin) {
    
}
