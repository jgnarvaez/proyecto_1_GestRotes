package co.edu.unicauca.gesrotesbackend.services.DTO;

import java.sql.Date;

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
    int idJornada;
    int idEtiqueta;
    int idEstudiante;
    int idPrograma;
    int idAsignatura;
    int idCoordinador;
}
