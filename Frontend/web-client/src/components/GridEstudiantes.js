import React, { useState, useEffect, useCallback } from 'react';
import { TabView, TabPanel } from 'primereact/tabview';
import Box from '@mui/material/Box';
import Grid from '@mui/material/Grid';
import { Card } from 'primereact/card';
import { Button } from 'primereact/button';
import { InputText } from "primereact/inputtext";
import PerfilEstudiante from './PerfilEstudiante';
import { ConfirmDialog } from 'primereact/confirmdialog';

export const GridEstudiantes = ({ asignatura }) => {

    // CAMBIO
    const [searchText, setSearchText] = useState('');
    const [estudiantesBusqueda, setEstudiantesBusqueda] = useState([]);
    const [timer, setTimer] = useState(null);
    // CAMBIO
    const [estudiantes, setEstudiantes] = useState([]);
    const [confirmDialogVisible, setConfirmDialogVisible] = useState(false);
    const [estudianteIdEliminar, setEstudianteIdEliminar] = useState([]);
    const [confirmDialogVisibleTodos, setConfirmDialogVisibleTodos] = useState(false);

    const fetchEstudiantes = useCallback(() => {
        const url = `http://127.0.0.1:8085/1/1/asignaturas/${asignatura.idAsignatura}/estudiantes`;
      
        fetch(url)
          .then(response => response.json())
          .then(data => setEstudiantes(data))
          .catch(error => console.error(error));
      }, [asignatura.idAsignatura]);
      
      useEffect(() => {
        fetchEstudiantes();
      }, [fetchEstudiantes]);

    // CAMBIO
    const buscarEstudiantes = (val) => {
        if (val.length > 0) {
            const url = `http://127.0.0.1:8085/1/1/asignaturas/${asignatura.idAsignatura}/estudiantes/${val}`;
            fetch(url)
            .then(response => response.json())
            .then(data => setEstudiantesBusqueda(data))
            .catch(error => console.error(error));
        }else{
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
        fetchEstudiantes(); //actualiza estudiantes
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
        fetchEstudiantes();
        } else {
        console.log(`No se pudo eliminar a los estudiantes.`);
        }
    } catch (error) {
        console.error(error);
    }
    setConfirmDialogVisibleTodos(false);
    };

    // CAMBIO

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

                                    const handleGestionarClickRegistrar = () => {
                                        
                                        //window.location.href = '/estudiantes';
                                    }
                                
                                    const footer = (
                                        <div style={footerStyle}>
                                            <Button label="Registrar" style={{ fontSize: '0.5rem', backgroundColor: 'blue' }}  onClick={handleGestionarClickRegistrar} />
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
                <p className="m-0">
                    Sed ut perspiciatis unde omnis iste natus error sit voluptatem accusantium doloremque laudantium, totam rem aperiam, 
                    eaque ipsa quae ab illo inventore veritatis et quasi architecto beatae vitae dicta sunt explicabo. Nemo
                    enim ipsam voluptatem quia voluptas sit aspernatur aut odit aut fugit, sed quia consequuntur magni dolores eos qui 
                    ratione voluptatem sequi nesciunt. Consectetur, adipisci velit, sed quia non numquam eius modi.
                </p>
            </TabPanel>
            <TabPanel header="Rotes">
                <p className="m-0">
                    At vero eos et accusamus et iusto odio dignissimos ducimus qui blanditiis praesentium voluptatum deleniti atque corrupti 
                    quos dolores et quas molestias excepturi sint occaecati cupiditate non provident, similique sunt in
                    culpa qui officia deserunt mollitia animi, id est laborum et dolorum fuga. Et harum quidem rerum facilis est et expedita distinctio. 
                    Nam libero tempore, cum soluta nobis est eligendi optio cumque nihil impedit quo minus.
                </p>
            </TabPanel>
            <TabPanel header="Turnos">
                <p className="m-0">
                    At vero eos et accusamus et iusto odio dignissimos ducimus qui blanditiis praesentium voluptatum deleniti atque corrupti 
                    quos dolores et quas molestias excepturi sint occaecati cupiditate non provident, similique sunt in
                    culpa qui officia deserunt mollitia animi, id est laborum et dolorum fuga. Et harum quidem rerum facilis est et expedita distinctio. 
                    Nam libero tempore, cum soluta nobis est eligendi optio cumque nihil impedit quo minus.
                </p>
            </TabPanel>
            <TabPanel header="Documentos">
                <p className="m-0">
                    At vero eos et accusamus et iusto odio dignissimos ducimus qui blanditiis praesentium voluptatum deleniti atque corrupti 
                    quos dolores et quas molestias excepturi sint occaecati cupiditate non provident, similique sunt in
                    culpa qui officia deserunt mollitia animi, id est laborum et dolorum fuga. Et harum quidem rerum facilis est et expedita distinctio. 
                    Nam libero tempore, cum soluta nobis est eligendi optio cumque nihil impedit quo minus.
                </p>
            </TabPanel>
        </TabView>
    </div>
    );
};