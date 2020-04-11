package com.api.modelos;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Objects;


@Document(collection= "Usuario")
public class Usuario {

	@Id
	private ObjectId _id;
	private String nombre;
	private String correo;
	private int cedula;
	private String contrasena;
	private String role;

	public Usuario(){}

	public Usuario(String nombre, String correo, int cedula, String contrasena, String role) {
		super();
		this.nombre = nombre;
		this.correo = correo;
		this.cedula = cedula;
		this.contrasena = contrasena;
		this.role = role;
	}

	public ObjectId get_id() {
		return _id;
	}

	public void set_id(ObjectId _id) {
		this._id = _id;
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

	public int getCedula() {
		return cedula;
	}

	public void setCedula(int cedula) {
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
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof Usuario)) return false;
		Usuario user = (Usuario) o;
		return _id.equals(user._id);
	}

	@Override
	public int hashCode() {
		return Objects.hash(_id);
	}

	@Override
	public String toString() {
		return "User{" +
				"_id=" + _id +
				", nombre='" + nombre + '\'' +
				", correo='" + correo + '\'' +
				", cedula=" + cedula +
				", contrasena='" + contrasena + '\'' +
				", role='" + role + '\'' +
				'}';
	}
}
