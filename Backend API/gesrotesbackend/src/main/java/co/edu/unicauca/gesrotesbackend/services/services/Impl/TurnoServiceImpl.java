package co.edu.unicauca.gesrotesbackend.services.services.Impl;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import co.edu.unicauca.gesrotesbackend.exceptions.HTTPException;
import co.edu.unicauca.gesrotesbackend.models.EstAsignacion;
import co.edu.unicauca.gesrotesbackend.models.Etiqueta;
import co.edu.unicauca.gesrotesbackend.models.Jornada;
import co.edu.unicauca.gesrotesbackend.models.Mes;
import co.edu.unicauca.gesrotesbackend.models.TipoAlimentacion;
import co.edu.unicauca.gesrotesbackend.models.Turno;
import co.edu.unicauca.gesrotesbackend.models.TurnoId;
import co.edu.unicauca.gesrotesbackend.repositories.EstAsignacionRepository;
import co.edu.unicauca.gesrotesbackend.repositories.EtiquetaRepository;
import co.edu.unicauca.gesrotesbackend.repositories.JornadaRepository;
import co.edu.unicauca.gesrotesbackend.repositories.TurnoRepository;
import co.edu.unicauca.gesrotesbackend.repositories.ValidacionTurnosRepository;
import co.edu.unicauca.gesrotesbackend.services.DTO.EstudianteFechaDTO;
import co.edu.unicauca.gesrotesbackend.services.DTO.EstudianteSeleccionadoDTO;
import co.edu.unicauca.gesrotesbackend.services.DTO.HorarioDTO;
import co.edu.unicauca.gesrotesbackend.services.DTO.JornadaDTO;
import co.edu.unicauca.gesrotesbackend.services.DTO.ModificarObsDTO;
import co.edu.unicauca.gesrotesbackend.services.DTO.NuevoTurnoDTO;
import co.edu.unicauca.gesrotesbackend.services.DTO.SeleccionEstudianteDTO;
import co.edu.unicauca.gesrotesbackend.services.DTO.SeleccionEstudiantesDTO;
import co.edu.unicauca.gesrotesbackend.services.DTO.TurnoAsociadoDTO;
import co.edu.unicauca.gesrotesbackend.services.DTO.TurnoCreadoDTO;
import co.edu.unicauca.gesrotesbackend.services.DTO.ValidacionEstudianteDTO;
import co.edu.unicauca.gesrotesbackend.services.DTO.ValidacionTurnoDTO;
import co.edu.unicauca.gesrotesbackend.services.Mapper.JornadaDTOMapper;
import co.edu.unicauca.gesrotesbackend.services.Utilities.HorarioJornada;
import co.edu.unicauca.gesrotesbackend.services.Utilities.Intervalo;
import co.edu.unicauca.gesrotesbackend.services.services.ITurnoService;
import co.edu.unicauca.gesrotesbackend.services.DTO.InformacionHorarioTurnoDTO;

@Service
public class TurnoServiceImpl implements ITurnoService{
    private final JornadaRepository jornadaRepository;
    private final EtiquetaRepository etiquetaRepository;
    private final TurnoRepository turnoRepository;
    private final EstAsignacionRepository estAsignacionRepository;
    private final ValidacionTurnosRepository validacionTurnosRepository;
    private final JornadaDTOMapper jornadaDTOMapper;
    private final LocalTime inicioRangoDesayuno = LocalTime.parse("06:00");
    private final LocalTime finRangoDesayuno = LocalTime.parse("12:00");
    private final LocalTime inicioRangoAlmuerzo = LocalTime.parse("12:00");
    private final LocalTime finRangoAlmuerzo = LocalTime.parse("14:00");
    private final LocalTime inicioRangoComida = LocalTime.parse("19:00");
    private final LocalTime finRangoComida = LocalTime.parse("21:00");

