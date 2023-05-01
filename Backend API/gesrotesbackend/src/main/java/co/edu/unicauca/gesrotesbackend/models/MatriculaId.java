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
public class MatriculaId implements Serializable {
    @ManyToOne
    @JoinColumn(name = "prog_id")
    private Programa programa;

    @ManyToOne
    @JoinColumn(name = "pu_id")
    private Estudiante estudiante;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MatriculaId)) return false;
        MatriculaId that = (MatriculaId) o;
        return Objects.equals(getPrograma(), that.getPrograma()) &&
                Objects.equals(getEstudiante(), that.getEstudiante());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getPrograma(), getEstudiante());
    }
}
