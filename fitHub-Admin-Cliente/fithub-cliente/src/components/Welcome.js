import React from "react";

import AuthService from "../services/AuthService";

import "react-responsive-carousel/lib/styles/carousel.min.css";
import { Carousel } from 'react-responsive-carousel';

import Container from '@material-ui/core/Container';
import Grid from '@material-ui/core/Grid';
import Typography from '@material-ui/core/Typography';
import Divider from '@material-ui/core/Divider';
import Link from '@material-ui/core/Link';

import { withStyles } from '@material-ui/core/styles';

import MainFeaturedPost from './MainFeaturedPost';
import FeaturedPost from "./FeaturedPost"
import Pricing from "./Pricing"

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
    title: 'Registrate y entrena con nosotros',
    description:
      "Comprometete con tu cuerpo y escoge el plan que mas se acomode a tus necesidades.",
    image: 'https://source.unsplash.com/FodEsaNZs48',
    imgText: 'main image description',
    linkText: 'Ver planes',
};

const featuredPosts = [
{
    title: 'Javier',
    date: "Boxing",
    description:
    'This is a wider card with supporting text below as a natural lead-in to additional content.',
    image: 'https://source.unsplash.com/7kEpUPB8vNk',
    imageText: 'Ver horarios',
},
{
    title: 'Camila',
    date: 'Yoga',
    description:
    'This is a wider card with supporting text below as a natural lead-in to additional content.',
    image: 'https://source.unsplash.com/rZmCg1_QOYQ',
    imageText: 'Ver horarios',
},
{
    title: "Erika",
    date: 'Taewbox',
    description:
        'This is a wider card with supporting text below as a natural lead-in to additional content.',
    image: 'https://source.unsplash.com/YA-9Ut5B03M',
    imageText: 'Ver horarios',
    },
];

const tiers = [
    {
      title: 'Inicial',
      price: '15.000',
      description: ['10 Clases incluidas', '2 Sesiones con entrenador', 'Lockers', 'Acceso a zona de estiramiento'],
      buttonText: 'Comprar Plan Inicial',
      buttonVariant: 'outlined',
    },
    {
      title: 'Pro',
      subheader: 'Most popular',
      price: '50.000',
      description: [
        '30 clases de su preferencia',
        'Asesoramiento personalizado',
        'Acceso a zonas humedas',
        'Zona VIP',
      ],
      buttonText: 'Comprar Plan Pro',
      buttonVariant: 'contained',
    },
    {
      title: 'Plan Fit',
      price: '30.000',
      description: [
        '15 Clases grupales',
        'Entrenamiento semipersonalzado',
        'Acceso a zona de fuerza y cardio ',
        'Lockers',
      ],
      buttonText: 'Comprar Plan Fit',
      buttonVariant: 'outlined',
    },
  ];

class Welcome extends React.Component{
    constructor(props){
        super(props)
    }

    componentDidMount(){
        const userRole = AuthService.getCurrentUserRole()

        if(userRole == "USER"){
            this.props.history.push('/welcomeUser')
        }else if(userRole == "ADMIN"){
            this.props.history.push('/welcomeAdmin')
        }
    }      
    

    render(){
        const { classes } = this.props;
        return(
            <Container maxWidth="xl">

                <MainFeaturedPost post={mainFeaturedPost} />
                <Typography variant="h4" align="center" gutterBottom>
                {"Conoce a nuestros entrenadores"}
                </Typography>
                
                <Divider variant="middle" />
                
                <Container maxWidth="lg">
                <Grid container spacing={4}>
                {featuredPosts.map((post) => (
                <FeaturedPost key={post.title} post={post} />
                ))}
                </Grid>
                <br></br>
                <Typography variant="h4" align="center" gutterBottom>
                {"Descubre nuestros planes"}
                </Typography >
                </Container>
                <Divider variant="middle" />

                <Container maxWidth="md" component="main">
                <Grid container maxWidth="md" spacing={5} alignItems="flex-end">
                  {tiers.map((tier) => (
                    <Pricing key={tier.title} tier={tier}/>
                  ))}
                </Grid>
                </Container>
                
                <br></br>
                <Typography variant="h4" align="center" gutterBottom>
                {"Conoce nuestras clases"}
                </Typography >
                <Divider variant="middle" />

                <Container maxWidth="md" component="main">
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

                <br></br>
                <footer className={classes.footer}>
                  <Container maxWidth="xl">
                    <Typography variant="body1" align="center">
                      Poner cosas en el footer :v 
                      </Typography>
                    <Copyright />
                  </Container>
                </footer>
                
          </Container>
        )
    }
}

export default withStyles(useStyles)(Welcome);