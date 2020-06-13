package com.api.controladores;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.api.dto.SesionDTO;
import com.api.servicios.PlanServicio;
import com.api.servicios.SesionServicio;
import com.api.servicios.TipoPlanServicio;
import com.api.servicios.UsuarioServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.modelos.Plan;
import com.api.modelos.Sesion;
import com.api.modelos.TipoPlan;
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
	
	@Autowired
	private TipoPlanServicio servicioTipoPlan;


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
				if(usuario.getPlan() == null)
					return "El usuario no tiene ningún plan inscrito";
				
				int cupos = sesion.getCupos() - 1;
				sesion.setCupos(cupos);
				List<Usuario> asistentes = sesion.getAsistentes();   
				asistentes.add(usuario);
				sesion.setAsistentes(asistentes); 
				Plan plan = usuario.getPlan();
				if(plan.getClasesDisponibles()<=0)
					return "El plan del Usuario no cuenta con clases disponibles";
				List<Sesion> sesionesReservadas = usuario.getPlan().getSesionesReservadas();
				sesionesReservadas.add(sesion);
				plan.setSesionesReservadas(sesionesReservadas);
				plan.setClasesDisponibles(plan.getClasesDisponibles()-1);
				usuario.setPlan(plan);
				servicioPlan.addPlan(plan);
				servicio.updateUser(usuario);
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
		for(Sesion ses: sesiones) {
			if (servicioSesion.usuarioInscrito(ses, usuario)){
				SesionDTO sesionSend = new SesionDTO();
				sesionSend.setId(ses.getId());
				sesionSend.setInstructor(ses.getInstructor().getNombre());
				sesionSend.setTipo(ses.getTipo());
				sesionSend.setFecha(ses.getFecha_hora());
				sesionSend.setCupos(ses.getCupos());
				sesionesInscritas.add(sesionSend); 
			}
			servicioPlan.actuaizarListasSesiones(usuario.getCedula());		
		}
		
		return sesionesInscritas;
	}
	
	@GetMapping("/cancelarCupo/{id}/{idSesion}")
	public String cancelarCupo(@PathVariable("id") String idUsuario,@PathVariable("idSesion") String idSesion) {
		Sesion sesion = servicioSesion.getSesionById(idSesion);
		Usuario usuario = servicio.getUserByCorreo(idUsuario);
		Date fecha_actual = new Date();
		if(sesion.getFecha_hora().before(fecha_actual))
			return "No puede cancelar la sesión debido a que ya comenzó";
		
		
		return servicioSesion.cancelarCupo(sesion, usuario);
	}
	
	@GetMapping("/reservarPlan/{id}/{idTipoPlan}")
	public String reservarPlan(@PathVariable("id") String idUsuario,@PathVariable("idTipoPlan") String idTipoPlan) {
		TipoPlan tipoPlan = servicioTipoPlan.getTipoPlanById(idTipoPlan);
		Plan plan = new Plan();
		Date fecha = new Date();
		
		plan.setClasesDisponibles(tipoPlan.getCantSesiones());
		plan.setFechaInicio(new Date());
		plan.setFechaFin(plan.SumarDias(fecha, tipoPlan.getCantDias()));
		plan.setSesionesAsistidas(new ArrayList<>());
		plan.setSesionesReservadas(new ArrayList<>());
		plan.setTipoPlan(tipoPlan);
		
		servicioPlan.addPlan(plan);
		Usuario usuario = servicio.getUserByCedula(idUsuario);
		usuario.setPlan(plan); 
		servicio.updateUser(usuario);
		
		return "Plan reservado con éxito " + usuario.getPlan();
	}
	
}