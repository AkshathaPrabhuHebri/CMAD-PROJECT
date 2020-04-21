import React, { Component } from 'react';
import DashboardPage from './pages/dashboard/DashboardPage'
import LoginPage from './pages/login/LoginPage'
import UserManagementPage from './pages/user-management/UserManagementPage'
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
              <Route path="/user-management">
                <UserManagementPage></UserManagementPage>
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
