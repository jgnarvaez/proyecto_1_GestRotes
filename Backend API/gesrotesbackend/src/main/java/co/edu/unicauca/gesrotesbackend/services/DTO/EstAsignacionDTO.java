package co.edu.unicauca.gesrotesbackend.services.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 *  Esta clase representa un DTO de un registro de un estudiante en una asignatura.
 *  Contiene el id del estudiante, id de la asignatura, id del programa al que pertenece la asignatura y
 *  el id del coordinador de asignatura asociado.
*/
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EstAsignacionDTO {
    int puId;
    int progId;
    int asigId;
    int cooId;
    //StuAsgState state;
}
