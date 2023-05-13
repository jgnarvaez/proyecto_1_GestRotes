package co.edu.unicauca.gesrotesbackend.services.DTO;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


//TODO: Documentar
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ConsultaTurnoEstudianteDTO {
    int idEstudiante;
    Date fechaTurno;
}
