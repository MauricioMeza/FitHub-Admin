package com.api.controladores;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.api.dto.TipoPlanDTO;
import com.api.dto.PlanDTO;
import com.api.dto.SesionDTO;
import com.api.dto.TipoSesionDTO;
import com.api.dto.UsuarioDTO;
import com.api.modelos.Sesion;
import com.api.modelos.TipoSesion;
import com.api.modelos.Usuario;
import com.api.servicios.InstructorServicio;
import com.api.servicios.PlanServicio;
import com.api.servicios.SesionServicio;
import com.api.servicios.TipoPlanServicio;
import com.api.servicios.TipoSesionServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import com.api.modelos.Instructor;
import com.api.modelos.Plan;
import com.api.modelos.TipoPlan;

import org.springframework.web.context.request.WebRequest;

import javax.validation.Valid;

@RestController
@RequestMapping("/Admin")
public class InstructorControlador {
	
	@Autowired
	private InstructorServicio servicioIns;

	@Autowired
	private SesionServicio servicioSes;
	
	@Autowired
	private TipoPlanServicio servicioTipoPlan;
	
	@Autowired
	private PlanServicio servicioPlan;

	@Autowired
	private TipoSesionServicio servicioTipoSesion;

	// ----------- Controladores Instructor ----------------

	@GetMapping("/validarInstructor")
	public String validacionInstructor(){
		return "Valido";
	}

	@PostMapping("/agregarInstructor")
	public String GuardarInstructor(@RequestBody Instructor instructor) {
		servicioIns.addInstructor(instructor);
		return "Instructor añadido con id: "+ instructor.getCedula();
	}
	
	@GetMapping("/encontrarTodosLosInstructores")
	public List<Instructor> getInstructores(){
		return servicioIns.getAllInstructors();
	}

	@ResponseBody
	@GetMapping("/instructoresNombres")
	public List<String> getInstructoresNombres(){
		List<Instructor> instructores = servicioIns.getAllInstructors();
		ArrayList<String> insNombres = new ArrayList<>();
		for (Instructor ins: instructores) {
			insNombres.add(ins.getNombre());
		}
		return insNombres;
	}

	@GetMapping("/encontrarInstructor/{id}")
	public Instructor getInstructor(@PathVariable String id){
		return servicioIns.getInstructorByCedula(id);
	}

	// ---------------- Controladores Sesion -----------------------

	@PostMapping("/agregarSesion")
	public String GuardarSesion(@RequestBody @Valid SesionDTO sesion) {
		servicioSes.addSesion(sesion);
		return "Sesion añadida para la fecha: " + sesion.getFecha() ;
	}

	@DeleteMapping("/eliminarSesion")
	public ResponseEntity<String> eliminarSesion(@RequestBody String id) {
		Sesion sesion = servicioSes.getSesionById(id);
		if (sesion != null) {
			servicioSes.deleteSesion(sesion);
			return ResponseEntity.ok().body("Sesion eliminada");
		} else {
			return ResponseEntity.badRequest().body("No existe ninguna sesion con este id");
		}
	}

	@PutMapping("/actualizarSesion")
	public String actualizarSesion(@Valid @RequestBody SesionDTO sesion) {
		servicioSes.cambiarSesion(sesion);
		return "Sesion Actualizada";
	}

	@ResponseBody
	@GetMapping("/buscarTodasSesiones")
	public List<SesionDTO> BuscarSesiones( ) {
		ArrayList<SesionDTO> sesionFormat = new ArrayList<>();
		List<Sesion> sesiones = servicioSes.findAllSesionesByFecha();
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
  
	// ---------------- Controladores TipoPlan -----------------------

	@PostMapping("/crearTipoPlan")
	public String crearTipoPlan(@RequestBody TipoPlanDTO tipoPlanDTO) {
		servicioTipoPlan.addTipoPlan(tipoPlanDTO);
		return "Tipo de Plan añadido con Nombre: "+ tipoPlanDTO.getNombre();
	}
	
	
	@GetMapping("/crearPlan/{idTipoPlan}")
	public String crearPlan(@PathVariable String idTipoPlan) {
		Plan plan = new Plan();
		Date fecha = new Date();
		TipoPlan tipoPlan = servicioTipoPlan.getTipoPlanById(idTipoPlan);
		
		plan.setClasesDisponibles(tipoPlan.getCantSesiones());
		plan.setFechaInicio(new Date());
		plan.setFechaFin(plan.SumarDias(fecha, tipoPlan.getCantDias()));
		plan.setSesionesAsistidas(new ArrayList<>());
		plan.setSesionesReservadas(new ArrayList<>());
		plan.setTipoPlan(tipoPlan);
		
		servicioPlan.addPlan(plan);
		return "Plan añadido con éxito";
	}

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

	@PutMapping("/actualizarTipoSesion")
	public String actualizarTipoSesion(@Valid @RequestBody TipoSesionDTO tipoSesionDTO) {
		servicioTipoSesion.cambiarTipoSesion(tipoSesionDTO);
		return "Tipo de Sesion Actualizado";
	}

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

	@ResponseBody
	@GetMapping("/buscarTiposSesionesNombres")
	public List<String> BuscarTipoSesionesNombres( ) {
		List<TipoSesion> tipoSesiones = servicioTipoSesion.findAllTipos();
		ArrayList<String> tipoSesionNombres = new ArrayList<>();
		for (TipoSesion tSes: tipoSesiones) {
			tipoSesionNombres.add(tSes.getNombre());
		}
		return tipoSesionNombres;
	}
  
}
