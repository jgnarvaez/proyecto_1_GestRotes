package co.edu.unicauca.gesrotesbackend.controllers;

import static org.hamcrest.Matchers.hasSize;
// import static org.mockito.ArgumentMatchers.any;
// import static org.mockito.ArgumentMatchers.anyInt;
// import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.assertj.core.api.Assertions.assertThat;

import co.edu.unicauca.gesrotesbackend.services.DTO.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import com.fasterxml.jackson.databind.ObjectMapper;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
// import static org.mockito.Mockito.doNothing;
// import static org.mockito.Mockito.when;
// import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import co.edu.unicauca.gesrotesbackend.models.TipoAlimentacion;
import co.edu.unicauca.gesrotesbackend.services.services.ITurnoService;

import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

@WebMvcTest(ControladorTurno.class)
public class ControladorTurnoTest {
    
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ITurnoService service;

    @Autowired
    private ObjectMapper objectMapper;

    private String url;

    @BeforeEach
    void setUp() {
        url = "http://127.0.0.1:8085/turnos/";
    }

    @Test
    void changeSelectStateTest() throws Exception {
        // given
        String urlChangeSelect = url + "/seleccion";
        SeleccionEstudianteDTO seleccionEstudianteDTO = new SeleccionEstudianteDTO(1, 1, 3, 1, true);

        // when
        ResultActions response = mockMvc.perform(put(urlChangeSelect)
                                        .contentType(MediaType.APPLICATION_JSON)
                                        .content(objectMapper.writeValueAsString(seleccionEstudianteDTO)));

        // then
        response.andExpect(status().isOk());
        
        ArgumentCaptor<SeleccionEstudianteDTO> seleccionEstudianteArgumentCaptor = ArgumentCaptor.forClass(SeleccionEstudianteDTO.class);
        verify(service).cambiarEstadoSeleccionado(seleccionEstudianteArgumentCaptor.capture());
        SeleccionEstudianteDTO seleccionEstudianteCapturado = seleccionEstudianteArgumentCaptor.getValue();
        
        assertThat(seleccionEstudianteCapturado.getPuId())
                .isEqualTo(seleccionEstudianteDTO.getPuId());
        assertThat(seleccionEstudianteCapturado.getProgId())
                .isEqualTo(seleccionEstudianteDTO.getProgId());
        assertThat(seleccionEstudianteCapturado.getAsigId())
                .isEqualTo(seleccionEstudianteDTO.getAsigId());
        assertThat(seleccionEstudianteCapturado.getCooId())
                .isEqualTo(seleccionEstudianteDTO.getCooId());
        assertThat(seleccionEstudianteCapturado.getEstado())
                .isEqualTo(seleccionEstudianteDTO.getEstado());
    }

