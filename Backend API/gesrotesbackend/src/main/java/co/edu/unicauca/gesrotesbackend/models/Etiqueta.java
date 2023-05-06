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
@Table(name = "tbl_etiqueta")
public class Etiqueta implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "eti_id")
    private int id;

    @Column(name = "eti_nombre")
    private String nombre;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "esc_id")
    private EscenarioPractica escenario;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ser_id")
    private Servicio servicio;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Etiqueta etiqueta = (Etiqueta) o;
        return id == etiqueta.id &&
                Objects.equals(nombre, etiqueta.nombre) &&
                Objects.equals(escenario, etiqueta.escenario) &&
                Objects.equals(servicio, etiqueta.servicio);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nombre, escenario, servicio);
    }

}
