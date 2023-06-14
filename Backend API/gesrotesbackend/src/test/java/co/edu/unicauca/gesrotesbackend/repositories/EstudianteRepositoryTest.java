package co.edu.unicauca.gesrotesbackend.repositories;

import static org.assertj.core.api.Assertions.assertThat;

import co.edu.unicauca.gesrotesbackend.models.*;
import co.edu.unicauca.gesrotesbackend.services.DTO.EstudianteDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

@DataJpaTest
public class EstudianteRepositoryTest {

    @Autowired
    private EstudianteRepository underTest;

    @Autowired
    private EstAsignacionRepository estAsignacionRepository;

    @Autowired
    private AsignacionRepository asignacionRepository;

    @Autowired
    private ProgramaRepository programaRepository;

    @Autowired
    private CoordinadorAsigRepository coordinadorAsigRepository;

    @Autowired
    private AsignaturaRepository asignaturaRepository;

    @BeforeEach
    void setUp(){
        //* Programas
        Programa programa = new Programa(1, "Enfermería");

        programaRepository.save(programa);

        //* Coordinadores
        CoordinadorAsignatura coordinadorAsignatura = new CoordinadorAsignatura();
        coordinadorAsignatura.setId(1);
        coordinadorAsignatura.setNombres("Jorge Ivan");
        coordinadorAsignatura.setApellidos("Solano");
        coordinadorAsignatura.setCorreo("jsolano");
        coordinadorAsignatura.setClave("123");
        coordinadorAsignatura.setRol("Coordinador_de_asignatura");
        coordinadorAsignatura.setFotoPerfil("url foto");

        coordinadorAsigRepository.save(coordinadorAsignatura);

        //Asignaturas
        Asignatura asignaturaUno = new Asignatura(1, "Cuidado de Enfermería en salud comunitaria y familiar");
        Asignatura asignaturaDos = new Asignatura(2, "Cuidado a las personas con Procesos infecciosos");
        Asignatura asignaturaTres = new Asignatura(3, "Cuidado de Enfermería en Salud Mental");
        Asignatura asignaturaCuatro = new Asignatura(4, "Cuidado de Enfermería al Adulto y al Anciano I");
        Asignatura asignaturaCinco = new Asignatura(5, "Cuidado de Enfermería al Adulto y al Anciano II");
        Asignatura asignaturaSeis = new Asignatura(6, "Fundamentos para el Cuidado de Enfermería");
        Asignatura asignaturaSiete = new Asignatura(7, "Cuidado al Niño y al Adolescente");

        asignaturaRepository.save(asignaturaUno);
        asignaturaRepository.save(asignaturaDos);
        asignaturaRepository.save(asignaturaTres);
        asignaturaRepository.save(asignaturaCuatro);
        asignaturaRepository.save(asignaturaCinco);
        asignaturaRepository.save(asignaturaSeis);
        asignaturaRepository.save(asignaturaSiete);

        //* AsignacionesIDs para coordinador 1
        AsignacionId idUno = new AsignacionId(programa, asignaturaUno, coordinadorAsignatura);
        AsignacionId idDos = new AsignacionId(programa, asignaturaDos, coordinadorAsignatura);
        AsignacionId idTres = new AsignacionId(programa, asignaturaTres, coordinadorAsignatura);
        AsignacionId idCuatro = new AsignacionId(programa, asignaturaCuatro, coordinadorAsignatura);
        AsignacionId idCinco = new AsignacionId(programa, asignaturaCinco, coordinadorAsignatura);
        AsignacionId idSeis = new AsignacionId(programa, asignaturaSeis, coordinadorAsignatura);
        AsignacionId idSiete = new AsignacionId(programa, asignaturaSiete, coordinadorAsignatura);

        //Asignaciones
        Asignacion asignacionUno = new Asignacion(idUno);
        Asignacion asignacionDos = new Asignacion(idDos);
        Asignacion asignacionTres = new Asignacion(idTres);
        Asignacion asignacionCuatro = new Asignacion(idCuatro);
        Asignacion asignacionCinco = new Asignacion(idCinco);
        Asignacion asignacionSeis = new Asignacion(idSeis);
        Asignacion asignacionSiete = new Asignacion(idSiete);
        asignacionRepository.save(asignacionUno);
        asignacionRepository.save(asignacionDos);
        asignacionRepository.save(asignacionTres);
        asignacionRepository.save(asignacionCuatro);
        asignacionRepository.save(asignacionCinco);
        asignacionRepository.save(asignacionSeis);
        asignacionRepository.save(asignacionSiete);

        // Estudiantes
        Estudiante estudianteUno = new Estudiante();
        estudianteUno.setNombres("Cristian");
        estudianteUno.setApellidos("Gomez Santos");
        estudianteUno.setIdentificacion(106813022001L);
        estudianteUno.setUsuario("cgomezs");
        estudianteUno.setFotoPerfil("url foto");
        Estudiante estudianteDos = new Estudiante();
        estudianteDos.setNombres("Cristobal");
        estudianteDos.setApellidos("Colon Lopez");
        estudianteDos.setIdentificacion(106813022002L);
        estudianteDos.setUsuario("ccolonl");
        estudianteDos.setFotoPerfil("url foto");
        Estudiante estudianteTres = new Estudiante();
        estudianteTres.setNombres("Maria");
        estudianteTres.setApellidos("Paz Bolaños");
        estudianteTres.setIdentificacion(106813022003L);
        estudianteTres.setUsuario("mpazb");
        estudianteTres.setFotoPerfil("url foto");
        Estudiante estudianteCuatro = new Estudiante();
        estudianteCuatro.setNombres("Maria");
        estudianteCuatro.setApellidos("Paz Bolaños");
        estudianteCuatro.setIdentificacion(106813022003L);
        estudianteCuatro.setUsuario("mpazb");
        estudianteCuatro.setFotoPerfil("url foto");
        underTest.save(estudianteUno);
        underTest.save(estudianteDos);
        underTest.save(estudianteTres);

        EstAsignacionId estAsignacionIdUno = new EstAsignacionId(estudianteUno, asignacionTres);
        EstAsignacionId estAsignacionIdDos = new EstAsignacionId(estudianteDos, asignacionTres);

        EstAsignacion estAsignacionUno = new EstAsignacion(estAsignacionIdUno, false);
        EstAsignacion estAsignacionDos = new EstAsignacion(estAsignacionIdDos, false);
        estAsignacionRepository.save(estAsignacionUno);
        estAsignacionRepository.save(estAsignacionDos);
        estAsignacionRepository.save(estAsignacionDos);
    }

