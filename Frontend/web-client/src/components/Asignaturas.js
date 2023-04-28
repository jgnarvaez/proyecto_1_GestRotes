import React from 'react'
import {MenuLateral} from './MenuLateral';
import {Navegador} from './Navegador';
import { GridAsignaturas } from './GridAsignaturas';

export const Asignaturas = () => {
    return (
        <div className="App">
            <div className="grid-container">
              <aside className='App-aside'>
                <MenuLateral/>
              </aside>
              <main className='App-main'>
                <Navegador/>
                <GridAsignaturas/>
              </main>
            </div>
        </div>
      );
}
