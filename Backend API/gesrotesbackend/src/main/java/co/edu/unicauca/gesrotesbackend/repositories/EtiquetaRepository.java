package co.edu.unicauca.gesrotesbackend.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import co.edu.unicauca.gesrotesbackend.models.Etiqueta;
import co.edu.unicauca.gesrotesbackend.services.DTO.EtiquetaCreadaDTO;
import co.edu.unicauca.gesrotesbackend.services.DTO.EtiquetaPorEscenarioDTO;
import co.edu.unicauca.gesrotesbackend.services.DTO.EtiquetaConServicioDTO;

public interface EtiquetaRepository extends JpaRepository<Etiqueta, Integer> {

    /**
     *  Método que retorna una lista de objetos EtiquetaCreadaDTO que contienen la 
     *  información de las etiquetas y sus respectivos escenarios.
     *  
     *  @return lista de objetos EtiquetaCreadaDTO.
     */
    @Query("SELECT new co.edu.unicauca.gesrotesbackend.services.DTO.EtiquetaCreadaDTO(e.id, e.nombre, e.escenario.nombre) " +
            "FROM Etiqueta e " +
            "JOIN e.escenario esc " +
            "WHERE e.escenario.id = esc.id")
    List<EtiquetaCreadaDTO> obtenerEtiquetasConEscenario();

    /**
     *  Método que retorna una lista de objetos EtiquetaConServicioDTO que contienen la 
     *  información de las etiquetas y sus respectivos servicios.
     *  
     *  @return lista de objetos EtiquetaConServicioDTO.
     */
    @Query("SELECT new co.edu.unicauca.gesrotesbackend.services.DTO.EtiquetaConServicioDTO(e.id, e.nombre, s.nombre, e.escenario.nombre) " +
            "FROM Etiqueta e " +
            "JOIN e.servicio s " +
            "WHERE e.servicio IS NOT NULL")
    List<EtiquetaConServicioDTO> obtenerEtiquetasConServicio();

    /**
     *  Método que retorna una lista de objetos EtiquetaPorEscenarioDTO que contienen la 
     *  información de las etiquetas asociadas a un escenario dado.
     *  
     *  @param idEscenario : el id del escenario para el cual se quiere obtener las etiquetas
     *  @return lista de objetos EtiquetaPorEscenarioDTO.
     */
    @Query("SELECT new co.edu.unicauca.gesrotesbackend.services.DTO.EtiquetaPorEscenarioDTO(e.id, e.nombre) " +
            "FROM Etiqueta e " +
            "WHERE e.escenario.id = :escenarioId")
    List<EtiquetaPorEscenarioDTO> obtenerPorEscenario(@Param("escenarioId") int idEscenario);

    /**
     *  Método que asocia una etiqueta con un servicio dado.
     *  
     *  @param idEtiqueta : el id de la etiqueta a asociar con el servicio.
     *  @param idServicio : el id del servicio a asociar con la etiqueta.
     */
    @Transactional
    @Modifying
    @Query("UPDATE Etiqueta e SET e.servicio.id = :servicioId WHERE e.id = :etiquetaId")
    void asociarEtiquetaConServicio(@Param("etiquetaId") int idEtiqueta, @Param("servicioId") int idServicio);

    /**
     *  Método que elimina la asociación de una etiqueta con un servicio dado.
     *  
     *  @param idEtiqueta : el id de la etiqueta a la cual se le quiere eliminar la asociación con el servicio.
     */
    @Transactional
    @Modifying
    @Query("UPDATE Etiqueta e SET e.servicio = NULL WHERE e.id = :etiquetaId")
    void eliminarServicioAsociado(@Param("etiquetaId") int idEtiqueta);
}
