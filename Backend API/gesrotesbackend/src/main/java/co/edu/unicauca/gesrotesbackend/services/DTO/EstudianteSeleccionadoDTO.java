package co.edu.unicauca.gesrotesbackend.services.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 *  Esta clase representa un DTO de un estudiante seleccionado.
 *  Contiene el id del estudiante y su nombre completo
 *  Se utiliza para:
 *  -Listar estudiantes seleccionados para asignarles turnos0
*/
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EstudianteSeleccionadoDTO {
    int id;
    String nombreCompleto;
}
