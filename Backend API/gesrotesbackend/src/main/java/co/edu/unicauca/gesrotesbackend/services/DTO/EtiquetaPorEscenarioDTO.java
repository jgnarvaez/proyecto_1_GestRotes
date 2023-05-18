package co.edu.unicauca.gesrotesbackend.services.DTO;

/**
 *  Esta clase representa un DTO para filtrar estiquetas por un mismo escenario.
 *  Contiene el id de la etiqueta y el nombre de la etiqueta.
 *  Se utiliza para:
 *  -Listar las etiquetas asociadas a un mismo escenario.
*/
public record EtiquetaPorEscenarioDTO (int idEtiqueta, String nombreEtiqueta){
    
}
