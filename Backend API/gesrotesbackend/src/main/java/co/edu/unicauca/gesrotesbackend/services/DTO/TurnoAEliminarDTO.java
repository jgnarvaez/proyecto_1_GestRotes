package co.edu.unicauca.gesrotesbackend.services.DTO;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TurnoAEliminarDTO {
    Date fecha;
    int idEstudiante;
    int idPrograma;
    int idAsignatura;
    int idCoordinador;
    int idJornada;
    int idEtiqueta;
}
