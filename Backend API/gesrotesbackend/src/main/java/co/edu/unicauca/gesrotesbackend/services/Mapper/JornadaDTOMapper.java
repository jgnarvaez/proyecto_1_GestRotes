package co.edu.unicauca.gesrotesbackend.services.Mapper;

import java.util.function.Function;

import org.springframework.stereotype.Service;

import co.edu.unicauca.gesrotesbackend.models.Jornada;
import co.edu.unicauca.gesrotesbackend.services.DTO.JornadaDTO;

@Service
public class JornadaDTOMapper implements Function<Jornada, JornadaDTO> {
    @Override
    public JornadaDTO apply(Jornada jornada) {
        return new JornadaDTO(
            jornada.getId(),
            jornada.getFranja(),
            jornada.getHoraInicio(),
            jornada.getHoraFin()
        );
    }
}
