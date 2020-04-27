import React from "react";

import {Jumbotron} from "react-bootstrap";
import AuthService from "../../services/AuthService";

class Welcome extends React.Component{
    constructor(props){
        super(props);

        this.state ={
            currentUser : AuthService.getCurrentUser()
        };
    }

    componentDidMount(){
        console.log(this.state.currentUser)
    }

    render(){
        const {currentUser} = this.state
        return(
            <Jumbotron className = "bg-dark text-white">
            <h1 className="text-center">¡Bienvenido a FitHub {this.state.currentUser.User}!</h1>
            </Jumbotron>
        )
    }
}

export default Welcome