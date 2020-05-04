import React from 'react';
import PropTypes from 'prop-types';
import {Link} from "react-router-dom";
import Button from '@material-ui/core/Button';
import CssBaseline from '@material-ui/core/CssBaseline';
import TextField from '@material-ui/core/TextField';
import Grid from '@material-ui/core/Grid';
import Box from '@material-ui/core/Box';
import Typography from '@material-ui/core/Typography';
import Container from '@material-ui/core/Container';
import { withStyles } from '@material-ui/core/styles';
import Axios from 'axios';
import AuthService from '../services/AuthService';

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
    this.state = {cedula: "", nombre: "", correo: "", contrasena: "", contrasenaRep: ""}
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
    AuthService.register(this.state.cedula, this.state.nombre, this.state.correo, this.state.contrasena, this.state.contrasenaRep)
    .then(()=> {
        this.props.history.push("/login");
        window.location.reload();
    })
    .catch(error => {
      console.log(error.response)
      switch (error.response.data) {
        case "Ya existe esa cédula en BD":
          alert(error.response.data)
          break
        case "Ya existe ese correo en BD":
          alert(error.response.data)
          break
        default:
          alert(error.response.data)
      }
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
                  name="contrasena"
                  label="Contraseña"
                  type="password"
                  id="contrasena"
                  value={this.state.contrasena}
                  onChange={this.changeUsuario}
                />
              </Grid>
              <Grid item xs={12}>
                <TextField
                  autoComplete="off"
                  variant="outlined"
                  required
                  fullWidth
                  name="contrasenaRep"
                  label="Confirmar contraseña"
                  type="password"
                  id="contrasenaRep"
                  value={this.state.contrasenaRep}
                  onChange={this.changeUsuario}
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