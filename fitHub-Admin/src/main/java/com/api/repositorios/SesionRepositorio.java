package com.api.repositorios;

import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.api.modelos.Sesion;

import java.util.List;


public interface SesionRepositorio extends MongoRepository<Sesion,Integer>{
	
	Sesion findById(String id);
	List<Sesion> findAll();
}
