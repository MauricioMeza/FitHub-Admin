package com.api.modelos;

import java.util.Date;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection= "Plan")
public class Plan {

	@Id
	public int idPlan;
	public Date fechaInicio;
	public Date fechaFin;
	public int clasesDisponibles;
	public boolean activo;
	public List<Integer> SesionesReservadas;
	
	
	public int getIdPlan() {
		return idPlan;
	}
	public void setIdPlan(int idPlan) {
		this.idPlan = idPlan;
	}
	public Date getFechaInicio() {
		return fechaInicio;
	}
	public void setFechaInicio(Date fechaInicio) {
		this.fechaInicio = fechaInicio;
	}
	public Date getFechaFin() {
		return fechaFin;
	}
	public void setFechaFin(Date fechaFin) {
		this.fechaFin = fechaFin;
	}
	public int getClasesDisponibles() {
		return clasesDisponibles;
	}
	public void setClasesDisponibles(int clasesDisponibles) {
		this.clasesDisponibles = clasesDisponibles;
	}
	public boolean isActivo() {
		return activo;
	}
	public void setActivo(boolean activo) {
		this.activo = activo;
	}
	public List<Integer> getSesionesReservadas() {
		return SesionesReservadas;
	}
	public void setSesionesReservadas(List<Integer> sesionesReservadas) {
		SesionesReservadas = sesionesReservadas;
	}
	
	
	
	
	
	
}
