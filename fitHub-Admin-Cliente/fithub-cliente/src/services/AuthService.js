import { BehaviorSubject } from 'rxjs';
import Axios from "axios";

const API_URL = "http://localhost:8080/";

class AuthService {

  login(correo, contrasena) {
    const login = JSON.stringify({
        correo: correo,
        contrasena: contrasena,
      })
      
      return Axios.post( API_URL + "login", login, {headers:{"Content-Type" : "application/json"}})
        .then(response => {
          console.log(response.data)
          localStorage.setItem("user", JSON.stringify(response.data))
        })
  
  }

  logout() {
    localStorage.removeItem("user");
  }

  register(cedula, nombre, correo, contrasena, contrasenaRep) {
    const usuario = JSON.stringify({
        cedula: cedula,
        nombre: nombre,
        correo: correo,
        contrasena: contrasena,
        contrasenaRep: contrasenaRep
      })      
      
      return Axios.post( API_URL +"register", usuario, {headers:{"Content-Type" : "application/json"}})
  }

  getCurrentUser() {
    const user = localStorage.getItem('user');
    return JSON.parse(user);
  }
}

export default new AuthService();