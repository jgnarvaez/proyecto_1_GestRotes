package co.edu.unicauca.gesrotesbackend.services.services;

import co.edu.unicauca.gesrotesbackend.services.DTO.EscenarioDTO;
import co.edu.unicauca.gesrotesbackend.services.DTO.EtiquetaCreadaDTO;
import co.edu.unicauca.gesrotesbackend.services.DTO.EtiquetaPorEscenarioDTO;
import co.edu.unicauca.gesrotesbackend.services.DTO.AsociacionEtiquetaServicioDTO;
import co.edu.unicauca.gesrotesbackend.services.DTO.EtiquetaConServicioDTO;
import co.edu.unicauca.gesrotesbackend.services.DTO.NuevaEtiquetaDTO;
import co.edu.unicauca.gesrotesbackend.services.DTO.ServicioDTO;

import java.util.List;

public interface IEtiquetaService {
    /**
     *  Registra una etiqueta asociada a un escenario
     *  
     *  @param nuevaEtiqueta : Objeto DTO con información para crear la etiqueta.
     *  @return Una objeto NuevaEtiquetaDTO que representa la etiqueta creada.
     */ 
    NuevaEtiquetaDTO crearEtiqueta(NuevaEtiquetaDTO nuevaEtiqueta);

    /**
     *  Obtiene una lista de objetos EtiquetaCreadaDTO que representan las etiquetas
     *  existentes.
     *  
     *  @return Una lista de objetos EtiquetaCreadaDTO que representan las etiquetas
     *          que han sido creadas.
     */ 
    List<EtiquetaCreadaDTO> obtenerEtiquetasCreadas();
    
    /**
     *  Obtiene una lista de objetos EtiquetaConServicioDTO que representan las etiquetas
     *  que tienen un servicio asociado.
     *  
     *  @return Una lista de objetos EtiquetaConServicioDTO que representan las etiquetas
     *          que tienen un servicio asociado.
     */ 
    List<EtiquetaConServicioDTO> obtenerEtiquetasAsociadas();
    
    /**
     *  Obtiene una lista de objetos EtiquetaPorEscenarioDTO que representan las etiquetas
     *  que tienen en común un escenario de práctica en específico.
     *  
     *  @param idEscenario : El ID del escenario que deben tener las etiquetas.
     *  @return Una lista de objetos EtiquetaPorEscenarioDTO que representan las etiquetas
     *          que tienen un mismo escenario de práctica.
     */ 
    List<EtiquetaPorEscenarioDTO> obtenerEtiquetasPorEscenario(int idEscenario);

    /**
     *  Obtiene una lista de objetos EscenarioDTO que representan los escenarios
     *  de práctica registrados
     *  
     *  @return Una lista de objetos EscenarioDTO que representan los escenarios
     *          de práctica existentes
     */ 
    List<EscenarioDTO> obetenerEscenarios();

    /**
     *  Obtiene una lista de objetos ServicioDTO que representan los servicios registrados
     *  
     *  @return Una lista de objetos ServicioDTO que representan los servicios existentes
     */ 
    List<ServicioDTO> obtenerServicios();

    /**
     *  Asocia un servicio a una etiqueta
     *  
     *  @param idEtiqueta : El ID de la etiqueta.
     *  @param idServicio : El ID del servicio a asociar.
     *  @return Un objeto AsociacionEtiquetaServicioDTO que representa las asociación
     *          entre la etiqueta y el servicio.
     */ 
    AsociacionEtiquetaServicioDTO asociarEtiqueta(int idEtiqueta, int idServicio);
    
    /**
     *  Elimina una etiqueta
     *  
     *  @param idEtiqueta : El ID de la etiqueta a eliminar.
     */ 
    void eliminarEtiqueta(int idEtiqueta);
    
    /**
     *  Elimina el servicio asociado a una etiqueta
     *  
     *  @param idEtiqueta : El ID de la etiqueta a la que se le va a eliminar el servicio.
     */ 
    void eliminarAsociacion(int idEtiqueta);
}
