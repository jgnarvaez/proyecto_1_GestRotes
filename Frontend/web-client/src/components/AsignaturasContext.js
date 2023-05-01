import React, { createContext, useState } from 'react';

export const AsignaturasContext = createContext();

export const AsignaturasProvider = ({ children }) => {
  const [asignaturas, setAsignaturas] = useState(null);

  return (
    <AsignaturasContext.Provider value={{ asignaturas, setAsignaturas }}>
      {children}
    </AsignaturasContext.Provider>
  );
};
