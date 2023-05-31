package co.edu.unicauca.gesrotesbackend.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Objects;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "tbl_validacion_turnos")
public class ValidacionTurnos implements Serializable {
    @EmbeddedId
    private ValidacionTurnosId id;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "vtu_mes")
    private Mes mes;

    @Column(name = "vtu_anio")
    private int anio;

    @Column(name = "vtu_asistencia")
    private Boolean asistencia;

    @Column(name = "vtu_estado")
    private Boolean estado;

    @Column(name = "vtu_observaciones")
    private String observaciones;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ValidacionTurnos)) return false;
        ValidacionTurnos that = (ValidacionTurnos) o;
        return Objects.equals(getMes(), that.getMes()) &&
                Objects.equals(getAnio(), that.getAnio()) &&
                Objects.equals(getAsistencia(), that.getAsistencia())&&
                Objects.equals(getEstado(), that.getEstado())&&
                Objects.equals(getObservaciones(), that.getObservaciones());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getMes(), getAnio(), getAsistencia(), getEstado(), getObservaciones());
    }
}
