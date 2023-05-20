// package co.edu.unicauca.gesrotesbackend.repositories;

// import static org.assertj.core.api.Assertions.assertThat;
// import java.util.Optional;

// import org.junit.jupiter.api.Test;
// import org.mockito.Mock;
// import org.mockito.Mockito;
// import org.mockito.junit.MockitoJUnitRunner;

// import co.edu.unicauca.gesrotesbackend.models.Jornada;

// // ? HECHO CON ...
// public class JornadaRepositoryTests {
//     @Mock
//     private JornadaRepository jornadaRepository;

//     @Test
//     public void testSave() {
//         // Datos de prueba
//         Jornada jornada = new Jornada();
//         jornada.setId(1);
//         jornada.setFranja("");

//         // Mockear el comportamiento del repositorio
//         Mockito.when(jornadaRepository.save(jornada)).thenReturn(jornada);

//         // Llamar al método del repositorio
//         Jornada resultado = jornadaRepository.save(jornada);

//         // Verificar el resultado
//         // Assert.assertEquals(jornada, resultado);

//         // Verificar que el método del repositorio fue llamado correctamente
//         Mockito.verify(jornadaRepository, Mockito.times(1)).save(jornada);
//     }

//     @Test
//     public void testFindById() {
//         // Datos de prueba
//         int jornadaId = 1;
//         Jornada jornada = new Jornada();
//         jornada.setId(jornadaId);
//         jornada.setFranja("");

//         // Mockear el comportamiento del repositorio
//         Mockito.when(jornadaRepository.findById(jornadaId)).thenReturn(Optional.of(jornada));

//         // Llamar al método del repositorio
//         Optional<Jornada> resultado = jornadaRepository.findById(jornadaId);

//         // Verificar el resultado
//         // Assert.assertTrue(resultado.isPresent());
//         // Assert.assertEquals(jornada, resultado.get());

//         // Verificar que el método del repositorio fue llamado correctamente
//         Mockito.verify(jornadaRepository, Mockito.times(1)).findById(jornadaId);
//     }

//     // Otros casos de prueba para los métodos básicos del repositorio (findAll, delete, etc.)

// }
