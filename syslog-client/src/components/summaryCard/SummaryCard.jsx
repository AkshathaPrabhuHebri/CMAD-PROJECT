import React, { Component } from 'react';

class SummaryCard extends Component {
    constructor(props){
        super(props);
        this.state={
            number:props.stats.number,
            label:props.stats.label,
            color:props.stats.color
        }
    }
    componentWillReceiveProps(nextProps){
        this.setState(nextProps.stats)
    }

    render(){
        return (
            <div >
                <div className="sumCard">
                <div className="firstBlock" style={{ 'color':this.state.color }}>{this.state.number}</div>
                    <div className="secondBlock">{this.state.label}</div>
                </div>
            </div>
        )
    }
}
export default SummaryCard;