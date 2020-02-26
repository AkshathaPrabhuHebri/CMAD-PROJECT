import React, { Component } from 'react';
import Interval from './interval/Interval.jsx'
import LogViewer from './logViewer/LogViewer.jsx'
import SummaryView from './summaryView/SummaryView.jsx'

class App extends Component {
    render() {
        return (
            <div className="App">
              <div className="topDiv row">
                <div className="col">
                  <h2>Dashboard</h2>
                </div>
                <div className="col"></div>
                <div className="col"></div>
                <div className="col text-right">
                  <Interval></Interval>
                </div>
              </div>
              <div>
                <SummaryView></SummaryView>
              </div>
              <div>
                <LogViewer></LogViewer>
              </div>
            </div>
        );
    }
} 

export default App;
