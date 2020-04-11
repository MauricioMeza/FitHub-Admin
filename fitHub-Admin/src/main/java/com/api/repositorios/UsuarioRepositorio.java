package com.api.repositorios;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.api.modelos.Usuario;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepositorio extends MongoRepository<Usuario, Integer>{
    //Usuario findBy_id(ObjectId id);
    Usuario findByCorreo(String correo);
    Usuario findByCedula(int cedula);
}
