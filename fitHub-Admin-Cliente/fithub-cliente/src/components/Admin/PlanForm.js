import React, {Component} from "react";
import PropTypes from 'prop-types';

import "react-datepicker/dist/react-datepicker.css";
import 'bootstrap/dist/css/bootstrap.min.css';
import DatePicker from 'react-datepicker';
 
import CssBaseline from '@material-ui/core/CssBaseline';
import Container from '@material-ui/core/Container';
import Typography from '@material-ui/core/Typography';
import Grid from '@material-ui/core/Grid';
import TextField from '@material-ui/core/TextField';
import MenuItem from '@material-ui/core/MenuItem';
import FormControl from '@material-ui/core/FormControl';
import InputLabel from '@material-ui/core/InputLabel';
import FormHelperText from '@material-ui/core/FormHelperText';
import Select from '@material-ui/core/Select';
import Button from '@material-ui/core/Button';

import {withStyles} from '@material-ui/core/styles';

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
    };

    this.handleChange = this.handleChange.bind(this);
    this.onFormSubmit = this.onFormSubmit.bind(this);    
    
    }

  componentDidMount(){
  }

  handleChange(event) {
    event.preventDefault()
    this.setState({
      [event.target.name] : event.target.value
    })
  }

  onFormSubmit(e){
    e.preventDefault();
  }


  render(){
    
    const {classes} = this.props;
    const {instructores, clases} = this.state;

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
                Tipos de Planes Creados
            </Typography>
            <br></br>
            <div>Inserte sesiones aquí</div>
          </Grid>
        </Container>

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
                Agregar tipo de sesión
          </Typography>
          </Grid>

          <form>
          <Grid item xs={12} >
                    <br></br>
          </Grid>
          <Grid container 
                  spacing={3} 
                  direction = "column" 
                  display="flex" 
                  alignItems="center" 
                  justify="center">
                  
                  <Grid item xs={12} >
                    <TextField id="plan-type" label="Nombre del tipo de plan" />
                  </Grid>
                  <Grid item xs={12} >
                    <TextField id="num-days" label="Cantidad de días" />
                  </Grid>
                  <Grid item xs={12} >
                    <TextField id="num-sessions" label="Cantidad de sesiones" />
                  </Grid>
                  <Grid item xs={12} >
                    <TextField id="num-sessions" label="Precio" />
                  </Grid>

                </Grid>

                <Button
                  className={classes.submit}
                  type="submit"
                  fullWidth
                  variant="contained"
                  color="primary"
                > Añadir tipo de plan
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