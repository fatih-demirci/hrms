import './App.css';
import NaviForGuest from "./layouts/NaviForGuest.jsx"
import NaviForJobSeeker from "./layouts/NaviForJobSeeker"
import NaviForEmployer from "./layouts/NaviForEmployer"
import NaviForSystemStaff from "./layouts/NaviForSystemStaff"
import 'semantic-ui-css/semantic.min.css'
import AdvertisementList from './pages/AdvertisementList';
import EmployerList from './pages/EmployerList';
import JobSeekerList from './pages/JobSeekerList';
import CvList from './pages/CvList.jsx';
function App() {
  return (
    <div className="App">
        <NaviForGuest/>
        <JobSeekerList/>
    </div>
  );
}

export default App;
