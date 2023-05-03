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
public class EstAsignacionId implements Serializable {
    @ManyToOne
    @JoinColumn(name = "pu_id")
    private Estudiante estudiante;

    @ManyToOne
    @JoinColumn(name = "prog_id")
    private Programa programa;

    @ManyToOne
    @JoinColumn(name = "asig_id")
    private Asignatura asignatura;

    @ManyToOne
    @JoinColumn(name = "coo_id")
    private CoordinadorAsignatura coordinator;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof EstAsignacionId)) return false;
        EstAsignacionId that = (EstAsignacionId) o;
        return Objects.equals(getEstudiante(), that.getEstudiante()) &&
                Objects.equals(getPrograma(), that.getPrograma()) &&
                Objects.equals(getAsignatura(), that.getAsignatura()) &&
                Objects.equals(getCoordinator(), that.getCoordinator());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getEstudiante(), getPrograma(), getAsignatura(), getCoordinator());
    }
}
