import React, {Component} from 'react';
import Typography from '@material-ui/core/Typography';
import PropTypes from "prop-types";
import ClassType from "./ClassTypesT";

class ClassTypes extends Component{
    constructor(props) {
        super(props)
    }

    render(){
        if(this.props.classTypes.length === 0){
            return (
                <Typography component="h1" variant="body2"> No hay tipos de sesiones registrados</Typography>
            )
        }
        else{
            return (
                this.props.classTypes.map((classType, i) => <ClassType classType = {classType} reload = {this.props.reload} key={i}/>)
            )
        }
    }
}

ClassTypes.propTypes ={
    classTypes: PropTypes.array.isRequired,
    reload: PropTypes.func.isRequired
}

export default ClassTypes;