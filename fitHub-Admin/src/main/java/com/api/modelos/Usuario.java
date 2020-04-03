package com.api.modelos;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


@Document(collection= "Usuario")
public class Usuario {
	
	@Id
	private int cedula;
	private String nombre;
	private String correo;
	private String contrasena;
	@DBRef
	private Plan planActual;

	public int getCedula() { return cedula;}
	public void setCedula(int cedula) { this.cedula = cedula; }

	public String getNombre() { return nombre; }
	public void setNombre(String nombre) { this.nombre = nombre; }

	public String getCorreo() { return correo; }
	public void setCorreo(String correo) { this.correo = correo; }

	public String getContrasena() { return contrasena; }
	public void setContrasena(String contrasena) { this.contrasena = contrasena; }

	public Plan getPlanActual() { return planActual; }
	public void setPlanActual(Plan planActual) { this.planActual = planActual; }
}
