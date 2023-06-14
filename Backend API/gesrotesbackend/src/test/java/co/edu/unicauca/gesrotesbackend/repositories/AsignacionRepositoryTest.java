package co.edu.unicauca.gesrotesbackend.repositories;

import static org.assertj.core.api.Assertions.assertThat;

import co.edu.unicauca.gesrotesbackend.services.DTO.AsignacionDTO;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import co.edu.unicauca.gesrotesbackend.models.Asignacion;
import co.edu.unicauca.gesrotesbackend.models.AsignacionId;
import co.edu.unicauca.gesrotesbackend.models.Asignatura;
import co.edu.unicauca.gesrotesbackend.models.CoordinadorAsignatura;
import co.edu.unicauca.gesrotesbackend.models.Programa;

import java.util.List;

@DataJpaTest
public class AsignacionRepositoryTest {

    @Autowired
    private AsignacionRepository underTest;

    @Autowired
    private ProgramaRepository programaRepository;

    @Autowired
    private CoordinadorAsigRepository coordinadorAsigRepository;

    @Autowired
    private AsignaturaRepository asignaturaRepository;

    @BeforeEach
    void setUp(){
        //* Programas
        Programa programaUno = new Programa(1, "Enfermería");

        Programa programaDos = new Programa(2, "Fisioterapia");

        programaRepository.save(programaUno);
        programaRepository.save(programaDos);

        //* Coordinadores
        CoordinadorAsignatura coordinadorAsignaturaUno = new CoordinadorAsignatura();
        coordinadorAsignaturaUno.setId(1);
        coordinadorAsignaturaUno.setNombres("Jorge Ivan");
        coordinadorAsignaturaUno.setApellidos("Solano");
        coordinadorAsignaturaUno.setCorreo("jsolano");
        coordinadorAsignaturaUno.setClave("123");
        coordinadorAsignaturaUno.setRol("Coordinador_de_asignatura");
        coordinadorAsignaturaUno.setFotoPerfil("url foto");

        CoordinadorAsignatura coordinadorAsignaturaDos = new CoordinadorAsignatura();
        coordinadorAsignaturaDos.setId(2);
        coordinadorAsignaturaDos.setNombres("Maria Alejandra");
        coordinadorAsignaturaDos.setApellidos("Zapata");
        coordinadorAsignaturaDos.setCorreo("mzapata");
        coordinadorAsignaturaDos.setClave("12345");
        coordinadorAsignaturaDos.setRol("Coordinador_de_asignatura");
        coordinadorAsignaturaDos.setFotoPerfil("url foto");

        coordinadorAsigRepository.save(coordinadorAsignaturaUno);
        coordinadorAsigRepository.save(coordinadorAsignaturaDos);

        //Asignaturas
        Asignatura asignaturaUno = new Asignatura(1, "Cuidado de Enfermería en salud comunitaria y familiar");
        Asignatura asignaturaDos = new Asignatura(2, "Cuidado a las personas con Procesos infecciosos");
        Asignatura asignaturaTres = new Asignatura(3, "Cuidado de Enfermería en Salud Mental");
        Asignatura asignaturaCuatro = new Asignatura(4, "Cuidado de Enfermería al Adulto y al Anciano I");
        Asignatura asignaturaCinco = new Asignatura(5, "Cuidado de Enfermería al Adulto y al Anciano II");
        Asignatura asignaturaSeis = new Asignatura(6, "Fundamentos para el Cuidado de Enfermería");
        Asignatura asignaturaSiete = new Asignatura(7, "Cuidado al Niño y al Adolescente");
        Asignatura asignaturaOcho = new Asignatura(8, "Práctica en Fisioterapia Osteomuscular");
        Asignatura asignaturaNueve = new Asignatura(9, "Práctica en Fisioterapia Neurológica");
        Asignatura asignaturaDiez = new Asignatura(10, "Práctica en Fisioterapia Cardiopulmunar y Cuidado Crítico");

        asignaturaRepository.save(asignaturaUno);
        asignaturaRepository.save(asignaturaDos);
        asignaturaRepository.save(asignaturaTres);
        asignaturaRepository.save(asignaturaCuatro);
        asignaturaRepository.save(asignaturaCinco);
        asignaturaRepository.save(asignaturaSeis);
        asignaturaRepository.save(asignaturaSiete);
        asignaturaRepository.save(asignaturaOcho);
        asignaturaRepository.save(asignaturaNueve);
        asignaturaRepository.save(asignaturaDiez);

        //* AsignacionesIDs para coordinador 1
        AsignacionId idUno = new AsignacionId(programaUno, asignaturaUno, coordinadorAsignaturaUno);
        AsignacionId idDos = new AsignacionId(programaUno, asignaturaDos, coordinadorAsignaturaUno);
        AsignacionId idTres = new AsignacionId(programaUno, asignaturaTres, coordinadorAsignaturaUno);
        AsignacionId idCuatro = new AsignacionId(programaUno, asignaturaCuatro, coordinadorAsignaturaUno);
        AsignacionId idCinco = new AsignacionId(programaUno, asignaturaCinco, coordinadorAsignaturaUno);
        AsignacionId idSeis = new AsignacionId(programaUno, asignaturaSeis, coordinadorAsignaturaUno);
        AsignacionId idSiete = new AsignacionId(programaUno, asignaturaSiete, coordinadorAsignaturaUno);
        //* AsignacionesIDs para coordinador 2
        AsignacionId idOcho = new AsignacionId(programaDos, asignaturaOcho, coordinadorAsignaturaDos);
        AsignacionId idNueve = new AsignacionId(programaDos, asignaturaNueve, coordinadorAsignaturaDos);
        AsignacionId idDiez = new AsignacionId(programaDos, asignaturaDiez, coordinadorAsignaturaDos);
        //Asignaciones
        Asignacion asignacionUno = new Asignacion(idUno);
        Asignacion asignacionDos = new Asignacion(idDos);
        Asignacion asignacionTres = new Asignacion(idTres);
        Asignacion asignacionCuatro = new Asignacion(idCuatro);
        Asignacion asignacionCinco = new Asignacion(idCinco);
        Asignacion asignacionSeis = new Asignacion(idSeis);
        Asignacion asignacionSiete = new Asignacion(idSiete);
        Asignacion asignacionOcho = new Asignacion(idOcho);
        Asignacion asignacionNueve = new Asignacion(idNueve);
        Asignacion asignacionDiez = new Asignacion(idDiez);
        underTest.save(asignacionUno);
        underTest.save(asignacionDos);
        underTest.save(asignacionTres);
        underTest.save(asignacionCuatro);
        underTest.save(asignacionCinco);
        underTest.save(asignacionSeis);
        underTest.save(asignacionSiete);
        underTest.save(asignacionOcho);
        underTest.save(asignacionNueve);
        underTest.save(asignacionDiez);
    }

    @DisplayName("Test para obtener una asignacion por su ID compuesto")
    @Test
    void itShouldFindRowById(){
        // Given
        int idCoordinador = 1;

        // When
        List<AsignacionDTO> asignacionDTOList = underTest.getByCoordinatorId(idCoordinador);

        // Then
        assertThat(asignacionDTOList).hasSize(7);
    }
}