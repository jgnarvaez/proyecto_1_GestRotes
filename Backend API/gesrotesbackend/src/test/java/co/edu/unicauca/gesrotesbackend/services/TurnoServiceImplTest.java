package co.edu.unicauca.gesrotesbackend.services;

import java.sql.Date;
import java.sql.Time;

// import static org.mockito.Mockito.RETURNS_MOCKS;
// import static org.mockito.Mockito.doNothing;
import static org.mockito.ArgumentMatchers.*;

import java.util.Optional;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.Assertions.assertThat;
// import static org.mockito.Answers.RETURNS_DEFAULTS;
// import static org.mockito.ArgumentMatchers.any;
// import static org.mockito.ArgumentMatchers.anyList;
// import static org.mockito.ArgumentMatchers.anyLong;
// import static org.mockito.ArgumentMatchers.notNull;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;
// import static org.mockito.Mockito.when;
// import static org.mockito.BDDMockito.when;

import co.edu.unicauca.gesrotesbackend.exceptions.HTTPException;
import co.edu.unicauca.gesrotesbackend.services.DTO.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
// import org.mockito.internal.matchers.InstanceOf;
import org.mockito.junit.jupiter.MockitoExtension;
// import org.springframework.test.context.jdbc.Sql;

import co.edu.unicauca.gesrotesbackend.models.Jornada;
import co.edu.unicauca.gesrotesbackend.models.Programa;
import co.edu.unicauca.gesrotesbackend.models.Servicio;
import co.edu.unicauca.gesrotesbackend.models.TipoAlimentacion;
import co.edu.unicauca.gesrotesbackend.models.Etiqueta;
import co.edu.unicauca.gesrotesbackend.models.Turno;
import co.edu.unicauca.gesrotesbackend.models.TurnoId;
import co.edu.unicauca.gesrotesbackend.models.Asignacion;
import co.edu.unicauca.gesrotesbackend.models.AsignacionId;
import co.edu.unicauca.gesrotesbackend.models.Asignatura;
import co.edu.unicauca.gesrotesbackend.models.CoordinadorAsignatura;
import co.edu.unicauca.gesrotesbackend.models.EscenarioPractica;
import co.edu.unicauca.gesrotesbackend.models.EstAsignacion;
import co.edu.unicauca.gesrotesbackend.models.EstAsignacionId;
import co.edu.unicauca.gesrotesbackend.models.Estudiante;
import co.edu.unicauca.gesrotesbackend.repositories.EstAsignacionRepository;
import co.edu.unicauca.gesrotesbackend.repositories.EtiquetaRepository;
import co.edu.unicauca.gesrotesbackend.repositories.JornadaRepository;
import co.edu.unicauca.gesrotesbackend.repositories.TurnoRepository;
import co.edu.unicauca.gesrotesbackend.services.Mapper.JornadaDTOMapper;
import co.edu.unicauca.gesrotesbackend.services.Utilities.HorarioJornada;
import co.edu.unicauca.gesrotesbackend.services.services.Impl.TurnoServiceImpl;
import org.springframework.dao.DataAccessException;

@ExtendWith(MockitoExtension.class)
public class TurnoServiceImplTest {
    @Mock
    private JornadaRepository jornadaRepository;
    @Mock
    private EtiquetaRepository etiquetaRepository;
    @Mock
    private TurnoRepository turnoRepository;
    @Mock
    private EstAsignacionRepository estAsignacionRepository;
    @Mock
    private JornadaDTOMapper jornadaDTOMapper;

    private TurnoServiceImpl serviceUnderTest;

    @BeforeEach
    void setUp() {
        serviceUnderTest = new TurnoServiceImpl(jornadaRepository, 
                                                    etiquetaRepository, 
                                                    turnoRepository, 
                                                    estAsignacionRepository, 
                                                    jornadaDTOMapper);
    }

    @Test
    void test_Can_ChangeStudentSelectedState() {
        // given
        SeleccionEstudianteDTO seleccionEstudiante = new SeleccionEstudianteDTO(1, 2, 3, 4, true);

        // when
        serviceUnderTest.cambiarEstadoSeleccionado(seleccionEstudiante);

        // then
        verify(estAsignacionRepository).modifyStateStudent(seleccionEstudiante.getEstado(), seleccionEstudiante.getPuId(), 
                                                            seleccionEstudiante.getProgId(), seleccionEstudiante.getAsigId(), 
                                                            seleccionEstudiante.getCooId());
    }

    @Test
    void test_Can_GetSelectedStudents() {
        // given
        int progId = 1;
        int asigId = 2;
        int cooId = 3;

        // when
        serviceUnderTest.obtenerEstudiantesSeleccionados(progId, asigId, cooId);
        
        // then
        verify(estAsignacionRepository).getSelectedStudents(progId, asigId, cooId);
    }

    @Test
    void test_Can_DeselectStudents() {
        // given
        SeleccionEstudiantesDTO seleccionEstudiantes = new SeleccionEstudiantesDTO(1, 2, 3);
        
        // when
        serviceUnderTest.deseleccionarEstudiantes(seleccionEstudiantes);
        
        // then
        verify(estAsignacionRepository).deselectStudents(seleccionEstudiantes.getProgId(), seleccionEstudiantes.getAsigId(), 
                                                            seleccionEstudiantes.getCooId());
    }

    @Test
    void test_Can_GetAllJornadas() {
        //when
        serviceUnderTest.obetenerJornadas();

        // then
        verify(jornadaRepository).findAll();
    }

