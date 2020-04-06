package com.api.servicios;

import com.api.dto.UsuarioDTO;
import com.api.modelos.Usuario;
import com.api.repositorios.UsuarioRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Iterator;
import java.util.List;
import java.util.Optional;

@Service
public class UsuarioServicioImpl implements UsuarioServicio{

    @Autowired
    UsuarioRepositorio repositorio;
    @Autowired
    BCryptPasswordEncoder encoder;

    @Override
    public void saveUsuario(UsuarioDTO usuarioDTO) {
        Usuario usuario = new Usuario();

        usuario.setCedula(usuarioDTO.getCedula());
        usuario.setCorreo(usuarioDTO.getCorreo());
        usuario.setNombre(usuarioDTO.getNombre());
        usuario.setContrasena(encoder.encode(usuarioDTO.getContrasena()));
        usuario.setRol("USER");

        repositorio.save(usuario);
    }

    @Override
    public void deleteUsuarioById(int id) {
        repositorio.deleteById(id);
    }

    @Override
    public List<Usuario> findAllUsuarios() {
        return repositorio.findAll();
    }

    @Override
    public Optional<Usuario> finUsuarioById(int id) {
        return repositorio.findById(id);
    }

	@Override
	public Usuario getUsuarioByEmail(String email) {
		List<Usuario> usuarios = this.findAllUsuarios();
		Iterator<Usuario> it = usuarios.iterator();
		while(it.hasNext()){
			Usuario user=it.next();
			if(user.getCorreo().equals(email))
				return user; 
		}
		return null;
	}
	
	@Override
	public Usuario getUsuarioByCedula(int cedula) {
		List<Usuario> usuarios = this.findAllUsuarios();
		Iterator<Usuario> it = usuarios.iterator();
		while(it.hasNext()){
			Usuario user=it.next();
			if(user.getCedula()==cedula)
				return user; 
		}
		return null;
	}

}