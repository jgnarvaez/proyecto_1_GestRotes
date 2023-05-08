package co.edu.unicauca.gesrotesbackend.services.services;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.unicauca.gesrotesbackend.exceptions.ValidacionException;
import co.edu.unicauca.gesrotesbackend.models.EscenarioPractica;
import co.edu.unicauca.gesrotesbackend.models.Etiqueta;
import co.edu.unicauca.gesrotesbackend.models.Servicio;

import co.edu.unicauca.gesrotesbackend.repositories.EscenarioRepository;
import co.edu.unicauca.gesrotesbackend.repositories.EtiquetaRepository;
import co.edu.unicauca.gesrotesbackend.repositories.ServicioRepository;
import co.edu.unicauca.gesrotesbackend.services.DTO.EscenarioDTO;
import co.edu.unicauca.gesrotesbackend.services.DTO.EtiquetaCreadaDTO;
import co.edu.unicauca.gesrotesbackend.services.DTO.EtiquetaPorEscenarioDTO;
import co.edu.unicauca.gesrotesbackend.services.DTO.AsociacionEtiquetaServicioDTO;
import co.edu.unicauca.gesrotesbackend.services.DTO.EtiquetaConServicioDTO;
import co.edu.unicauca.gesrotesbackend.services.DTO.NuevaEtiquetaDTO;
import co.edu.unicauca.gesrotesbackend.services.DTO.ServicioDTO;

@Service
public class EtiquetaServiceImpl implements IEtiquetaService {

    @Autowired 
    private EtiquetaRepository etiquetaRepository;
    @Autowired
    private EscenarioRepository escenarioRepository;
    @Autowired
    private ServicioRepository servicioRepository;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<EtiquetaCreadaDTO> obtenerEtiquetasCreadas() {
        return this.etiquetaRepository.obtenerEtiquetasConEscenario();
    }

    @Override
    public List<EtiquetaConServicioDTO> obtenerEtiquetasAsociadas() {
        return this.etiquetaRepository.obtenerEtiquetasConServicio();
    }

    @Override
    public List<EtiquetaPorEscenarioDTO> obtenerEtiquetasPorEscenario(int idEscenario){
        return this.etiquetaRepository.obtenerPorEscenario(idEscenario);
    }

    @Override
    public List<EscenarioDTO> obetenerEscenarios() {
        List<EscenarioPractica> listadoEntity = this.escenarioRepository.findAll();
        List<EscenarioDTO> listadoDTO = this.modelMapper.map(listadoEntity,new TypeToken<List<EscenarioDTO>>(){}.getType());
        return listadoDTO;
    }

    @Override
    public List<ServicioDTO> obtenerServicios() {
        List<Servicio> listadoEntity = this.servicioRepository.findAll();
        List<ServicioDTO> listadoDTO = this.modelMapper.map(listadoEntity,new TypeToken<List<ServicioDTO>>(){}.getType());
        return listadoDTO;
    }
    
    @Override
    public NuevaEtiquetaDTO crearEtiqueta(NuevaEtiquetaDTO nuevaEtiqueta) {
        // Etiqueta objEntity = this.modelMapper.map(nuevaEtiqueta,Etiqueta.class);
        // System.out.println("ID de la etiqueta: " + objEntity.getId() +
        //                     ". Nombre de la etiqueta: " + objEntity.getNombre() + 
        //                     ". ID del escenario: " + objEntity.getEscenario().getId());
        if(etiquetaRepository.existsByName(nuevaEtiqueta.getNombreEtiqueta()) != 0){
            System.out.println("Ya existe una etiqueta con el nombre " + nuevaEtiqueta.getNombreEtiqueta());
            return null;
        }
        if(etiquetaRepository.saveLabel(nuevaEtiqueta.getNombreEtiqueta(),nuevaEtiqueta.getIdEscenario()) == 0){
            return null;
        }
        //NuevaEtiquetaDTO EtiquetaDTO = this.modelMapper.map(objEtiquetaEntity,NuevaEtiquetaDTO.class);
        return nuevaEtiqueta;
    }

    public AsociacionEtiquetaServicioDTO asociarEtiqueta(int idEtiqueta, int idServicio) {
        try {
            this.etiquetaRepository.asociarEtiquetaConServicio(idEtiqueta, idServicio);
            // Si llegamos aquí, la actualización se ha realizado correctamente
            // Realizar una consulta para obtener la etiqueta actualizada con el servicio asociado
            Etiqueta etiquetaActualizada = etiquetaRepository.getReferenceById(idEtiqueta);
            return new AsociacionEtiquetaServicioDTO(etiquetaActualizada.getId(), etiquetaActualizada.getServicio().getId());
        } catch (Exception e) {
            // Si se produce una excepción, lanzamos una excepción personalizada
            throw new ValidacionException("Error al asociar la etiqueta con el servicio", e);
        }
    }    

    @Override
    public void eliminarEtiqueta(int idEtiqueta) throws ValidacionException {
        Optional<Etiqueta> etiquetaOptional = etiquetaRepository.findById(idEtiqueta);
        if (etiquetaOptional.isPresent()) {
            etiquetaRepository.deleteById(idEtiqueta);
        } else {
            throw new ValidacionException("No se ha encontrado la etiqueta con el ID " + idEtiqueta);
        }
    }

    @Override
    public void eliminarAsociacion(int idEtiqueta) throws ValidacionException {
        Optional<Etiqueta> etiquetaOptional = etiquetaRepository.findById(idEtiqueta);
        if (etiquetaOptional.isPresent()) {
            etiquetaRepository.eliminarServicioAsociado(idEtiqueta);
        } else {
            throw new ValidacionException("No se ha encontrado la etiqueta con el ID " + idEtiqueta);
        }
    }

}
