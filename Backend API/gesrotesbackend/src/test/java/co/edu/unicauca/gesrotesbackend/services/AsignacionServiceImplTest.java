package co.edu.unicauca.gesrotesbackend.services;

import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import co.edu.unicauca.gesrotesbackend.repositories.AsignacionRepository;
import co.edu.unicauca.gesrotesbackend.services.services.Impl.AsignacionServiceImpl;

@ExtendWith(MockitoExtension.class)
public class AsignacionServiceImplTest {
    @Mock
    private AsignacionRepository asignacionRepository;
    private AsignacionServiceImpl serviceUnderTest;

    @BeforeEach
    void setUp(){
        serviceUnderTest = new AsignacionServiceImpl(asignacionRepository);
    }

    @Test
    void testGetAllSubjectsByCoo() {
        // given
        int idCoordinador = 1;
        // when
        serviceUnderTest.getAllByCoo(idCoordinador);
        // then
        verify(asignacionRepository).getByCoordinatorId(idCoordinador);
    }
}
