package co.edu.unicauca.gesrotesbackend.services.services.Impl;

import java.util.List;

import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import co.edu.unicauca.gesrotesbackend.exceptions.HTTPException;
import co.edu.unicauca.gesrotesbackend.models.Etiqueta;

import co.edu.unicauca.gesrotesbackend.repositories.EscenarioRepository;
import co.edu.unicauca.gesrotesbackend.repositories.EtiquetaRepository;
import co.edu.unicauca.gesrotesbackend.repositories.ServicioRepository;
import co.edu.unicauca.gesrotesbackend.services.DTO.EscenarioDTO;
import co.edu.unicauca.gesrotesbackend.services.DTO.EtiquetaCreadaDTO;
import co.edu.unicauca.gesrotesbackend.services.DTO.EtiquetaPorEscenarioDTO;
import co.edu.unicauca.gesrotesbackend.services.DTO.AsociacionEtiquetaServicioDTORequest;
import co.edu.unicauca.gesrotesbackend.services.DTO.AsociacionEtiquetaServicioDTOResponse;
import co.edu.unicauca.gesrotesbackend.services.DTO.EtiquetaConServicioDTO;
import co.edu.unicauca.gesrotesbackend.services.DTO.NuevaEtiquetaDTO;
import co.edu.unicauca.gesrotesbackend.services.DTO.ServicioDTO;
import co.edu.unicauca.gesrotesbackend.services.Mapper.EscenarioDTOMapper;
import co.edu.unicauca.gesrotesbackend.services.Mapper.ServicioDTOMapper;
import co.edu.unicauca.gesrotesbackend.services.services.IEtiquetaService;

@Service
public class EtiquetaServiceImpl implements IEtiquetaService {
    private final EtiquetaRepository etiquetaRepository;
    private final EscenarioRepository escenarioRepository;
    private final ServicioRepository servicioRepository;
    private final EscenarioDTOMapper escenarioDTOMapper;
    private final ServicioDTOMapper servicioDTOMapper;

    public EtiquetaServiceImpl(EtiquetaRepository etiquetaRepository,
                                EscenarioRepository escenarioRepository,
                                ServicioRepository servicioRepository,
                                EscenarioDTOMapper escenarioDTOMapper,
                                ServicioDTOMapper servicioDTOMapper){
        this.etiquetaRepository = etiquetaRepository;
        this.escenarioRepository = escenarioRepository;
        this.servicioRepository = servicioRepository;
        this.escenarioDTOMapper = escenarioDTOMapper;
        this.servicioDTOMapper = servicioDTOMapper;
    }

    @Override
    public List<EtiquetaCreadaDTO> obtenerEtiquetasCreadas() {
        return etiquetaRepository.obtenerEtiquetasConEscenario();
    }

    @Override
    public List<EtiquetaConServicioDTO> obtenerEtiquetasAsociadas() {
        return etiquetaRepository.obtenerEtiquetasConServicio();
    }

    @Override
    public List<EtiquetaPorEscenarioDTO> obtenerEtiquetasPorEscenario(int idEscenario){
        return etiquetaRepository.obtenerPorEscenario(idEscenario);
    }

    @Override
    public List<EscenarioDTO> obetenerEscenarios() {
        return escenarioRepository.findAll()
                .stream()
                .map(escenarioDTOMapper)
                .collect(Collectors.toList());
    }

    @Override
    public List<ServicioDTO> obtenerServicios() {
        return servicioRepository.findAll()
                .stream()
                .map(servicioDTOMapper)
                .collect(Collectors.toList());
    }
    
    @Override
    public NuevaEtiquetaDTO crearEtiqueta(NuevaEtiquetaDTO nuevaEtiqueta) {
        if(etiquetaRepository.alreadyExists(nuevaEtiqueta.getNombreEtiqueta(), nuevaEtiqueta.getIdEscenario()) != 0){
            throw new HTTPException(HttpStatus.CONFLICT.value(), 
                                    "Ya existe una etiqueta con nombre " + nuevaEtiqueta.getNombreEtiqueta() + 
                                    " asociada al escenario con ID: " + nuevaEtiqueta.getIdEscenario());
        }else if(etiquetaRepository.saveLabel(nuevaEtiqueta.getNombreEtiqueta(),nuevaEtiqueta.getIdEscenario()) == 0){
            throw new HTTPException(HttpStatus.INTERNAL_SERVER_ERROR.value(), 
                                    "No se pudo crear la etiqueta con el nombre " + nuevaEtiqueta.getNombreEtiqueta() +
                                    " y con ID de escenario: " + nuevaEtiqueta.getIdEscenario());
        }
        return nuevaEtiqueta;
    }

    public AsociacionEtiquetaServicioDTOResponse asociarEtiqueta(AsociacionEtiquetaServicioDTORequest etiqueta) {
        try {
            etiquetaRepository.asociarEtiquetaConServicio(etiqueta.getIdEtiqueta(), etiqueta.getIdServicio());
            // Si llegamos aquí, la actualización se ha realizado correctamente
            // Realizar una consulta para obtener la etiqueta actualizada con el servicio asociado
            Etiqueta etiquetaActualizada = etiquetaRepository.getReferenceById(etiqueta.getIdEtiqueta());
            return new AsociacionEtiquetaServicioDTOResponse(etiquetaActualizada.getId(), etiquetaActualizada.getServicio().getId());
        } catch (Exception e) {
            // Si se produce una excepción, lanzamos una excepción personalizada
            throw new HTTPException(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Error al asociar la etiqueta con el servicio");
        }
    }    

    @Override
    public void eliminarEtiqueta(int idEtiqueta) {
        etiquetaRepository.findById(idEtiqueta)
            .orElseThrow(() -> new HTTPException(HttpStatus.NOT_FOUND.value(), "No se ha encontrado la etiqueta con el ID " + idEtiqueta));
        
        etiquetaRepository.eliminarServicioAsociado(idEtiqueta);
        etiquetaRepository.deleteById(idEtiqueta);
    }

    @Override
    public void eliminarAsociacion(int idEtiqueta) {
        etiquetaRepository.findById(idEtiqueta)
            .orElseThrow(() -> new HTTPException(HttpStatus.NOT_FOUND.value(), "No se ha encontrado la etiqueta con el ID " + idEtiqueta));
        
        etiquetaRepository.eliminarServicioAsociado(idEtiqueta);
    }

    /**
     *  Verifica si una cadena contiene caracteres especiales
     *  
     *  @param str cadena a evaluar
     *  @return true si la cadena contiene caracteres especiales
     */
    // private static boolean hasSpecialChars(String str) {
    //     Pattern pattern = Pattern.compile("[^a-zA-Z0-9 ]");
    //     Matcher matcher = pattern.matcher(str);
    //     return matcher.find();
    // }
}
