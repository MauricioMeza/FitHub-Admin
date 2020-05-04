package com.api.modelos;

import java.util.Date;
import java.util.List;

import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection= "Sesion")
public class Sesion {

	private Date fecha_hora;
	private String tipo;
	@DBRef
	private Instructor instructor;
	@DBRef
	private List<Usuario> asistentes;

	public Sesion(){};

	public Sesion(Date fecha_hora, String tipo, Instructor instructor, List<Usuario> asistentes) {
		super();
		this.fecha_hora = fecha_hora;
		this.tipo = tipo;
		this.instructor = instructor;
		this.asistentes = asistentes;
	}


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

	public List<Usuario> getAsistentes() {
		return asistentes;
	}
	public void setAsistentes(List<Usuario> asistentes) {
		this.asistentes = asistentes;
	}

	public String getTipo() { return tipo; }
	public void setTipo(String tipo) { this.tipo = tipo; }
}
