package co.edu.unicauca.gesrotesbackend.controllers;

import co.edu.unicauca.gesrotesbackend.services.DTO.EstAsignacionDTO;
import co.edu.unicauca.gesrotesbackend.services.DTO.EstudianteDTO;
import co.edu.unicauca.gesrotesbackend.services.services.IAsignacionEstudiantesService;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@RestController
@RequestMapping("{cooId}/{progId}/asignaturas/{asigId}")
@CrossOrigin(origins = "*")
public class ControladorAsignacionEstudiantes {
    
    private final IAsignacionEstudiantesService asignacionEstudiantesService;

    public ControladorAsignacionEstudiantes(IAsignacionEstudiantesService asignacionEstudiantesService){
        this.asignacionEstudiantesService = asignacionEstudiantesService;
    }

    // Listar estudiantes registrados
    @GetMapping("/estudiantes")
    // @ResponseBody
    public List<EstudianteDTO> findAll(@PathVariable int cooId, @PathVariable int progId, @PathVariable int asigId){
        return asignacionEstudiantesService.getAllStudents(asigId,progId,cooId);
    }

    // Buscar estudiantes por nombre
    @GetMapping("/estudiantes/{puNombre}")
    public List<EstudianteDTO> findStudent(@PathVariable String puNombre, @PathVariable int cooId, @PathVariable int progId, @PathVariable int asigId){
        return asignacionEstudiantesService.findStudentsByName(puNombre,asigId,progId,cooId);
    }

    // Registrar estudiante
    @PostMapping("/estudiantes/{puId}")
    // @ResponseBody
    public EstAsignacionDTO regiStudent(@RequestBody EstAsignacionDTO nuevoRegistro, @PathVariable int cooId, @PathVariable int progId, @PathVariable int asigId, @PathVariable int puId){
        return asignacionEstudiantesService.registerStudent(cooId,progId,asigId, puId);
    }

    //Eliminar todos los estudiantes
    @DeleteMapping("/estudiantes")
    // @ResponseBody
    public void deleteAllStudents(@PathVariable int cooId, @PathVariable int progId,@PathVariable int asigId){
        asignacionEstudiantesService.deleteStudents(cooId,progId,asigId);
    }

    //Eliminar un estudiante en especifico
    @DeleteMapping("/estudiantes/{puId}")
    // @ResponseBody
    public void deleteAllStudent(@PathVariable int cooId, @PathVariable int progId,@PathVariable int asigId,@PathVariable int puId){
        asignacionEstudiantesService.deleteStudent(cooId,progId,asigId,puId);
    }
}
