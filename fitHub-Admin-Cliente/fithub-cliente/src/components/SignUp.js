import React from 'react';
import PropTypes from 'prop-types';
import {Link} from "react-router-dom";

import Button from '@material-ui/core/Button';
import CssBaseline from '@material-ui/core/CssBaseline';
import TextField from '@material-ui/core/TextField';
import FormControlLabel from '@material-ui/core/FormControlLabel';
import Checkbox from '@material-ui/core/Checkbox';
import Grid from '@material-ui/core/Grid';
import Box from '@material-ui/core/Box';
import Typography from '@material-ui/core/Typography';
import Container from '@material-ui/core/Container';
import { withStyles } from '@material-ui/core/styles';
import Axios from 'axios';

function Copyright() {
  return (
    <Typography variant="body2" color="textSecondary" align="center">
      {'Copyright © '}
      <Link color="inherit" to="/">
        FITHUB Admin
      </Link>{' '}
      {new Date().getFullYear()}
      {'.'}
    </Typography>
  );
}

const styles = theme => ({
  paper: {
    marginTop: theme.spacing(8),
    display: 'flex',
    flexDirection: 'column',
    alignItems: 'center',
  },
  avatar: {
    margin: theme.spacing(1),
    backgroundColor: theme.palette.secondary.main,
  },
  form: {
    width: '100%', // Fix IE 11 issue.
    marginTop: theme.spacing(3),
  },
  submit: {
    margin: theme.spacing(3, 0, 2),
  },
});

class SignUp extends React.Component{
  constructor(props){
    super(props);
    this.state = {cedula: "", nombre: "", correo: "", contraseña: "", contraseñaRep: ""}
    this.submitUsuario = this.submitUsuario.bind(this)
    this.changeUsuario = this.changeUsuario.bind(this)
  }

  componentDidMount(){
    Axios.get("http://localhost:8080/register")
      .then(response => {
        console.log(response.data)})
      .catch(error => {
        console.log(error.response)
      })
  }

  submitUsuario(event){
    event.preventDefault()
    
    const usuario = JSON.stringify({
      cedula: this.state.cedula,
      nombre: this.state.nombre,
      correo: this.state.correo,
      contrasena: this.state.contraseña,
      contrasenaRep: this.state.contraseñaRep
    })
    
    
    Axios.post("http://localhost:8080/register", usuario, {headers:{"Content-Type" : "application/json"}})
      .then(response => {
        console.log(response.data)
      })
      .catch(error => {
        console.log(error.response)
      })
    
  }

  changeUsuario(event){
    event.preventDefault()
    this.setState({
      [event.target.name] : event.target.value
    });
  }

  render(){
    const {classes} = this.props;

    return (
      <Container component="main" maxWidth="xs">
        <CssBaseline />
        <div className={classes.paper}>
          <Typography component="h1" variant="h5">
            Registro de usuario
          </Typography>
          <form className={classes.form} onSubmit={this.submitUsuario}>
            <Grid container spacing={2}>
              <Grid item xs={12}>
                <TextField
                  autoComplete="off"
                  name="nombre"
                  variant="outlined"
                  required
                  fullWidth
                  id="nombre"
                  label="Nombre"
                  value={this.state.name}
                  onChange={this.changeUsuario}
                  autoFocus
                />
              </Grid>
              <Grid item xs={12}>
                <TextField
                  autoComplete="off"
                  variant="outlined"
                  required
                  fullWidth
                  id="cedula"
                  label="Numero de cedula"
                  name="cedula"
                  value={this.state.cedula}
                  onChange={this.changeUsuario}
                />
              </Grid>
              <Grid item xs={12}>
                <TextField
                  autoComplete="off"
                  variant="outlined"
                  required
                  fullWidth
                  id="correo"
                  label="Correo electronico"
                  name="correo"
                  value={this.state.correo}
                  onChange={this.changeUsuario}
                />
              </Grid>
              <Grid item xs={12}>
                <TextField
                  autoComplete="off"
                  variant="outlined"
                  required
                  fullWidth
                  name="contraseña"
                  label="Contraseña"
                  type="password"
                  id="contraseña"
                  value={this.state.contraseña}
                  onChange={this.changeUsuario}
                />
              </Grid>
              <Grid item xs={12}>
                <TextField
                  autoComplete="off"
                  variant="outlined"
                  required
                  fullWidth
                  name="contraseñaRep"
                  label="Confirmar contraseña"
                  type="password"
                  id="contraseñaRep"
                  value={this.state.contraseñaRep}
                  onChange={this.changeUsuario}
                />
              </Grid>
              <Grid item xs={12}>
                <FormControlLabel
                  control={<Checkbox value="allowExtraEmails" color="primary" />}
                  label="Estoy de acuerdo con los terminos y condiciones."
                />
              </Grid>
            </Grid>
            <Button
              type="submit"
              fullWidth
              variant="contained"
              color="primary"
              className={classes.submit}
            >
              Registrarse
            </Button>
            <Grid container justify="flex-end">
              <Grid item>
                <Link to="/Login">
                  ¿Ya tienes una cuenta? Ingresa aqui
                </Link>
              </Grid>
            </Grid>
          </form>
        </div>
        <Box mt={5}>
          <Copyright />
        </Box>
      </Container>
    );
  }
}

SignUp.propTypes = {
  classes: PropTypes.object.isRequired,
};

export default withStyles(styles)(SignUp);