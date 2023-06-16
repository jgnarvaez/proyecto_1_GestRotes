package co.edu.unicauca.gesrotesbackend.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
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
import co.edu.unicauca.gesrotesbackend.services.DTO.AsociacionEtiquetaServicioDTORequest;
import co.edu.unicauca.gesrotesbackend.services.DTO.AsociacionEtiquetaServicioDTOResponse;
import co.edu.unicauca.gesrotesbackend.services.DTO.EtiquetaConServicioDTO;
import co.edu.unicauca.gesrotesbackend.services.DTO.NuevaEtiquetaDTO;
import co.edu.unicauca.gesrotesbackend.services.DTO.ServicioDTO;

import co.edu.unicauca.gesrotesbackend.services.services.IEtiquetaService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("etiquetas")
@Validated
@CrossOrigin(origins = "*")
@Transactional(timeout = 2)
public class ControladorEtiqueta {
    private final IEtiquetaService etiquetaService;

    public ControladorEtiqueta(IEtiquetaService etiquetaService){
        this.etiquetaService = etiquetaService;
    }

    // * Listar etiquetas creadas
    @GetMapping("/")
    public List<EtiquetaCreadaDTO> findAll(){
        return etiquetaService.obtenerEtiquetasCreadas();
    }

    // * Listar etiquetas con servicio
    @GetMapping("/onlyService")
    public List<EtiquetaConServicioDTO> findAllWithService(){
        return etiquetaService.obtenerEtiquetasAsociadas();
    }

    // * Listar escenarios
    @GetMapping("/escenarios")
    public List<EscenarioDTO> findHospitals(){
        return etiquetaService.obetenerEscenarios();
    }

    // * Listar servicios
    @GetMapping("/servicios")
    public List<ServicioDTO> findServices(){
        return etiquetaService.obtenerServicios();
    }

    // * Listar etiquetas de un escenario
    @GetMapping("/escenarios/{escId}/etiquetas")
    public List<EtiquetaPorEscenarioDTO> findByHospitals(@PathVariable int escId){
        return etiquetaService.obtenerEtiquetasPorEscenario(escId);
    }

    // * Crear nueva etiqueta
    @PostMapping("/")
    public ResponseEntity<String> createLabel(@Valid @RequestBody NuevaEtiquetaDTO etiquetaRequest){
        NuevaEtiquetaDTO etiquetaResponse = etiquetaService.crearEtiqueta(etiquetaRequest);
        String body = "Etiqueta con nombre " + etiquetaResponse.getNombreEtiqueta() +
                            " e ID de escenario " + etiquetaResponse.getIdEscenario() +
                            " registrada correctamente";
        return ResponseEntity.ok(body);
    }

    // * Asociar servicio a una etiqueta
    @PutMapping("/asociar")
    @CrossOrigin(origins = "*", methods = { RequestMethod.PUT })
    public AsociacionEtiquetaServicioDTOResponse updateLabel(@RequestBody AsociacionEtiquetaServicioDTORequest etiqueta){
        AsociacionEtiquetaServicioDTOResponse objProducto = etiquetaService.asociarEtiqueta(etiqueta);
        return objProducto;
    }

    // * Eliminar una etiqueta
    @DeleteMapping("/{idEtiqueta}")
    @CrossOrigin(origins = "*", methods = { RequestMethod.DELETE })
    public ResponseEntity<String> delete(@PathVariable int idEtiqueta) {
        etiquetaService.eliminarEtiqueta(idEtiqueta);
        String body = "Etiqueta eliminada correctamente";
        return ResponseEntity.ok(body);
    }

    // * Eliminar el servicio de una etiqueta
    @PutMapping("/{idEtiqueta}/eliminarAsosiacion")
    @CrossOrigin(origins = "*", methods = { RequestMethod.PUT })
    public ResponseEntity<String> deleteLabel(@PathVariable int idEtiqueta) {
        etiquetaService.eliminarAsociacion(idEtiqueta);
        String body = "Etiqueta eliminada correctamente";
        return ResponseEntity.ok(body);
    }
}
