package com.api.servicios;

import com.api.modelos.Sesion;
import com.api.repositorios.SesionRepositorio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SesionServicioImpl implements SesionServicio {

    @Autowired
    SesionRepositorio repositorio;

    @Override
    public void saveSesion(Sesion sesion) {
        repositorio.save(sesion);
    }

    @Override
    public void deleteSesionById(int idSesion) {
        repositorio.deleteById(idSesion);
    }

    @Override
    public List<Sesion> findAllSesiones() {
        return repositorio.findAll();
    }

    @Override
    public Optional<Sesion> findSesionById(int idSesion) {
        return repositorio.findById(idSesion);
    }

}
