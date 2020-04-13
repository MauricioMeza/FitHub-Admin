import React from "react";

import {Navbar, Nav, Button} from "react-bootstrap";
import {Link} from "react-router-dom";
import AuthService from '../services/AuthService';

class NavigationBar extends React.Component {
    constructor(props){
        super(props);
        this.logOut = this.logOut.bind(this);

        this.state = {
            showUserBoard: false,
            currentUser: undefined
        }; 
    }

    componentDidMount(){
        const user = AuthService.getCurrentUser()

        if(user){
            this.setState({
            currentUser: user,
            showUserBoard: true
            });
        }
    }

    logOut(){
        AuthService.logout()
    }


    render(){
        const{currentUser, showUserBoard} = this.state

        if(showUserBoard){
            return(
                <Navbar bg="primary" variant="dark">
                    <Link to={"/welcomeUser"} className="navbar-brand">
                        FITHUB
                    </Link>
                    <Nav className="mr-auto">
                    <Button onClick={this.logOut} className="nav-button"> Log Out</Button>
                    </Nav>
                </Navbar>
            )           
        }else{
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