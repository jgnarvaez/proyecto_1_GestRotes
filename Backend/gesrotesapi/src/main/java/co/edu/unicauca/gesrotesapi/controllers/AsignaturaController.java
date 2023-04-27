package co.edu.unicauca.gesrotesapi.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import co.edu.unicauca.gesrotesapi.services.DTO.AsignaturaDTO;
import co.edu.unicauca.gesrotesapi.services.DTO.EstudianteDTO;
import co.edu.unicauca.gesrotesapi.services.DTO.MatriculaDTO;
import co.edu.unicauca.gesrotesapi.services.services.IServiceAsignatura;
import co.edu.unicauca.gesrotesapi.services.services.IServiceMatricula;

import java.util.List;

@RestController
@RequestMapping("asignaturas")
public class AsignaturaController {

    @Autowired
    private IServiceAsignatura serviceAsignatura;
    @Autowired
    private IServiceMatricula serviceMatricula;

    //listar todas las asignaturas
    @GetMapping("/")
    @ResponseBody
    public List<AsignaturaDTO> findAll(){
        return this.serviceAsignatura.findAll();
    }

    //listar una asignatura en especifico
    @GetMapping("/{id}")
    @ResponseBody
    public AsignaturaDTO findById(@PathVariable int id){
        return serviceAsignatura.findById(id);
    }

    //Registrar matricula
    @PostMapping("/matricular")
    @ResponseBody
    public ResponseEntity<String> matricularEstudiante(@RequestParam("pu_id") int idEstudiante, @RequestParam("asig_id") int idAsignatura){
        MatriculaDTO matricula = serviceMatricula.matricularEstudiante(idEstudiante, idAsignatura);
        if(matricula!=null){
            return ResponseEntity.ok("El estudiante ha sido registrado en la asignatura.");
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("No se pudo registar al estudiante en la asignatura.");
        }
    }

    //Listar estudiantes registrados en una asignatura
    @GetMapping("/{id_asignatura}/estudiantes")
    @ResponseBody
    public List<EstudianteDTO> findStudentsBySubject(@PathVariable int id_asignatura){
        return serviceMatricula.estudiantesMatriculados(id_asignatura);
    }

    //Elminar todos los estudiantes registrados en una asignatura
    @DeleteMapping("/{id_asignatura}/estudiantes")
    public ResponseEntity<String> eliminarEstudiantesDeAsignatura(@PathVariable int id_asignatura) {
        if (serviceMatricula.eliminarEstudiantes(id_asignatura)) {
            return ResponseEntity.ok("Se han eliminado todos los estudiantes de la asignatura con ID " + id_asignatura);
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("No se pudo eliminar los estudiantes de la asignatura con ID " + id_asignatura);
        }
    }
}
