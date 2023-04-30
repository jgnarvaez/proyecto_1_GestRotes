package co.edu.unicauca.gesrotesbackend.models;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;;
import jakarta.persistence.Table;
import jakarta.persistence.Column;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tbl_matricula")
public class EnrollmentEntity {
    @EmbeddedId
    private EnrollmentId id;

    @Column(name = "mat_estado")
    private EnrState state;
}
