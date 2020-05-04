import React, {Component} from "react"; 
import PropTypes from "prop-types";

class ClassT extends Component{
    styleCompleted(){
        return{
            fontSize: "20px",
            color: "black"
        }
    }

    render(){
        const {clas} = this.props;
        return <div style={this.styleCompleted()} >
            {clas.id} -
            {clas.startDate} -  
            {clas.type} 
        </div>
}

}

ClassT.propTypes ={
    clas: PropTypes.object.isRequired
}

export default ClassT;