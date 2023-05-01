package co.edu.unicauca.gesrotesbackend.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Time;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "tbl_jornada")
public class Jornada {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "jor_id")
    private int id;

    @Column(name = "jor_franja")
    private String franja;

    @Column(name = "jor_hora_inicio")
    private Time horaInicio;

    @Column(name = "jor_hora_fin")
    private Time horaFin;
}
