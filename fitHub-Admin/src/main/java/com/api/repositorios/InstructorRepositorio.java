package com.api.repositorios;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.api.modelos.Instructor;
import com.api.modelos.Usuario;

public interface InstructorRepositorio extends MongoRepository<Instructor,Integer>{

	Instructor findByCorreo(String correo);
	Instructor findByCedula(String cedula);
	Instructor findByNombre(String nombre);
}
