package co.edu.unicauca.gesrotesbackend.repositories;

import static org.assertj.core.api.Assertions.assertThat;

import co.edu.unicauca.gesrotesbackend.models.EscenarioPractica;
import co.edu.unicauca.gesrotesbackend.models.Etiqueta;
import co.edu.unicauca.gesrotesbackend.models.Servicio;
import co.edu.unicauca.gesrotesbackend.services.DTO.EtiquetaConServicioDTO;
import co.edu.unicauca.gesrotesbackend.services.DTO.EtiquetaCreadaDTO;
import co.edu.unicauca.gesrotesbackend.services.DTO.EtiquetaPorEscenarioDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

@DataJpaTest
public class EtiquetaRepositoryTest {

    @Autowired
    private EtiquetaRepository underTest;

    @Autowired
    private ServicioRepository servicioRepository;

    @Autowired
    private EscenarioRepository escenarioRepository;

    @BeforeEach
    void setUp(){
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

        Etiqueta etiquetaUno = new Etiqueta();
        etiquetaUno.setNombre("Salas");
        etiquetaUno.setEscenario(escenarioPracticaUno);
        etiquetaUno.setServicio(servicioDos);
        Etiqueta etiquetaDos = new Etiqueta();
        etiquetaDos.setNombre("Primer piso");
        etiquetaDos.setEscenario(escenarioPracticaDos);
        etiquetaDos.setServicio(servicioUno);
        underTest.save(etiquetaUno);
        underTest.save(etiquetaDos);
    }

    @Test
    void shouldVerifyIfAlreadyExistsTrueCase() {
        // given
        String nombreEtiqueta = "Primer piso";
        int escenarioId = 2;

        // when
        int existe = underTest.alreadyExists(nombreEtiqueta, escenarioId);

        // then
        assertThat(existe).isEqualTo(1);
    }

    @Test
    void shouldVerifyIfAlreadyExistsFalseCase() {
        // given
        String nombreEtiqueta = "Segundo piso";
        int escenarioId = 2;

        // when
        int existe = underTest.alreadyExists(nombreEtiqueta, escenarioId);

        // then
        assertThat(existe).isEqualTo(0);
    }

    @Test
    void shouldSaveLabelByNameAndIdScenario() {
        // given
        String nombreEtiqueta = "Segundo piso";
        int escenarioId = 2;

        // when
        int etiquetaGuardada = underTest.saveLabel(nombreEtiqueta, escenarioId);

        // then
        assertThat(etiquetaGuardada).isGreaterThan(0);
    }

    @Test
    void shouldGetLabelsWithScenario() {
        // when
        underTest.saveLabel("Segundo piso", 2);
        List<EtiquetaCreadaDTO> etiquetaCreadaDTOList = underTest.obtenerEtiquetasConEscenario();

        // then
        assertThat(etiquetaCreadaDTOList).hasSize(3);
    }

    @Test
    void shouldGetLabelsWithService() {
        // when
        List<EtiquetaConServicioDTO> etiquetaConServicioDTOList = underTest.obtenerEtiquetasConServicio();

        // then
        assertThat(etiquetaConServicioDTOList).hasSize(2);
    }

    @Test
    void shouldGetLabelsByScenario() {
        // given
        int escenarioId = 1;

        // when
        List<EtiquetaPorEscenarioDTO> etiquetaPorEscenarioDTOSList = underTest.obtenerPorEscenario(escenarioId);

        // then
        assertThat(etiquetaPorEscenarioDTOSList).hasSize(1);
    }

    @Test
    void shouldAssociateLabelWithService() {
        // given
        int etiquetaId = 3;
        int servicioId = 3;

        // when
        underTest.saveLabel("Tercer piso", 1);
        underTest.asociarEtiquetaConServicio(etiquetaId, servicioId);
        List<EtiquetaConServicioDTO> etiquetaConServicioDTOList = underTest.obtenerEtiquetasConServicio();

        // then
        assertThat(etiquetaConServicioDTOList).hasSize(3);
    }

    @Test
    void shouldVerifyIfExistsAnAssociationTrueCase() {
        // given
        int etiquetaId = 1;
        int servicioId = 2;

        // when
        int existeAsociacion = underTest.existsAssociation(etiquetaId, servicioId);

        // then
        assertThat(existeAsociacion).isGreaterThan(0);
    }

    @Test
    void shouldVerifyIfExistsAnAssociationFalseCase() {
        // given
        int etiquetaId = 1;
        int servicioId = 1;

        // when
        int existeAsociacion = underTest.existsAssociation(etiquetaId, servicioId);

        // then
        assertThat(existeAsociacion).isEqualTo(0);
    }

    @Test
    void shouldDeleteAnAssociatedService() {
        // given
        int etiquetaId = 1;

        // when
        underTest.eliminarServicioAsociado(etiquetaId);
        List<EtiquetaConServicioDTO> etiquetaConServicioDTOList = underTest.obtenerEtiquetasConServicio();

        // then
        assertThat(etiquetaConServicioDTOList).hasSize(1);
    }
}