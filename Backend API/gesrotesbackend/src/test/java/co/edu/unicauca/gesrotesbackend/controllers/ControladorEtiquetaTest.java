package co.edu.unicauca.gesrotesbackend.controllers;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.any;
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

import co.edu.unicauca.gesrotesbackend.services.DTO.AsociacionEtiquetaServicioDTORequest;
import co.edu.unicauca.gesrotesbackend.services.DTO.AsociacionEtiquetaServicioDTOResponse;
import co.edu.unicauca.gesrotesbackend.services.DTO.EscenarioDTO;
import co.edu.unicauca.gesrotesbackend.services.DTO.EtiquetaConServicioDTO;
import co.edu.unicauca.gesrotesbackend.services.DTO.EtiquetaCreadaDTO;
import co.edu.unicauca.gesrotesbackend.services.DTO.EtiquetaPorEscenarioDTO;
import co.edu.unicauca.gesrotesbackend.services.DTO.NuevaEtiquetaDTO;
import co.edu.unicauca.gesrotesbackend.services.DTO.ServicioDTO;
import co.edu.unicauca.gesrotesbackend.services.services.IEtiquetaService;

import java.util.ArrayList;
import java.util.List;

@WebMvcTest(ControladorEtiqueta.class)
public class ControladorEtiquetaTest {
    
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private IEtiquetaService service;

    @Autowired
    private ObjectMapper objectMapper;

    private String url;

    @BeforeEach
    void setUp() {
        url = "http://127.0.0.1:8085/etiquetas/";
    }

    @Test
    void findAllTest() throws Exception {
        // given
        List<EtiquetaCreadaDTO> etiquetas = new ArrayList<>();
        etiquetas.add(new EtiquetaCreadaDTO(1, "Primer piso", "Hospital San José E.S.E"));
        etiquetas.add(new EtiquetaCreadaDTO(2, "Salas", "Hospital San José E.S.E"));
        given(service.obtenerEtiquetasCreadas()).willReturn(etiquetas);

        // when
        ResultActions response = mockMvc.perform(get(url));

        // then
        response.andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$", hasSize(etiquetas.size())))
                .andExpect(jsonPath("$[0].idEtiqueta").value(1))
                .andExpect(jsonPath("$[0].nombreEtiqueta").value("Primer piso"))
                .andExpect(jsonPath("$[0].nombreEscenario").value("Hospital San José E.S.E"))
                .andExpect(jsonPath("$[1].idEtiqueta").value(2))
                .andExpect(jsonPath("$[1].nombreEtiqueta").value("Salas"))
                .andExpect(jsonPath("$[1].nombreEscenario").value("Hospital San José E.S.E"));

        verify(service, times(1)).obtenerEtiquetasCreadas();
    }

    @Test
    void findAllWithServiceTest() throws Exception {
        // given
        String urlFindWithService = url + "onlyService";
        List<EtiquetaConServicioDTO> etiquetasConServicio = new ArrayList<>();
        etiquetasConServicio.add(new EtiquetaConServicioDTO(1, "Primer piso", 
                                                "Cardiologia", "Hospital San José E.S.E"));
        etiquetasConServicio.add(new EtiquetaConServicioDTO(2, "Salas", 
                                                "Urgencias", "Hospital San José E.S.E"));
        given(service.obtenerEtiquetasAsociadas()).willReturn(etiquetasConServicio);

        // when
        ResultActions response = mockMvc.perform(get(urlFindWithService));

        // then
        response.andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$", hasSize(etiquetasConServicio.size())))
                .andExpect(jsonPath("$[0].idEtiqueta").value(1))
                .andExpect(jsonPath("$[0].nombreEtiqueta").value("Primer piso"))
                .andExpect(jsonPath("$[0].nombreServicio").value("Cardiologia"))
                .andExpect(jsonPath("$[0].nombreEscenario").value("Hospital San José E.S.E"))
                .andExpect(jsonPath("$[1].idEtiqueta").value(2))
                .andExpect(jsonPath("$[1].nombreEtiqueta").value("Salas"))
                .andExpect(jsonPath("$[1].nombreServicio").value("Urgencias"))
                .andExpect(jsonPath("$[1].nombreEscenario").value("Hospital San José E.S.E"));

        verify(service, times(1)).obtenerEtiquetasAsociadas();
    }

    @Test
    void findHospitalsTest() throws Exception {
        // given
        String urlFindHospitals = url + "escenarios";
        List<EscenarioDTO> escenarios = new ArrayList<>();
        escenarios.add(new EscenarioDTO(1, "Hospital San José E.S.E"));
        escenarios.add(new EscenarioDTO(2, "Clinica la Estancia"));
        given(service.obetenerEscenarios()).willReturn(escenarios);

        // when
        ResultActions response = mockMvc.perform(get(urlFindHospitals));

        // then
        response.andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$", hasSize(escenarios.size())))
                .andExpect(jsonPath("$[0].idEscenario").value(1))
                .andExpect(jsonPath("$[0].nombreEscenario").value("Hospital San José E.S.E"))
                .andExpect(jsonPath("$[1].idEscenario").value(2))
                .andExpect(jsonPath("$[1].nombreEscenario").value("Clinica la Estancia"));

        verify(service, times(1)).obetenerEscenarios();
    }

