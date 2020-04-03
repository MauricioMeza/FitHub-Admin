package com.api.repositorios;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.api.modelos.Sesion;

public interface SesionRepositorio extends MongoRepository<Sesion,Integer>{

}
