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
public class EnrollmentId implements Serializable {
    @ManyToOne
    @JoinColumn(name = "prog_id")
    private ProgramEntity program;

    @ManyToOne
    @JoinColumn(name = "pu_id")
    private StudentEntity student;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof EnrollmentId)) return false;
        EnrollmentId that = (EnrollmentId) o;
        return Objects.equals(getProgram(), that.getProgram()) &&
                Objects.equals(getStudent(), that.getStudent());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getProgram(), getStudent());
    }
}
