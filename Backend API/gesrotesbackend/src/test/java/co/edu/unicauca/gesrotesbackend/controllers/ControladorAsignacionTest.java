package co.edu.unicauca.gesrotesbackend.controllers;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.BDDMockito.given;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import co.edu.unicauca.gesrotesbackend.services.DTO.AsignacionDTO;
import co.edu.unicauca.gesrotesbackend.services.services.IAsignacionService;

import java.util.ArrayList;
import java.util.List;

@WebMvcTest(ControladorAsignacion.class)
public class ControladorAsignacionTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private IAsignacionService serviceUnderTest;

    @Test
    void test_Can_FindAllSubjects() throws Exception {
        // given
        int cooId = 1;
        int progId = 1;
        String url = "http://127.0.0.1:8085/" + cooId + "/" + progId + "/asignaturas/";
        List<AsignacionDTO> listaAsignacionDTOs = new ArrayList<>();
        listaAsignacionDTOs.add(new AsignacionDTO(1, "Cuidado de Enfermería en salud comunitaria y familiar", "Enfermeria"));
        listaAsignacionDTOs.add(new AsignacionDTO(2, "Cuidado a las personas con Procesos infecciosos", "Enfermeria"));
        listaAsignacionDTOs.add(new AsignacionDTO(3, "Cuidado de Enfermería en Salud Mental", "Enfermeria"));
        listaAsignacionDTOs.add(new AsignacionDTO(4, "Cuidado de Enfermería al Adulto y al Anciano I", "Enfermeria"));
        listaAsignacionDTOs.add(new AsignacionDTO(5, "Fundamentos para el Cuidado de Enfermería", "Enfermeria"));
        given(serviceUnderTest.getAllByCoo(cooId)).willReturn(listaAsignacionDTOs);

        // when
        ResultActions response = mockMvc.perform(MockMvcRequestBuilders
                                        .get(url));

        // then
        response.andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(listaAsignacionDTOs.size())));
    }

}
