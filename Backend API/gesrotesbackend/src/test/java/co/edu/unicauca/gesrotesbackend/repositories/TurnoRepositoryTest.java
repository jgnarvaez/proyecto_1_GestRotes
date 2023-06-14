package co.edu.unicauca.gesrotesbackend.repositories;

import static org.assertj.core.api.Assertions.assertThat;

import co.edu.unicauca.gesrotesbackend.models.*;
import co.edu.unicauca.gesrotesbackend.services.DTO.EstudianteFechaDTO;
import co.edu.unicauca.gesrotesbackend.services.DTO.TurnoAsociadoDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.sql.Date;
import java.sql.Time;
import java.util.List;

@DataJpaTest
public class TurnoRepositoryTest {

    @Autowired
    private TurnoRepository underTest;

    @Autowired
    private JornadaRepository jornadaRepository;

    @Autowired
    private EstAsignacionRepository estAsignacionRepository;

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

    @Autowired
    private EtiquetaRepository etiquetaRepository;

    @Autowired
    private ServicioRepository servicioRepository;

    @Autowired
    private EscenarioRepository escenarioRepository;

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

        EstAsignacion estAsignacionUno = new EstAsignacion(estAsignacionIdUno, true);
        EstAsignacion estAsignacionDos = new EstAsignacion(estAsignacionIdDos, false);
        estAsignacionRepository.save(estAsignacionUno);
        estAsignacionRepository.save(estAsignacionDos);

        EscenarioPractica escenarioPracticaUno = new EscenarioPractica();
        escenarioPracticaUno.setNombre("Hospital San José E.S.E");
        EscenarioPractica escenarioPracticaDos = new EscenarioPractica();
        escenarioPracticaDos.setNombre("Hospital Susana Lopez de Valencia");
        EscenarioPractica escenarioPracticaTres = new EscenarioPractica();
        escenarioPracticaDos.setNombre("Clínica la Estancia");
        escenarioRepository.save(escenarioPracticaUno);
        escenarioRepository.save(escenarioPracticaDos);
        escenarioRepository.save(escenarioPracticaTres);

        Servicio servicioUno = new Servicio();
        servicioUno.setNombre("Cardiología");
        Servicio servicioDos = new Servicio();
        servicioDos.setNombre("Salud Mental");
        Servicio servicioTres = new Servicio();
        servicioTres.setNombre("Pediatría");
        Servicio servicioCuatro = new Servicio();
        servicioCuatro.setNombre("Urgencias");
        servicioRepository.save(servicioUno);
        servicioRepository.save(servicioDos);
        servicioRepository.save(servicioTres);
        servicioRepository.save(servicioCuatro);

        Etiqueta etiquetaUno = new Etiqueta(1, "Salas", escenarioPracticaUno, servicioDos);

        Etiqueta etiquetaDos = new Etiqueta(2, "Primer piso", escenarioPracticaDos, servicioUno);

        etiquetaRepository.save(etiquetaUno);
        etiquetaRepository.save(etiquetaDos);

        Jornada jornadaUno = new Jornada(1, "Mañana", Time.valueOf("06:30:00"), Time.valueOf("11:30:00"));
        Jornada jornadaDos = new Jornada(2, "Tarde", Time.valueOf("12:30:00"), Time.valueOf("16:00:00"));
        Jornada jornadaTres = new Jornada(3, "Noche", Time.valueOf("18:00:00"), Time.valueOf("21:30:00"));

        jornadaRepository.save(jornadaUno);
        jornadaRepository.save(jornadaDos);
        jornadaRepository.save(jornadaTres);

