package com.api.controladores;

import java.util.ArrayList;
import java.util.List;

import com.api.dto.TipoPlanDTO;
//import com.api.dto.PlanDTO;
import com.api.dto.SesionDTO;
import com.api.dto.TipoSesionDTO;
import com.api.modelos.Sesion;
import com.api.modelos.TipoPlan;
import com.api.modelos.TipoSesion;
import com.api.modelos.Usuario;
import com.api.servicios.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.api.modelos.Instructor;
//import com.api.modelos.Plan;

import javax.validation.Valid;

@RestController
@RequestMapping("/Admin")
public class InstructorControlador {
	
	@Autowired
	private InstructorServicio servicioInstructor;
	@Autowired
	private SesionServicio servicioSesion;
	@Autowired
	private TipoPlanServicio servicioTipoPlan;
	@Autowired
	private TipoSesionServicio servicioTipoSesion;
	@Autowired
	private UsuarioServicio servicioUsuario;
	//@Autowired
	//private PlanServicio servicioPlan;




	// ----------- Controladores Instructor ----------------
	/*@GetMapping("/encontrarInstructor/{id}")
	public Instructor getInstructor(@PathVariable String id){
		return servicioInstructor.getInstructorByCedula(id);
	}*/

	@GetMapping("/encontrarTodosLosInstructores")
	public List<Instructor> getInstructores(){
		return servicioInstructor.getAllInstructors();
	}

	@ResponseBody
	@GetMapping("/instructoresNombres")
	public List<String> getInstructoresNombres(){
		List<Instructor> instructores = servicioInstructor.getAllInstructors();
		ArrayList<String> insNombres = new ArrayList<>();
		for (Instructor ins: instructores) {
			insNombres.add(ins.getNombre());
		}
		return insNombres;
	}

	@PostMapping("/agregarInstructor")
	public String GuardarInstructor(@RequestBody Instructor instructor) {
		servicioInstructor.addInstructor(instructor);
		return "Instructor añadido con id: "+ instructor.getCedula();
	}
	
	@DeleteMapping("/eliminarInstructor")
	public ResponseEntity<String> eliminarInstructor(@RequestBody String cedula) {
		Instructor instructor = servicioInstructor.getInstructorByCedula(cedula);
		if (instructor != null) {
			servicioInstructor.deleteInstructor(instructor);
			return ResponseEntity.ok().body("Instructor eliminado");
		} else {
			return ResponseEntity.badRequest().body("No existe ningún instructor con este id");
		}
	}



	// ---------------- Controladores Usuario ----------------------
	@GetMapping("/encontrarTodosLosUsuarios")
	public List<Usuario> getUsuarios(){
		return servicioUsuario.getAllUsers();
	}

	// ---------------- Controladores Sesion -----------------------
	@ResponseBody
	@GetMapping("/buscarTodasSesiones")
	public List<SesionDTO> BuscarSesiones( ) {
		ArrayList<SesionDTO> sesionFormat = new ArrayList<>();
		List<Sesion> sesiones = servicioSesion.findAllSesionesByFecha();
		for (Sesion ses: sesiones) {
			SesionDTO sesionData = new SesionDTO();
			sesionData.setFecha(ses.getFecha_hora());
			sesionData.setTipo(ses.getTipo());
			sesionData.setInstructor(ses.getInstructor().getNombre());
			sesionData.setId(ses.getId());
			sesionData.setCupos(ses.getCupos());
			List<String> nombres = new ArrayList<>();
			for(int i = 0; i < ses.getAsistentes().size(); i++ ) {
				nombres.add(ses.getAsistentes().get(i).getNombre());
			}

			sesionData.setNombresAsistentes(nombres);
			sesionFormat.add(sesionData);
		}
		return sesionFormat;
	}

	@PostMapping("/agregarSesion")
	public String GuardarSesion(@RequestBody @Valid SesionDTO sesion) {
		servicioSesion.addSesion(sesion);
		return "Sesion añadida para la fecha: " + sesion.getFecha() ;
	}

	@DeleteMapping("/eliminarSesion")
	public ResponseEntity<String> eliminarSesion(@RequestBody String id) {
		Sesion sesion = servicioSesion.getSesionById(id);
		if (sesion != null) {
			servicioSesion.deleteSesion(sesion);
			return ResponseEntity.ok().body("Sesion eliminada");
		} else {
			return ResponseEntity.badRequest().body("No existe ninguna sesion con este id");
		}
	}

	@PutMapping("/actualizarSesion")
	public String actualizarSesion(@Valid @RequestBody SesionDTO sesion) {
		servicioSesion.cambiarSesion(sesion);
		return "Sesion Actualizada";
	}

