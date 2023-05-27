package co.edu.unicauca.gesrotesbackend.controllers;

import static org.hamcrest.Matchers.hasSize;
// import static org.mockito.ArgumentMatchers.anyInt;
// import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
// import static org.mockito.Mockito.doNothing;

// import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import co.edu.unicauca.gesrotesbackend.services.DTO.EstAsignacionDTO;
import co.edu.unicauca.gesrotesbackend.services.DTO.EstudianteDTO;
import co.edu.unicauca.gesrotesbackend.services.services.IAsignacionEstudiantesService;

import java.util.ArrayList;
import java.util.List;

@WebMvcTest(ControladorAsignacionEstudiantes.class)
public class ControladorAsignacionEstudiantesTest {
    
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private IAsignacionEstudiantesService service;

    private String url;
    private int cooId;
    private int progId;
    private int asigId;

    @BeforeEach
    void setUp() {
        cooId = 1;
        progId = 1;
        asigId = 3;
        url = "http://127.0.0.1:8085/" + cooId + "/" + progId + "/asignaturas/" + asigId;
    }
    
    @Test
    void test_Can_FindAllStudents() throws Exception {
        // given
        String urlFindAllStudents = url + "/estudiantes";
        List<EstudianteDTO> listaEstudianteDTOs = new ArrayList<>();
        listaEstudianteDTOs.add(new EstudianteDTO(1, "Cristian Gomez Santos", 106813022001L, "cgomezs"));
        listaEstudianteDTOs.add(new EstudianteDTO(2, "Cristobal Colon Lopez", 106813022002L, "ccolonl"));
        listaEstudianteDTOs.add(new EstudianteDTO(3, "Maria Paz Bolaños", 106813022003L, "mpazb"));
        listaEstudianteDTOs.add(new EstudianteDTO(4, "Juan Camilo Sanchez", 106813022004L, "jcamilos"));
        given(service.getAllStudents(asigId, progId, cooId)).willReturn(listaEstudianteDTOs);

        // when
        ResultActions response = mockMvc.perform(MockMvcRequestBuilders
                                        .get(urlFindAllStudents));

        // then
        response.andExpect(status().isOk())
                // .andDo(print())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$", hasSize(listaEstudianteDTOs.size())));

        verify(service, times(1)).getAllStudents(asigId, progId, cooId);
    }

    @Test
    public void searchStudentsTest() throws Exception {
        // given
        String nombre = "Cris";
        String urlSearchStudents = url + "/estudiantes/" + nombre;
        List<EstudianteDTO> listaEstudianteDTOs = new ArrayList<>();
        listaEstudianteDTOs.add(new EstudianteDTO(1, "Cristian Gomez Santos", 106813022001L, "cgomezs"));
        listaEstudianteDTOs.add(new EstudianteDTO(2, "Cristobal Colon Lopez", 106813022002L, "ccolonl"));
        given(service.findStudentsByName(nombre, asigId, progId, cooId)).willReturn(listaEstudianteDTOs);
        
        // when
        ResultActions response = mockMvc.perform(MockMvcRequestBuilders
                                        .get(urlSearchStudents));

        // then
        response.andExpect(status().isOk())
                // .andDo(print())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$", hasSize(listaEstudianteDTOs.size())));
        
        verify(service, times(1)).findStudentsByName(nombre, asigId, progId, cooId);
    }

    @Test
    public void regiStudentTest() throws Exception {
        int puId = 1;
        String urlRegisterStudent = url + "/estudiantes/" + puId;
        EstAsignacionDTO estAsignacionDTO = new EstAsignacionDTO(puId, progId, asigId, cooId);
        given(service.registerStudent(cooId, progId, asigId, puId)).willReturn(estAsignacionDTO);

        // when
        ResultActions response = mockMvc.perform(MockMvcRequestBuilders
                                        .post(urlRegisterStudent));

        // then
        response.andExpect(status().isOk())
                // .andDo(print())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.puId").value(puId))
                .andExpect(jsonPath("$.progId").value(progId))
                .andExpect(jsonPath("$.asigId").value(asigId))
                .andExpect(jsonPath("$.cooId").value(cooId));
        
        verify(service, times(1)).registerStudent(cooId, progId, asigId, puId);
    }

    @Test
    public void deleteAllStudentsTest() throws Exception {
        // given
        String urlDeleteStudents = url + "/estudiantes";
        // doNothing().when(service).deleteStudents(cooId, progId, asigId);

        // when
        ResultActions response = mockMvc.perform(MockMvcRequestBuilders
                                        .delete(urlDeleteStudents));
        
        response.andExpect(status().isOk());

        verify(service, times(1)).deleteStudents(cooId, progId, asigId);
    }

    @Test
    public void deleteStudentTest() throws Exception {
        // given
        int puId = 1;
        String urlDeleteStudents = url + "/estudiantes/" + puId;

        // when
        ResultActions response = mockMvc.perform(MockMvcRequestBuilders
                                        .delete(urlDeleteStudents));
        
        response.andExpect(status().isOk());

        verify(service, times(1)).deleteStudent(cooId, progId, asigId, puId);
    }
}
