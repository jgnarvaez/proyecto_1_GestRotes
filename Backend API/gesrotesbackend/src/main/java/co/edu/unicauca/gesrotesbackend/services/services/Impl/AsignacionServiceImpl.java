package co.edu.unicauca.gesrotesbackend.services.services.Impl;

import co.edu.unicauca.gesrotesbackend.repositories.*;
import co.edu.unicauca.gesrotesbackend.services.DTO.AsignacionDTO;
import co.edu.unicauca.gesrotesbackend.services.services.IAsignacionService;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AsignacionServiceImpl implements IAsignacionService {
    private final AsignacionRepository asignacionRepository;

    public AsignacionServiceImpl(AsignacionRepository asignacionRepository){
        this.asignacionRepository = asignacionRepository;
    }

    @Override
    public List<AsignacionDTO> getAllByCoo(int cooId) {
        return asignacionRepository.obtenerPorCoordinador(cooId);
    }
}

