package com.api.servicios;

import com.api.dto.UsuarioDTO;
import com.api.modelos.Usuario;
import org.bson.types.ObjectId;

import java.util.List;

public interface UsuarioServicio {

    Usuario getUserById(ObjectId id);
    Usuario getUserByCorreo(String correo);
    List<Usuario> getAllUsers();
    Usuario addUser(Usuario user);
    void addUsuario(UsuarioDTO usuarioDTO);

}