import React from "react";

import {Jumbotron} from "react-bootstrap";
import AuthService from "../services/AuthService";

class Welcome extends React.Component{
    constructor(props){
        super(props)
    }

    componentDidMount(){
        //Revisa si ya existe un usuario actualmente logeado
        //  - Si el usuario es de tipo USER redirige a WelcomeUser
        //  - Si el usuario es de tipo ADMIN redirige a WelcomeAdmin
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