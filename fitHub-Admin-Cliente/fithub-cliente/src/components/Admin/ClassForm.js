import React, {Component} from "react";
import PropTypes from 'prop-types';

import "react-datepicker/dist/react-datepicker.css";
import 'bootstrap/dist/css/bootstrap.min.css';
import DatePicker from 'react-datepicker';
//import addDays from 'date-fns/addDays'
 
import CssBaseline from '@material-ui/core/CssBaseline';
import Container from '@material-ui/core/Container';
import Typography from '@material-ui/core/Typography';
import Grid from '@material-ui/core/Grid';
import MenuItem from '@material-ui/core/MenuItem';
import FormControl from '@material-ui/core/FormControl';
import InputLabel from '@material-ui/core/InputLabel';
import FormHelperText from '@material-ui/core/FormHelperText';
import Select from '@material-ui/core/Select';
import Button from '@material-ui/core/Button';

import { withStyles } from '@material-ui/core/styles';
import ClaseService from "../../services/ClaseService";
import Classes from "./Classes";
//import Class from "./ClassT";

import {Inject, ScheduleComponent, Day, Week, Month, ViewsDirective, ViewDirective} from "@syncfusion/ej2-react-schedule";
import { extend } from '@syncfusion/ej2-base';
import classData from './ClassData.ts'


const styles = theme => ({
  paper: {
    marginTop: theme.spacing(8),
    display: 'flex',
    flexDirection: 'column',
    alignItems: 'center',
  },
  form: {
    width: '100%', // Fix IE 11 issue.
    marginTop: theme.spacing(3),
  },
  submit: {
    margin: theme.spacing(3, 0, 2),
  },
  formControl: {
    width: "100%"
  },
  dateControl: {
    width: "100%"
  },
  containerC: {
    padding: theme.spacing(3, 0, 1)
  },
  containerCalendar: {
    padding: theme.spacing(10, 0, 10)
  },
});

class ClassForm extends React.Component{

  constructor (props) {
    super(props)
    this.data = extend([], null, null, true);

    this.state = {
      instructores : [], 
      clases : [],
      clasesBD : [],
      startDate: new Date(),
      type: "",
      instructor: "",
    };

    this.handleChange = this.handleChange.bind(this);
    this.handleDateChange = this.handleDateChange.bind(this);
    this.reloadClases = this.reloadClases.bind(this);
    this.onFormSubmit = this.onFormSubmit.bind(this);    
    
  }

  componentDidMount(){
    ClaseService.getInstNombres()
    .then(response => {
      this.setState({
        instructores : response.data, 
      })
    })
    .catch(error => {
      console.log(error)
    })

    this.setState({
      clases :ClaseService.getClasesNombres(), 
    })

    this.reloadClases()
    
  }

  handleChange(event) {
    event.preventDefault()
    this.setState({
      [event.target.name] : event.target.value
    })
  }
  handleDateChange(date) {
    this.setState({
      startDate : date
    })
  }

  reloadClases(){
    ClaseService.getClases()
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
  }

  onFormSubmit(e) {
    e.preventDefault();
    ClaseService.addClase(this.state.startDate, this.state.type, this.state.instructor)
    .then(response => {
      console.log(response)
      this.reloadClases()
    })
    .catch(error => {
      if(error.response.status == 400){
        alert(error.response.data.errors[0].defaultMessage) 
      }
      console.log(error.response.data)
    })
  }

  onPopupOpen(args) {
    console.log(args)
    if(args.data.Id){
    }else{
      args.cancel = true
    }
  }

  render() {
    const {classes} = this.props;
    const {instructores, clases, clasesBD} = this.state;


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
                Clases Creadas
            </Typography>
            <br></br>
            <Classes classes={clasesBD} reload ={this.reloadClases}></Classes>
          </Grid>
        </Container>
      
        <Container component="main" maxWidth="xs">
          <CssBaseline />
          <div className={classes.paper}>
            <Typography component="h1" variant="h5">
                Agregar clase
            </Typography>
              <form className={classes.form} onSubmit={ this.onFormSubmit }>
                <Grid container 
                  spacing={3} 
                  direction = "column" 
                  display="flex" 
                  alignItems="stretch" 
                  justify="center">

                  <Grid item xs={12} className={classes.dateControl}>
                    <DatePicker
                        selected={ this.state.startDate }
                        onChange={ this.handleDateChange }
                        showTimeSelect
                        timeFormat="HH:mm"
                        timeIntervals={30}
                        timeCaption="Hora"
                        dateFormat="yyyy/MM/dd h:mm aa"
                        name = "startDate"
                      />
                    <FormHelperText>Seleccione Fecha y Hora</FormHelperText>
                  </Grid>

                  <Grid item xs={12}>
                    <FormControl className={classes.formControl}>
                      <InputLabel>Tipo de clase</InputLabel>
                      <Select
                        required
                        name = "type"
                        onChange = {this.handleChange}
                        selected = {this.state.type}
                        value = {this.state.type}
                      >
                        {clases.map((clase, i) =>
                          <MenuItem value={clase} key={i}>{clase}</MenuItem>
                        )}
                        
                      </Select>
                      <FormHelperText>Seleccione un tipo de clase</FormHelperText>
                    </FormControl>
                  </Grid>

                  <Grid item xs={12}>  
                    <FormControl className={classes.formControl}>
                      <InputLabel>Instructor</InputLabel>
                      <Select
                        required
                        name = "instructor"
                        onChange = {this.handleChange}
                        selected = {this.state.instructor}
                        value = {this.state.instructor}
                      >
                        {instructores.map((instructor, i) =>
                          <MenuItem value={instructor} key={i}>{instructor}</MenuItem>
                        )}
                        
                      </Select>
                      <FormHelperText>Seleccione un Instructor</FormHelperText> 
                    </FormControl>                
                  </Grid>

                </Grid>

                <Button
                  className={classes.submit}
                  type="submit"
                  fullWidth
                  variant="contained"
                  color="primary"
                > Añadir clase
                </Button>
              </form>
          </div>
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

ClassForm.propTypes = {
  classes: PropTypes.object.isRequired,
};

export default withStyles(styles)(ClassForm);