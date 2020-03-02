import React, { Component } from 'react';
import refresh from '../../assets/images/refresh.svg';

class Interval extends Component {
    constructor(props){
        super(props);
        console.log(props);
        this.state={
            currentDate:new Date()
        }
        //this.change.bind(this);
    }

    change(event){
        this.setState({currentDate:new Date()})
        this.props.setTime(event.target.value);
    }
    render(){
        return (
            <div>
                <div className="input-group">
                    <div className="labelForDp">
                        <span className="input-group-text">Logs for Interval:</span>
                    </div>
                    <select className="form-control" onChange={this.change.bind(this)}>
                        <option value="1">Last 1 Hour</option>
                        <option value="12">Last 12 Hours</option>
                        <option value="24">Last 24 Hours</option>
                    </select>
                    <img src={refresh} className="refresh refreshImg" alt="logo" />
                </div>
        <div className="lastUpdated">{this.state.currentDate.toString()}</div>
            </div>
        )
    }
}

export default Interval;