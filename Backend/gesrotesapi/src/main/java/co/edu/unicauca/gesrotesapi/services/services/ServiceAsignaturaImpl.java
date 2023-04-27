package co.edu.unicauca.gesrotesapi.services.services;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.unicauca.gesrotesapi.models.AsignaturaEntity;
import co.edu.unicauca.gesrotesapi.repositories.AsignaturaRepository;
import co.edu.unicauca.gesrotesapi.repositories.ProgramaRepository;
import co.edu.unicauca.gesrotesapi.services.DTO.AsignaturaDTO;

@Service
public class ServiceAsignaturaImpl implements IServiceAsignatura{
    //Inyeccion de dependencias
    @Autowired
    private AsignaturaRepository asignaturaRepository;
    @Autowired
    private ProgramaRepository programaRepository;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<AsignaturaDTO> findAll(){
        List<AsignaturaDTO> listadoDTO = new ArrayList<>();
        List<AsignaturaEntity> listadoEntity = this.asignaturaRepository.findAll();
        for (AsignaturaEntity asignaturaEntity : listadoEntity) {
            AsignaturaDTO asignaturaDTO = new AsignaturaDTO();
            String prog_nombre = this.programaRepository.findByID(asignaturaEntity.getProg_id()).getProg_nombre();
            asignaturaDTO.setAsig_id(asignaturaEntity.getAsig_id());
            asignaturaDTO.setAsig_nombre(asignaturaEntity.getAsig_nombre());
            asignaturaDTO.setProg_nombre(prog_nombre);
            listadoDTO.add(asignaturaDTO);
        }
        //List<AsignaturaDTO> listadoDTO = this.modelMapper.map(listadoEntity,new TypeToken<List<AsignaturaDTO>>(){}.getType());
        return listadoDTO;
    }
    
    @Override
    public AsignaturaDTO findById(int id){
        AsignaturaEntity objEntity = this.asignaturaRepository.findByID(id);
        AsignaturaDTO objDTO = this.modelMapper.map(objEntity,AsignaturaDTO.class);
        return objDTO;
    }
}
