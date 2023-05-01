import React, { useState } from 'react';
import { MenuLateral } from './MenuLateral';
import { Navegador } from './Navegador';
import { GridAsignaturas } from './GridAsignaturas';
import { GridEstudiantes } from './GridEstudiantes';

export const Asignaturas = () => {
  const [mostrarGridAsignaturas, setMostrarGridAsignaturas] = useState(true);
  const [asignaturaSeleccionada, setAsignaturaSeleccionada] = useState(null);
  const [opcionSeleccionada, setOpcionSeleccionada] = useState(null);

  const toggleMostrarGridAsignaturas = () => {
    setMostrarGridAsignaturas(!mostrarGridAsignaturas);
  };

  const handleAsignaturaSeleccionada = (asignatura) => {
    setAsignaturaSeleccionada(asignatura);
  };

  const handleOpcionSeleccionada = (opcion) => {
    setOpcionSeleccionada(opcion);
  };

  return (
    <div className="App">
      <div className="grid-container">
        <aside className="App-aside">
          <MenuLateral onOpcionSeleccionadaMenu={handleOpcionSeleccionada} />
        </aside>
        <main className="App-main">
          <Navegador asignatura={asignaturaSeleccionada} opcion={opcionSeleccionada} />
          {opcionSeleccionada !== 'Asignaturas' ? (
            <div></div>
          ) : mostrarGridAsignaturas ? (
            <GridAsignaturas
              toggleMostrarGridAsignaturas={toggleMostrarGridAsignaturas}
              onAsignaturaSeleccionada={handleAsignaturaSeleccionada}
            />
          ) : (
            <GridEstudiantes asignatura={asignaturaSeleccionada} />
          )}
        </main>
      </div>
    </div>
  );
};
