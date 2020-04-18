package com.api.controladores;

import java.util.List;
import java.util.Optional;

import com.api.servicios.InstructorServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
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
	public String GuardarUsuario(@RequestBody Instructor instructor) {
		servicio.saveInstructor(instructor);
		return "Usuario añadido con id: "+ instructor.getCedula();
	}
	
	@GetMapping("/encontrarTodosLosInstructores")
	public List<Instructor> getUsuarios(){
		return servicio.findAllInstructores();
	}
	
	@GetMapping("/encontrarInstructor/{Id}")
	public Optional<Instructor> getInstructor(@PathVariable int cedula){
		return servicio.findInstructorById(cedula);
	}
	
	@DeleteMapping("/borrarInstructor/{Id}")
	public String borrarInstructor(@PathVariable int cedula){
		servicio.deleteInstructorById(cedula);
		return "El instructor con el Id: "+ cedula+" ha sido borrado";
	}
	
	
	
}
