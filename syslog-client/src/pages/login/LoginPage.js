import React, { Component } from "react";
import { Redirect } from "react-router-dom";
import API from "../../utils/api";
class LoginPage extends Component {
    constructor(props) {
        super(props);
        this.state = {
            username: "",
            password: "",
            message: "",
            showError: false,
            redirect: null,
        };
        this.handleChangeForPassword = this.handleChangeForPassword.bind(this);
        this.handleChangeForUsername = this.handleChangeForUsername.bind(this);
        this.handleSubmit = this.handleSubmit.bind(this);
        this.showError = this.showError.bind(this);
    }

    handleChangeForUsername(event) {
        this.setState({ username: event.target.value });
    }

    handleChangeForPassword(event) {
        this.setState({ password: event.target.value });
    }

    showError(message, show) {
        if (show == undefined) {
            show = true;
        }
        this.setState({
            message: message,
            showError: show,
        });
    }

    handleSubmit(event) {
        event.preventDefault();
        this.showError("", false);
        API.authenticate(this.state.username, this.state.password)
            .then((resp) => {
                if (resp.data.token) {
                    localStorage.setItem("authToken", resp.data.token);
                    localStorage.setItem("role", resp.data.role);
                    if (resp.data.role == "ROLE_ADMIN") {
                        this.setState({ redirect: "/user-management" });
                    }else{
                        this.setState({ redirect: "/dashboard" });
                    }
                } else {
                    localStorage.setItem("authToken", "");
                    localStorage.setItem("role", "");
                    this.showError("Could not login. Please try again");
                }
            })
            .catch((err) => {
                localStorage.setItem("authToken", "");
                localStorage.setItem("role", "");
                let message = "Could not login. Please try again";
                if (err.response && err.response.status == 401) {
                    message = "Invalid Username/Password";
                }
                this.showError(message);
            });
    }

    render() {
        if (this.state.redirect) {
            return <Redirect to={this.state.redirect} />;
        }
        return (
            <div>
                <div className="sidenav">
                    <div className="login-main-text">
                        <h2>
                            Syslog Viewer
                            <br /> Login
                        </h2>
                        <p>Login from here to access.</p>
                    </div>
                </div>
                <div className="main">
                    <div className="col-md-6 col-sm-12">
                        <div className="login-form">
                            {this.state.showError && (
                                <div
                                    className="alert alert-danger"
                                    role="alert"
                                >
                                    {this.state.message}
                                </div>
                            )}
                            <form onSubmit={this.handleSubmit}>
                                <div className="form-group">
                                    <label>User Name</label>
                                    <input
                                        type="text"
                                        required
                                        className="form-control"
                                        value={this.state.username}
                                        onChange={this.handleChangeForUsername}
                                        placeholder="User Name"
                                    />
                                </div>
                                <div className="form-group">
                                    <label>Password</label>
                                    <input
                                        type="password"
                                        required
                                        className="form-control"
                                        value={this.state.password}
                                        onChange={this.handleChangeForPassword}
                                        placeholder="Password"
                                    />
                                </div>
                                <button type="submit" className="btn btn-black">
                                    Login
                                </button>
                                {/* <button type="submit" className="btn btn-secondary">Register</button> */}
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        );
    }
}

export default LoginPage;
