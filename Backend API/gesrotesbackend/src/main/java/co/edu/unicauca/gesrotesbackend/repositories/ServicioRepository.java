package co.edu.unicauca.gesrotesbackend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import co.edu.unicauca.gesrotesbackend.models.Servicio;

public interface ServicioRepository extends JpaRepository<Servicio, Integer> {
    
}
