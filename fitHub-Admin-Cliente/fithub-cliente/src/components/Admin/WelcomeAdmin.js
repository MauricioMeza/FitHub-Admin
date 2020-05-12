import React from "react";

import {Jumbotron} from "react-bootstrap";
import AuthService from "../../services/AuthService";
import ClaseService from "../../services/ClaseService";

class WelcomeAdmin extends React.Component{
    constructor(props){
        super(props);

        this.state ={
            currentUser : AuthService.getCurrentUser()
        };
    }

    componentDidMount(){
        ClaseService.validarInstructor()
        .then((response) => {
            console.log(response)
        })
        .catch((error) =>{
            console.log(error)
            alert("Entrando a un lugar sin permiso")
            this.props.history.push("/")
        })  
    }

    render(){
        const {currentUser} = this.state
        return(
            <Jumbotron className = "bg-dark text-white">
            <h1 className="text-center">¡Bienvenido a FitHub {currentUser.User}!</h1>
            <br></br>
            <h2 className="text-center">Esta es la Pagina Principal para Instructores</h2>
            </Jumbotron>
        )
    }
}

export default WelcomeAdmin 