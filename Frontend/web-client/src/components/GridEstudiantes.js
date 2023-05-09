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
import axios from 'axios';

export const GridEstudiantes = ({ asignatura }) => {
    
    const toast = useRef(null);
    // CAMBIO
    const [searchText, setSearchText] = useState('');
    const [timer, setTimer] = useState(null);

    // ESTUDIANTES
    const [estudiantesBusqueda, setEstudiantesBusqueda] = useState([]);
    const [estudiantes, setEstudiantes] = useState([]);
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

    //CREAR ETIQUETAS
    const [valueCrearEtiqueta, setValueCrearEtiqueta] = useState('');
    const [etiquetasListarCreadas, setEtiquetasListarCreadas] = useState([]);
    const [etiquetasListarAsociadas, setEtiquetasListarAsociadas] = useState([]);
    const [escenarios, setEscenarios] = useState([]);
    const [servicios, setServicios] = useState([]);
    

    //SELECCION ESCENARIO
    const [selectedEscenario, setSelectedEscenario] = useState(null);
    const [selectedEtiqueta, setSelectedEtiqueta] = useState(null);
    const [selectedServicio, setSelectedServicio] = useState(null);

    //ESTADO BOTONES
    const [botonCrearEtiquetas, setBotonCrearEtiquetas] = useState(true);
    const [botonAsociarEtiquetas, setBotonAsociarEtiquetas] = useState(false);

    //ELIMINAR ETIQUETAS
    const [etiquetaIdEliminar, setEtiquetaIdEliminar] = useState([]);
    const [confirmDialogVisibleEtiquetas, setConfirmDialogVisibleEtiquetas] = useState(false);
    const [etiquetaIdEliminarAsociado, setEtiquetaIdEliminarAsociado] = useState([]);
    const [confirmDialogVisibleEtiquetasAsociado, setConfirmDialogVisibleEtiquetasAsociado] = useState(false);

    // Validar si ambos campos tienen información
    const isFormValid = valueCrearEtiqueta.trim() !== '' && selectedEscenario !== null;
    const isFormValidAsociado = selectedEtiqueta != null && selectedServicio != null;

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

    const showSuccessRegistrarEstudiante = () => {
        toast.current.show({severity:'success', summary: 'Registrado!', detail:'El estudiante se registro exitosamente.', life: 8000});
    }

    const showSuccessCrearEtiqueta = () => {
        toast.current.show({severity:'success', summary: 'Creada!', detail:'Se ha creado una nueva etiqueta.', life: 8000});
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

    const showErrorRegistrarEstudiante = () => {
        toast.current.show({severity:'error', summary: 'Error', detail:'Error al intentar registrar el estudiante.', life: 8000});
    }

    const showErrorCrearEtiqueta = (response) => {
        toast.current.show({severity:'error', summary: 'Error', detail:'Error al intentar crear la etiqueta: \n' + response, life: 8000});
    }

    const showErrorEtiquetaAsociado = () => {
        toast.current.show({severity:'error', summary: 'Error', detail:'Error al intentar asociar el servicio a la etiqueta.', life: 8000});
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
    

    /*/Listar etiqueta especifica TODO
    //*const listarEtiquetaEspecifica= () => {
        const url = `http://127.0.0.1:8085/etiquetas/escenarios/4`
    }/*/

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
            showErrorCrearEtiqueta(error.response.data);
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

    //Eliminar etiqueta
    const eliminarEtiqueta = async () => {
        //const url = `http://127.0.0.1:8085/etiquetas/2`
        try {
            const response = await fetch(`http://127.0.0.1:8085/etiquetas/${etiquetaIdEliminar}`, {
            method: 'DELETE'
            });
        
            if (response.ok) {
                console.log(`Etiqueta con ID ${etiquetaIdEliminar} eliminada.`);
                listarEtiquetasCreadas(); //actualiza etiquetas
                showInfoEliminarEtiquetaCreada();
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
                    <Button label="Gestión estudiantes" style={{ fontSize: '0.8rem', backgroundColor: 'blue', marginRight: '5px' }} />
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
                                        options={etiquetasListarCreadas.map((etiqueta) => ({label: etiqueta.nombreEtiqueta, value: etiqueta.idEtiqueta}))} 
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
                    
                    
                    <Button label="Alimentación" style={{ fontSize: '0.8rem', backgroundColor: 'blue', marginRight: '15px' }} />
                    <span className="p-input-icon-left" style={{ marginRight: '15px' }}>
                        <i className="pi pi-search" />
                        <InputText placeholder="Buscar estudiante" style={{fontSize: '0.8rem', width: '180px', height: '30px'}} value={searchText} onChange={(e) => {setSearchText(e.target.value); clearTimeout(timer); setTimer(setTimeout(() => {buscarEstudiantes(e.target.value);}, 500));}}/>
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
                    <Button label="Validación de turnos" style={{ fontSize: '0.8rem', backgroundColor: 'red', marginRight: '5px' }} />
                </div>

                <DataTable value={estudiantes} tableStyle={{ minWidth: '50rem' }}>
                    <Column header={<div><div>Fecha</div><div>Estudiante</div></div>}
                        body={(rowData) => (
                            <div style={{ display: 'flex', justifyContent: 'center', alignItems: 'center' }}>
                                <div style={{ display: 'flex', flexDirection: 'column', alignItems: 'center' }}>
                                    <PerfilEstudiante src="https://primefaces.org/cdn/primereact/images/avatar/amyelsner.png" style={{marginBottom: '0.5rem'}} />
                                    <p style={{ fontSize: '0.6rem', margin: 0, fontWeight: 'bold' }}>{rowData.nombreCompleto}</p>
                                </div>
                            </div>
                        )}
                    />

                    {[0, 1, 2, 3, 4, 5, 6].map((dayOfWeek) => {
                    const daysToAdd = dayOfWeek - fechaActual.getDay();
                    const dateColumn = new Date(year, month, fechaActual.getDate() + daysToAdd);

                    return (
                        <Column header={dateColumn.toLocaleDateString("es-ES", { weekday: "short" }) + " " + dateColumn.getDate()} key={dayOfWeek}
                        body={() => 
                            <Card style={{ display: "flex", flexDirection: "column",height: '100px', width: '100px' }}>
                                <div style={{ display: "flex", justifyContent: "center", alignItems: "center", flexDirection: "column" }}>
                                    <Button style={{ width: "30px", height: "30px", borderRadius: "15px", backgroundColor: '#bebbbb', display: "flex", justifyContent: "center", alignItems: "center" }}>
                                        <i className="pi pi-plus" style={{ margin: 0 }} /> {/*El button es para hacer el OnClick de eliminar*/}
                                    </Button>
                                    <p style={{ fontSize: '0.5rem', margin: '4px 0 0 0' }}>Sin asignar</p>
                                </div>
                            </Card>
                            }
                        />
                    );
                    })}
                </DataTable>
            </div>   
            </TabPanel>
            <TabPanel header="Documentos">
                <h1>En construcción</h1>
            </TabPanel>
        </TabView>
    </div>
    );
};