import React from 'react';

class Dashboard extends React.Component {
   constructor(props) {
      super(props);
      this.state = {
         // searchtext: '',
         sevStats: [],
         logs: []
      }

      
      function generateCurDateTimeString(curDate){
      // generates timestring in the form "2020-02-06 18:50:38" from the given time
         var curTimeString = curDate.toLocaleString();
         var arr = curTimeString.split(",")[0].split("/");
         var new_date_str = arr[2] + "-" + arr[1] + "-" + arr[0];
         return new_date_str + curTimeString.split(",")[1]
      };


      function generatePastDateTimeString(curDate, noOfHours){
      // generates timestring in the form "2020-02-06 18:50:38" from the given time - noofHours
         curDate.setHours(curDate.getHours() - noOfHours)
         var oldTimeString = curDate.toLocaleString();
         console.log("initial string" + oldTimeString)
         var arr = oldTimeString.split(",")[0].split("/");
         var new_date_str = arr[2] + "-" + arr[1] + "-" + arr[0];
         return new_date_str + oldTimeString.split(",")[1]
      };

      this.onDurationChange = (e) => {

      }

      this.curDate = new  Date();
      this.startTime = generateCurDateTimeString(this.curDate);
      this.endTime = generatePastDateTimeString(this.curDate, 24);

      this.onRefresh = (e) => {
 
         fetch('http://localhost:8080/log?startTime=' + this.startTime + '&endTime=' + this.endTime).then(response => {
         response.json().then(o => {
            console.log(o);
            this.setState({
               logs: o
               });
            })
            
         });

         fetch('http://localhost:8080/log/severity/count?startTime=' + this.startTime + '&endTime=' + this.endTime).then(response => {
            response.json().then(o => {
               console.log(o);
               this.setState({
                  sevStats: o
                  });
               })
               
            });
     
      }
   }

   render() {
      return (
         <div>
            <hr />
            <Interval onRefresh={this.onRefresh}> </Interval>
            <hr />
            <StatCard sevStats={this.state.sevStats}> </StatCard>
            <hr />
            <LogSummary logs={this.state.logs}> </LogSummary>
         </div>
      );
   }
}

class Interval extends React.Component {
   render() {
      return (
      <div class="row">
         <div class="col-md-6">
            <span class="label label-default">Dashboard</span>
         </div>
         <div class="col-md-2">
            <span class="label label-default">Logs For Interval</span>
         </div>

         <div class="col-md-3">
            <div class="dropdown">
               <button class="btn btn-secondary dropdown-toggle" type="button" id="dropdownMenuButton"
                data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                  Last 1 hour
               </button>
               <div class="dropdown-menu" aria-labelledby="dropdownMenuButton">
                  <a class="dropdown-item" href="#">Last 2 hours</a>
                  <a class="dropdown-item" href="#">Last 6 hours</a>
                  <a class="dropdown-item" href="#">Last 12 hours</a>
               </div>
            </div>
         </div>
         <div class="col-md-1">
            <button type="button" class="btn btn-primary" onClick={this.props.onRefresh}>  
            <span class="label label-default">
               Refresh <i class="fas fa-sync"></i>
            </span>
            </button>
         </div>
      </div>
      );
   }
}

class StatCard extends React.Component {
   render(){
      return(
         <div>
            {
               this.props.sevStats.map(sevStat => 
               <div class="col-md-2">
               <div class="card">
               <div class="card-body">
               {sevStat[0]} {sevStat[1]}
               </div>
               </div>
               </div>
               )
            }
         </div>
      );

   }

}

class LogSummary extends React.Component{
   render(){
      return(
         <table class="table">
  <thead class="thead-dark">
    <tr>
      <th scope="col">Timestamp</th>
      <th scope="col">Severity</th>
      <th scope="col">Facility</th>
      <th scope="col">Message</th>
    </tr>
  </thead>
  <tbody>
     {
        this.props.logs.map(log => <tr><th scope="row">{log.timestamp}</th> <td>{log.severity}</td>  
        <td>{log.facility}</td> <td>{log.message}</td></tr>)
     }

  </tbody>
</table>

      );
   }
}
export default Dashboard;
