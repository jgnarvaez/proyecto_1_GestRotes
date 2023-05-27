package co.edu.unicauca.gesrotesbackend.repositories;

import co.edu.unicauca.gesrotesbackend.models.Asignacion;
import co.edu.unicauca.gesrotesbackend.models.AsignacionId;
import co.edu.unicauca.gesrotesbackend.services.DTO.AsignacionDTO;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AsignacionRepository extends JpaRepository<Asignacion, AsignacionId> {
    /**
     *  Consulta que obtiene los registros de la tabla tbl_asignacion que
     *  tienen en común un coordinador de asignatura específico.
     *  
     *  @param idCoordinador El ID del coordinador a filtrar.
     *  @return Una lista de objetos AsignacionDTO que representan las asignaturas
     *          asignadas al coordinador.
     */ 
    @Query("SELECT new co.edu.unicauca.gesrotesbackend.services.DTO.AsignacionDTO(e.id.asignatura.id, e.id.asignatura.name, e.id.programa.nombre) " +
            "FROM Asignacion e " +
            "WHERE e.id.coordinador.id = :coordinadorId")
    List<AsignacionDTO> getByCoordinatorId(@Param("coordinadorId") int idCoordinador);

    //! Deprecated
    /**
     *  Verifica si existe un registro en la tabla tbl_asignación con
     *  los IDs de programa, asignatura y coordinador especificados.
     *  
     *  @param progId El ID del programa asociado a la asignación.
     *  @param subjId El ID de la asignatura asociada a la asignación.
     *  @param cooId El ID del coordinador asociado a la asignación.
     *  @return El número de filas que coinciden con los IDs especificados.
     */ 
    // @Query(value = "SELECT COUNT(*) " +
    //                 "FROM tbl_asignacion t " +
    //                 "WHERE t.prog_id = :progId " +
    //                     "AND t.asig_id = :asigId " +
    //                     "AND t.coo_id = :cooId", nativeQuery = true)
    // int existsByIds(@Param("progId") int progId, @Param("asigId") int subjId, @Param("cooId") int cooId);

}

