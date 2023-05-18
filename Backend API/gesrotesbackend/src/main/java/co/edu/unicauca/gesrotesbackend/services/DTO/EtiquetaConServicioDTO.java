package co.edu.unicauca.gesrotesbackend.services.DTO;

/**
 *  Esta clase representa un DTO de una etiqueta con servicio.
 *  Contiene el id de la etiqueta, el nombre de la etiqueta, el nombre del escenario y
 *  el nombre del servicio asociados.
 *  Se utiliza para:
 *  -Listar las etiquetas que tienen un servicio asociado
*/
public record EtiquetaConServicioDTO (int idEtiqueta, String nombreEtiqueta, String nombreServicio, String nombreEscenario){
    
}