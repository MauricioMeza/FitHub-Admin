import React from "react";

import Card from '@material-ui/core/Card';
import CardContent from '@material-ui/core/CardContent';
import CardHeader from '@material-ui/core/CardHeader';
import Typography from '@material-ui/core/Typography';
import { withStyles } from '@material-ui/core/styles';
import Container from '@material-ui/core/Container';

import { Progress } from 'react-sweet-progress';
import "react-sweet-progress/lib/style.css";


const useStyles = theme => ({
    root: {
        minWidth: 275,
      },
      bullet: {
        display: 'inline-block',
        margin: '0 2px',
        transform: 'scale(0.8)',
      },
      title: {
        fontSize: 14,
      },
      pos: {
        marginBottom: 12,
      },
      cardHeader: {
        fontSize: 14,
        backgroundColor:
          theme.palette.type === 'light' ? theme.palette.grey[200] : theme.palette.grey[700],
      },
  });

class PlanUser extends React.Component{
    render(){
        const {classes} = this.props;
        return (

            <Container maxWidth="md" component="main">
            <Card className={classes.root}>
                <CardHeader
                    title="Mi plan actual"
                    subheader="Nombre de usuario"
                    titleTypographyProps={{ align: 'center' }}
                    subheaderTypographyProps={{ align: 'center' }}
                    className={classes.cardHeader}
                />
                <CardContent>
                    <Typography className={classes.pos} variant="h5" color="textPrimary">
                    Mi plan actual es : Nombre tipo plan 
                    </Typography>
                    <Typography variant="body2" variant="subtitle1" component="p">
                    Fecha de inico: 14/05/2020
                    <br />
                    Fecha final: 14/05/2021
                    </Typography>

                    <Progress percent={88} status="active" />
                    
                    <Typography variant="body2" align="center" variant="body2" component="p">
                    Te quedan 203 dias 
                    </Typography>
                    <Typography variant="body2" variant="subtitle1" component="p">
                    Clases iniciales: 2 clases
                    <br />
                    Clases atendidas: 23 clases
                    <br/>
                    Clases reservadas: 2 clases
                    </Typography>
                    <Progress percent={55} status="active" />
                    <Typography variant="body2" align="center" variant="body2" component="p">
                    Te quedan 10 clases 
                    </Typography>
            </CardContent>
        </Card>
        </Container>

        
        )
    }
}

export default withStyles(useStyles)(PlanUser);