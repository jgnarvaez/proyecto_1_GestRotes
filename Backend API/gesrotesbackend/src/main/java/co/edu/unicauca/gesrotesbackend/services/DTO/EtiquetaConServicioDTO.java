package co.edu.unicauca.gesrotesbackend.services.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 *  Esta clase representa un DTO de una etiqueta con servicio.
 *  Contiene el id de la etiqueta, el nombre de la etiqueta, el nombre del escenario y
 *  el nombre del servicio asociados.
 *  Se utiliza para:
 *  -Listar las etiquetas que tienen un servicio asociado
*/
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EtiquetaConServicioDTO {
    int idEtiqueta;
    String nombreEtiqueta;
    String nombreServicio;
    String nombreEscenario;
}
