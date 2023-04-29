package co.edu.unicauca.gesrotesbackend.controllers;

import co.edu.unicauca.gesrotesbackend.models.SubjectEntity;
import co.edu.unicauca.gesrotesbackend.services.DTO.SubjectProgramDTO;
import co.edu.unicauca.gesrotesbackend.services.services.ISubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("asignaturas")
public class SubjectController {

    @Autowired
    private ISubjectService subjectService;

    @GetMapping("/")
    @ResponseBody
    public List<SubjectProgramDTO> findAll(){
        return this.subjectService.getAllByCoo(1);
    }
}
