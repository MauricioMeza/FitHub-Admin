import React, { Component } from 'react';
import './App.css';

//importar componentes

import SignUp from "./components/SignUp";
import Login from "./components/Login";

class App extends Component{
  
  render(){
    return <div>
      <SignUp/>
    </div>
  }
}

export default App;
