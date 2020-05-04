import React from "react";
import {Navbar, Nav, Button} from "react-bootstrap";
import {Link} from "react-router-dom";
import AuthService from '../services/AuthService';
import NavigationBarStore from "../reducers/NavigationBarStore";
import { connect } from "react-redux";

class NavigationBar extends React.Component {
    constructor(props){
        super(props);
        this.logOut = this.logOut.bind(this);

        this.state = {
            showUserBoard: 0,
            currentUser: undefined
        }; 
    }

    componentDidMount(){
        // Escoge que barra de navegacion mostrar dependiendo del usuario loggeado
        this.setState(NavigationBarStore())
        /*if(localStorage.getItem('user') == null){
            this.setState({
                currentUser: undefined,
                showUserBoard: 0
                });
        }else{
            const user = AuthService.getCurrentUser()
            if(user.Rol === "USER"){
                this.setState({
                currentUser: user,
                showUserBoard: 1
                });
            }else if(user.Rol === "ADMIN"){
                this.setState({
                    currentUser: user,
                    showUserBoard: 2
                });
            }
        }  */
    }

    logOut(){
        AuthService.logout()
        this.setState({
            showUserBoard: 0
        });
    }

    render(){
        const{showUserBoard} = this.state
        switch(showUserBoard){
            case 1:
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
            case 2:
                return(
                    <Navbar bg="primary" variant="dark">
                        <Link to={"/welcomeAdmin"} className="navbar-brand">
                            FITHUB-ADMIN
                        </Link>
                        <Nav className="mr-auto">
                        <Link to={"/"} className="navbar-brand">
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
            case 0:
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
            default:
        }
                       
    }
}

export default connect(null, null) (NavigationBar);