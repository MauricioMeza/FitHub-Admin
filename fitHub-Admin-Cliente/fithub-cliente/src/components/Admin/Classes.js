import React, {Component} from 'react';

import PropTypes from "prop-types";

import Class from './ClassT';

class Classes extends Component{
    constructor(props) {
        super(props)
      }

    render(){
          return (
            this.props.classes.map((clas, i) => <Class clas ={clas} key={i}/>)
        )
    }
}

Classes.propTypes ={
    classes: PropTypes.array.isRequired
}

export default Classes;