    public TurnoServiceImpl(JornadaRepository jornadaRepository, 
                            EtiquetaRepository etiquetaRepository,
                            TurnoRepository turnoRepository, 
                            EstAsignacionRepository estAsignacionRepository,
                            ValidacionTurnosRepository validacionTurnosRepository,
                            JornadaDTOMapper jornadaDTOMapper){
        this.jornadaRepository = jornadaRepository;
        this.etiquetaRepository = etiquetaRepository;
        this.turnoRepository = turnoRepository;
        this.estAsignacionRepository = estAsignacionRepository;
        this.validacionTurnosRepository = validacionTurnosRepository;
        this.jornadaDTOMapper = jornadaDTOMapper;
    }

    @Override
    public void cambiarEstadoSeleccionado(SeleccionEstudianteDTO seleccionEstudiante){
        estAsignacionRepository.modifyStateStudent(seleccionEstudiante.getEstado(),
                                                seleccionEstudiante.getPuId(), 
                                                seleccionEstudiante.getProgId(), 
                                                seleccionEstudiante.getAsigId(), 
                                                seleccionEstudiante.getCooId());
        
        //TODO hacer testing
        // if(seleccionEstudiante.getEstado()){
        //     EstAsignacion estAsignacion = estAsignacionRepository.getRowByIds(seleccionEstudiante.getPuId(), 
        //                                                                 seleccionEstudiante.getProgId(), 
        //                                                                 seleccionEstudiante.getAsigId(), 
        //                                                                 seleccionEstudiante.getCooId());
        
        //     ValidacionTurnosId idValidacionTurnos = new ValidacionTurnosId();
        //     idValidacionTurnos.setEstAsignacion(estAsignacion);
        //     ValidacionTurnos validacionTurnos = new ValidacionTurnos(idValidacionTurnos, Mes.valueOf(seleccionEstudiante.getMes()),
        //                                                             seleccionEstudiante.getAnio(), null, null, "");
        //     validacionTurnos.setId(idValidacionTurnos);
        //     validacionTurnosRepository.save(validacionTurnos);
        // }else{
        //     validacionTurnosRepository.deleteRowByUnique(Mes.valueOf(seleccionEstudiante.getMes()), seleccionEstudiante.getAnio(), 
        //                                                 seleccionEstudiante.getPuId(), seleccionEstudiante.getProgId(), 
        //                                                 seleccionEstudiante.getAsigId(), seleccionEstudiante.getCooId());
        // }
        
    }

    @Override
    public List<EstudianteSeleccionadoDTO> obtenerEstudiantesSeleccionados(int progId, int asigId, int cooId){
        List<EstudianteSeleccionadoDTO> listDTO = estAsignacionRepository.getSelectedStudents(progId, asigId, cooId);
        return listDTO;
    }

    @Override
    public void deseleccionarEstudiantes(SeleccionEstudiantesDTO seleccionEstudiantes){
        estAsignacionRepository.deselectStudents(seleccionEstudiantes.getProgId(),
                                                    seleccionEstudiantes.getAsigId(), 
                                                    seleccionEstudiantes.getCooId());
                    
        //TODO hacer testing
        // validacionTurnosRepository.deleteRowsByAsignation(Mes.valueOf(seleccionEstudiantes.getMes()), seleccionEstudiantes.getAnio(), 
        //                                                 seleccionEstudiantes.getProgId(), seleccionEstudiantes.getAsigId(), 
        //                                                 seleccionEstudiantes.getCooId());
    }

    @Override
    public List<JornadaDTO> obetenerJornadas() {
        return jornadaRepository.findAll()
                .stream()
                .map(jornadaDTOMapper)
                .collect(Collectors.toList());
    }

