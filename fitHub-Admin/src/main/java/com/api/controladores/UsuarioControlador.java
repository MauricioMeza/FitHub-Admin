package com.javatechie.spring.mongo.api.controlador;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.javatechie.spring.mongo.api.modelo.Usuario;
import com.javatechie.spring.mongo.api.repositorio.UsuarioRepositorio;

@RestController

public class UsuarioControlador {
	
	@Autowired
	private UsuarioRepositorio repositorio;
	
	@PostMapping("/agregarUsuario")
	public String GuardarUsuario(@RequestBody Usuario usuario) {
		repositorio.save(usuario);
		return "Usuario añadido con id: "+ usuario.getId();
	}
	
	@GetMapping("/encontrarTodosLosUsuarios")
	public List<Usuario> getUsuarios(){
		return repositorio.findAll();
	}
	
	@GetMapping("/encontrarUsuario/{Id}")
	public Optional<Usuario> getUsuario(@PathVariable int id){
		return repositorio.findById(id);
	}
	
	@DeleteMapping("/borrarUsuario/{Id}")
	public String borrarUsuario(@PathVariable int id){
		repositorio.deleteById(id);
		return "El usuario con el Id: "+ id+" ha sido borrado";
	}
	
	
	
}
