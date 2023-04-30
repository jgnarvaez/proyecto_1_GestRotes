package co.edu.unicauca.gesrotesbackend.repositories;

import co.edu.unicauca.gesrotesbackend.models.ProgramEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProgramRepository extends JpaRepository<ProgramEntity, Integer> {
}
