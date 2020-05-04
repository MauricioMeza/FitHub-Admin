//import Axios from "axios";

import React from 'react';
import { connect } from "react-redux";

const DataService = ({user}) => ({})

const mapStateToProps = state => ({
})

const mapDispatchToProps = dispatch => ({
  authHeader(user) {
    dispatch ({
      type: "AUTH_HEADER",
      user
    })
  }
})

export default connect(mapStateToProps, mapDispatchToProps)(DataService);

/*export default function authHeader() {
    const user = JSON.parse(localStorage.getItem('user'));
  
    if (user && user.accessToken) {
      return { Authorization: user.Authorization };
    } else {
      return {};
    }
}*/