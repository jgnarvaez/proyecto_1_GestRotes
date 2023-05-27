package co.edu.unicauca.gesrotesbackend.services;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import co.edu.unicauca.gesrotesbackend.exceptions.HTTPException;
import co.edu.unicauca.gesrotesbackend.models.Asignacion;
import co.edu.unicauca.gesrotesbackend.models.AsignacionId;
import co.edu.unicauca.gesrotesbackend.models.Asignatura;
import co.edu.unicauca.gesrotesbackend.models.CoordinadorAsignatura;
import co.edu.unicauca.gesrotesbackend.models.EstAsignacion;
import co.edu.unicauca.gesrotesbackend.models.EstAsignacionId;
import co.edu.unicauca.gesrotesbackend.models.Estudiante;
import co.edu.unicauca.gesrotesbackend.models.Programa;
import co.edu.unicauca.gesrotesbackend.repositories.AsignacionRepository;
import co.edu.unicauca.gesrotesbackend.repositories.AsignaturaRepository;
import co.edu.unicauca.gesrotesbackend.repositories.CoordinadorAsigRepository;
import co.edu.unicauca.gesrotesbackend.repositories.EstAsignacionRepository;
import co.edu.unicauca.gesrotesbackend.repositories.EstudianteRepository;
import co.edu.unicauca.gesrotesbackend.repositories.ProgramaRepository;
import co.edu.unicauca.gesrotesbackend.services.services.Impl.AsignacionEstudiantesServiceImpl;

@ExtendWith(MockitoExtension.class)
public class AsignacionEstudiantesServiceImplTest {
    @Mock
    private AsignacionRepository asignacionRepository;
    @Mock
    private EstAsignacionRepository estAsignacionRepository;
    @Mock
    private EstudianteRepository estudianteRepository;
    @Mock
    private ProgramaRepository programaRepository;
    @Mock
    private AsignaturaRepository asignaturaRepository;
    @Mock
    private CoordinadorAsigRepository coordinadorAsigRepository;

    private AsignacionEstudiantesServiceImpl serviceUnderTest;

    @BeforeEach
    void setUp(){
        serviceUnderTest = new AsignacionEstudiantesServiceImpl(asignacionRepository, 
                                                                estAsignacionRepository, 
                                                                estudianteRepository, 
                                                                programaRepository, 
                                                                asignaturaRepository, 
                                                                coordinadorAsigRepository);
    }

    @Test
    void test_Can_RegisterStudent() {
        // given
        int idEstudiante = 1;
        int idPrograma = 1;
        int idAsignatura = 1;
        int idCoordinador = 1;
        
        Estudiante estudiante = new Estudiante();
        estudiante.setId(idEstudiante);
        estudiante.setNombres("Cristian");
        estudiante.setApellidos("Gomez Santos");
        estudiante.setIdentificacion(106813022001L);
        estudiante.setUsuario("cgomezs");
        estudiante.setFotoPerfil("url foto");
        
        Programa programa = new Programa(idPrograma, "Enfermería");
        
        Asignatura asignatura = new Asignatura(idAsignatura, "Cuidado de Enfermería en salud comunitaria y familiar");
        
        CoordinadorAsignatura coordinadorAsignatura = new CoordinadorAsignatura();
        coordinadorAsignatura.setId(idCoordinador);
        coordinadorAsignatura.setNombres("Jorge Ivan");
        coordinadorAsignatura.setApellidos("Solano");
        coordinadorAsignatura.setCorreo("jsolano");
        coordinadorAsignatura.setClave("1234");
        coordinadorAsignatura.setFotoPerfil("url foto");
        
        AsignacionId idAsignacion = new AsignacionId(programa, asignatura, coordinadorAsignatura);
        
        Asignacion asignacion = new Asignacion(idAsignacion);
        
        EstAsignacionId idEstAsignacion = new EstAsignacionId(estudiante, asignacion);
        
        EstAsignacion estAsignacion = new EstAsignacion(idEstAsignacion, false);

        given(estudianteRepository.findById(idEstudiante)).willReturn(Optional.of(estudiante));
        given(programaRepository.findById(idPrograma)).willReturn(Optional.of(programa));
        given(asignaturaRepository.findById(idAsignatura)).willReturn(Optional.of(asignatura));
        given(coordinadorAsigRepository.findById(idCoordinador)).willReturn(Optional.of(coordinadorAsignatura));
        given(asignacionRepository.findById(idAsignacion)).willReturn(Optional.of(asignacion));

        // when
        serviceUnderTest.registerStudent(idCoordinador, idPrograma, idAsignatura, idEstudiante);
        
        // then
        ArgumentCaptor<EstAsignacion> asignacionArgumentCaptor = ArgumentCaptor.forClass(EstAsignacion.class);
        
        verify(estAsignacionRepository).save(asignacionArgumentCaptor.capture());
        
        EstAsignacion estAsignacionCapturada = asignacionArgumentCaptor.getValue();

        assertThat(estAsignacionCapturada.getId().getEstudiante())
                .isEqualTo(estAsignacion.getId().getEstudiante());
        assertThat(estAsignacionCapturada.getId().getAsignacion().getId().getPrograma())
                .isEqualTo(estAsignacion.getId().getAsignacion().getId().getPrograma());
        assertThat(estAsignacionCapturada.getId().getAsignacion().getId().getAsignatura())
                .isEqualTo(estAsignacion.getId().getAsignacion().getId().getAsignatura());
        assertThat(estAsignacionCapturada.getId().getAsignacion().getId().getCoordinador())
                .isEqualTo(estAsignacion.getId().getAsignacion().getId().getCoordinador());
    }

