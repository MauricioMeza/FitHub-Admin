package com.api.dto;

import javax.validation.constraints.NotEmpty;

public class TipoSesionDTO {

    private String id;

    @NotEmpty(message = "Nombre de tipo vacio")
    private String nombre;

    @NotEmpty(message = "Número de cupos vacio")
    private int cupos;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getCupos() {
        return cupos;
    }

    public void setCupos(int cupos) {
        this.cupos = cupos;
    }
}
