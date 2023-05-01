package co.edu.unicauca.gesrotesbackend.controllers;

import co.edu.unicauca.gesrotesbackend.services.DTO.AsignacionDTO;
import co.edu.unicauca.gesrotesbackend.services.DTO.EstAsignacionDTO;
import co.edu.unicauca.gesrotesbackend.services.DTO.EstudianteDTO;
import co.edu.unicauca.gesrotesbackend.services.services.IAsignaturaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("{cooId}/{progId}/asignaturas")
public class ControladorAsignatura {

    @Autowired
    private IAsignaturaService subjectService;

    // Listar asignaturas para coordinador
    @GetMapping("/")
    @ResponseBody
    public List<AsignacionDTO> findAll(@PathVariable int cooId){
        return this.subjectService.getAllByCoo(cooId);
    }

    // Listar estudiantes registrados
    @GetMapping("/{asigId}/estudiantes")
    @ResponseBody
    public List<EstudianteDTO> findAll(@PathVariable int cooId, @PathVariable int progId, @PathVariable int asigId){
        return  this.subjectService.getAllStudents(asigId,progId,cooId);
    }

    // Buscar estudiantes por nombre
    @GetMapping("/{asigId}/estudiantes/{puNombre}")
    public List<EstudianteDTO> findStudent(@PathVariable String puNombre, @PathVariable int cooId, @PathVariable int progId, @PathVariable int asigId){
        return this.subjectService.findStudentsByName(puNombre,asigId,progId,cooId);
    }

    // Registrar estudiante
    @PostMapping("/{asigId}/estudiantes/{puId}")
    @ResponseBody
    public EstAsignacionDTO regiStudent(@RequestBody EstAsignacionDTO nuevoRegistro, @PathVariable int cooId, @PathVariable int progId, @PathVariable int asigId, @PathVariable int puId){
        return this.subjectService.registerStudent(cooId,progId,asigId, puId);
    }

    //Eliminar todos los estudiantes
    @DeleteMapping("/{asigId}/estudiantes")
    @ResponseBody
    public void deleteAllStudents(@PathVariable int cooId, @PathVariable int progId,@PathVariable int asigId){
        this.subjectService.deleteStudents(cooId,progId,asigId);
    }

    //Eliminar un estudiante en especifico
    @DeleteMapping("/{asigId}/estudiantes/{puId}")
    @ResponseBody
    public void deleteAllStudent(@PathVariable int cooId, @PathVariable int progId,@PathVariable int asigId,@PathVariable int puId){
        this.subjectService.deleteStudent(cooId,progId,asigId,puId);
    }
}
