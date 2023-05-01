package co.edu.unicauca.gesrotesbackend.models;

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
@Table(name = "tbl_turno_est_asig")
public class TurEstAsignacion {
    @EmbeddedId
    private TurEstAsignacionId id;
}
