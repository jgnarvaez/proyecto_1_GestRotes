package co.edu.unicauca.gesrotesbackend.services;

import static org.mockito.Mockito.verify;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.BDDMockito.given;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import co.edu.unicauca.gesrotesbackend.exceptions.HTTPException;
import co.edu.unicauca.gesrotesbackend.models.Etiqueta;
import co.edu.unicauca.gesrotesbackend.models.Servicio;
import co.edu.unicauca.gesrotesbackend.repositories.EscenarioRepository;
import co.edu.unicauca.gesrotesbackend.repositories.EtiquetaRepository;
import co.edu.unicauca.gesrotesbackend.repositories.ServicioRepository;
import co.edu.unicauca.gesrotesbackend.services.DTO.AsociacionEtiquetaServicioDTORequest;
import co.edu.unicauca.gesrotesbackend.services.DTO.NuevaEtiquetaDTO;
import co.edu.unicauca.gesrotesbackend.services.Mapper.EscenarioDTOMapper;
import co.edu.unicauca.gesrotesbackend.services.Mapper.ServicioDTOMapper;
import co.edu.unicauca.gesrotesbackend.services.services.Impl.EtiquetaServiceImpl;

@ExtendWith(MockitoExtension.class)
public class EtiquetaServiceImplTest {
    @Mock
    private EtiquetaRepository etiquetaRepository;
    @Mock
    private EscenarioRepository escenarioRepository;
    @Mock
    private ServicioRepository servicioRepository;
    @Mock
    private EscenarioDTOMapper escenarioDTOMapper;
    @Mock
    private ServicioDTOMapper servicioDTOMapper;

    private EtiquetaServiceImpl serviceUnderTest;

    @BeforeEach
    void setUp() {
        serviceUnderTest = new EtiquetaServiceImpl(etiquetaRepository, 
                                                    escenarioRepository, 
                                                    servicioRepository, 
                                                    escenarioDTOMapper, 
                                                    servicioDTOMapper);
    }

    @Test
    void test_Can_GetAllCreatedLabels() {
        // when
        serviceUnderTest.obtenerEtiquetasCreadas();
        // then
        verify(etiquetaRepository).obtenerEtiquetasConEscenario();
    }

    @Test
    void test_Can_GetAllAsociatedLabels() {
        // when
        serviceUnderTest.obtenerEtiquetasAsociadas();
        // then
        verify(etiquetaRepository).obtenerEtiquetasConServicio();
    }

    @Test
    void test_Can_GetAllLabelsByScenario() {
        // given
        int idEscenario = 1;
        // when
        serviceUnderTest.obtenerEtiquetasPorEscenario(idEscenario);
        // then
        verify(etiquetaRepository).obtenerPorEscenario(idEscenario);
    }
    
    @Test
    void test_Can_GetAllScenarios() {
        // when
        serviceUnderTest.obetenerEscenarios();
        // then
        verify(escenarioRepository).findAll();
    }
    
    @Test
    void test_Can_GetAllServices() {
        // when
        serviceUnderTest.obtenerServicios();
        // then
        verify(servicioRepository).findAll();
    }

    @Test
    void test_Can_CreateNewLabel() {
        // given
        NuevaEtiquetaDTO nuevaEtiqueta = new NuevaEtiquetaDTO("Segundo Piso", 1); 
        given(etiquetaRepository.alreadyExists(nuevaEtiqueta.getNombreEtiqueta(), nuevaEtiqueta.getIdEscenario())).willReturn(0);
        given(etiquetaRepository.saveLabel(nuevaEtiqueta.getNombreEtiqueta(), nuevaEtiqueta.getIdEscenario())).willReturn(1);
        
        // when
        serviceUnderTest.crearEtiqueta(nuevaEtiqueta);

        //then
        verify(etiquetaRepository).saveLabel(nuevaEtiqueta.getNombreEtiqueta(), nuevaEtiqueta.getIdEscenario());
    }

    @Test
    void test_CreateNewLabel_ThrowsException_WhenLabelAlreadyExists() {
        // given
        NuevaEtiquetaDTO nuevaEtiqueta = new NuevaEtiquetaDTO("Segundo Piso", 1); 
        String mensajeError = "Ya existe una etiqueta con nombre " + nuevaEtiqueta.getNombreEtiqueta() + 
                                " asociada al escenario con ID: " + nuevaEtiqueta.getIdEscenario();
        given(etiquetaRepository.alreadyExists(nuevaEtiqueta.getNombreEtiqueta(), nuevaEtiqueta.getIdEscenario())).willReturn(1);
        
        // when y then
        assertThatThrownBy(() -> serviceUnderTest.crearEtiqueta(nuevaEtiqueta))
                .isInstanceOf(HTTPException.class)
                .hasMessageContaining(mensajeError);        
    }

    @Test
    void test_Cannot_CreateNewLabel() {
        // given
        NuevaEtiquetaDTO nuevaEtiqueta = new NuevaEtiquetaDTO("Segundo Piso", 1); 
        String mensajeError = "No se pudo crear la etiqueta con el nombre " + nuevaEtiqueta.getNombreEtiqueta() +
                                " y con ID de escenario: " + nuevaEtiqueta.getIdEscenario();
        given(etiquetaRepository.alreadyExists(nuevaEtiqueta.getNombreEtiqueta(), nuevaEtiqueta.getIdEscenario())).willReturn(0);
        given(etiquetaRepository.saveLabel(nuevaEtiqueta.getNombreEtiqueta(), nuevaEtiqueta.getIdEscenario())).willReturn(0);
        
        // when y then
        assertThatThrownBy(() -> serviceUnderTest.crearEtiqueta(nuevaEtiqueta))
                .isInstanceOf(HTTPException.class)
                .hasMessageContaining(mensajeError);        
    }

