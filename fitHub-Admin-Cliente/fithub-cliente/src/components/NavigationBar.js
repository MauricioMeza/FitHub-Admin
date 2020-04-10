import React from "react";

import {Navbar, Nav} from "react-bootstrap";
import {Link} from "react-router-dom";

class NavigationBar extends React.Component {
    render(){
        return(
            <Navbar bg="primary" variant="dark">
                <Link to={"welcome"} className="navbar-brand">
                    FITHUB
                </Link>
                <Nav className="mr-auto">
                <Link to={"registro"} className="nav-link"> Registrarse</Link>
                <Link to={"login"} className="nav-link"> Ingresar</Link>
                </Nav>

            </Navbar>
        )
    }
}

export default NavigationBar;