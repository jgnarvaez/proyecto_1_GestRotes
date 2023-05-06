package co.edu.unicauca.gesrotesbackend.services.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 *  Esta clase representa un DTO de un escenario de práctica.
 *  Contiene el id y el nombre del escenario.
 *  Se utiliza para:
 *  -Listar los escenarios existentes.
*/
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EscenarioDTO {
    int idEscenario;
    String nombreEscenario;
}