    @Test
    void testRegisterStudent_ThrowsException_WhenStudentDoesNotExist() {
        // given
        int cooId = 1;
        int progId = 1;
        int asigId = 1;
        int estId = 1;

        given(estudianteRepository.findById(estId)).willReturn(Optional.empty());

        // when y then
        assertThatThrownBy(() -> serviceUnderTest.registerStudent(cooId, progId, asigId, estId))
                .isInstanceOf(HTTPException.class)
                .hasMessageContaining("No se encontró el estudiante con el ID: " + estId);
    }

    @Test
    void testRegisterStudent_ThrowsException_WhenProgramDoesNotExist() {
        // given
        int cooId = 1;
        int progId = 1;
        int asigId = 3;
        int estId = 4;

        given(estudianteRepository.findById(estId)).willReturn(Optional.of(new Estudiante()));
        given(programaRepository.findById(progId)).willReturn(Optional.empty());

        // when y then
        assertThatThrownBy(() -> serviceUnderTest.registerStudent(cooId, progId, asigId, estId))
                .isInstanceOf(HTTPException.class)
                .hasMessageContaining("No se encontró el programa con el ID: " + progId);
    }

    @Test
    void testRegisterStudent_ThrowsException_WhenSubjectDoesNotExist() {
        // given
        int cooId = 1;
        int progId = 1;
        int asigId = 3;
        int estId = 4;

        given(estudianteRepository.findById(estId)).willReturn(Optional.of(new Estudiante()));
        given(programaRepository.findById(progId)).willReturn(Optional.of(new Programa()));
        given(asignaturaRepository.findById(asigId)).willReturn(Optional.empty());

        // when y then
        assertThatThrownBy(() -> serviceUnderTest.registerStudent(cooId, progId, asigId, estId))
                .isInstanceOf(HTTPException.class)
                .hasMessageContaining("No se encontró la asignatura con el ID: " + asigId);
    }

    @Test
    void testRegisterStudent_ThrowsException_WhenCoordinatorDoesNotExist() {
        // given
        int cooId = 1;
        int progId = 1;
        int asigId = 3;
        int estId = 4;

        given(estudianteRepository.findById(estId)).willReturn(Optional.of(new Estudiante()));
        given(programaRepository.findById(progId)).willReturn(Optional.of(new Programa()));
        given(asignaturaRepository.findById(asigId)).willReturn(Optional.of(new Asignatura()));
        given(coordinadorAsigRepository.findById(cooId)).willReturn(Optional.empty());

        // when y then
        assertThatThrownBy(() -> serviceUnderTest.registerStudent(cooId, progId, asigId, estId))
                .isInstanceOf(HTTPException.class)
                .hasMessageContaining("No se encontró el coordinador con el ID: " + cooId);
    }

    @Test
    void testRegisterStudent_ThrowsException_WhenAsignacionDoesNotExist() {
        // given
        int cooId = 1;
        int progId = 1;
        int asigId = 3;
        int estId = 4;

        given(estudianteRepository.findById(estId)).willReturn(Optional.of(new Estudiante()));
        given(programaRepository.findById(progId)).willReturn(Optional.of(new Programa()));
        given(asignaturaRepository.findById(asigId)).willReturn(Optional.of(new Asignatura()));
        given(coordinadorAsigRepository.findById(cooId)).willReturn(Optional.of(new CoordinadorAsignatura()));
        given(asignacionRepository.findById(any())).willReturn(Optional.empty());

        // when y then
        assertThatThrownBy(() -> serviceUnderTest.registerStudent(cooId, progId, asigId, estId))
                .isInstanceOf(HTTPException.class)
                .hasMessageContaining("No se encontró la asignación");
    }

    @Test
    void test_Can_GetAllRegisteredStudents(){
        // given
        int asigId = 3;
        int progId = 1;
        int cooId = 1;
        // when
        serviceUnderTest.getAllStudents(asigId, progId, cooId);
        // then
        verify(estudianteRepository).getStudentsInfo(asigId, progId, cooId);
    }

    @Test
    void test_Can_FindAllRegisteredStudentsByName(){
        // given
        int asigId = 3;
        int progId = 1;
        int cooId = 1;
        String cadena = "Cr";
        // when
        serviceUnderTest.findStudentsByName(cadena, asigId, progId, cooId);
        // then
        verify(estudianteRepository).getStudentsInfoByName(cadena, asigId, progId, cooId);
    }

    @Test
    void test_Can_DeleteAllRegisteredStudents(){
        // given
        int progId = 1;
        int asigId = 3;
        int cooId = 1;
        // when
        serviceUnderTest.deleteStudents(cooId, progId, asigId);
        // then
        verify(estAsignacionRepository).deleteAllStudents(progId, asigId, cooId);
    }

    @Test
    void test_Can_DeleteOneRegisteredStudent(){
        // given
        int progId = 1;
        int asigId = 3;
        int cooId = 1;
        int estId = 4;
        // when
        serviceUnderTest.deleteStudent(cooId, progId, asigId, estId);
        // then
        verify(estAsignacionRepository).deleteStudent(progId, asigId, cooId, estId);
    }
}
