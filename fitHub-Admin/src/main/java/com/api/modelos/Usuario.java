package com.api.modelos;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString

@Document(collection= "Usuario")
public class Usuario {
	
	@Id
	public int cedula;
	public String nombre;
	public String correo;
	
	
	
	public int getId() {
		return cedula;
		
		
	}
	public void setId(int id) {
		this.cedula = id;
	}
	public String getUsuario_Nombre() {
		return nombre;
	}
	public void setUsuario_Nombre(String nombre) {
		this.nombre = nombre;
	}
	public String getUsuario_Apellido() {
		return correo;
	}
	public void setCorreo(String correo) {
		this.correo = correo;
	}
	
}
