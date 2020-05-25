package com.api.servicios;

import com.api.dto.UsuarioDTO;
import com.api.modelos.Usuario;
import java.util.List;

import org.springframework.stereotype.Service;

@Service
public interface UsuarioServicio {

    Usuario getUserByCorreo(String correo);
    Usuario getUserByCedula(String cedula);
    List<Usuario> getAllUsers();
    Usuario addUser(Usuario user);
    void addUsuario(UsuarioDTO usuarioDTO);

}