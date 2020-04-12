package com.api.modelos;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Objects;


@Document(collection= "Usuario")
public class Usuario {

	@Id
	private String cedula;
	private String nombre;
	private String correo;
	private String contrasena;
	private String role;

	public Usuario(){}

	public Usuario(String nombre, String correo, String cedula, String contrasena, String role) {
		super();
		this.nombre = nombre;
		this.correo = correo;
		this.cedula = cedula;
		this.contrasena = contrasena;
		this.role = role;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public String getCedula() {
		return cedula;
	}

	public void setCedula(String cedula) {
		this.cedula = cedula;
	}

	public String getContrasena() {
		return contrasena;
	}

	public void setContrasena(String contrasena) {
		this.contrasena = contrasena;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	@Override
	public String toString() {
		return "User{" +
				"cedula=" + cedula + + '\'' +
				", nombre='" + nombre + '\'' +
				", correo='" + correo + '\'' +
				", contrasena='" + contrasena + '\'' +
				", role='" + role + '\'' +
				'}';
	}
}
