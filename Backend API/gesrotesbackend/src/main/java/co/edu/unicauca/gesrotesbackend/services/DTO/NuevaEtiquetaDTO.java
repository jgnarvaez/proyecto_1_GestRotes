package co.edu.unicauca.gesrotesbackend.services.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 *  Esta clase representa una nueva etiqueta para un escenario de práctica.
 *  Contiene el nombre de la etiqueta y el identificador del escenario.
 *  Se utiliza para:
 *  -Crear una nueva etiqueta.
*/
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class NuevaEtiquetaDTO {
    String nombreEtiqueta;
    int idEscenario;
}
