import React, {Component} from "react"; 
import PropTypes from "prop-types";
import {withStyles} from '@material-ui/core/styles';
import List from '@material-ui/core/List';
import ListItem from '@material-ui/core/ListItem';
import ListItemIcon from '@material-ui/core/ListItemIcon';
import ListItemSecondaryAction from '@material-ui/core/ListItemSecondaryAction';
import ListItemText from '@material-ui/core/ListItemText';
import Grid from '@material-ui/core/Grid';
import CalendarIcon from '@material-ui/icons/CalendarToday';
import DeleteIcon from '@material-ui/icons/Delete';
import IconButton from '@material-ui/core/IconButton';

const styles = theme => ({
  root: {
    width: '100%',
    maxWidth: '36ch',
    backgroundColor: theme.palette.background.paper,
  },
  inline: {
    display: 'inline',
  },
  infoText: {
    width: "100%"
  },
  infoContainer: {
    width: "100%"
  }
});

class ClassTypeT extends Component{
  constructor(props){
    super(props);
    this.deletePlan = this.deletePlan.bind(this)
  }

  //Completar funciones

  deletePlan(i){
      

  }  
    
  render(){ 
    const {classType, classTypes} = this.props;
    return(
      <Grid item xs={12} md={6} className = {classTypes.infoContainer} >
        <div>
          <List>
            <ListItem>
              <ListItemIcon>
                <CalendarIcon />
              </ListItemIcon>
              <ListItemText className = {classTypes.infoText}
                primary= {classType.id}
              />
              <ListItemSecondaryAction>
                <IconButton edge="end" aria-label="delete" size="medium" onClick={(e) => {this.deletePlan(classType.id)}}>
                <DeleteIcon />
                </IconButton>
              </ListItemSecondaryAction>
            </ListItem>
          </List>
        </div>
      </Grid>
     )
}

}

ClassTypeT.propTypes ={
  classType: PropTypes.object.isRequired,
  classTypes: PropTypes.object.isRequired,
  reload: PropTypes.func.isRequired
}

export default withStyles(styles)(ClassTypeT);