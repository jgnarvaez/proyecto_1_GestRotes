import React, { useState, useEffect, useCallback, useRef } from 'react';
import { TabView, TabPanel } from 'primereact/tabview';
import Box from '@mui/material/Box';
import Grid from '@mui/material/Grid';
import { Card } from 'primereact/card';
import { Button } from 'primereact/button';
import { InputText } from "primereact/inputtext";
import PerfilEstudiante from './PerfilEstudiante';
import { ConfirmDialog } from 'primereact/confirmdialog';
import { DataTable } from 'primereact/datatable';
import { Column } from 'primereact/column';
import { Dropdown } from 'primereact/dropdown';
import { Dialog } from 'primereact/dialog';
import { Divider } from 'primereact/divider';
import { Badge } from 'primereact/badge';
import { Toast } from 'primereact/toast';
import { Checkbox } from "primereact/checkbox";
import { Tag } from 'primereact/tag';
import axios from 'axios';

export const GridEstudiantes = ({ asignatura }) => {

    const CustomCard = ({ children }) => (
        <div
          style={{
            display: 'flex',
            flexDirection: 'column',
            textAlign: 'left',
            height: '100px',
            width: '100px',
            border: '1px solid #ccc',
            borderRadius: '4px',
            padding: '8px',
          }}
        >
          {children}
        </div>
      );

    const toast = useRef(null);
    // CAMBIO
    const [searchText, setSearchText] = useState('');
    const [timer, setTimer] = useState(null);
    //const [searchTextEstudiantes, setSearchTextEstudiantes] = useState('');

    // ESTUDIANTES
    const [estudiantesBusqueda, setEstudiantesBusqueda] = useState([]);
    const [estudiantes, setEstudiantes] = useState([]);
    const [estudiantesSeleccionados, setEstudiantesSeleccionados] = useState([]);
    const [confirmDialogVisible, setConfirmDialogVisible] = useState(false);
    const [estudianteIdEliminar, setEstudianteIdEliminar] = useState([]);
    const [confirmDialogVisibleTodos, setConfirmDialogVisibleTodos] = useState(false);

    //TURNOS
    const [fechaActual, setFechaActual] = useState(new Date());
    const [month, setMonth] = useState(new Date().getMonth());
    const [year, setYear] = useState(new Date().getFullYear());
    
    

    const months = [
        {label: 'Enero', value: 0},
        {label: 'Febrero', value: 1},
        {label: 'Marzo', value: 2},
        {label: 'Abril', value: 3},
        {label: 'Mayo', value: 4},
        {label: 'Junio', value: 5},
        {label: 'Julio', value: 6},
        {label: 'Agosto', value: 7},
        {label: 'Septiembre', value: 8},
        {label: 'Octubre', value: 9},
        {label: 'Noviembre', value: 10},
        {label: 'Diciembre', value: 11}
    ];

    const years = [];
    for (let i = new Date().getFullYear() - 10; i <= new Date().getFullYear() + 10; i++) {
        years.push({label: i.toString(), value: i});
    }

    //GESTION ETIQUETAS
    const [visibleEtiqueta, setVisibleEtiqueta] = useState(false);
    const [visibleAsociacion, setVisibleAsociacion] = useState(false);
    const [visibleEstudiantes, setVisibleEstudiantes] = useState(false);

    //TURNOS
    const [visibleTurnos, setVisibleTurnos] = useState(false);
    const [selectedDate, setSelectedDate] = useState(null);
    const [selectedEstudiante, setSelectedEstudiante] = useState(null);
    const [turnosEstudiante, setTurnosEstudiante] = useState([]);
    const [visibleMasInformacion, setVisibleMasInformacion] = useState(false);
    const [infoHorarioTurno, setInfoHorarioTurno] = useState([]);
    const [confirmDialogVisibleTurnosEliminar, setConfirmDialogVisibleTurnosEliminar] = useState(false);
    const [turnoIdEliminar, setTurnoIdEliminar] = useState([]);
    const [visibleValidarTurnos, setVisibleValidarTurnos] = useState(false);
    const [visibleAlimentacion, setVisibleAlimentacion] = useState(false);
    const [diaSeleccionado, setDiaSeleccionado] = useState(false);
    const [estudiantesConAlimentacion, setEstudiantesConAlimentacion] = useState([]);


    //CREAR ETIQUETAS
    const [valueCrearEtiqueta, setValueCrearEtiqueta] = useState('');
    const [etiquetasListarCreadas, setEtiquetasListarCreadas] = useState([]);
    const [etiquetasListarAsociadas, setEtiquetasListarAsociadas] = useState([]);
    const [etiquetasListarPorEscenerio, setEtiquetasListarPorEscenario] = useState([]);
    const [escenarios, setEscenarios] = useState([]);
    const [servicios, setServicios] = useState([]);
    const [jornadas, setJornadas] = useState([]);
    const [horarios, setHorarios] = useState([]);
    

    //SELECCION ESCENARIO
    const [selectedEscenario, setSelectedEscenario] = useState(null);
    const [selectedEtiqueta, setSelectedEtiqueta] = useState(null);
    const [selectedServicio, setSelectedServicio] = useState(null);
    const [selectedJornada, setSelectedJornada] = useState(null);

    //ESTADO BOTONES
    const [botonCrearEtiquetas, setBotonCrearEtiquetas] = useState(true);
    const [botonAsociarEtiquetas, setBotonAsociarEtiquetas] = useState(false);
    const [botonEstudiantesTodos, setBotonEstudiantesTodos] = useState(true);
    const [botonEstudiantesSeleccionados, setBotonEstudiantesSeleccionados] = useState(false);
    const [botonEstudiantesNoSeleccionados, setBotonEstudiantesNoSeleccionados] = useState(false);
    const [botonTurnosTodos, setBotonTurnosTodos] = useState(true);
    const [botonTurnosAprobados, setBotonTurnosAprobados] = useState(false);
    const [botonTurnosNoAprobados, setBotonTurnosNoAprobados] = useState(false);
    const [botonTurnosSinValidar, setBotonTurnosSinValidar] = useState(true);

    //ELIMINAR ETIQUETAS
    const [etiquetaIdEliminar, setEtiquetaIdEliminar] = useState([]);
    const [confirmDialogVisibleEtiquetas, setConfirmDialogVisibleEtiquetas] = useState(false);
    const [etiquetaIdEliminarAsociado, setEtiquetaIdEliminarAsociado] = useState([]);
    const [confirmDialogVisibleEtiquetasAsociado, setConfirmDialogVisibleEtiquetasAsociado] = useState(false);

    // Validar si ambos campos tienen información
    const isFormValid = valueCrearEtiqueta.trim() !== '' && selectedEscenario !== null;
    const isFormValidAsociado = selectedEtiqueta != null && selectedServicio != null;
    const isFormValidTurno = selectedEscenario != null && selectedJornada != null && selectedEtiqueta != null ;

    //POST CREAR ETIQUETA
    const etiquetaNueva = {
        nombreEtiqueta: valueCrearEtiqueta,
        idEscenario: selectedEscenario
        //idServicio: null
    }

    //PUT CREAR ASOCION
    const etiquetaAsociacionServicio ={
        idEtiqueta: selectedEtiqueta, 
        idServicio: selectedServicio
    }

    //PUT CREAR TURNO
    const turnoCrear = {
        fechaTurno: selectedDate
        ? `${selectedDate.getFullYear()}-${String(selectedDate.getMonth() + 1).padStart(2, '0')}-${String(selectedDate.getDate()).padStart(2, '0')}`
        : null,
        idEstudiante: selectedEstudiante ? selectedEstudiante.id : null,
        idPrograma: 1,
        idAsignatura: asignatura.idAsignatura,
        idCoordinador: 1,
        idJornada: selectedJornada,
        idEtiqueta: selectedEtiqueta
    };

    const showSuccessRegistrarEstudiante = () => {
        toast.current.show({severity:'success', summary: 'Registrado!', detail:'El estudiante se registro exitosamente.', life: 8000});
    }

    const showSuccessCrearEtiqueta = () => {
        toast.current.show({severity:'success', summary: 'Creada!', detail:'Se ha creado una nueva etiqueta.', life: 8000});
    }

    const showSuccessCrearTurno = () => {
        toast.current.show({severity:'success', summary: 'Creada!', detail:'Se ha creado un nuevo turno.', life: 8000});
    }

    const showSuccessEtiquetaAsociado = () => {
        toast.current.show({severity:'success', summary: 'Asociado!', detail:'El servicio se ha asociado exitosamente a la etiqueta.', life: 8000});
    }

    const showInfoEliminarEstudiante = () => {
        toast.current.show({severity:'info', summary: 'Eliminado!', detail:'Se ha eliminado el registro del estudiante.', life: 8000});
    }

    const showInfoEliminarTodo = () => {
        toast.current.show({severity:'info', summary: 'Eliminado!', detail:'Se ha eliminado todos los registros de estudiantes.', life: 8000});
    }

    const showInfoEliminarEtiquetaCreada = () => {
        toast.current.show({severity:'info', summary: 'Eliminado!', detail:'Se ha eliminado la etiqueta correctamente.', life: 8000});
    }

    const showInfoEliminarEtiquetaAsociado = () => {
        toast.current.show({severity:'info', summary: 'Eliminado!', detail:'Se ha eliminado el servicio asociado a la etiqueta correctamente.', life: 8000});
    }

    const showInfoEliminarTurno = () => {
        toast.current.show({severity:'info', summary: 'Eliminado!', detail:'Se ha eliminado el turno correctamente.', life: 8000});
    }

    const showErrorRegistrarEstudiante = () => {
        toast.current.show({severity:'error', summary: 'Error', detail:'Error al intentar registrar el estudiante.', life: 8000});
    }

    const showErrorCrearEtiqueta = (response) => {
        toast.current.show({severity:'error', summary: 'Error', detail:'Error al intentar crear la etiqueta: \n' + response, life: 8000});
    }

    const showErrorEtiquetaAsociado = () => {
        toast.current.show({severity:'error', summary: 'Error', detail:'Error al intentar asociar el servicio a la etiqueta.', life: 8000});
    }

    const showErrorAlimentacion = () => {
        toast.current.show({severity:'error', summary: 'Error', detail:'No ha seleccionado un dia.', life: 8000});
    }

    const handleClickEstadoBotones = (tipo) => {
        if (tipo === 'crear') {
            setBotonCrearEtiquetas(true);
            setBotonAsociarEtiquetas(false);
            setVisibleEtiqueta(true)
          } else if (tipo === 'asociar'){
            setBotonCrearEtiquetas(false);
            setBotonAsociarEtiquetas(true);
            setVisibleAsociacion(true);
          }
    }

    const handleClickEstadoBotonesEstudiantes = (tipo) => {
        if (tipo === 'todos') {
            setBotonEstudiantesTodos(true);
            setBotonEstudiantesSeleccionados(false);
            setBotonEstudiantesNoSeleccionados(false);
            //setVisibleEstudiantesTodos(true)
          } else if (tipo === 'seleccionados'){
            setBotonEstudiantesTodos(false);
            setBotonEstudiantesSeleccionados(true);
            setBotonEstudiantesNoSeleccionados(false);
            //setVisibleEstudiantesTodosSeleccionados(true);
          } else if (tipo === 'noSeleccionados'){
            setBotonEstudiantesTodos(false);
            setBotonEstudiantesSeleccionados(false);
            setBotonEstudiantesNoSeleccionados(true);
            //setVisibleEstudiantesTodosNoSeleccionados(true);
          }
    }

    const handleClickEstadoBotonesValidarTurno = (tipo) => {
        if (tipo === 'todos') {
            setBotonTurnosTodos(true);
            setBotonTurnosAprobados(false);
            setBotonTurnosNoAprobados(false);
            setBotonTurnosSinValidar(false);
            
          } else if (tipo === 'aprobados'){
            setBotonTurnosTodos(false);
            setBotonTurnosAprobados(true);
            setBotonTurnosNoAprobados(false);
            setBotonTurnosSinValidar(false);
          } else if (tipo === 'noAprobados'){
            setBotonTurnosTodos(false);
            setBotonTurnosAprobados(false);
            setBotonTurnosNoAprobados(true);
            setBotonTurnosSinValidar(false);
          } else if (tipo === 'sinValidar'){
            setBotonTurnosTodos(false);
            setBotonTurnosAprobados(false);
            setBotonTurnosNoAprobados(false);
            setBotonTurnosSinValidar(true);
          }
    }

    const irSemanaAnterior = () => {
        const fechaNueva = new Date(fechaActual);
        fechaNueva.setDate(fechaActual.getDate() - 7);
        
        const nuevoMes = fechaNueva.getMonth();
        const nuevoAnio = fechaNueva.getFullYear();
        
        if (nuevoAnio !== year) {
          setYear(nuevoAnio);
          setMonth(nuevoMes);
        } else if (nuevoMes < month) {
          setMonth(nuevoMes);
        }
        
        setFechaActual(fechaNueva);
      };

      const irSemanaSiguiente = () => {
        const fechaNueva = new Date(fechaActual);
        fechaNueva.setDate(fechaActual.getDate() + 7);
        
        const nuevoMes = fechaNueva.getMonth();
        const nuevoAnio = fechaNueva.getFullYear();
        
        if (nuevoAnio !== year) {
          setYear(nuevoAnio);
          setMonth(nuevoMes);
        } else if (nuevoMes > month) {
          setMonth(nuevoMes);
        }
        
        setFechaActual(fechaNueva);
      };

    const fetchEstudiantes = useCallback(() => {
        const url = `http://127.0.0.1:8085/1/1/asignaturas/${asignatura.idAsignatura}/estudiantes`;
      
        axios.get(url)
        .then(response => setEstudiantes(response.data))
        .catch(error => console.error(error));
    }, [asignatura.idAsignatura]);
      
      useEffect(() => {
        fetchEstudiantes();
      }, [fetchEstudiantes]);

      const buscarEstudiantes = (val) => {
        fetchEstudiantes();
        if (val.length > 0) {
            const url = `http://127.0.0.1:8085/1/1/asignaturas/${asignatura.idAsignatura}/estudiantes/${val}`;
            axios.get(url)
                .then(response => setEstudiantesBusqueda(response.data))
                .catch(error => console.error(error));
        } else {
            setEstudiantesBusqueda([]);
        }
    };

    const handleConfirmDialogAceptar = async () => {
    try {
        const response = await fetch(`http://127.0.0.1:8085/1/1/asignaturas/${asignatura.idAsignatura}/estudiantes/${estudianteIdEliminar}`, {
        method: 'DELETE'
        });
    
        if (response.ok) {
        console.log(`Estudiante con ID ${estudianteIdEliminar} eliminado.`);
        
        showInfoEliminarEstudiante();
        fetchEstudiantes(); //actualiza estudiantes
        handleEstudianteSeleccionado(estudianteIdEliminar);
        listarHorarios();
        setEstudiantesBusqueda([]);
        setSearchText('');
        } else {
        console.log(`No se pudo eliminar al estudiante con ID ${estudianteIdEliminar}.`);
        }
    } catch (error) {
        console.error(error);
    }
        setConfirmDialogVisible(false);
        setEstudianteIdEliminar(null);
    };

    const handleEliminarTodosClick = () => {
        setConfirmDialogVisibleTodos(true);
    };

    const eliminarTodo = async () => {
    try {
        const response = await fetch(`http://127.0.0.1:8085/1/1/asignaturas/${asignatura.idAsignatura}/estudiantes`, {
        method: 'DELETE'
        });
    
        if (response.ok) {
        console.log(`Todos los estudiantes han sido eliminados.`);
        showInfoEliminarTodo();
        fetchEstudiantes();
        setEstudiantesBusqueda([]);
        setSearchText('');
        
        } else {
        console.log(`No se pudo eliminar a los estudiantes.`);
        }
    } catch (error) {
        console.error(error);
    }
    setConfirmDialogVisibleTodos(false);
    };

    //Listar etiquetas creadas 
    const listarEtiquetasCreadas = useCallback(() => {
        const url = `http://127.0.0.1:8085/etiquetas/`
        axios.get(url)
        .then(response => setEtiquetasListarCreadas(response.data))
        .catch(error => console.error(error));
    }, []);
    useEffect(() => {
        listarEtiquetasCreadas();
    }, [listarEtiquetasCreadas]);

    //Listar etiquetas asociadas 
    const listarEtiquetasAsociadas = useCallback(() => {
        const url = `http://127.0.0.1:8085/etiquetas/onlyService`
        axios.get(url)
          .then(response => setEtiquetasListarAsociadas(response.data))
          .catch(error => console.error(error));
      }, []);
      useEffect(() => {
        listarEtiquetasAsociadas();
      }, [listarEtiquetasAsociadas]);
    
    //Listar etiquetas por escenario 
    const listarEtiquetasPorEscenario = useCallback(() => {
        if (selectedEscenario !== null) {
            const url = `http://127.0.0.1:8085/etiquetas/escenarios/${selectedEscenario}/etiquetas`;
            axios.get(url)
                .then(response => setEtiquetasListarPorEscenario(response.data))
                .catch(error => console.error(error));
        }
    }, [selectedEscenario]);
    useEffect(() => {
        listarEtiquetasPorEscenario();
    }, [listarEtiquetasPorEscenario]);

    //Listar escenarios
    const listarEscenarios = useCallback(() => {
        const url = `http://127.0.0.1:8085/etiquetas/escenarios`
        axios.get(url)
        .then(response => setEscenarios(response.data))
        .catch(error => console.error(error));
    }, []);
    useEffect(() => {
        listarEscenarios();
    }, [listarEscenarios]);

    //Listar servicios
    const listarServicios=  useCallback(() => {
        const url = `http://127.0.0.1:8085/etiquetas/servicios`
        axios.get(url)
        .then(response => setServicios(response.data))
        .catch(error => console.error(error));
    }, []);
    useEffect(() => {
        listarServicios();
    }, [listarServicios]);
    
    //Listar jornadas
    const listarJornadas=  useCallback(() => {
        const url = `http://127.0.0.1:8085/turnos/jornadas`
        axios.get(url)
        .then(response => setJornadas(response.data))
        .catch(error => console.error(error));
    }, []);
    useEffect(() => {
        listarJornadas();
    }, [listarJornadas]);

    //Listar horarios
    const listarHorarios = useCallback(() => {
        const url = `http://127.0.0.1:8085/turnos/1/1/${asignatura.idAsignatura}` 
        axios.get(url) 
        .then(response => setHorarios(response.data))
        .catch(error => console.error(error));
    }, [asignatura.idAsignatura]);
    useEffect(() => {
        listarHorarios();
    }, [listarHorarios]);

     //LISTAR TURNOS ESTUDIANTE
     const listarTurnosEstudiante = (estudiante,fecha) => {
        const fechaR = `${fecha.getFullYear()}-${String(fecha.getMonth() + 1).padStart(2, '0')}-${String(fecha.getDate()).padStart(2, '0')}`;

        const url = `http://127.0.0.1:8085/turnos/turnosPorFechaEstudiante/${estudiante.id}/${fechaR}`

        axios.get(url)
            .then(response => {
            console.log('Turnos listados:');
            setTurnosEstudiante(response.data);
            })
            .catch(error => console.error(error));
    }

    const listarEstudiantesConAlimentacion = (fecha) => {
        const fechaR = `${fecha.getFullYear()}-${String(fecha.getMonth() + 1).padStart(2, '0')}-${String(fecha.getDate()).padStart(2, '0')}`;
        
        const url = `http://127.0.0.1:8085/turnos/estudiantesConAlimentacion/1/${asignatura.idAsignatura}/1/${fechaR}`

        axios.get(url)
            .then(response => {
            console.log('Turnos listados:');
            setEstudiantesConAlimentacion(response.data);
            })
            .catch(error => console.error(error));

    }

    const handleEstudiantesConAlimentacion = () => {
        
        if (diaSeleccionado) {
          listarEstudiantesConAlimentacion(diaSeleccionado);
          setVisibleAlimentacion(true);
        } else {
          // Acción a realizar cuando no hay un día seleccionado
          showErrorAlimentacion();
          console.log("No se ha seleccionado un día");
        }
      };

    //Crear etiqueta
    const handleCrearEtiqueta = (etiquetaCrear) => {
        const url = `http://127.0.0.1:8085/etiquetas/`;
      
        axios.post(url, etiquetaCrear)
          .then(response => {
            console.log('Etiqueta registrada:', response.data);
            listarEtiquetasCreadas();
            showSuccessCrearEtiqueta();
            setValueCrearEtiqueta('');
            setSelectedEscenario(null);
          })
          .catch(error => {
            console.error('Error al registrar la etiqueta:', error);
            const errorMessage = error.response && error.response.data && error.response.data.message ? error.response.data.message : 'Error desconocido';
            showErrorCrearEtiqueta(errorMessage);
          });
      }

    //Asociar servicio a una etiqueta
    const asociarServicioEtiqueta = (etiquetaAsociada) => {
        const url = `http://localhost:8085/etiquetas/asociar`
        axios.put(url, etiquetaAsociada)
          .then(response => {
            console.log('Servicio asociado a la etiqueta con exito:', response.data);
            listarEtiquetasAsociadas();
            showSuccessEtiquetaAsociado();
          })
          .catch(error => {
            console.error('Error al asociar el servicio a la etiqueta:', error);
            showErrorEtiquetaAsociado();
          });
    }

    //Crear Turno
    const crearTurno = (params) => {
        const url = `http://127.0.0.1:8085/turnos/`
        axios.post(url, params)
          .then(response => {
            console.log('Turno creado:', response.data);
            showSuccessCrearTurno();
            listarTurnosEstudiante(selectedEstudiante,selectedDate);
            listarHorarios();
            setSelectedEscenario(null);
            setSelectedJornada(null);
            setSelectedEtiqueta(null);
          })
          .catch(error => {
            console.error('Error al crear turno:', error);
            //showErrorEtiquetaAsociado();
          });
    }

    //Eliminar turno
    const eliminarTurno = async () => {
        try {
            const response = await fetch(`http://127.0.0.1:8085/turnos/${turnoIdEliminar}`, {
                method: 'DELETE',
            });
        
            if (response.ok) {
                console.log(`Turno con ID ${turnoIdEliminar} eliminado.`);
                listarTurnosEstudiante(selectedEstudiante,selectedDate); //actualiza turnos
                showInfoEliminarTurno();
                listarHorarios();
            } else {
            console.log(`No se pudo eliminar el Turno.`);
            }
        } catch (error) {
            console.error(error);
        }
            setConfirmDialogVisibleTurnosEliminar(false);
            setTurnoIdEliminar(null);
    }
    const handleEliminarTurno = (id) => {
        setConfirmDialogVisibleTurnosEliminar(true);
        setTurnoIdEliminar(id);
    };

    //Eliminar etiqueta
    const eliminarEtiqueta = async () => {
        try {
            const response = await fetch(`http://127.0.0.1:8085/etiquetas/${etiquetaIdEliminar}`, {
            method: 'DELETE'
            });
        
            if (response.ok) {
                console.log(`Etiqueta con ID ${etiquetaIdEliminar} eliminada.`);
                listarEtiquetasCreadas(); //actualiza etiquetas
                showInfoEliminarEtiquetaCreada();
                listarEtiquetasAsociadas();
            } else {
            console.log(`No se pudo eliminar la etiqueta con ID ${etiquetaIdEliminar}.`);
            }
        } catch (error) {
            console.error(error);
        }
            setConfirmDialogVisibleEtiquetas(false);
            setEtiquetaIdEliminar(null);
    }
    const handleEliminarEtiqueta = (id) => {
        setConfirmDialogVisibleEtiquetas(true);
        setEtiquetaIdEliminar(id);
    };

    //Eliminar servicio asociado a una etiqueta
    const eliminarServicioAsociadoEtiqueta = async () => {
        //const url = `http://localhost:8085/etiquetas/2/eliminarAsosiacion`
        try {
            
            const response = await fetch(`http://localhost:8085/etiquetas/${etiquetaIdEliminarAsociado}/eliminarAsosiacion`, {
            method: 'PUT'
            });
        
            if (response.ok) {
                console.log(`Servicio asociado a la etiqueta con ID ${etiquetaIdEliminarAsociado} eliminado.`);
                listarEtiquetasAsociadas(); //actualiza etiquetas
                showInfoEliminarEtiquetaAsociado();
            } else {
                console.log(`No se pudo eliminar el servicio asociado a la etiqueta con ID ${etiquetaIdEliminarAsociado}.`);
            }
        } catch (error) {
            console.error(error);
        }
            setConfirmDialogVisibleEtiquetasAsociado(false);
            setEtiquetaIdEliminarAsociado(null);
    }

    const handleEliminarEtiquetaAsociado = (id) => {
        setConfirmDialogVisibleEtiquetasAsociado(true);
        setEtiquetaIdEliminarAsociado(id);
    };

    //INFORMACION HORARIO-TURNO    
    const informacionHorarioTurno = (estudiante,fecha) => {
        const fechaR = `${fecha.getFullYear()}-${String(fecha.getMonth() + 1).padStart(2, '0')}-${String(fecha.getDate()).padStart(2, '0')}`;

        const url = `http://127.0.0.1:8085/turnos/horarioTurno/${estudiante.id}/${fechaR}`

        axios.get(url)
            .then(response => {
            setInfoHorarioTurno(response.data);
            })
            .catch(error => console.error(error));
    }

    const listarEstudiantesSeleccionados = useCallback(() => {
        const url = `http://127.0.0.1:8085/turnos/estudiantesSeleccionados/1/${asignatura.idAsignatura}/1`

        axios.get(url)
            .then(response => {
            console.log('Estudiantes listados:');
            setEstudiantesSeleccionados(response.data);
            })
            .catch(error => console.error(error));
        }, [asignatura.idAsignatura]);

        useEffect(() => {
        listarEstudiantesSeleccionados();
        }, [listarEstudiantesSeleccionados]);

    const handleEstudianteSeleccionado = (id) => {

        //PUT CAMBIAR ESTADO
        const estadoEstudiante = {
            puId: id,
            progId: 1,
            asigId: asignatura.idAsignatura,
            cooId: 1,
            estado: estudiantesSeleccionados.some(e => e.id === id) ? false : true
        };

        const url = `http://127.0.0.1:8085/turnos/seleccion`
        axios.put(url, estadoEstudiante)
          .then(response => {
            console.log('Estado cambiado:', response.data);
            listarEstudiantesSeleccionados();
            //showSuccessEtiquetaAsociado();
          })
          .catch(error => {
            console.error('Error :', error);

            //showErrorEtiquetaAsociado();
          });
    };

    const handleDesSeleccionarEstudiantes = () => {
        const estudiantesDesSeleccionados ={
            progId: 1,
            asigId: asignatura.idAsignatura,
            cooId: 1
        };
      
        const url = `http://127.0.0.1:8085/turnos/deseleccionarTodos`;
              
        axios.put(url, estudiantesDesSeleccionados)
          .then(response => {
            console.log('Estudiantes deseleccionados:', response.data);
            listarEstudiantesSeleccionados();
            //showSuccessEtiquetaAsociado();
          })
          .catch(error => {
            console.error('Error:', error);
            //showErrorEtiquetaAsociado();
          });
      };

    const estudiantesNoSeleccionados = estudiantes.filter((estudiante) => {
        return !estudiantesSeleccionados.some((est) => est.id === estudiante.id);
      });

      const [filtroEstudiantesTodos, setFiltroEstudiantesTodos] = useState('');
      const [filtroEstudiantesSeleccionados, setFiltroEstudiantesSeleccionados] = useState('');
      const [filtroEstudiantesNoSeleccionados, setFiltroEstudiantesNoSeleccionados] = useState('');

      const handleFiltroChangeTodos = (event) => {
        setFiltroEstudiantesTodos(event.target.value);
      };

      const handleFiltroChangeSeleccionados = (event) => {
        setFiltroEstudiantesSeleccionados(event.target.value);
      };

      const handleFiltroChangeNoSeleccionados = (event) => {
        setFiltroEstudiantesNoSeleccionados(event.target.value);
      };

      const estudiantesFiltradosTodos = estudiantes.filter(
        (estudiante) =>
          estudiante.nombreCompleto
            .toLowerCase()
            .includes(filtroEstudiantesTodos.toLowerCase())
      );

      const estudiantesFiltradosSeleccionados = estudiantesSeleccionados.filter(
        (estudiante) =>
          estudiante.nombreCompleto
            .toLowerCase()
            .includes(filtroEstudiantesSeleccionados.toLowerCase())
      );

      const estudiantesFiltradosNoSeleccionados = estudiantesNoSeleccionados.filter(
        (estudiante) =>
          estudiante.nombreCompleto
            .toLowerCase()
            .includes(filtroEstudiantesNoSeleccionados.toLowerCase())
      );

      const handleClickDiaSeleccionado = (fecha) => {
        setDiaSeleccionado(fecha);
      }

      

    return (
    <div className="card">
        <TabView>
            <TabPanel header="Estudiantes">
            <div className='component-container-grid-gestion' style={{ display: 'flex', flexDirection: 'row' }}>
                <div style={{ borderRight: '2px solid #bebbbb',flex: 2 }}>
                    <div className='buscar-estudiantes'>
                        <p>Buscar Estudiantes</p>
                    </div>
                    <div className="card flex flex-wrap justify-content-center gap-3">
                        <Toast ref={toast} />
                        <span className="p-input-icon-left">
                            <i className="pi pi-search" />
                            {/* CAMBIO */}
                            <InputText 
                            placeholder="Ingrese el nombre de un estudiante" 
                            style={{fontSize: '0.5rem', width: '200px', height: '30px'}} 
                            value={searchText}
                            onChange={(e) => {
                                setSearchText(e.target.value);
                                clearTimeout(timer);
                                setTimer(setTimeout(() => {
                                    buscarEstudiantes(e.target.value);
                                }, 500));
                            }}
                            />
                            {/* CAMBIO */}
                        </span>
                    </div>
                    {/* CAMBIO */}
                    <div className='component-grid' style={{ maxHeight: '400px', overflowY: 'auto', }}>
                        <Box sx={{ flexGrow: 1 }}>
                            <Grid container spacing={{ xs: 2, md: 3 }} columns={1}>
                                {estudiantesBusqueda.map((estudianteBusqueda, index) => {

                                    const footerStyle = {
                                        paddingTop: '2px'
                                    };

                                    const handleGestionarClickRegistrar = (estudianteRegistrar) => {
                                        const url = `http://127.0.0.1:8085/1/1/asignaturas/${asignatura.idAsignatura}/estudiantes/${estudianteRegistrar.id}`;
                                      
                                        axios.post(url, estudianteRegistrar)
                                          .then(response => {
                                            console.log('Estudiante registrado:', response.data);
                                            showSuccessRegistrarEstudiante();
                                            setEstudiantesBusqueda([]);
                                            setSearchText('');
                                            fetchEstudiantes();
                                            
                                          })
                                          .catch(error => {
                                            console.error('Error al registrar estudiante:', error);
                                            showErrorRegistrarEstudiante();
                                          });
                                    }
                                
                                    const footer = (
                                        <div style={footerStyle}>
                                            
                                            <Button label="Registrar" style={{ fontSize: '0.5rem', backgroundColor: 'blue' }}  onClick={() => handleGestionarClickRegistrar(estudianteBusqueda)} />
                                        </div>
                                    );

                                    const cardStyle = {
                                        height: '160px',
                                        width: '250px'
                                    };

                                    return (
                                    <Grid item xs={2} sm={4} md={4} key={index}>
                                        <div className="custom-grid-item">
                                        <Card footer={footer}  style={cardStyle}>
                                            <PerfilEstudiante name={estudianteBusqueda.nombreCompleto} codigo={estudianteBusqueda.identificacion} correo={estudianteBusqueda.usuario} src="https://primefaces.org/cdn/primereact/images/avatar/amyelsner.png"/>
                                        </Card>
                                        </div>
                                    </Grid>
                                    )
                                })}
                            </Grid>
                        </Box>
                    </div>
                    {/* CAMBIO */}
                </div>
                <div style={{ paddingLeft: '10px',flex: 7 }}> 
                <Toast ref={toast} />
                    <div className='navegador-estudiantes-registrados' style={{ display: 'flex', justifyContent: 'space-between', alignItems: 'center' }}>
                        <p></p>
                        <p style={{ textAlign: 'center', margin: 0 }}>Estudiantes Registrados</p>
                        <Button label="Eliminar todo" style={{ fontSize: '0.5rem', backgroundColor: 'red'}}  onClick={() => handleEliminarTodosClick()}/>
                        <ConfirmDialog visible={confirmDialogVisibleTodos} onHide={() => setConfirmDialogVisibleTodos(false)} message="¿Estás seguro de que deseas eliminar todos los estudiantes?" header="Confirmar eliminación" acceptLabel="Aceptar" rejectLabel="Cancelar" icon="pi pi-exclamation-triangle" accept={() => eliminarTodo()} />
                    </div>
                    <div className='component-grid' style={{ maxHeight: '450px', overflowY: 'auto' }}>
                        <Box sx={{ flexGrow: 1 }}>
                            <Grid container spacing={{ xs: 2, md: 3 }} columns={{ xs: 4, sm: 8, md: 12 }}>
                                {estudiantes.map((estudiante, index) => {

                                    const footerStyle = {
                                        paddingTop: '2px'
                                    };
                                    //window.location.href = '/estudiantes';
                                    const handleEliminarClick = (id) => {
                                        setConfirmDialogVisible(true);
                                        setEstudianteIdEliminar(id);
                                      };
                                
                                    const footer = (
                                        <div style={footerStyle}>
                                            <Button label="Eliminar registro" style={{ fontSize: '0.5rem', backgroundColor: 'red' }}  onClick={() => handleEliminarClick(estudiante.id)} />
                                            <ConfirmDialog visible={confirmDialogVisible} onHide={() => setConfirmDialogVisible(false)} message="¿Estás seguro de que deseas eliminar este estudiante?" header="Confirmar eliminación" acceptLabel="Aceptar" rejectLabel="Cancelar" icon="pi pi-exclamation-triangle" accept={() => handleConfirmDialogAceptar()} />

                                        </div>
                                    );

                                    const cardStyle = {
                                        height: '160px',
                                        width: '245px'
                                    };

                                    return (
                                    <Grid item xs={2} sm={4} md={4} key={index}>
                                        <div className="custom-grid-item">
                                        <Card footer={footer}  style={cardStyle}>
                                            <PerfilEstudiante name={estudiante.nombreCompleto} codigo={estudiante.identificacion} correo={estudiante.usuario} src="https://primefaces.org/cdn/primereact/images/avatar/amyelsner.png"/>
                                        </Card>
                                        </div>

                                        
                                    </Grid>
                                    )
                                })}
                            </Grid>
                        </Box>
                    </div>
                </div>
            </div>
            </TabPanel>
            <TabPanel header="Profesores">
                <h1>En construcción</h1>

            </TabPanel>
            <TabPanel header="Rotes">
                <h1>En construcción</h1>
            </TabPanel>
            <TabPanel header="Turnos">
            <div className='component-container-grid-gestion'>
                <div style={{marginBottom: '10px'}}>
                    <Button label="Gestión estudiantes" style={{ fontSize: '0.8rem', backgroundColor: 'blue', marginRight: '5px' }} onClick={() => setVisibleEstudiantes(true)} />
                    <Dialog header="GESTIÓN ESTUDIANTES" visible={visibleEstudiantes} style={{ width: '65vw' }} onHide={() => setVisibleEstudiantes(false)}>
                        <div>
                            <span className="p-input-icon-left" style={{ marginRight: '15px' }}>
                                <i className="pi pi-search" />
                                {botonEstudiantesTodos && (
                                    <InputText
                                    placeholder="Buscar estudiante por nombre"
                                    style={{ fontSize: '0.8rem', width: '230px', height: '30px' }}
                                    value={filtroEstudiantesTodos}
                                    onChange={handleFiltroChangeTodos}
                                    />
                                )}
                                {botonEstudiantesSeleccionados && (
                                    <InputText
                                    placeholder="Buscar estudiante por nombre"
                                    style={{ fontSize: '0.8rem', width: '230px', height: '30px' }}
                                    value={filtroEstudiantesSeleccionados}
                                    onChange={handleFiltroChangeSeleccionados}
                                    />
                                )}
                                {botonEstudiantesNoSeleccionados && (
                                    <InputText
                                    placeholder="Buscar estudiante por nombre"
                                    style={{ fontSize: '0.8rem', width: '230px', height: '30px' }}
                                    value={filtroEstudiantesNoSeleccionados}
                                    onChange={handleFiltroChangeNoSeleccionados}
                                    />
                                )}
                               
                            </span>
                            <Button label="TODOS" style={{ fontSize: '0.8rem',marginLeft: '10px', backgroundColor: botonEstudiantesTodos ? 'red' : 'grey' }} onClick={() => handleClickEstadoBotonesEstudiantes('todos')}/>
                            <Button label="SELECCIONADOS" style={{ fontSize: '0.8rem', backgroundColor: botonEstudiantesSeleccionados ? 'red' : 'grey' }} onClick={() => handleClickEstadoBotonesEstudiantes('seleccionados')} />
                            <Button label="NO SELECCIONADOS" style={{ fontSize: '0.8rem', backgroundColor: botonEstudiantesNoSeleccionados ? 'red' : 'grey' }} onClick={() => handleClickEstadoBotonesEstudiantes('noSeleccionados')} />
                            <Button label="DESMARCAR TODOS" style={{ fontSize: '0.8rem', backgroundColor: 'red', marginLeft: '25px' }}  onClick={() => handleDesSeleccionarEstudiantes()} />
                            {botonEstudiantesTodos ? (
                                <div>
                                    <div className='component-grid' style={{ maxHeight: '650px', overflowY: 'auto' }}>
                                    <Box sx={{ flexGrow: 1 }}>
                                        <Grid container spacing={{ xs: 2, md: 3 }} columns={{ xs: 4, sm: 8, md: 12 }}>
                                            {estudiantesFiltradosTodos.map((estudiante, index) => {
                                            const isSelected = estudiantesSeleccionados.some((est) => est.id === estudiante.id);

                                            return (
                                                <Grid item xs={2} sm={4} md={4} key={index}>
                                                <div style={{ border: '1px solid grey', padding: '5px', display: 'flex', alignItems: 'center', width: '240px', justifyContent: 'center', borderRadius: '10px' }}>
                                                    <InputText value={estudiante.nombreCompleto} readOnly style={{ border: "none", boxShadow: "none" }} />
                                                    <Checkbox onChange={() => handleEstudianteSeleccionado(estudiante.id)} checked={isSelected} />
                                                </div>
                                                </Grid>
                                            );
                                            })}
                                        </Grid>
                                    </Box>
                                    </div>
                                </div>
                            ) : botonEstudiantesSeleccionados ? (
                                <div>
                                    <div className='component-grid' style={{ maxHeight: '450px', overflowY: 'auto' }}>
                                    <Box sx={{ flexGrow: 1 }}>
                                        <Grid container spacing={{ xs: 2, md: 3 }} columns={{ xs: 4, sm: 8, md: 12 }}>
                                            {estudiantesFiltradosSeleccionados.map((estudiante, index) => {
                                            const isSelected = estudiantesSeleccionados.some((est) => est.id === estudiante.id);

                                            return (
                                                <Grid item xs={2} sm={4} md={4} key={index}>
                                                <div style={{ border: '1px solid grey', padding: '5px', display: 'flex', alignItems: 'center', width: '240px', justifyContent: 'center', borderRadius: '10px' }}>
                                                    <InputText value={estudiante.nombreCompleto} readOnly style={{ border: "none", boxShadow: "none" }} />
                                                    <Checkbox onChange={() => handleEstudianteSeleccionado(estudiante.id)} checked={isSelected} />
                                                </div>
                                                </Grid>
                                            );
                                            })}
                                        </Grid>
                                    </Box>
                                    </div>
                                </div>
                                
                            ) : (
                                <div>
                                    <div className='component-grid' style={{ maxHeight: '450px', overflowY: 'auto' }}>
                                    <Box sx={{ flexGrow: 1 }}>
                                        <Grid container spacing={{ xs: 2, md: 3 }} columns={{ xs: 4, sm: 8, md: 12 }}>
                                            
                                            {estudiantesFiltradosNoSeleccionados.map((estudiante, index) => {
                                            const isSelected = estudiantesSeleccionados.some((est) => est.id === estudiante.id);
                                                
                                            return (
                                                <Grid item xs={2} sm={4} md={4} key={index}>
                                                <div style={{ border: '1px solid grey', padding: '5px', display: 'flex', alignItems: 'center', width: '240px', justifyContent: 'center', borderRadius: '10px' }}>
                                                    <InputText value={estudiante.nombreCompleto} readOnly style={{ border: "none", boxShadow: "none" }} />
                                                    <Checkbox onChange={() => handleEstudianteSeleccionado(estudiante.id)} checked={isSelected} />
                                                </div>
                                                </Grid>
                                            );
                                            })}
                                        
                                        </Grid>
                                    </Box>
                                    </div>
                                </div>
                            )}
                        </div>
                    </Dialog>
                    <Button label="Gestión etiquetas" style={{ fontSize: '0.8rem', backgroundColor: 'blue', marginRight: '5px' }} onClick={() => {setVisibleEtiqueta(true); setVisibleAsociacion(true)}} /> 
                    {botonCrearEtiquetas ?
                        <Dialog header="GESTIONAR ETIQUETAS" visible={visibleEtiqueta} onHide={() => {setVisibleEtiqueta(false); setVisibleAsociacion(false)}}>

                        <div style={{ textAlign: 'center' }}>
                            <Button label="CREAR ETIQUETAS" style={{ fontSize: '0.8rem', backgroundColor: botonCrearEtiquetas ? 'red' : 'grey' }} onClick={() => handleClickEstadoBotones('crear')} />
                            <Button label="ASOCIAR ETIQUETAS" style={{ fontSize: '0.8rem', backgroundColor: botonAsociarEtiquetas ? 'red' : 'grey' }} onClick={() => handleClickEstadoBotones('asociar')} />
                        </div> 
                        <Divider/>
                        <div style={{ display: 'flex', flexDirection: 'row', alignItems: 'center', height: '200px', width: '100%'}}>
                            <div style={{ marginLeft:'30px', textAlign: 'center' }}>
                                <p style={{ fontWeight: 'bold' }}>
                                    <Badge value="1" style={{ marginRight: '20px' }}></Badge>
                                    Nombre de etiqueta
                                </p>
                                <p>Nombre de la etiqueta del Hospital *</p>
                                <InputText value={valueCrearEtiqueta} onChange={(e) => setValueCrearEtiqueta(e.target.value)} placeholder="Ingrese un nombre" />
                                <p style={{ fontSize: '0.7rem'}}>&nbsp;</p >
                            </div>
                            <Divider layout="vertical" />
                            <div style={{marginLeft:'30px', textAlign: 'center' }}>
                                <p style={{fontWeight: 'bold' }}>
                                    <Badge value="2" style={{ marginRight: '20px' }}></Badge>
                                    Selección de escenario
                                </p>
                                <div style={{ display: 'block' }}>
                                    <p>Seleccione el hospital *</p>
                                    <Dropdown 
                                        value={selectedEscenario} 
                                        onChange={(e) => setSelectedEscenario(e.value)} 
                                        options={escenarios.map((escenario) => ({label: escenario.nombreEscenario, value: escenario.idEscenario}))} 
                                        placeholder="Seleccione el Hospital"
                                    />
                                </div>
                                <div style={{ display: 'block', marginTop: '10px' }}>
                                    <Toast ref={toast} />
                                    <Button label="CREAR" style={{ fontSize: '0.5rem', backgroundColor: 'blue' }} onClick={() => handleCrearEtiqueta(etiquetaNueva)}  disabled={!isFormValid} />
                                </div>
                            </div>
                        </div>
                        <Divider/>
                        <div style={{ textAlign: 'center', width:'600px' }}>
                            <p style={{fontWeight: 'bold' }}>LISTA DE ETIQUETAS CREADAS </p>
                            <Toast ref={toast} />
                            <DataTable value={etiquetasListarCreadas} tableStyle={{ width:'500px' , margin: 'auto'}} bodystyle={{ textAlign: 'center' }}>
                                <Column field="nombreEtiqueta" header="Etiqueta"></Column>
                                <Column field="nombreEscenario" header="Hospital"></Column>
                                <Column header="Eliminar" body={(rowData) => (
                                    <div>
                                        <button style={{ border: 'none', background: 'none' }} onClick={() => handleEliminarEtiqueta(rowData.idEtiqueta)}>
                                            <i className="pi pi-trash"></i>
                                        </button>
                                        <ConfirmDialog visible={confirmDialogVisibleEtiquetas} onHide={() => setConfirmDialogVisibleEtiquetas(false)} message="¿Estás seguro de que deseas eliminar esta etiqueta?" header="Confirmar eliminación" acceptLabel="Aceptar" rejectLabel="Cancelar" icon="pi pi-exclamation-triangle" accept={() => eliminarEtiqueta()} />
                                    </div>
                                )}>
                                </Column>
                            </DataTable>
                        </div>
                    </Dialog>
                    :
                        <Dialog header="GESTIONAR ETIQUETAS" visible={visibleAsociacion} onHide={() => {setVisibleAsociacion(false); setVisibleEtiqueta(false)}}>
                            <div style={{ textAlign: 'center' }}>
                            <Button label="CREAR ETIQUETAS" style={{ fontSize: '0.8rem', backgroundColor: botonCrearEtiquetas ? 'red' : 'grey' }} onClick={() => handleClickEstadoBotones('crear')} />
                            <Button label="ASOCIAR ETIQUETAS" style={{ fontSize: '0.8rem', backgroundColor: botonAsociarEtiquetas ? 'red' : 'grey' }} onClick={() => handleClickEstadoBotones('asociar')} />
                        </div> 
                        <Divider/>
                        <div style={{ display: 'flex', flexDirection: 'row', alignItems: 'center', height: '200px', width: '100%'}}>
                            <div style={{ marginLeft:'30px', textAlign: 'center' }}>
                                <p style={{ fontWeight: 'bold' }}>
                                    <Badge value="1" style={{ marginRight: '20px' }}></Badge>
                                    Seleccion de etiqueta
                                </p>
                                <p>Nombre de la etiqueta del Hospital *</p>
                                <Dropdown 
                                        value={selectedEtiqueta} 
                                        onChange={(e) => setSelectedEtiqueta(e.value)} 
                                        options={etiquetasListarCreadas.map((etiqueta) => ({label: etiqueta.nombreEtiqueta +" - "+ etiqueta.nombreEscenario, value: etiqueta.idEtiqueta}))} 
                                        placeholder="Etiqueta"
                                    />
                                <p style={{ fontSize: '0.7rem'}}>&nbsp;</p >
                            </div>
                            <Divider layout="vertical" />
                            <div style={{marginLeft:'30px', textAlign: 'center' }}>
                                <p style={{fontWeight: 'bold' }}>
                                    <Badge value="2" style={{ marginRight: '20px' }}></Badge>
                                    Selección de servicio
                                </p>
                                <div style={{ display: 'block' }}>
                                    <p>Servicio del hospital *</p>
                                    <Dropdown 
                                        value={selectedServicio} 
                                        onChange={(e) => setSelectedServicio(e.value)} 
                                        options={servicios.map((servicio) => ({label: servicio.nombreServicio, value: servicio.idServicio}))} 
                                        placeholder="Servicio"
                                    />
                                </div>
                                <div style={{ display: 'block', marginTop: '10px' }}>
                                    <Toast ref={toast} />
                                    <Button label="ASOCIAR" style={{ fontSize: '0.5rem', backgroundColor: 'blue' }} onClick={() => asociarServicioEtiqueta(etiquetaAsociacionServicio)} disabled={!isFormValidAsociado}/>
                                </div>
                            </div>
                        </div>
                        <Divider/>
                        <div style={{ textAlign: 'center', width:'600px' }}>
                            <p style={{fontWeight: 'bold' }}>LISTA DE ETIQUETAS ASOCIADAS </p>
                            <Toast ref={toast} />
                            <DataTable value={etiquetasListarAsociadas} tableStyle={{ width:'500px' , margin: 'auto'}} bodystyle={{ textAlign: 'center' }}>
                                <Column field="nombreEtiqueta" header="Etiqueta"></Column>
                                <Column field="nombreServicio" header="Servicio"></Column>
                                <Column field="nombreEscenario" header="Hospital"></Column>
                                <Column header="Eliminar" body={(rowData) => (
                                    <div>
                                        <button style={{ border: 'none', background: 'none' }} onClick={() => handleEliminarEtiquetaAsociado(rowData.idEtiqueta)}>
                                            <i className="pi pi-trash"></i>
                                        </button>
                                        <ConfirmDialog visible={confirmDialogVisibleEtiquetasAsociado} onHide={() => setConfirmDialogVisibleEtiquetasAsociado(false)} message="¿Estás seguro de que deseas eliminar la asociación de esta etiqueta?" header="Confirmar eliminación" acceptLabel="Aceptar" rejectLabel="Cancelar" icon="pi pi-exclamation-triangle" accept={() => eliminarServicioAsociadoEtiqueta()} />
                                    </div> 
                                )}>
                                </Column>
                            </DataTable>
                        </div>
                    </Dialog>
                    }
                    <Toast ref={toast} />
                    <Button label="Alimentación" onClick={() => handleEstudiantesConAlimentacion()} style={{ fontSize: '0.8rem', backgroundColor: 'blue', marginRight: '15px' }} />
                    <Dialog header="LISTA ESTUDIANTES CON ALIMENTACIÓN" visible={visibleAlimentacion} style={{ width: '65vw' }} onHide={() => setVisibleAlimentacion(false)}>
                        <div >
                        <div style={{ display: 'flex', justifyContent: 'center', alignItems: 'center' }}>
                            <p style={{ marginRight: '10px' }}><span style={{ fontWeight: 'bold' }}>MES:</span> {months.find(m => m.value === month)?.label}</p>
                            <p style={{ marginRight: '10px' }}><span style={{ fontWeight: 'bold' }}>AÑO:</span> {year}</p>
                            <p style={{  }}><span style={{ fontWeight: 'bold' }}>DIA:</span> {diaSeleccionado.getDate() + ' / ' + diaSeleccionado.toLocaleDateString('es-ES', { weekday: 'long' })}</p>
                            <br></br>
                        </div>
                        <DataTable value={estudiantesConAlimentacion} tableStyle={{ minWidth: '50rem' }}>
                                    <Column header="Nombre y Apellidos"
                                    body={(rowData) => {
                                        return (
                                        <div style={{ display: 'flex', alignItems: 'center' }}>
                                            <PerfilEstudiante src="https://primefaces.org/cdn/primereact/images/avatar/amyelsner.png" style={{ marginBottom: '0.5rem' }} />
                                            <p style={{ fontSize: '0.8rem', margin: 0, fontWeight: 'bold', marginLeft: '0.5rem' }}>{rowData.nombreEstudiante}</p>      
                                        </div>
                                        );
                                    }}

                                    />
                                    <Column header="Desayuno"
                                    body={(rowData) => {
                                        return (
                                        <div style={{ display: 'flex', alignItems: 'center' }}>
                                            {rowData.desayuno ? (
                                                <Badge value="✓" severity="success"></Badge>
                                                ) : (
                                                <Badge value="X" severity="danger"></Badge>
                                            )}
                                        </div>
                                        );
                                    }}
                                    />
                                    <Column header="Almuerzo"
                                     body={(rowData) => {
                                        return (
                                        <div style={{ display: 'flex', alignItems: 'center' }}>
                                            {rowData.almuerzo ? (
                                                <Badge value="✓" severity="success"></Badge>
                                                ) : (
                                                <Badge value="X" severity="danger"></Badge>
                                            )}
                                        </div>
                                        );
                                    }}
                                    />
                                    <Column header="Comida"
                                     body={(rowData) => {
                                        return (
                                        <div style={{ display: 'flex', alignItems: 'center' }}>
                                            {rowData.comida ? (
                                                <Badge value="✓" severity="success"></Badge>
                                                ) : (
                                                <Badge value="X" severity="danger"></Badge>
                                            )}
                                        </div>
                                        );
                                    }}
                                    />
                                    <Column header="Horario Turno"
                                     body={(rowData) => {
                                        return (
                                        <div style={{ display: 'flex', alignItems: 'center' }}>
                                            <p style={{ fontSize: '0.8rem', margin: 0, fontWeight: 'bold', marginLeft: '0.5rem' }}>{rowData.rangoHorario}</p>
                                        </div>
                                        );
                                    }}
                                    />
                        </DataTable>
                        </div>
                    </Dialog>
                    <span className="p-input-icon-left" style={{ marginRight: '15px' }}>
                        <i className="pi pi-search" />
                        <InputText placeholder="Buscar estudiante" style={{fontSize: '0.8rem', width: '180px', height: '30px'}} value={filtroEstudiantesSeleccionados} onChange={handleFiltroChangeSeleccionados} />
                    </span>
                    <Dropdown options={years} value={year} onChange={(e) => {
                        const newDate = new Date(fechaActual);
                            newDate.setFullYear(e.value);
                            setYear(e.value);
                            setFechaActual(newDate);
                    }}
                    placeholder="Seleccionar año" style={{ marginRight: '5px' }} />
                    <Dropdown options={months} value={month} onChange={(e) => {
                        const newDate = new Date(fechaActual);
                            newDate.setMonth(e.value);
                            setMonth(e.value);
                            setFechaActual(newDate);
                        }}
                        placeholder="Seleccionar mes" style={{ marginRight: '15px' }}
                    />
                    <button onClick={irSemanaAnterior} style={{ marginRight: '2px' }}>&lt;</button>
                    <button onClick={irSemanaSiguiente} style={{ marginRight: '10px' }}>&gt;</button>
                    <Button label="Validación de turnos" onClick={() => setVisibleValidarTurnos(true)} style={{ fontSize: '0.8rem', backgroundColor: 'red', marginRight: '5px' }}  />
                    <Dialog header="VALIDACIÓN DE TURNOS" visible={visibleValidarTurnos} style={{ width: '65vw' }} onHide={() => setVisibleValidarTurnos(false)}>
                        <div>
                            <Button label="MES:" style={{ fontSize: '0.8rem',marginLeft: '10px', backgroundColor: 'blue' }} />
                            <Button label="AÑO" style={{ fontSize: '0.8rem', backgroundColor: 'blue' }}/>
                            <span className="p-input-icon-left" style={{ marginRight: '15px' }}>
                                <i className="pi pi-search" />
                                <InputText
                                    placeholder="Buscar estudiante por nombre"
                                    style={{ fontSize: '0.8rem', width: '230px', height: '30px' }}
                                />
                            </span>
                            <Button label="TODOS" style={{ fontSize: '0.8rem',marginLeft: '10px', backgroundColor: botonTurnosTodos ? 'red' : 'grey' }} onClick={() => handleClickEstadoBotonesValidarTurno('todos')} />
                            <Button label="APROBADOS" style={{ fontSize: '0.8rem', backgroundColor: botonTurnosAprobados ? 'red' : 'grey' }} onClick={() => handleClickEstadoBotonesValidarTurno('aprobados')}/>
                            <Button label="NO APROBADOS" style={{ fontSize: '0.8rem', backgroundColor: botonTurnosNoAprobados ? 'red' : 'grey' }}  onClick={() => handleClickEstadoBotonesValidarTurno('noAprobados')} />
                            <Button label="SIN VALIDAR" style={{ fontSize: '0.8rem', backgroundColor: botonTurnosSinValidar ? 'red' : 'grey' }}  onClick={() => handleClickEstadoBotonesValidarTurno('sinValidar')} />
                        
                            <DataTable value={estudiantesFiltradosSeleccionados} tableStyle={{ minWidth: '50rem' }}>
                                <Column header="NOMBRE"
                                body={(rowData) => {
                                    return (
                                    <div style={{ display: 'flex', alignItems: 'center' }}>
                                        <PerfilEstudiante src="https://primefaces.org/cdn/primereact/images/avatar/amyelsner.png" style={{ marginBottom: '0.5rem' }} />
                                        <p style={{ fontSize: '0.8rem', margin: 0, fontWeight: 'bold', marginLeft: '0.5rem' }}>{rowData.nombreCompleto}</p>
                                    </div>
                                    );
                                 }}

                                 />
                                <Column header="¿EL ESTUDIANTE ASISTIÓ A LOS TURNOS?"></Column>
                                <Column header="ESTADO"></Column>
                                <Column header="OBSERVACIONES"></Column>
                            </DataTable>
                        
                        
                        
                        
                        
                        
                        </div>
                    </Dialog>
                </div>
                <div style={{ height: '425px', overflow: 'auto' }}>
                    <DataTable value={estudiantesFiltradosSeleccionados} tableStyle={{ minWidth: '50rem' }}>
                        <Column
                            header={
                                <div style={{ width: '80px', height:'55px', display: 'flex', justifyContent: 'space-between', alignItems: 'center', flexDirection: 'column', padding: '8px', position: 'relative' }}>
                                    <div style={{ alignSelf: 'flex-end', marginBottom: '0.5rem' }}>
                                        <div style={{ display: 'flex', flexDirection: 'row' }}>
                                        <p style={{ fontSize: '0.6rem', margin: 0, fontWeight: 'bold', marginRight: 'auto' }}>Fecha</p>
                                        </div>
                                    </div>
                                    <div style={{ position: 'absolute', top: '20px', left: '10px', width: '70%', height: '70%' }}>
                                        <div style={{ position: 'absolute', top: '0', left: '0', width: '100%', height: '1px', background: 'black', transform: 'rotate(25deg)' }}></div>
                                    </div>
                                    <div style={{ display: 'flex', justifyContent: 'flex-start' }}>
                                        <p style={{ fontSize: '0.6rem', margin: 0, fontWeight: 'bold', marginLeft: 0 }}>Estudiante</p>
                                    </div>
                                </div>

                            }
                            body={(rowData) => {
                                
                                return (
                                <div style={{ display: 'flex', justifyContent: 'center', alignItems: 'center' }}>
                                    <div style={{ display: 'flex', flexDirection: 'column', alignItems: 'center' }}>
                                    <PerfilEstudiante src="https://primefaces.org/cdn/primereact/images/avatar/amyelsner.png" style={{ marginBottom: '0.5rem' }} />
                                    <p style={{ fontSize: '0.6rem', margin: 0, fontWeight: 'bold' }}>{rowData.nombreCompleto}</p>
                                    </div>
                                </div>
                                );
                            }}
                        />

                        {[0, 1, 2, 3, 4, 5, 6].map((dayOfWeek) => {
                        const daysToAdd = dayOfWeek - fechaActual.getDay();
                        const dateColumn = new Date(year, month, fechaActual.getDate() + daysToAdd);

                        return (
                            <Column
                            header={
                                <Button onClick={() => handleClickDiaSeleccionado(dateColumn)} style={{ width: '100px' , border: 'none', background: 'none', color: 'black'}}>
                                    <div style={{ display: 'flex', flexDirection: 'column', alignItems: 'center', justifyContent: 'center', marginRight: 'auto' }}>
                                        <div>{dateColumn.toLocaleDateString('es-ES', { weekday: 'short' })}</div>
                                        <div>{dateColumn.getDate()}</div>
                                    </div>
                                </Button>
                            }
                            key={dayOfWeek}
                            body={(rowData) => {
                                
                                const formattedDateColumn = `${dateColumn.getFullYear()}-${String(dateColumn.getMonth() + 1).padStart(2, '0')}-${String(dateColumn.getDate()).padStart(2, '0')}`;
                                const hasMatchingHorario = horarios.find((horario) => horario.idEstudiante === rowData.id && horario.fechaTurno === formattedDateColumn);
                                
                                if (hasMatchingHorario) {
                                return (
                                    <CustomCard>
                                    <div style={{ display: 'flex', flexDirection: 'column', textAlign: 'left', alignItems: 'flex-start' }}>
                                            <p style={{fontSize: '0.5rem', margin: 0, fontWeight: 'bold', color: 'red' }}>Información de turno</p>
                                            <p style={{fontSize: '0.5rem', margin: 0}}>{hasMatchingHorario.nombreEscenario}</p>
                                            <p style={{fontSize: '0.5rem', margin: 0}}>{hasMatchingHorario.nombreEtiqueta}</p>
                                            <p style={{fontSize: '0.5rem', margin: 0}}>{hasMatchingHorario.franjasJornada}</p>
                                            <Button
                                                label='Ver más información'
                                                style={{ fontSize: '0.5rem', textDecoration: 'underline', background: 'none', border: 'none', color: 'blue', padding: 0, margin: 0 }}
                                                onClick={() => {
                                                    setSelectedDate(dateColumn);
                                                    setSelectedEstudiante(rowData);
                                                    setVisibleMasInformacion(true);
                                                    informacionHorarioTurno(rowData, dateColumn);
                                                    }}
                                            />
                                            <Button
                                                label='Gestionar Turno'
                                                style={{ fontSize: '0.5rem', textDecoration: 'underline', background: 'none', border: 'none', color: 'blue', padding: 0, margin: 0 }}
                                                onClick={() => {
                                                setSelectedDate(dateColumn);
                                                setSelectedEstudiante(rowData);
                                                setVisibleTurnos(true);
                                                listarTurnosEstudiante(rowData, dateColumn);
                                                }}
                                            />
                                        </div>
                                        {visibleMasInformacion && (
                                        <Dialog header="INFORMACIÓN TURNO" visible={visibleMasInformacion} onHide={() => setVisibleMasInformacion(false)}>
                                            <div>
                                                <p style={{marginLeft:'30px' ,marginRight: '25px' }}><span style={{ fontWeight: 'bold' }}>Estudiante:</span> {infoHorarioTurno.nombreEstudiante}</p>
                                                <p style={{marginLeft:'30px' ,marginRight: '25px' }}><span style={{ fontWeight: 'bold' }}>Horario:</span> {selectedDate.toLocaleDateString('es-ES', { weekday: 'long' })} {infoHorarioTurno.rangoHorario}</p>
                                                <div style={{ display: 'flex', alignItems: 'center' }}>
                                                    <p style={{ marginLeft: '30px', marginRight: '25px' }}>
                                                        <span style={{ fontWeight: 'bold' }}>Desayuno:</span>
                                                    </p>
                                                    {infoHorarioTurno.desayuno ? (
                                                        <Tag icon="pi pi-check" severity="success" rounded>Apto</Tag>
                                                        ) : (
                                                        <Tag icon="pi pi-times" severity="danger" rounded>No Apto</Tag>
                                                    )}
                                                    
                                                    </div>
                                                    <div style={{ display: 'flex', alignItems: 'center' }}>
                                                    <p style={{ marginLeft: '30px', marginRight: '25px' }}>
                                                        <span style={{ fontWeight: 'bold' }}>Almuerzo:</span>
                                                    </p>
                                                    {infoHorarioTurno.almuerzo ? (
                                                        <Tag icon="pi pi-check" severity="success" rounded>Apto</Tag> 
                                                        ) : (
                                                        <Tag icon="pi pi-times" severity="danger" rounded>No Apto</Tag>
                                                    )}
                                                    </div>
                                                    <div style={{ display: 'flex', alignItems: 'center' }}>
                                                    <p style={{ marginLeft: '30px', marginRight: '25px' }}>
                                                        <span style={{ fontWeight: 'bold' }}>Comida:</span>
                                                    </p>
                                                    {infoHorarioTurno.comida ? (
                                                        <Tag icon="pi pi-check" severity="success" rounded>Apto</Tag>
                                                        ) : (
                                                        <Tag icon="pi pi-times" severity="danger" rounded>No Apto</Tag>
                                                    )}
                                                </div>
                                            </div>
                                        </Dialog>
                                        )}
                                        </CustomCard>
                                );
                                } else {
                                    return (
                                        <CustomCard>
                                        <div style={{display: 'flex', justifyContent: 'center', alignItems: 'center', flexDirection: 'column', height: '100%'}}>
                                            <Button
                                            onClick={() => {
                                                setSelectedDate(dateColumn);
                                                setSelectedEstudiante(rowData);
                                                setVisibleTurnos(true);
                                                listarTurnosEstudiante(rowData, dateColumn);
                                            }}
                                            style={{width: '30px', height: '30px', borderRadius: '15px', backgroundColor: '#bebbbb', display: 'flex', justifyContent: 'center', alignItems: 'center'}}
                                            >
                                            <i className="pi pi-plus" style={{ margin: 0 }} />
                                            </Button>
                                            <p style={{ fontSize: '0.5rem', margin: '4px 0 0 0' }}>Sin asignar</p>
                                        </div>
                                        </CustomCard>
                                    );
                                }
                            }}
                            />
                        );
                        })}
                    </DataTable>
                </div>

                <Dialog header="GESTIONAR TURNO" visible={visibleTurnos} onHide={() => setVisibleTurnos(false)}>
                {selectedDate && selectedEstudiante && (
                    <div>
                        <div>
                            <div style={{ display: 'flex', alignItems: 'center' }}>
                                <p style={{marginLeft:'30px' ,marginRight: '25px', fontSize: '0.8rem' }}><span style={{ fontWeight: 'bold' }}>Estudiante:</span> {selectedEstudiante.nombreCompleto}</p>
                                <p style={{marginLeft:'30px', fontSize: '0.8rem'}}><span style={{ fontWeight: 'bold' }}>Fecha seleccionada:</span>: {selectedDate.toLocaleDateString('es-ES')}</p>
                            </div>
                            <p style={{ marginLeft:'30px', fontSize: '0.8rem', fontWeight: 'bold', color: 'red' }}>Nota: El número máximo de horas asociadas a los turnos es de 12.</p>
                        </div>
                        <div style={{ display: 'flex', flexDirection: 'row', alignItems: 'center', height: '200px', width: '100%'}}>
                            <div style={{ marginLeft:'30px', textAlign: 'center' ,marginRight: '20px'}}>
                                <p style={{ fontWeight: 'bold' }}>
                                    <Badge value="1" style={{ marginRight: '20px' }}></Badge>
                                    Selección de escenario
                                </p>
                                <div style={{ display: 'block' }}>
                                    <p>Nombre del escenario *</p>
                                    <Dropdown 
                                        value={selectedEscenario} 
                                        onChange={(e) => setSelectedEscenario(e.value)} 
                                        options={escenarios.map((escenario) => ({label: escenario.nombreEscenario, value: escenario.idEscenario}))} 
                                        placeholder="Escenario"
                                    />
                                    <p style={{ fontSize: '0.7rem'}}>&nbsp;</p >
                                </div>
                            </div>
                            <Divider layout="vertical" />
                            <div style={{marginLeft:'30px', textAlign: 'center',marginRight: '20px' }}>
                                <p style={{fontWeight: 'bold' }}>
                                    <Badge value="2" style={{ marginRight: '20px' }}></Badge>
                                    Selección de jornada
                                </p>
                                <div style={{ display: 'block' }}>
                                    <p>Tipo de jornada *</p>
                                    <Dropdown 
                                        value={selectedJornada}
                                        onChange={(e) => setSelectedJornada(e.value)} 
                                        options={jornadas.map((jornada) => ({label: jornada.franja +" , "+ jornada.horaInicio+" - "+ jornada.horaFin , value: jornada.idJornada}))} 
                                        placeholder="Jornada"
                                    />
                                    <p style={{ fontSize: '0.7rem'}}>&nbsp;</p >
                                </div>
                            </div>
                            <Divider layout="vertical" />
                            <div style={{marginLeft:'30px', textAlign: 'center', marginRight: '30px' }}>
                                <p style={{fontWeight: 'bold' }}>
                                    <Badge value="3" style={{ marginRight: '20px' }}></Badge>
                                    Selección de etiqueta
                                </p>
                                <div style={{ display: 'block' }}>
                                    <p>Etiqueta *</p>
                                    <Dropdown 
                                       value={selectedEtiqueta} 
                                       onChange={(e) => setSelectedEtiqueta(e.value)} 
                                       options={etiquetasListarPorEscenerio.map((etiqueta) => ({label: etiqueta.nombreEtiqueta , value: etiqueta.idEtiqueta}))} 
                                       placeholder="Etiqueta"
                                    />
                                </div>
                                
                                <div style={{ display: 'block', marginTop: '10px' }}>
                                    <Toast ref={toast} />
                                    <Button label="ASOCIAR" style={{ fontSize: '0.5rem', backgroundColor: 'blue' }} onClick={() => crearTurno(turnoCrear)} disabled={!isFormValidTurno}/>
                                </div>
                            </div>

                        </div>
                        <Divider/>
                        <div style={{ textAlign: 'center', width: '600px', margin: 'auto' }}>
                            <p style={{fontWeight: 'bold' }}>LISTA DE TURNOS CREADOS </p>
                            <Toast ref={toast} />
                            <DataTable value={turnosEstudiante} tableStyle={{ width:'500px' , margin: 'auto'}} bodystyle={{ textAlign: 'center' }}>
                                <Column field="nombreEscenario" header="Escenario"></Column>
                                <Column field="franjaJornada" header="Jornada" body={(rowData) => (
                                <span>{rowData.franjaJornada} ({rowData.horaInicio} - {rowData.horaFin})</span>
                                )}></Column>
                                <Column field="nombreEtiqueta" header="Etiqueta"></Column>
                                
                                <Column header="Eliminar" body={(rowData) => (
                                    <div>
                                        <button style={{ border: 'none', background: 'none' }} onClick={() => handleEliminarTurno(rowData.idTurno)}>
                                            <i className="pi pi-trash"></i>
                                        </button>
                                        <ConfirmDialog visible={confirmDialogVisibleTurnosEliminar} onHide={() => setConfirmDialogVisibleTurnosEliminar(false)} message="¿Estás seguro de que deseas eliminar este turno?" header="Confirmar eliminación" acceptLabel="Aceptar" rejectLabel="Cancelar" icon="pi pi-exclamation-triangle" accept={() => eliminarTurno()} />
                                    </div>
                                )}>
                                    
                                </Column>
                            </DataTable>
                        </div>
                    </div>
                )}
                </Dialog>
            </div>   
            </TabPanel>
            <TabPanel header="Documentos">
                <h1>En construcción</h1>
            </TabPanel>
        </TabView>
    </div>
    );
};