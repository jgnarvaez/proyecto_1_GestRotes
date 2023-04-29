import React from 'react'
import {MenuLateral} from './MenuLateral';
import {Navegador} from './Navegador';
import { GridEstudiantes } from './GridEstudiantes';

export const Estudiantes = () => {
  return (
    <div className="App">
        <div className="grid-container">
            <aside className='App-aside'>
                <MenuLateral/>
            </aside>
            <main className='App-main'>
                <Navegador/>
                <GridEstudiantes/>
            </main>
        </div>
    </div>
    );
}
