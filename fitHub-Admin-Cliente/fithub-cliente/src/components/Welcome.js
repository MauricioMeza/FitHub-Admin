import React from "react";

import {Jumbotron} from "react-bootstrap";
import AuthService from "../services/AuthService";

class Welcome extends React.Component{
    constructor(props){
        super(props)
    }

    componentDidMount(){
        const userRole = AuthService.getCurrentUserRole()

        if(userRole == "USER"){
            this.props.history.push('/welcomeUser')
        }else if(userRole == "ADMIN"){
            this.props.history.push('/welcomeAdmin')
        }
    }      
    

    render(){
        return(
            <Jumbotron className = "bg-dark text-white">
            <h1 className="text-center">¡Bienvenidos a FitHub!</h1>
            </Jumbotron>
        )
    }
}

export default Welcome