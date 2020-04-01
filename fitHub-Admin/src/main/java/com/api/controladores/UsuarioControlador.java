package com.api.controladores;

import java.util.List;
import java.util.Optional;

import com.api.servicios.UsuarioServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.api.modelos.Usuario;

@RestController
public class UsuarioControlador {
	
	@Autowired
	private UsuarioServicio servicio;
	
	@PostMapping("/agregarUsuario")
	public String GuardarUsuario(@RequestBody Usuario usuario) {
		servicio.saveUsuario(usuario);
		return "Usuario añadido con id: "+ usuario.getId();
	}
	
	@GetMapping("/encontrarTodosLosUsuarios")
	public List<Usuario> getUsuarios(){
		return servicio.findAllUsuarios();
	}
	
	@GetMapping("/encontrarUsuario/{Id}")
	public Optional<Usuario> getUsuario(@PathVariable int id){
		return servicio.finUsuarioById(id);
	}
	
	@DeleteMapping("/borrarUsuario/{Id}")
	public String borrarUsuario(@PathVariable int id){
		servicio.deleteUsuarioById(id);
		return "El usuario con el Id: "+ id+" ha sido borrado";
	}
	
	
	
}
