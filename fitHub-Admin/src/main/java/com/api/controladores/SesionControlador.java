package com.api.controladores;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.api.modelos.Sesion;
import com.api.servicios.SesionServicio;

@RestController
public class SesionControlador {
	
	@Autowired
	private SesionServicio servicio;

	@PostMapping("/agregarSesion")
	public String GuardarInstructor(@RequestBody Sesion sesion) {
		servicio.addSesion(sesion);
		return "Usuario añadido con id: "+ sesion.getIdSesion();
	}
	
	@GetMapping("/encontrarTodasLasSesiones")
	public List<Sesion> getSesiones(){
		return servicio.findAllSesiones();
	}
	
	@GetMapping("/encontrarSesion/{id}")
	public Sesion getSesion(@PathVariable int id){
		return servicio.getSesionById(id);
	}

}