package co.edu.unicauca.gesrotesapi.models;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class EstudianteEntity extends PersonaUniversitariaEntity{
    private List<TurnoEntity> turnos;
}
