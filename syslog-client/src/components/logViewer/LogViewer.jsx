import React, { Component } from 'react';

class LogViewer extends Component {
  constructor(props){
    super(props);
    this.state={
      ...props,
      data:[]
    };
    console.log(this.state);
    this.sendRequestAndSetState(this.state.props.startTime,this.state.props.endTime)
  }

  sendRequestAndSetState(startTime,endTime){
    startTime=startTime.toString().replace("T"," ").replace("Z","");
    endTime=endTime.toString().replace("T"," ").replace("Z","");
    let self=this;
    fetch("http://localhost:5100/log?startTime="+endTime+"&endTime="+startTime).then((resp) => resp.json()).then((data) =>{
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
      return (
           <tr key={row.id}>
              <th scope="row">{row.timestamp}</th>
              <td>{row.severity}</td>
              <td>{row.facility}</td>
              <td>{row.message}</td>
            </tr>
      )
    })
    return rows;
  }

  render(){
    console.log(this.state.data);
    return (
      <div>
        <table className="table table-striped">
          <thead>
            <tr>
              <th scope="col">Timestamp</th>
              <th scope="col">Severity</th>
              <th scope="col">Facility</th>
              <th scope="col">Message</th>
            </tr>
          </thead>
          <tbody>
            {this.generateRows(this.state.data)}
          </tbody>
        </table>
        {/* <nav aria-label="...">
          <ul class="pagination">
            <li class="page-item disabled">
              <a class="page-link" href="#" tabindex="-1" aria-disabled="true">Previous</a>
            </li>
            <li class="page-item"><a class="page-link" href="#">1</a></li>
            <li class="page-item active" aria-current="page">
              <a class="page-link" href="#">2 <span class="sr-only">(current)</span></a>
            </li>
            <li class="page-item"><a class="page-link" href="#">3</a></li>
            <li class="page-item">
              <a class="page-link" href="#">Next</a>
            </li>
          </ul>
        </nav> */}
      </div>
      
    );
  }
}
export default LogViewer;





