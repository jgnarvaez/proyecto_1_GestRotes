package co.edu.unicauca.gesrotesbackend.services.services;

import co.edu.unicauca.gesrotesbackend.models.SubjectEntity;
import co.edu.unicauca.gesrotesbackend.services.DTO.SubjectProgramDTO;

import java.util.List;

public interface ISubjectService {
    //Obtener todas las asignaturas
    List<SubjectProgramDTO> getAllByCoo(int cooId);
}
