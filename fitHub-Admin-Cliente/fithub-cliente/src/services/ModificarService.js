import Axios from "axios";

var token = ""
if(AuthService.getCurrentUser() != null){
  token = AuthService.getCurrentUser().Authorization
}

const API_URL = "http://localhost:8080/"
const API_URL_Admin = API_URL + "Admin/";
const API_URL_User = API_URL + "User/";

class ClaseService {

    // CRUD TipoSesion----------------------
    addTipoSesion(nom, cup, dur) {
        const tipoSesion = JSON.stringify({
            nombre: nom,
            cupos: cup,
            duracion: dur,
        })
        console.log(sesion)

        return Axios.post( API_URL_Admin + "agregarTipoSesion", tipoSesion, 
        {headers:{"Content-Type" : "application/json",
                    "Authorization": token
                    }})
    }

    updateTipoSesion(tSesion){
        const sesionDTO = JSON.stringify({
        id: tsesion.id,
        nombre: tsesion.nombre,
        cupos: tsesion.cupos,
        duracion: tsesion.duracion,
        })
        
        return Axios.put(API_URL_Admin + "actualizarTipoSesion", sesionDTO,
        {headers:{"Content-Type" : "application/json",
                "Authorization": token
                }})
    }

    deleteSesion(claseId){
        return Axios.delete(API_URL_Admin + "eliminarTipoSesion", {headers:{"Authorization": token}, data: claseId})
    }
  

    // CRUD Plan ----------------------------
    addTipoPlan(nom, dias, sesiones, precio) {
        const tipoPlan = JSON.stringify({
            nombre: nom,
            cantDias: dias,
            cantSesiones: sesiones,
            precio: precio
        })
        console.log(sesion)

        return Axios.post( API_URL_Admin + "agregarTipoPlan", tipoPlan, 
        {headers:{"Content-Type" : "application/json",
                    "Authorization": token
                    }})
    }

    updateTipoSesion(tSesion){
        const sesionDTO = JSON.stringify({
        id: tsesion.id,
        nombre: tsesion.nombre,
        cupos: tsesion.cupos,
        duracion: tsesion.duracion,
        })
        
        return Axios.put(API_URL_Admin + "actualizarTipoSesion", sesionDTO,
        {headers:{"Content-Type" : "application/json",
                "Authorization": token
                }})
    }

    deleteSesion(claseId){
        return Axios.delete(API_URL_Admin + "eliminarTipoSesion", {headers:{"Authorization": token}, data: claseId})
    }
}

export default new ModificarService();