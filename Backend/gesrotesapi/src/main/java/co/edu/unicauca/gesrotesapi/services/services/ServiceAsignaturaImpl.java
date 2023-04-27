package co.edu.unicauca.gesrotesapi.services.services;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.unicauca.gesrotesapi.models.AsignaturaEntity;
import co.edu.unicauca.gesrotesapi.repositories.AsignaturaRepository;
import co.edu.unicauca.gesrotesapi.services.DTO.AsignaturaDTO;

@Service
public class ServiceAsignaturaImpl implements IServiceAsignatura{
    //Inyeccion de dependencias
    @Autowired
    private AsignaturaRepository asignaturaRepository;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<AsignaturaDTO> findAll(){
        List<AsignaturaEntity> listadoEntity = this.asignaturaRepository.findAll();
        List<AsignaturaDTO> listadoDTO = this.modelMapper.map(listadoEntity,new TypeToken<List<AsignaturaDTO>>(){}.getType());
        return listadoDTO;
    }
    
    @Override
    public AsignaturaDTO findById(int id){
        AsignaturaEntity objEntity = this.asignaturaRepository.findByID(id);
        AsignaturaDTO objDTO = this.modelMapper.map(objEntity,AsignaturaDTO.class);
        return objDTO;
    }
}
