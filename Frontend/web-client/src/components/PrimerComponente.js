import React from 'react';
import { InputText } from "primereact/inputtext";
import Box from '@mui/material/Box';
import Grid from '@mui/material/Grid';
import { Card } from 'primereact/card';
import { Button } from 'primereact/button';
import fetch from 'isomorphic-fetch';


export const PrimerComponente = () => {

  const url = 'http://127.0.0.1:8085/asignaturas/'

  fetch(url)
  .then(response => response.json())
  .then(data => console.log(data))
  .catch(error => console.error(error));




  return (
    <div className='component-container-grid'>
        <div className="card flex flex-wrap justify-content-center gap-3">
            <span className="p-input-icon-left">
                <i className="pi pi-search" />
                <InputText placeholder="Ingrese el nombre de la asignatura" className="input-text-custom" />
            </span>
        </div>
        <div className='component-grid' style={{ maxHeight: '500px', overflowY: 'auto' }}>
           <Box sx={{ flexGrow: 1 }}>
            <Grid container spacing={{ xs: 2, md: 3 }} columns={{ xs: 4, sm: 8, md: 12 }}>
              {Array.from(Array(12)).map((_, index) => (
                <Grid item xs={2} sm={4} md={4} key={index}>
                   <div className="card">
                    <Card >
                      <div className="flex-container">
                        <div className="flex-item top">
                          <p>Asignatura</p>
                          
                          
                        </div>
                        <div className="flex-item center">
                          <br></br>
                          <br></br>
                        </div>
                        <div className="flex-item bottom">
                        <Button label="Estado" severity="success" />
                        <Button label="Gestion" severity="success" />
                        </div>
                      </div>
                    </Card>
                  </div>
                </Grid>
              ))}
            </Grid>
          </Box>
        </div>
    </div>
  )
}
