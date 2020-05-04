import React, { Component } from 'react';
import "bootstrap/dist/css/bootstrap.min.css";
import {Row, Col} from "react-bootstrap";
import {BrowserRouter as Router, Switch, Route } from "react-router-dom";
import {Container} from '@material-ui/core';
import { Provider } from "react-redux";
import store from "./reducers/store"

//importar componentes

import SignUp from "./components/SignUp";
import Login from "./components/Login";
import NavigationBar from "./components/NavigationBar";
import Welcome from "./components/Welcome";
import WelcomeUser from "./components/User/WelcomeUser";
import WelcomeAdmin from "./components/Admin/WelcomeAdmin";

class App extends Component{
  render(){
    return(
      <Router>
        <Provider store = {store}>
        <NavigationBar/>
        <Container>
          <Row>
            <Col lg={12} >
              <Switch>
                <Route exact path={"/"} component={Welcome} />
                <Route exact path="/welcomeUser" component={WelcomeUser} />
                <Route exact path="/welcomeAdmin" component={WelcomeAdmin} />
                <Route exact path="/registro" component={SignUp} />
                <Route exact path="/login" component={Login} />
              </Switch>
            </Col>
          </Row>
        </Container>
        </Provider>
      </Router>
    )
  }
}

export default App;
