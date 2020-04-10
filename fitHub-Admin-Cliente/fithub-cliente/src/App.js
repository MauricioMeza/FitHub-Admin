import React, { Component } from 'react';
import './App.css';
import "bootstrap/dist/css/bootstrap.min.css"

import {Row, Col} from "react-bootstrap";
import {BrowserRouter as Router, Switch, Route } from "react-router-dom";
import {Container} from '@material-ui/core';

//importar componentes

import SignUp from "./components/SignUp";
import Login from "./components/Login";
import NavigationBar from "./components/NavigationBar";
import Welcome from "./components/Welcome";

class App extends Component{

  
  render(){
    return(
    <Router>
      <NavigationBar/>
      <Container>
        <Row>
          <Col lg={12} >
            <Switch>
              <Route path="/welcome" exact component={Welcome} />
              <Route path="/registro" exact component={SignUp} />
              <Route path="/login" exact component={Login} />
            </Switch>
          </Col>
        </Row>
      </Container>
    </Router>
    )
  }
}

export default App;
