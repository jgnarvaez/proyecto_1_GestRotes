package co.edu.unicauca.gesrotesapi.services.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class PersonaUniversitariaDTO {
    
    private int pu_id;
    private String pu_nombres;
    private String pu_apellidos;
    private Long pu_identificacion;
    private String pu_correo;
    private String pu_foto_perfil;

    public PersonaUniversitariaDTO(){
        
    }
}
