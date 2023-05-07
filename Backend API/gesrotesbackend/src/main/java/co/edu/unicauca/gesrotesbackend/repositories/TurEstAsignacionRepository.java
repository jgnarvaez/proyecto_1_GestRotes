package co.edu.unicauca.gesrotesbackend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import co.edu.unicauca.gesrotesbackend.models.TurEstAsignacion;
import co.edu.unicauca.gesrotesbackend.models.TurEstAsignacionId;

public interface TurEstAsignacionRepository extends JpaRepository<TurEstAsignacion, TurEstAsignacionId> {
    
}