    @Test
    void findServicesTest() throws Exception {
        // given
        String urlFindServices = url + "servicios";
        List<ServicioDTO> servicios = new ArrayList<>();
        servicios.add(new ServicioDTO(1, "Servicio 1"));
        servicios.add(new ServicioDTO(2, "Servicio 2"));
        given(service.obtenerServicios()).willReturn(servicios);

        // when
        ResultActions response = mockMvc.perform(get(urlFindServices));

        // then
        response.andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$", hasSize(servicios.size())))
                .andExpect(jsonPath("$[0].idServicio").value(1))
                .andExpect(jsonPath("$[0].nombreServicio").value("Servicio 1"))
                .andExpect(jsonPath("$[1].idServicio").value(2))
                .andExpect(jsonPath("$[1].nombreServicio").value("Servicio 2"));

        verify(service, times(1)).obtenerServicios();
    }

    @Test
    void findByHospitalsTest() throws Exception {
        // given
        int escId = 1;
        String urlFindByHospitals = url + "/escenarios/" + escId + "/etiquetas";
        List<EtiquetaPorEscenarioDTO> etiquetas = new ArrayList<>();
        etiquetas.add(new EtiquetaPorEscenarioDTO(1, "Etiqueta 1"));
        etiquetas.add(new EtiquetaPorEscenarioDTO(2, "Etiqueta 2"));
        given(service.obtenerEtiquetasPorEscenario(escId)).willReturn(etiquetas);

        // when
        ResultActions response = mockMvc.perform(get(urlFindByHospitals));

        // then
        response.andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$", hasSize(etiquetas.size())))
                .andExpect(jsonPath("$[0].idEtiqueta").value(1))
                .andExpect(jsonPath("$[0].nombreEtiqueta").value("Etiqueta 1"))
                .andExpect(jsonPath("$[1].idEtiqueta").value(2))
                .andExpect(jsonPath("$[1].nombreEtiqueta").value("Etiqueta 2"));

        verify(service, times(1)).obtenerEtiquetasPorEscenario(escId);
    }

    @Test
    public void createLabel() throws Exception {
        // given
        NuevaEtiquetaDTO etiquetaRequest = new NuevaEtiquetaDTO("Tercer piso", 1);
        NuevaEtiquetaDTO etiquetaResponse = new NuevaEtiquetaDTO("Tercer piso", 1);
        given(service.crearEtiqueta(any(NuevaEtiquetaDTO.class))).willReturn(etiquetaResponse);

        // when
        ResultActions response = mockMvc.perform(post(url)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(etiquetaRequest)));

        // then
        response.andExpect(status().isOk())
                .andExpect(content().string("Etiqueta con nombre Tercer piso e ID de escenario 1 registrada correctamente"));

    }

    @Test
    void updateLabelTest() throws Exception {
        // given
        String urlAssociate = url + "asociar";
        AsociacionEtiquetaServicioDTORequest request = new AsociacionEtiquetaServicioDTORequest(2, 1);
        AsociacionEtiquetaServicioDTOResponse response = new AsociacionEtiquetaServicioDTOResponse(2, 1);
        given(service.asociarEtiqueta(any(AsociacionEtiquetaServicioDTORequest.class))).willReturn(response);

        // when
        ResultActions resultActions = mockMvc.perform(put(urlAssociate)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)));

        // then
        resultActions.andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.idEtiqueta").value(2))
                .andExpect(jsonPath("$.idServicio").value(1));
    }

    @Test
    void deleteTest() throws Exception {
        // given
        int idEtiqueta = 1;
        String urlDelete = url + idEtiqueta;

        // when
        ResultActions response = mockMvc.perform(delete(urlDelete));

        // then
        response.andExpect(status().isOk())
                .andExpect(content().string("Etiqueta eliminada correctamente"));

        verify(service, times(1)).eliminarEtiqueta(idEtiqueta);
    }

    @Test
    void deleteLabelTest() throws Exception {
        // given
        int idEtiqueta = 1;
        String urlDeleteAssociation = url + idEtiqueta + "/eliminarAsosiacion";

        // when
        ResultActions response = mockMvc.perform(put(urlDeleteAssociation));

        // then
        response.andExpect(status().isOk())
                .andExpect(content().string("Etiqueta eliminada correctamente"));

        verify(service, times(1)).eliminarAsociacion(idEtiqueta);
    }
}
