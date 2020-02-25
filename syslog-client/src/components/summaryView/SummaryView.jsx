import React, { Component } from 'react';
import SummaryCard from '../summaryCard/SummaryCard.jsx';

class SummaryView extends Component {
    stats=
        {
            number:20,
            label:"INFO",
            color:"blue"
        }
    render(){
        return (
            <div className="summaryView">
                <div className="row">
                    <div className="col">
                        <SummaryCard stats={this.stats}></SummaryCard>
                    </div>
                    <div className="col">
                        <SummaryCard stats={this.stats}></SummaryCard>
                    </div>
                    <div className="col">
                        <SummaryCard stats={this.stats}></SummaryCard>
                    </div>
                    <div className="col">
                        <SummaryCard stats={this.stats}></SummaryCard>
                    </div>
                </div>
                <div className="row">
                    <div className="col">
                        <SummaryCard stats={this.stats}></SummaryCard>
                    </div>
                    <div className="col">
                        <SummaryCard stats={this.stats}></SummaryCard>
                    </div>
                    <div className="col">
                        <SummaryCard stats={this.stats}></SummaryCard>
                    </div>
                    <div className="col">
                        <SummaryCard stats={this.stats}></SummaryCard>
                    </div>
                </div>
            </div>
        );
    }
}
export default SummaryView;