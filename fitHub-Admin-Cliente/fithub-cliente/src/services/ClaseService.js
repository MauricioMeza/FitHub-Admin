import Axios from "axios";
import AuthService from "./AuthService";
 
var token = ""
if(AuthService.getCurrentUser() != null){
  token = AuthService.getCurrentUser().Authorization
}

const API_URL = "http://localhost:8080/"
const API_URL_Admin = API_URL + "Admin/";
const API_URL_User = API_URL + "User/";

class ClaseService {

  addClase(fecha, clase, instructor) {
    const sesion = JSON.stringify({
        fecha: fecha,
        sesion: clase,
        instructor: instructor,
      })

      return Axios.post( API_URL_Admin + "agregarSesion", sesion, 
      {headers:{"Content-Type" : "application/json",
                "Authorization": token
              }})
  }

  // Acciones Administrador----------------------
  getClasesAdmin(){
    return Axios.get(API_URL_Admin + "buscarTodasSesiones", {headers:{"Authorization": token}})
  }

  getInstNombres(){
    return Axios.get(API_URL_Admin + "instructoresNombres" , {headers:{"Authorization": token}})
  }
  getClasesNombres(){
    //return Axios.get( API_URL +"encontrarTodasLasClases")
    return ["Clase de boxeo", "Clase de spinning", "Clase de cardio", "Clase de yoga"]
  }

  deleteSesion(claseId){
    return Axios.delete(API_URL_Admin + "eliminarSesion", {headers:{"Authorization": token}, data: claseId})
  }
  

  // Acciones Usuario ----------------------------
  getClasesUser(){
    let user = AuthService.getCurrentUser()
    console.log(user)
    return Axios.get(API_URL_User + "verSesionesReservadas/" + user.Mail , {headers:{"Authorization": token}})
  }
  
  reserveClase(ClaseId){
    let user = AuthService.getCurrentUser()
    return Axios.get(API_URL_User + "reservarCupo/" + user.Mail + "/" + ClaseId , {headers:{"Authorization": token}})
  }

  cancelClase(ClaseId ){
    let user = AuthService.getCurrentUser()
    return Axios.get(API_URL_User + "cancelarCupo/" + user.Mail + "/" + ClaseId , {headers:{"Authorization": token}})
  }

  getClases(){
    return Axios.get(API_URL + "listaSesiones")
  }
}

export default new ClaseService();