package co.edu.unicauca.gesrotesbackend.models;

import jakarta.persistence.Embeddable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Objects;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Embeddable
public class StuAssignmentId {
    @ManyToOne
    @JoinColumn(name = "pu_id")
    private StudentEntity student;

    @ManyToOne
    @JoinColumn(name = "prog_id")
    private ProgramEntity program;

    @ManyToOne
    @JoinColumn(name = "asig_id")
    private SubjectEntity subject;

    @ManyToOne
    @JoinColumn(name = "coo_id")
    private SubCoordinatorEntity coordinator;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof StuAssignmentId)) return false;
        StuAssignmentId that = (StuAssignmentId) o;
        return Objects.equals(getStudent(), that.getStudent()) &&
                Objects.equals(getProgram(), that.getProgram()) &&
                Objects.equals(getSubject(), that.getSubject()) &&
                Objects.equals(getCoordinator(), that.getCoordinator());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getStudent(), getProgram(), getSubject(), getCoordinator());
    }
}
