package co.edu.unicauca.gesrotesbackend.models;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinColumns;
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
public class ValidacionTurnosId implements Serializable {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "vtu_id")
    private int id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumns({
        @JoinColumn(name = "pu_id", referencedColumnName = "pu_id"),
        @JoinColumn(name = "prog_id", referencedColumnName = "prog_id"),
        @JoinColumn(name = "asig_id", referencedColumnName = "asig_id"),
        @JoinColumn(name = "coo_id", referencedColumnName = "coo_id")
    })
    EstAsignacion estAsignacion;

    @Override
    public boolean equals(Object o) {
        if (o == this) return true;
        if (!(o instanceof ValidacionTurnosId)) return false;
        ValidacionTurnosId other = (ValidacionTurnosId) o;
        return Objects.equals(id, other.id) &&
                Objects.equals(estAsignacion, other.estAsignacion);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, estAsignacion);
    }
}
