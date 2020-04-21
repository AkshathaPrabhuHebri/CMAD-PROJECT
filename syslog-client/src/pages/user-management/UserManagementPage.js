import React, { Component } from 'react';
import AddUserComponent from '../../components/addUser/AddUserComponent';
import { Redirect } from "react-router-dom";
import UserViewer from '../../components/userViewer/UserViewer';
import Logout from "../../components/logout/LogoutComponent"

class UserManagementPage extends Component {
    constructor(props){
        super(props);
        this.state={
            redirect: null
        }
    }

    componentWillMount(){
        let role=localStorage.getItem("role");
        let token=localStorage.getItem("authToken");
        if(role!="ROLE_ADMIN" || token==undefined || token ==null || token == ""){
            this.setState({ redirect: "/login" });
        }
    }

    render(){
        if (this.state.redirect) {
            return <Redirect to={this.state.redirect} />;
        }
        return (
            <div className="App">
              <div className="topDiv row">
                <div className="col">
                  <h2>User Management</h2>
                </div>
              </div>
              <div className="addUser">
                <AddUserComponent></AddUserComponent>
              </div>
              <br></br>
              <div>
                  <UserViewer></UserViewer>
              </div>
              <Logout></Logout>
            </div>
        )
    }
}

export default UserManagementPage;