	/*@ResponseBody
	@GetMapping("/buscarTodasSesionesConAsistentes")
	public List<SesionDTO> BuscarSesionesConAsistentes( ) {
		ArrayList<SesionDTO> sesionFormat = new ArrayList<>();
		List<Sesion> sesiones = servicioSesion.findAllSesionesByFecha();
		for (Sesion ses: sesiones) {
			if(ses.getAsistentes().size()>0) {
				SesionDTO sesionData = new SesionDTO();
				sesionData.setFecha(ses.getFecha_hora());
				sesionData.setTipo(ses.getTipo());
				sesionData.setInstructor(ses.getInstructor().getNombre());
				sesionData.setId(ses.getId());
				sesionData.setCupos(ses.getCupos());
				List<String> nombres = new ArrayList<>();
				for(int i = 0; i < ses.getAsistentes().size(); i++ ) {
					nombres.add(ses.getAsistentes().get(i).getNombre());
				}
				sesionData.setNombresAsistentes(nombres);
				sesionFormat.add(sesionData);
			}
		}
		return sesionFormat;
	}*/

	// ---------------- Controladores TipoPlan -----------------------

	@PostMapping("/crearTipoPlan")
	public String crearTipoPlan(@RequestBody TipoPlanDTO tipoPlanDTO) {
		servicioTipoPlan.addTipoPlan(tipoPlanDTO);
		return "Tipo de Plan añadido con Nombre: "+ tipoPlanDTO.getNombre();
	}
	@DeleteMapping("/eliminarTipoPlan")
	public ResponseEntity<String> eliminarTipoPlan(@RequestBody String nombre) {
		TipoPlan tipoPlan = servicioTipoPlan.getTipoPlanByNombre(nombre);
		if (tipoPlan != null) {
			servicioTipoPlan.deleteTipoPlan(tipoPlan);
			return ResponseEntity.ok().body("Tipo de Plan eliminado");
		} else {
			return ResponseEntity.badRequest().body("No existe ningún tipo de plan con este nombre");
		}
	}

	/*@PutMapping("/actualizarTipoPlan")
	public String actualizarTipoPlan(@Valid @RequestBody TipoPlanDTO tipoPlanDTO) {
		servicioTipoPlan.cambiarTipoPlan(tipoPlanDTO);
		return "Tipo de Plan Actualizado";
	}*/

	// ---------------- Controladores TipoSesion -----------------------

	@PostMapping("/agregarTipoSesion")
	public String guardarTipoSesion(@RequestBody @Valid TipoSesionDTO tipoSesionDTO) {
		servicioTipoSesion.addTipoSesion(tipoSesionDTO);
		return "Tipo de Sesion añadida";
	}

	@DeleteMapping("/eliminarTipoSesion")
	public ResponseEntity<String> eliminarTipoSesion(@RequestBody String nombre) {
		TipoSesion tipoSesion = servicioTipoSesion.getTipoSesionByNombre(nombre);
		if (tipoSesion != null) {
			servicioTipoSesion.deleteTipoSesion(tipoSesion);
			return ResponseEntity.ok().body("Tipo de Sesion eliminado");
		} else {
			return ResponseEntity.badRequest().body("No existe ningún tipo de sesion con este nombre");
		}
	}

	/*@PutMapping("/actualizarTipoSesion")
	public String actualizarTipoSesion(@Valid @RequestBody TipoSesionDTO tipoSesionDTO) {
		servicioTipoSesion.cambiarTipoSesion(tipoSesionDTO);
		return "Tipo de Sesion Actualizado";
	}*/

	@ResponseBody
	@GetMapping("/buscarTodosTiposSesiones")
	public List<TipoSesionDTO> BuscarTipoSesiones( ) {
		List<TipoSesion> tipoSesiones = servicioTipoSesion.findAllTipos();
		ArrayList<TipoSesionDTO> tipoSesionFormat = new ArrayList<>();
		for (TipoSesion tSes: tipoSesiones) {
			TipoSesionDTO tipoSesionData = new TipoSesionDTO();
			tipoSesionData.setNombre(tSes.getNombre());
			tipoSesionData.setCupos(tSes.getCupos());
			tipoSesionData.setId(tSes.getId());
			tipoSesionFormat.add(tipoSesionData);
		}
		return tipoSesionFormat;
	}
	/*
	@ResponseBody
	@GetMapping("/buscarTiposSesionesNombres")
	public List<String> BuscarTipoSesionesNombres( ) {
		List<TipoSesion> tipoSesiones = servicioTipoSesion.findAllTipos();
		ArrayList<String> tipoSesionNombres = new ArrayList<>();
		for (TipoSesion tSes: tipoSesiones) {
			tipoSesionNombres.add(tSes.getNombre());
		}
		return tipoSesionNombres;
	}*/
	
	//---------------------- Controladores Plan -----------------
	
	/*@PostMapping("/crearPlan")
	public String crearPlan(@RequestBody PlanDTO planDTO) {
		servicioPlan.addPlan(planDTO);
		return "Plan añadido con id: "+ planDTO.getId();
	}
	@DeleteMapping("/eliminarPlan")
	public ResponseEntity<String> eliminarPlan(@RequestBody String idPlan) {
		Plan Plan = servicioPlan.getPlanById(idPlan);
		if (Plan != null) {
			servicioPlan.deletePlan(Plan);
			return ResponseEntity.ok().body("Plan eliminado");
		} else {
			return ResponseEntity.badRequest().body("No existe ningún plan con este id");
		}
	}

	@PutMapping("/actualizarPlan")
	public String actualizarPlan(@Valid @RequestBody PlanDTO PlanDTO) {
		servicioPlan.cambiarPlan(PlanDTO);
		return "Plan Actualizado";
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
