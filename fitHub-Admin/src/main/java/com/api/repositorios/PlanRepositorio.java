package com.api.repositorios;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.api.modelos.Plan;

public interface PlanRepositorio extends MongoRepository<Plan,Integer>{

}
