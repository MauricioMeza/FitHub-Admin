import React from "react";

import {Navbar, Nav, Button} from "react-bootstrap";
import {Link} from "react-router-dom";
import AuthService from '../services/AuthService';

class NavigationBar extends React.Component {
    constructor(props){
        super(props);
        this.logOut = this.logOut.bind(this);

        this.state = {
            showUserBoard: AuthService.getCurrentUserRole(),
        }; 
    }

    componentDidMount(){
        // Escoge que barra de navegacion mostrar dependiendo del usuario loggeado
        this.setState({
            showUserBoard: AuthService.getCurrentUserRole()
        });        
    }

    logOut(){
        AuthService.logout()
        this.setState({
            showUserBoard: AuthService.getCurrentUserRole()
        });
    }


    render(){
        const{currentUser, showUserBoard} = this.state
        switch(showUserBoard){
            case "USER":
                return(
                    <Navbar bg="primary" variant="dark">
                        <Link to={"/welcomeUser"} className="navbar-brand">
                            FITHUB-USER
                        </Link>
                        <Nav className="mr-auto">
                        <Link to={"/"} className="navbar-brand">
                            <Button>Mis Clases</Button>
                        </Link>
                        <Link to={"/"} className="navbar-brand">
                            <Button>Mis Planes</Button>
                        </Link>
                        <Link to={"/"} className="navbar-brand">
                            <Button onClick={this.logOut} className="nav-button"> LogOut</Button>
                        </Link>
                        </Nav>
                    </Navbar>
                )
            case "ADMIN":
                return(
                    <Navbar bg="primary" variant="dark">
                        <Link to={"/welcomeAdmin"} className="navbar-brand">
                            FITHUB-ADMIN
                        </Link>
                        <Nav className="mr-auto">
                        <Link to={"/ClassForm"} className="navbar-brand">
                            <Button>Clases</Button>
                        </Link>
                        <Link to={"/"} className="navbar-brand">
                            <Button>Usuarios</Button>
                        </Link>
                        <Link to={"/"} className="navbar-brand">
                            <Button onClick={this.logOut} className="nav-button"> LogOut</Button>
                        </Link>
                        </Nav>
                    </Navbar>
                )
            case "NULL":
                return(
                    <Navbar bg="primary" variant="dark">
                        <Link to={"/"} className="navbar-brand">
                            FITHUB
                        </Link>
                        <Nav className="mr-auto">
                        <Link to={"/registro"} className="nav-link"> Registrarse</Link>
                        <Link to={"/login"} className="nav-link"> Ingresar</Link>
                        </Nav>
                    </Navbar>
                )  

        }
                       
    }
}

export default NavigationBar;