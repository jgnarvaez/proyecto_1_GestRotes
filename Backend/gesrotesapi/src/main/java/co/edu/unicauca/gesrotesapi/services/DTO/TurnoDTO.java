package co.edu.unicauca.gesrotesapi.services.DTO;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class TurnoDTO {
    
    private int id;
    private Date fecha;
    private Boolean desayuno;
    private Boolean almuerzo;
    private Boolean comida;
    private JornadaDTO jornada;
    private EtiquetaDTO etiqueta;
}
