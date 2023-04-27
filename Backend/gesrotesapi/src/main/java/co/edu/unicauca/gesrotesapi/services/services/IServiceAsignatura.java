package co.edu.unicauca.gesrotesapi.services.services;

import java.util.List;

import co.edu.unicauca.gesrotesapi.services.DTO.AsignaturaDTO;

public interface IServiceAsignatura {
    //Obtener todas las asignaturas
    public List<AsignaturaDTO> findAll();
    //Obtener una asignatura en  especifico
    public AsignaturaDTO findById(int id);
}
