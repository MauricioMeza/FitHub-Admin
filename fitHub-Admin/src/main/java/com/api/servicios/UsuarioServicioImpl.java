package com.api.servicios;

import com.api.dto.UsuarioDTO;
import com.api.modelos.Usuario;
import com.api.repositorios.UsuarioRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UsuarioServicioImpl implements UsuarioServicio{

    @Autowired
    UsuarioRepositorio repositorio;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public Usuario getUserByCorreo(String correo){
        return repositorio.findByCorreo(correo);
    }

    @Override
    public Usuario getUserByCedula(String cedula){
        return repositorio.findByCedula(cedula);
    }

    @Override
    public List<Usuario> getAllUsers(){
        return repositorio.findAll();
    }

    @Override
    public Usuario addUser(Usuario user) {
        Usuario usuario = user;
        usuario.setContrasena(passwordEncoder.encode(usuario.getContrasena()));
        return repositorio.save(usuario);
    }

    @Override
    public void addUsuario(UsuarioDTO usuarioDTO){
        Usuario user = new Usuario();
        user.setNombre(usuarioDTO.getNombre());
        user.setContrasena(usuarioDTO.getContrasena());
        user.setCorreo(usuarioDTO.getCorreo());
        user.setCedula(usuarioDTO.getCedula());
        user.setRole("USER");
        user.setPlan(usuarioDTO.getPlan());

        addUser(user);
    };

}