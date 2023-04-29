package co.edu.unicauca.gesrotesapi.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class EtiquetaEntity {
    
    private int id;
    private String nombre;
    private EscenarioPracticaEntity escenario;
    private ServicioEntity servicio;

    public EtiquetaEntity(){
        
    }
}
