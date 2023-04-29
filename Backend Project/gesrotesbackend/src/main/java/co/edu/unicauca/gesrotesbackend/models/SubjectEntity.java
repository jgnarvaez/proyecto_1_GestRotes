package co.edu.unicauca.gesrotesbackend.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@Entity
@Table(name = "tbl_asignatura")
public class SubjectEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "asig_id")
    private int id;

    @Column(name = "asig_nombre")
    private String name;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "coo_id")
    private SubCoordinatorEntity coordinator;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "prog_id")
    private ProgramEntity program;

    public SubjectEntity(){}
}
