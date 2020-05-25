import React from "react";
import PropTypes from 'prop-types';

import {Navbar, Nav, Button} from "react-bootstrap";
import {Link} from "react-router-dom";
import AuthService from '../services/AuthService';

import { withStyles } from '@material-ui/core/styles';

const styles = theme => ({
    navBar: {
        marginBottom: theme.spacing(2)
    }
});

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
        const {classes} = this.props;

        switch(showUserBoard){
            case "USER":
                return(
                    <Navbar className={classes.navBar} bg="primary" variant="dark" expand="lg" sticky="top"> 
                        <Link to={"/welcomeUser"} className="navbar-brand">
                            FITHUB-USER
                        </Link>
                        <Navbar.Toggle aria-controls="basic-navbar-nav" />
                        <Navbar.Collapse id="basic-navbar-nav">
                            <Nav className="mr-auto p-2">
                                <Link to={"ClassUser"} className="navbar-brand">
                                    <Button>Mis Clases</Button>
                                </Link>
                                <Link to={"/"} className="navbar-brand">
                                    <Button>Mis Planes</Button>
                                </Link>
                            </Nav>

                            <Nav>
                                <Link to={"/"} className="navbar-brand">
                                    <Button onClick={this.logOut} className="nav-button"> LogOut</Button>
                                </Link>
                            </Nav>
                        </Navbar.Collapse>
                    </Navbar>
                )
            case "ADMIN":
                return(
                    <Navbar className={classes.navBar} bg="primary" variant="dark"  expand="lg" sticky="top">
                        <Link to={"/welcomeAdmin"} className="navbar-brand">
                            FITHUB-ADMIN
                        </Link>
                        <Navbar.Toggle aria-controls="basic-navbar-nav" />
                        <Navbar.Collapse id="basic-navbar-nav">
                            <Nav className="mr-auto p-2">
                                <Link to={"/ClassForm"} className="navbar-brand">
                                    <Button>Clases</Button>
                                </Link>
                                <Link to={"/"} className="navbar-brand">
                                    <Button>Usuarios</Button>
                                </Link>
                            </Nav>
                            <Nav>
                                <Link to={"/"} className="navbar-brand">
                                    <Button onClick={this.logOut} className="nav-button"> LogOut</Button>
                                </Link>
                            </Nav>
                        </Navbar.Collapse>
                    </Navbar>
                )
            case "NULL":
                return(
                    <Navbar className={classes.navBar} bg="primary" variant="dark" expand="lg" sticky="top">
                        <Link to={"/"} className="navbar-brand">
                            FITHUB
                        </Link>
                        <Nav className="mr-auto p-2">
                        <Link to={"/registro"} className="nav-link"> Registrarse</Link>
                        <Link to={"/login"} className="nav-link"> Ingresar</Link>
                        </Nav>
                    </Navbar>
                )  
        }                   
    }
}

NavigationBar.propTypes = {
    classes: PropTypes.object.isRequired,
};

export default withStyles(styles)(NavigationBar);