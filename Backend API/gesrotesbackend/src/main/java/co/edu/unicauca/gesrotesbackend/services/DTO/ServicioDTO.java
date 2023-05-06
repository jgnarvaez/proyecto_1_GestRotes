package co.edu.unicauca.gesrotesbackend.services.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 *  Esta clase representa un DTO de un servicio.
 *  Contiene el id del servicio y el nombre del servicio.
 *  Se utiliza para:
 *  -Listar los servicios existentes.
*/
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ServicioDTO {
    int idServicio;
    String nombreServicio;
}
