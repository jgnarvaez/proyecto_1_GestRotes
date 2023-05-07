package co.edu.unicauca.gesrotesbackend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import co.edu.unicauca.gesrotesbackend.models.Turno;

public interface TurnoRepository extends JpaRepository<Turno, Integer> {
    
}
