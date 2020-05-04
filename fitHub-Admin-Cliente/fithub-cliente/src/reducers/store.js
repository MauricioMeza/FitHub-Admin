import { createStore } from "redux"
import AuthService from "../services/AuthService";

const initialState = {
    user: [AuthService.getCurrentUser()]
}

const reducerStore = (state = initialState, action) => {
    switch(action.type) {
        case "SAVE_USER_SESSION":
            return {
                ...state,
                user: state.user.concat(action.savedUser)
            }
        default:
    }  
}

export default createStore(reducerStore)