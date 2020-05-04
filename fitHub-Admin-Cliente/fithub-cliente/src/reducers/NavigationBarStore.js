import { createStore } from "redux"
import AuthService from "../services/AuthService";

const initialState = {
    showUserBoard: 0,
    currentUser: undefined
}

const navReducer = (state = initialState) => {
    if(localStorage.getItem('user') != null){
        const user = AuthService.getCurrentUser()
        if(user.Rol === "USER"){
            initialState.showUserBoard = 1;
        }else if(user.Rol === "ADMIN"){
            initialState.showUserBoard = 2;
        }
    }
    return initialState
}

export default navReducer