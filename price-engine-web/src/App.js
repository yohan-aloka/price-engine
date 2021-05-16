import './styles/index.scss';
import { BrowserRouter as Router, Route, Switch } from 'react-router-dom';


import HomePageComponent from './homePage/HomePageComponent';
import PriceListComponent from './priceListPage/PriceListComponent';
import CalculatorPageComlpnent from './calculatorPage/CalculatorPageComponent';
import NavBarComponent from './shared/navBar/NavBarComponent';

function App() {
  return (
    <div className="container-fluid">
      <Router>
        <NavBarComponent />
        <Switch>
          <Route path='/' component={HomePageComponent} exact={true} />
          <Route path='/price-list' component={PriceListComponent} exact={true} />
          <Route path='/calculator' component={CalculatorPageComlpnent} exact={true} />
        </Switch>
      </Router>
    </div>
  );
}

export default App;
