import React, { Component } from "react";
import { connect } from "react-redux";
import { loadUsers } from "../../redux/actions/userActions";
import API from "../../utils/api";
class AddUserComponent extends Component {
    constructor(props) {
        super(props);
        this.state = {
            users: [],
            message: "",
            showError: false,
            errorClass:"alert-danger",
            devices:[]
        };
        this.handleChangeForPassword = this.handleChangeForPassword.bind(this);
        this.handleChangeForUsername = this.handleChangeForUsername.bind(this);
        this.handleChangeForDevices = this.handleChangeForDevices.bind(this);
        this.handleSubmit = this.handleSubmit.bind(this);
        this.showError = this.showError.bind(this);
    }

    handleChangeForUsername(event) {
        this.setState({ username: event.target.value });
    }

    handleChangeForPassword(event) {
        this.setState({ password: event.target.value });
    }

    handleChangeForDevices(event) {
        this.setState({devices: Array.from(event.target.selectedOptions, (item) => item.value)});
    }

    showError(errorClass,message, show) {
        if (show == undefined) {
            show = true;
        }
        this.setState({
            errorClass:errorClass,
            message: message,
            showError: show,
        });
    }

    handleSubmit(event) {
        event.preventDefault();
        this.showError("alert-danger","", false);
        console.log(this.state)
        API.addUser(this.state.username, this.state.password,this.state.devices)
            .then((resp) => {
                console.log(resp);
                if (resp.status==201) {
                    this.showError("alert-success","User added Successfully");
                    this.props.loadUsers();
                    setTimeout(function(){
                        this.showError("alert-success","",false);
                    }.bind(this),3000)
                } else {
                    this.showError("alert-danger","Could not add user. Please try again");
                }
            })
            .catch((err) => {
                console.log(err.response);
                let message = "Could not add user. Please try again";
                if(err.response.data.message){
                    message=err.response.data.message;
                }
                this.showError("alert-danger",message);
            });
    }

    render() {
        return (
            <div>
                {this.state.showError && (
                    <div className={"alert "+this.state.errorClass} role="alert">
                        {this.state.message}
                    </div>
                )}
                <form onSubmit={this.handleSubmit}>
                    <div className="form-group">
                        <label htmlFor="username">Username</label>
                        <input
                            type="input"
                            className="form-control"
                            id="username"
                            aria-describedby="username"
                            value={this.state.username}
                            onChange={this.handleChangeForUsername}
                            required
                            placeholder="Enter username"
                        />
                        <small id="username" className="form-text text-muted">
                            Enter the username
                        </small>
                    </div>
                    <div className="form-group">
                        <label htmlFor="password">Password</label>
                        <input
                            type="password"
                            className="form-control"
                            id="password"
                            value={this.state.password}
                            onChange={this.handleChangeForPassword}
                            required
                            placeholder="Enter password"
                        />
                    </div>
                    <div className="form-group">
                        <label htmlFor="devices">Select Devices:</label>
                        <select
                            className="form-control"
                            id="devices"
                            name="devices"
                            multiple
                            value={this.state.devices}
                            required
                            onChange={this.handleChangeForDevices}
                        >
                            <option value="device1">device1</option>
                            <option value="device2">device2</option>
                            <option value="device3">device3</option>
                            <option value="device4">device4</option>
                        </select>
                    </div>
                    <button type="submit" className="btn btn-primary">
                        Add User
                    </button>
                </form>
            </div>
        );
    }
}
const mapStateToProps = (state) => {
    return { users: state.users };
};
export default connect(mapStateToProps, { loadUsers })(AddUserComponent);
