import React from "react";
import { connect } from "react-redux";
import {Jumbotron} from "react-bootstrap";
import AuthService from "../../services/AuthService";

/*const WelcomeUser = ({user, saveUserSession}) => (
    <Jumbotron className = "bg-dark text-white">
        {
            user = AuthService.getCurrentUser().User
        }
        <h1 className="text-center">¡Bienvenido a FitHub {saveUserSession(user)}!</h1>
        <br></br>
        <h2 className="text-center">Esta es la Pagina Principal para Usuarios</h2>
    </Jumbotron>
)

const mapStateToProps = state => ({
    user: state.user
})

const mapDispatchToProps = dispatch => ({
    saveUserSession(savedUser) {
        dispatch ({
            type: "SAVE_USER_SESSION",
            savedUser
        })
    }
})

export default connect(mapStateToProps, mapDispatchToProps)(WelcomeUser)*/

class WelcomeUser extends React.Component{
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
        //const {currentUser} = this.state
        return(
            <Jumbotron className = "bg-dark text-white">
            <h1 className="text-center">¡Bienvenido a FitHub {this.state.currentUser.User}!</h1> 
            <br></br>
            <h2 className="text-center">Esta es la Pagina Principal para Usuarios</h2>
            </Jumbotron>
        )
    }
}

export default connect(null, null)(WelcomeUser)