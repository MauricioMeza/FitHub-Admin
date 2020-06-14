package com.api.servicios;

import com.api.dto.UsuarioDTO;
import com.api.modelos.TipoPlan;
import com.api.modelos.Usuario;
import com.api.repositorios.TipoPlanRepositorio;
import com.api.repositorios.UsuarioRepositorio;
import com.api.seguridad.JwtProperties;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UsuarioServicioImpl implements UsuarioServicio{

    @Autowired
    UsuarioRepositorio repositorio;
    @Autowired
    TipoPlanServicio servicioTipoPlan;
    @Autowired
    PlanServicio servicioPlan;
    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public Usuario getUserByCorreo(String correo){
        return repositorio.findByCorreo(correo);
    }

    @Override
    public Usuario getUserByCedula(String cedula){
        return repositorio.findByCedula(cedula);
    }

    @Override
    public Usuario getUserByNombre(String nombre){
        return  repositorio.findByNombre(nombre);
    }

    @Override
    public List<Usuario> getAllUsers(){
        return repositorio.findAll();
    }

    @Override
    public Usuario addUser(Usuario user) {
        Usuario usuario = user;
        usuario.setContrasena(passwordEncoder.encode(usuario.getContrasena()));
        return repositorio.save(usuario);
    }
    
    @Override 
    public Usuario updateUser(Usuario user) {
        Usuario usuario = user;
        return repositorio.save(usuario);
    }

    @Override
    public void addUsuario(UsuarioDTO usuarioDTO){
        Usuario user = new Usuario();

        user.setNombre(usuarioDTO.getNombre());
        user.setContrasena(usuarioDTO.getContrasena());
        user.setCorreo(usuarioDTO.getCorreo());
        user.setCedula(usuarioDTO.getCedula());
        user.setRole("USER");
        addUser(user);

        TipoPlan planPrueba = servicioTipoPlan.getTipoPlanByNombre("Plan de Prueba");
        servicioPlan.addNewPlan(planPrueba, user);
        this.updateUser(user);
    };

    @Override
    public Claims infoJWT(String jwt){
        Claims info = Jwts.parser().setSigningKey(JwtProperties.SECRET)
                .parseClaimsJws(jwt)
                .getBody();

        return info;
    }

}