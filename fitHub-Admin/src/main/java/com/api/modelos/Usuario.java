package com.api.modelos;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.core.GrantedAuthority;

import java.util.Objects;


@Document(collection= "Usuario")
public class Usuario {

	@Id
	private int cedula;
	private String nombre;
	private String correo;
	private String contrasena;
	private String rol;
	@DBRef
	private Plan planActual;

	public Usuario(){

	}

	public Usuario(int cedula, String nombre, String correo, String contrasena, Plan planActual) {
		super();
		this.cedula = cedula;
		this.nombre = nombre;
		this.correo = correo;
		this.contrasena = contrasena;
		this.planActual = planActual;
	}

	public int getCedula() { return cedula;}
	public void setCedula(int cedula) { this.cedula = cedula; }

	public String getNombre() { return nombre; }
	public void setNombre(String nombre) { this.nombre = nombre; }

	public String getCorreo() { return correo; }
	public void setCorreo(String correo) { this.correo = correo; }

	public String getContrasena() { return contrasena; }
	public void setContrasena(String contrasena) { this.contrasena = contrasena; }

	public String getRol() { return rol; }

	public void setRol(String rol) { this.rol = rol;}

	public Plan getPlanActual() { return planActual; }
	public void setPlanActual(Plan planActual) { this.planActual = planActual; }

	@Override
	public String toString() {
		return "Usuario{" +
				"cedula=" + cedula +
				", nombre='" + nombre + '\'' +
				", correo='" + correo + '\'' +
				", contrasena='" + contrasena + '\'' +
				", planActual=" + planActual +
				'}';
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof Usuario)) return false;
		Usuario usuario = (Usuario) o;
		return cedula == usuario.cedula;
	}

	@Override
	public int hashCode() {
		return Objects.hash(cedula);
	}
}
