package co.edu.unicauca.gesrotesapi.services.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class EtiquetaDTO {
    
    private int id;
    private String nombre;
    private EscenarioPracticaDTO escenario;
    private ServicioDTO servicio;

    public EtiquetaDTO(){
        
    }
}
