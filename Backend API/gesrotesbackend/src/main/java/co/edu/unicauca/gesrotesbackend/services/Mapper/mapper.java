package co.edu.unicauca.gesrotesbackend.services.Mapper;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 *  Configuración de la clase Mapper utilizada para mapear objetos DTO a entidades y
 *  viceversa.
*/
@Configuration
public class mapper {
    /**
     *  Método que devuelve una instancia de ModelMapper utilizada para mapear
     *  objetos DTO a entidades y viceversa.
     *  @return ModelMapper.
    */
    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    } 
}
