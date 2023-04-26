package co.edu.unicauca.gesrotesapi.models;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class AlimentacionEntity {
    
    private int id;
    private Boolean desayuno;
    private Boolean almuerzo;
    private Boolean comida;
    private List<TurnoEntity> turnos;

    public AlimentacionEntity(){

    }
}
