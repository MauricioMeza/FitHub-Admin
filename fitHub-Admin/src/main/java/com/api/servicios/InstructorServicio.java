package com.api.servicios;

import com.api.modelos.Instructor;
import java.util.List;

public interface InstructorServicio {

	Instructor getInstructorByCorreo(String correo);
	Instructor getInstructorByCedula(String cedula);
    Instructor getInstructorByNombre(String nombre);
    List<Instructor> getAllInstructors();
    Instructor addInstructor(Instructor instructor);

}
