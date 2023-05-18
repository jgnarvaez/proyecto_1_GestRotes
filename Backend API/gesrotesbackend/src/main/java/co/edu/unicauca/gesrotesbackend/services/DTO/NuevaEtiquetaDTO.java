package co.edu.unicauca.gesrotesbackend.services.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
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
    @NotBlank(message = "{label.name.empty}")
    @Size(min = 5, max = 30, message = "{label.name.length}")
    @Pattern(regexp = "^[a-zA-Z\\s]+$", message = "{label.name.invalid}")
    String nombreEtiqueta;
    @NotNull(message = "{scenario.id.empty}")
    int idEscenario;
}
