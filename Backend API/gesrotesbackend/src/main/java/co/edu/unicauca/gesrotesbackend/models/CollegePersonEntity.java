package co.edu.unicauca.gesrotesbackend.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.Table;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Column;

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
@Table(name = "tbl_persona_universitaria")
public class CollegePersonEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pu_id")
    private int id;

    @Column(name = "pu_nombres")
    private String names;

    @Column(name = "pu_apellidos")
    private String lastnames;

    @Column(name = "pu_identificacion")
    private Long identificationCode;

    @Column(name = "pu_usuario")
    private String username;

    @Column(name = "pu_foto_perfil")
    private String profilePicture;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CollegePersonEntity)) return false;
        CollegePersonEntity that = (CollegePersonEntity) o;
        return Objects.equals(getNames(), that.getNames()) &&
                Objects.equals(getLastnames(), that.getLastnames()) &&
                Objects.equals(getIdentificationCode(), that.getIdentificationCode()) &&
                Objects.equals(getUsername(), that.getUsername())&&
                Objects.equals(getProfilePicture(), that.getProfilePicture());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getNames(), getLastnames(), getIdentificationCode(), getUsername(), getProfilePicture());
    }
}