package com.api.controladores;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.api.dto.SesionDTO;
import com.api.servicios.SesionServicio;
import com.api.servicios.UsuarioServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.modelos.Sesion;
import com.api.modelos.Usuario;

@RestController
@RequestMapping("/User")
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

		Usuario usuario = servicio.getUserByCorreo(idUsuario);
		Sesion sesion = servicioSesion.getSesionById(idSesion);
		Date fecha_actual = new Date();
		//List<Sesion> sesionesReservadas = usuario.getSesionesReservadas();
		
		if(sesion.getFecha_hora().before(fecha_actual))
			return "La sesión ya pasó";
		
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
		return "El usuario ha reservado un cupo con éxito";
	}
	
	
	@GetMapping("/verSesionesReservadas/{email}")
	public List<SesionDTO> verSesionesReservadas(@PathVariable("email") String id){
		Usuario usuario = servicio.getUserByCorreo(id);
		List <Sesion> sesiones = servicioSesion.findAllSesionesByFecha();
		List <SesionDTO> sesionesInscritas = new ArrayList<>();
		for(int i = 0; i < sesiones.size(); i++) {
			if (servicioSesion.usuarioInscrito(sesiones.get(i), usuario)){
				SesionDTO sesionSend = new SesionDTO();
				sesionSend.setId(sesiones.get(i).getId());
				sesionSend.setInstructor(sesiones.get(i).getInstructor().getNombre());
				sesionSend.setSesion(sesiones.get(i).getTipo());
				sesionSend.setFecha(sesiones.get(i).getFecha_hora());
				sesionesInscritas.add(sesionSend);
			}
		}
		return sesionesInscritas;
	}
	
	@GetMapping("/cancelarCupo/{id}/{idSesion}")
	public String cancelarCupo(@PathVariable("id") String idUsuario,@PathVariable("idSesion") String idSesion) {
		Sesion sesion = servicioSesion.getSesionById(idSesion);
		Usuario usuario = servicio.getUserByCorreo(idUsuario);
		return servicioSesion.cancelarCupo(sesion, usuario);
	}
	
}