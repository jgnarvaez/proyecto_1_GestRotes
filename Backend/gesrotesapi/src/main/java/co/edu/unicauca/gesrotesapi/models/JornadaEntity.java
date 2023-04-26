package co.edu.unicauca.gesrotesapi.models;

import java.sql.Time;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class JornadaEntity {
    
    private int id;
    private String franja;
    private Time horaInicio;
    private Time horaFin;

    public JornadaEntity(){
        
    }
}
