import React from "react";

import {Jumbotron} from "react-bootstrap";

class Welcome extends React.Component{
    render(){
        return(
            <Jumbotron className = "bg-dark text-white">
            <h1 class="text-center">¡Bienvenidos a FitHub!</h1>
            </Jumbotron>
        )
    }
}

export default Welcome