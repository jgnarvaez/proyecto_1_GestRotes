package co.edu.unicauca.gesrotesapi.services.services;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.unicauca.gesrotesapi.models.EstudianteEntity;
import co.edu.unicauca.gesrotesapi.models.MatriculaEntity;
import co.edu.unicauca.gesrotesapi.repositories.MatriculaRepository;
import co.edu.unicauca.gesrotesapi.services.DTO.EstudianteDTO;
import co.edu.unicauca.gesrotesapi.services.DTO.MatriculaDTO;

@Service
public class ServiceMatriculaImpl implements IServiceMatricula{

    @Autowired
    private MatriculaRepository matriculaRepository;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public MatriculaDTO matricularEstudiante(int id_estudiante, int id_asignatura) {
        MatriculaEntity objMatriculaEntity = this.matriculaRepository.registerStudentInSubject(id_estudiante,id_asignatura);
        MatriculaDTO matriculaDTO = this.modelMapper.map(objMatriculaEntity,MatriculaDTO.class);
        return matriculaDTO;
    }

    @Override
    public List<EstudianteDTO> estudiantesMatriculados(int id_asignatura){
        List<EstudianteEntity> listadoEntity = this.matriculaRepository.findStudentsInSubject(id_asignatura);
        List<EstudianteDTO> listadoDTO = this.modelMapper.map(listadoEntity,new TypeToken<List<EstudianteDTO>>(){}.getType());
        return listadoDTO;
    }
    @Override
    public boolean eliminarEstudiantes(int id_asignatura){
        return matriculaRepository.deleteAllStudentsFromSubject(id_asignatura);
    }
    @Override
    public boolean eliminarEstudiante(int id_estudiante, int id_asignatura){
        return true;
    }
}
