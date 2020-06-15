import Axios from "axios";
import AuthService from "./AuthService";

var token = ""
if(AuthService.getCurrentUser() != null){
  token = AuthService.getCurrentUser().Authorization
}

const API_URL = "http://localhost:8080/"
const API_URL_User = "http://localhost:8080/User/"

class InfoService {
  
    getInstructoresList(){
      var InstListFront = [

      ];
      //return Axios.get( API_URL +"buscarTodosInstructores").then(response => { meter todo lo de abajo aca })
      let InstListBack = [
        {"nombre":"Ivan"
        ,"correo":"ivan@Fithub.com",
        "image": 'https://source.unsplash.com/7kEpUPB8vNk'},

        {"nombre":"Juan"
        ,"correo":"juan@Fithub.com",
        "image": 'https://source.unsplash.com/rZmCg1_QOYQ'},

        {"nombre":"Daniela"
        ,"correo":"daniela@Fithub.com",
        "image":'https://source.unsplash.com/YA-9Ut5B03M'},
      ];
      


      InstListBack.map((inst) => {
        let descripcion = inst.nombre + " Es un entrenador con años de experiencia y dedicado en su compromiso a que mejores tu salud"
        let planFront = {title: inst.nombre, date: inst.correo, description: descripcion,image: inst.image, imageText: 'Ver horarios'}
        InstListFront.push(planFront)
      })
      
      return InstListFront
    
    }

    getPlanesList(){
      var planListFront = [];
      //return Axios.get( API_URL +"buscarTodosTiposSesiones").then(response => { meter todo lo de abajo aca })
      let PlanListBack = [
        {"id": "5ee5b8eccbaa9235ea1bdcbd",
        "nombre":"Plan Gold",
        "cantDias":30,
        "cantSesiones":20,
        "precio":100000},

        {"id": "5ee5b8fccbaa9235ea1bdcbe",
        "nombre":"Plan Silver",
        "cantDias":30,
        "cantSesiones":15,
        "precio":80000},

        {"id": "5ee5b8fccbadfasd5ea1bdcbe",
        "nombre":"Plan Lite",
        "cantDias":15,
        "cantSesiones":10,
        "precio":40000},
      ];

      PlanListBack.map((plan) => {
        let descripcion = [plan.cantSesiones + " Clases Incluidas", plan.cantDias + " Dias de Duracion"]
        let planFront = {title: plan.nombre, price: plan.precio, description: descripcion, buttonText: "Adquirir " + plan.nombre}
        planListFront.push(planFront)
      })
      
      return planListFront
      
    }

    getUserInfo(){
      let user = AuthService.getCurrentUser()
      return Axios.get(API_URL_User + "getInfoUsuario/" + user.Mail, {headers:{"Authorization": token}})
    }

}export default new InfoService();