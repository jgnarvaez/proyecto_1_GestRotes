import React from 'react'
import {MenuLateral} from './MenuLateral';
import {Navegador} from './Navegador';

export const Inicio = () => {
    return (
        <div className="App">
            <div className="grid-container">
              <aside className='App-aside'>
                <MenuLateral/>
              </aside>
              <main className='App-main'>
                <Navegador/>
              </main>
            </div>
        </div>
      );
}
