package com.api.servicios;

import com.api.dto.SesionDTO;
import com.api.modelos.Sesion;
import com.api.modelos.Usuario;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
public interface SesionServicio {

	Sesion addSesion(SesionDTO sesionDTO);
    List<Sesion> findAllSesionesByFecha();
    Sesion getSesionById(String idSesion);
    void deleteSesion(Sesion sesion);
    void cambiarSesion(Sesion sesion);
    boolean usuarioInscrito(Sesion sesion, Usuario usuario); 
    String cancelarCupo(Sesion sesion, Usuario usuario);

}
