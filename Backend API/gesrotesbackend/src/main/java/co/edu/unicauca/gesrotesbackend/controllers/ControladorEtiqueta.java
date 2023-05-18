package co.edu.unicauca.gesrotesbackend.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import co.edu.unicauca.gesrotesbackend.services.DTO.EscenarioDTO;
import co.edu.unicauca.gesrotesbackend.services.DTO.EtiquetaCreadaDTO;
import co.edu.unicauca.gesrotesbackend.services.DTO.EtiquetaPorEscenarioDTO;
import co.edu.unicauca.gesrotesbackend.exceptions.HTTPException;
import co.edu.unicauca.gesrotesbackend.exceptions.ValidacionException;
import co.edu.unicauca.gesrotesbackend.services.DTO.AsociacionEtiquetaServicioDTO;
import co.edu.unicauca.gesrotesbackend.services.DTO.EtiquetaConServicioDTO;
import co.edu.unicauca.gesrotesbackend.services.DTO.NuevaEtiquetaDTO;
import co.edu.unicauca.gesrotesbackend.services.DTO.ServicioDTO;

import co.edu.unicauca.gesrotesbackend.services.services.IEtiquetaService;

@RestController
@RequestMapping("etiquetas")
@CrossOrigin(origins = "*")
public class ControladorEtiqueta {

    private final IEtiquetaService etiquetaService;

    public ControladorEtiqueta(IEtiquetaService etiquetaService){
        this.etiquetaService = etiquetaService;
    }

    // Listar etiquetas creadas
    @GetMapping("/")
    // @ResponseBody
    public List<EtiquetaCreadaDTO> findAll(){
        return this.etiquetaService.obtenerEtiquetasCreadas();
    }

    // Listar etiquetas con servicio
    @GetMapping("/onlyService")
    // @ResponseBody
    public List<EtiquetaConServicioDTO> findAllWithService(){
        return this.etiquetaService.obtenerEtiquetasAsociadas();
    }

    // Listar escenarios
    @GetMapping("/escenarios")
    // @ResponseBody
    public List<EscenarioDTO> findHospitals(){
        return this.etiquetaService.obetenerEscenarios();
    }

    // Listar servicios
    @GetMapping("/servicios")
    // @ResponseBody
    public List<ServicioDTO> findServices(){
        return this.etiquetaService.obtenerServicios();
    }

    // Listar etiquetas de un escenario
    @GetMapping("/escenarios/{escId}/etiquetas")
    // @ResponseBody
    public List<EtiquetaPorEscenarioDTO> findByHospitals(@PathVariable int escId){
        return this.etiquetaService.obtenerEtiquetasPorEscenario(escId);
    }
    
    // Crear nueva etiqueta
    @PostMapping("/")
    // @ResponseBody
    public ResponseEntity<String> createLabel(@RequestBody NuevaEtiquetaDTO etiqueta){
        try {
            etiquetaService.crearEtiqueta(etiqueta);
        } catch (HTTPException e) {
            if(e.getStatusCode()==400){
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
            }else if(e.getStatusCode()==409){
                return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
            }else if(e.getStatusCode()==500){
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
            }
        }
        return ResponseEntity.ok("Etiqueta registrada correctamente");
    }

    // Asociar servicio a una etiqueta
    @PutMapping("/asociar")
    // @ResponseBody
    @CrossOrigin(origins = "*", methods = { RequestMethod.PUT })
    public AsociacionEtiquetaServicioDTO updateLabel(@RequestBody AsociacionEtiquetaServicioDTO etiqueta){
        AsociacionEtiquetaServicioDTO objProducto = this.etiquetaService.asociarEtiqueta(etiqueta.getIdEtiqueta(),etiqueta.getIdServicio());
        return objProducto;
    }

    // Eliminar una etiqueta
    @DeleteMapping("/{idEtiqueta}")
    // @ResponseBody
    @CrossOrigin(origins = "*", methods = { RequestMethod.DELETE })
    public ResponseEntity<String> delete(@PathVariable int idEtiqueta) {
        try {
            etiquetaService.eliminarEtiqueta(idEtiqueta);
            return ResponseEntity.ok("Etiqueta eliminada correctamente");
        } catch (ValidacionException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se ha encontrado la etiqueta con el ID " + idEtiqueta);
        }
    }

    // Eliminar el servicio de una etiqueta
    @PutMapping("/{idEtiqueta}/eliminarAsosiacion")
    // @ResponseBody
    @CrossOrigin(origins = "*", methods = { RequestMethod.PUT })
    public ResponseEntity<String> deleteLabel(@PathVariable int idEtiqueta) {
        try {
            etiquetaService.eliminarAsociacion(idEtiqueta);
            return ResponseEntity.ok("Servicio desvinculado correctamente");
        } catch (ValidacionException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se ha encontrado la etiqueta con el ID " + idEtiqueta);
        }
    }
}
