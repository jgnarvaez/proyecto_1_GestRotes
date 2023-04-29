package co.edu.unicauca.gesrotesapi.services.DTO;

import java.sql.Time;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class JornadaDTO {
    private int id;
    private String franja;
    private Time horaInicio;
    private Time horaFin;

    public JornadaDTO(){
    }
}
