import React, { Component } from 'react';
import Interval from '../../components/interval/Interval'
import LogViewer from '../../components/logViewer/LogViewer'
import SummaryView from '../../components/summaryView/SummaryView'
import { Router, Route, Link, browserHistory, IndexRoute } from 'react-router'

class DashboardPage extends Component {
  
    constructor(props){
      super(props);
      let startDateTime=new Date();
      let endDateTime=new Date(startDateTime.getTime());
      endDateTime.setHours(endDateTime.getHours()-parseInt(1));
      this.state = {
        startTime:startDateTime.toISOString(),
        endTime:endDateTime.toISOString(),
        hour:"1"
      }
    }

    setTime(hour){
      let startDateTime=new Date();
      let endDateTime=new Date(startDateTime.getTime());
      endDateTime.setHours(endDateTime.getHours()-parseInt(hour));
      this.setState({
        hour:hour,
        startTime:startDateTime.toISOString(),
        endTime:endDateTime.toISOString()
      })
    }

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
                  <Interval setTime={this.setTime.bind(this)}></Interval>
                </div>
              </div>
              <div>
                <SummaryView props={{...this.state}}></SummaryView>
              </div>
              <div>
                <LogViewer props={{...this.state}}></LogViewer>
              </div>
            </div>
        );
    }
} 

export default DashboardPage;