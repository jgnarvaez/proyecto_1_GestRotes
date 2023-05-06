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

    @Query("SELECT new co.edu.unicauca.gesrotesbackend.services.DTO.EtiquetaCreadaDTO(e.id, e.nombre, e.escenario.nombre) " +
            "FROM Etiqueta e " +
            "JOIN e.escenario esc " +
            "WHERE e.escenario.id = esc.id")
    List<EtiquetaCreadaDTO> obtenerEtiquetasConEscenario();

    @Query("SELECT new co.edu.unicauca.gesrotesbackend.services.DTO.EtiquetaConServicioDTO(e.id, e.nombre, s.nombre, e.escenario.nombre) " +
            "FROM Etiqueta e " +
            "JOIN e.servicio s " +
            "WHERE e.servicio IS NOT NULL")
    List<EtiquetaConServicioDTO> obtenerEtiquetasConServicio();

    @Query("SELECT new co.edu.unicauca.gesrotesbackend.services.DTO.EtiquetaPorEscenarioDTO(e.id, e.nombre) " +
            "FROM Etiqueta e " +
            "WHERE e.escenario.id = :escenarioId")
    List<EtiquetaPorEscenarioDTO> obtenerPorEscenario(@Param("escenarioId") int idEscenario);

    @Transactional
    @Modifying
    @Query("UPDATE Etiqueta e SET e.servicio.id = :servicioId WHERE e.id = :etiquetaId")
    void asociarEtiquetaConServicio(@Param("etiquetaId") int idEtiqueta, @Param("servicioId") int idServicio);

    @Transactional
    @Modifying
    @Query("UPDATE Etiqueta e SET e.servicio = NULL WHERE e.id = :etiquetaId")
    void eliminarServicioAsociado(@Param("etiquetaId") int idEtiqueta);
}
