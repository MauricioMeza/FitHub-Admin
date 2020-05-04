import Axios from "axios";

const API_URL = "http://localhost:8080/Admin/";

class ClaseService {

  addClase(fecha, clase, instructor) {
    const sesion = JSON.stringify({
        fecha: fecha,
        sesion: clase,
        instructor: instructor,
      })

      return Axios.post( API_URL + "agregarSesion", sesion, {headers:{"Content-Type" : "application/json"}})
  }

  getClases(){
    return Axios.get(API_URL + "buscarTodasSesiones")
  }

  getInstNombres(){
    return Axios.get( API_URL + "instructoresNombres")
  }

  getClasesNombres(){
    //return Axios.get( API_URL +"encontrarTodasLasClases")
    return ["Clase de boxeo", "Clase de spinning", "Clase de cardio", "Clase de yoga"]
  }
}

export default new ClaseService();