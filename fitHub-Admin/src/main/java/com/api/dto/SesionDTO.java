package com.api.dto;

import java.util.Date;
import java.util.List;

import javax.validation.constraints.Future;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.api.modelos.Instructor;
import com.api.modelos.TipoSesion;
import com.api.modelos.Usuario;

public class SesionDTO {
	@Future(message = "La fecha debe ser en el futuro")
    private Date fecha;

	@NotEmpty(message = "Tipo de clase vacio")
	private TipoSesion tipoSesion;

    @NotEmpty(message = "Instructor vacio")
    private String instructor;
    
    private List<String> nombresAsistentes;

    private String id;
    
    
	public List<String> getNombresAsistentes() {
		return nombresAsistentes;
	}
	public void setNombresAsistentes(List<String> nombresAsistentes) {
		this.nombresAsistentes = nombresAsistentes;
	}
	public Date getFecha() { return fecha; }
	public void setFecha(Date fecha_hora) { this.fecha = fecha_hora; }

	public String getInstructor() { return instructor; }
	public void setInstructor(String instructor) { this.instructor = instructor; }

	public String getId() { return id; }
	public void setId(String id) { this.id = id; }

	public TipoSesion getTipoSesion() {
		return tipoSesion;
	}

	public void setTipoSesion(TipoSesion tipoSesion) {
		this.tipoSesion = tipoSesion;
	}
}

