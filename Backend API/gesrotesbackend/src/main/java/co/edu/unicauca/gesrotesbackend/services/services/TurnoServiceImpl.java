package co.edu.unicauca.gesrotesbackend.services.services;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.unicauca.gesrotesbackend.models.EstAsignacion;
import co.edu.unicauca.gesrotesbackend.models.Etiqueta;
import co.edu.unicauca.gesrotesbackend.models.Jornada;
import co.edu.unicauca.gesrotesbackend.models.Turno;
import co.edu.unicauca.gesrotesbackend.repositories.EstAsignacionRepository;
import co.edu.unicauca.gesrotesbackend.repositories.EtiquetaRepository;
import co.edu.unicauca.gesrotesbackend.repositories.JornadaRepository;
import co.edu.unicauca.gesrotesbackend.repositories.TurnoRepository;
import co.edu.unicauca.gesrotesbackend.services.DTO.EstudianteSeleccionadoDTO;
import co.edu.unicauca.gesrotesbackend.services.DTO.JornadaDTO;
import co.edu.unicauca.gesrotesbackend.services.DTO.NuevoTurnoDTO;
import co.edu.unicauca.gesrotesbackend.services.DTO.SeleccionEstudianteDTO;
import co.edu.unicauca.gesrotesbackend.services.DTO.SeleccionEstudiantesDTO;
import co.edu.unicauca.gesrotesbackend.services.DTO.TurnoCreadoDTO;

@Service
public class TurnoServiceImpl implements ITurnoService{
    @Autowired
    private JornadaRepository jornadaRepository;
    @Autowired
    private EtiquetaRepository etiquetaRepository;
    @Autowired
    private TurnoRepository turnoRepository;
    @Autowired
    private EstAsignacionRepository estAsignacionRepository;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public void cambiarEstadoSeleccionado(SeleccionEstudianteDTO seleccionEstudiante){
        estAsignacionRepository.modifyStateStudent(seleccionEstudiante.getEstado(),
                                                seleccionEstudiante.getPuId(), 
                                                seleccionEstudiante.getProgId(), 
                                                seleccionEstudiante.getAsigId(), 
                                                seleccionEstudiante.getCooId());
    }

    // @Override
    // public void deseleccionarEstudiante(SeleccionEstudianteDTO seleccionEstudiante){
    //     estAsignacionRepository.deselectStudent(seleccionEstudiante.getPuId(), 
    //                                             seleccionEstudiante.getProgId(), 
    //                                             seleccionEstudiante.getAsigId(), 
    //                                             seleccionEstudiante.getCooId(), 
    //                                             Mes.valueOf(seleccionEstudiante.getMes()), 
    //                                             seleccionEstudiante.getAnio());
    // }

    @Override
    public List<EstudianteSeleccionadoDTO> obtenerEstudiantesSeleccionados(SeleccionEstudiantesDTO seleccionEstudiantes){
        List<EstudianteSeleccionadoDTO> listDTO = estAsignacionRepository.getSelectedStudents(seleccionEstudiantes.getProgId(), 
                                                                                            seleccionEstudiantes.getAsigId(), 
                                                                                            seleccionEstudiantes.getCooId());
        return listDTO;
    }

    @Override
    public void deseleccionarEstudiantes(SeleccionEstudiantesDTO seleccionEstudiantes){
        estAsignacionRepository.deselectStudents(seleccionEstudiantes.getProgId(),
                                                    seleccionEstudiantes.getAsigId(), 
                                                    seleccionEstudiantes.getCooId());
    }

    @Override
    public List<JornadaDTO> obetenerJornadas() {
        List<Jornada> listadoEntity = this.jornadaRepository.findAll();
        List<JornadaDTO> listadoDTO = this.modelMapper.map(listadoEntity,new TypeToken<List<JornadaDTO>>(){}.getType());
        return listadoDTO;
    }

    @Override
    public TurnoCreadoDTO crearTurno(NuevoTurnoDTO nuevoTurno) {
        //* Crear un registro en tbl_turno
        //* Obtener la jornada
        Optional<Jornada> jornada = jornadaRepository.findById(nuevoTurno.getIdJornada());
        //* Obtener la etiqueta
        Optional<Etiqueta> etiqueta = etiquetaRepository.findById(nuevoTurno.getIdEtiqueta());
        if (!(jornada.isPresent() && etiqueta.isPresent())) {
            return null;
        }
        //* Inicializar el turno
        Turno auxTurno = new Turno();
        //* settear la fecha
        auxTurno.setFecha(nuevoTurno.getFechaTurno());
        //* settear la jornada
        auxTurno.setJornada(jornada.get());
        //* settear la etiqueta
        auxTurno.setEtiqueta(etiqueta.get());
        //* Guardar el turno en la BD
        Turno turnoCreado = turnoRepository.save(auxTurno);// ? validar con try catch?
        
        //* Obtener el estAsignacion asociado a los ID's de estudiante, programa, asignatura y coordinador
        EstAsignacion estAsignacion = estAsignacionRepository.getRowByIds(nuevoTurno.getIdEstudiante(), nuevoTurno.getIdPrograma(), nuevoTurno.getIdAsignatura(), nuevoTurno.getIdCoordinador());
        
        //* Crear un registro en tbl_turno_est_asig con el turno anterior
        // TurnoId idNuevoRegistro = new TurnoId(estAsignacion, turnoCreado);
        // TurEstAsignacion nuevoRegistro = new TurEstAsignacion(idNuevoRegistro);
        // turEstAsignacionRepository.save(nuevoRegistro);
        
        //* Asignarle los valores al DTO a retornar
        TurnoCreadoDTO objDTO = new TurnoCreadoDTO();
        objDTO.setNombreEtiqueta(turnoCreado.getEtiqueta().getNombre());
        objDTO.setFranjaJornada(turnoCreado.getJornada().getFranja());
        objDTO.setHoraInicio(turnoCreado.getJornada().getHoraInicio());
        objDTO.setHoraFin(turnoCreado.getJornada().getHoraFin());
        objDTO.setNombreEscenario(turnoCreado.getEtiqueta().getEscenario().getNombre());
        return objDTO;
    }
    
}
