package com.api.dto;


import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Email;

@contrasenaIgualValidacion
public class UsuarioDTO {
    @NotNull
    @Digits(integer = 10, fraction=0)
    
    @NotEmpty
    private String cedula;

    @NotEmpty
    private String nombre;

    @NotEmpty
    @Email
    private String correo;

    @NotEmpty
    private String contrasena;
    private String contrasenaRep;

    public String getCedula() { return cedula; }
    public void setCedula(String cedula) { this.cedula = cedula; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getCorreo() { return correo; }
    public void setCorreo(String correo) { this.correo = correo; }

    public String getContrasena() { return contrasena; }
    public void setContrasena(String contrasena) { this.contrasena = contrasena; }

    public String getContrasenaRep() { return contrasenaRep; }
    public void setContrasenaRep(String contrasenaRep) { this.contrasenaRep = contrasenaRep; }
}
