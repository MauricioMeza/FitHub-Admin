package com.javatechie.spring.mongo.api.repositorio;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.javatechie.spring.mongo.api.modelo.Usuario;

public interface UsuarioRepositorio extends MongoRepository<Usuario,Integer>{

	
}
