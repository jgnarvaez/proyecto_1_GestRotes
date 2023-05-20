// package co.edu.unicauca.gesrotesbackend.repositories;

// import static org.assertj.core.api.Assertions.assertThat;

// import org.junit.jupiter.api.AfterEach;
// import org.junit.jupiter.api.BeforeEach;
// import org.junit.jupiter.api.DisplayName;
// import org.junit.jupiter.api.Test;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

// import co.edu.unicauca.gesrotesbackend.models.Asignacion;
// import co.edu.unicauca.gesrotesbackend.models.AsignacionId;
// import co.edu.unicauca.gesrotesbackend.models.Asignatura;
// import co.edu.unicauca.gesrotesbackend.models.Coordinador;
// import co.edu.unicauca.gesrotesbackend.models.CoordinadorAsignatura;
// import co.edu.unicauca.gesrotesbackend.models.Programa;
// import co.edu.unicauca.gesrotesbackend.models.Rol;

// @DataJpaTest
// public class AsignacionRepositoryTests {

//     @Autowired
//     private AsignacionRepository underTest;

//     @BeforeEach
//     void setUp(){
//         //Programas
//         Programa programaUno = new Programa(1, "Enfermería");
//         Programa programaDos = new Programa(2, "Fisioterapia");
//         //Coordinadores
//         CoordinadorAsignatura coordinadorAsignaturaUno = new CoordinadorAsignatura();
//         coordinadorAsignaturaUno.setId(1);
//         coordinadorAsignaturaUno.setNombres("Jorge Ivan");
//         coordinadorAsignaturaUno.setApellidos("Solano");
//         coordinadorAsignaturaUno.setCorreo("jsolano");
//         coordinadorAsignaturaUno.setClave("123");
//         coordinadorAsignaturaUno.setClave("Coordinador_de_asignatura");
//         coordinadorAsignaturaUno.setFotoPerfil("url foto");
//         CoordinadorAsignatura coordinadorAsignaturaDos = new CoordinadorAsignatura();
//         coordinadorAsignaturaDos.setId(2);
//         coordinadorAsignaturaDos.setNombres("Maria Alejandra");
//         coordinadorAsignaturaDos.setApellidos("Zapata");
//         coordinadorAsignaturaDos.setCorreo("mzapata");
//         coordinadorAsignaturaDos.setClave("12345");
//         coordinadorAsignaturaDos.setClave("Coordinador_de_asignatura");
//         coordinadorAsignaturaDos.setFotoPerfil("url foto");
//         //Asignaturas
//         Asignatura asignaturaUno = new Asignatura(1, "Cuidado de Enfermería en salud comunitaria y familiar");
//         Asignatura asignaturaDos = new Asignatura(2, "Cuidado de Enfermería en salud comunitaria y familiar");
//         Asignatura asignaturaTres = new Asignatura(3, "Cuidado de Enfermería en salud comunitaria y familiar");
//         Asignatura asignaturaCuatro = new Asignatura(4, "Cuidado de Enfermería en salud comunitaria y familiar");
//         Asignatura asignaturaCinco = new Asignatura(5, "Cuidado de Enfermería en salud comunitaria y familiar");
//         Asignatura asignaturaSeis = new Asignatura(6, "Cuidado de Enfermería en salud comunitaria y familiar");
//         Asignatura asignaturaSiete = new Asignatura(7, "Cuidado de Enfermería en salud comunitaria y familiar");
//         Asignatura asignaturaOcho = new Asignatura(8, "Cuidado de Enfermería en salud comunitaria y familiar");
//         Asignatura asignaturaNueve = new Asignatura(9, "Cuidado de Enfermería en salud comunitaria y familiar");
//         Asignatura asignaturaDiez = new Asignatura(10, "Cuidado de Enfermería en salud comunitaria y familiar");
//         //AsignacionesIDs
//         AsignacionId idUno = new AsignacionId(programaUno, asignaturaUno, coordinadorAsignaturaUno);
//         AsignacionId idDos = new AsignacionId(programaUno, asignaturaDos, coordinadorAsignaturaUno);
//         AsignacionId idTres = new AsignacionId(programaUno, asignaturaTres, coordinadorAsignaturaUno);
//         AsignacionId idCuatro = new AsignacionId(programaUno, asignaturaCuatro, coordinadorAsignaturaUno);
//         AsignacionId idCinco = new AsignacionId(programaUno, asignaturaCinco, coordinadorAsignaturaUno);
//         AsignacionId idSeis = new AsignacionId(programaUno, asignaturaSeis, coordinadorAsignaturaUno);
//         AsignacionId idSiete = new AsignacionId(programaUno, asignaturaSiete, coordinadorAsignaturaUno);
//         AsignacionId idOcho = new AsignacionId(programaDos, asignaturaOcho, coordinadorAsignaturaDos);
//         AsignacionId idNueve = new AsignacionId(programaDos, asignaturaNueve, coordinadorAsignaturaDos);
//         AsignacionId idDiez = new AsignacionId(programaDos, asignaturaDiez, coordinadorAsignaturaDos);
//         //Asignaciones
//         Asignacion asignacionUno = new Asignacion(idUno);
//         Asignacion asignacionDos = new Asignacion(idDos);
//         Asignacion asignacionTres = new Asignacion(idTres);
//         Asignacion asignacionCuatro = new Asignacion(idCuatro);
//         Asignacion asignacionCinco = new Asignacion(idCinco);
//         Asignacion asignacionSeis = new Asignacion(idSeis);
//         Asignacion asignacionSiete = new Asignacion(idSiete);
//         Asignacion asignacionOcho = new Asignacion(idOcho);
//         Asignacion asignacionNueve = new Asignacion(idNueve);
//         Asignacion asignacionDiez = new Asignacion(idDiez);
//         underTest.save(asignacionUno);
//         underTest.save(asignacionDos);
//         underTest.save(asignacionTres);
//         underTest.save(asignacionCuatro);
//         underTest.save(asignacionCinco);
//         underTest.save(asignacionSeis);
//         underTest.save(asignacionSiete);
//         underTest.save(asignacionOcho);
//         underTest.save(asignacionNueve);
//         underTest.save(asignacionDiez);
//     }

//     @AfterEach
//     void tearDown(){
//         underTest.deleteAll();
//     }

//     @DisplayName("Test para obtener las materias asignadas a un coordinador")
//     @Test
//     void itShouldGetSubjectsByCoordinatorId(){
//         // Given

//         // When
        
//         // Then
//     }

//     @DisplayName("Test para saber si ya existe una asignacion")
//     @Test
//     void itShouldCheckWhenRowExistsByIds(){
//         // Given
//         int progId = 1;
//         int asigId = 1;
//         int cooId = 1;
//         // When
//         int result = underTest.existsByIds(progId, asigId, cooId);
//         // Then
//         assertThat(result).isEqualTo(1);
//     }
    
//     @DisplayName("Test para saber si no existe una asignacion")
//     @Test
//     void itShouldCheckWhenRowDoesNotExistsByIds(){
//         // Given
//         int progId = 1;
//         int asigId = 8;
//         int cooId = 2;
//         // When
//         int result = underTest.existsByIds(progId, asigId, cooId);
//         // Then
//         assertThat(result).isEqualTo(0);
//     }

//     @DisplayName("Test para obtener una asignacion por su ID compuesto")
//     @Test
//     void itShouldFindRowById(){
//         // Given

//         // When
        
//         // Then
//     }
// }