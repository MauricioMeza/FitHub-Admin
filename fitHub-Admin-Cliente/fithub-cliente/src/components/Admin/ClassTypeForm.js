import React, {Component} from "react";
import PropTypes from 'prop-types';

import "react-datepicker/dist/react-datepicker.css";
import 'bootstrap/dist/css/bootstrap.min.css';
 
import CssBaseline from '@material-ui/core/CssBaseline';
import Container from '@material-ui/core/Container';
import Typography from '@material-ui/core/Typography';
import Grid from '@material-ui/core/Grid';
import TextField from '@material-ui/core/TextField';

import Button from '@material-ui/core/Button';

import {withStyles} from '@material-ui/core/styles';

import ModService from "../../services/ModificarService";
import PlanService from "../../services/PlanService";

import Pricing from "../Pricing";
import ClaseService from "../../services/ClaseService";

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

class PlanForm extends React.Component{

  constructor (props) {
    super(props)

    this.state = {
      nombre: "",
      cupos: 0,
      duracion: 0,
      clases: []
    };

    this.handleChange = this.handleChange.bind(this);
    this.onFormSubmit = this.onFormSubmit.bind(this);    
    
  }

  componentDidMount(){
    this.reloadClasInfo()
  }

  reloadClasInfo(){
    ClaseService.getClasesNombres()
      .then(response => {
        this.setState({
          clases: response.data
        })
      })
  }

  handleChange(event) {
    event.preventDefault()
    this.setState({
      [event.target.name] : event.target.value
    })
  }

  onFormSubmit(e){
    e.preventDefault();
    const sesion = this.state
    ModService.addTipoSesion(sesion.nombre, sesion.cupos, sesion.duracion)
      .then(response => {
        this.reloadClasInfo()
      })
      .catch(error => {
        console.log(error)
      })
  }


  render(){ 
    const {classes} = this.props;
    const {planes} = this.state;

    return(
      <React.Fragment>

        <Container component="main" maxWidth="xs">
          <CssBaseline />
          <div className={classes.paper}>
          <Grid container 
                  spacing={3} 
                  direction = "column" 
                  display="flex" 
                  alignItems="center" 
                  justify="center">
          <Typography component="h1" variant="h5">
                Agregar Nuevo Tipo de Sesion
          </Typography>
          </Grid>

          <form className={classes.form} onSubmit={this.onFormSubmit}>
            <Grid container 
              spacing={3} 
              direction = "column" 
              display="flex" 
              alignItems="center" 
              justify="center">
              
              <Grid item xs={12} >
                <TextField 
                  required
                  name="nombre" 
                  id="plan-type" 
                  label="Nombre Tipo de Clase" 
                  onChange={this.handleChange} 
                  value={this.state.nombre}/>
              </Grid>
              <Grid item xs={12} >
                <TextField 
                  required
                  name="cupos"
                  id="cupos" 
                  label="Cantidad de Cupos"
                  onChange={this.handleChange} 
                  value={this.state.cupos} />
              </Grid>
              <Grid item xs={12} >
                <TextField 
                  required
                  name="duracion"
                  id="duracion" 
                  label="Duracion de Clase (min)"
                  onChange={this.handleChange} 
                  value={this.state.duracion} />
              </Grid>
            </Grid>

            <Button
              className={classes.submit}
              type="submit"
              fullWidth
              variant="contained"
              color="primary"
            > Añadir tipo de sesion
            </Button>
            </form>
          </div>
        </Container>
      </React.Fragment>
    );
  }
}

PlanForm.propTypes = {
  classes: PropTypes.object.isRequired,
};

export default withStyles(styles)(PlanForm);