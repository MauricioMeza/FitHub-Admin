package com.api.servicios;

import com.api.dto.SesionDTO;
import com.api.modelos.Sesion;

import java.util.List;

public interface SesionServicio {

    Sesion addSesion(SesionDTO sesionDTO);
    List<Sesion> findAllSesiones();
    Sesion getSesionById(int idSesion);

}
