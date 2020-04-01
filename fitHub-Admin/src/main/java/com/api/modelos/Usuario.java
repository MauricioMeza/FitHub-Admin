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
	private int id;
	private String Usuario_Nombre;
	private String Usuario_Apellido;
	private int edad;
	
	
	
	public int getId() {
		return id;
		
		
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUsuario_Nombre() {
		return Usuario_Nombre;
	}
	public void setUsuario_Nombre(String usuario_Nombre) {
		Usuario_Nombre = usuario_Nombre;
	}
	public String getUsuario_Apellido() {
		return Usuario_Apellido;
	}
	public void setUsuario_Apellido(String usuario_Apellido) {
		Usuario_Apellido = usuario_Apellido;
	}
	public int getEdad() {
		return edad;
	}
	public void setEdad(int edad) {
		this.edad = edad;
	}
	
	

}
