package com.api.servicios;

import com.api.dto.SesionDTO;
import com.api.modelos.Sesion;

import java.util.List;

public interface SesionServicio {

    Sesion addSesion(SesionDTO sesionDTO);
    List<Sesion> findAllSesionesByFecha();
    Sesion getSesionById(String idSesion);
    void deleteSesion(Sesion sesion);

}
