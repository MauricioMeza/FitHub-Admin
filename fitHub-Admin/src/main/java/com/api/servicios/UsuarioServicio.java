package com.api.servicios;

import com.api.dto.UsuarioDTO;
import com.api.modelos.Usuario;

import java.util.List;
import java.util.Optional;

public interface UsuarioServicio {

    void saveUsuario(UsuarioDTO usuarioDTO);
    void deleteUsuarioById(int id);
    List<Usuario> findAllUsuarios();
    Optional<Usuario> finUsuarioById(int id);
    Usuario getUsuarioByEmail(String email);
    Usuario getUsuarioByCedula(int cedula);

}