package co.edu.unicauca.gesrotesbackend.repositories;

import co.edu.unicauca.gesrotesbackend.models.AssignmentEntity;
import co.edu.unicauca.gesrotesbackend.models.AssignmentId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AssignmentRepository extends JpaRepository<AssignmentEntity, AssignmentId> {
    //@Query("SELECT a FROM AssignmentEntity a WHERE a.id.coordinator.id = :coordinatorId")
    @Query("SELECT a FROM AssignmentEntity a JOIN a.id.coordinator c WHERE c.id = :coordinatorId")
    List<AssignmentEntity> findByCoordinator(@Param("coordinatorId") Integer coordinatorId);
    /*
    Sin @Query:
    List<AssignmentEntity> findByIdCoordinatorId(Integer coordinatorId);
    */
    @Query(value = "SELECT COUNT(*) FROM tbl_asignacion t WHERE t.prog_id = :progId AND t.asig_id = :asigId AND t.coo_id = :cooId", nativeQuery = true)
    int existsByIds(@Param("progId") int progId, @Param("asigId") int subjId, @Param("cooId") int cooId);

}

