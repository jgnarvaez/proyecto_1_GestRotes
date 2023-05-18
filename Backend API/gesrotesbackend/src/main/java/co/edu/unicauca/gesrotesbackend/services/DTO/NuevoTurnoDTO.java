package co.edu.unicauca.gesrotesbackend.services.DTO;

import java.sql.Date;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 *  Esta clase representa un DTO con la información necesaria para crear un turno
 *  Contiene la fecha del turno y los ID's de jornada, etiqueta, estudiante,
 *  programa, asignatura y coordinador para asociar
 *  Se utiliza para:
 *  -Crear un turno
*/
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class NuevoTurnoDTO {
    Date fechaTurno;
    @NotNull(message = "{student.id.empty}")
    int idEstudiante;
    @NotNull(message = "{program.id.empty}")
    int idPrograma;
    @NotNull(message = "{subject.id.empty}")
    int idAsignatura;
    @NotNull(message = "{coordinator.id.empty}")
    int idCoordinador;
    @NotNull(message = "{period.id.empty}")
    int idJornada;
    @NotNull(message = "{label.id.empty}")
    int idEtiqueta;
}
