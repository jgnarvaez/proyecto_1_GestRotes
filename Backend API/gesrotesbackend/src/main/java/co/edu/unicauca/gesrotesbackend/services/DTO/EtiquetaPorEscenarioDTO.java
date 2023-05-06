package co.edu.unicauca.gesrotesbackend.services.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 *  Esta clase representa un DTO para filtrar estiquetas por un mismo escenario.
 *  Contiene el id de la etiqueta y el nombre de la etiqueta.
 *  Se utiliza para:
 *  -Listar las etiquetas asociadas a un mismo escenario.
*/
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EtiquetaPorEscenarioDTO {
    int idEtiqueta;
    String nombreEtiqueta;
}
