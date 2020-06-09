package com.api.modelos;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Objects;

@Document(collection = "TipoSesion")
public class TipoSesion {

    @Id
    private String id;
    private String nombre;
    private int cupos;

    public TipoSesion(){};

    public TipoSesion(String nombre, int cupos) {
        super();
        this.nombre = nombre;
        this.cupos = cupos;
    }

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

    @Override
    public String toString() {
        return "TipoSesion{" +
                "id='" + id + '\'' +
                ", nombre='" + nombre + '\'' +
                ", cupos=" + cupos +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TipoSesion that = (TipoSesion) o;
        return cupos == that.cupos &&
                id.equals(that.id) &&
                nombre.equals(that.nombre);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nombre, cupos);
    }
}
