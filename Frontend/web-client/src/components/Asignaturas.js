import React from 'react'
import {MenuLateral} from './MenuLateral';
import {Navegador} from './Navegador';
import { PrimerComponente } from './PrimerComponente';

export const Asignaturas = () => {
    return (
        <div className="App">
            <div className="grid-container">
              <aside className='App-aside'>
                <MenuLateral/>
              </aside>
              <main className='App-main'>
                <Navegador/>
                <PrimerComponente/>
              </main>
            </div>
        </div>
      );
}
