package co.edu.unicauca.gesrotesbackend.repositories;

import static org.assertj.core.api.Assertions.assertThat;

import co.edu.unicauca.gesrotesbackend.models.*;
import co.edu.unicauca.gesrotesbackend.services.DTO.EstudianteSeleccionadoDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

@DataJpaTest
public class EstAsignacionRepositoryTest {

    @Autowired
    private EstAsignacionRepository underTest;

    @Autowired
    private EstudianteRepository estudianteRepository;

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
        estudianteRepository.save(estudianteUno);
        estudianteRepository.save(estudianteDos);

        EstAsignacionId estAsignacionIdUno = new EstAsignacionId(estudianteUno, asignacionTres);
        EstAsignacionId estAsignacionIdDos = new EstAsignacionId(estudianteDos, asignacionTres);

        EstAsignacion estAsignacionUno = new EstAsignacion(estAsignacionIdUno, false);
        EstAsignacion estAsignacionDos = new EstAsignacion(estAsignacionIdDos, false);
        underTest.save(estAsignacionUno);
        underTest.save(estAsignacionDos);
    }

    @Test
    void shouldGetRowByIds() {
        // given
        int estId = 1;
        int progId = 1;
        int asigId = 3;
        int cooId = 1;

        // when
        EstAsignacion estAsignacion = underTest.getRowByIds(estId, progId, asigId, cooId);

        // then
        assertThat(estAsignacion.getSeleccionado()).isEqualTo(false);
        assertThat(estAsignacion.getId().getEstudiante().getId()).isEqualTo(estId);
        assertThat(estAsignacion.getId().getAsignacion().getId().getPrograma().getId()).isEqualTo(progId);
        assertThat(estAsignacion.getId().getAsignacion().getId().getAsignatura().getId()).isEqualTo(asigId);
        assertThat(estAsignacion.getId().getAsignacion().getId().getCoordinador().getId()).isEqualTo(cooId);
    }

    @Test
    void shouldDeleteStudents() {
        // given
        int progId = 1;
        int asigId = 3;
        int cooId = 1;

        // when
        underTest.deleteAllStudents(progId, asigId, cooId);
        EstAsignacion estAsignacionUno = underTest.getRowByIds(1, progId, asigId, cooId);
        EstAsignacion estAsignacionDos = underTest.getRowByIds(2, progId, asigId, cooId);
        List<EstAsignacion> estAsignacionList = underTest.findAll();

        // then
        assertThat(estAsignacionList).hasSize(0);
        assertThat(estAsignacionUno).isNull();
        assertThat(estAsignacionDos).isNull();
    }

    @Test
    void shouldDeleteStudent() {
        // given
        int progId = 1;
        int asigId = 3;
        int cooId = 1;
        int estId = 1;

        // when
        underTest.deleteStudent(progId, asigId, cooId, estId);
        EstAsignacion estAsignacionUno = underTest.getRowByIds(1, progId, asigId, cooId);
        EstAsignacion estAsignacionDos = underTest.getRowByIds(2, progId, asigId, cooId);
        List<EstAsignacion> estAsignacionList = underTest.findAll();

        // then
        assertThat(estAsignacionList).hasSize(1);
        assertThat(estAsignacionUno).isNull();
        assertThat(estAsignacionDos).isNotNull();
    }

    /*
    @Test
    void shouldModifyStateStudentToTrue() {
        // given
        Boolean estado = true;
        int estId = 1;
        int progId = 1;
        int asigId = 3;
        int cooId = 1;

        // when
        underTest.modifyStateStudent(estado, estId, progId, asigId, cooId);
        EstAsignacion estAsignacion = underTest.getRowByIds(estId, progId, asigId, cooId);

        assertThat(estAsignacion.getSeleccionado()).isEqualTo(estado);
        assertThat(estAsignacion.getId().getEstudiante().getId()).isEqualTo(estId);
        assertThat(estAsignacion.getId().getAsignacion().getId().getPrograma().getId()).isEqualTo(progId);
        assertThat(estAsignacion.getId().getAsignacion().getId().getAsignatura().getId()).isEqualTo(asigId);
        assertThat(estAsignacion.getId().getAsignacion().getId().getCoordinador().getId()).isEqualTo(cooId);
    }*/

    @Test
    void shouldGetSelectedStudents() {
        // given
        int progId = 1;
        int asigId = 3;
        int cooId = 1;

        // when
        underTest.modifyStateStudent(true, 1, progId, asigId, cooId);
        List<EstudianteSeleccionadoDTO> estudianteSeleccionadoDTOList = underTest.getSelectedStudents(progId, asigId, cooId);

        // then
        assertThat(estudianteSeleccionadoDTOList).hasSize(1);
    }

    @Test
    void shouldDeselectStudents() {
        // given
        int progId = 1;
        int asigId = 3;
        int cooId = 1;

        // when
        underTest.modifyStateStudent(true, 1, progId, asigId, cooId);
        underTest.modifyStateStudent(true, 2, progId, asigId, cooId);
        underTest.deselectStudents(progId, asigId, cooId);
        List<EstudianteSeleccionadoDTO> estudianteSeleccionadoDTOList = underTest.getSelectedStudents(progId, asigId, cooId);

        // then
        assertThat(estudianteSeleccionadoDTOList).hasSize(0);
    }
}
