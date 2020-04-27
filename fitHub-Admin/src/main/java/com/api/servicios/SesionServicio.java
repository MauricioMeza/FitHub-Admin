package com.api.servicios;

import com.api.modelos.Sesion;

import java.util.List;

public interface SesionServicio {

    Sesion addSesion(Sesion sesion);
    List<Sesion> findAllSesiones();
    Sesion getSesionById(int idSesion);

}
