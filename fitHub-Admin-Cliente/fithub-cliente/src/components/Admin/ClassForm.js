import React, {Component} from "react";

import DatePicker from 'react-datepicker';
import addDays from 'date-fns/addDays'
import Container from '@material-ui/core/Container';
import Typography from '@material-ui/core/Typography';
import Grid from '@material-ui/core/Grid';

import "react-datepicker/dist/react-datepicker.css";
import 'bootstrap/dist/css/bootstrap.min.css';

import MenuItem from '@material-ui/core/MenuItem';
import FormControl from '@material-ui/core/FormControl';
import InputLabel from '@material-ui/core/InputLabel';
import FormHelperText from '@material-ui/core/FormHelperText';
import Select from '@material-ui/core/Select';
import Button from '@material-ui/core/Button';

import { makeStyles } from '@material-ui/core/styles';
import Class from "./ClassT";

export default class ClassForm extends Component{

    constructor (props) {
        super(props)
        this.state = {
          startDate: new Date(),
          type: " "
        };
        this.handleDateChange = this.handleDateChange.bind(this);
        this.onFormSubmit = this.onFormSubmit.bind(this);
      }
    
      handleDateChange(date) {
        this.setState({
          startDate: date
        })
      }
    
      onFormSubmit(e) {
        e.preventDefault();
        console.log(this.state.startDate, this.state.type)
        //this.props.addClass(this.state.startDate, this.state.type)
      }

      render() {
        const class_type_list = ["Clase de boxeo", "Clase de spinning", "Clase de cardio", "Clase de yoga"];
        return (
            <Container component="main" maxWidth="xs">
            <br></br>
            <Typography component="h1" variant="h5">
                Agregar clase
            </Typography>
            <br></br>
            <form onSubmit={ this.onFormSubmit }>
            <Grid container spacing={2}>
              <Grid item xs={12}>
              <DatePicker
                  selected={ this.state.startDate }
                  onChange={ this.handleDateChange }
                  showTimeSelect
                  timeFormat="HH:mm"
                  timeIntervals={20}
                  timeCaption="time"
                  dateFormat="yyyy/MM/dd h:mm aa"
              />
            </Grid>
            <Grid item xs={12}>
            <FormControl >
            <InputLabel id="classes">
            Tipo de clase
            </InputLabel>
            <Select
            required
              onChange={ (event) => { this.state.type = event.target.value }}
              selected = { this.state.type }
            >
              {class_type_list.map(clase =>
                <MenuItem value={clase}>{clase}</MenuItem>
                )}
              
            </Select>
            <FormHelperText>Seleccione un tipo de clase</FormHelperText>
          </FormControl>
            </Grid>
            </Grid>
            <br></br>
            <Button
              type="submit"
              fullWidth
              variant="contained"
              color="primary"
            > Añadir clase
            </Button>
          </form>
          </Container>
        );
    }
}