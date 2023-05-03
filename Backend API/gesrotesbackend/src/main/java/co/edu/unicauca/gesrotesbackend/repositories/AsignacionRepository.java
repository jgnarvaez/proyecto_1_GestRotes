package co.edu.unicauca.gesrotesbackend.repositories;

import co.edu.unicauca.gesrotesbackend.models.Asignacion;
import co.edu.unicauca.gesrotesbackend.models.AsignacionId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AsignacionRepository extends JpaRepository<Asignacion, AsignacionId> {
    //@Query("SELECT a FROM Asignacion a WHERE a.id.coordinator.id = :coordinatorId")
    @Query("SELECT a FROM Asignacion a JOIN a.id.coordinador c WHERE c.id = :coordinadorId")
    List<Asignacion> findByIdCoordinadorId(@Param("coordinadorId") Integer coordinatorId);
    /*
    Sin @Query:
    List<AssignmentEntity> findByIdCoordinadorId(Integer coordinatorId);
    */
    @Query(value = "SELECT COUNT(*) FROM tbl_asignacion t WHERE t.prog_id = :progId AND t.asig_id = :asigId AND t.coo_id = :cooId", nativeQuery = true)
    int existsByIds(@Param("progId") int progId, @Param("asigId") int subjId, @Param("cooId") int cooId);

}