    /*@Test
    void test_Can_CreateShift() {
        // given
        NuevoTurnoDTO  nuevoTurnoDTO = new NuevoTurnoDTO(Date.valueOf("2023-05-12"), 1, 2, 3, 4, 
                                                5, 6);
        TipoAlimentacion alimentacion = TipoAlimentacion.Desayuno;
        
        Jornada jornada = new Jornada(nuevoTurnoDTO.getIdJornada(), "Mañana", Time.valueOf("06:30:00"), Time.valueOf("11:35:00"));
        
        EscenarioPractica escenario = new EscenarioPractica(1, "Hospital San Jose");
        
        Servicio servicio = new Servicio(1, "Cardiologia");
        
        Etiqueta etiqueta = new Etiqueta(1, "Primer piso", escenario, servicio);
        
        Estudiante estudiante = new Estudiante();
        estudiante.setId(1);
        estudiante.setNombres("Cristian");
        estudiante.setApellidos("Gomez Santos");
        estudiante.setIdentificacion(106813022001L);
        estudiante.setUsuario("cgomezs");
        estudiante.setFotoPerfil("url foto");

        Programa programa = new Programa(1, "Enfermería");
        
        Asignatura asignatura = new Asignatura(1, "Cuidado de Enfermería en salud comunitaria y familiar");
        
        CoordinadorAsignatura coordinadorAsignatura = new CoordinadorAsignatura();
        coordinadorAsignatura.setId(1);
        coordinadorAsignatura.setNombres("Jorge Ivan");
        coordinadorAsignatura.setApellidos("Solano");
        coordinadorAsignatura.setCorreo("jsolano");
        coordinadorAsignatura.setClave("1234");
        coordinadorAsignatura.setFotoPerfil("url foto");
        
        AsignacionId idAsignacion = new AsignacionId(programa, asignatura, coordinadorAsignatura);
        
        Asignacion asignacion = new Asignacion(idAsignacion);
        EstAsignacionId idEstAsignacion = new EstAsignacionId(estudiante, asignacion);
        EstAsignacion estAsignacion = new EstAsignacion(idEstAsignacion, true);
        TurnoId idTurno = new TurnoId();
        idTurno.setEstAsignacion(estAsignacion);
        Turno turno = new Turno(idTurno, nuevoTurnoDTO.getFechaTurno(), alimentacion, jornada, etiqueta);
        given(jornadaRepository.findById(nuevoTurnoDTO.getIdJornada())).willReturn(Optional.of(jornada));
        given(etiquetaRepository.findById(nuevoTurnoDTO.getIdEtiqueta())).willReturn(Optional.of(etiqueta));
        given(estAsignacionRepository.getRowByIds(nuevoTurnoDTO.getIdEstudiante(), nuevoTurnoDTO.getIdPrograma(), 
                                                    nuevoTurnoDTO.getIdAsignatura(), nuevoTurnoDTO.getIdCoordinador()))
                                                    .willReturn(estAsignacion);
        given(turnoRepository.alreadyExists(nuevoTurnoDTO.getFechaTurno(), nuevoTurnoDTO.getIdEstudiante(), 
                                            nuevoTurnoDTO.getIdPrograma(), nuevoTurnoDTO.getIdAsignatura(), 
                                            nuevoTurnoDTO.getIdCoordinador(), nuevoTurnoDTO.getIdJornada(), 
                                            nuevoTurnoDTO.getIdEtiqueta())).willReturn(0);
        given(turnoRepository.save(turno)).willReturn(turno);

        // when
        serviceUnderTest.crearTurno(nuevoTurnoDTO);
        
        // then
        ArgumentCaptor<Turno> turnoArgumentCaptor = ArgumentCaptor.forClass(Turno.class);

        verify(turnoRepository).save(turnoArgumentCaptor.capture());

        Turno turnoCapturado = turnoArgumentCaptor.getValue();

        assertThat(turnoCapturado.getId().getEstAsignacion().getId().getEstudiante())
                .isEqualTo(turno.getId().getEstAsignacion().getId().getEstudiante());
        assertThat(turnoCapturado.getId().getEstAsignacion().getId().getAsignacion())
                .isEqualTo(turno.getId().getEstAsignacion().getId().getAsignacion());
        assertThat(turnoCapturado.getFecha())
                .isEqualTo(turno.getFecha());
        assertThat(turnoCapturado.getAlimentacion())
                .isEqualTo(turno.getAlimentacion());
        assertThat(turnoCapturado.getJornada())
                .isEqualTo(turno.getJornada());
        assertThat(turnoCapturado.getEtiqueta())
                .isEqualTo(turno.getEtiqueta());
    }*/

