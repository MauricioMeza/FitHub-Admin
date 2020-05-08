package com.api.controladores;

import java.util.ArrayList;
import java.util.List;

import com.api.dto.SesionDTO;
import com.api.modelos.Sesion;
import com.api.servicios.InstructorServicio;
import com.api.servicios.SesionServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.api.modelos.Instructor;

import javax.validation.Valid;

@RestController
@RequestMapping("/Admin")
public class InstructorControlador {
	
	@Autowired
	private InstructorServicio servicioIns;
	@Autowired
	private SesionServicio servicioSes;

	@PostMapping("/agregarInstructor")
	public String GuardarInstructor(@RequestBody Instructor instructor) {
		servicioIns.addInstructor(instructor);
		return "Instructor añadido con id: "+ instructor.getCedula();
	}

	@GetMapping("/validarInstructor")
	public String validacionInstructor(){
		return "Valido";
	}
	
	@GetMapping("/encontrarTodosLosInstructores")
	public List<Instructor> getInstructores(){
		return servicioIns.getAllInstructors();
	}

	@GetMapping("/encontrarInstructor/{id}")
	public Instructor getInstructor(@PathVariable String id){
		return servicioIns.getInstructorByCedula(id);
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

	@PostMapping("/agregarSesion")
	public String GuardarSesion(@RequestBody @Valid SesionDTO sesion) {
		servicioSes.addSesion(sesion);
		return "Sesion añadida para la fecha: " + sesion.getFecha() ;
	}

	@ResponseBody
	@GetMapping("/buscarTodasSesiones")
	public List<Sesion> BuscarSesiones( ) {
		return servicioSes.findAllSesiones();
	}

	

	
	
	
}
