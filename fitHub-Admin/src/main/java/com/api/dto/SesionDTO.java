package com.api.dto;

import java.util.Date;
import javax.validation.constraints.NotEmpty;
import com.api.modelos.Instructor;

@fechaFuturaValidacion(message = "La fecha no es una fecha futura")
public class SesionDTO {
    @NotEmpty
    private Date fecha_hora;
    
    @NotEmpty
    private Instructor instructor;
    
	public Date getFecha_hora() {
		return fecha_hora;
	}

	public void setFecha_hora(Date fecha_hora) {
		this.fecha_hora = fecha_hora;
	}

	public Instructor getInstructor() {
		return instructor;
	}

	public void setInstructor(Instructor instructor) {
		this.instructor = instructor;
	}
    
    
    



}
