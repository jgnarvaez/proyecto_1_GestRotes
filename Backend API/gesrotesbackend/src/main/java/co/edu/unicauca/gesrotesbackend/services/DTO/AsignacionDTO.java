package co.edu.unicauca.gesrotesbackend.services.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 *  Esta clase representa un DTO de una asignatura asociada a un coordinador.
 *  Contiene el id de la asignatura, el nombre de la asignatura y el nombre del programa al que pertenece.
 *  Se utiliza para:
 *  -Listar las asignaturas asociadas a un coordinador de asignatura
*/
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AsignacionDTO {
    private int idAsignatura;
    private String nombreAsignatura;
    private String nombrePrograma;
}