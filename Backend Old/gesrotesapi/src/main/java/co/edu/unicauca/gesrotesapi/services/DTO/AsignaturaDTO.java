package co.edu.unicauca.gesrotesapi.services.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class AsignaturaDTO {

    private int asig_id;
    private String asig_nombre;
    private String prog_nombre;

    public AsignaturaDTO(){

    }

}
