package co.edu.unicauca.gesrotesbackend.services.DTO;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EstudianteFechaDTO {
    int idEstudiante;
    Date fechaTurno;
}
