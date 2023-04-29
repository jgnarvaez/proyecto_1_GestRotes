package co.edu.unicauca.gesrotesapi.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class CoordinadorEntity {
    
    private int code;
    private String name;
    private long initValue;
    
    public CoordinadorEntity() {
    }
}