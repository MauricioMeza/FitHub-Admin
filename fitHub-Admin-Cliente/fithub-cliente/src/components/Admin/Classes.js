import React, {Component} from 'react';
import Typography from '@material-ui/core/Typography';
import PropTypes from "prop-types";
import {Inject, ScheduleComponent, Day, Week, WorkWeek, Month, Agenda} from "@syncfusion/ej2-react-schedule";
import Class from './ClassT';
import classData from './ClassData.ts'

//let cd = new classData();

class Classes extends Component{
    constructor(props) {
        super(props)
    }

    render(){
        /*if(this.props.classes.length == 0){
            return (
                <Typography component="h1" variant="body2"> No hay clases registradas</Typography>
            )
        }
        else{
            return (
                this.props.classes.map((clas, i) => <Class clas ={clas} reload={this.props.reload} key={i}/>)
            ) 
        }*/
       
        return(
            <ScheduleComponent currentView='Month' selectedDate={new Date(2017, 5, 5)} 
            eventSettings={{dataSource: classData.remoteData}}> 
                <Inject services = {[Day, Week, WorkWeek, Month, Agenda]}/>
            </ScheduleComponent>
        )
    }
}

Classes.propTypes ={
    classes: PropTypes.array.isRequired,
    reload: PropTypes.func.isRequired
}

export default Classes;