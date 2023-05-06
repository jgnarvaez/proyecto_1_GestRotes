package co.edu.unicauca.gesrotesbackend.services.services;

import co.edu.unicauca.gesrotesbackend.repositories.*;
import co.edu.unicauca.gesrotesbackend.services.DTO.AsignacionDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AsignacionServiceImpl implements IAsignacionService {
    @Autowired
    private AsignacionRepository asignacionRepository;

    @Override
    public List<AsignacionDTO> getAllByCoo(int cooId) {
        return asignacionRepository.obtenerPorCoordinador(cooId);
    }
}

