package co.edu.unicauca.gesrotesbackend.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "tbl_coordinador")
public class CoordinatorEntity implements Serializable {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CoordinatorEntity)) return false;
        CoordinatorEntity that = (CoordinatorEntity) o;
        return Objects.equals(getNames(), that.getNames()) &&
                Objects.equals(getLastnames(), that.getLastnames()) &&
                Objects.equals(getEmail(), that.getEmail())&&
                Objects.equals(getPassword(), that.getPassword()) &&
                Objects.equals(getRole(), that.getRole())&&
                Objects.equals(getProfilePicture(), that.getProfilePicture());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getNames(), getLastnames(), getEmail(), getPassword(), getRole(), getProfilePicture());
    }
}
