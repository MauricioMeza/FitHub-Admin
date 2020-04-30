package com.api.dto;

import java.util.Date;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.api.modelos.Instructor;

@fechaFuturaValidacion(message = "La fecha no es una fecha futura")
public class SesionDTO {
    @NotEmpty
    private Date fecha;
	@NotNull
    private int hora;
	@NotNull
	private int minuto;
    
    @NotEmpty
    private String instructor;
    
	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha_hora) {
		this.fecha = fecha_hora;
	}

	public String getInstructor() {
		return instructor;
	}
	public void setInstructor(String instructor) {
		this.instructor = instructor;
	}

	public int getHora() { return hora; }
	public void setHora(int hora) { this.hora = hora; }

	public int getMinuto() { return minuto; }

	public void setMinuto(int minuto) { this.minuto = minuto; }
}
