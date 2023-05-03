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
public class PersonaUniversitaria implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pu_id")
    private int id;

    @Column(name = "pu_nombres")
    private String nombres;

    @Column(name = "pu_apellidos")
    private String apellidos;

    @Column(name = "pu_identificacion")
    private Long identificacion;

    @Column(name = "pu_usuario")
    private String usuario;

    @Column(name = "pu_foto_perfil")
    private String fotoPerfil;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PersonaUniversitaria)) return false;
        PersonaUniversitaria that = (PersonaUniversitaria) o;
        return Objects.equals(getNombres(), that.getNombres()) &&
                Objects.equals(getApellidos(), that.getApellidos()) &&
                Objects.equals(getIdentificacion(), that.getIdentificacion()) &&
                Objects.equals(getUsuario(), that.getUsuario())&&
                Objects.equals(getFotoPerfil(), that.getFotoPerfil());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getNombres(), getApellidos(), getIdentificacion(), getUsuario(), getFotoPerfil());
    }
}