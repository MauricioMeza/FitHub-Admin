package com.api.repositorios;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.api.modelos.Usuario;

public interface UsuarioRepositorio extends MongoRepository<Usuario,Integer>{
    Usuario findUsuarioByCorreo(String correo);
    Usuario findUsuarioByCedula(int cedula);
}
