package co.edu.unicauca.gesrotesapi.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class AsignaturaEntity {
    
    private int asig_id;
    private String asig_nombre;
    private int coo_id;
    private int prog_id;

    public AsignaturaEntity(){
        
    }
}
