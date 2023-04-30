package co.edu.unicauca.gesrotesbackend.services.DTO;

import co.edu.unicauca.gesrotesbackend.models.StuAsgState;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class StuAssignmentDTO {
    int cpId;
    int progId;
    int subjId;
    int cooId;
    StuAsgState state;
}