    @Test
    void shouldGetStudentsInfo() {
        // given
        int asigId = 3;
        int progId = 1;
        int cooId = 1;

        // when
        List<EstudianteDTO> estudianteDTOList = underTest.getStudentsInfo(asigId, progId, cooId);

        // then
        assertThat(estudianteDTOList).hasSize(2);
        assertThat(estudianteDTOList.get(0).id()).isEqualTo(1);
        assertThat(estudianteDTOList.get(0).nombreCompleto()).isEqualTo("Cristian Gomez Santos");
        assertThat(estudianteDTOList.get(0).identificacion()).isEqualTo(106813022001L);
        assertThat(estudianteDTOList.get(0).usuario()).isEqualTo("cgomezs");
        assertThat(estudianteDTOList.get(1).id()).isEqualTo(2);
        assertThat(estudianteDTOList.get(1).nombreCompleto()).isEqualTo("Cristobal Colon Lopez");
        assertThat(estudianteDTOList.get(1).identificacion()).isEqualTo(106813022002L);
        assertThat(estudianteDTOList.get(1).usuario()).isEqualTo("ccolonl");
    }

    @Test
    void shouldGetStudentsInfoByName() {
        // given
        String cadenaBusqueda = "Ma";
        int asigId = 3;
        int progId = 1;
        int cooId = 1;

        // when
        List<EstudianteDTO> estudianteDTOList = underTest.getStudentsInfoByName(cadenaBusqueda, asigId, progId, cooId);

        // then
        assertThat(estudianteDTOList).hasSize(1);
        assertThat(estudianteDTOList.get(0).id()).isEqualTo(3);
        assertThat(estudianteDTOList.get(0).nombreCompleto()).isEqualTo("Maria Paz Bolaños");
        assertThat(estudianteDTOList.get(0).identificacion()).isEqualTo(106813022003L);
        assertThat(estudianteDTOList.get(0).usuario()).isEqualTo("mpazb");
    }
}
