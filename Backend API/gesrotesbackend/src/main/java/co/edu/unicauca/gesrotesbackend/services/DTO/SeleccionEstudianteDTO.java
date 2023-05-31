package co.edu.unicauca.gesrotesbackend.services.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 *  Esta clase representa un DTO para modificar el estado de selección de un estudiante.
 *  Contiene el id del estudiante, id de la asignatura, id del programa al que pertenece la asignatura,
 *  el id del coordinador de asignatura asociado, el mes en el cual se le van a asignar turnos y el año.
*/
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SeleccionEstudianteDTO {
    @NotNull(message = "{student.id.empty}")
    int puId;
    @NotNull(message = "{program.id.empty}")
    int progId;
    @NotNull(message = "{subject.id.empty}")
    int asigId;
    @NotNull(message = "{coordinator.id.empty}")
    int cooId;
    @NotNull(message = "{selection.state.empty}")
    Boolean estado;
    @NotBlank(message = "{selection.month.empty}")
    String mes;
    @NotNull(message = "{selection.year.empty}")
    int anio;
}
