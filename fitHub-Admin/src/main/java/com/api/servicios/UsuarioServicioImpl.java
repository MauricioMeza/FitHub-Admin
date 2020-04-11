package com.api.servicios;

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
    public List<Usuario> getAllUsers(){
        return repositorio.findAll();
    }

    @Override
    public Usuario addUser(Usuario usuario){
        usuario.setContrasena(passwordEncoder.encode(usuario.getContrasena()));
        return repositorio.save(usuario);
    };

}