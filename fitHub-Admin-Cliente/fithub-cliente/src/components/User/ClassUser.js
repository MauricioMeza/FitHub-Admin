import React from "react";
import PropTypes from 'prop-types';


import 'bootstrap/dist/css/bootstrap.min.css';
 
import Container from '@material-ui/core/Container';
import Typography from '@material-ui/core/Typography';
import Grid from '@material-ui/core/Grid';

import { withStyles } from '@material-ui/core/styles';
import ClaseService from "../../services/ClaseService";
import Classes from "../Admin/Classes";

import {Inject, ScheduleComponent, Day, Week, Month, ViewsDirective, ViewDirective} from "@syncfusion/ej2-react-schedule";
import { extend } from '@syncfusion/ej2-base';
import classData from '../Admin/ClassData'


const styles = theme => ({
  paper: {
    marginTop: theme.spacing(8),
    display: 'flex',
    flexDirection: 'column',
    alignItems: 'center',
  },
  containerC: {
    padding: theme.spacing(3, 0, 1)
  },
  containerCalendar: {
    padding: theme.spacing(10, 0, 10)
  },
});

class ClassUser extends React.Component{

  constructor (props) {
    super(props)
    this.data = extend([], null, null, true);

    this.state = {
      clasesBD : [],
      clasesHorario : [],
      selectedClass: ""
    };

    this.reloadClases = this.reloadClases.bind(this);
  }

  componentDidMount(){
    this.reloadClases() 
  }

  reloadClases(){
    ClaseService.getClasesUser()
    .then(response => {
      console.log(response)
      var clas = response.data.map((c, i) => {
        var fecha = new Date(c.fecha)
        var months = ["Ene/", "Feb/", "Mar/", "Abr/", "May/", "Jun/", "Jul/", "Ago/", "Sep/", "Oct/", "Nov/", "Dec/"];
        var horaMin = fecha.getMinutes()
        if(horaMin == 0) horaMin = "00"
        return {
          "fecha" : " " + months[fecha.getMonth()] + fecha.getDate() + " ",
          "hora" : " " + fecha.getHours() + ":" + horaMin + " ",
          "tipo" : " " + c.sesion + " ",
          "instructor": " " + c.instructor + " ",
          "id": c.id
        }
      })
      this.setState({
        clasesBD : clas 
      })
    })
    .catch(error => {
      console.log(error)
    })
    
    ClaseService.getClases()
  }

  onPopupOpen(args) {
    console.log(args)
    if(args.data.Id){
      if(args.type == "DeleteAlert"){
        args.cancel = true
        /*
        ClaseService.cancelSesion(args.data.Id)
        .then(response => {
          console.log(response)
          this.reloadClases();  
        })
        */
      }
      if(args.type == "Editor"){
        args.cancel = true
        /*
        ClaseService.reserveSesion(args.data.Id)
        .then(response => {
          console.log(response)
          this.reloadClases();  
        })
        */
      }
    }else{
      args.cancel = true
    }
  }

  footer(props) {
    this.render()
    return (
      <div>
        {props.Description}
      </div>
    );
  }

  render() {
    const {classes} = this.props;
    const {clasesBD} = this.state;


    return(
      <React.Fragment>
        <Container className={classes.containerC} component="main" maxWidth="xl">
          <Grid container 
                  spacing={3} 
                  direction = "column" 
                  display="flex" 
                  alignItems="center" 
                  justify="center">
            <Typography component="h1" variant="h5">
                Clases Reservadas
            </Typography>
            <br></br>
            <Classes classes={clasesBD} reload ={this.reloadClases}></Classes>
          </Grid>
        </Container>
      

        <Container className={classes.containerCalendar} component="main" maxWidth="md">
          <Grid container 
                  spacing={3} 
                  direction = "column" 
                  display="flex" 
                  alignItems="center" 
                  justify="center">
            <Typography component="h1" variant="h5">
                Horario de Clases
            </Typography>
            <br></br>
            <ScheduleComponent currentView='Week' eventSettings={classData.localData} popupOpen={this.onPopupOpen.bind(this)}
            startHour='05:00' endHour='22:00' > 
              <ViewsDirective>
                <ViewDirective option='Day'/>
                <ViewDirective option='Week'/>
                <ViewDirective option='Month'/>
              </ViewsDirective>
              <Inject services = {[Day, Week, Month]}/>
            </ScheduleComponent>
          </Grid>
        </Container>

      </React.Fragment>
    );
  }
}

ClassUser.propTypes = {
  classes: PropTypes.object.isRequired,
};

export default withStyles(styles)(ClassUser);