package com.api.controladores;

import java.util.List;

import com.api.servicios.InstructorServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.api.modelos.Instructor;

@RestController
public class InstructorControlador {
	
	@Autowired
	private InstructorServicio servicio;
	
	@PostMapping("/agregarInstructor")
	public String GuardarInstructor(@RequestBody Instructor instructor) {
		servicio.addInstructor(instructor);
		return "Usuario añadido con id: "+ instructor.getCedula();
	}
	
	@GetMapping("/encontrarTodosLosInstructores")
	public List<Instructor> getInstructores(){
		return servicio.getAllInstructors();
	}
	
	@GetMapping("/encontrarInstructor/{id}")
	public Instructor getInstructor(@PathVariable String id){
		return servicio.getInstructorByCedula(id);
	}
	
	
	
}
