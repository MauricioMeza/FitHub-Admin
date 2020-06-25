package com.api.servicios;

import com.api.dto.SesionDTO;
import com.api.modelos.Sesion;
import com.api.modelos.Usuario;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
public interface SesionServicio {

	Sesion addSesion(SesionDTO sesionDTO);
    Sesion updateSesion(SesionDTO sesionDTO);
    List<Sesion> findAllSesionsByDate();
    Sesion getSesionById(String idSesion);
    void deleteSesion(Sesion sesion);
    void updateSesion(Sesion sesion);
    String deleteUserFromSesion(Sesion sesion, Usuario user);
    String addUserToSesion(String idSesion, String idUser);


}
