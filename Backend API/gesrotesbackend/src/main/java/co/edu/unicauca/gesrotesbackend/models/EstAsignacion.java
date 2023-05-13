package co.edu.unicauca.gesrotesbackend.models;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tbl_est_asignacion")
public class EstAsignacion {
    @EmbeddedId
    private EstAsignacionId id;

    @Column(name = "est_asig_seleccionado")
    private Boolean seleccionado;

    // @Enumerated(EnumType.STRING)
    // @Column(name = "est_asig_mes")
    // private Mes mes;

    // @Column(name = "est_asig_anio")
    // private int anio;
}