    @Override
    public TurnoCreadoDTO crearTurno(NuevoTurnoDTO nuevoTurno) {
        //* Crear un registro en tbl_turno
        //* Obtener la jornada
        Jornada jornada = jornadaRepository.findById(nuevoTurno.getIdJornada())
            .orElseThrow(() -> new HTTPException(HttpStatus.NOT_FOUND.value(),
                                            "No se encontró la jornada con ID " +
                                            nuevoTurno.getIdJornada()));
        //* Obtener la etiqueta
        Etiqueta etiqueta = etiquetaRepository.findById(nuevoTurno.getIdEtiqueta())
            .orElseThrow(() -> new HTTPException(HttpStatus.NOT_FOUND.value(),
                                            "No se encontró la etiqueta con ID " +
                                            nuevoTurno.getIdEtiqueta()));
        //* Inicializar el turno
        Turno auxTurno = new Turno();
        //* settear la fecha
        auxTurno.setFecha(nuevoTurno.getFechaTurno());
        //* settear la jornada
        auxTurno.setJornada(jornada);
        //* settear la etiqueta
        auxTurno.setEtiqueta(etiqueta);
        //* Obtener el estAsignacion asociado a los ID's de estudiante, programa, asignatura y coordinador
        EstAsignacion estAsignacion = estAsignacionRepository.getRowByIds(nuevoTurno.getIdEstudiante(), 
                                                                        nuevoTurno.getIdPrograma(), 
                                                                        nuevoTurno.getIdAsignatura(), 
                                                                        nuevoTurno.getIdCoordinador());
        // * Validar que la estAsginacion existe
        if(estAsignacion == null) {
            throw new HTTPException(HttpStatus.NOT_FOUND.value(), "El estudiante no está registrado en la asignación donde"+
                                                                " el ID del programa sea " + nuevoTurno.getIdPrograma() +
                                                                " el ID de la asignatura sea " + nuevoTurno.getIdAsignatura() +
                                                                " el ID del coordinador sea " + nuevoTurno.getIdCoordinador());
        }
        // * Validar que el turno no exista
        if(turnoRepository.alreadyExists(nuevoTurno.getFechaTurno(), nuevoTurno.getIdEstudiante(), 
                                            nuevoTurno.getIdPrograma(), nuevoTurno.getIdAsignatura(), 
                                            nuevoTurno.getIdCoordinador(), nuevoTurno.getIdJornada(), 
                                            nuevoTurno.getIdEtiqueta())!=0){
            throw new HTTPException(HttpStatus.CONFLICT.value(), "El turno que intenta registrar ya exise");
        }
        //* Setear la estAsignacion
        TurnoId auxTurnoId = new TurnoId();
        auxTurnoId.setEstAsignacion(estAsignacion);
        auxTurno.setId(auxTurnoId);
        //* Establecer la alimentación
        HorarioJornada obj = new HorarioJornada(jornada.getHoraInicio().toString(), jornada.getHoraFin().toString());
        
        if(obj.estaEnRango(inicioRangoDesayuno, finRangoDesayuno)){
            auxTurno.setAlimentacion(TipoAlimentacion.Desayuno);
        } else if (obj.estaEnRango(inicioRangoAlmuerzo, finRangoAlmuerzo)) {
            auxTurno.setAlimentacion(TipoAlimentacion.Almuerzo);
        } else if (obj.estaEnRango(inicioRangoComida, finRangoComida)) {
            auxTurno.setAlimentacion(TipoAlimentacion.Comida);
        }
        //* Guardar el turno en la BD
        Turno turnoCreado = new Turno();
        try {
            turnoCreado = turnoRepository.save(auxTurno);    
        } catch (DataAccessException e) {
            throw new HTTPException(HttpStatus.BAD_REQUEST.value(), "Ya existe un turno asignado al estudiante en la misma fecha");
        }
        //* Asignarle los valores al DTO a retornar
        TurnoCreadoDTO objDTO = new TurnoCreadoDTO(turnoCreado.getEtiqueta().getNombre(),
                                                    turnoCreado.getJornada().getFranja(),
                                                    turnoCreado.getJornada().getHoraInicio(),
                                                    turnoCreado.getJornada().getHoraFin(),
                                                    turnoCreado.getEtiqueta().getEscenario().getNombre());
        // objDTO.setNombreEtiqueta(turnoCreado.getEtiqueta().getNombre());
        // objDTO.setFranjaJornada(turnoCreado.getJornada().getFranja());
        // objDTO.setHoraInicio(turnoCreado.getJornada().getHoraInicio());
        // objDTO.setHoraFin(turnoCreado.getJornada().getHoraFin());
        // objDTO.setNombreEscenario(turnoCreado.getEtiqueta().getEscenario().getNombre());
        return objDTO;
    }

