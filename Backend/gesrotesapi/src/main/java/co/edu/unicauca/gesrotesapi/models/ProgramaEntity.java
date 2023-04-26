package co.edu.unicauca.gesrotesapi.models;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ProgramaEntity {
    
    private int id;
    private String nombre;
    private List<AsignaturaEntity> asignaturas;

    public ProgramaEntity(){

    }
}
