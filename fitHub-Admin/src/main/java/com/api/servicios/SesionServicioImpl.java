package com.api.servicios;

import com.api.dto.SesionDTO;
import com.api.modelos.Instructor;
import com.api.modelos.Sesion;
import com.api.repositorios.InstructorRepositorio;
import com.api.repositorios.SesionRepositorio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.List;

@Service
public class SesionServicioImpl implements SesionServicio {

    @Autowired
    SesionRepositorio repositorio;
    @Autowired
    InstructorRepositorio repositorioIns;

    @Override
    public Sesion addSesion(SesionDTO sesionDTO) {
        Sesion nuevaSesion = new Sesion();

        Instructor instructor = repositorioIns.findByNombre(sesionDTO.getInstructor());
        nuevaSesion.setInstructor(instructor);

        Calendar ahora = Calendar.getInstance();
        ahora.setTime(sesionDTO.getFecha());
        ahora.set(Calendar.HOUR_OF_DAY, sesionDTO.getHora());
        ahora.set(Calendar.MINUTE, sesionDTO.getMinuto());

        nuevaSesion.setFecha_hora(ahora.getTime());
        return repositorio.save(nuevaSesion);
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
