import React, { Component } from 'react';
import { connect } from "react-redux";
import {loadUsers} from "../../redux/actions/userActions";

class UserViewer extends Component {
  constructor(props){
    super(props);
    this.state={
      ...props,
      users:[]
    };
    console.log(this.state);
  }

  componentDidMount(){
      this.props.loadUsers();
  }

  componentWillReceiveProps(nextProps){
    this.setState({...nextProps});
    console.log(nextProps);
  }

  generateRows(data){
    let rows =data.map(row => {
      return (
           <tr key={row.uid}>
              <th scope="row">{row.username}</th>
              <td>{row.roles!=null && row.roles.join(", ")}</td>
              <td>{row.devices!=null && row.devices.join(", ")}</td>
            </tr>
      )
    })
    return rows;
  }

  render(){
    console.log(this.state.users);
    return (
      <div>
        <table className="table table-striped table-bordered table-striped table-hover">
          <thead className="thead-dark">
            <tr>
              <th scope="col">Username</th>
              <th scope="col">Roles</th>
              <th scope="col">Devices</th>
            </tr>
          </thead>
          <tbody>
            {this.generateRows(this.state.users)}
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
const mapStateToProps = state => {
    console.log(state);
    return { users: state.users }; 
}
export default connect(mapStateToProps, {loadUsers})(UserViewer);





