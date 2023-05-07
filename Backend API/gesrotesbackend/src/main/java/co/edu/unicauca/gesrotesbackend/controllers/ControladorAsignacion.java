package co.edu.unicauca.gesrotesbackend.controllers;

import co.edu.unicauca.gesrotesbackend.services.DTO.AsignacionDTO;
import co.edu.unicauca.gesrotesbackend.services.services.IAsignacionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("{cooId}/{progId}/asignaturas")
@CrossOrigin(origins = "*")
public class ControladorAsignacion {

    @Autowired
    private IAsignacionService asignacionService;

    // Listar asignaturas asociadas a un coordinador
    @GetMapping("/")
    @ResponseBody
    public List<AsignacionDTO> findAll(@PathVariable int cooId){
        return asignacionService.getAllByCoo(cooId);
    }
}
