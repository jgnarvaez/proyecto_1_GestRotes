package co.edu.unicauca.gesrotesbackend.services.DTO;

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
    int puId;
    int progId;
    int asigId;
    int cooId;
    Boolean estado;
}
