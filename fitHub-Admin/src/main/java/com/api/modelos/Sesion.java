package com.api.modelos;

import java.util.Date;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection= "Sesion")
public class Sesion {

	@Id
	public int idSesion;
	public Date fecha_hora;
	public Instructor instructor;
	public List<Integer> asistentes;
	
	
	public int getIdSesion() {
		return idSesion;
	}
	public void setIdSesion(int idSesion) {
		this.idSesion = idSesion;
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
	public List<Integer> getAsistentes() {
		return asistentes;
	}
	public void setAsistentes(List<Integer> asistentes) {
		this.asistentes = asistentes;
	}
	
	
}
