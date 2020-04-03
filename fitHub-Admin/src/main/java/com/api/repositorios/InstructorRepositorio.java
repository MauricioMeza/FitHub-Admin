package com.api.repositorios;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.api.modelos.Instructor;

public interface InstructorRepositorio extends MongoRepository<Instructor,Integer>{

}