    @Test
    public void testCrearTurno_RangoDesayuno() {
        // Arrange
        NuevoTurnoDTO  nuevoTurnoDTO = new NuevoTurnoDTO(Date.valueOf("2023-05-12"), 1, 2, 3, 4,
                5, 6);
        TipoAlimentacion alimentacion = TipoAlimentacion.Desayuno;

        Jornada jornada = new Jornada(nuevoTurnoDTO.getIdJornada(), "Mañana", Time.valueOf("06:30:00"), Time.valueOf("11:35:00"));

        EscenarioPractica escenario = new EscenarioPractica(1, "Hospital San Jose");

        Servicio servicio = new Servicio(1, "Cardiologia");

        Etiqueta etiqueta = new Etiqueta(nuevoTurnoDTO.getIdEtiqueta(), "Primer piso", escenario, servicio);

        Estudiante estudiante = new Estudiante();
        estudiante.setId(1);
        estudiante.setNombres("Cristian");
        estudiante.setApellidos("Gomez Santos");
        estudiante.setIdentificacion(106813022001L);
        estudiante.setUsuario("cgomezs");
        estudiante.setFotoPerfil("url foto");

        Programa programa = new Programa(nuevoTurnoDTO.getIdPrograma(), "Enfermería");

        Asignatura asignatura = new Asignatura(nuevoTurnoDTO.getIdAsignatura(), "Cuidado de Enfermería en salud comunitaria y familiar");

        CoordinadorAsignatura coordinadorAsignatura = new CoordinadorAsignatura();
        coordinadorAsignatura.setId(nuevoTurnoDTO.getIdCoordinador());
        coordinadorAsignatura.setNombres("Jorge Ivan");
        coordinadorAsignatura.setApellidos("Solano");
        coordinadorAsignatura.setCorreo("jsolano");
        coordinadorAsignatura.setClave("1234");
        coordinadorAsignatura.setFotoPerfil("url foto");

        AsignacionId idAsignacion = new AsignacionId(programa, asignatura, coordinadorAsignatura);

        Asignacion asignacion = new Asignacion(idAsignacion);
        EstAsignacionId idEstAsignacion = new EstAsignacionId(estudiante, asignacion);
        EstAsignacion estAsignacion = new EstAsignacion(idEstAsignacion, true);
        TurnoId idTurno = new TurnoId();
        idTurno.setEstAsignacion(estAsignacion);
        Turno turno = new Turno(idTurno, nuevoTurnoDTO.getFechaTurno(), alimentacion, jornada, etiqueta);

        given(jornadaRepository.findById(5)).willReturn(Optional.of(jornada));
        given(etiquetaRepository.findById(6)).willReturn(Optional.of(etiqueta));
        given(estAsignacionRepository.getRowByIds(1, 2, 3, 4)).willReturn(estAsignacion);
        given(turnoRepository.alreadyExists(Date.valueOf("2023-05-12"), 1, 2, 3, 4, 5, 6)).willReturn(0);
        given(turnoRepository.save(any(Turno.class))).willReturn(turno);

        // Act
        TurnoCreadoDTO turnoCreadoDTO = serviceUnderTest.crearTurno(nuevoTurnoDTO);

        // Assert
        ArgumentCaptor<Turno> turnoArgumentCaptor = ArgumentCaptor.forClass(Turno.class);
        verify(turnoRepository).save(turnoArgumentCaptor.capture());
        Turno turnoCapturado = turnoArgumentCaptor.getValue();
        assertThat(turnoCapturado.getAlimentacion()).isEqualTo(TipoAlimentacion.Desayuno);
        assertThat(turnoCreadoDTO).isNotNull();
        assertThat(turnoCreadoDTO.nombreEtiqueta()).isEqualTo("Primer piso");
        assertThat(turnoCreadoDTO.nombreEscenario()).isEqualTo("Hospital San Jose");
        // Realiza las demás verificaciones necesarias

        // Verifica que los métodos necesarios hayan sido llamados
        verify(jornadaRepository, times(1)).findById(5);
        verify(etiquetaRepository, times(1)).findById(6);
        verify(estAsignacionRepository, times(1)).getRowByIds(1, 2, 3, 4);
        verify(turnoRepository, times(1)).alreadyExists(Date.valueOf("2023-05-12"), 1, 2, 3, 4, 5, 6);
        verify(turnoRepository, times(1)).save(any(Turno.class));
    }

    @Test
    public void testCrearTurno_RangoAlmuerzo() {
        // Arrange
        NuevoTurnoDTO  nuevoTurnoDTO = new NuevoTurnoDTO(Date.valueOf("2023-05-12"), 1, 2, 3, 4,
                5, 6);
        TipoAlimentacion alimentacion = TipoAlimentacion.Desayuno;

        Jornada jornada = new Jornada(nuevoTurnoDTO.getIdJornada(), "Mañana", Time.valueOf("12:00:00"), Time.valueOf("13:40:00"));

        EscenarioPractica escenario = new EscenarioPractica(1, "Hospital San Jose");

        Servicio servicio = new Servicio(1, "Cardiologia");

        Etiqueta etiqueta = new Etiqueta(nuevoTurnoDTO.getIdEtiqueta(), "Primer piso", escenario, servicio);

        Estudiante estudiante = new Estudiante();
        estudiante.setId(1);
        estudiante.setNombres("Cristian");
        estudiante.setApellidos("Gomez Santos");
        estudiante.setIdentificacion(106813022001L);
        estudiante.setUsuario("cgomezs");
        estudiante.setFotoPerfil("url foto");

        Programa programa = new Programa(nuevoTurnoDTO.getIdPrograma(), "Enfermería");

        Asignatura asignatura = new Asignatura(nuevoTurnoDTO.getIdAsignatura(), "Cuidado de Enfermería en salud comunitaria y familiar");

        CoordinadorAsignatura coordinadorAsignatura = new CoordinadorAsignatura();
        coordinadorAsignatura.setId(nuevoTurnoDTO.getIdCoordinador());
        coordinadorAsignatura.setNombres("Jorge Ivan");
        coordinadorAsignatura.setApellidos("Solano");
        coordinadorAsignatura.setCorreo("jsolano");
        coordinadorAsignatura.setClave("1234");
        coordinadorAsignatura.setFotoPerfil("url foto");

        AsignacionId idAsignacion = new AsignacionId(programa, asignatura, coordinadorAsignatura);

        Asignacion asignacion = new Asignacion(idAsignacion);
        EstAsignacionId idEstAsignacion = new EstAsignacionId(estudiante, asignacion);
        EstAsignacion estAsignacion = new EstAsignacion(idEstAsignacion, true);
        TurnoId idTurno = new TurnoId();
        idTurno.setEstAsignacion(estAsignacion);
        Turno turno = new Turno(idTurno, nuevoTurnoDTO.getFechaTurno(), alimentacion, jornada, etiqueta);

        given(jornadaRepository.findById(5)).willReturn(Optional.of(jornada));
        given(etiquetaRepository.findById(6)).willReturn(Optional.of(etiqueta));
        given(estAsignacionRepository.getRowByIds(1, 2, 3, 4)).willReturn(estAsignacion);
        given(turnoRepository.alreadyExists(Date.valueOf("2023-05-12"), 1, 2, 3, 4, 5, 6)).willReturn(0);
        given(turnoRepository.save(any(Turno.class))).willReturn(turno);

        // Act
        TurnoCreadoDTO turnoCreadoDTO = serviceUnderTest.crearTurno(nuevoTurnoDTO);

        // Assert
        ArgumentCaptor<Turno> turnoArgumentCaptor = ArgumentCaptor.forClass(Turno.class);
        verify(turnoRepository).save(turnoArgumentCaptor.capture());
        Turno turnoCapturado = turnoArgumentCaptor.getValue();
        assertThat(turnoCapturado.getAlimentacion()).isEqualTo(TipoAlimentacion.Almuerzo);
        assertThat(turnoCreadoDTO).isNotNull();
        assertThat(turnoCreadoDTO.nombreEtiqueta()).isEqualTo("Primer piso");
        assertThat(turnoCreadoDTO.nombreEscenario()).isEqualTo("Hospital San Jose");
        // Realiza las demás verificaciones necesarias

        // Verifica que los métodos necesarios hayan sido llamados
        verify(jornadaRepository, times(1)).findById(5);
        verify(etiquetaRepository, times(1)).findById(6);
        verify(estAsignacionRepository, times(1)).getRowByIds(1, 2, 3, 4);
        verify(turnoRepository, times(1)).alreadyExists(Date.valueOf("2023-05-12"), 1, 2, 3, 4, 5, 6);
        verify(turnoRepository, times(1)).save(any(Turno.class));
    }