    @Override
    public InformacionHorarioTurnoDTO obetenerInfoHorarioTurnoPorFecha(int idEstudiante, Date fechaTurno){
        // * Obtengo la lista de turnos que tiene un estudiante en determinada fecha
        List<TurnoAsociadoDTO> turnosAsociadosDTO = turnoRepository.findShiftsAssociationsByDate(idEstudiante, fechaTurno);
        // * Realizo las operacoines necesarias para obtener el horario para el estudiante en esa fecha
        List<HorarioJornada> horarios = new ArrayList<>();
        for (TurnoAsociadoDTO turnoAsociado : turnosAsociadosDTO) {
            horarios.add(new HorarioJornada(turnoAsociado.horaInicio().toString(), turnoAsociado.horaFin().toString()));
        }
        String rango = establecerHorario(horarios);
        // System.out.println("El horario es de: " + rango);
        // * Realizo las operaciones necesarias para obtenter los booleanos de desayuno, almuerzo y comida para el horario en esa fecha
        Boolean[] alimentacion = aptoParaAlimentacion(turnosAsociadosDTO);        
        // * Agrego los datos a un objeto InformacionHorarioTurnoDTO
        InformacionHorarioTurnoDTO horarioTurnoDTO = new InformacionHorarioTurnoDTO(turnosAsociadosDTO.get(0).nombreEstudiante(), 
                                                                                    rango, 
                                                                                    alimentacion[0], 
                                                                                    alimentacion[1],
                                                                                    alimentacion[2]);
        // * Retorno el objeto InformacionHorarioTurnoDTO con la información a mostrar al usuario final
        return horarioTurnoDTO;
    }
    
    @Override
    public List<HorarioDTO> obetenerHorariosTurno(int idPrograma, int idCoordinador, int idAsignatura){
        List<HorarioDTO> horariosDTO = new ArrayList<>();
        // * Obtengo una lista donde cada objeto tiene un idEstudiante y fechaTurno, para poder sacar las franjas y el horario de Turno en esa fecha
        List<EstudianteFechaDTO> estFechaDTOList = turnoRepository.findDifferentSchedules(idPrograma, idAsignatura, idCoordinador);
        // * Recorro la lista para sacar las franjas y el horario de Turno a cada estudiante en determinada fecha
        for (EstudianteFechaDTO estudianteFechaDTO : estFechaDTOList) {
            // * Obtengo la los turnos asociados a un estudiante en determinada fecha
            List<TurnoAsociadoDTO> turnosAsociadosDTO = turnoRepository.findShiftsAssociationsByDate2(estudianteFechaDTO.idEstudiante(), estudianteFechaDTO.fechaTurno());
            // * Operaciones para obtener las franjas de los turnos asociados al estudiante en esa fecha
            String franjas = "";
            for (TurnoAsociadoDTO turnoAsociado : turnosAsociadosDTO) {
                franjas += turnoAsociado.franjaJornada() + " y ";
            }
            // Elimino el "y " al final
            franjas = franjas.substring(0, franjas.length() - 2);
            // * Asigno el nombre del escenario, nombre de la etiqueta, franjas, fecha turno e id del estudiante a un objeto HorarioDTO
            HorarioDTO horarioEst = new HorarioDTO(turnosAsociadosDTO.get(0).nombreEscenario(),
                                                    turnosAsociadosDTO.get(0).nombreEtiqueta(),
                                                    franjas,
                                                    estudianteFechaDTO.fechaTurno(),
                                                    estudianteFechaDTO.idEstudiante());
            // * Añado el objeto a la lista a retornar
            horariosDTO.add(horarioEst);
        }
        return horariosDTO;
    }

