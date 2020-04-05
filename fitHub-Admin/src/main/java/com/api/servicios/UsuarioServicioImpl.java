package com.api.servicios;

import com.api.dto.UsuarioDTO;
import com.api.modelos.Usuario;
import com.api.repositorios.UsuarioRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioServicioImpl implements UsuarioServicio {

    @Autowired
    UsuarioRepositorio repositorio;

    @Override
    public void saveUsuario(UsuarioDTO usuarioDTO) {
        Usuario usuario = new Usuario();

        usuario.setCedula(usuarioDTO.getCedula());
        usuario.setCorreo(usuarioDTO.getCorreo());
        usuario.setNombre(usuarioDTO.getNombre());
        usuario.setContrasena(usuarioDTO.getContrasena());

        repositorio.save(usuario);
    }

    @Override
    public void deleteUsuarioById(int id) {
        repositorio.deleteById(id);
    }

    @Override
    public List<Usuario> findAllUsuarios() {
        return repositorio.findAll();
    }

    @Override
    public Optional<Usuario> finUsuarioById(int id) {
        return repositorio.findById(id);
    }
}
