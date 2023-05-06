package co.edu.unicauca.gesrotesbackend.services.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 *  Esta clase representa un DTO de una etiqueta con escenario.
 *  Contiene el id de la etiqueta, el nombre de la etiqueta y el nombre del escenario asociado.
 *  Se utiliza para:
 *  -Listar las etiquetas existentes
*/
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EtiquetaCreadaDTO {
    int idEtiqueta;
    String nombreEtiqueta;
    String nombreEscenario;
}
