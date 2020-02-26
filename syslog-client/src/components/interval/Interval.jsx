import React, { Component } from 'react';
import refresh from '../../assets/images/refresh.svg';

class Interval extends Component {
    render(){
        return (
            <div>
                <div className="input-group">
                    <div className="labelForDp">
                        <span className="input-group-text">Logs for Interval:</span>
                    </div>
                    <select className="form-control">
                        <option>Last 1 Hour</option>
                        <option>Last 12 Hours</option>
                        <option>Last 24 Hours</option>
                    </select>
                    <img src={refresh} className="refresh refreshImg" alt="logo" />
                </div>
                <div className="lastUpdated">Last Updated a min ago</div>
            </div>
        )
    }
}

export default Interval;