    @Test
    public void testCrearTurno_RangoComida() {
        // Arrange
        NuevoTurnoDTO  nuevoTurnoDTO = new NuevoTurnoDTO(Date.valueOf("2023-05-12"), 1, 2, 3, 4,
                5, 6);
        TipoAlimentacion alimentacion = TipoAlimentacion.Desayuno;

        Jornada jornada = new Jornada(nuevoTurnoDTO.getIdJornada(), "Mañana", Time.valueOf("18:00:00"), Time.valueOf("20:00:00"));

        EscenarioPractica escenario = new EscenarioPractica(1, "Hospital San Jose");

        Servicio servicio = new Servicio(1, "Cardiologia");

        Etiqueta etiqueta = new Etiqueta(nuevoTurnoDTO.getIdEtiqueta(), "Primer piso", escenario, servicio);

        Estudiante estudiante = new Estudiante();
        estudiante.setId(1);
        estudiante.setNombres("Cristian");
        estudiante.setApellidos("Gomez Santos");
        estudiante.setIdentificacion(106813022001L);
        estudiante.setUsuario("cgomezs");
        estudiante.setFotoPerfil("url foto");

        Programa programa = new Programa(nuevoTurnoDTO.getIdPrograma(), "Enfermería");

        Asignatura asignatura = new Asignatura(nuevoTurnoDTO.getIdAsignatura(), "Cuidado de Enfermería en salud comunitaria y familiar");

        CoordinadorAsignatura coordinadorAsignatura = new CoordinadorAsignatura();
        coordinadorAsignatura.setId(nuevoTurnoDTO.getIdCoordinador());
        coordinadorAsignatura.setNombres("Jorge Ivan");
        coordinadorAsignatura.setApellidos("Solano");
        coordinadorAsignatura.setCorreo("jsolano");
        coordinadorAsignatura.setClave("1234");
        coordinadorAsignatura.setFotoPerfil("url foto");

        AsignacionId idAsignacion = new AsignacionId(programa, asignatura, coordinadorAsignatura);

        Asignacion asignacion = new Asignacion(idAsignacion);
        EstAsignacionId idEstAsignacion = new EstAsignacionId(estudiante, asignacion);
        EstAsignacion estAsignacion = new EstAsignacion(idEstAsignacion, true);
        TurnoId idTurno = new TurnoId();
        idTurno.setEstAsignacion(estAsignacion);
        Turno turno = new Turno(idTurno, nuevoTurnoDTO.getFechaTurno(), alimentacion, jornada, etiqueta);

        given(jornadaRepository.findById(5)).willReturn(Optional.of(jornada));
        given(etiquetaRepository.findById(6)).willReturn(Optional.of(etiqueta));
        given(estAsignacionRepository.getRowByIds(1, 2, 3, 4)).willReturn(estAsignacion);
        given(turnoRepository.alreadyExists(Date.valueOf("2023-05-12"), 1, 2, 3, 4, 5, 6)).willReturn(0);
        given(turnoRepository.save(any(Turno.class))).willReturn(turno);

        // Act
        TurnoCreadoDTO turnoCreadoDTO = serviceUnderTest.crearTurno(nuevoTurnoDTO);

        // Assert
        ArgumentCaptor<Turno> turnoArgumentCaptor = ArgumentCaptor.forClass(Turno.class);
        verify(turnoRepository).save(turnoArgumentCaptor.capture());
        Turno turnoCapturado = turnoArgumentCaptor.getValue();
        assertThat(turnoCapturado.getAlimentacion()).isEqualTo(TipoAlimentacion.Comida);
        assertThat(turnoCreadoDTO).isNotNull();
        assertThat(turnoCreadoDTO.nombreEtiqueta()).isEqualTo("Primer piso");
        assertThat(turnoCreadoDTO.nombreEscenario()).isEqualTo("Hospital San Jose");
        // Realiza las demás verificaciones necesarias

        // Verifica que los métodos necesarios hayan sido llamados
        verify(jornadaRepository, times(1)).findById(5);
        verify(etiquetaRepository, times(1)).findById(6);
        verify(estAsignacionRepository, times(1)).getRowByIds(1, 2, 3, 4);
        verify(turnoRepository, times(1)).alreadyExists(Date.valueOf("2023-05-12"), 1, 2, 3, 4, 5, 6);
        verify(turnoRepository, times(1)).save(any(Turno.class));
    }

