import React, {Component} from "react"; 
import PropTypes from "prop-types";
import { makeStyles } from '@material-ui/core/styles';

import List from '@material-ui/core/List';
import ListItem from '@material-ui/core/ListItem';
import ListItemIcon from '@material-ui/core/ListItemIcon';
import ListItemSecondaryAction from '@material-ui/core/ListItemSecondaryAction';
import ListItemText from '@material-ui/core/ListItemText';
import Grid from '@material-ui/core/Grid';
import CalendarIcon from '@material-ui/icons/CalendarToday';
import DeleteIcon from '@material-ui/icons/Delete';
import IconButton from '@material-ui/core/IconButton';

const styles = makeStyles((theme) => ({
  root: {
    width: '100%',
    maxWidth: '36ch',
    backgroundColor: theme.palette.background.paper,
  },
  inline: {
    display: 'inline',
  },
}));


class ClassT extends Component{
    
    render(){
        const {clas} = this.props;
        return(
       <Grid item xs={12} md={6} >
           <div>
             <List>
                 <ListItem>
                   <ListItemIcon>
                     <CalendarIcon />
                   </ListItemIcon>
                  <ListItemText
                     primary= {clas.fecha + "-"+ clas.hora + clas.tipo}
                     secondary = {clas.instructor}
                  />
                   <ListItemSecondaryAction>
                     <IconButton edge="end" aria-label="delete" size="medium">
                       <DeleteIcon />
                     </IconButton>
                   </ListItemSecondaryAction>
                </ListItem>
             </List>
           </div>
        </Grid>
        )

        // return <div style={this.styleCompleted()} >
        //     {clas.fecha} -
        //     {clas.hora} -  
        //     {clas.tipo} -
        //     {clas.instructor}  
        // </div>
}

}

ClassT.propTypes ={
    clas: PropTypes.object.isRequired
}

export default ClassT;