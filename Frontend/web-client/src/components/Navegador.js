import React from 'react';
import { BreadCrumb } from 'primereact/breadcrumb';
import Perfil from './Perfil';

export const Navegador = ({ asignatura, opcion }) => {
    
    const items = [];

    if(opcion){
        items.push({ label: opcion, url: '/' });
    }
    
    if (asignatura) { // Si la variable nombreAsignatura tiene un valor, agrega un nuevo elemento al breadcrumb
        items.push({ label: asignatura.nombreAsignatura });
      }

    const home = { icon: 'pi pi-home', url: '/' }

    const handleClick = (event, item) => {
        event.preventDefault(); // Evita la navegación predeterminada
        window.location.href = item.url; // Navega a la URL del item
    };

    return (
        <div className="nav-container">
            <BreadCrumb model={items} home={home} onItemClick={handleClick}/>
            <div>
                <Perfil name="Sandra Martinez" profession="Coordinador de asignatura" src="https://primefaces.org/cdn/primereact/images/avatar/amyelsner.png"/>
            </div>
           
        </div>
    )
}