    @Test
    public void testCrearTurno_JornadaNoEncontrada() {
        // given
        NuevoTurnoDTO  nuevoTurnoDTO = new NuevoTurnoDTO(Date.valueOf("2023-05-12"), 1, 2, 3, 4,
                5, 6);

        given(jornadaRepository.findById(anyInt())).willReturn(Optional.empty());

        // when y then
        assertThatThrownBy(() -> {serviceUnderTest.crearTurno(nuevoTurnoDTO);})
                .isInstanceOf(HTTPException.class)
                .hasMessageContaining("No se encontró la jornada con ID " + nuevoTurnoDTO.getIdJornada());

        verify(jornadaRepository, times(1)).findById(nuevoTurnoDTO.getIdJornada());
        verify(etiquetaRepository, times(0)).findById(nuevoTurnoDTO.getIdEtiqueta());
        verify(estAsignacionRepository, times(0)).getRowByIds(nuevoTurnoDTO.getIdEstudiante(),
                                                                                    nuevoTurnoDTO.getIdPrograma(),
                                                                                    nuevoTurnoDTO.getIdAsignatura(),
                                                                                    nuevoTurnoDTO.getIdCoordinador());
        verify(turnoRepository, times(0)).alreadyExists(nuevoTurnoDTO.getFechaTurno(),
                                                                                nuevoTurnoDTO.getIdEstudiante(),
                                                                                nuevoTurnoDTO.getIdPrograma(),
                                                                                nuevoTurnoDTO.getIdAsignatura(),
                                                                                nuevoTurnoDTO.getIdCoordinador(),
                                                                                nuevoTurnoDTO.getIdJornada(),
                                                                                nuevoTurnoDTO.getIdEtiqueta());
        verify(turnoRepository, times(0)).save(any(Turno.class));
    }

    @Test
    public void testCrearTurno_EtiquetaNoEncontrada() {
        // given
        NuevoTurnoDTO  nuevoTurnoDTO = new NuevoTurnoDTO(Date.valueOf("2023-05-12"), 1, 2, 3, 4,
                5, 6);

        given(jornadaRepository.findById(anyInt())).willReturn(Optional.of(new Jornada()));
        given(etiquetaRepository.findById(anyInt())).willReturn(Optional.empty());

        // when y then
        assertThatThrownBy(() -> {serviceUnderTest.crearTurno(nuevoTurnoDTO);})
                .isInstanceOf(HTTPException.class)
                .hasMessageContaining("No se encontró la etiqueta con ID " + nuevoTurnoDTO.getIdEtiqueta());

        verify(jornadaRepository, times(1)).findById(nuevoTurnoDTO.getIdJornada());
        verify(etiquetaRepository, times(1)).findById(nuevoTurnoDTO.getIdEtiqueta());
        verify(estAsignacionRepository, times(0)).getRowByIds(nuevoTurnoDTO.getIdEstudiante(),
                nuevoTurnoDTO.getIdPrograma(),
                nuevoTurnoDTO.getIdAsignatura(),
                nuevoTurnoDTO.getIdCoordinador());
        verify(turnoRepository, times(0)).alreadyExists(nuevoTurnoDTO.getFechaTurno(),
                nuevoTurnoDTO.getIdEstudiante(),
                nuevoTurnoDTO.getIdPrograma(),
                nuevoTurnoDTO.getIdAsignatura(),
                nuevoTurnoDTO.getIdCoordinador(),
                nuevoTurnoDTO.getIdJornada(),
                nuevoTurnoDTO.getIdEtiqueta());
        verify(turnoRepository, times(0)).save(any(Turno.class));
    }

    @Test
    public void testCrearTurno_EstudianteNoRegistrado() {
        // given
        NuevoTurnoDTO  nuevoTurnoDTO = new NuevoTurnoDTO(Date.valueOf("2023-05-12"), 1, 2, 3, 4,
                5, 6);

        given(jornadaRepository.findById(anyInt())).willReturn(Optional.of(new Jornada()));
        given(etiquetaRepository.findById(anyInt())).willReturn(Optional.of(new Etiqueta()));
        given(estAsignacionRepository.getRowByIds(nuevoTurnoDTO.getIdEstudiante(),
                                                    nuevoTurnoDTO.getIdPrograma(),
                                                    nuevoTurnoDTO.getIdAsignatura(),
                                                    nuevoTurnoDTO.getIdCoordinador()))
                                                    .willReturn(null);

        // when y then
        assertThatThrownBy(() -> {serviceUnderTest.crearTurno(nuevoTurnoDTO);})
                .isInstanceOf(HTTPException.class)
                .hasMessageContaining("El estudiante no está registrado en la asignación donde"+
                                                " el ID del programa sea " + nuevoTurnoDTO.getIdPrograma() +
                                                " el ID de la asignatura sea " + nuevoTurnoDTO.getIdAsignatura() +
                                                " el ID del coordinador sea " + nuevoTurnoDTO.getIdCoordinador());

        verify(jornadaRepository, times(1)).findById(nuevoTurnoDTO.getIdJornada());
        verify(etiquetaRepository, times(1)).findById(nuevoTurnoDTO.getIdEtiqueta());
        verify(estAsignacionRepository, times(1))
                .getRowByIds(nuevoTurnoDTO.getIdEstudiante(),
                                nuevoTurnoDTO.getIdPrograma(),
                                nuevoTurnoDTO.getIdAsignatura(),
                                nuevoTurnoDTO.getIdCoordinador());
        verify(turnoRepository, times(0))
                .alreadyExists(nuevoTurnoDTO.getFechaTurno(),
                                nuevoTurnoDTO.getIdEstudiante(),
                                nuevoTurnoDTO.getIdPrograma(),
                                nuevoTurnoDTO.getIdAsignatura(),
                                nuevoTurnoDTO.getIdCoordinador(),
                                nuevoTurnoDTO.getIdJornada(),
                                nuevoTurnoDTO.getIdEtiqueta());
        verify(turnoRepository, times(0)).save(any(Turno.class));
    }

