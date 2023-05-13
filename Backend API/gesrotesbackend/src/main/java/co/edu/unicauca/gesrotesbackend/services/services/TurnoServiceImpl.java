package co.edu.unicauca.gesrotesbackend.services.services;

import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.unicauca.gesrotesbackend.models.EstAsignacion;
import co.edu.unicauca.gesrotesbackend.models.Etiqueta;
import co.edu.unicauca.gesrotesbackend.models.Jornada;
import co.edu.unicauca.gesrotesbackend.models.TipoAlimentacion;
import co.edu.unicauca.gesrotesbackend.models.Turno;
import co.edu.unicauca.gesrotesbackend.models.TurnoId;
import co.edu.unicauca.gesrotesbackend.repositories.EstAsignacionRepository;
import co.edu.unicauca.gesrotesbackend.repositories.EtiquetaRepository;
import co.edu.unicauca.gesrotesbackend.repositories.JornadaRepository;
import co.edu.unicauca.gesrotesbackend.repositories.TurnoRepository;
import co.edu.unicauca.gesrotesbackend.services.DTO.ConsultaTurnoEstudianteDTO;
import co.edu.unicauca.gesrotesbackend.services.DTO.EstudianteSeleccionadoDTO;
import co.edu.unicauca.gesrotesbackend.services.DTO.InformacionTurnoAsociadoDTO;
import co.edu.unicauca.gesrotesbackend.services.DTO.JornadaDTO;
import co.edu.unicauca.gesrotesbackend.services.DTO.NuevoTurnoDTO;
import co.edu.unicauca.gesrotesbackend.services.DTO.SeleccionEstudianteDTO;
import co.edu.unicauca.gesrotesbackend.services.DTO.SeleccionEstudiantesDTO;
import co.edu.unicauca.gesrotesbackend.services.DTO.TurnoAEliminarDTO;
import co.edu.unicauca.gesrotesbackend.services.DTO.TurnoCreadoDTO;
import co.edu.unicauca.gesrotesbackend.services.DTO.Horario;
import co.edu.unicauca.gesrotesbackend.services.DTO.Intervalo;
import co.edu.unicauca.gesrotesbackend.services.DTO.InformacionHorarioTurnoDTO;

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
        //* Obtener el estAsignacion asociado a los ID's de estudiante, programa, asignatura y coordinador
        EstAsignacion estAsignacion = estAsignacionRepository.getRowByIds(nuevoTurno.getIdEstudiante(), nuevoTurno.getIdPrograma(), nuevoTurno.getIdAsignatura(), nuevoTurno.getIdCoordinador());
        //* Setear la estAsignacion
        TurnoId auxId = new TurnoId();
        auxId.setEstAsignacion(estAsignacion);
        auxTurno.setId(auxId);
        //* Establecer la alimentación
        Horario obj = new Horario(jornada.get().getHoraInicio().toString(), jornada.get().getHoraFin().toString());
        LocalTime inicioRangoDesayuno = LocalTime.parse("06:00");
        LocalTime finRangoDesayuno = LocalTime.parse("12:00");
        LocalTime inicioRangoAlmuerzo = LocalTime.parse("12:00");
        LocalTime finRangoAlmuerzo = LocalTime.parse("14:00");
        LocalTime inicioRangoComida = LocalTime.parse("19:00");
        LocalTime finRangoComida = LocalTime.parse("21:00");
        if(obj.estaEnRango(inicioRangoDesayuno, finRangoDesayuno)){
            //System.out.println("El estudiante es apto para desayuno");
            auxTurno.setAlimentacion(TipoAlimentacion.Desayuno);
        } else if (obj.estaEnRango(inicioRangoAlmuerzo, finRangoAlmuerzo)) {
            //System.out.println("El estudiante es apto para almuerzo");
            auxTurno.setAlimentacion(TipoAlimentacion.Almuerzo);
        } else if (obj.estaEnRango(inicioRangoComida, finRangoComida)) {
            //System.out.println("El estudiante es apto para comida");
            auxTurno.setAlimentacion(TipoAlimentacion.Comida);
        }
        //* Guardar el turno en la BD
        Turno turnoCreado = turnoRepository.save(auxTurno);// ? validar con try catch?
        //* Asignarle los valores al DTO a retornar
        TurnoCreadoDTO objDTO = new TurnoCreadoDTO();
        objDTO.setNombreEtiqueta(turnoCreado.getEtiqueta().getNombre());
        objDTO.setFranjaJornada(turnoCreado.getJornada().getFranja());
        objDTO.setHoraInicio(turnoCreado.getJornada().getHoraInicio());
        objDTO.setHoraFin(turnoCreado.getJornada().getHoraFin());
        objDTO.setNombreEscenario(turnoCreado.getEtiqueta().getEscenario().getNombre());
        return objDTO;
    }

    @Override
    public InformacionHorarioTurnoDTO obetenerTurnosEstPorFecha(ConsultaTurnoEstudianteDTO turnoEstudiante){
        List<InformacionTurnoAsociadoDTO> turnosAsociadosDTO = turnoRepository.findShiftsAssociationsByDate(turnoEstudiante.getIdEstudiante(), 
                                                                                                            turnoEstudiante.getFechaTurno());
        
        List<Horario> horarios = new ArrayList<>();
        String franjas = "";
        for (InformacionTurnoAsociadoDTO turnoAsociado : turnosAsociadosDTO) {
            horarios.add(new Horario(turnoAsociado.getHoraInicio().toString(), turnoAsociado.getHoraFin().toString()));
            franjas += turnoAsociado.getFranjaJornada() + " y ";
        }
        // Elimino el "y " al final
        franjas = franjas.substring(0, franjas.length() - 2);
        
        String rango = establecerHorario(horarios);
        // System.out.println("El horario es de: " + rango);

        Boolean[] alimentacion = aptoParaAlimentacion(turnosAsociadosDTO);
        
        InformacionHorarioTurnoDTO horarioTurnoDTO = new InformacionHorarioTurnoDTO(turnosAsociadosDTO.get(0).getNombreEscenario(),
                                                                                    turnosAsociadosDTO.get(0).getNombreEtiqueta(),
                                                                                    franjas, 
                                                                                    turnosAsociadosDTO.get(0).getNombreEstudiante(), 
                                                                                    rango, 
                                                                                    alimentacion[0], 
                                                                                    alimentacion[1],
                                                                                    alimentacion[2]);
        
        return horarioTurnoDTO;
    }

    @Override
    public void eliminarTurnoAsociado(TurnoAEliminarDTO turno){
        turnoRepository.deleteRowByIdsAndOthers(turno.getFecha(), turno.getIdEstudiante(), turno.getIdPrograma(), turno.getIdAsignatura(), 
                                                turno.getIdCoordinador(), turno.getIdJornada(), turno.getIdEtiqueta());
    }

    /**
     *  TODO: Documentar
     *  @param horarios
     *  @return
     */
    public String establecerHorario(List<Horario> horarios){
        // Ordena los horarios por hora de inicio
        Collections.sort(horarios);
        // Combina los intervalos solapados de los horarios
        List<Intervalo> intervalos = new ArrayList<>();
        Intervalo intervaloActual = new Intervalo(horarios.get(0));
        for (int i = 1; i < horarios.size(); i++) {
            Horario horario = horarios.get(i);
            if (intervaloActual.fin.compareTo(horario.horaInicio) >= 0) {
                intervaloActual.fin = horario.horaFin;
            } else {
                intervalos.add(intervaloActual);
                intervaloActual = new Intervalo(horario);
            }
        }
        intervalos.add(intervaloActual);
        // Imprime el horario completo
        String rango = "";
        SimpleDateFormat formato = new SimpleDateFormat("HH:mm");
        for (Intervalo intervalo : intervalos) {
            rango += formato.format(intervalo.inicio) + " a " + formato.format(intervalo.fin) + " y ";
        }
        // Elimino el "y " al final
        rango = rango.substring(0, rango.length() - 2);
        return rango;
    }

    /**
     *  TODO: Documentar
     *  @param turnosAsociadosDTO
     *  @return
     */
    public Boolean[] aptoParaAlimentacion(List<InformacionTurnoAsociadoDTO> turnosAsociadosDTO){
        Boolean[] alimentacion = new Boolean[3];
        for (InformacionTurnoAsociadoDTO turnoAsociado : turnosAsociadosDTO) {
            TipoAlimentacion tipoAlimentacion = turnoAsociado.getAlimentacion();
            if (tipoAlimentacion == TipoAlimentacion.Desayuno) {
                alimentacion[0] = true;
            } else if (tipoAlimentacion == TipoAlimentacion.Almuerzo) {
                alimentacion[1] = true;
            } else if (tipoAlimentacion == TipoAlimentacion.Comida) {
                alimentacion[2] = true;
            }
        }
        return alimentacion;
    }
}
