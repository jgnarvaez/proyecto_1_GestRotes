package co.edu.unicauca.gesrotesbackend.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import co.edu.unicauca.gesrotesbackend.services.DTO.JornadaDTO;
import co.edu.unicauca.gesrotesbackend.services.DTO.NuevoTurnoDTO;
import co.edu.unicauca.gesrotesbackend.services.DTO.TurnoCreadoDTO;
import co.edu.unicauca.gesrotesbackend.services.services.ITurnoService;

@RestController
// @RequestMapping("{cooId}/{progId}/asignaturas/{asigId}")
@RequestMapping("turnos")
@CrossOrigin(origins = "*")
public class ControladorTurno {
    @Autowired
    ITurnoService turnoService;

    // * Listar las jornadas
    @GetMapping("/jornadas")
    @ResponseBody
    public List<JornadaDTO> findHospitals(){
        return turnoService.obetenerJornadas();
    }

    // * Crear un turno a un estudiante
    @PostMapping("/")
    @ResponseBody
    public TurnoCreadoDTO create(@RequestBody NuevoTurnoDTO nuevoTurno){
        // TODO: Validar que los ID's no sean nulos
        // TODO: Validar que la fecha no sea nula
        return turnoService.crearTurno(nuevoTurno);
    }

    // TODO: 
}