    @Test
    public void testCrearTurno_TurnoYaExiste() {
        // given
        NuevoTurnoDTO  nuevoTurnoDTO = new NuevoTurnoDTO(Date.valueOf("2023-05-12"), 1, 2, 3, 4,
                5, 6);

        given(jornadaRepository.findById(anyInt())).willReturn(Optional.of(new Jornada()));
        given(etiquetaRepository.findById(anyInt())).willReturn(Optional.of(new Etiqueta()));
        given(estAsignacionRepository.getRowByIds(nuevoTurnoDTO.getIdEstudiante(),
                nuevoTurnoDTO.getIdPrograma(),
                nuevoTurnoDTO.getIdAsignatura(),
                nuevoTurnoDTO.getIdCoordinador()))
                .willReturn(new EstAsignacion());
        given(turnoRepository.alreadyExists(Date.valueOf("2023-05-12"), 1, 2,
                                            3, 4, 5, 6)).willReturn(1);

        // when y then
        assertThatThrownBy(() -> {serviceUnderTest.crearTurno(nuevoTurnoDTO);})
                .isInstanceOf(HTTPException.class)
                .hasMessageContaining("El turno que intenta registrar ya exise");

        verify(jornadaRepository, times(1)).findById(nuevoTurnoDTO.getIdJornada());
        verify(etiquetaRepository, times(1)).findById(nuevoTurnoDTO.getIdEtiqueta());
        verify(estAsignacionRepository, times(1))
                .getRowByIds(nuevoTurnoDTO.getIdEstudiante(),
                        nuevoTurnoDTO.getIdPrograma(),
                        nuevoTurnoDTO.getIdAsignatura(),
                        nuevoTurnoDTO.getIdCoordinador());
        verify(turnoRepository, times(1))
                .alreadyExists(nuevoTurnoDTO.getFechaTurno(),
                        nuevoTurnoDTO.getIdEstudiante(),
                        nuevoTurnoDTO.getIdPrograma(),
                        nuevoTurnoDTO.getIdAsignatura(),
                        nuevoTurnoDTO.getIdCoordinador(),
                        nuevoTurnoDTO.getIdJornada(),
                        nuevoTurnoDTO.getIdEtiqueta());
        verify(turnoRepository, times(0)).save(any(Turno.class));
    }

    @Test
    public void testCrearTurno_TurnoYaExisteMismaFecha() {
        // given
        NuevoTurnoDTO  nuevoTurnoDTO = new NuevoTurnoDTO(Date.valueOf("2023-05-12"), 1, 2, 3, 4,
                5, 6);
        Jornada jornada = new Jornada(nuevoTurnoDTO.getIdJornada(), "Mañana", Time.valueOf("06:30:00"), Time.valueOf("11:35:00"));
        //HorarioJornada horarioJornada = new HorarioJornada("06:30:00", "11:35:00");
        given(jornadaRepository.findById(anyInt())).willReturn(Optional.of(jornada));
        given(etiquetaRepository.findById(anyInt())).willReturn(Optional.of(new Etiqueta()));
        given(estAsignacionRepository.getRowByIds(nuevoTurnoDTO.getIdEstudiante(),
                nuevoTurnoDTO.getIdPrograma(),
                nuevoTurnoDTO.getIdAsignatura(),
                nuevoTurnoDTO.getIdCoordinador()))
                .willReturn(new EstAsignacion());
        given(turnoRepository.alreadyExists(Date.valueOf("2023-05-12"), 1, 2,
                3, 4, 5, 6)).willReturn(0);
        given(turnoRepository.save(any(Turno.class)))
                .willThrow(new DataAccessException(anyString()) {});

        // when y then
        assertThatThrownBy(() -> {serviceUnderTest.crearTurno(nuevoTurnoDTO);})
                .isInstanceOf(HTTPException.class)
                .hasMessageContaining("Ya existe un turno asignado al estudiante en la misma fecha");

        verify(jornadaRepository, times(1)).findById(nuevoTurnoDTO.getIdJornada());
        verify(etiquetaRepository, times(1)).findById(nuevoTurnoDTO.getIdEtiqueta());
        verify(estAsignacionRepository, times(1))
                .getRowByIds(nuevoTurnoDTO.getIdEstudiante(),
                        nuevoTurnoDTO.getIdPrograma(),
                        nuevoTurnoDTO.getIdAsignatura(),
                        nuevoTurnoDTO.getIdCoordinador());
        verify(turnoRepository, times(1))
                .alreadyExists(nuevoTurnoDTO.getFechaTurno(),
                        nuevoTurnoDTO.getIdEstudiante(),
                        nuevoTurnoDTO.getIdPrograma(),
                        nuevoTurnoDTO.getIdAsignatura(),
                        nuevoTurnoDTO.getIdCoordinador(),
                        nuevoTurnoDTO.getIdJornada(),
                        nuevoTurnoDTO.getIdEtiqueta());
        verify(turnoRepository, times(1)).save(any(Turno.class));
    }

    @Test
    void test_GetInfoScheduleByDate() {
        // given
        int idEstudiante = 1;
        Date fechaTurno = Date.valueOf("2023-05-12");
        List<TurnoAsociadoDTO> turnoAsociadoDTOs = new ArrayList<>();
        turnoAsociadoDTOs.add(new TurnoAsociadoDTO(1, "Hospital San Jose", "Mañana", 
                                            Time.valueOf("06:30:00"), Time.valueOf("11:30:00"), 
                                            "Primer piso", fechaTurno, idEstudiante, TipoAlimentacion.Comida, 
                                            "Cristian Paz"));
        given(turnoRepository.findShiftsAssociationsByDate(idEstudiante, fechaTurno)).willReturn(turnoAsociadoDTOs);

        // when
        InformacionHorarioTurnoDTO horarioTurnoDTO = serviceUnderTest.obetenerInfoHorarioTurnoPorFecha(idEstudiante, fechaTurno);

        // then
        verify(turnoRepository).findShiftsAssociationsByDate(idEstudiante, fechaTurno);
        assertThat(horarioTurnoDTO.nombreEstudiante()).isEqualTo(turnoAsociadoDTOs.get(0).nombreEstudiante());
        assertThat(horarioTurnoDTO.rangoHorario()).isNotBlank();
    }

