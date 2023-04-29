import './App.css';
import "primereact/resources/themes/bootstrap4-light-blue/theme.css";     
import "primereact/resources/primereact.min.css";    
import 'primeicons/primeicons.css';
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import { Asignaturas } from './components/Asignaturas';
import { Inicio } from './components/Inicio';
import { Estudiantes } from './components/Estudiantes';

function App() {
  return (
    <Router>
      <Routes>
        <Route path="/" element={<Inicio />} />
        <Route path="/asignaturas" element={<Asignaturas />} />
        <Route path="/estudiantes" element={<Estudiantes />} />
      </Routes>
    </Router>
      

  );
}

export default App;
