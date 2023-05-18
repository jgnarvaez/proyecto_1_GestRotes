package co.edu.unicauca.gesrotesbackend.services.Mapper;

import java.util.function.Function;

import org.springframework.stereotype.Service;

import co.edu.unicauca.gesrotesbackend.models.EscenarioPractica;
import co.edu.unicauca.gesrotesbackend.services.DTO.EscenarioDTO;

@Service
public class EscenarioDTOMapper implements Function<EscenarioPractica, EscenarioDTO> {
	@Override
	public EscenarioDTO apply(EscenarioPractica escenarioPractica) {
        return new EscenarioDTO(
            escenarioPractica.getId(),
            escenarioPractica.getNombre()
        );
    }
}
