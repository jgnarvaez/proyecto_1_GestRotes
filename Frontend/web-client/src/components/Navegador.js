import React from 'react';
import { BreadCrumb } from 'primereact/breadcrumb';
import Perfil from './Perfil';

export const Navegador = () => {
     const items = [{ label: 'Asignaturas' }, { label: 'Prueba' }];

    const home = { icon: 'pi pi-home', url: '/' }

   

    return (
        <div className="nav-container">
            <BreadCrumb model={items} home={home}/>
            <div>
             <Perfil name="Sandra Martinez" profession="Coordinador de asignatura" src="https://primefaces.org/cdn/primereact/images/avatar/amyelsner.png"/>
            </div>
           
        </div>
    )
}
