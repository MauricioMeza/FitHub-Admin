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


	@GetMapping("/encontrarTodosLosUsuarios")
	public List<Usuario> getUsuarios(){
		return servicio.getAllUsers();
	}
	
	@GetMapping("/encontrarUsuario/{id}")
	public Usuario getUsuario(@PathVariable String id){
		return servicio.getUserByCedula(id);
	}
	
}
