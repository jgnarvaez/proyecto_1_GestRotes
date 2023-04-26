package co.edu.unicauca.gesrotesapi.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class AsignaturaEntity {
    
    private int id;
    private String nombre;

    public AsignaturaEntity(){
        
    }
}
