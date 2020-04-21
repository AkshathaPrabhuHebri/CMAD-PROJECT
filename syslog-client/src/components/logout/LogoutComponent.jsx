import React, { Component } from 'react';
import { Redirect } from "react-router-dom";

class Logout extends Component {
    constructor(props){
        super(props);
        this.state={
            redirect: null
        }
        this.logout = this.logout.bind(this);
    }

    logout(){
        localStorage.setItem("authToken","");
        localStorage.setItem("role","");
        this.setState({ redirect: "/login" });

    }
    render(){
        if (this.state.redirect) {
            return <Redirect to={this.state.redirect} />;
        }
        return (
            <button className="logoutButton" onClick={this.logout}>Logout</button>
        )        
    }
}

export default Logout;