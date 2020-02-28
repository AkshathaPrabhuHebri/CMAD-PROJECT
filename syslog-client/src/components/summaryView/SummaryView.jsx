import React, { Component } from 'react';
import SummaryCard from '../summaryCard/SummaryCard.jsx';

class SummaryView extends Component {
    constructor(props){
        super(props);
        this.state={
          ...props,
          data:[]
        };
        console.log(this.state);
        this.sendRequestAndSetState(this.state.props.startTime,this.state.props.endTime)
    }

    severity={
        0:"Emergency",
        1:"Alert",
        2:"Critical",
        3:"Error",
        4:"Warning",
        5:"Notice",
        6:"Informational",
        7:"Debug"
    };

    sendRequestAndSetState(startTime,endTime){
        startTime=startTime.toString().replace("T"," ").replace("Z","");
        endTime=endTime.toString().replace("T"," ").replace("Z","");
        let self=this;
        fetch("http://server:8080/log/severity/count?startTime="+endTime+"&endTime="+startTime).then((resp) => resp.json()).then((data) =>{
          self.setState({data:data});
        })
      }

      componentWillReceiveProps(nextProps){
        this.setState({...nextProps});
        console.log(nextProps);
        this.sendRequestAndSetState(nextProps.props.startTime,nextProps.props.endTime);
      }

      generateRows(data){
        let rows =data.map(row => {
         let stats={
            number:row.count,
            label:row.severity,
            color:"blue"
         };
          return (
                <div className="col" key={row.severity}>
                    <SummaryCard stats={stats}></SummaryCard>
                </div>
          )
        })
        return rows;
      }
    stats=
        {
            number:20,
            label:"INFO",
            color:"blue"
        }
    render(){
        return (
            <div className="summaryView">
                <div class="row">
                    {this.generateRows(this.state.data)}
                </div>
                {/* <div className="row">
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
                </div> */}
            </div>
        );
    }
}
export default SummaryView;