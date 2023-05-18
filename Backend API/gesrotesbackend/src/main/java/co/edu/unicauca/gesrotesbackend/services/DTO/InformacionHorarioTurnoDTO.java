package co.edu.unicauca.gesrotesbackend.services.DTO;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

//TODO: Documentar
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class InformacionHorarioTurnoDTO {
    String nombreEstudiante;
    String rangoHorario;
    Boolean desayuno;
    Boolean almuerzo;
    Boolean comida;
}
