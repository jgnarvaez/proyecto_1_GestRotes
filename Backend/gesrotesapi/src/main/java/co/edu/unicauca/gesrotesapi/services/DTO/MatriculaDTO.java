package co.edu.unicauca.gesrotesapi.services.DTO;

import co.edu.unicauca.gesrotesapi.models.AsignaturaEntity;
import co.edu.unicauca.gesrotesapi.models.EstudianteEntity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class MatriculaDTO {
    private int id;
    private AsignaturaEntity asignatura;
    private EstudianteEntity estudiante;
    private Boolean estado;

    public MatriculaDTO (){
    }
}
