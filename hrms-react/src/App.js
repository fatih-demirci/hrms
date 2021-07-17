import './App.css';
import 'semantic-ui-css/semantic.min.css'
import { Container } from 'semantic-ui-react'
import Navi from './layouts/Navi';
import Dashboard from './layouts/Dashboard';

function App() {
  return (
    <div className="App">
        <Navi/>
        
          <Dashboard/>
        
    </div>
  );
}

export default App;
