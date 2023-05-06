package co.edu.unicauca.gesrotesbackend.services.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 *  Esta clase representa un DTO de asociación entre una etiqueta y un servicio.
 *  Contiene el id de la etiqueta y el id del servicio a asociar.
 *  Se utiliza para:
 *  -Asociar un servicio a una etiqueta
*/
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AsociacionEtiquetaServicioDTO {
    int idEtiqueta;
    int idServicio;
}