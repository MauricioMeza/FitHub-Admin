import React, { Component } from 'react';
import "bootstrap/dist/css/bootstrap.min.css";

import {Row, Col, Alert} from "react-bootstrap";
import {BrowserRouter as Router, Switch, Route } from "react-router-dom";
import {Container} from '@material-ui/core';

//importar componentes

import SignUp from "./components/SignUp";
import Login from "./components/Login";
import NavigationBar from "./components/NavigationBar";
import Welcome from "./components/Welcome";


import WelcomeAdmin from "./components/Admin/WelcomeAdmin";
import ClassForm from "./components/Admin/ClassForm";
import ClassTypeForm from "./components/Admin/ClassTypeForm";
import PlanForm from "./components/Admin/PlanForm";
import classes from "./sample/classes.json";

import WelcomeUser from "./components/User/WelcomeUser";
import ClassUser from "./components/User/ClassUser";

class App extends Component{

  state = {
    classes: classes
  }

  addClass = (startDate, type) => {
    const newClass = {
      id: this.state.classes.length,
      startDate: startDate,
      type: type
    }
    this.setState({
      classes: [...this.state.classes, newClass]
    })
  }
  
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
              <Route exact path="/welcomeAdmin"  component={WelcomeAdmin} />
              <Route exact path="/registro"  component={SignUp} />
              <Route exact path="/login"  component={Login} />
              <Route exact path="/ClassForm"  component={ClassForm} />
              <Route exact path="/PlanForm"  component={PlanForm} />
              <Route exact path="/ClassTypeForm"  component={ClassTypeForm} />
              <Route exact path="/ClassUser"  component={ClassUser} />
            </Switch>
          </Col>
        </Row>
      </Container>
    </Router>
    )
  }
}

export default App;
