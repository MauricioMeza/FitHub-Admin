package com.api.servicios;

import com.api.modelos.Sesion;
import com.api.repositorios.SesionRepositorio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SesionServicioImpl implements SesionServicio {

    @Autowired
    SesionRepositorio repositorio;

    @Override
    public Sesion addSesion(Sesion sesion) {
        return repositorio.save(sesion);
    }

    @Override
    public List<Sesion> findAllSesiones() {
        return repositorio.findAll();
    }

    @Override
    public Sesion getSesionById(int idSesion) {
        return repositorio.findById(idSesion);
    }

}