    @Test
    void test_Can_AssociateLabel() {
        // given
        AsociacionEtiquetaServicioDTORequest etiqueta = new AsociacionEtiquetaServicioDTORequest(3, 2);
        given(etiquetaRepository.findById(etiqueta.getIdEtiqueta())).willReturn(Optional.of(new Etiqueta()));
        given(servicioRepository.findById(etiqueta.getIdServicio())).willReturn(Optional.of(new Servicio()));
        given(etiquetaRepository.existsAssociation(etiqueta.getIdEtiqueta(), etiqueta.getIdServicio())).willReturn(0);

        // when
        serviceUnderTest.asociarEtiqueta(etiqueta);

        // then
        verify(etiquetaRepository).asociarEtiquetaConServicio(etiqueta.getIdEtiqueta(), etiqueta.getIdServicio());
    }

    @Test
    void test_AssociateLabel_TrhowsException_WhenLabelDoesNotExists() {
        // given
        AsociacionEtiquetaServicioDTORequest etiqueta = new AsociacionEtiquetaServicioDTORequest(350, 2);
        String mensajeError = "No se encontró la etiqueta con el ID: " + etiqueta.getIdEtiqueta();
        given(etiquetaRepository.findById(etiqueta.getIdEtiqueta())).willReturn(Optional.empty());

        // when y then
        assertThatThrownBy(() -> serviceUnderTest.asociarEtiqueta(etiqueta))
                .isInstanceOf(HTTPException.class)
                .hasMessageContaining(mensajeError);
    }

    @Test
    void test_AssociateLabel_TrhowsException_WhenServiceDoesNotExists() {
        // given
        AsociacionEtiquetaServicioDTORequest etiqueta = new AsociacionEtiquetaServicioDTORequest(3, 250);
        String mensajeError = "No se encontró el servicio con el ID: " + etiqueta.getIdServicio();
        given(etiquetaRepository.findById(etiqueta.getIdEtiqueta())).willReturn(Optional.of(new Etiqueta()));
        given(servicioRepository.findById(etiqueta.getIdServicio())).willReturn(Optional.empty());

        // when y then
        assertThatThrownBy(() -> serviceUnderTest.asociarEtiqueta(etiqueta))
                .isInstanceOf(HTTPException.class)
                .hasMessageContaining(mensajeError);
    }

    @Test
    void test_AssociateLabel_TrhowsException_WhenAnAssociationAlreadyExists() {
        // given
        AsociacionEtiquetaServicioDTORequest etiqueta = new AsociacionEtiquetaServicioDTORequest(3, 2);
        String mensajeError = "Ya existe una asociación entre la etiqueta con ID " + etiqueta.getIdEtiqueta() + 
                                " y el servicio con ID "+ etiqueta.getIdServicio();        
        given(etiquetaRepository.findById(etiqueta.getIdEtiqueta())).willReturn(Optional.of(new Etiqueta()));
        given(servicioRepository.findById(etiqueta.getIdServicio())).willReturn(Optional.of(new Servicio()));
        given(etiquetaRepository.existsAssociation(etiqueta.getIdEtiqueta(), etiqueta.getIdServicio())).willReturn(1);

        // when y then
        assertThatThrownBy(() -> serviceUnderTest.asociarEtiqueta(etiqueta))
                .isInstanceOf(HTTPException.class)
                .hasMessageContaining(mensajeError);
    }

    @Test
    void test_Can_DeleteLabel() {
        // given
        int idEtiqueta = 1;
        given(etiquetaRepository.findById(idEtiqueta)).willReturn(Optional.of(new Etiqueta()));
        
        // when
        serviceUnderTest.eliminarEtiqueta(idEtiqueta);

        // then
        verify(etiquetaRepository).eliminarServicioAsociado(idEtiqueta);
        verify(etiquetaRepository).deleteById(idEtiqueta);
    }

    @Test
    void test_Cannot_DeleteLabel() {
        // given
        int idEtiqueta = 100;
        String mensajeError = "No se ha encontrado la etiqueta con el ID " + idEtiqueta;
        given(etiquetaRepository.findById(idEtiqueta)).willReturn(Optional.empty());
        
        // when y then
        assertThatThrownBy(() -> serviceUnderTest.eliminarEtiqueta(idEtiqueta))
                .isInstanceOf(HTTPException.class)
                .hasMessageContaining(mensajeError);
    }

    @Test
    void test_Can_DeleteAssociation() {
        // given
        int idEtiqueta = 1;
        given(etiquetaRepository.findById(idEtiqueta)).willReturn(Optional.of(new Etiqueta()));
        
        // when
        serviceUnderTest.eliminarAsociacion(idEtiqueta);

        // then
        verify(etiquetaRepository).eliminarServicioAsociado(idEtiqueta);
    }

    @Test
    void test_Cannot_DeleteAssociation() {
        // given
        int idEtiqueta = 100;
        String mensajeError = "No se ha encontrado la etiqueta con el ID " + idEtiqueta;
        given(etiquetaRepository.findById(idEtiqueta)).willReturn(Optional.empty());

        // when y then
        assertThatThrownBy(() -> serviceUnderTest.eliminarAsociacion(idEtiqueta))
                .isInstanceOf(HTTPException.class)
                .hasMessageContaining(mensajeError);
    }
}
