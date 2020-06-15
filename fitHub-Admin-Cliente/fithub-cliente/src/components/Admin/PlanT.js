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

class PlanT extends Component{
  constructor(props){
    super(props);
    this.deletePlan = this.deletePlan.bind(this)
  }

  //Completar funciones

  deletePlan(i){
      

  }  
    
  render(){ 
    const {plan, plans} = this.props;
    return(
      <Grid item xs={12} md={6} className = {plans.infoContainer} >
        <div>
          <List>
            <ListItem>
              <ListItemIcon>
                <CalendarIcon />
              </ListItemIcon>
              <ListItemText className = {plans.infoText}
                primary= {plan.id}
              />
              <ListItemSecondaryAction>
                <IconButton edge="end" aria-label="delete" size="medium" onClick={(e) => {this.deletePlan(plan.id)}}>
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

PlanT.propTypes ={
  plan: PropTypes.object.isRequired,
  plans: PropTypes.object.isRequired,
  reload: PropTypes.func.isRequired
}

export default withStyles(styles)(PlanT);