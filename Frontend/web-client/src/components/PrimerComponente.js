import React from 'react';
import { InputText } from "primereact/inputtext";
import { experimentalStyled as styled } from '@mui/material/styles';
import Box from '@mui/material/Box';
import Paper from '@mui/material/Paper';
import Grid from '@mui/material/Grid';
import { Button } from 'primereact/button';

export const PrimerComponente = () => {

  const Item = styled(Paper)(({ theme }) => ({
    backgroundColor: theme.palette.mode === 'dark' ? '#1A2027' : '#fff',
    ...theme.typography.body2,
    padding: theme.spacing(7),
    textAlign: 'center',
    color: theme.palette.text.secondary,
  }));

  

  return (
    <div className='component-container'>
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
                  <Item>
                    <div style={{background:'blue' }}>

                    </div>

                    <div className="item-part item-part-1">
                      <p>Part 1</p>
                    </div>
                    <div className="item-part item-part-2">
                      <p>Part 2</p>
                    </div>
                    <div className="item-part item-part-3" >
                      <Button variant="contained" color="primary">ESTADO DE LA ASIGNATURA</Button>
                      <Button variant="contained" color="secondary">GESTIONAR ASIGNATURA</Button>
                    </div>
                  </Item>
                </Grid>
              ))}
            </Grid>
          </Box>
        </div>
    </div>
  )
}
