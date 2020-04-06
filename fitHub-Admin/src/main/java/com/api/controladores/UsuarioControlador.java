package com.api.controladores;

import java.util.List;
import java.util.Optional;

import com.api.servicios.UsuarioServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import com.api.modelos.Usuario;

@Controller
@RequestMapping("Usuario")
public class UsuarioControlador {
	
	@Autowired
	private UsuarioServicio servicio;

	@GetMapping("/index")
	public String usuarioIndexGet(){
		return "Usuario/index";
	}
	@PostMapping("/index")
	public String usuarioIndexPost(){
		return "Usuario/index";
	}

	/*
	@GetMapping("/encontrarTodosLosUsuarios")
	public List<Usuario> getUsuarios(){
		return servicio.findAllUsuarios();
	}
	
	@GetMapping("/encontrarUsuario/{id}")
	public Optional<Usuario> getUsuario(@PathVariable int id){
		return servicio.finUsuarioById(id);
	}
	
	@DeleteMapping("/borrarUsuario/{id}")
	public String borrarUsuario(@PathVariable int id){
		servicio.deleteUsuarioById(id);
		return "El usuario con el Id: "+ id+" ha sido borrado";
	}
	*/

	@GetMapping("/encontrarTodosLosUsuarios")
	public List<Usuario> getUsuarios(){
		return servicio.findAllUsuarios();
	}
	
	
}
