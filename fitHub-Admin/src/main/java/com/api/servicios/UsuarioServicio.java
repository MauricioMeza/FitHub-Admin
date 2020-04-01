package com.api.servicios;

import com.api.modelos.Usuario;

import java.util.List;
import java.util.Optional;

public interface UsuarioServicio {

    void saveUsuario(Usuario usuario);
    void deleteUsuarioById(int id);
    List<Usuario> findAllUsuarios();
    Optional<Usuario> finUsuarioById(int id);

}
