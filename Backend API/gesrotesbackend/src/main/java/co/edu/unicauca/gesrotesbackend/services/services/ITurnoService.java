package co.edu.unicauca.gesrotesbackend.services.services;

import java.util.List;

import co.edu.unicauca.gesrotesbackend.services.DTO.JornadaDTO;
import co.edu.unicauca.gesrotesbackend.services.DTO.NuevoTurnoDTO;
import co.edu.unicauca.gesrotesbackend.services.DTO.TurnoCreadoDTO;

public interface ITurnoService {
    /**
     *  Obtiene una lista de objetos JornadaDTO que representan las jornadas registradas.
     *  
     *  @return Una lista de objetos JornadaDTO que representan las jornadas existentes.
     */ 
    List<JornadaDTO> obetenerJornadas();
    
    /**
     *  Crea un turno a un estudiante
     *  
     *  @param nuevoTurno : un objeto NuevoTurnoDTO con la información para crear y asociar el
     *                      turno al estudiante.
     *  @return Un objeto TurnoCreadoDTO que representa el turno asociado al estudiante.
     */ 
    TurnoCreadoDTO crearTurno(NuevoTurnoDTO nuevoTurno);
}
