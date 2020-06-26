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
		Usuario user = servicioUsuario.getUserByEmail(correo);
		Plan plan = user.getPlan();
		UsuarioDTO userSend = new UsuarioDTO();
		PlanDTO planSend = new PlanDTO();

		planSend.setId(plan.getId());
		planSend.setClasesDisponibles(plan.getClasesDisponibles());
		planSend.setFechaInicio(plan.getFechaInicio());
		planSend.setFechaFin(plan.getFechaFin());
		planSend.setTipo(plan.getTipoPlan());
		planSend.setActivo(plan.isActivo());
		planSend.setSesionAsistida(plan.getSesionesAsistidas().size());
		planSend.setSesionReservada(plan.getSesionesReservadas().size());

		userSend.setNombre(user.getNombre());
		userSend.setCorreo(user.getCorreo());
		userSend.setCedula(user.getCedula());
		userSend.setRole(user.getRole());
		userSend.setPlanDTO(planSend);
		
		servicioPlan.updateSesionsLists(userSend.getCedula());

		return userSend;
	}


	// -------------- Controladores Sesion --------------------------

	@GetMapping("/reservarCupo/{id}/{idSesion}")
	public String reservarCupo(@PathVariable("id") String idUsuario,@PathVariable("idSesion") String idSesion){
		return servicioSesion.addUserToSesion(idSesion, idUsuario);
	}

	@GetMapping("/verSesionesReservadas/{email}")
	public List<SesionDTO> verSesionesReservadas(@PathVariable("email") String correo){
		Usuario usuario = servicioUsuario.getUserByEmail(correo);
		List <Sesion> sesiones = servicioSesion.findAllSesionsByDate();
		List <SesionDTO> sesionesInscritas = new ArrayList<>();
		for(Sesion ses: sesiones) {
			if (servicioUsuario.signedUser(ses, usuario)){
				SesionDTO sesionSend = new SesionDTO();
				sesionSend.setId(ses.getId());
				sesionSend.setInstructor(ses.getInstructor().getNombre());
				sesionSend.setTipo(ses.getTipo());
				sesionSend.setFecha(ses.getFecha_hora());
				sesionSend.setCupos(ses.getCupos());
				sesionesInscritas.add(sesionSend); 
			}
		}
		
		return sesionesInscritas;
	}
	
	@GetMapping("/cancelarCupo/{id}/{idSesion}")
	public String cancelarCupo(@PathVariable("id") String idUsuario,@PathVariable("idSesion") String idSesion) {
		Sesion sesion = servicioSesion.getSesionById(idSesion);
		Usuario usuario = servicioUsuario.getUserByEmail(idUsuario);		
		return servicioSesion.deleteUserFromSesion(sesion, usuario);
	}

	// -------------- Controladores Plan --------------------------

	@GetMapping("/reservarPlan/{id}/{idTipoPlan}")
	public String reservarPlan(@PathVariable("id") String idUsuario,@PathVariable("idTipoPlan") String idTipoPlan) {
		TipoPlan tipoPlan = servicioTipoPlan.getTipoPlanById(idTipoPlan);
		Usuario usuario = servicioUsuario.getUserByEmail(idUsuario);
		
		usuario = servicioPlan.addNewPlan(tipoPlan, usuario);
		servicioUsuario.updateUser(usuario);
		
		return "Plan reservado con éxito " + usuario.getPlan();
	}
	
	/*@GetMapping("/cancelarPlan/{idPlan}")
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
	}*/
	
	
}