import React, { useState, useEffect } from 'react';
import { TabView, TabPanel } from 'primereact/tabview';
import Box from '@mui/material/Box';
import Grid from '@mui/material/Grid';
import { Card } from 'primereact/card';
import { Button } from 'primereact/button';
import { InputText } from "primereact/inputtext";
import PerfilEstudiante from './PerfilEstudiante';

export const GridEstudiantes = () => {

    const [asignaturas, setAsignaturas] = useState([]);
    useEffect(() => {
        const url = 'http://127.0.0.1:8085/asignaturas/'

        fetch(url)
        .then(response => response.json())
        .then(data => setAsignaturas(data))
        .catch(error => console.error(error));
    }, []);

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
                            <InputText placeholder="Ingrese el nombre de un estudiante" style={{fontSize: '0.5rem', width: '200px', height: '30px'}} />
                        </span>
                    </div>
                </div>
                <div style={{ paddingLeft: '10px',flex: 7 }}> 
                    <div className='navegador-estudiantes-registrados' style={{ display: 'flex', justifyContent: 'space-between', alignItems: 'center' }}>
                        <p></p>
                        <p style={{ textAlign: 'center', margin: 0 }}>Estudiantes Registrados</p>
                        <Button label="Eliminar todo" style={{ fontSize: '0.5rem', backgroundColor: 'red'}} />
                    </div>
                    <div className='component-grid' style={{ maxHeight: '450px', overflowY: 'auto' }}>
                        <Box sx={{ flexGrow: 1 }}>
                            <Grid container spacing={{ xs: 2, md: 3 }} columns={{ xs: 4, sm: 8, md: 12 }}>
                                {asignaturas.map((asignatura, index) => {

                                    const footerStyle = {
                                        paddingTop: '2px'
                                    };

                                    const handleGestionarClick = () => {
                                        window.location.href = '/estudiantes';
                                    }
                                
                                    const footer = (
                                        <div style={footerStyle}>
                                            <Button label="Eliminar registro" style={{ fontSize: '0.5rem', backgroundColor: 'red' }}  onClick={handleGestionarClick} />
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
                                            <PerfilEstudiante name="Sandra Martinez" codigo="140619" correo="samart" src="https://primefaces.org/cdn/primereact/images/avatar/amyelsner.png"/>
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