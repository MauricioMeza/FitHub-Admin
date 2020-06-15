import React from "react";

import AuthService from "../services/AuthService";
import InfoService from "../services/InfoService";


import "react-responsive-carousel/lib/styles/carousel.min.css";
import { Carousel } from 'react-responsive-carousel';

import Container from '@material-ui/core/Container';
import Grid from '@material-ui/core/Grid';
import Typography from '@material-ui/core/Typography';
import Divider from '@material-ui/core/Divider';
import Link from '@material-ui/core/Link';

import { withStyles } from '@material-ui/core/styles';

import MainFeaturedPost from './MainFeaturedPost';
import FeaturedPost from "./FeaturedPost";
import Pricing from "./Pricing";
import FooterPage from "./FooterPage";

const useStyles = theme => ({
    root: {
       flexGrow: 1,
     },
     footer: {
      padding: theme.spacing(3, 2),
      marginTop: 'auto',
      backgroundColor:
        theme.palette.type === 'light' ? theme.palette.grey[200] : theme.palette.grey[800],
    },
    });

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

const mainFeaturedPost = {
    title: 'Bienvenido a Fithub',
    description:
      "Somos el gimnasio numero 1 para que entrenes con las últimas tendencias del Fitness mundial",
    title2: 'Registrate y obten una clase de prueba Gratis',
    title3: 'Si ya eres miembro Inicia Sesion y reserva tus clases',
    
    image: 'https://source.unsplash.com/FodEsaNZs48',
    imgText: 'main image description',
    linkText: 'Ver planes',
};

class Welcome extends React.Component{
  constructor(props){
      super(props)
      this.state = {planes: [], instructores: [], }
  }

  componentDidMount(){
      const userRole = AuthService.getCurrentUserRole()
      if(userRole == "USER"){
          this.props.history.push('/welcomeUser')
      }else if(userRole == "ADMIN"){
          this.props.history.push('/welcomeAdmin')
      }

      this.setState({
        planes: InfoService.getPlanesList()
      })
    
      this.setState({
          instructores: InfoService.getInstructoresList()
      }) 
  }      
    

  render(){
      const { classes } = this.props;
      const {planes, instructores} = this.state
      return(
          <Container maxwidth="xl">

              <MainFeaturedPost post={mainFeaturedPost} />

              <br></br>
              <br></br>
              <br></br>

              <Typography variant="h4" align="center" gutterBottom>
              {"Conoce a nuestros entrenadores"}
              </Typography>
              
              <Divider variant="middle" />
              
              <Container maxWidth="lg">
              <Grid container spacing={4}>
              {instructores.map((ins) => (
              <FeaturedPost key={ins.title} post={ins} />
              ))}
              </Grid>
              
              <br></br>
              <br></br>
              <br></br>
              <br></br>

              <Typography variant="h4" align="center" gutterBottom>
              {"Descubre nuestros planes"}
              </Typography >
              </Container>
              <Divider variant="middle" />

              <Container maxwidth="md" component="main">
              <Grid container maxwidth="md" spacing={5} alignItems="flex-start" justify="center">
                {planes.map((plan, i) => (
                  <Pricing tier={plan} key={i} />
                ))}
              </Grid>
              </Container>
              
              <br></br>
              <br></br>
              <br></br>
              <br></br>

              <Typography variant="h4" align="center" gutterBottom>
              {"Conoce nuestras clases"}
              </Typography >
              <Divider variant="middle" />

              <Container maxwidth="md" component="main">
                <Carousel>
                  <div>
                    <img src='https://source.unsplash.com/3RnkZpDqsEI' />
                    <p className="legend">Streching</p>
                  </div>
                  <div>
                    <img src='https://source.unsplash.com/gJtDg6WfMlQ' />
                    <p className="legend">Yoga</p>
                  </div>
                  <div>
                    <img src='https://source.unsplash.com/y0SMHt74yqc' />
                    <p className="legend">Body Combat</p>
                  </div>
                </Carousel>
              </Container>                  
        </Container>
      )
  }
}

export default withStyles(useStyles)(Welcome);