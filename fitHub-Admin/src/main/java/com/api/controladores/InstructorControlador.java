package com.api.controladores;

import java.util.ArrayList;
import java.util.List;

import com.api.dto.SesionDTO;
import com.api.dto.UsuarioDTO;
import com.api.modelos.Sesion;
import com.api.modelos.Usuario;
import com.api.servicios.InstructorServicio;
import com.api.servicios.SesionServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import com.api.modelos.Instructor;
import org.springframework.web.context.request.WebRequest;

import javax.validation.Valid;

@RestController
@RequestMapping("/Admin")
public class InstructorControlador {
	
	@Autowired
	private InstructorServicio servicioIns;

	@Autowired
	private SesionServicio servicioSes;

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
		List<Sesion> sesiones = servicioSes.findAllSesionesByFecha();
		ArrayList<SesionDTO> sesionFormat = new ArrayList<>();
		for (Sesion ses: sesiones) {
			SesionDTO sesionData = new SesionDTO();
			sesionData.setFecha(ses.getFecha_hora());
			sesionData.setSesion(ses.getTipo());
			sesionData.setInstructor(ses.getInstructor().getNombre());
			sesionData.setId(ses.getId());
			List<String> nombres = new ArrayList<>();
			for(int i = 0; i < ses.getAsistentes().size(); i++ ) {
				nombres.add(ses.getAsistentes().get(i).getNombre());
			}
			sesionData.setNombresAsistentes(nombres);
			sesionFormat.add(sesionData);
		}
		return sesionFormat;
	}

	

	
	
	
}
