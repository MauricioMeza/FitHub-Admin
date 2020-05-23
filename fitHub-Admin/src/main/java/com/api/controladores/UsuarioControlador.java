package com.api.controladores;

import java.util.ArrayList;
import java.util.List;

import com.api.servicios.SesionServicio;
import com.api.servicios.UsuarioServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.api.modelos.Sesion;
import com.api.modelos.Usuario;

@RestController
public class UsuarioControlador {
	
	@Autowired
	private UsuarioServicio servicio;
	
	@Autowired
	private SesionServicio servicioSesion;


	@GetMapping("/encontrarTodosLosUsuarios")
	public List<Usuario> getUsuarios(){
		return servicio.getAllUsers();
	}
	
	@GetMapping("/encontrarUsuario/{id}")
	public Usuario getUsuario(@PathVariable String id){
		return servicio.getUserByCedula(id);
	}
	
	@GetMapping("/reservarCupo/{id}/{idSesion}")
	public String reservarCupo(@PathVariable("id") String idUsuario,@PathVariable("idSesion") String idSesion){

		Usuario usuario = servicio.getUserByCedula(idUsuario);
		Sesion sesion = servicioSesion.getSesionById(idSesion);
		//List<Sesion> sesionesReservadas = usuario.getSesionesReservadas();
		
		if(servicioSesion.usuarioInscrito(sesion, usuario)) {
			return "El usuario " + usuario.getNombre() + " ya está inscrito en la Sesion";
		}
		else{
			if(sesion.getCupos() <= 0) {
				return "La sesión no tiene cupos suficientes para realizar la inscripción";
			}
			else{	
		
				//sesionesReservadas.add(sesion);
				//usuario.setSesionesReservadas(sesionesReservadas);
				//servicio.addUser(usuario);
			
				int cupos = sesion.getCupos() - 1;
				sesion.setCupos(cupos);
				List<Usuario> asistentes = sesion.getAsistentes();   
				asistentes.add(usuario);
				sesion.setAsistentes(asistentes); 
				servicioSesion.cambiarSesion(sesion);
			}
		}
		return "El usuario " + usuario.getNombre() + " ha reservado un cupo con éxito";
	}
	
	
	@GetMapping("/verSesionesReservadas/{id}")
	public String verSesionesReservadas(@PathVariable("id") String id){
		Usuario usuario = servicio.getUserByCedula(id);
		List <Sesion> sesiones = servicioSesion.findAllSesionesByFecha();
		List <Sesion> sesionesInscritas = new ArrayList<>();
		for(int i = 0; i < sesiones.size(); i++)
			if(servicioSesion.usuarioInscrito(sesiones.get(i), usuario))
				sesionesInscritas.add(sesiones.get(i));
		
		return sesionesInscritas.toString();
	}
	
	@GetMapping("/cancelarCupo/{id}/{idSesion}")
	public String cancelarCupo(@PathVariable("id") String idUsuario,@PathVariable("idSesion") String idSesion) {
		Sesion sesion = servicioSesion.getSesionById(idSesion);
		Usuario usuario = servicio.getUserByCedula(idUsuario);
		return servicioSesion.cancelarCupo(sesion, usuario);
	}
	
}