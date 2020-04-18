package com.api.servicios;

import com.api.modelos.Instructor;

import java.util.List;
import java.util.Optional;

public interface InstructorServicio {

    public void saveInstructor(Instructor instructor);
    public void deleteInstructorById(int cedula);
    public List<Instructor> findAllInstructores();
    public Optional<Instructor> findInstructorById(int cedula);

}
