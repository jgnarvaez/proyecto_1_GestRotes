package co.edu.unicauca.gesrotesbackend.controllers;

import co.edu.unicauca.gesrotesbackend.services.DTO.AsignacionDTO;
import co.edu.unicauca.gesrotesbackend.services.services.IAsignacionService;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@RestController
@RequestMapping("{cooId}/{progId}/asignaturas")
@Validated
@CrossOrigin(origins = "*")
public class ControladorAsignacion {
    private final IAsignacionService asignacionService;

    public ControladorAsignacion(IAsignacionService asignacionService){
        this.asignacionService = asignacionService;
    }

    // * Listar asignaturas asociadas a un coordinador
    @GetMapping("/")
    public List<AsignacionDTO> findAllSubjects(@PathVariable("cooId") 
                                        @NotBlank(message = "{coordinator.id.empty}")
                                        @Pattern(regexp = "\\d+", message = "{id.value.string}")
                                        @Min(value = 1, message = "{id.value.min}")
                                        @Digits(integer = 10, fraction = 0, message = "{id.value.float}") String cooId){
        return asignacionService.getAllByCoo(Integer.parseInt(cooId));
    }
}
