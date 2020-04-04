import React, { Component } from 'react';
import DashboardPage from './pages/dashboard/DashboardPage'
import LoginPage from './pages/login/LoginPage'
import {
  BrowserRouter as Router,
  Switch,
  Route
} from "react-router-dom";


import { createBrowserHistory } from 'history'

const newHistory = createBrowserHistory();
class App extends Component {

    render() {
        return (
          <Router>
          <div>
            <Switch>
              <Route path="/dashboard">
                <DashboardPage />
              </Route>
              <Route path="/">
                <LoginPage />
              </Route>
            </Switch>
          </div>
        </Router>
        );
    }
} 

export default App;
