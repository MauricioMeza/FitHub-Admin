import React, {Component} from 'react';
import App from '../App';

import PropTypes from "prop-types";

import Class from './ClassT';

class Classes extends Component{
    render(){
        return this.props.classes.map(clas => <Class clas ={clas} key={clas.id}/>)
    }

}

Classes.propTypes ={
    classes: PropTypes.array.isRequired
}

export default Classes;