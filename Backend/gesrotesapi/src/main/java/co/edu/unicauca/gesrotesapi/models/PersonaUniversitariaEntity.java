package co.edu.unicauca.gesrotesapi.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class PersonaUniversitariaEntity {
    
    private int id;
    private String nombre;
    private Long identificacion;
    private String correo;
    private String fotoPerfil;

    public PersonaUniversitariaEntity(){

    }
}
