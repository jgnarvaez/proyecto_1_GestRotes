package co.edu.unicauca.gesrotesbackend.controllers;

import co.edu.unicauca.gesrotesbackend.services.DTO.AsignacionDTO;
import co.edu.unicauca.gesrotesbackend.services.services.IAsignacionService;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@RestController
@RequestMapping("{cooId}/{progId}/asignaturas")
@CrossOrigin(origins = "*")
public class ControladorAsignacion {

    private final IAsignacionService asignacionService;

    public ControladorAsignacion(IAsignacionService asignacionService){
        this.asignacionService = asignacionService;
    }

    // Listar asignaturas asociadas a un coordinador
    @GetMapping("/")
    public List<AsignacionDTO> findAll(@PathVariable int cooId){
        return asignacionService.getAllByCoo(cooId);
    }
}
