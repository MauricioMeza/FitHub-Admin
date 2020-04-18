package com.api.servicios;

import com.api.modelos.Instructor;
import com.api.repositorios.InstructorRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class InstructorServicioImpl implements InstructorServicio {

    @Autowired
    InstructorRepositorio repositorio;

    @Override
    public void saveInstructor(Instructor instructor) {
        repositorio.save(instructor);
    }

    @Override
    public void deleteInstructorById(int cedula) {
        repositorio.deleteById(cedula);
    }

    @Override
    public List<Instructor> findAllInstructores() {
        return repositorio.findAll();
    }

    @Override
    public Optional<Instructor> findInstructorById(int cedula) {
        return repositorio.findById(cedula);
    }

}
