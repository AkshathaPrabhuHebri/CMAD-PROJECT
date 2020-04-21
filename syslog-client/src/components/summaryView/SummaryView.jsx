import React, { Component } from 'react';
import SummaryCard from '../summaryCard/SummaryCard.jsx';
const SEVERITY=[
    "Emergency",
    "Alert",
    "Critical",
    "Error",
    "Warning",
    "Notice",
    "Informational",
    "Debug"
  ]
  const COLOR=[
    "#e91e63",
    "#d5852e",
    "#9c27b0",
    "#f44336",
    "#ff9800",
    "#2096f3",
    "#607d8b",
    "#8ac34a"
  ]
class SummaryView extends Component {
    constructor(props){
        super(props);
        this.state={
          ...props,
          formatData:[
            {label:"Emergency",number:0,color:"blue",code:0},
            {label:"Alert",number:0,color:"blue",code:1},
            {label:"Critical",number:0,color:"blue",code:2},
            {label:"Error",number:0,color:"blue",code:3},
            {label:"Warning",number:0,color:"blue",code:4},
            {label:"Notice",number:0,color:"blue",code:5},
            {label:"Informational",number:0,color:"blue",code:6},
            {label:"Debug",number:0,color:"blue",code:7},
          ],
          data:[]
        };
        console.log(this.state);
        this.sendRequestAndSetState(this.state.props.startTime,this.state.props.endTime)
    }

    sendRequestAndSetState(startTime,endTime){
        startTime=startTime.toString().replace("T"," ").replace("Z","");
        endTime=endTime.toString().replace("T"," ").replace("Z","");
        let self=this;
        let authToken=localStorage.getItem("authToken");
        fetch("http://54.245.136.98:5100/log/severity/count?startTime="+endTime+"&endTime="+startTime,{headers: {
            'Authorization': 'Bearer '+authToken,
          }}).then((resp) => resp.json()).then((data) =>{
          self.setState({data:data});
        })
      }

      componentWillReceiveProps(nextProps){
        this.setState({...nextProps});
        console.log(nextProps);
        this.sendRequestAndSetState(nextProps.props.startTime,nextProps.props.endTime);
      }

      generateRows(data){
        //   let formatData=this.state.formatData.map(eachData=>{
        //     let stats={
        //         number:eachData.count,
        //         label:row.severity,
        //         color:eachData.color
        //      };
        //     return (
        //         <div className="col" key={row.severity}>
        //             <SummaryCard stats={stats}></SummaryCard>
        //         </div>
        //     );
        //   });
        let rows =data.map(row => {
         let stats={
            number:row.count,
            label:SEVERITY[row.severity],
            color:COLOR[row.severity]
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
                <div className="row">
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