    @Test
    void findSelectedStudentsTest() throws Exception {
        // given
        int progId = 1;
        int asigId = 3;
        int cooId = 1;
        String urlFindSelectedStudents = url + "estudiantesSeleccionados/" + progId + "/" + asigId + "/" + cooId;
        List<EstudianteSeleccionadoDTO> estudiantesSeleccionados = new ArrayList<>();
        estudiantesSeleccionados.add(new EstudianteSeleccionadoDTO(1, "Cristian Gomez Santos"));
        estudiantesSeleccionados.add(new EstudianteSeleccionadoDTO(2, "Cristobal Colon Lopez"));
        given(service.obtenerEstudiantesSeleccionados(progId, asigId, cooId)).willReturn(estudiantesSeleccionados);

        // when
        ResultActions response = mockMvc.perform(get(urlFindSelectedStudents));

        // then
        response.andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(estudiantesSeleccionados)))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$", hasSize(estudiantesSeleccionados.size())))
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].nombreCompleto").value("Cristian Gomez Santos"))
                .andExpect(jsonPath("$[1].id").value(2))
                .andExpect(jsonPath("$[1].nombreCompleto").value("Cristobal Colon Lopez"));

        verify(service, times(1)).obtenerEstudiantesSeleccionados(progId, asigId, cooId);
    }

    @Test
    void deselectStudentsTest() throws Exception {
        // given
        int progId = 1;
        int asigId = 3;
        int cooId = 1;
        String urlDeselectStudents = url + "deseleccionarTodos";
        SeleccionEstudiantesDTO seleccionEstudiantesDTO = new SeleccionEstudiantesDTO(progId, asigId, cooId);

        // when
        ResultActions response = mockMvc.perform(put(urlDeselectStudents)
                                        .contentType(MediaType.APPLICATION_JSON)
                                        .content(objectMapper.writeValueAsString(seleccionEstudiantesDTO)));

        // then
        response.andExpect(status().isOk());

        ArgumentCaptor<SeleccionEstudiantesDTO> seleccionEstudiantesArgumentCaptor = ArgumentCaptor.forClass(SeleccionEstudiantesDTO.class);
        verify(service).deseleccionarEstudiantes(seleccionEstudiantesArgumentCaptor.capture());
        SeleccionEstudiantesDTO seleccionEstudiantesCapturado = seleccionEstudiantesArgumentCaptor.getValue();
        
        assertThat(seleccionEstudiantesCapturado.getProgId())
                .isEqualTo(seleccionEstudiantesDTO.getProgId());
        assertThat(seleccionEstudiantesCapturado.getAsigId())
                .isEqualTo(seleccionEstudiantesDTO.getAsigId());
        assertThat(seleccionEstudiantesCapturado.getCooId())
                .isEqualTo(seleccionEstudiantesDTO.getCooId());
    }

    @Test
    void findHospitalsTest() throws Exception {
        // given
        String urlFindHospitals = url + "jornadas";
        List<JornadaDTO> jornadas = new ArrayList<>();
        jornadas.add(new JornadaDTO(1, "Mañana", Time.valueOf("06:30:00"), Time.valueOf("11:35:00")));
        jornadas.add(new JornadaDTO(2, "Tarde", Time.valueOf("14:00:00"), Time.valueOf("18:30:00")));
        given(service.obetenerJornadas()).willReturn(jornadas);

        // when
        ResultActions response = mockMvc.perform(get(urlFindHospitals));

        // then
        response.andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(jornadas)))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$", hasSize(jornadas.size())))
                .andExpect(jsonPath("$[0].idJornada").value(1))
                .andExpect(jsonPath("$[0].franja").value("Mañana"))
                .andExpect(jsonPath("$[0].horaInicio").value("06:30:00"))
                .andExpect(jsonPath("$[0].horaFin").value("11:35:00"))
                .andExpect(jsonPath("$[1].idJornada").value(2))
                .andExpect(jsonPath("$[1].franja").value("Tarde"))
                .andExpect(jsonPath("$[1].horaInicio").value("14:00:00"))
                .andExpect(jsonPath("$[1].horaFin").value("18:30:00"));

        verify(service, times(1)).obetenerJornadas();
    }

    @Test
    void createTest() throws Exception {
        // given
        NuevoTurnoDTO nuevoTurno = new NuevoTurnoDTO(Date.valueOf("2023-05-18"), 4, 1, 
                                                        1, 1, 1, 1);
        TurnoCreadoDTO turnoCreado = new TurnoCreadoDTO("Salas", "Mañana", 
                                                        Time.valueOf("06:30:00"), Time.valueOf("11:30:00"),
                                                        "Hospital San José E.S.E");
        given(service.crearTurno(nuevoTurno)).willReturn(turnoCreado);

        // when
        ResultActions response = mockMvc.perform(post(url)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(nuevoTurno)));

        // then
        response.andExpect(status().isOk());
                // .andDo(print());
                
        ArgumentCaptor<NuevoTurnoDTO> NuevoTurnoArgumentCaptor = ArgumentCaptor.forClass(NuevoTurnoDTO.class);
        verify(service).crearTurno(NuevoTurnoArgumentCaptor.capture());
        NuevoTurnoDTO nuevoTurnoCapturado = NuevoTurnoArgumentCaptor.getValue();
        
        assertThat(nuevoTurnoCapturado.getFechaTurno())
                .isEqualTo(nuevoTurno.getFechaTurno());
        assertThat(nuevoTurnoCapturado.getIdEstudiante())
                .isEqualTo(nuevoTurno.getIdEstudiante());
        assertThat(nuevoTurnoCapturado.getIdPrograma())
                .isEqualTo(nuevoTurno.getIdPrograma());
        assertThat(nuevoTurnoCapturado.getIdAsignatura())
                .isEqualTo(nuevoTurno.getIdAsignatura());
        assertThat(nuevoTurnoCapturado.getIdCoordinador())
                .isEqualTo(nuevoTurno.getIdCoordinador());
        assertThat(nuevoTurnoCapturado.getIdJornada())
                .isEqualTo(nuevoTurno.getIdJornada());
        assertThat(nuevoTurnoCapturado.getIdEtiqueta())
                .isEqualTo(nuevoTurno.getIdEtiqueta());
    }

    @Test
    void findScheduleTest() throws Exception {
        // given
        int idEstudiante = 4;
        Date fechaTurno = Date.valueOf("2023-05-12");
        String urlFindSchedule = url + "horarioTurno/" + idEstudiante +"/" + fechaTurno;
        InformacionHorarioTurnoDTO informacionHorarioTurno = new InformacionHorarioTurnoDTO("Juan Camilo Sanchez",
                                                                                "06:30 a 11:30 y 14:00 a 21:00 ", 
                                                                                true, false, true);
        given(service.obetenerInfoHorarioTurnoPorFecha(idEstudiante, fechaTurno))
                .willReturn(informacionHorarioTurno);

        // when
        ResultActions response = mockMvc.perform(get(urlFindSchedule));

        // then
        response.andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(informacionHorarioTurno)));

        verify(service, times(1)).obetenerInfoHorarioTurnoPorFecha(idEstudiante, fechaTurno);
    }

    @Test
    void findSchedulesTest() throws Exception {
        // given
        int idPrograma = 1;
        int idCoordinador = 1;
        int idAsignatura = 1;
        String urlFindSchedules = url + idPrograma + "/" + idCoordinador + "/" + idAsignatura;
        List<HorarioDTO> horarios = new ArrayList<>();
        horarios.add(new HorarioDTO("Hospital San José E.S.E", "Salas", 
                                        "Mañana y Tarde y Noche ", Date.valueOf("2023-05-12"), 4));
        horarios.add(new HorarioDTO("Hospital San José E.S.E", "Primer piso", 
                                        "Mañana y Tarde ", Date.valueOf("2023-05-15"), 3));
        given(service.obetenerHorariosTurno(idPrograma, idCoordinador, idAsignatura))
                .willReturn(horarios);

        // when
        ResultActions response = mockMvc.perform(get(urlFindSchedules));

        // then
        response.andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(horarios)))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$", hasSize(horarios.size())));

        verify(service, times(1)).obetenerHorariosTurno(idPrograma, idCoordinador, idAsignatura);
    }

    @Test
    void findShiftsTest() throws Exception {
        // given
        int idEstudiante = 1;
        Date fechaTurno = Date.valueOf("2023-05-12");
        String urlFindShifts = url + "turnosPorFechaEstudiante/" + idEstudiante + "/" + fechaTurno.toString();
        List<TurnoAsociadoDTO> turnosAsociados = new ArrayList<>();
        turnosAsociados.add(new TurnoAsociadoDTO(1, "Hospital San José E.S.E",
                                "Mañana", Time.valueOf("06:30:00"), Time.valueOf("11:30:00"),
                                "Salas", fechaTurno, 4,
                                TipoAlimentacion.Desayuno, "Juan Camilo Sanchez"));
        turnosAsociados.add(new TurnoAsociadoDTO(2, "Hospital San José E.S.E",
                                "Tarde", Time.valueOf("14:00:00"), Time.valueOf("18:00:00"),
                                "Salas", fechaTurno, 4,
                                TipoAlimentacion.Desayuno, "Juan Camilo Sanchez"));
        given(service.obetenerTurnosPorFecha(idEstudiante, fechaTurno))
                .willReturn(turnosAsociados);

        // when
        ResultActions response = mockMvc.perform(get(urlFindShifts));

        // then
        response.andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(turnosAsociados)))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$", hasSize(turnosAsociados.size())));

        verify(service, times(1)).obetenerTurnosPorFecha(idEstudiante, fechaTurno);
    }

    @Test
    void modifyShiftTest() throws Exception {
        // given
        TurnoAModificarDTO turnoAModificarDTO = new TurnoAModificarDTO(1, 2, 2);
        given(service.modificarTurno(any(TurnoAModificarDTO.class))).willReturn(true);

        // when
        ResultActions response = mockMvc.perform(put(url)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(turnoAModificarDTO)));

        // then
        response.andExpect(status().isOk())
                .andExpect(content().string("Turno modificado exitosamente!"));

        ArgumentCaptor<TurnoAModificarDTO> turnoAModificarDTOArgumentCaptorArgumentCaptor = ArgumentCaptor.forClass(TurnoAModificarDTO.class);
        verify(service).modificarTurno(turnoAModificarDTOArgumentCaptorArgumentCaptor.capture());
        TurnoAModificarDTO turnoAModificarDTODos = turnoAModificarDTOArgumentCaptorArgumentCaptor.getValue();
        verify(service, times(1)).modificarTurno(turnoAModificarDTODos);
    }

    @Test
    void cannotModifyShiftTest() throws Exception {
        // given
        TurnoAModificarDTO turnoAModificarDTO = new TurnoAModificarDTO(1, 2, 2);
        given(service.modificarTurno(any(TurnoAModificarDTO.class))).willReturn(false);

        // when
        ResultActions response = mockMvc.perform(put(url)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(turnoAModificarDTO)));

        // then
        response.andExpect(status().isInternalServerError())
                .andExpect(content().string("NO se pudo modificar el turno"));

        ArgumentCaptor<TurnoAModificarDTO> turnoAModificarDTOArgumentCaptorArgumentCaptor = ArgumentCaptor.forClass(TurnoAModificarDTO.class);
        verify(service).modificarTurno(turnoAModificarDTOArgumentCaptorArgumentCaptor.capture());
        TurnoAModificarDTO turnoAModificarDTODos = turnoAModificarDTOArgumentCaptorArgumentCaptor.getValue();
        verify(service, times(1)).modificarTurno(turnoAModificarDTODos);
    }

    @Test
    void deleteTest() throws Exception {
        // given
        int idTurno = 1;
        String urlDelete = url + idTurno;

        // when
        ResultActions response = mockMvc.perform(delete(urlDelete));
        
        //then
        response.andExpect(status().isOk())
                .andExpect(content().string("Turno asociado eliminado correctamente"));

        verify(service, times(1)).eliminarTurnoAsociado(idTurno);
    }

    @Test
    void findStudentsWithFoodTest() throws Exception {
        // given
        int progId = 1;
        int asigId = 3;
        int cooId = 1;
        Date fecha = Date.valueOf("2023-06-10");
        String urlGetStudentsWithFood = url + "estudiantesConAlimentacion/"+progId+"/"+asigId+"/"+cooId+"/"+fecha;
        List<InformacionHorarioTurnoDTO> estudiantesConAlimentacion = new ArrayList<>();
        estudiantesConAlimentacion.add(new InformacionHorarioTurnoDTO("Juan Camilo Sanchez",
                                                "06:30 a 12:00 y 14:00 a 21:00 ",
                                                    true, null, true));
        estudiantesConAlimentacion.add(new InformacionHorarioTurnoDTO("Hector Esteban Coral",
                                                "11:30 a 21:30 ",
                                                true, true, true));
        given(service.obtenerEstudiantesConAlimentacion(fecha, progId, asigId, cooId))
                    .willReturn(estudiantesConAlimentacion);

        // when
        ResultActions response = mockMvc.perform(get(urlGetStudentsWithFood));

        // then
        response.andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(estudiantesConAlimentacion)))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$", hasSize(estudiantesConAlimentacion.size())));

        verify(service, times(1)).obtenerEstudiantesConAlimentacion(fecha, progId, asigId, cooId);
    }

    @Test
    void findStudentsToValidateTest() throws Exception {
        // given
        int progId = 1;
        int asigId = 3;
        int cooId = 1;
        String mes = "Junio";
        int anio = 2023;
        String urlGetStudentsToValidate = url + "estudiantesAValidar/"+progId+"/"+asigId+"/"+cooId+"/"+mes+"/"+anio;
        List<ValidacionEstudianteDTO> validacionEstudiantes = new ArrayList<>();
        validacionEstudiantes.add(new ValidacionEstudianteDTO(1,"Juan Camilo Sanchez",
                                                    true, true, null));
        validacionEstudiantes.add(new ValidacionEstudianteDTO(2, "Hector Esteban Coral",
                                                    false, false, "mal comportamiento"));
        given(service.obtenerEstudiantesValidacion(progId, asigId, cooId, mes, anio))
                    .willReturn(validacionEstudiantes);

        // when
        ResultActions response = mockMvc.perform(get(urlGetStudentsToValidate));

        // then
        response.andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(validacionEstudiantes)))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$", hasSize(validacionEstudiantes.size())));

        verify(service, times(1)).obtenerEstudiantesValidacion(progId, asigId, cooId, mes, anio);
    }

    @Test
    void validateAttendanceTest() throws Exception {
        // given
        ValidacionTurnoDTO validacionTurnoDTO = new ValidacionTurnoDTO(1, true, "");
        String urlValidateAttendance = url + "validarAsistencia";
        // when
        ResultActions response = mockMvc.perform(put(urlValidateAttendance)
                                        .contentType(MediaType.APPLICATION_JSON)
                                        .content(objectMapper.writeValueAsString(validacionTurnoDTO)));

        // then
        response.andExpect(status().isOk());

        ArgumentCaptor<ValidacionTurnoDTO> validacionTurnoDTOArgumentCapture = ArgumentCaptor.forClass(ValidacionTurnoDTO.class);
        verify(service).modificarAsistenciaYEstado(validacionTurnoDTOArgumentCapture.capture());
        ValidacionTurnoDTO validacionTurnoDTOCapturado = validacionTurnoDTOArgumentCapture.getValue();
        verify(service, times(1)).modificarAsistenciaYEstado(validacionTurnoDTOCapturado);
    }

    @Test
    void modifyObservationsTest() throws Exception {
        // given
        ModificarObsDTO modificarObsDTO = new ModificarObsDTO(2, "mal comportamiento");
        String urlModifyObservations = url + "modificarObservaciones";
        // when
        ResultActions response = mockMvc.perform(put(urlModifyObservations)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(modificarObsDTO)));

        // then
        response.andExpect(status().isOk());

        ArgumentCaptor<ModificarObsDTO> modificarObsDTOArgumentCapture = ArgumentCaptor.forClass(ModificarObsDTO.class);
        verify(service).modificarObservaciones(modificarObsDTOArgumentCapture.capture());
        ModificarObsDTO modificarObsDTOCapturado = modificarObsDTOArgumentCapture.getValue();
        verify(service, times(1)).modificarObservaciones(modificarObsDTOCapturado);
    }
}
