import React, { useState, useEffect } from 'react';
import { InputText } from "primereact/inputtext";
import Box from '@mui/material/Box';
import Grid from '@mui/material/Grid';
import { Card } from 'primereact/card';
import { Button } from 'primereact/button';
import axios from 'axios';


export const GridAsignaturas = ({ toggleMostrarGridAsignaturas, onAsignaturaSeleccionada }) => {

  const [asignaturas, setAsignaturas] = useState([]);
  const [filtroAsignaturas, setFiltroAsignaturas] = useState('');
  
  useEffect(() => {
    const url = 'http://127.0.0.1:8085/1/1/asignaturas/'

    axios.get(url)
        .then(response => setAsignaturas(response.data))
        .catch(error => console.error(error));
}, []);

  const handleFiltroChange = (event) => {
    setFiltroAsignaturas(event.target.value);
  };

  const asignaturasFiltradas = asignaturas.filter(
    (asignatura) =>
      asignatura.nombreAsignatura
        .toLowerCase()
        .includes(filtroAsignaturas.toLowerCase())
  );

  return (
    <div className='component-container-grid'>
        <div className="card flex flex-wrap justify-content-center gap-3">
            <span className="p-input-icon-left">
                <i className="pi pi-search" />
                <InputText placeholder="Ingrese el nombre de la asignatura" className="input-text-custom" value={filtroAsignaturas} onChange={handleFiltroChange} />
            </span>
        </div>
        <div className='component-grid' style={{ maxHeight: '500px', overflowY: 'auto' }}>
          <Box sx={{ flexGrow: 1 }}>
            <Grid container spacing={{ xs: 2, md: 3 }} columns={{ xs: 4, sm: 8, md: 12 }}>
              {asignaturasFiltradas.map((asignatura, index) => {
                const headerStyle = {
                  backgroundColor: '#013383',
                  color: 'white',
                  padding: '1px',
                };
                
                const header = (
                  <div style={headerStyle}>
                    <p style={{ fontSize: '0.8rem' }}>{asignatura.nombreAsignatura}</p>
                    <p style={{ fontSize: '0.6rem' }}>{asignatura.nombrePrograma}</p>
                  </div>
                );

                const footerStyle = {
                  paddingTop: '10px',
                  borderTop: '2px solid #ececec'
                };

                const handleGestionarClick = (asignatura) => {
                  //window.location.href = '/estudiantes';
                  onAsignaturaSeleccionada(asignatura);
                  toggleMostrarGridAsignaturas();
                }
              
                const footer = (
                    <div style={footerStyle}>
                        <Button label="ESTADO DE LA ASIGNATURA" style={{ fontSize: '0.5rem', marginRight: '10px', backgroundColor: 'green'}} />
                        <Button label="GESTIONAR ASIGNATURA" style={{ fontSize: '0.5rem', backgroundColor: 'green' }} onClick={() => handleGestionarClick(asignatura)} />
                   </div>
                );

                const cardStyle = {
                  height: '160px', // ajusta esta altura a tu preferencia
                  width: '320px'
                };

                return (
                  <Grid item xs={2} sm={4} md={4} key={index}>
                    <div className="custom-grid-item">
                      <Card footer={footer} header={header} style={cardStyle}>
                        
                      </Card>
                    </div>
                  </Grid>
                )
              })}
            </Grid>
          </Box>
        </div>
    </div>
  )
}
