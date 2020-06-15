package com.api.controladores;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.api.dto.PlanDTO;
import com.api.dto.SesionDTO;
import com.api.dto.UsuarioDTO;
import com.api.servicios.PlanServicio;
import com.api.servicios.SesionServicio;
import com.api.servicios.TipoPlanServicio;
import com.api.servicios.UsuarioServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.api.modelos.Plan;
import com.api.modelos.Sesion;
import com.api.modelos.TipoPlan;
import com.api.modelos.Usuario;

@RestController
@RequestMapping("/User")
public class UsuarioControlador {
	
	@Autowired
	private UsuarioServicio servicioUsuario;
	@Autowired
	private SesionServicio servicioSesion;
	@Autowired
	private PlanServicio servicioPlan;
	@Autowired
	private TipoPlanServicio servicioTipoPlan;


	// -------------- Controladores Usuario --------------------------

	@GetMapping("getInfoUsuario/{correo}")
	public UsuarioDTO getInfoUsuario(@PathVariable String correo){
		Usuario user = servicioUsuario.getUserByCorreo(correo);
		Plan plan = user.getPlan();
		UsuarioDTO userSend = new UsuarioDTO();
		PlanDTO planSend = new PlanDTO();

		planSend.setId(plan.getId());
		planSend.setClasesDisponibles(plan.getClasesDisponibles());
		planSend.setFechaInicio(plan.getFechaInicio());
		planSend.setFechaFin(plan.getFechaFin());
		planSend.setTipo(plan.getTipoPlan());
		planSend.setSesionAsistida(plan.getSesionesAsistidas().size());
		planSend.setSesionReservada(plan.getSesionesReservadas().size());

		userSend.setNombre(user.getNombre());
		userSend.setCorreo(user.getCorreo());
		userSend.setCedula(user.getCedula());
		userSend.setRole(user.getRole());
		userSend.setPlanDTO(planSend);

		return userSend;
	}


	// -------------- Controladores Sesion --------------------------

	@GetMapping("/reservarCupo/{id}/{idSesion}")
	public String reservarCupo(@PathVariable("id") String idUsuario,@PathVariable("idSesion") String idSesion){

		Usuario usuario = servicioUsuario.getUserByCorreo(idUsuario);
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
				if(usuario.getPlan() == null) {
					return "El usuario no tiene ningún plan inscrito";
				}
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
				servicioUsuario.updateUser(usuario);
				servicioSesion.cambiarSesion(sesion);
			}
		}
		return "El usuario ha reservado un cupo con éxito";
	}

	@GetMapping("/verSesionesReservadas/{email}")
	public List<SesionDTO> verSesionesReservadas(@PathVariable("email") String correo){
		Usuario usuario = servicioUsuario.getUserByCorreo(correo);
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
		Usuario usuario = servicioUsuario.getUserByCorreo(idUsuario);
		Date fecha_actual = new Date();
		long fechaActualMili = fecha_actual.getTime();
		long fechaLimiteCancelarMili = sesion.getFecha_hora().getTime() - 3600 * 2000;
		if(sesion.getFecha_hora().before(fecha_actual)) {
			return "No puede cancelar, la sesión ya comenzó";
		}else if(fechaActualMili > fechaLimiteCancelarMili){
			return "No puede cancelar, la sesión esta a punto de comenzar";
		}
		
		return servicioSesion.cancelarCupo(sesion, usuario);
	}

	// -------------- Controladores Plan --------------------------

	@GetMapping("/reservarPlan/{id}/{idTipoPlan}")
	public String reservarPlan(@PathVariable("id") String idUsuario,@PathVariable("idTipoPlan") String idTipoPlan) {
		TipoPlan tipoPlan = servicioTipoPlan.getTipoPlanById(idTipoPlan);
		Usuario usuario = servicioUsuario.getUserByCedula(idUsuario);
		
		usuario = servicioPlan.addNewPlan(tipoPlan, usuario);
		servicioUsuario.updateUser(usuario);
		
		return "Plan reservado con éxito " + usuario.getPlan();
	}
	
	@GetMapping("/cancelarPlan/{idPlan}")
	public String cancelarPlan(@PathVariable("idPlan") String idPlan){
		servicioPlan.cancelarPlan(idPlan);
		return "Plan con id:" + idPlan + " cancelado";
	}
	
	@GetMapping("/buscarPlanes")
	public List<Plan> BuscarPlanes(){
		List<Plan> planes = servicioPlan.getAllPlans();
		return planes;
	}
	
	@GetMapping("/buscarPlanesActivos")
	public List<Plan> BuscarPlanesActivos(){
		List<Plan> planes = servicioPlan.getAllActivePlans();
		return planes;
	}
	
	
}