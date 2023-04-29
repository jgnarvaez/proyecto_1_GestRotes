package co.edu.unicauca.gesrotesbackend.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "tbl_coordinador")
public class CoordinatorEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "coo_id")
    private int id;

    @Column(name = "coo_nombres")
    private String names;

    @Column(name = "coo_apellidos")
    private String lastnames;

    @Column(name = "coo_correo")
    private String email;

    @Column(name = "coo_clave")
    private String password;

    @Column(name = "coo_rol")
    private String role;

    @Column(name = "coo_foto_perfil")
    private String profilePicture;

    public CoordinatorEntity(){}
}
