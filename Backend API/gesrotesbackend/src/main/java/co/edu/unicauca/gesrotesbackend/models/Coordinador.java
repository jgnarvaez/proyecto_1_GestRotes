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
public class Coordinador implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "coo_id")
    private int id;

    @Column(name = "coo_nombres")
    private String nombres;

    @Column(name = "coo_apellidos")
    private String apellidos;

    @Column(name = "coo_correo")
    private String correo;

    @Column(name = "coo_clave")
    private String clave;

    @Column(name = "coo_rol")
    private String rol;

    @Column(name = "coo_foto_perfil")
    private String fotoPerfil;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Coordinador)) return false;
        Coordinador that = (Coordinador) o;
        return Objects.equals(getNombres(), that.getNombres()) &&
                Objects.equals(getApellidos(), that.getApellidos()) &&
                Objects.equals(getCorreo(), that.getCorreo())&&
                Objects.equals(getClave(), that.getClave()) &&
                Objects.equals(getRol(), that.getRol())&&
                Objects.equals(getFotoPerfil(), that.getFotoPerfil());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getNombres(), getApellidos(), getCorreo(), getClave(), getRol(), getFotoPerfil());
    }
}
