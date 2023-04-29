package co.edu.unicauca.gesrotesapi.services.DTO;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EstudianteDTO extends PersonaUniversitariaDTO{
    
    private List<TurnoDTO> turnos;

    public EstudianteDTO(){
    }
}
