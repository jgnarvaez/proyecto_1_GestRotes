package co.edu.unicauca.gesrotesapi.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class MatriculaEntity {
    private int id;
    private AsignaturaEntity asignatura;
    private EstudianteEntity estudiante;
    private Boolean estado;

    public MatriculaEntity (){
    }
}
