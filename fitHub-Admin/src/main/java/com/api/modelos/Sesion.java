package com.api.modelos;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection= "Sesion")
public class Sesion {

	@Id
	private String id;
	private Date fecha_hora;
	private String tipo;
	@DBRef
	private Instructor instructor;
	@DBRef
	private List<Usuario> asistentes;
	private int cupos;

	public Sesion(){};

	public Sesion(String id, Date fecha_hora, String tipo, Instructor instructor, List<Usuario> asistentes, int cupos) {
		super();
		this.id = id;
		this.fecha_hora = fecha_hora;
		this.tipo = tipo;
		this.instructor = instructor;
		this.asistentes = asistentes;
		this.cupos = cupos;
	}
	
	public List<Usuario> quitarAsistente(Usuario usuario){
		List<Usuario> asistentes = this.asistentes;
		List<Usuario> asistentes_actualizado = new ArrayList<>();
		for(int i = 0; i < asistentes.size(); i++) {
			if(asistentes.get(i).getCedula() == usuario.getCedula())
				asistentes_actualizado.add(asistentes.get(i));
		}
		return asistentes_actualizado;
	}
	
	public int getCupos() {
		return cupos;
	}

	public void setCupos(int cupos) {
		this.cupos = cupos;
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

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
}
