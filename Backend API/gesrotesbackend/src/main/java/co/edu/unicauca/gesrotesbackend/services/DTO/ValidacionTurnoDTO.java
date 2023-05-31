package co.edu.unicauca.gesrotesbackend.services.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ValidacionTurnoDTO {
    // @NotNull(message = "{student.id.empty}")
    // int puId;
    // @NotNull(message = "{program.id.empty}")
    // int progId;
    // @NotNull(message = "{subject.id.empty}")
    // int asigId;
    // @NotNull(message = "{coordinator.id.empty}")
    // int cooId;
    // @NotBlank(message = "{selection.month.empty}")
    // String mes;
    // @NotNull(message = "{selection.year.empty}")
    // int anio;
    int vtuId;
    Boolean asistencia;
    String observaciones;
}
