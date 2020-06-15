import React, {Component} from 'react';
import Typography from '@material-ui/core/Typography';
import PropTypes from "prop-types";
import Plan from "./PlansT";

class Plans extends Component{
    constructor(props) {
        super(props)
    }

    render(){
        if(this.props.plans.length === 0){
            return (
                <Typography component="h1" variant="body2"> No hay tipos de planes registrados</Typography>
            )
        }
        else{
            return (
                this.props.plans.map((plan, i) => <Plan plan = {plan} reload = {this.props.reload} key={i}/>)
            )
        }
    }
}

Plans.propTypes ={
    plans: PropTypes.array.isRequired,
    reload: PropTypes.func.isRequired
}

export default Plans;