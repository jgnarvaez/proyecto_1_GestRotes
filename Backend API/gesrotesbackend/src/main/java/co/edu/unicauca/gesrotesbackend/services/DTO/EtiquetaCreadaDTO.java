package co.edu.unicauca.gesrotesbackend.services.DTO;

/**
 *  Esta clase representa un DTO de una etiqueta con escenario.
 *  Contiene el id de la etiqueta, el nombre de la etiqueta y el nombre del escenario asociado.
 *  Se utiliza para:
 *  -Listar las etiquetas existentes
*/
public record EtiquetaCreadaDTO (int idEtiqueta, String nombreEtiqueta, String nombreEscenario){
    
}
