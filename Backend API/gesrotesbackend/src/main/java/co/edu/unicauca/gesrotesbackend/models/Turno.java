package co.edu.unicauca.gesrotesbackend.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.sql.Date;
import java.util.Objects;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "tbl_turno")
public class Turno implements Serializable {
    @EmbeddedId
    private TurnoId id;

    @Column(name = "tur_fecha")
    private Date fecha;

    @Enumerated(EnumType.STRING)
    @Column(name = "tur_alimentacion")
    private TipoAlimentacion alimentacion;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "jor_id")
    private Jornada jornada;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "eti_id")
    private Etiqueta etiqueta;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Turno)) return false;
        Turno that = (Turno) o;
        return Objects.equals(getFecha(), that.getFecha()) &&
                Objects.equals(getAlimentacion(), that.getAlimentacion()) &&
                Objects.equals(getJornada(), that.getJornada())&&
                Objects.equals(getEtiqueta(), that.getEtiqueta());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getFecha(), getAlimentacion(), getJornada(), getEtiqueta());
    }
}
