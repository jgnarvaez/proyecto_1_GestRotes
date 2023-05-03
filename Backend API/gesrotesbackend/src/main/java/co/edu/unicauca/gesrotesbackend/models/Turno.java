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
@Table(name = "tbl_truno")
public class Turno implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tur_id")
    private int id;

    @Column(name = "tur_fecha")
    private Date fecha;

    @Column(name = "tur_desayuno")
    private Boolean desayuno;

    @Column(name = "tur_almuerzo")
    private Boolean almuerzo;

    @Column(name = "tur_comida")
    private Boolean comida;

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
                Objects.equals(getDesayuno(), that.getDesayuno()) &&
                Objects.equals(getAlmuerzo(), that.getAlmuerzo())&&
                Objects.equals(getComida(), that.getComida()) &&
                Objects.equals(getJornada(), that.getJornada())&&
                Objects.equals(getEtiqueta(), that.getEtiqueta());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getFecha(), getDesayuno(), getAlmuerzo(), getComida(), getJornada(), getEtiqueta());
    }
}
