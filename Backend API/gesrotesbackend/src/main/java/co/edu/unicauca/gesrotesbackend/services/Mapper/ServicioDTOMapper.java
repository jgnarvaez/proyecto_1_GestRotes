package co.edu.unicauca.gesrotesbackend.services.Mapper;

import java.util.function.Function;

import org.springframework.stereotype.Service;

import co.edu.unicauca.gesrotesbackend.models.Servicio;
import co.edu.unicauca.gesrotesbackend.services.DTO.ServicioDTO;

@Service
public class ServicioDTOMapper implements Function<Servicio, ServicioDTO> {
    @Override
    public ServicioDTO apply(Servicio servicio) {
        return new ServicioDTO(
            servicio.getId(),
            servicio.getNombre()
        );
    }
}
