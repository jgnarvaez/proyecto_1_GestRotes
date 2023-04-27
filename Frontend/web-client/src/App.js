import './App.css';
import "primereact/resources/themes/bootstrap4-light-blue/theme.css";     
import "primereact/resources/primereact.min.css";    
import 'primeicons/primeicons.css';
import { PrimerComponente } from './components/PrimerComponente';
import { MenuLateral } from './components/MenuLateral';
import { Navegador } from './components/Navegador';


function App() {
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

export default App;
