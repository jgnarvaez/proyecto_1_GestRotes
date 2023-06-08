package co.edu.unicauca.gesrotesbackend.services.DTO;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TurnoAModificarDTO {
    @NotNull(message = "{shift.id.empty}")
    int idTurno;
    @NotNull(message = "{period.id.empty}")
    int idJornada;
    @NotNull(message = "{label.id.empty}")
    int idEtiqueta;
}