    /*@Test
    void test_GetAllSchedulesInSubject (){
        // given
        int idPrograma = 1;
        int idCoordinador = 2;
        int idAsignatura = 3;

        // when
        // List<HorarioDTO> horariosDTO = serviceUnderTest.obetenerHorariosTurno(idPrograma, idCoordinador, idAsignatura);
        serviceUnderTest.obetenerHorariosTurno(idPrograma, idCoordinador, idAsignatura);

        // then
        verify(turnoRepository).findDifferentSchedules(idPrograma, idAsignatura, idCoordinador);
        // verify(turnoRepository).findShiftsAssociationsByDate2(idAsignatura, new Date(anyLong()));
    }*/

    @Test
    void obtenerHorariosTurnoTest() {
        // given
        int idPrograma = 1;
        int idCoordinador = 2;
        int idAsignatura = 3;
        Date fechaTurno1 = Date.valueOf("2023-05-12");
        Date fechaTurno2 = Date.valueOf("2023-05-13");

        // Mock de los resultados del repositorio
        List<EstudianteFechaDTO> estFechaDTOList = new ArrayList<>();
        estFechaDTOList.add(new EstudianteFechaDTO(1, Date.valueOf("2023-05-12")));
        estFechaDTOList.add(new EstudianteFechaDTO(2, Date.valueOf("2023-05-13")));

        List<TurnoAsociadoDTO> turnosAsociadosDTO1 = new ArrayList<>();
        turnosAsociadosDTO1.add(new TurnoAsociadoDTO(1, "Hospital San José E.S.E",
                        "Mañana", Time.valueOf("06:00:00"), Time.valueOf("11:00:00"),
                        "Salas", Date.valueOf("2023-05-12"), 1,
                        TipoAlimentacion.Desayuno, "Cristian Gomez Lopez"));

        turnosAsociadosDTO1.add(new TurnoAsociadoDTO(2, "Hospital San José E.S.E",
                        "Tarde", Time.valueOf("14:00:00"), Time.valueOf("18:00:00"),
                        "Salas", Date.valueOf("2023-05-12"), 1,
                        null, "Cristian Gomez Lopez"));

        List<TurnoAsociadoDTO> turnosAsociadosDTO2 = new ArrayList<>();
        turnosAsociadosDTO2.add(new TurnoAsociadoDTO(3, "Clínica la Estancia",
                        "Mañana", Time.valueOf("06:30:00"), Time.valueOf("08:30:00"),
                        "Primer piso", Date.valueOf("2023-05-13"), 2,
                        TipoAlimentacion.Desayuno, "Cristobal Colon"));

        // Configurar el comportamiento del repositorio
        given(turnoRepository.findDifferentSchedules(idPrograma, idAsignatura, idCoordinador))
                .willReturn(estFechaDTOList);

        given(turnoRepository.findShiftsAssociationsByDate2(1, fechaTurno1))
                .willReturn(turnosAsociadosDTO1);

        given(turnoRepository.findShiftsAssociationsByDate2(2, fechaTurno2))
                .willReturn(turnosAsociadosDTO2);

        // when
        List<HorarioDTO> horariosDTO = serviceUnderTest.obetenerHorariosTurno(idPrograma, idCoordinador, idAsignatura);

        // then
        assertThat(horariosDTO).hasSize(2);

        HorarioDTO horarioDTO1 = horariosDTO.get(0);
        assertThat(horarioDTO1.nombreEscenario()).isEqualTo("Hospital San José E.S.E");
        assertThat(horarioDTO1.nombreEtiqueta()).isEqualTo("Salas");
        assertThat(horarioDTO1.franjasJornada()).isEqualTo("Mañana y Tarde ");
        assertThat(horarioDTO1.fechaTurno()).isEqualTo(Date.valueOf("2023-05-12"));
        assertThat(horarioDTO1.idEstudiante()).isEqualTo(1);

        HorarioDTO horarioDTO2 = horariosDTO.get(1);
        assertThat(horarioDTO2.nombreEscenario()).isEqualTo("Clínica la Estancia");
        assertThat(horarioDTO2.nombreEtiqueta()).isEqualTo("Primer piso");
        assertThat(horarioDTO2.franjasJornada()).isEqualTo("Mañana ");
        assertThat(horarioDTO2.fechaTurno()).isEqualTo(Date.valueOf("2023-05-13"));
        assertThat(horarioDTO2.idEstudiante()).isEqualTo(2);

        // Verificar que los métodos del repositorio se llamaron con los parámetros correctos
        verify(turnoRepository).findDifferentSchedules(idPrograma, idAsignatura, idCoordinador);
        verify(turnoRepository).findShiftsAssociationsByDate2(1, Date.valueOf("2023-05-12"));
        verify(turnoRepository).findShiftsAssociationsByDate2(2, Date.valueOf("2023-05-13"));
    }

    @Test
    void test_GetShiftsByDate() {
        // given
        int idEstudiante = 1;
        Date fechaTurno = Date.valueOf("2023-05-12");
        
        // when
        serviceUnderTest.obetenerTurnosPorFecha(idEstudiante, fechaTurno);

        // then
        verify(turnoRepository).findShiftsAssociationsByDate2(idEstudiante, fechaTurno);
    }

    @Test
    void test_DeleteAssociatedShift() {
        // given
        int idTurno = 1;
        
        // when
        serviceUnderTest.eliminarTurnoAsociado(idTurno);

        // then
        verify(turnoRepository).myDeletebyid(idTurno);
    }

    @Test
    void test_ShouldSetSchedule() {
        // given
        HorarioJornada horario1 = new HorarioJornada("06:30", "11:30");
        HorarioJornada horario2 = new HorarioJornada("12:00", "14:00");
        HorarioJornada horario3 = new HorarioJornada("14:00", "18:00");

        List<HorarioJornada> horarios = new ArrayList<>();
        horarios.add(horario1);
        horarios.add(horario2);
        horarios.add(horario3);

        String resultadoEsperado = "06:30 a 11:30 y 12:00 a 18:00 ";
        
        // when
        String resultado = serviceUnderTest.establecerHorario(horarios);

        // then
        assertThat(resultado).isEqualTo(resultadoEsperado);
    }

