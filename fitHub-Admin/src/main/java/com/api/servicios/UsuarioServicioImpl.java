package com.api.servicios;

import com.api.dto.UsuarioDTO;
import com.api.modelos.Usuario;
import com.api.repositorios.UsuarioRepositorio;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioServicioImpl implements UsuarioServicio{

    @Autowired
    UsuarioRepositorio repositorio;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public Usuario getUserById(ObjectId id){
        return repositorio.findBy_id(id);
    }

    @Override
    public Usuario getUserByCorreo(String correo){
        return repositorio.findByCorreo(correo);
    }

    @Override
    public Usuario getUserByCedula(int cedula){
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

        addUser(user);
    };

}