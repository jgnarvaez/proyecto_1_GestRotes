import React, { useRef } from 'react';
import { Menu } from 'primereact/menu';
import { Toast } from 'primereact/toast';
import Contacto from './Contacto';
import { Link } from 'react-router-dom';

export const MenuLateral = ({ onOpcionSeleccionadaMenu }) => {
    const toast = useRef(null);

    let items = [
        { label: 'GESROTES', items:[], style: { background:'#f2f2f2' } }, 
        { separator: true },
        {},
        { label: 'GESTIÓN', items: [
            { label: 'Verificar Estudiantes', icon: 'pi pi-users', command: () => onOpcionSeleccionadaMenu('Verificar Estudiantes') },
            { label: 'Verificar Docentes', icon: 'pi pi-user-edit', command: () => onOpcionSeleccionadaMenu('Verificar Docentes') },
            { template: () => (<Link className="p-menuitem-link" onClick={() => onOpcionSeleccionadaMenu('Asignaturas')}><i className="pi pi-book"></i><span>&nbsp;</span><span>Asignaturas</span></Link>)},
        ],  style: { background:'#f2f2f2' } },
        {},
        { label: 'MI PERFIL', items: [
            { label: 'Configuración', icon: 'pi pi-fw pi-cog' },
            { label: 'Salir', icon: 'pi pi-sign-out' }
        ],  style: { background:'#f2f2f2' } },
        {},
        {},
        {},
        {},
        { separator: true },
        
    ];

    return (
        <div className='asider-container'>
            <Toast ref={toast} />
            <Menu model={items} style={{ width: '100%', background: '#f2f2f2', border: 'none' }} />
            <Contacto name='Contactanos' number='+234 92 928 2891' src=''/>
        </div>
    )
};