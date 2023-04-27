package co.edu.unicauca.gesrotesapi.models;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ProgramaEntity {
    
    private int prog_id;
    private String prog_nombre;

    public ProgramaEntity(){

    }
}
