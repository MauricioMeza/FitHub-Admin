package com.api.servicios;

import com.api.modelos.Sesion;

import java.util.List;
import java.util.Optional;

public interface SesionServicio {

    public void saveSesion(Sesion sesion);
    public void deleteSesionById(int idSesion);
    public List<Sesion> findAllSesiones();
    public Optional<Sesion> findSesionById(int idSesion);

}