    @Override
    public List<TurnoAsociadoDTO> obetenerTurnosPorFecha(int idEstudiante, Date fechaTurno){
        return turnoRepository.findShiftsAssociationsByDate2(idEstudiante, fechaTurno);
    }

    @Override
    public void eliminarTurnoAsociado(int idTurno){
        turnoRepository.myDeletebyid(idTurno);
    }

    /**
     *  Determina el rango del horario mediante la jornada de cada turno asociado
     * 
     *  @param horarioJornadas lista de horarios que tiene un estudiante en una fecha
     *  @return cadena con el rango de horario que tendrá el estudiante
     */
    public String establecerHorario(List<HorarioJornada> horarioJornadas){
        // Ordena los horarios por hora de inicio
        Collections.sort(horarioJornadas);
        // Combina los intervalos solapados de los horarios
        List<Intervalo> intervalos = new ArrayList<>();
        Intervalo intervaloActual = new Intervalo(horarioJornadas.get(0));
        for (int i = 1; i < horarioJornadas.size(); i++) {
            HorarioJornada horario = horarioJornadas.get(i);
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
     *  Determina si el estudiante es apto para desayuno, almuerzo o comida según los turnos asociados
     *  
     *  @param turnosAsociadosDTO lista de los turnos asociados un estudiante en determinada fecha
     *  @return array booleano de dimension 3
     */
    public Boolean[] aptoParaAlimentacion(List<TurnoAsociadoDTO> turnosAsociadosDTO){
        Boolean[] alimentacion = new Boolean[3];
        for (TurnoAsociadoDTO turnoAsociado : turnosAsociadosDTO) {
            TipoAlimentacion tipoAlimentacion = turnoAsociado.alimentacion();
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

    //TODO
    public List<InformacionHorarioTurnoDTO> obtenerEstudiantesConAlimentacion(Date fechaTurno, int progId, int asigId, int cooId){
        // * Inicializar la lista de InformacionHorarioTurnoDTO
        List<InformacionHorarioTurnoDTO> listDTO = new ArrayList<>();
        // * Obtener los IDs de los estudiantes asociados a esa fecha (Crear metodo en EstAsignacion)
        List<Integer> listaConsulta = turnoRepository.findAllStudentsIdByDate(fechaTurno, progId, asigId, cooId);
        List<Integer> listaIdEstudiantes = eliminarRepetidos(listaConsulta);
        // * por cada elemento de la lista de IDs pasar por parametros el ID y fecha al metodo obetenerInfoHorarioTurnoPorFecha(int idEstudiante, Date fechaTurno)
        for (Integer estudianteID : listaIdEstudiantes) {
            InformacionHorarioTurnoDTO obj = obetenerInfoHorarioTurnoPorFecha(estudianteID, fechaTurno);
            // * agregar a la lista de InformacionHorarioTurnoDTO
            listDTO.add(obj);
        }
        
        // * retornar la lista
        return listDTO;
    }

    public List<ValidacionEstudianteDTO> obtenerEstudiantesValidacion(int progId, int asigId, int cooId, String mes, int anio){
        return validacionTurnosRepository.getStudentsToValidate(Mes.valueOf(mes), anio, progId, asigId, cooId);
    }

    public void modificarAsistenciaYEstado(ValidacionTurnoDTO validacionTurnoDTO){
        Boolean estado = validacionTurnoDTO.getAsistencia();
        validacionTurnosRepository.asociarEtiquetaConServicio(validacionTurnoDTO.getVtuId(), validacionTurnoDTO.getAsistencia(), 
                                                                estado, validacionTurnoDTO.getObservaciones());
    }

    public void modificarObservaciones(ModificarObsDTO modificarObsDTO){
        validacionTurnosRepository.actualizarObservaciones(modificarObsDTO.getVtuId(), modificarObsDTO.getObservaciones());
    }

    public static List<Integer> eliminarRepetidos(List<Integer> listaOriginal) {
        List<Integer> listaSinRepetidos = listaOriginal.stream()
                                                        .distinct()
                                                        .collect(Collectors.toList());
        return listaSinRepetidos;
    }
}
