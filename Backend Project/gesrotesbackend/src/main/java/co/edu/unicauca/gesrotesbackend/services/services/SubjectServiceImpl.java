package co.edu.unicauca.gesrotesbackend.services.services;

import co.edu.unicauca.gesrotesbackend.models.SubjectEntity;
import co.edu.unicauca.gesrotesbackend.repositories.SubjectRepository;
import co.edu.unicauca.gesrotesbackend.services.DTO.SubjectProgramDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SubjectServiceImpl implements ISubjectService {
    @Autowired
    private SubjectRepository subjectRepository;

    @Override
    public List<SubjectProgramDTO> getAllByCoo(int cooId) {

        List<SubjectEntity> entities = subjectRepository.findByCoordinatorId(cooId);
        List<SubjectProgramDTO> subjectProgramDTOs = new ArrayList<>();
        for (SubjectEntity entity : entities) {
            SubjectProgramDTO objDTO = new SubjectProgramDTO();
            objDTO.setAsig_nombre(entity.getName());
            objDTO.setProg_nombre(entity.getProgram().getNombre());
            subjectProgramDTOs.add(objDTO);
        }
        System.out.println("Enviado");
        return subjectProgramDTOs;
    }


}