        TurnoId turnoIdUno = new TurnoId(1, estAsignacionUno);
        Turno turnoUno = new Turno(turnoIdUno, Date.valueOf("2023-06-15"), TipoAlimentacion.Desayuno, jornadaUno, etiquetaUno);
        TurnoId turnoIdDos = new TurnoId(2, estAsignacionDos);
        Turno turnoDos = new Turno(turnoIdDos, Date.valueOf("2023-06-15"), TipoAlimentacion.Almuerzo, jornadaDos, etiquetaUno);
        TurnoId turnoIdTres = new TurnoId(3, estAsignacionDos);
        Turno turnoTres = new Turno(turnoIdTres, Date.valueOf("2023-06-15"), TipoAlimentacion.Comida, jornadaTres, etiquetaUno);
        TurnoId turnoIdCuatro = new TurnoId(4, estAsignacionUno);
        Turno turnoCuatro = new Turno(turnoIdCuatro, Date.valueOf("2023-06-16"), TipoAlimentacion.Desayuno, jornadaUno, etiquetaUno);
        underTest.save(turnoUno);
        underTest.save(turnoDos);
        underTest.save(turnoTres);
        underTest.save(turnoCuatro);
    }

    @Test
    void shouldFindShiftsByStudentId() {
        // given
        int estId = 1;
        Date fechaTurno = Date.valueOf("2023-06-15");

        // when
        List<Turno> turnos = underTest.findShiftsByStudentId(estId, fechaTurno);

        // then
        assertThat(turnos).hasSize(1);
    }

    @Test
    void shouldFindShiftsAssociationsByDate() {
        // given
        int estId = 2;
        Date fechaTurno = Date.valueOf("2023-06-15");

        // when
        List<TurnoAsociadoDTO> turnoAsociadoDTOList = underTest.findShiftsAssociationsByDate(estId, fechaTurno);

        // then
        assertThat(turnoAsociadoDTOList).hasSize(2);
    }

    @Test
    void shouldFindAllStudentsIdByDate() {
        // given
        Date fechaTurno = Date.valueOf("2023-06-15");
        int progId = 1;
        int asigId = 3;
        int cooId = 1;

        // when
        List<Integer> estudiantesIdList = underTest.findAllStudentsIdByDate(fechaTurno, progId, asigId, cooId);

        // then
        assertThat(estudiantesIdList).hasSize(3);
    }

    @Test
    void shouldFindDifferentSchedules() {
        // given
        int progId = 1;
        int asigId = 3;
        int cooId = 1;

        // when
        List<EstudianteFechaDTO> estudianteFechaDTOList = underTest.findDifferentSchedules(progId, asigId, cooId);

        // then
        assertThat(estudianteFechaDTOList).hasSize(3);
    }

    @Test
    void shouldFindShiftsAssociationsByDate2() {
        // given
        int estId = 2;
        Date fechaTurno = Date.valueOf("2023-06-15");

        // when
        List<TurnoAsociadoDTO> turnoAsociadoDTOList = underTest.findShiftsAssociationsByDate2(estId, fechaTurno);

        // then
        assertThat(turnoAsociadoDTOList).hasSize(2);
    }

    @Test
    void shouldDeleteById() {
        // given
        int turnoId = 1;

        // when
        underTest.myDeletebyid(turnoId);
        List<Turno> turnos = underTest.findAll();

        // then
        assertThat(turnos).hasSize(3);
    }

    @Test
    void shouldVerifyIfAlreadyExistsTrueCase() {
        // given
        Date fechaTurno = Date.valueOf("2023-06-15");
        int estId = 1;
        int progId = 1;
        int asigId = 3;
        int cooId = 1;
        int jorId = 1;
        int etiId = 1;

        // when
        int existeTurno = underTest.alreadyExists(fechaTurno, estId, progId, asigId, cooId, jorId, etiId);

        // then
        assertThat(existeTurno).isGreaterThan(0);
    }

    @Test
    void shouldVerifyIfAlreadyExistsFalseCase() {
        // given
        Date fechaTurno = Date.valueOf("2023-06-16");
        int estId = 2;
        int progId = 1;
        int asigId = 3;
        int cooId = 1;
        int jorId = 1;
        int etiId = 1;

        // when
        int existeTurno = underTest.alreadyExists(fechaTurno, estId, progId, asigId, cooId, jorId, etiId);

        // then
        assertThat(existeTurno).isEqualTo(0);
    }

    @Test
    void shouldUpdateShift() {
        // given
        int turnoId = 1;
        int jorId = 2;
        int etiId = 2;

        // when
        int fueActualizado = underTest.updateShift(turnoId, jorId, etiId);

        // then
        assertThat(fueActualizado).isEqualTo(1);
    }
}
