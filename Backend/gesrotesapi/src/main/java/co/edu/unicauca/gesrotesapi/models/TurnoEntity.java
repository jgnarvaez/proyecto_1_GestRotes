package co.edu.unicauca.gesrotesapi.models;

import java.sql.Date;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class TurnoEntity {
    
    private int id;
    private Date fecha;
    private JornadaEntity jornada;
    private EtiquetaEntity etiqueta;
    private List<EstudianteEntity> estudiantes;
    private AsignaturaEntity asignatura;

    public TurnoEntity(){

    }
}
