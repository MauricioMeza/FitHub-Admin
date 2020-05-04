const API_URL = "http://localhost:8080/";

class ClaseService {

  addClase(fecha, clase, instructor) {
    const usuario = JSON.stringify({
        fecha: fecha,
        clase: clase,
        instructor: instructor,
      })

      return usuario
      //return Axios.post( API_URL +"addSesion", usuario, {headers:{"Content-Type" : "application/json"}})
  }

  getInstNombres(){
    //return Axios.get( API_URL +"encontrarTodosLosInstructores")
    return ["Juan", "Ivan", "Daniela"]
  }

  getClasesNombres(){
    //return Axios.get( API_URL +"encontrarTodasLasClases")
    return ["Clase de boxeo", "Clase de spinning", "Clase de cardio", "Clase de yoga"]
  }
}

export default new ClaseService();