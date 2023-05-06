package co.edu.unicauca.gesrotesbackend.services.services;

import co.edu.unicauca.gesrotesbackend.services.DTO.AsignacionDTO;

import java.util.List;

public interface IAsignacionService {
    /**
     *  Obtiene una lista de objetos AsignacionDTO que representan las asignaturas
     *  asignadas a un coordinador específico.
     *  
     *  @param cooId : El ID del coordinador cuyas asignaturas se quieren obtener.
     *  @return Una lista de objetos AsignacionDTO que representan las asignaturas
     *          asignadas al coordinador.
     */ 
    List<AsignacionDTO> getAllByCoo(int cooId);
}
