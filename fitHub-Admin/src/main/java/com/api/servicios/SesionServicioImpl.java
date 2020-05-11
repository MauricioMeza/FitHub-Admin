package com.api.servicios;

import com.api.dto.SesionDTO;
import com.api.modelos.Instructor;
import com.api.modelos.Sesion;
import com.api.repositorios.InstructorRepositorio;
import com.api.repositorios.SesionRepositorio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
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
        nuevaSesion.setTipo(sesionDTO.getSesion());
        nuevaSesion.setFecha_hora(sesionDTO.getFecha());

        return repositorio.save(nuevaSesion);
    }

    @Override
    public List<Sesion> findAllSesionesByFecha() {
        return repositorio.findAll(Sort.by(Sort.Direction.ASC, "fecha_hora") );
    }

    @Override
    public Sesion getSesionById(String idSesion) {
        return repositorio.findById(idSesion);
    }

    @Override
    public void deleteSesion(Sesion sesion){
        repositorio.delete(sesion);
    }

}
