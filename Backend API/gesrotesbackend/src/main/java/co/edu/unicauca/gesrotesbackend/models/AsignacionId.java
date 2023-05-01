package co.edu.unicauca.gesrotesbackend.models;

import jakarta.persistence.Embeddable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
@Embeddable
public class AsignacionId implements Serializable {

    @ManyToOne
    @JoinColumn(name = "prog_id")
    private Programa programa;

    @ManyToOne
    @JoinColumn(name = "asig_id")
    private Asignatura asignatura;

    @ManyToOne
    @JoinColumn(name = "coo_id")
    private CoordinadorAsignatura coordinador;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AsignacionId)) return false;
        AsignacionId that = (AsignacionId) o;
        return Objects.equals(getPrograma(), that.getPrograma()) &&
                Objects.equals(getAsignatura(), that.getAsignatura()) &&
                Objects.equals(getCoordinador(), that.getCoordinador());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getPrograma(), getAsignatura(), getCoordinador());
    }
}