    @Test
    void test_ShouldBeSuitableForBreackfast() {
        // given
        List<TurnoAsociadoDTO> turnoAsociadoDTOs = new ArrayList<>();
        turnoAsociadoDTOs.add(new TurnoAsociadoDTO(1, null, null, null, 
                                            null, null, null, 0, 
                                            TipoAlimentacion.Desayuno, null));
        // when
        Boolean[] alimentacion = serviceUnderTest.aptoParaAlimentacion(turnoAsociadoDTOs);

        // then
        assertThat(alimentacion[0]).isEqualTo(true);
        assertThat(alimentacion[1]).isEqualTo(null);
        assertThat(alimentacion[2]).isEqualTo(null);
    }

    @Test
    void test_ShouldBeSuitableForLunch() {
        // given
        List<TurnoAsociadoDTO> turnoAsociadoDTOs = new ArrayList<>();
        turnoAsociadoDTOs.add(new TurnoAsociadoDTO(1, null, null, null, 
                                            null, null, null, 0, 
                                            TipoAlimentacion.Almuerzo, null));
        // when
        Boolean[] alimentacion = serviceUnderTest.aptoParaAlimentacion(turnoAsociadoDTOs);

        // then
        assertThat(alimentacion[0]).isEqualTo(null);
        assertThat(alimentacion[1]).isEqualTo(true);
        assertThat(alimentacion[2]).isEqualTo(null);
    }

    @Test
    void test_ShouldBeSuitableForDinner() {
        // given
        List<TurnoAsociadoDTO> turnoAsociadoDTOs = new ArrayList<>();
        turnoAsociadoDTOs.add(new TurnoAsociadoDTO(1, null, null, null, 
                                            null, null, null, 0, 
                                            TipoAlimentacion.Comida, null));
        // when
        Boolean[] alimentacion = serviceUnderTest.aptoParaAlimentacion(turnoAsociadoDTOs);

        // then
        assertThat(alimentacion[0]).isEqualTo(null);
        assertThat(alimentacion[1]).isEqualTo(null);
        assertThat(alimentacion[2]).isEqualTo(true);
    }

    @Test
    void test_ShouldBeSuitableForBreakfastAndLunch() {
        // given
        List<TurnoAsociadoDTO> turnoAsociadoDTOs = new ArrayList<>();
        turnoAsociadoDTOs.add(new TurnoAsociadoDTO(1, null, null, null, 
                                            null, null, null, 0, 
                                            TipoAlimentacion.Desayuno, null));
        turnoAsociadoDTOs.add(new TurnoAsociadoDTO(2, null, null, null, 
                                            null, null, null, 0, 
                                            TipoAlimentacion.Almuerzo, null));
        // when
        Boolean[] alimentacion = serviceUnderTest.aptoParaAlimentacion(turnoAsociadoDTOs);

        // then
        assertThat(alimentacion[0]).isEqualTo(true);
        assertThat(alimentacion[1]).isEqualTo(true);
        assertThat(alimentacion[2]).isEqualTo(null);
    }

    @Test
    void test_ShouldBeSuitableForBreakfastAndDinner() {
        // given
        List<TurnoAsociadoDTO> turnoAsociadoDTOs = new ArrayList<>();
        turnoAsociadoDTOs.add(new TurnoAsociadoDTO(1, null, null, null, 
                                            null, null, null, 0, 
                                            TipoAlimentacion.Desayuno, null));
        turnoAsociadoDTOs.add(new TurnoAsociadoDTO(2, null, null, null, 
                                            null, null, null, 0, 
                                            TipoAlimentacion.Comida, null));
        // when
        Boolean[] alimentacion = serviceUnderTest.aptoParaAlimentacion(turnoAsociadoDTOs);

        // then
        assertThat(alimentacion[0]).isEqualTo(true);
        assertThat(alimentacion[1]).isEqualTo(null);
        assertThat(alimentacion[2]).isEqualTo(true);
    }

    @Test
    void test_ShouldBeSuitableForLunchAndDinner() {
        // given
        List<TurnoAsociadoDTO> turnoAsociadoDTOs = new ArrayList<>();
        turnoAsociadoDTOs.add(new TurnoAsociadoDTO(1, null, null, null, 
                                            null, null, null, 0, 
                                            TipoAlimentacion.Almuerzo, null));
        turnoAsociadoDTOs.add(new TurnoAsociadoDTO(2, null, null, null, 
                                            null, null, null, 0, 
                                            TipoAlimentacion.Comida, null));
        // when
        Boolean[] alimentacion = serviceUnderTest.aptoParaAlimentacion(turnoAsociadoDTOs);

        // then
        assertThat(alimentacion[0]).isEqualTo(null);
        assertThat(alimentacion[1]).isEqualTo(true);
        assertThat(alimentacion[2]).isEqualTo(true);
    }
    
    @Test
    void test_ShouldBeSuitableForBreakfastAndLunchAndDinner() {
        // given
        List<TurnoAsociadoDTO> turnoAsociadoDTOs = new ArrayList<>();
        turnoAsociadoDTOs.add(new TurnoAsociadoDTO(1, null, null, null, 
                                            null, null, null, 0, 
                                            TipoAlimentacion.Desayuno, null));
        turnoAsociadoDTOs.add(new TurnoAsociadoDTO(2, null, null, null, 
                                            null, null, null, 0, 
                                            TipoAlimentacion.Almuerzo, null));
        turnoAsociadoDTOs.add(new TurnoAsociadoDTO(3, null, null, null, 
                                            null, null, null, 0, 
                                            TipoAlimentacion.Comida, null));
        // when
        Boolean[] alimentacion = serviceUnderTest.aptoParaAlimentacion(turnoAsociadoDTOs);

        // then
        assertThat(alimentacion[0]).isEqualTo(true);
        assertThat(alimentacion[1]).isEqualTo(true);
        assertThat(alimentacion[2]).isEqualTo(true);
    }
}