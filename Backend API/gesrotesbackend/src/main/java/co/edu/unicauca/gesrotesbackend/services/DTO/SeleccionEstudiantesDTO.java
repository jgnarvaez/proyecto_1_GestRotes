package co.edu.unicauca.gesrotesbackend.services.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 *  Esta clase representa un DTO para listar los estudiantes seleccionados o para deseleccionarlos
 *  Contiene el id de la asignatura, id del programa al que pertenece la asignatura,
 *  el id del coordinador de asignatura asociado, el mes en el cual se le van a asignar turnos y el año.
*/
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SeleccionEstudiantesDTO {
    int progId;
    int asigId;
    int cooId;
}
