package com.api.controladores;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.api.dto.SesionDTO;
import com.api.dto.TipoPlanDTO;
import com.api.servicios.PlanServicio;
import com.api.servicios.SesionServicio;
import com.api.servicios.UsuarioServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.modelos.Plan;
import com.api.modelos.Sesion;
import com.api.modelos.Usuario;

@RestController
@RequestMapping("/User")
public class UsuarioControlador {
	
	@Autowired
	private UsuarioServicio servicio;
	
	@Autowired
	private SesionServicio servicioSesion;
	
	@Autowired
	private PlanServicio servicioPlan;


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
			
				int cupos = sesion.getCupos() - 1;
				sesion.setCupos(cupos);
				List<Usuario> asistentes = sesion.getAsistentes();   
				asistentes.add(usuario);
				sesion.setAsistentes(asistentes); 
				Plan plan = usuario.getPlan();
				List<Sesion> sesionesReservadas = usuario.getPlan().getSesionesReservadas();
				sesionesReservadas.add(sesion);
				plan.setSesionesReservadas(sesionesReservadas);
				usuario.setPlan(plan);
				servicio.addUser(usuario);
				servicioSesion.cambiarSesion(sesion);
			}
		}
		return "El usuario ha reservado un cupo con éxito";
	}
	
	
	@GetMapping("/verSesionesReservadas/{email}")
	public List<SesionDTO> verSesionesReservadas(@PathVariable("email") String correo){
		Usuario usuario = servicio.getUserByCorreo(correo);
		List <Sesion> sesiones = servicioSesion.findAllSesionesByFecha();
		List <SesionDTO> sesionesInscritas = new ArrayList<>();
		Date fechaActual = new Date();
		for(Sesion ses: sesiones) {
			if (servicioSesion.usuarioInscrito(ses, usuario)){
				SesionDTO sesionSend = new SesionDTO();
				sesionSend.setId(ses.getId());
				sesionSend.setInstructor(ses.getInstructor().getNombre());
				sesionSend.setTipo(ses.getTipo());
				sesionSend.setFecha(ses.getFecha_hora());
				sesionSend.setCupos(ses.getCupos());
				sesionesInscritas.add(sesionSend);
				if(sesiones.get(i).getFecha_hora().before(fechaActual)) {
					usuario.getPlan().SesionReservada_Asistida(sesiones.get(i).getId());
				}
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
	
	@GetMapping("/reservarPlan/{id}/{idPlan}")
	public String reservarPlan(@PathVariable("id") String idUsuario,@PathVariable("idPlan") String idPlan) {
		Plan plan = servicioPlan.getPlanById(idPlan);
		Usuario usuario = servicio.getUserByCedula(idUsuario);
		usuario.setPlan(plan);
		servicio.addUser(usuario);
		
		return "Plan reservado con éxito " + usuario.getPlan();
	}
	
}