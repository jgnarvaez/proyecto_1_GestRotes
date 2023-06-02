package co.edu.unicauca.gesrotesbackend.services.DTO;

/**
 *  Esta clase representa un DTO de un estudiante seleccionado.
 *  Contiene el id del estudiante y su nombre completo
 *  Se utiliza para:
 *  -Listar estudiantes seleccionados para asignarles turnos0
*/
public record EstudianteSeleccionadoDTO (int id, String nombreCompleto, int mes, int anio){
    
}
