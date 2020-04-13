import React, { Component } from 'react';
import './App.css';
import "bootstrap/dist/css/bootstrap.min.css"

import {Row, Col, Alert} from "react-bootstrap";
import {BrowserRouter as Router, Switch, Route } from "react-router-dom";
import {Container} from '@material-ui/core';

//importar componentes

import SignUp from "./components/SignUp";
import Login from "./components/Login";
import NavigationBar from "./components/NavigationBar";
import Welcome from "./components/Welcome";
import WelcomeUser from "./components/User/WelcomeUser";
import AuthService from './services/AuthService';

class App extends Component{
  render(){
    return(

    <Router>
      <NavigationBar/>
      <Container>
        <Row>
          <Col lg={12} >
            <Switch>
              <Route exact path={"/"}  component={Welcome} />
              <Route exact path="/welcomeUser"  component={WelcomeUser} />
              <Route exact path="/registro"  component={SignUp} />
              <Route exact path="/login"  component={Login} />
            </Switch>
          </Col>
        </Row>
      </Container>
    </Router>
    )
  }
}

export default App;
