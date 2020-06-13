package com.api.servicios;

import com.api.dto.UsuarioDTO;
import com.api.modelos.Usuario;
import java.util.List;

import com.api.seguridad.JwtProperties;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.stereotype.Service;

@Service
public interface UsuarioServicio {

    Usuario getUserByCorreo(String correo);
    Usuario getUserByCedula(String cedula);
    Usuario getUserByNombre(String nombre);
    List<Usuario> getAllUsers();
    Usuario addUser(Usuario user);
    void addUsuario(UsuarioDTO usuarioDTO);
    public Usuario updateUser(Usuario user);
    Claims infoJWT(String jwt);

}