package com.api.controladores;

import java.util.List;
import java.util.Optional;

import com.api.servicios.SesionServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.api.modelos.Sesion;

@RestController
public class SesionControlador {
	
	@Autowired
	private SesionServicio servicio;
	
	@PostMapping("/agregarSesion")
	public String GuardarSesion(@RequestBody Sesion instructor) {
		servicio.saveSesion(instructor);
		return "Usuario añadido con id: "+ instructor.getIdSesion();
	}
	
	@GetMapping("/encontrarTodasLasSesiones")
	public List<Sesion> getSesiones(){
		return servicio.findAllSesiones();
	}
	
	@GetMapping("/encontrarSesion/{idSesion}")
	public Optional<Sesion> getInstructor(@PathVariable int idSesion){
		return servicio.findSesionById(idSesion);
	}
	 
	@DeleteMapping("/borrarSesion/{idSesion}")
	public String borrarSesion(@PathVariable int idSesion){
		servicio.deleteSesionById(idSesion);
		return "El instructor con el Id: "+ idSesion+" ha sido borrado";
	}
	
	
	
}
